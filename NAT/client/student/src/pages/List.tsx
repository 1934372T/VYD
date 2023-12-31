// ** Import React
import { useEffect, useState } from "react";

// ** Import MUI
import Box from "@mui/material/Box";
import FormControl from "@mui/material/FormControl";
import Grid from "@mui/material/Grid";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import Modal from "@mui/material/Modal";
import Select, { SelectChangeEvent } from "@mui/material/Select";
import Typography from "@mui/material/Typography";

// ** Import Components
import Template from "pages/Template";
import {
  BaseContainer,
  BaseItem,
  EnhancedTableHeadType,
  GenericTable,
  Title,
} from "components/views/ui";

import { AxiosError, AxiosResponse } from "axios";
import { $axios } from "configs/axios";
import { Button } from "@mui/material";
import { useNavigate } from "react-router-dom";

const header: EnhancedTableHeadType[] = [
  {
    id: "id",
    numeric: true,
    disablePadding: true,
    label: "ID",
  },
  {
    id: "name",
    numeric: true,
    disablePadding: true,
    label: "氏名",
  },
  {
    id: "title",
    numeric: false,
    disablePadding: true,
    label: "題目",
  },
  {
    id: "date",
    numeric: false,
    disablePadding: true,
    label: "発表日",
  },
];

type Presentations = {
  name: string;
  title: string;
  date: Date;
}[];

const style = {
  position: "absolute" as "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: window.innerWidth * 0.5,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

const ListPage = () => {
  const nav = useNavigate();
  const dm = new Map<string, string>();
  dm.set("bachelor", "学士");
  dm.set("master", "修士");
  dm.set("doctor", "博士");

  const [degree, setDegree] = useState<string>("none");
  const [term, setTerm] = useState<string>("none");
  const [terms, setTerms] = useState<string[]>([]);
  const [listTitle, setListTitle] = useState<string | undefined>(undefined);
  const [listData, setListData] = useState<Presentations>([]);
  const [open, setOpen] = useState(false);

  /**
   * for modal
   * stateが沢山あって好ましくない．
   * リファクタリングの必要あり
   */
  const [modalTerm, setModalTerm] = useState<string | undefined>("");
  const [modalTitle, setModalTitle] = useState<string | undefined>("");
  const [modalDegree, setModalDegree] = useState<string | undefined>("");
  const [modalSlideId, setModalSlideId] = useState<number | undefined>();
  const [modalPaperId, setModalPaperId] = useState<number | undefined>();

  const builder = (term: string, degree: string) => {
    var t: string = "";
    var d: string = "";
    if (term === "none") {
      t = "年度未指定 ";
    } else {
      t = term + "年度 ";
    }
    if (degree === "none") {
      d = "";
    } else {
      d = dm.get(degree)!;
    }
    return t + d;
  };

  const handleChangeDegree = (event: SelectChangeEvent) => {
    setDegree(event.target.value);
    setListTitle(builder(term, event.target.value));
  };

  const handleChangeTerm = (event: SelectChangeEvent) => {
    setTerm(event.target.value);
    setListTitle(builder(event.target.value, degree));
  };

  const handleClickRow = (id: number) => {
    console.log(id);
    $axios()
      .get(`presentation/?id=${id}`)
      .then((res: AxiosResponse) => {
        const { data } = res;
        setModalTerm(data.term + "年度");
        setModalTitle(data.title);
        setModalDegree(dm.get(data.degree));
        setModalPaperId(data.paperId);
        setModalSlideId(data.slideId);
        setOpen(true);
      })
      .catch((e: AxiosError) => {
        console.log(e);
      });
  };

  const handleClose = () => setOpen(false);

  useEffect(() => {
    $axios()
      .get("presentation/terms")
      .then((res: AxiosResponse) => {
        const { data } = res;
        setTerms(() => [...data]);
      })
      .catch((e: AxiosError) => {
        console.log(e);
      });
  }, []);

  useEffect(() => {
    $axios()
      .get(`presentation/list?term=${term}&degree=${degree}`)
      .then((res: AxiosResponse<Presentations>) => {
        const { data } = res;
        setListData(() => [...data]);
      })
      .catch((e: AxiosError) => {
        console.log(e);
      });
  }, [term, degree]);

  return (
    <Template name="発表資料一覧">
      <BaseContainer>
        <BaseItem xs={12}>
          <Title title={"フィルタリング"} />
          <Grid container spacing={2}>
            <Grid item xs={6}>
              <FormControl fullWidth size="small">
                <InputLabel id="degree">学位</InputLabel>
                <Select
                  labelId="degree"
                  id="select-degree"
                  value={degree}
                  label="degree"
                  onChange={handleChangeDegree}
                >
                  <MenuItem key={"degree-" + 1} value={"none"}>
                    {"指定なし"}
                  </MenuItem>
                  <MenuItem key={"degree-" + 2} value={"bachelor"}>
                    {"学士"}
                  </MenuItem>
                  <MenuItem key={"degree-" + 3} value={"master"}>
                    {"修士"}
                  </MenuItem>
                  <MenuItem key={"degree-" + 4} value={"doctor"}>
                    {"博士"}
                  </MenuItem>
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={6}>
              <FormControl fullWidth size="small">
                <InputLabel id="term-label">年度</InputLabel>
                <Select
                  labelId="term-label"
                  id="term"
                  value={String(term)}
                  label="term"
                  onChange={handleChangeTerm}
                >
                  <MenuItem key={"term-none"} value={"none"}>
                    {"指定なし"}
                  </MenuItem>
                  {terms.map((term, i) => (
                    <MenuItem key={"term-" + i} value={term}>
                      {term}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>
          </Grid>
        </BaseItem>
        <BaseItem xs={12}>
          {listTitle !== undefined ? <Title title={listTitle} /> : <></>}
          <GenericTable
            headCells={header}
            rows={listData}
            ignoreIndex={1000}
            onClickTableRow={handleClickRow}
            customModal={
              <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
              >
                <Box sx={style}>
                  <Title title={modalTitle} />
                  <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                    {modalTerm + " " + modalDegree}
                  </Typography>
                  <Grid container spacing={3} sx={{ mt: 3 }}>
                    <Grid item xs={6}>
                      {modalPaperId !== undefined ? (
                        <>
                          <Button
                            variant="contained"
                            color={"secondary"}
                            onClick={() => {
                              nav(
                                `/preview?file-type=paper&id=${modalPaperId}`
                              );
                            }}
                            fullWidth
                          >
                            論文閲覧
                          </Button>
                        </>
                      ) : (
                        <></>
                      )}
                    </Grid>
                    <Grid item xs={6}>
                      {modalSlideId !== undefined ? (
                        <>
                          <Button
                            variant="contained"
                            color={"secondary"}
                            onClick={() => {
                              nav(
                                `/preview?file-type=slide&id=${modalSlideId}`
                              );
                            }}
                            fullWidth
                          >
                            発表スライド閲覧
                          </Button>
                        </>
                      ) : (
                        <></>
                      )}
                    </Grid>
                  </Grid>
                </Box>
              </Modal>
            }
          />
        </BaseItem>
      </BaseContainer>
    </Template>
  );
};

export default ListPage;

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

const header: EnhancedTableHeadType[] = [
  {
    id: "id",
    numeric: true,
    disablePadding: true,
    label: "ID",
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
  width: 400,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

const ListPage = () => {
  const [degree, setDegree] = useState<string>("none");
  const [term, setTerm] = useState<string>("none");
  const [terms, setTerms] = useState<string[]>([]);
  const [listTitle, setListTitle] = useState<string | undefined>(undefined);
  const [listData, setListData] = useState<Presentations>([]);
  const [open, setOpen] = useState(false);

  const builder = (term: string, degree: string) => {
    var t: string = "";
    var d: string = "";
    const dm = new Map<string, string>();
    dm.set("bachelor", "学士");
    dm.set("master", "修士");
    dm.set("doctor", "博士");
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
        console.log(data);
      })
      .catch((e: AxiosError) => {
        console.log(e);
      });
    setOpen(true);
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
                  <Title title={"テストモーダル"} />
                  <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                    Duis mollis, est non commodo luctus, nisi erat porttitor
                    ligula.
                  </Typography>
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

// ** Import React

// ** Import MUI

// ** Import Components
import Template from "pages/Template";
import {
  BaseContainer,
  BaseItem,
  EnhancedTableHeadType,
  GenericTable,
  Title,
} from "components/views/ui";
import {
  FormControl,
  Grid,
  InputLabel,
  MenuItem,
  Select,
  SelectChangeEvent,
} from "@mui/material";
import { useEffect, useState } from "react";
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

const ListPage = () => {
  const [degree, setDegree] = useState<string>("none");
  const [term, setTerm] = useState<string>("none");
  const [terms, setTerms] = useState<string[]>([]);
  const [listTitle, setListTitle] = useState<string | undefined>(undefined);
  const [listData, setListData] = useState<Presentations>([]);

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
                  <MenuItem key={1} value={"none"}>
                    {"指定なし"}
                  </MenuItem>
                  <MenuItem key={2} value={"bachelor"}>
                    {"学士"}
                  </MenuItem>
                  <MenuItem key={3} value={"master"}>
                    {"修士"}
                  </MenuItem>
                  <MenuItem key={4} value={"doctor"}>
                    {"博士"}
                  </MenuItem>
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={6}>
              <FormControl fullWidth size="small">
                <InputLabel id="demo-simple-select-label">年度</InputLabel>
                <Select
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  value={String(term)}
                  label="term"
                  onChange={handleChangeTerm}
                >
                  <MenuItem key={-1} value={"none"}>
                    {"指定なし"}
                  </MenuItem>
                  {terms.map((term, i) => (
                    <MenuItem key={i} value={term}>
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
          <GenericTable headCells={header} rows={listData} ignoreIndex={1000} />
        </BaseItem>
      </BaseContainer>
    </Template>
  );
};

export default ListPage;

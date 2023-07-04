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
import axios from "axios";

import "mock/axios";

const header: EnhancedTableHeadType[] = [
  {
    id: "title",
    numeric: false,
    disablePadding: true,
    label: "題目",
  },
  {
    id: "name",
    numeric: false,
    disablePadding: true,
    label: "名前",
  },
  {
    id: "date",
    numeric: false,
    disablePadding: true,
    label: "発表日",
  },
];

const ListPage = () => {
  const [degree, setDegree] = useState<string | undefined>("");
  const [year, setYear] = useState<number | undefined>(2022);
  const [listTitle, setListTitle] = useState<string | undefined>(undefined);
  const [listData, setListData] = useState<any[]>([]);

  const handleChangeDegree = (event: SelectChangeEvent) => {
    setDegree(event.target.value as string);
    setListTitle((year + "年度 " + event.target.value) as string);
  };

  const handleChangeYear = (event: SelectChangeEvent) => {
    setYear(Number(event.target.value));
    setListTitle((event.target.value as string) + "年度 " + degree);
  };

  useEffect(() => {
    axios
      .get("/materials")
      .then((res) => {
        const { data } = res;
        console.log(data);
        setListData(listDataBuilder(data));
      })
      .catch((e) => {
        console.log(e);
      });
  }, []);

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
                  <MenuItem value={""}>指定なし</MenuItem>
                  <MenuItem value={"学士"}>学士</MenuItem>
                  <MenuItem value={"修士"}>修士</MenuItem>
                  <MenuItem value={"博士"}>博士</MenuItem>
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={6}>
              <FormControl fullWidth size="small">
                <InputLabel id="demo-simple-select-label">年度</InputLabel>
                <Select
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  value={String(year)}
                  label="year"
                  onChange={handleChangeYear}
                >
                  <MenuItem value={2022}>{"2022"}</MenuItem>
                  <MenuItem value={2021}>{"2021"}</MenuItem>
                  <MenuItem value={2020}>{"2020"}</MenuItem>
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

const listDataBuilder = (data: any[]) => {
  const res: any[] = [];
  for (let i = 0; i < data.length; i++) {
    res.push({
      title: data[i].title,
      name: data[i].name,
      date: data[i].date,
    });
  }
  return res;
};

export default ListPage;

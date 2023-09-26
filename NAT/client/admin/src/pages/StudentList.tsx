import Template from "pages/Template";
import {
  BaseContainer,
  BaseItem,
  GenericTable,
  EnhancedTableHeadType,
  Title,
} from "components/views/ui";
import { useEffect, useState } from "react";
import { $axios } from "configs/axios";
import { AxiosError, AxiosResponse } from "axios";
// import { SelectChangeEvent } from "@mui/material";
// import { useNavigate } from "react-router-dom";

const header: EnhancedTableHeadType[] = [
  {
    id: "id",
    numeric: true,
    disablePadding: true,
    label: "ID",
  },
  {
    id: "student_id",
    numeric: false,
    disablePadding: true,
    label: "学籍番号",
  },
  {
    id: "name",
    numeric: true,
    disablePadding: true,
    label: "氏名",
  },
  {
    id: "grade",
    numeric: false,
    disablePadding: true,
    label: "学年",
  },
];

type Students = {
  student_id: string;
  name: string;
  grade: string;
}[];

const StudentListPage = () => {
  const [b4, setB4] = useState<Students>([]);
  const [m1, setM1] = useState<Students>([]);
  const [m2, setM2] = useState<Students>([]);
  const [d3, setD3] = useState<Students>([]);
  const [d5, setD5] = useState<Students>([]);

  useEffect(() => {
    $axios()
      .get("student/list?grade=B4")
      .then((res: AxiosResponse) => {
        const { data } = res;
        setB4([]);
        setB4(() => [...data]);
      })
      .catch((e: AxiosError) => {
        console.log(e);
      });
    $axios()
      .get("student/list?grade=M1")
      .then((res: AxiosResponse) => {
        const { data } = res;
        setM1([]);
        setM1(() => [...data]);
      })
      .catch((e: AxiosError) => {
        console.log(e);
      });
    $axios()
      .get("student/list?grade=M2")
      .then((res: AxiosResponse) => {
        const { data } = res;
        setM2([]);
        setM2(() => [...data]);
      })
      .catch((e: AxiosError) => {
        console.log(e);
      });
    $axios()
      .get("student/list?grade=D3")
      .then((res: AxiosResponse) => {
        const { data } = res;
        setD3([]);
        setD3(() => [...data]);
      })
      .catch((e: AxiosError) => {
        console.log(e);
      });
    $axios()
      .get("student/list?grade=D5")
      .then((res: AxiosResponse) => {
        const { data } = res;
        setD5([]);
        setD5(() => [...data]);
      })
      .catch((e: AxiosError) => {
        console.log(e);
      });
  }, []);

  return (
    <Template name={"学生一覧"}>
      <BaseContainer>
        <BaseItem xs={12}>
          <Title title={"B4"} />
          <GenericTable headCells={header} rows={b4} ignoreIndex={1000} />
        </BaseItem>
        <BaseItem xs={12}>
          <Title title={"M1"} />
          <GenericTable headCells={header} rows={m1} ignoreIndex={1000} />
        </BaseItem>
        <BaseItem xs={12}>
          <Title title={"M2"} />
          <GenericTable headCells={header} rows={m2} ignoreIndex={1000} />
        </BaseItem>
        <BaseItem xs={12}>
          <Title title={"D3"} />
          <GenericTable headCells={header} rows={d3} ignoreIndex={1000} />
        </BaseItem>
        <BaseItem xs={12}>
          <Title title={"D5"} />
          <GenericTable headCells={header} rows={d5} ignoreIndex={1000} />
        </BaseItem>
      </BaseContainer>
    </Template>
  );
};

export default StudentListPage;

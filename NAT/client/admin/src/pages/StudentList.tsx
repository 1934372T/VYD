import Template from "pages/Template";
import {
  BaseContainer,
  BaseItem,
  //   EnhancedTableHeadType,
  Title,
} from "components/views/ui";
import { useEffect } from "react";
import { $axios } from "configs/axios";
import { AxiosError, AxiosResponse } from "axios";
// import { SelectChangeEvent } from "@mui/material";
// import { useNavigate } from "react-router-dom";

// const header: EnhancedTableHeadType[] = [
//   {
//     id: "id",
//     numeric: true,
//     disablePadding: true,
//     label: "ID",
//   },
//   {
//     id: "student_id",
//     numeric: false,
//     disablePadding: true,
//     label: "学籍番号",
//   },
//   {
//     id: "name",
//     numeric: true,
//     disablePadding: true,
//     label: "氏名",
//   },
//   {
//     id: "grade",
//     numeric: false,
//     disablePadding: true,
//     label: "学年",
//   },
// ];

// type Students = {
//   student_id: string;
//   name: string;
//   grade: string;
// }[];

const StudentListPage = () => {
  //   const nav = useNavigate();
  //   const dm = new Map<string, string>();
  //   dm.set("bachelor", "学士");
  //   dm.set("master", "修士");
  //   dm.set("doctor", "博士");

  //   const [grade, setGrade] = useState<string>("none");

  useEffect(() => {
    $axios()
      .get("student/list")
      .then((res: AxiosResponse) => {
        const { data } = res;
        console.log(data);
      })
      .catch((e: AxiosError) => {
        console.log(e);
      });
  }, []);

  return (
    <Template name={"学生一覧"}>
      <BaseContainer>
        <BaseItem xs={12}>
          <Title title={"論文管理システム by ボルシャック大和ドラゴン"} />
        </BaseItem>
      </BaseContainer>
    </Template>
  );
};

export default StudentListPage;

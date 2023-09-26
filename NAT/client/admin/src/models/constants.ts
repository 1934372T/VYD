import { EnhancedTableHeadType } from "components/views/ui";

export const WINDOW_INNER_HEIGHT: number = -1;

export const HOME: string = "Home";

export const MEMBER_COLUMS: EnhancedTableHeadType[] = [
  {
    id: "id",
    numeric: false,
    disablePadding: true,
    label: "学籍番号",
  },
  {
    id: "name",
    numeric: false,
    disablePadding: true,
    label: "名前",
  },
  {
    id: "research",
    numeric: false,
    disablePadding: true,
    label: "研究内容",
  },
];

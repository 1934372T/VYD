// ** MUI Import
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import DashboardIcon from "@mui/icons-material/Dashboard";
import FormatListBulletedIcon from "@mui/icons-material/FormatListBulleted";
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import PeopleIcon from '@mui/icons-material/People';
import { HOME } from "models/constants";

const ListItems = () => {
  return (
    <>
      <ListItemButton href="/#/">
        <ListItemIcon>
          <DashboardIcon />
        </ListItemIcon>
        <ListItemText primary={HOME} />
      </ListItemButton>
      <ListItemButton href="/#/student/create">
        <ListItemIcon>
          <PersonAddIcon />
        </ListItemIcon>
        <ListItemText primary={"学生登録"} />
      </ListItemButton>
      <ListItemButton href="/#/student/list">
        <ListItemIcon>
          <PeopleIcon />
        </ListItemIcon>
        <ListItemText primary={"学生一覧"} />
      </ListItemButton>
      <ListItemButton href="/#/paper/list">
        <ListItemIcon>
          <FormatListBulletedIcon />
        </ListItemIcon>
        <ListItemText primary={"資料一覧"} />
      </ListItemButton>
    </>
  );
};

export default ListItems;

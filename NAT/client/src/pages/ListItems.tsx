// ** MUI Import
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import DashboardIcon from "@mui/icons-material/Dashboard";
import DescriptionIcon from "@mui/icons-material/Description";
import FormatListBulletedIcon from "@mui/icons-material/FormatListBulleted";
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
      <ListItemButton href="/#/upload">
        <ListItemIcon>
          <DescriptionIcon />
        </ListItemIcon>
        <ListItemText primary={"資料アップロード"} />
      </ListItemButton>
      <ListItemButton href="/#/list">
        <ListItemIcon>
          <FormatListBulletedIcon />
        </ListItemIcon>
        <ListItemText primary={"資料一覧"} />
      </ListItemButton>
    </>
  );
};

export default ListItems;

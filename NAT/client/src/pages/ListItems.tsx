// ** MUI Import
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import DashboardIcon from "@mui/icons-material/Dashboard";
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
          <DashboardIcon />
        </ListItemIcon>
        <ListItemText primary={"資料アップロード"} />
      </ListItemButton>
    </>
  );
};

export default ListItems;

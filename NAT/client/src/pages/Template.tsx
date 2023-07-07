// ** React Import
import { ReactNode, useEffect, useState } from "react";

// ** MUI Import
import { styled, createTheme, ThemeProvider } from "@mui/material/styles";
import { keyframes } from "@mui/system";
import CssBaseline from "@mui/material/CssBaseline";
import MuiDrawer from "@mui/material/Drawer";
import Box from "@mui/material/Box";
import MuiAppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import List from "@mui/material/List";
import Typography from "@mui/material/Typography";
import Divider from "@mui/material/Divider";
import IconButton from "@mui/material/IconButton";

import MenuIcon from "@mui/icons-material/Menu";
import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";

// ** UI Import
import { HyperLink } from "components/views/ui";

// ** Views Import
import ListItems from "pages/ListItems";
import { Container } from "@mui/material";
import { GitHub } from "@mui/icons-material";
import { $axios } from "configs/axios";
import { AxiosError, AxiosResponse } from "axios";

const drawerWidth = 240;

const fadeInUpAnimation = keyframes`
  0% {
    opacity: 0;
    transform: translateY(30px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
`;

const AppBar = styled(MuiAppBar, {
  shouldForwardProp: (prop) => prop !== "open",
  // @ts-ignore
})(({ theme, open }) => ({
  zIndex: theme.zIndex.drawer + 1,
  transition: theme.transitions.create(["width", "margin"], {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  ...(open && {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(["width", "margin"], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  }),
}));

const Drawer = styled(MuiDrawer, {
  shouldForwardProp: (prop) => prop !== "open",
})(({ theme, open }) => ({
  "& .MuiDrawer-paper": {
    position: "relative",
    whiteSpace: "nowrap",
    width: drawerWidth,
    transition: theme.transitions.create("width", {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
    boxSizing: "border-box",
    ...(!open && {
      overflowX: "hidden",
      transition: theme.transitions.create("width", {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
      }),
      width: theme.spacing(7),
      [theme.breakpoints.up("sm")]: {
        width: theme.spacing(9),
      },
    }),
  },
}));

const mdTheme = createTheme({
  palette: {
    primary: {
      main: "#00008b",
    },
    secondary: {
      // This is green.A700 as hex.
      main: "#87ceeb",
    },
  },
});

const Footer = styled("footer")(({ theme }) => ({
  backgroundColor: "gainsboro",
  padding: theme.spacing(1),
  position: "fixed",
  bottom: 0,
  width: "100%",
  zIndex: theme.zIndex.drawer + 1,
}));

interface TemplateProps {
  children: ReactNode;
  name: string;
}

export default function Template(props: TemplateProps) {
  const { name, children } = props;
  const [open, setOpen] = useState(true);
  const toggleDrawer = () => {
    setOpen(!open);
  };
  const [show, setShow] = useState<boolean>(false);
  useEffect(() => {
    $axios()
      .post("/auth/is-valid-token")
      .then((res: AxiosResponse) => {
        if (res.status !== 200) {
          window.location.replace("/#/signin");
        } else {
          setShow(true);
        }
      })
      .catch((e: AxiosError) => {
        window.location.replace("/#/signin");
      });
  }, []);

  return show ? (
    <ThemeProvider theme={mdTheme}>
      {/* @ts-ignore */}
      <Box sx={{ display: "flex" }}>
        <CssBaseline />
        {/* @ts-ignore */}
        <AppBar position="absolute" open={open} color={"secondary"}>
          <Toolbar
            sx={{
              pr: "24px", // keep right padding when drawer closed
            }}
          >
            <IconButton
              edge="start"
              color="inherit"
              aria-label="open drawer"
              onClick={toggleDrawer}
              sx={{
                marginRight: "36px",
                ...(open && { display: "none" }),
              }}
            >
              <MenuIcon />
            </IconButton>
            <Typography
              component="h1"
              variant="h5"
              color="inherit"
              noWrap
              sx={{ flexGrow: 1 }}
            >
              {name}
            </Typography>
            <Box
              component="img"
              sx={{
                height: 59,
                width: 170,
              }}
              alt="ES3 Lab."
              src="header.png"
              onClick={() => {
                window.location.replace("/#/");
              }}
            />
          </Toolbar>
        </AppBar>
        <Drawer variant="permanent" open={open}>
          <Toolbar
            sx={{
              display: "flex",
              alignItems: "center",
              justifyContent: "flex-end",
              backgroundColor: "gainsboro",
              px: [1],
            }}
          >
            <IconButton onClick={toggleDrawer}>
              <ChevronLeftIcon />
            </IconButton>
          </Toolbar>
          <Divider />
          <List component="nav">
            <ListItems />
          </List>
        </Drawer>
        <Box
          component="main"
          sx={{
            backgroundColor: (theme) => theme.palette.grey[300],
            flexGrow: 1,
            height: "100vh",
            overflow: "auto",
          }}
        >
          <Toolbar />
          <Box
            sx={{
              animation: `${fadeInUpAnimation} 1s ease`,
              animationFillMode: "forwards",
              opacity: 0,
              transform: "translateY(30px)",
            }}
          >
            {children}
          </Box>
        </Box>
        <Footer>
          <Container
            maxWidth="lg"
            sx={{ display: "flex", justifyContent: "center" }}
          >
            <HyperLink
              url={"https://github.com/ES3-Kobe-U"}
              data={<GitHub sx={{ ml: 1, mr: 1 }} />}
            />
            Copyright © ES3 Lab. All Rights Reserved.
          </Container>
        </Footer>
      </Box>
    </ThemeProvider>
  ) : (
    <></>
  );
}

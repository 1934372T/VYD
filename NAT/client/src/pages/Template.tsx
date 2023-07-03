// ** React Import
import React, { useEffect, useState } from "react";

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
import Paper from "@mui/material/Paper";
import TableContainer from "@mui/material/TableContainer";
import TableBody from "@mui/material/TableBody";
import TablePagination from "@mui/material/TablePagination";
import Table from "@mui/material/Table";
import TableCell from "@mui/material/TableCell";
import TableRow from "@mui/material/TableRow";
import MenuIcon from "@mui/icons-material/Menu";
import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";

// ** UI Import
import {
  EnhancedTableHead,
  EnhancedTableHeadType,
  HyperLink,
} from "components/views/ui";

// ** Views Import
import ListItems from "pages/ListItems";
import { Container } from "@mui/material";
import { GitHub } from "@mui/icons-material";

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
  children: React.ReactNode;
  name: string;
}

export default function Template(props: TemplateProps) {
  const { name, children } = props;
  const [open, setOpen] = useState(true);
  const toggleDrawer = () => {
    setOpen(!open);
  };
  useEffect(() => {}, [props]);

  return (
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
  );
}

interface GenericTableProps {
  headCells: EnhancedTableHeadType[];
  rows: any[];
  ignoreIndex: number; // どのインデックス以降で表示を無視するか
  rowColorJuder?: (v: any) => string;
}

export const GenericTable = (props: GenericTableProps) => {
  const { headCells, rows, ignoreIndex, rowColorJuder } = props;
  const [page, setPage] = useState<number>(0);
  const [rowsPerPage, setRowsPerPage] = useState<number>(10);
  const emptyRows =
    page > 0 ? Math.max(0, (1 + page) * rowsPerPage - rows.length) : 0; // Avoid a layout jump when reaching the last page with empty rows.
  // @ts-ignore
  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };
  // @ts-ignore
  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };
  return (
    <>
      <Box
        sx={{
          width: "100%",
        }}
      >
        <Paper sx={{ width: "100%", mb: 2 }}>
          <TableContainer>
            <Table
              sx={{ width: "flex" }}
              aria-labelledby="tableTitle"
              size={"medium"}
            >
              <EnhancedTableHead headCells={headCells} />
              <TableBody>
                {rows
                  .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                  .map((row, index) => {
                    const labelId = `enhanced-table-checkbox-${index}`;
                    return (
                      <>
                        <TableRow
                          tabIndex={-1}
                          key={index}
                          sx={{
                            // @ts-ignore
                            backgroundColor: rowColorJuder
                              ? rowColorJuder(row)
                              : "white",
                            height: (window.innerHeight * 0.8) / 12,
                          }}
                        >
                          {Object.values(row).map((v, i) => {
                            if (i >= ignoreIndex) {
                              return null;
                            } else {
                              return (
                                <>
                                  <TableCell
                                    component="th"
                                    key={labelId + String(v)}
                                    id={labelId}
                                    scope="row"
                                    padding="none"
                                    align="center"
                                  >
                                    {<>{v}</>}
                                  </TableCell>
                                </>
                              );
                            }
                          })}
                        </TableRow>
                      </>
                    );
                  })}
                {emptyRows > 0 && (
                  <TableRow
                    style={{
                      height: ((window.innerHeight * 0.8) / 12) * emptyRows,
                    }}
                  >
                    <TableCell colSpan={6} />
                  </TableRow>
                )}
              </TableBody>
            </Table>
          </TableContainer>
          <TablePagination
            rowsPerPageOptions={[10]}
            component="div"
            count={rows.length}
            rowsPerPage={rowsPerPage}
            page={page}
            onPageChange={handleChangePage}
            onRowsPerPageChange={handleChangeRowsPerPage}
          />
        </Paper>
      </Box>
    </>
  );
};

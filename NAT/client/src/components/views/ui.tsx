// ** React Import
import { ReactNode, useState } from "react";

// ** MUI Import
import TableCell from "@mui/material/TableCell";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Typography from "@mui/material/Typography";
import Grid from "@mui/material/Grid";
import Container from "@mui/material/Container";
import Paper from "@mui/material/Paper";
import TableContainer from "@mui/material/TableContainer";
import TableBody from "@mui/material/TableBody";
import Table from "@mui/material/Table";
import {
  Box,
  Card,
  CardActionArea,
  CardContent,
  TablePagination,
} from "@mui/material";
import { GenericListsType } from "models/types";

export type EnhancedTableHeadType = {
  id: string;
  numeric: boolean;
  disablePadding: boolean;
  label: string;
};

interface EnhancedTableHeadProps {
  headCells: EnhancedTableHeadType[];
}

export const EnhancedTableHead = (props: EnhancedTableHeadProps) => {
  const { headCells } = props;
  return (
    <TableHead sx={{ backgroundColor: "gainsboro" }}>
      <TableRow sx={{ height: 50 }}>
        {headCells.map((headCell) => (
          <TableCell
            key={headCell.id}
            align={"center"}
            padding={headCell.disablePadding ? "none" : "normal"}
          >
            {headCell.label}
          </TableCell>
        ))}
      </TableRow>
    </TableHead>
  );
};

interface TitleProps {
  title: ReactNode;
}

export const Title = (props: TitleProps) => {
  const { title } = props;
  return (
    <Typography gutterBottom variant="h5" component="div">
      {title}
    </Typography>
  );
};

interface BaseContainerProps {
  children: ReactNode;
}

export const BaseContainer = (props: BaseContainerProps) => {
  const { children } = props;
  return (
    <>
      <Container maxWidth="xl" sx={{ mt: 2, mb: 4 }}>
        <Grid container spacing={3}>
          {children}
        </Grid>
      </Container>
    </>
  );
};

interface BaseItemProps {
  xs: number;
  children: ReactNode;
}

export const BaseItem = (props: BaseItemProps) => {
  const { xs, children } = props;
  return (
    <>
      <Grid item xs={xs}>
        <Paper
          sx={{
            p: 2,
            display: "flex",
            flexDirection: "column",
            height: "flex",
          }}
        >
          {children}
        </Paper>
      </Grid>
    </>
  );
};

interface HyperLinkProps {
  url: string;
  data: ReactNode;
}

export const HyperLink = (props: HyperLinkProps) => {
  const { url, data } = props;
  return (
    <a href={url} target="_blank" rel="noopener noreferrer">
      {data}
    </a>
  );
};

interface SecondaryTextProps {
  data: ReactNode;
}

export const SecondaryText = (props: SecondaryTextProps) => {
  const { data } = props;
  return (
    <Typography variant="body2" color="text.secondary">
      {data}
    </Typography>
  );
};

interface SingleCardItemProps {
  children: ReactNode;
}

export const SingleCardItem = (props: SingleCardItemProps) => {
  const { children } = props;
  return (
    <Card sx={{ maxWidth: "100%", mt: 1, mb: 1 }}>
      <CardActionArea>
        <CardContent>{children}</CardContent>
      </CardActionArea>
    </Card>
  );
};

interface GenericListProps {
  data: GenericListsType;
}

export const GenericList = (props: GenericListProps) => {
  const { data } = props;
  return (
    <>
      <ul className="list">
        {data.map((d, i) => (
          <li key={i}>
            {d.url !== undefined ? (
              <HyperLink url={d.url} data={d.text} />
            ) : (
              <>{d.text}</>
            )}
          </li>
        ))}
      </ul>
    </>
  );
};

interface GenericTableProps {
  headCells: EnhancedTableHeadType[];
  rows: any[];
  ignoreIndex: number; // どのインデックス以降で表示を無視するか
  customModal?: ReactNode;
  onClickTableRow?: (v: any) => void;
}

export const GenericTable = (props: GenericTableProps) => {
  const { headCells, rows, ignoreIndex, customModal, onClickTableRow } = props;
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
    <Box sx={{ width: "100%" }}>
      <Paper sx={{ width: "100%" }}>
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
                    <TableRow
                      tabIndex={-1}
                      key={index}
                      hover
                      sx={{
                        backgroundColor: "white",
                        height: (window.innerHeight * 0.8) / 12,
                      }}
                      onClick={
                        // eslint-disable-next-line @typescript-eslint/no-empty-function
                        onClickTableRow ? () => onClickTableRow(row) : () => {}
                      }
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
            {customModal}
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
  );
};

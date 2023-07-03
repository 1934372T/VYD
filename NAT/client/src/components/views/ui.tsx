// ** React Import
import { ReactNode } from "react";

// ** MUI Import
import TableCell from "@mui/material/TableCell";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Typography from "@mui/material/Typography";
import Grid from "@mui/material/Grid";
import Paper from "@mui/material/Paper";
import Container from "@mui/material/Container";
import { Card, CardActionArea, CardContent } from "@mui/material";
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
    <TableHead>
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

import {
  TimelineConnector,
  TimelineContent,
  TimelineDot,
  TimelineItem,
  TimelineOppositeContent,
  TimelineSeparator,
} from "@mui/lab";
import {
  Button,
  Card,
  CardActions,
  CardContent,
  CardMedia,
  Grid,
} from "@mui/material";
import Typography from "@mui/material/Typography";
import { ReactNode } from "react";

interface TimeLineItemProps {
  icon: ReactNode;
  date: string;
  title: string;
  detail: ReactNode;
}

export const TimeLineItem = (props: TimeLineItemProps) => {
  const { icon, date, title, detail } = props;
  return (
    <TimelineItem>
      <TimelineOppositeContent
        sx={{ m: "auto 0" }}
        variant="body2"
        color="textSecondary"
      >
        {date}
      </TimelineOppositeContent>
      <TimelineSeparator>
        <TimelineDot color="secondary">{icon}</TimelineDot>
        <TimelineConnector />
      </TimelineSeparator>
      <TimelineContent sx={{ py: "12px", px: 2 }}>
        <Typography variant="h6" component="span">
          {title}
        </Typography>
        <Typography>{detail}</Typography>
      </TimelineContent>
    </TimelineItem>
  );
};

interface CardItemProps {
  title: string;
  details: string;
  url: string;
}

export const CardItem = (props: CardItemProps) => {
  const { title, details, url } = props;
  return (
    <Grid item xs={6} sm={6} md={4}>
      <Card
        sx={{
          height: "100%",
          display: "flex",
          flexDirection: "column",
        }}
      >
        <CardMedia
          component="div"
          sx={{
            height: 0,
            paddingTop: "75%", // 4:3 aspect ratio
            backgroundSize: "75%", // this line controls the size of the image
          }}
          image={url}
        />
        <CardContent sx={{ flexGrow: 1 }}>
          <Typography gutterBottom variant="h5" component="h2">
            {title}
          </Typography>
          <Typography>{details}</Typography>
        </CardContent>
        <CardActions>
          <Button size="small">編集</Button>
        </CardActions>
      </Card>
    </Grid>
  );
};

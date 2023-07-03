// ** Import React
import { ChangeEvent, useState } from "react";

// ** Import MUI
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import Backdrop from "@mui/material/Backdrop";
import CircularProgress from "@mui/material/CircularProgress";

// ** Import Components
import Template from "pages/Template";
import { BaseContainer, BaseItem, Title } from "components/views/ui";
import { FileUploader } from "components/utils/FileUploader";
import { DatePickers } from "components/utils/DatePicker";

const UploadPage = () => {
  const [open] = useState<boolean>(false);
  const [url, setUrl] = useState<string>("");

  const handleChangeInputData = (e: ChangeEvent<HTMLInputElement>) => {
    e.preventDefault();
    if (e.target.value) {
      setUrl(e.target.value);
      console.log(url);
    }
  };

  return (
    <Template name="発表資料アップロード">
      <BaseContainer>
        <BaseItem xs={12}>
          <Title title={"基本情報"} />
          <Grid container spacing={3}>
            <Grid item xs={6}>
              <Typography>題目</Typography>
              <TextField
                fullWidth
                id="fullWidth"
                size="small"
                onChange={handleChangeInputData}
              />
            </Grid>
            <Grid item xs={6}>
              <Typography>発表日時</Typography>
              <DatePickers onChangeDate={() => {}} />
            </Grid>
          </Grid>
        </BaseItem>
        <BaseItem xs={12}>
          <Title title={"資料"} />
          <FileUploader />
        </BaseItem>
      </BaseContainer>
      <Backdrop open={open}>
        <CircularProgress color={"secondary"} />
      </Backdrop>
    </Template>
  );
};

export default UploadPage;

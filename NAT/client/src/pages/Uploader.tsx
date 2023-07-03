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
import { MultiTextForm } from "components/utils/MultiTextForm";
import { Button } from "@mui/material";

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
          <Grid container spacing={3} sx={{ mb: 2 }}>
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
            <Grid item xs={12}>
              <Typography>備考</Typography>
              <MultiTextForm />
            </Grid>
          </Grid>
          <Title title={"資料"} />
          <FileUploader />
          <Grid container spacing={2} sx={{ mt: 1 }} justifyContent="flex-end">
            <Grid item>
              <Button variant="contained" color="secondary">
                登録
              </Button>
            </Grid>
            <Grid item>
              <Button variant="contained" color="secondary">
                キャンセル
              </Button>
            </Grid>
          </Grid>
        </BaseItem>
      </BaseContainer>
      <Backdrop open={open}>
        <CircularProgress color={"secondary"} />
      </Backdrop>
    </Template>
  );
};

export default UploadPage;

// ** Import React
import { ChangeEvent, FormEvent, useState } from "react";

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
import { $axios } from "configs/axios";
import { AxiosError, AxiosResponse } from "axios";
import { useNavigate } from "react-router-dom";

const UploadPage = () => {
  const [open, setOpen] = useState<boolean>(false);
  const [title, setTitle] = useState<string>("");
  const [note, setNote] = useState<string>("");
  const [date, setDate] = useState<string | undefined>();
  const [paperFile, setPaperFile] = useState<File | null>(null);
  const [slideFile, setSlideFile] = useState<File | null>(null);

  const router = useNavigate();

  const handleChangeTitle = (e: ChangeEvent<HTMLInputElement>) => {
    e.preventDefault();
    if (e.target.value) {
      setTitle(e.target.value);
    }
  };

  const handleSubmit = async (event: FormEvent) => {
    event.preventDefault();
    setOpen(true);

    const formData = new FormData();
    formData.append("title", title);
    formData.append("note", note);
    formData.append("date", date!);
    if (paperFile) {
      console.log("paper");
      formData.append("paper", paperFile);
    }
    if (slideFile) {
      console.log("slide");
      formData.append("slide", slideFile);
    }

    $axios({ multipart: true })
      .post("presentation/upload", formData)
      .then((res: AxiosResponse) => {
        router("/list");
      })
      .catch((e: AxiosError) => {
        console.log(e);
      });
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
                onChange={handleChangeTitle}
              />
            </Grid>
            <Grid item xs={6}>
              <Typography>発表日時</Typography>
              <DatePickers onChangeDate={setDate} />
            </Grid>
            <Grid item xs={12}>
              <Typography>備考</Typography>
              <MultiTextForm onChange={setNote} />
            </Grid>
          </Grid>
          <Title title={"論文"} />
          <FileUploader
            title={
              "アップロードしたい論文（pdf）をドラッグ&ドロップしてください．"
            }
            onUpload={setPaperFile}
          />
          <Title title={"発表スライド"} />
          <FileUploader
            title={
              "アップロードしたい発表スライド（pdf or pptx）をドラッグ&ドロップしてください．"
            }
            onUpload={setSlideFile}
          />
          <Grid container spacing={2} sx={{ mt: 1 }} justifyContent="flex-end">
            <Grid item>
              <Button
                variant="contained"
                color="secondary"
                onClick={handleSubmit}
              >
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

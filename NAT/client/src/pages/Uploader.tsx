// ** Import React
import { ChangeEvent, useState, useCallback } from "react";

// ** Import MUI
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import Select, { SelectChangeEvent } from "@mui/material/Select";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Backdrop from "@mui/material/Backdrop";
import CircularProgress from "@mui/material/CircularProgress";

// ** Import utils
import { useDropzone } from "react-dropzone";

// ** Import Components
import Template from "pages/Template";
import { BaseContainer, BaseItem } from "components/views/ui";

const UploadPage = () => {
  const [file, setFile] = useState<File | null>(null);
  const [preview, setPreview] = useState<string | null>(null);
  const [open, setOpen] = useState<boolean>(false);
  const [logoSize, setLogoSize] = useState<string>("");
  const [url, setUrl] = useState<string>("");

  const onDrop = useCallback((acceptedFiles: File[]) => {
    setFile(acceptedFiles[0]);
    setPreview(URL.createObjectURL(acceptedFiles[0]));
  }, []);

  const { getRootProps, getInputProps } = useDropzone({ onDrop });

  const handleChangeLogoSize = (e: SelectChangeEvent) => {
    e.preventDefault();
    setLogoSize(e.target.value);
  };

  const handleChangeInputData = (e: ChangeEvent<HTMLInputElement>) => {
    e.preventDefault();
    if (e.target.value) {
      setUrl(e.target.value);
    }
  };

  const handleFileDelete = () => {
    setFile(null);
    setPreview(null);
  };

  return (
    <Template name="発表資料アップロード">
      <BaseContainer>
        <BaseItem xs={12}>
          <Container maxWidth="xl" sx={{ mt: 4, mb: 4 }}>
            <Grid container spacing={3}>
              <Grid item xs={6}>
                <Typography>QRコードに対するロゴの大きさ</Typography>
                <FormControl fullWidth size="small">
                  <Select value={logoSize} onChange={handleChangeLogoSize}>
                    <MenuItem value={""}>
                      <em>未選択</em>
                    </MenuItem>
                    <MenuItem value={"too-large"}>極大</MenuItem>
                    <MenuItem value={"large"}>大</MenuItem>
                    <MenuItem value={"medium"}>中</MenuItem>
                    <MenuItem value={"small"}>小</MenuItem>
                    <MenuItem value={"too-small"}>極小</MenuItem>
                  </Select>
                </FormControl>
              </Grid>
              <Grid item xs={6}>
                <Typography>
                  QRコードに埋め込むデータを入力してください．
                </Typography>
                <TextField
                  fullWidth
                  id="fullWidth"
                  size="small"
                  onChange={handleChangeInputData}
                />
              </Grid>
            </Grid>
          </Container>
          <Container maxWidth="xl" sx={{ mt: 4, mb: 4 }}>
            <Grid container spacing={4}>
              <Grid item xs={6}>
                {file ? (
                  <>
                    {" "}
                    <Button
                      variant="contained"
                      color={"secondary"}
                      onClick={handleFileDelete}
                      sx={{ mt: 3 }}
                    >
                      ファイルを削除する
                    </Button>
                  </>
                ) : (
                  <>
                    <div {...getRootProps()}>
                      <input {...getInputProps()} />
                      <Typography>
                        使用する画像をここにドラッグ＆ドロップしてください．
                      </Typography>
                    </div>
                  </>
                )}
              </Grid>
              <Grid item xs={6}>
                {file && (
                  <Typography>選択されたファイル: {file.name}</Typography>
                )}
                {preview && (
                  <img
                    src={preview}
                    alt="preview"
                    style={{
                      width: "100px", // 幅を指定
                    }}
                  />
                )}
              </Grid>
            </Grid>
          </Container>
        </BaseItem>
      </BaseContainer>
      <Backdrop open={open}>
        <CircularProgress color={"secondary"} />
      </Backdrop>
    </Template>
  );
};

export default UploadPage;

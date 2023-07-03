// ** Import React
import { useState, useCallback } from "react";

import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";

// ** Import utils
import { useDropzone } from "react-dropzone";

export const FileUploader = () => {
  const [file, setFile] = useState<File | null>(null);
  const [preview, setPreview] = useState<string | null>(null);

  const onDrop = useCallback((acceptedFiles: File[]) => {
    setFile(acceptedFiles[0]);
    setPreview(URL.createObjectURL(acceptedFiles[0]));
  }, []);

  const { getRootProps, getInputProps } = useDropzone({ onDrop });

  const handleFileDelete = () => {
    setFile(null);
    setPreview(null);
  };

  return (
    <Grid container spacing={3} sx={{ mb: 1 }}>
      {file ? (
        <>
          <Grid item xs={6}>
            {file && <Typography>選択されたファイル: {file.name}</Typography>}
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
          <Grid item xs={6}>
            <Button
              variant="contained"
              color={"secondary"}
              onClick={handleFileDelete}
              sx={{ mt: 3 }}
            >
              ファイルを削除する
            </Button>
          </Grid>
        </>
      ) : (
        <Grid item xs={12}>
          <div
            {...getRootProps()}
            style={{
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
              height: "100%",
            }}
          >
            <input {...getInputProps()} />
            <Card
              sx={{
                backgroundColor: "gainsboro",
                width: "100%",
                height: "100px",
              }}
            >
              <CardContent>
                <Typography align="center">
                  アップロードしたい資料をここにドラッグ＆ドロップしてください．
                </Typography>
              </CardContent>
            </Card>
          </div>
        </Grid>
      )}
    </Grid>
  );
};

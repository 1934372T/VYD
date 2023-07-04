import { useState, useCallback } from "react";

import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";

import { useDropzone } from "react-dropzone";

interface FileUploaderProps {
  title: string;
}

export const FileUploader = (props: FileUploaderProps) => {
  const { title } = props;
  const [file, setFile] = useState<File | null>(null);
  const [preview, setPreview] = useState<string | null>(null);

  const onDrop = useCallback((acceptedFiles: File[]) => {
    setFile(acceptedFiles[0]);
    setPreview(URL.createObjectURL(acceptedFiles[0]));
  }, []);

  const { getRootProps, getInputProps } = useDropzone({
    onDrop,
    accept: {
      "application/pdf": [],
      "application/vnd.openxmlformats-officedocument.presentationml.presentation": [],
    },
  });

  const handleFileDelete = () => {
    setFile(null);
    setPreview(null);
  };

  return (
    <Grid container spacing={3} sx={{ mb: 1 }}>
      {file ? (
        <Grid item xs={12}>
          <Grid container spacing={3} sx={{ mb: 3 }}>
            <Grid item xs={6}>
              <Typography>選択されたファイル: {file.name}</Typography>
            </Grid>
            <Grid item xs={6}>
              <Button
                variant="contained"
                color={"secondary"}
                onClick={handleFileDelete}
              >
                ファイルを削除する
              </Button>
            </Grid>
          </Grid>

          {file.type ===
          "application/vnd.openxmlformats-officedocument.presentationml.presentation" ? (
            <Typography color="error">プレビューは利用できません</Typography>
          ) : (
            preview && (
              <object
                data={preview}
                type="application/pdf"
                style={{ width: "100%", height: "500px" }}
              >
                PDFプレビューが表示できません
              </object>
            )
          )}
        </Grid>
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
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
              }}
            >
              <CardContent>
                <Typography align="center">{title}</Typography>
              </CardContent>
            </Card>
          </div>
        </Grid>
      )}
    </Grid>
  );
};

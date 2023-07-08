import { FormEvent, useState } from "react";
import { useNavigate } from "react-router-dom";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import Typography from "@mui/material/Typography";
import Grid from "@mui/material/Grid";
import Button from "@mui/material/Button";
import axios, { AxiosError, AxiosResponse } from "axios";
import { HOST_URL } from "configs/api";
import { BaseBackdrop, BaseForm } from "components/views/ui";
import { style } from "components/views/style";
import keys from "configs/keys";

const SignInPage = () => {
  const router = useNavigate();
  const [open, setOpen] = useState<boolean>(false);
  const [openModal, setOpenModal] = useState<boolean>(false);
  const [errorMsg, setErrorMsg] = useState<string>("");
  const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setOpen(true);
    const data = new FormData(event.currentTarget);
    axios
      .post(HOST_URL + "auth/signin", {
        student_id: data.get("student_id"),
        password: data.get("password"),
      })
      .then((res: AxiosResponse) => {
        const { data } = res;
        if (res.status === 200) {
          setOpen(false);
          console.log(data);
          window.localStorage.setItem(keys.accessToken, String(data));
          router("/#/");
        } else {
          setOpen(false);
          // @ts-ignore
          setErrorMsg("サインインに失敗しました．");
          setOpenModal(true);
        }
      })
      .catch((e: AxiosError) => {
        setOpen(false);
        // @ts-ignore
        setErrorMsg(e.response?.data.message);
        setOpenModal(true);
      });
  };

  return (
    <BaseForm
      formTitle="サインイン"
      buttonTitle="送信"
      handleSubmit={handleSubmit}
      mode="signin"
    >
      <TextField
        margin="normal"
        required
        fullWidth
        id="student_id"
        label="学籍番号"
        name="student_id"
        autoComplete="student_id"
        autoFocus
      />
      <TextField
        margin="normal"
        required
        fullWidth
        name="password"
        label="パスワード"
        type="password"
        id="password"
        autoComplete="current-password"
      />
      <BaseBackdrop open={open} />
      <Modal open={openModal}>
        <Box sx={style}>
          <Typography
            id="modal-modal-title"
            variant="h6"
            component="h2"
            align="center"
          >
            サインイン失敗
          </Typography>
          <Typography id="modal-modal-description" sx={{ mt: 2 }}>
            {errorMsg}
          </Typography>
          <Typography id="modal-modal-description" sx={{ mt: 2 }}>
            再度サインインしてください．
          </Typography>
          <Grid container>
            <Grid item xs={12} sx={{ mt: 2 }}>
              <Button
                variant="contained"
                fullWidth
                onClick={() => {
                  window.location.reload();
                }}
              >
                戻る
              </Button>
            </Grid>
          </Grid>
        </Box>
      </Modal>
    </BaseForm>
  );
};

export default SignInPage;

import { FormEvent, useState } from "react";

// Import MUI
import TextField from "@mui/material/TextField";
import Modal from "@mui/material/Modal";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Grid from "@mui/material/Grid";
import Button from "@mui/material/Button";

// Import Utils
import axios, { AxiosResponse } from "axios";
import { useNavigate } from "react-router-dom";

import { BaseForm } from "components/views/ui";
import { HOST_URL } from "configs/api";
import { BaseBackdrop } from "components/views/ui";
import { style } from "components/views/style";

const SignUpPage = () => {
  const router = useNavigate();
  const [open, setOpen] = useState<boolean>(false);
  const [openModal, setOpenModal] = useState<boolean>(false);
  const [errorMsg, setErrorMsg] = useState<string>("");

  const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setOpen(true);
    const data = new FormData(event.currentTarget);
    axios
      .post(HOST_URL + "auth/admin/signup", {
        email: data.get("email"),
        password: data.get("password"),
        first_name: data.get("first_name"),
        last_name: data.get("last_name"),
      })
      .then((res: AxiosResponse) => {
        if (res.status === 200) {
          setOpen(false);
          setOpenModal(true);
        } else {
          setOpen(false);
          // @ts-ignore
          setErrorMsg(e.response?.data.message);
          setOpenModal(true);
        }
      })
      .catch((e) => {
        setOpen(false);
        // @ts-ignore
        setErrorMsg(e.response?.data.message);
        setOpenModal(true);
      });
  };

  const handleGoToSignIn = () => {
    router("/#/signin");
  };

  return (
    <BaseForm
      formTitle="サインアップ（管理者）"
      buttonTitle="作成"
      handleSubmit={handleSubmit}
      mode="signup"
    >
      <TextField
        margin="normal"
        required
        fullWidth
        id="email"
        label="メールアドレス"
        name="email"
        autoComplete="email"
        autoFocus
      />
      <TextField
        margin="normal"
        required
        fullWidth
        id="last_name"
        label="名字"
        name="last_name"
        autoComplete="last_name"
      />
      <TextField
        margin="normal"
        required
        fullWidth
        id="first_name"
        label="名前"
        name="first_name"
        autoComplete="first_name"
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
          {errorMsg !== "" ? (
            <>
              <Typography
                id="modal-modal-title"
                variant="h6"
                component="h2"
                align="center"
              >
                サインアップ失敗
              </Typography>
              <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                {errorMsg}
              </Typography>
              <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                再度サインアップしてください．
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
            </>
          ) : (
            <>
              <Typography
                id="modal-modal-title"
                variant="h6"
                component="h2"
                align="center"
              >
                管理者登録完了
              </Typography>
              <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                管理者登録が完了しました．サインイン画面からサインインしてください．
              </Typography>
              <Grid container>
                <Grid item xs={12} sx={{ mt: 2 }}>
                  <Button
                    variant="contained"
                    fullWidth
                    onClick={handleGoToSignIn}
                  >
                    サインインへ
                  </Button>
                </Grid>
              </Grid>
            </>
          )}
        </Box>
      </Modal>
    </BaseForm>
  );
};

export default SignUpPage;

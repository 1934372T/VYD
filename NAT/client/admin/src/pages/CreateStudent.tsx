import Template from "pages/Template";
import { BaseBackdrop, BaseContainer, BaseItem } from "components/views/ui";
import {
  Box,
  Button,
  FormControl,
  Grid,
  InputLabel,
  MenuItem,
  Modal,
  Select,
  SelectChangeEvent,
  TextField,
  Typography,
} from "@mui/material";
import axios, { AxiosResponse } from "axios";
import { HOST_URL } from "configs/api";
import { useNavigate } from "react-router-dom";
import { FormEvent, useState } from "react";
import { style } from "components/views/style";

const CreateStudentPage = () => {
  const router = useNavigate();
  const [open, setOpen] = useState<boolean>(false);
  const [openModal, setOpenModal] = useState<boolean>(false);
  const [errorMsg, setErrorMsg] = useState<string>("");
  const [grade, setGrade] = useState("");

  const handleChangeGrade = (event: SelectChangeEvent) => {
    setGrade(event.target.value as string);
  };
  const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setOpen(true);
    const data = new FormData(event.currentTarget);
    console.log(data);
    axios
      .post(HOST_URL + "auth/student/signup", {
        student_id: data.get("student_id"),
        password: data.get("password"),
        first_name: data.get("first_name"),
        last_name: data.get("last_name"),
        grade: grade,
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

  const handleGoToStudentList = () => {
    router("/#/student/list");
  };
  return (
    <Template name={"学生登録"}>
      <BaseContainer>
        <BaseItem xs={12}>
          {/* <BaseForm
            formTitle="サインアップ"
            buttonTitle="作成"
            handleSubmit={handleSubmit}
            mode="signup"
          > */}
          <form onSubmit={handleSubmit}>
            <Grid container spacing={3}>
              <Grid item xs={4}>
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
              </Grid>
              <Grid item xs={4}>
                <TextField
                  margin="normal"
                  required
                  fullWidth
                  id="last_name"
                  label="名字"
                  name="last_name"
                  autoComplete="last_name"
                />
              </Grid>
              <Grid item xs={4}>
                <TextField
                  margin="normal"
                  required
                  fullWidth
                  id="first_name"
                  label="名前"
                  name="first_name"
                  autoComplete="first_name"
                />
              </Grid>
            </Grid>
            <Grid container spacing={3}>
              <Grid item xs={4}>
                <FormControl fullWidth margin="normal" required>
                  <InputLabel id="grade-select-label">学年</InputLabel>
                  <Select
                    labelId="grade-select-label"
                    id="grade-select"
                    value={grade}
                    label="grade"
                    onChange={handleChangeGrade}
                  >
                    <MenuItem value={"B4"}>B4</MenuItem>
                    <MenuItem value={"M1"}>M1</MenuItem>
                    <MenuItem value={"M2"}>M2</MenuItem>
                    <MenuItem value={"D3"}>D3</MenuItem>
                    <MenuItem value={"D5"}>D5</MenuItem>
                  </Select>
                </FormControl>
              </Grid>
              <Grid item xs={4}>
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
              </Grid>
            </Grid>
            {/* other form fields go here */}
            <Grid
              container
              spacing={2}
              sx={{ mt: 1 }}
              justifyContent="flex-end"
            >
              <Grid item>
                <Button
                  variant="contained"
                  color="secondary"
                  type="submit" // this should trigger form submission
                >
                  登録
                </Button>
              </Grid>
              <Grid item>
                <Button
                  variant="contained"
                  color="secondary"
                  type="button"
                  onClick={handleGoToStudentList}
                >
                  キャンセル
                </Button>
              </Grid>
            </Grid>
            {/* rest of your code */}
          </form>
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
                    学生登録完了
                  </Typography>
                  <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                    学生登録が完了しました．
                  </Typography>
                  <Grid container>
                    <Grid item xs={12} sx={{ mt: 2 }}>
                      <Button
                        variant="contained"
                        fullWidth
                        onClick={handleGoToStudentList}
                      >
                        一覧へ
                      </Button>
                    </Grid>
                  </Grid>
                </>
              )}
            </Box>
          </Modal>
          {/* </BaseForm> */}
        </BaseItem>
      </BaseContainer>
    </Template>
  );
};

export default CreateStudentPage;

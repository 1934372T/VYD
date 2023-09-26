import { Route, Routes } from "react-router-dom";

import Home from "pages/Home";
import PaperListPage from "pages/PaperList";
import SignInPage from "pages/SignIn";
import SignUpPage from "pages/SignUp";
import Preview from "pages/Preview";
import StudentListPage from "pages/StudentList";
import CreateStudentPage from "pages/CreateStudent";

const Router = () => {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/paper/list" element={<PaperListPage />} />
      <Route path="/signin" element={<SignInPage />} />
      <Route path="/signup" element={<SignUpPage />} />
      <Route path="/preview" element={<Preview />} />
      <Route path="/student/list" element={<StudentListPage />}/>
      <Route path="/student/create" element={<CreateStudentPage />}/>
    </Routes>
  );
};

export default Router;

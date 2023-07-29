import { Route, Routes } from "react-router-dom";

import Home from "pages/Home";
import UploadPage from "pages/Uploader";
import ListPage from "pages/List";
import SignInPage from "pages/SignIn";
import Preview from "pages/Preview";

const Router = () => {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/upload" element={<UploadPage />} />
      <Route path="/list" element={<ListPage />} />
      <Route path="/signin" element={<SignInPage />} />
      <Route path="/preview" element={<Preview />} />
    </Routes>
  );
};

export default Router;

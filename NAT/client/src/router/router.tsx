import { Route, Routes } from "react-router-dom";

import Home from "pages/Home";
import UploadPage from "pages/Uploader";
import ListPage from "pages/List";

const Router = () => {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/upload" element={<UploadPage />} />
      <Route path="/list" element={<ListPage />} />
    </Routes>
  );
};

export default Router;

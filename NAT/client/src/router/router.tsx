import { Route, Routes } from "react-router-dom";

import Home from "pages/Home";
import UploadPage from "pages/Uploader";

const Router = () => {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/upload" element={<UploadPage />} />
    </Routes>
  );
};

export default Router;

import ReactDOM from "react-dom/client";
import Router from "router/router";
import { HashRouter } from "react-router-dom";

const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement
);
root.render(<HashRouter children={<Router />} />);

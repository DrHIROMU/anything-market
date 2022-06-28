import React from "react";
import ReactDOM from "react-dom";
import "./index.scss";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import { BrowserRouter, Routes, Route } from "react-router-dom";

const Market = React.lazy(() => import("./component/market/market.component"));
const UploadFile = React.lazy(() =>
  import("./component/upload-file/upload-file.component")
);

ReactDOM.render(
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<App />} />
      <Route
        path="market"
        element={
          <React.Suspense fallback={<>...</>}>
            <Market />
          </React.Suspense>
        }
      />
      <Route
        path="upload-file"
        element={
          <React.Suspense fallback={<>...</>}>
            <UploadFile />
          </React.Suspense>
        }
      />
      <Route
        path="*"
        element={
          <main style={{ padding: "1rem" }}>
            <p>There's nothing here!</p>
          </main>
        }
      />
    </Routes>
  </BrowserRouter>,
  document.getElementById("root")
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

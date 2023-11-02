import { Navigate, createBrowserRouter } from "react-router-dom";
import Root from "../App";
import { HomePage } from "../pages/HomePage";
import { LoginPage } from "../pages/LoginPage";
import { RegisterPage } from "../pages/RegisterPage";
import { AnonymusRoute } from "./AnonymusRoute";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    children: [
      {
        index: true,
        element: <Navigate to="/home" />,
      },
      {
        path: "home",
        element: <HomePage />,
      },
      {
        path: "register",
        element: <AnonymusRoute ><RegisterPage /></AnonymusRoute>,
      },
      {
        path: "login",
        element: <AnonymusRoute ><LoginPage /></AnonymusRoute>,
      },
    ],
  },
]);

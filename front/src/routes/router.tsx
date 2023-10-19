import { createBrowserRouter } from "react-router-dom";
import { HomePage } from "../pages/HomePage";
import { LoginPage } from "../pages/LoginPage";
import { RegisterPage } from "../pages/RegisterPage";
import { AnonymusRoute } from "./AnonymusRoute";
import { ProtectedRoute } from "./PrivateRoute";

export const router = createBrowserRouter([
  {
    path: "/",
    element: (
      <ProtectedRoute>
        <HomePage />
      </ProtectedRoute>
    ),
    children: [],
  },
  {
    path: "register",
    element: (
      <AnonymusRoute>
        <RegisterPage />
      </AnonymusRoute>
    ),
  },
  {
    path: "login",
    element: (
      <AnonymusRoute>
        <LoginPage />
      </AnonymusRoute>
    ),
  },
]);

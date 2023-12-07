import React from "react";
import ReactDOM from "react-dom/client";
import { RouterProvider } from "react-router-dom";
import { router } from "./routes/router.tsx";
import "./index.css";
import { AuthProvider } from "./core/providers/AuthProvider/AuthProvider.tsx";
import { CourseActionsProvider } from "./modules/Courses/context/Provider.tsx";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <AuthProvider>
      <CourseActionsProvider>
        <RouterProvider router={router} />
      </CourseActionsProvider>
    </AuthProvider>
  </React.StrictMode>
);

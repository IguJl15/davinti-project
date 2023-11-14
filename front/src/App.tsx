import { NavLink, Outlet } from "react-router-dom";
import { useAuth } from "./core/hooks/useAuth";
import "./index.css";
import {
  authRepository,
  httpClient,
} from "./core/providers/AuthProvider/AuthProvider";

const Root = () => {
  const { isSignedIn, logOut } = useAuth();
  return (
    <>
      <header
        style={{ display: "flex", justifyContent: "center", gap: "10px" }}
      >
        <NavLink to={"/home"} style={{ color: "#F2F0F4" }}>
          Home
        </NavLink>
        <NavLink to={"/courses"} style={{ color: "#F2F0F4" }}>
          Cursos
        </NavLink>
        {!isSignedIn && (
          <>
            <NavLink to={"/login"} style={{ color: "#F2F0F4" }}>
              Login
            </NavLink>
            <NavLink to={"/register"} style={{ color: "#F2F0F4" }}>
              Registro
            </NavLink>
          </>
        )}
        {isSignedIn && (
          <>
            <button
              onSubmit={() =>
                httpClient.post("/session/refresh", {
                  refreshToken: authRepository.getLocalAuthData()?.refreshToken,
                })
              }
            >
              Recarregar tokens
            </button>
            <button onClick={logOut} type="button">
              Sair{" "}
            </button>
          </>
        )}
      </header>

      <Outlet />
      <footer></footer>
    </>
  );
};

export default Root;

import { NavLink, Outlet } from "react-router-dom";
import { useAuth } from "./core/hooks/useAuth";

function Root() {
  const { isSignedIn, logOut } = useAuth();

  return (
    <>
      <header
        style={{ display: "flex", justifyContent: "center", gap: "10px" }}>
        <NavLink to={"/home"}>Home</NavLink>
        {!isSignedIn && (
          <>
            <NavLink to={"/login"}>Login</NavLink>
            <NavLink to={"/register"}>Registro</NavLink>
          </>
        )}
        {isSignedIn && (
          <button onClick={logOut} type="button">
            Sair{" "}
          </button>
        )}
      </header>

      <Outlet />
      <footer></footer>
    </>
  );
}

export default Root;

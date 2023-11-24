import { useContext } from "react";
import { AuthContext } from "../contexts/AuthContext/AuthContext";

function useAuth() {
  return useContext(AuthContext);
}

export { useAuth };
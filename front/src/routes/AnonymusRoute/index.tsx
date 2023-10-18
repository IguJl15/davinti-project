import { PropsWithChildren } from "react";
import { useAuth } from "../../core/hooks/useAuth";
import { Navigate } from "react-router-dom";

function AnonymusRoute({ children }: PropsWithChildren) {
  const { isSignedIn } = useAuth();

  if (isSignedIn) {
    <Navigate to="/home" />;
  }

  return children;
}

export { AnonymusRoute };

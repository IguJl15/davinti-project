import { PropsWithChildren } from "react";
import { Navigate } from "react-router-dom";
import { useAuth } from "../../core/hooks/useAuth";

function AnonymusRoute({ children }: PropsWithChildren) {
  const { isSignedIn } = useAuth();

  if (isSignedIn) {
    return <Navigate to="/home" />;
  }

  return children;
}

export { AnonymusRoute };

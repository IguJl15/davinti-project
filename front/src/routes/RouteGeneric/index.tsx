import { ReactNode } from "react";
import { useAuth } from "../../core/hooks/useAuth";
import { Navigate } from "react-router-dom";

export enum RouteType {
  PROTECTED,
  ANONYMUS,
}

interface GenericRouteProps {
  children: ReactNode;
  routeType: RouteType;
}

function GenericRoute({ children, routeType }: GenericRouteProps) {
  const { isSignedIn } = useAuth();

  switch (routeType) {
    case RouteType.PROTECTED: {
      if (!isSignedIn) {
        <Navigate to="login" />;
      }

      return children;
    }
    case RouteType.ANONYMUS: {
      if (isSignedIn) {
        <Navigate to="/home" />;
      }

      return children;
    }
  }
}

export { GenericRoute };

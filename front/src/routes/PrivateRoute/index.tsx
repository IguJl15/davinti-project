import { PropsWithChildren } from 'react';
import { useAuth } from '../../core/hooks/useAuth';
import { Navigate } from 'react-router-dom';
import { Role } from '../../core/interfaces/Role';

function ProtectedRoute({ children, role }: { role?: Role } & PropsWithChildren) {
  const { isSignedIn, authData } = useAuth();

  if (!isSignedIn) {
    return <Navigate to="/login" />;
  }

  if (role && authData?.user?.role != role) {
    return <Navigate to="/mycourses" />;
  }

  return children;
}

export { ProtectedRoute };

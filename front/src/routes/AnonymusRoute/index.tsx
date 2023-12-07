import { PropsWithChildren } from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth } from '../../core/hooks/useAuth';
import { Role } from '../../core/interfaces/Role';

function AnonymusRoute({ children }: PropsWithChildren) {
  const { isSignedIn, authData } = useAuth();

  if (isSignedIn) {
    if (authData?.user?.role == Role.INSTRUCTOR) {
      return <Navigate to="/instructor/mycourses" />;
    }
    return <Navigate to="/mycourses" />;
  }

  return children;
}

export { AnonymusRoute };

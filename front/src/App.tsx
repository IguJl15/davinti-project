import { Outlet } from 'react-router-dom';
import { AuthProvider } from './core/providers/AuthProvider';

function Root() {
  return (
    <>
      <AuthProvider>
        <header></header>
        <Outlet />
        <footer></footer>
      </AuthProvider>
    </>
  );
}

export default Root;

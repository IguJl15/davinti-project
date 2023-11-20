import { Outlet } from 'react-router-dom';
import { NavBar } from './core/components/NavBar';
import './index.css';

const Root = () => {
  return (
    <>
      <header>
        <NavBar />
      </header>

      <Outlet />
      <footer></footer>
    </>
  );
};

export default Root;

import { NavLink } from 'react-router-dom';
import style from './style.module.css';

interface NavButtonProps {
  redirectLink: string;
}

function NavButton({ redirectLink }: NavButtonProps) {
  return (
    <div className={style.button_wrapper}>
      <NavLink
        style={{ color: 'white', listStyle: 'none', textDecoration: 'none' }}
        to={redirectLink}
      >
        {redirectLink}
      </NavLink>
    </div>
  );
}

export { NavButton };

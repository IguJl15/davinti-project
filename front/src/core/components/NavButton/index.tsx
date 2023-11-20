import { NavLink } from 'react-router-dom';
import style from './style.module.css';

interface NavButtonProps {
  redirectLink: string;
}

function NavButton({ redirectLink }: NavButtonProps) {
  return (
    <div className={style.button_wrapper}>
      <NavLink className={style.nav_link} to={redirectLink}>
        {redirectLink}
      </NavLink>
    </div>
  );
}

export { NavButton };

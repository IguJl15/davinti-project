import { NavLink } from 'react-router-dom';
import style from './style.module.css';

interface NavButtonProps {
  label?: string;
  redirectLink: string;
  onClick?: () => void;
}

function NavButton({ label, redirectLink, onClick }: NavButtonProps) {
  return (
    <div className={style.button_wrapper}>
      <NavLink className={style.nav_link} to={redirectLink} onClick={onClick}>
        {label ?? redirectLink}
      </NavLink>
    </div>
  );
}

export { NavButton };

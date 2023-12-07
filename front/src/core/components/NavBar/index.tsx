import IfipLogo from '../../../assets/baixados.png';
import user_logo from '../../../assets/user_logo.png';
import styles from './style.module.css';
import { NavButton } from '../NavButton';
import { useAuth } from '../../../core/hooks/useAuth';

function NavBar() {
  const { isSignedIn, logOut } = useAuth();
  return (
    <div className={styles.navbar_wrapper}>
      <div className={styles.frame_1}>
        <div className={styles.image_holder}>
          <img src={IfipLogo} alt="ifpi-logo" />
        </div>
      </div>
      <div className={styles.button_wrapper}>
        {!isSignedIn && (
          <>
            <NavButton label="Entrar" redirectLink="/login" />
            <NavButton label="Registrar" redirectLink="/register" />
          </>
        )}
        {isSignedIn && (
          <>
            <NavButton label="Cursos" redirectLink="/courses" />
            <NavButton label="Meus Cursos" redirectLink="/mycourses" />
            <NavButton label="Sair" redirectLink="/login" onClick={logOut} />
          </>
        )}

        <NavButton label="Meus Cursos (instrutor)" redirectLink="/instructor" />
      </div>
      <div className={styles.frame_2}>
        <div className={styles.user_icon}>
          <img style={{ height: '50px' }} src={user_logo} />
        </div>
      </div>
    </div>
  );
}

export { NavBar };

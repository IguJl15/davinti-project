import IfpiLogoFull from '../../../assets/baixados-desktop.png';
import IfpiLogoMobile from '../../../assets/baixados-mobile.png';
import user_logo from '../../../assets/user_logo.png';
import styles from './style.module.css';
import { NavButton } from '../NavButton';
import { useAuth } from '../../../core/hooks/useAuth';
import { Role } from '../../../core/interfaces/Role';

function NavBar() {
  const { isSignedIn, authData, logOut } = useAuth();
  return (
    <div className={styles.navbar_wrapper}>
      <div className={styles.image_holder}>
        <picture>
          <source media="(min-width: 1000px)" srcSet={IfpiLogoFull} />
          <img src={IfpiLogoMobile} alt="ifpi-logo" />
        </picture>
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
            <NavButton
              label="Meus Cursos"
              redirectLink={
                authData?.user?.role == Role.INSTRUCTOR ? '/instructor/mycourses' : '/mycourses'
              }
            />
            <NavButton label="Sair" redirectLink="/login" onClick={logOut} />
          </>
        )}
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

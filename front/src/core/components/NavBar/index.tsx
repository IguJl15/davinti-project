import IfipLogo from '../../../../public/baixados.png';
import user_logo from '../../../../public/user_logo.png';
import styles from './style.module.css';
import { NavButton } from '../NavButton';

function NavBar() {
  return (
    <div className={styles.navbar_wrapper}>
      <div className={styles.frame_1}>
        <div className={styles.image_holder}>
          <img src={IfipLogo} alt="ifpi-logo" />
        </div>
      </div>
      <div className={styles.button_wrapper}>
        <NavButton redirectLink="home" />
        <NavButton redirectLink="login" />
        <NavButton redirectLink="register" />
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

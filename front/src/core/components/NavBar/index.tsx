import IfipLogo from "../../../../public/baixados.png";
import styles from "./style.module.css";

function NavBar() {
  return (
    <div className={styles.navbar_wrapper}>
      <div className={styles.frame_1}>
        <div className={styles.icon_holder}></div>
        <div className={styles.image_holder}>
          <img src={IfipLogo} alt="ifpi-logo" />
        </div>
      </div>

      <div className={styles.frame_2}>
        <div className={styles.user_icon}></div>
      </div>
    </div>
  );
}

export { NavBar };

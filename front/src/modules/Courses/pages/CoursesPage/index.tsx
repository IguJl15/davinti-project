import styles from "./style.module.css";
import { Body } from "../../../../core/components/Body";

function CoursesPage() {
  return (
    <Body>
      <div className={styles.page_wrapper}>
        <div className={styles.greeting}>
          <h2>Hello, Kelson Filho</h2>
        </div>
        <div className={styles.main_wrapper}></div>
      </div>
    </Body>
  );
}

export { CoursesPage };

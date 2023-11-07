import styles from "./style.module.css";

function CoursesPage() {
  return (
    <div className={styles.page_wrapper}>
      <div className={styles.greeting}>
        <h2>Hello, Kelson Filho</h2>
      </div>
      <div className={styles.main_wrapper}></div>
    </div>
  );
}

export { CoursesPage };

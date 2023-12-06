import { Lessson } from 'modules/Courses/models/course';
import styles from './style.module.css';

export default function SupportContentCard({ lesson }: { lesson: Lessson }) {
  return (
    <div className={styles.cardWrapper}>
      <h4 className="title-large on-surface-text">Conteúdos de suporte</h4>
      <div className={styles.contents_list}>
        <SupportItem />
        <SupportItem />
        <SupportItem />
        <SupportItem />
      </div>
    </div>
  );
}

function SupportItem() {
  return <div className={styles.support_item}>SuportItem</div>;
}

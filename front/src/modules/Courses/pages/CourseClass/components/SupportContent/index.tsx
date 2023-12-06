import { Content, Lessson } from 'modules/Courses/models/course';
import styles from './style.module.css';
import { ContentType } from '../Content';

export default function SupportContentCard({ contents }: { contents: Content[] }) {
  return (
    <div className={styles.cardWrapper}>
      <h4 className="title-large on-surface-text">Conteúdos de suporte</h4>
      <div className={styles.contents_list}>
        {contents.map((content) => (
          <>
            <SupportItem content={content} />
          </>
        ))}
      </div>
    </div>
  );
}

function SupportItem({ content }: { content: Content }) {
  var icon: string;
  var url: string;

  if (content.url) {
    icon = 'link';
    url = content.url;
  } else if (content.videoUrl) {
    icon = 'smart_display';
    url = content.videoUrl;
  } else if (content.filePath) {
    icon = 'description';
    url = content.filePath;
  } else {
    icon = '';
  }

  return (
    <a href={url!} className={styles.support_item + ' title-medium on-primary-fixed-variant-text'}>
      <span className="material-symbols-outlined">{icon}</span>
      <span className={styles.support_title}>{content.title}</span>
    </a>
  );
}

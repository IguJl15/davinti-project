import { Content, Lessson } from 'modules/Courses/models/course';
import styles from './style.module.css';
import { ContentType } from '../Content';
import { assureHttpPrefix } from '../ContentLink';

export default function SupportContentCard({ contents }: { contents: Content[] }) {
  return (
    <div className={styles.cardWrapper}>
      <h4 className="title-large on-surface-text">Conteúdos de suporte</h4>
      <div className={styles.contents_list}>
        {contents.map((content) => (
          <SupportItem key={content.id} content={content} />
        ))}
      </div>
    </div>
  );
}

function SupportItem({ content }: { content: Content }) {
  var icon: string;
  var url: string | null;

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
    url = null;
  }

  if (url) url = assureHttpPrefix(url);

  return (
    <a
      href={url ?? '#'}
      target="_blank"
      rel="noopener noreferrer"
      className={styles.support_item + ' title-medium on-primary-fixed-variant-text'}
    >
      <span className="material-symbols-outlined">{icon}</span>
      <span className={styles.support_title}>{content.title}</span>
    </a>
  );
}

import { PropsWithChildren, ReactNode } from 'react';
import { ContentLink } from '../ContentLink';
import { ContentText } from '../ContentText';
import { ContentVideo } from '../ContentVideo';
import styles from './style.module.css';

export enum ContentType {
  VIDEO,
  LINK,
  TEXT,
}

type ContentProps = {
  contentType: ContentType;
  title: string;
  videoLink?: string;
  urlLink?: string;
  text?: string;
} & PropsWithChildren;

function Content({ contentType, videoLink, text, urlLink, title }: ContentProps) {
  var content: ReactNode;
  switch (contentType) {
    case ContentType.VIDEO:
      content = <ContentVideo videoLink={videoLink} />;
      break;
    case ContentType.TEXT:
      content = <ContentText text={text} />;
      break;
    case ContentType.LINK:
      content = <ContentLink title={title} urlLink={urlLink!} />;
      break;
  }

  return (
    <div className={styles.caixa}>
      <h2 className="title-large">{title}</h2>

      <div className={styles.content}>{content}</div>
    </div>
  );
}

export { Content };

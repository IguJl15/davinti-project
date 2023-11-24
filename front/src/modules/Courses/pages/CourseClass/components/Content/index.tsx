import { ContentLink } from '../ContentLink';
import { ContentText } from '../ContentText';
import { ContentVideo } from '../ContentVideo';

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
};

function Content({ contentType, videoLink, text, urlLink, title }: ContentProps) {
  switch (contentType) {
    case ContentType.VIDEO:
      return <ContentVideo videoLink={videoLink} title={title} />;
    case ContentType.TEXT:
      return <ContentText text={text} title={title} />;
    case ContentType.LINK:
      return <ContentLink urlLink={urlLink} title={title} />;
  }
}

export { Content };

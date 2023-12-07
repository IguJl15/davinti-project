import React from 'react';
import styles from './style.module.css';

interface ContentLinkProps {
  title: string;
  urlLink: string;
}

function ContentLink({ title, urlLink }: ContentLinkProps): React.JSX.Element {
  return (
    <div className={styles.link_wrapper}>
      <a href={assureHttpPrefix(urlLink)} target="_blank" rel="noopener noreferrer">
        <span className="material-symbols-outlined">link</span>
        {title}
      </a>
    </div>
  );
}

export function assureHttpPrefix(url: string) {
  return url.match(/^.{3,5}\:\/\//) ? url : `http://${url}`;
}

export { ContentLink };

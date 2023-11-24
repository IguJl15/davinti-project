import React from 'react';
import styles from './style.module.css';

interface ContentLinkProps {
  urlLink?: string;
  title?: string;
}

function ContentLink({ urlLink, title }: ContentLinkProps): React.JSX.Element {
  return (
    <>
      <div className={styles.link_card}>
        <span className="text-large">{title}</span>
        <a href={urlLink}>{urlLink}</a>
      </div>
    </>
  );
}

export { ContentLink };

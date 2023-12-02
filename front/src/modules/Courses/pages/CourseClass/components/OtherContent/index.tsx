import React from 'react';

interface OtherContentProps {
  content: string[] | null;
}

function OtherContent({ content }: OtherContentProps): React.JSX.Element {
  return (
    <div className="wrapper">
      <div className="content">
        {content === null ? (
          <h1>No content available</h1>
        ) : (
          content
        ) }
      </div>
    </div>
  );
}

export { OtherContent };

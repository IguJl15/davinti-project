import React from 'react';

interface ContentTextProps {
  text?: string;
  title?: string;
}

function ContentText({ text }: ContentTextProps): React.JSX.Element {
  return (
    <span className="body-large" style={{ whiteSpace: 'pre-wrap' }}>
      {text}
    </span>
  );
}

export { ContentText };

import style from './styles.module.css';

interface ContentVideoProps {
  videoLink?: string;
  title?: string;
}

function ContentVideo({
  videoLink = 'https://www.youtube.com/watch?v=HChXoAprXsM'
}: ContentVideoProps) {
  const id = getVideoId(videoLink);
  return (  
    <iframe
      className={style.iframe}
      src={'https://www.youtube.com/embed/' + id}
      title="YouTube video player"
      frameBorder="0"
      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
      allowFullScreen
    ></iframe>
  );
}

export { ContentVideo };

function getVideoId(youtubeLink: string): string | null {
  var regex: RegExp;

  if (youtubeLink.includes('watch')) {
    // https://stackoverflow.com/questions/1280557/extract-parameter-value-from-url-using-regular-expressions
    regex = RegExp(/v=([\w-]{11})/);
  } else {
    regex = RegExp(/\/([\w-]{11})/);
  }
  const matches = youtubeLink.match(regex!);

  if (!matches) return null;

  const id = matches[1];

  return id;
}

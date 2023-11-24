import style from './styles.module.css';

interface ContentVideoProps {
  videoLink?: string;
  title?: string;
}

function ContentVideo({
  videoLink = 'https://www.youtube.com/watch?v=HChXoAprXsM',
  title,
}: ContentVideoProps) {
  return (
    <div className={style.video_card}>
      <span className="text-large">{title}</span>
      <iframe
        height="500"
        src={videoLink}
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
        allowFullScreen
      ></iframe>
    </div>
  );
}

export { ContentVideo };

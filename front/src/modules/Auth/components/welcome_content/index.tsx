import style from './style.module.css';

function WelcomeContent() {
  return (
    <div className={style.welcome_content}>
      <span>COMPANY LOGO</span>
      <div className="welcome_text">
        <h2>Lorem Ipsum.....</h2>
        <span>
          Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has
          been the industry's standard dummy text ever since the 1500s,
        </span>
      </div>
      <span>Lorem ipsum solo ador</span>
    </div>
  );
}

export { WelcomeContent };

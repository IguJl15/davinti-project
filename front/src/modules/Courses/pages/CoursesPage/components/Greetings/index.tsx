import { useAuth } from '../../../../../../core/hooks/useAuth';
import style from './style.module.css';

function Greetings() {
  const { isSignedIn, authData } = useAuth();

  return (
    <div className={style.greeting}>
      <h2 className="headline-small">
        {isSignedIn ? `Hello, ${authData!.user!.completeName}` : 'Olá! Seja bem vindo'}
      </h2>
    </div>
  );
}

export { Greetings };

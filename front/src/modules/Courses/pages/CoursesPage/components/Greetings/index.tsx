import { useAuth } from '../../../../../../core/hooks/useAuth';
import style from "./style.module.css"

function Greetings() {
  const { isSignedIn, authData } = useAuth();

  return (
    <div className={style.greeting}>
      {isSignedIn ? <h2>Hello, {authData!.user!.completeName}</h2> : <h2>Olá! Seja bem vindo</h2>}
    </div>
  );
}

export { Greetings };

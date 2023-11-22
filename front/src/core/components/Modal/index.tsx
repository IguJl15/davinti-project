import { PrimaryButton } from '../Button';
import { Input } from '../Input';
import style from './style.module.css';

interface ModalProps {
  state: boolean;
  changeState: React.Dispatch<React.SetStateAction<boolean>>;
  handleSubmit?: React.FormEventHandler<HTMLFormElement> | undefined;
}

function Modal({ state, handleSubmit, changeState }: ModalProps) {
  return (
    <div>
      {state && (
        <div className={style.modalBackground}>
          <div className={style.modal}>
            <form onSubmit={handleSubmit}>
              <h3>Preencha o formulario</h3>
              <Input label="Nome do curso" inputType="text" />
              <Input label="Descrição do curso" inputType="text" />
              <div>
                <PrimaryButton label="ENVIAR" type="submit" />
                <PrimaryButton
                  label="CANCELAR"
                  onClick={() => {
                    changeState(state);
                  }}
                />
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}

export { Modal };

import { zodResolver } from '@hookform/resolvers/zod';
import { useForm } from 'react-hook-form';
import {
  CreateCourseFormData,
  createCourseSchema,
} from '../../../modules/Instructor/context/InstructorActionsContext';
import { PrimaryButton } from '../Button';
import { Input } from '../Input';
import style from './style.module.css';

interface ModalProps {
  state: boolean;
  changeState: React.Dispatch<React.SetStateAction<boolean>>;
  onFormSubmit: (data: CreateCourseFormData) => void;
}

function Modal({ state, onFormSubmit: handleSubmit, changeState }: ModalProps) {
  const form = useForm<CreateCourseFormData>({ resolver: zodResolver(createCourseSchema) });

  return (
    <div>
      {state && (
        <div className={style.modalBackground}>
          <div className={style.modal}>
            <div className={style.header}>
              <h3 className="headline-small on-surface-text">Criar Novo Curso</h3>
            </div>
            <form onSubmit={form.handleSubmit(handleSubmit)}>
              <div className={style.body}>
                <span className="label-large">Dados do Curso</span>
                <Input
                  label="Nome do curso"
                  inputType="text"
                  {...form.register('name', { required: true })}
                />
                <Input
                  label="Descrição"
                  inputType="textarea"
                  {...form.register('description', { required: true })}
                />
              </div>
              <div className={style.footer}>
                <PrimaryButton
                  label="Cancelar"
                  buttonStyle="Text"
                  onClick={() => changeState(state)}
                />
                <PrimaryButton label="Criar Curso" type="submit" />
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}

export { Modal };

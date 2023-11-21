import { SubmitHandler, useForm } from 'react-hook-form';
import { RegisterSchema } from '../../../../core/schemas/RegisterSchema';
import { z } from 'zod';
import { zodResolver } from '@hookform/resolvers/zod';
import style from './style.module.css';
import { Input } from '../../../../core/components/Input';
import { PrimaryButton } from '../../../../core/components/Button';
import { ErrorMessage } from '../../../../core/components/ErrorText';
import { useAuth } from '../../../../core/hooks/useAuth';
import { WelcomeContent } from '../../components/welcome_content';

type CreateRegisterFormData = z.infer<typeof RegisterSchema>;

export function RegisterPage() {
  const { signUp: signIn } = useAuth();

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<CreateRegisterFormData>({
    resolver: zodResolver(RegisterSchema),
  });

  const onSubmit: SubmitHandler<CreateRegisterFormData> = (data: CreateRegisterFormData) => {
    try {
      signIn(data);
    } catch (error: unknown) {
      console.log(error);
    }
  };

  return (
    <div className={style.page_wrapper}>
      <div className={style.form_container}>
        <WelcomeContent />

        <form onSubmit={handleSubmit(onSubmit)} className={style.form}>
          <div className={style.company_logo_wrapper}>
            <span>COMPANY LOGO</span>
          </div>
          <div className="form_text">
            <h1>Criar conta</h1>
            <p>Welcome to our plataform, please register yourself and explore our new world</p>
          </div>
          <Input
            inputType="text"
            autoComplete="name"
            label="Nome"
            {...register('name', { required: true })}
          />
          <ErrorMessage>{errors.name?.message}</ErrorMessage>

          <Input inputType="email" label="Email" {...register('email', { required: true })} />
          <ErrorMessage>{errors.email?.message}</ErrorMessage>

          <Input
            autoComplete="new-password"
            inputType="password"
            label="Senha"
            {...register('password', { required: true })}
          />
          <ErrorMessage>{errors.password?.message}</ErrorMessage>

          <PrimaryButton label="Registrar" type="submit" />
        </form>
      </div>
    </div>
  );
}

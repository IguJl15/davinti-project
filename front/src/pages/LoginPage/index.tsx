import { SubmitHandler, useForm } from "react-hook-form";
import { LoginSchema } from "../../core/schemas/LoginSchema";
import { zodResolver } from "@hookform/resolvers/zod";
import { z } from "zod";
import style from "./style.module.css";
import { Input } from "../../core/components/Input";
import { Button } from "../../core/components/Button";
import { ErrorMessage } from "../../core/components/ErrorText";
import { useAuth } from "../../core/hooks/useAuth";
import { useEffect } from "react";
import { Navigate } from "react-router-dom";

type CreateLoginFormData = z.infer<typeof LoginSchema>;

function LoginPage() {
  const { logIn, isSignedIn } = useAuth();

  useEffect(() => {
    if (isSignedIn) {
      <Navigate to="/" />;
    }
  });

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<CreateLoginFormData>({ resolver: zodResolver(LoginSchema) });

  const onSubmit: SubmitHandler<CreateLoginFormData> = async (
    data
  ) => {
    try {
      await logIn(data);
    } catch (error: unknown) {
      console.log(error);
    }
  };

  return (
    <div className={style.page_wrapper}>
      <div className={style.form_container}>
        <div className={style.welcome_content}>
          <span>COMPANY LOGO</span>
          <div className="welcome_text">
            <h2>Lorem Ipsum.....</h2>
            <span>
              Lorem Ipsum is simply dummy text of the printing and typesetting
              industry. Lorem Ipsum has been the industry's standard dummy text
              ever since the 1500s,
            </span>
          </div>
          <span>Lorem ipsum solo ador</span>
        </div>

        <form onSubmit={handleSubmit(onSubmit)} className={style.form}>
          <div className={style.company_logo_wrapper}>
            <span>COMPANY LOGO</span>
          </div>
          <div className="form_text">
            <h1>Entrar</h1>
            <p>Welcome back to the future of education...</p>
          </div>
          <Input inputType="email" label="Email" {...register("email")} autoComplete="email" />
          <ErrorMessage>{errors.email?.message}</ErrorMessage>
          <Input
            label="Senha"
            autoComplete="current-password"
            inputType="password"
            {...register("password")}
          />
          <ErrorMessage>{errors.password?.message}</ErrorMessage>
          <Button label="Entrar" />
        </form>
      </div>
    </div>
  );
}

export { LoginPage };

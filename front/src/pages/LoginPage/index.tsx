import { SubmitHandler, useForm } from "react-hook-form";
import { LoginSchema } from "../../core/schemas/LoginSchema";
import { zodResolver } from "@hookform/resolvers/zod";
import { z } from "zod";
import style from "./style.module.css";
import { Input } from "../../core/components/Input";

type createUserFormData = z.infer<typeof LoginSchema>;

function LoginPage() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<createUserFormData>({ resolver: zodResolver(LoginSchema) });

  const onSubmit: SubmitHandler<createUserFormData> = (
    data: createUserFormData
  ) => {
    alert(data.email);
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
            <h1>Login</h1>
            <p>Welcome back to the future of education...</p>
          </div>
          <Input inputType="email" label="Email" {...register("email")} />
          {errors.email && <span>O campo de email é obrigatório</span>}
          <Input
            inputType="password"
            label="Password"
            {...register("password")}
          />
          {errors.password && <span>O campo de senha é obrigatório</span>}
          <div className={style.button_wrapper}>
            <input type="button" value="Logar" />
          </div>
        </form>
      </div>
    </div>
  );
}

export { LoginPage };

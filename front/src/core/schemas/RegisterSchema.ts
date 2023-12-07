import { z } from "zod";

const RegisterSchema = z.object({
  name: z.string().trim().min(1, { message: "O campo nao pode esta vazio" }),
  email: z
    .string()
    .email()
    .trim()
    .min(1, { message: "O campo nao pode esta vazio" }),
  password: z
    .string()
    .min(8, { message: "É necessário no minimo 8 caracteres na sua senha" }),
});

export { RegisterSchema };

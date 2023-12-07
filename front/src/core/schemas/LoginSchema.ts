import { z } from "zod";

export const LoginSchema = z.object({
  email: z
    .string()
    .email()
    .trim()
    .min(1, { message: "O campo nao pode esta vazio" }),
  password: z
    .string()
    .min(1, { message: "O campo nao pode esta vazio" }),
});

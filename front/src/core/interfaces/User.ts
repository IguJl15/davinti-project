import { Role } from "./Role"

export interface User {
     name: string
     email: string
     password: string
     role: Role
}
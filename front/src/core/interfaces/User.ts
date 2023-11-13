import { Role } from "./Role";

export interface User {
  id: string;
  name: string;
  email: string;
  registrationNumber: string;
  role: Role;
}

import { Role } from "./Role";

export interface User {
  id: string;
  completeName: string;
  email: string;
  registrationNumber: string;
  role: Role;
}

import { z } from "zod";
import { AuthData } from "../../../core/contexts/AuthContext/AuthContext";
import { httpClient } from "../../../core/providers/AuthProvider/AuthProvider";
import { User } from "../../../core/interfaces/User";

const emptyFieldErrorMsg = "O campo nao pode esta vazio"

export const createCourseSchema = z.object({
    name: z.string().trim().min(1, { message: emptyFieldErrorMsg }),
    description: z.string().trim().min(1, { message: emptyFieldErrorMsg }),
});

export type CreateCourseFormData = z.infer<typeof createCourseSchema>


export class InstructorActions {

    public get instructor(): User {
        return this.authData.user!
    }

    constructor(private authData: AuthData) { }

    public async createCourse(data: CreateCourseFormData) {
        await httpClient.post("/courses", data)
    }
}


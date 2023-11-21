export default interface Course {
    id: number
    name: string
    instructorId: number
    lessons: Lessson[]
}

export interface Lessson {
    id: number
    title: string
    mainContent: Content
    supportContent: Content[]
}

export interface Content {
    title: string
    url?: string
    id: number
    filePath?: string
}

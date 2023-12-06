export default interface Course {
    id: number
    name: string
    description: string
    instructor: { name: string }
    lessons: Lessson[]
}

export interface Lessson {
    id: number
    title: string
    mainContent: Content
    supportContent: Content[]
}

export interface Content {
    id: number
    title: string

    url?: string
    text?: string
    videoUrl?: string
    filePath?: string
}

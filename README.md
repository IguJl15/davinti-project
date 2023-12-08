# DaVinti Project

Open and standalone Virtual Learning Environment made with React, Spring and :heart:

---

:construction: Docs under construction :construction_worker: We are actively expanding this documentation and will soon offer comprehensive guidance.

> In the meantime, find instructions below for building and running the application.

---

## Up N Running

We've prepared a readily available instance of DaVinti Project for you to explore. Experience both the front end (React) and back end (Spring) through the following links:

- Front end: [https://davinti-project.fly.dev/](https://davinti-project.fly.dev/)
- Back end: [https://davinti-project-backend.fly.dev/ (swagger endpoint)]([https://davinti-project-backend.fly.dev/](https://davinti-project-backend.fly.dev/swagger-ui/index.html)https://davinti-project-backend.fly.dev/swagger-ui/index.html)

Both instances are conveniently hosted on [fly.io](https://fly.io/)

### Credentials

To use the front end web site, access and make requests with swagger you need to be authenticated. Some endpoints
required Administrator access, other need Instructor credentials and some endpoints are blocked for access authenticated
as Student.

Detailed tutorials on our authentication/authorization system are on the way. For now, thoose familiar or simply wanting to
know how to use it can create their own accounts through the registration page.

## Build And Run

### Requirements

#### Backend

Happily, we have established a solid experience developing alongside Docker containers for both development and production environments of the backend service. Therefore, only Docker is required to run the backend services (Spring Server and PostgreSql Database).

To get it up, execute the following command:

```bash
docker compose up -d
```

This will build and start the necessary Docker containers.

##### Standalone back end

For users who prefer non-Docker execution or encounter Docker Compose issues, here's an alternative approach:

1. Run a standalone PostgreSQL container:

docker compose up postgres -d
This command starts a separate container solely for the PostgreSQL database.

2. Start the Spring application:

gradle bootRun
This command utilizes Gradle to directly run the Spring application.


> Remember:
> 
> This approach bypasses Docker Compose and requires separate execution of the database and application.
> The development environment used JDK 17.


#### Front end

Development of the front-end utilizes Vite for compilation and hot reload functionality. To build and serve the front end application, follow these steps:

- Open a terminal within your project directory.
- Install Vite dependencies: `yarn install`
- Build the project: `yarn build`
- Start the development server: `yarn dev`

The front-end application will be accessible at http://localhost:3000

## Know issues

we've tracked a list of known issues encountered during the development of DaVinti Project. This information aims to assist you in navigating and understanding any potential roadblocks you may encounter:

### Back end

- Running Docker command: `mkdir: cannot create directory '/bitnami/postgresql/data': permission denied`

This occurs because Docker Compose attempts to create a volume (a folder) within the project directory to store persistent database data. Though Docker Compose handles the initial creation, the PostgreSQL instance lacks access when attempting to access and initialize the database.

To solve this issue, modify the ownership of the directory, changing the user and group that can access it. Execute the following command:

```bash
sudo chown -R 1001:1001 database_data
```

This command changes the owner of the database_data directory to user ID 1001 and group ID 1001.


Having other issues? Please don't hesitate to post them on our [GitHub issues tab](https://github.com/IguJl15/davinti-project/issues/new)!

--- 

Thank you
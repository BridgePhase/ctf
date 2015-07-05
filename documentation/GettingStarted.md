# Getting started

This will guide you through the steps necessary to get a local 
deployment of the *Consider the following* application. The application
offers various deployment methods depending on your needs, here's a listing 
of them:  

* [Spring Boot Gradle Application](#sprintBootDeployment)
* [Runnable JAR standalone deployment](#jarDeployment)
* [WAR file on a Java container](#warDeployment)
* [Docker release deployment](#dockerDeployment) - requires Docker installed

**In order to build the application, a Java Development Kit 7+ is required for all deployments except Docker**

* For the Gradle, JAR, and WAR options, make sure you clone the repository and open a terminal window (
or Command Prompt for Windows) in the repository directory (defaults to `ctf/`).

<a name="sprintBootDeployment"></a>
## Sprint Boot Gradle application 
This is by far the easiest deployment method; it is suitable for local deployments where
you just want to get the application up and running as quick as possible.

1. From the terminal run:
	* `./gradlew bootRun` on Linux and Mac systems
	* `gradlew bootRun` on Windows machines
2. After a few seconds (more on the first run) you should see an output of 
`Started Application in #### seconds `, you're up and running 
3. The application can be accessed locally at `http://localhost:8080/ctf`
 
<a name="jarDeployment"></a>
## Runnable JAR standalone deployment 
This option allows you to build a distributable `jar` file that can be run from 
the command line.

1. From the terminal run:
	* `./gradlew build` on Linux and Mac systems
	* `gradlew build` on Windows machines
2. After a few seconds (more on the first run) you should see an output of
 `BUILD SUCCESSFUL`. 
3. In the `ctf/build/libs` directory you'll find a `jar` file named `ctf-X.X.jar`.
 This is your deployable artifact.
4. Feel free to copy this `jar` file, you can run it using `java -jar ctf-X.X.jar`
5. After a few seconds you should see an output of 
`Started Application in #### seconds ` (you're up and running) 
6. The application can be accessed locally at `http://localhost:8080/ctf`

<a name="warDeployment"></a>
## WAR file on a Java container 
This option allows you to build a distributable `war` file that can be run on a
Java container (for example Tomcat)

1. From the terminal run:
	* `./gradlew build` on Linux and Mac systems
	* `gradlew build` on Windows machines
2. After a few seconds (more on the first run) you should see an output of
 `BUILD SUCCESSFUL`. 
3. In the `ctf/build/distributions` directory you'll find a `war` file named
`ctf.war`. 
4. You can deploy this `war` file on the container of your choice, for example
 in Tomcat, you would copy this `war` file to the `{TOMCAT_DIRECTORY}/webapps` 
 folder to deploy it.
5. The application can be accessed locally at `http://localhost:8080/ctf`

<a name="dockerDeployment"></a>
## Docker deployment

This option allows you to deploy the application locally based off our Docker images.

1. The Docker images are stored in the [jramirez/bridgephase](https://registry.hub.docker.com/u/jramirez/bridgephase/tags/manage/#) repository
2. You can deploy any of our releases (`sprint_*` and `master` branches create releases). To find out the release we have deployed in production you can go to our [CTF Version](http://considerbridgephase.com/ctf/alive) page.
3. Run the command:
	* `docker run -ti -p 8080:8080 jramirez/bridgephase:RELEASE_TAG` for example:
	* `docker run -ti -p 8080:8080 jramirez/bridgephase:ctf-2015-07-03-25` to deploy version ctf-2015-07-023-25
4. The application will be exposed on port `8080` so browse to http://localhost:8080 to see the application running  

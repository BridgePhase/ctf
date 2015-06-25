# Getting started

This will guide you trough the steps necessary to get a local 
deployment of the *Consider the following* application. The application
offers various deployment methods depending on your needs, here's a listing 
of them:  

* [Spring Boot Gradle Application](#sprintBootDeployment)
* [Runnable JAR standalone deployment](#jarDeployment)
* [WAR file on a Java container](#warDeployment)

**In order to build the application, a Java Development Kit (1.7+) is required.**

<a name="sprintBootDeployment"></a>
## Sprint Boot Gradle application 
This is by far the easiest deployment model, it's suitable for local deployments where
you just want to get the application up and running as quick as possible.

1. Clone the repository and open a terminal window
2. From the repository directory (defaults to `ctf`) run:
	* `./gradlew bootRun` on Linux and Mac systems
	* `gradlew bootRun` on Windows machines
3. After a few seconds (more on the first run) you should see an output of 
`Started Application in #### seconds `, you're up and running 
 
<a name="jarDeployment"></a>
## Runnable JAR standalone deployment 
This option allows you to build a distributable `jar` file which can be run from 
the command line.

1. Clone the repository and open a terminal window
2. From the repository directory (defaults to `ctf`) run:
	* `./gradlew build` on Linux and Mac systems
	* `gradlew build` on Windows machines
3. After a few seconds (more on the first run) you should see an output of
 `BUILD SUCCESSFUL`. 
4. In the `ctf/build/libs` directory you'll find a `jar` file named `ctf-X.X.jar`.
 This is your deployable artifact.
5. Feel free to copy this `jar` file, you can run it using `java -jar ctf-X.X.jar`
6. After a few seconds you should see an output of 
`Started Application in #### seconds `, you're up and running 

<a name="warDeployment"></a>
## WAR file on a Java container 
This option allows you to build a distributable `war` file which can be run on a
Java container (for example Tomcat)

1. Clone the repository and open a terminal window
2. From the repository directory (defaults to `ctf`) run:
	* `./gradlew build` on Linux and Mac systems
	* `gradlew build` on Windows machines
3. After a few seconds (more on the first run) you should see an output of
 `BUILD SUCCESSFUL`. 
4. In the `ctf/build/distributions` directory you'll find a `war` file named
`ctf.war`. 
5. You can deploy this `war` file on the container of your choice, for example
 in Tomcat, you would copy this `war` file to the `{TOMCAT_DIRECTORY}/webapps` 
 folder to deploy it.
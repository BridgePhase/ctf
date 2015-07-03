# Consider the Following
Take [Consider the following](http://considerbridgephase.com) for a test drive!

[Consider the following - testing environment.](http://ec2-54-209-151-190.compute-1.amazonaws.com:8080)

[![Build Status](https://travis-ci.org/BridgePhase/ctf.svg?branch=master)](https://travis-ci.org/BridgePhase/ctf)

## See it in action, on your own terms

All you need to get started is a Java Development Kit 7+ on your path. Then clone the repository and from the main repository directory execute the command:

`gradlew bootRun`

The application will load at [http://localhost:8080/ctf](http://localhost:8080/ctf)
<em>(in Linux systems you may have to execute: `./gradlew bootRun`)</em>


For other deployment options visit our [Getting Started document](documentation/GettingStarted.md) 

## Category Level II Requirements
* Team members and roles are presented at the [About Us](http://considerbridgephase.com/ctf/#/aboutus) page of our application

* This prototype is powered by Spring Boot, Hibernate, AngularJS, PureCSS, Gradle and others. For a full listing view our [Technologies document](documentation/Technologies.md)

* Our implementation is deployed on Amazon Web Services (AWS) EC2 instances using a [Docker](https://www.docker.com) image. Details are included
in our [CI/CD](documentation/CI-CD.md) and [AWS](documentation/aws.md) documents

* Our application includes unit tests for the Java backend services written in jUnit and an end-to-end UI test using [Protractor](http://protractor.org). The results of these tests are documented in [our Travis CI project](https://travis-ci.org/BridgePhase/ctf)

* All branches under this repository run through [our Travis CI project](https://travis-ci.org/BridgePhase/ctf) after a push or pull request. Branches starting with `sprint_*` are automatically deployed to a [Staging environment](http://54.175.58.210:8080/ctf), pushes to `master` automatically deployed by Travis to our [Production environment](http://considerbridgephase.com)

* Our configuration management process revolved around Github issues and pull requests. This is how we controlled what code was ready for production and which one was not. Additional information can be found in our [Configuration Management document](documentation/ConfigurationManagement.md)

* The application is monitored using the AWS monitoring console as well as by using New Relic to provide browser level statistics. ** Attach screenshots!!!! **

* The application is deployed on AWS EC2 instances created from an AMI file which can be generated on-demand. The actual CTF application is run within a Tomcat 7 Java Container.

* During our various sprints and our [bug bash session](http://considerbridgephase.com/ctf/#/about), we created and resolved feedback issues through Github. For more information about how we accomplished this visit our [Configuration Management document](documentation/ConfigurationManagement.md).
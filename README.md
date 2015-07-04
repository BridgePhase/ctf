# Consider the Following

[![Build Status](https://travis-ci.org/BridgePhase/ctf.svg?branch=master)](https://travis-ci.org/BridgePhase/ctf)

_Consider the Following_ is an application built to demonstrate various ways of interacting with data from [Open FDA](https://open.fda.gov). Our goal is to use the data they provide dealing with food recalls, adverse medical events, and medical devices to create a site that provides meaningful information to its users. We implemented various ways to interact with data including prescribed queries (medical devices), geo-location based queries (food recalls), open text-search (medicines and adverse events), and we even used their data to generate human readable headlines live. 

From a technical perspective, we created a fully-automated build and deployment process driven by Travis CI and used Docker along with Amazon Web Services to provie a scalable application. In order to handle query limitations from the Open FDA API, we also created a simple, yet usable [caching system](documentation/Caching.md).

Take [Consider the following](http://considerbridgephase.com) for a test drive!

## See it in action, on your own terms

All you need to get started is a Java Development Kit 7+ on your path. Then clone the repository and from the main repository directory execute the command:

`gradlew bootRun`

The application will load at [http://localhost:8080/ctf](http://localhost:8080/ctf)
<em>(in Linux systems you may have to execute: `./gradlew bootRun`)</em>

For other deployment options visit our [Getting Started document](documentation/GettingStarted.md) 

## Category Level II Requirements
* BridgePhase LLC assembled a team which included a product owner, a devops engineer, developers and functional testers. The names and roles of the team members are presented in the [About Us](http://considerbridgephase.com/ctf/#/aboutus) page of our application

* This prototype is powered by Spring Boot, Hibernate, AngularJS, PureCSS, Gradle and others. For a full listing view our [Technologies document](documentation/Technologies.md)

* Our implementation is deployed on Amazon Web Services (AWS) EC2 instances using a [Docker](https://www.docker.com) image. Details are included
in our [CI/CD](documentation/CI-CD.md) and [AWS](documentation/aws.md) documents

* Our application includes unit tests for the Java backend services written in jUnit and an end-to-end UI test using [Protractor](https://angular.github.io/protractor). The results of these tests are documented in [our Travis CI project](https://travis-ci.org/BridgePhase/ctf)

* All branches under this repository run through [our Travis CI project](https://travis-ci.org/BridgePhase/ctf) after a push or pull request. Branches starting with `sprint_*` are automatically deployed to a [Staging environment](http://ec2-54-209-151-190.compute-1.amazonaws.com:8080), pushes to `master` automatically deployed by Travis to our [Production environment](http://considerbridgephase.com)

* Our configuration management process revolved around [Github issues](https://github.com/BridgePhase/ctf/issues?utf8=✓&q=is%3Aissue+is%3Aclosed) and [pull requests](https://github.com/BridgePhase/ctf/pulls?utf8=✓&q=is%3Aclosed). This is how we controlled what code was ready for production and which one was not. Additional information can be found in our [Configuration Management document](documentation/ConfigurationManagement.md)

* The application is monitored using the AWS monitoring console as well as by using New Relic to provide browser level statistics. These monitoring tools gave us insight into potential bottlenecks and areas for optimization. This is detailed on our [Monitoring document](documentation/Monitoring.md).

* The application is deployed on AWS EC2 instances running Docker as our container platform. Deployment details are included in our [CI/CD Document](documentation/CI-CD.md)

* During our various sprints and our [bug bash session](http://considerbridgephase.com/ctf/#/about), we created and resolved feedback issues through Github. For more information about how we accomplished this visit our [Configuration Management document](documentation/ConfigurationManagement.md).
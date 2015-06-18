# ***Consider the Following***

Take the [CTF Application](http://bp-testdeployslb-1690116647.us-east-1.elb.amazonaws.com/ctf) for a test drive!

[![Build Status](https://travis-ci.org/BridgePhase/ctf.svg?branch=master)](https://travis-ci.org/BridgePhase/ctf)

## Agile Delivery Services Prototype

### **RFQ Requirements**   
#### This README.md file should explain the following:
#### (**Remove items as they are completed!**)

* Assembled a multidisciplinary and collaborative team that includes   
  at a minimum two of the labor categories limited to the Development Pool   
  labor categories to develop the prototype as quoted in Attachment C.   
  The quoter’s proposed mix of labor categories and level of effort for   
  its working prototype, as reflected in Attachment C, shall be evaluated   
  to assess the quoter’s understanding and capability to supply agile delivery services
* Used at least five modern and open-source technologies, regardless of   
  architectural layer(frontend, backend etc)   
* Deployed the prototype on an Infrastructure as a Service (IaaS) or   
  Platform as a Service (PaaS) provider, explanation (link) and indicated   
  which provider was used   
* Wrote unit tests for their code
* Set up or used a continuous integration system to automate the running of   
  tests and continuously deployed their code to their IaaS or PaaS provider   
* Set up or used configuration management h.   set up or used continuous monitoring   
* Deploy their software in a container (i.e., utilized operating-system-level virtualization)
* Used an interactive approach, where feedback informed subsequent work or   
  versions of the prototype   
* Provided sufficient documentation to install and run their prototype on another machine   
  prototype and underlying platforms used to create and run the prototype are openly   
  licensed and free of charge


### Technology Stack
This prototype is a Gradle build driven Java project to quickly create a new Spring powered Java 
Web Application. The front-end is powered using webjars by AngularJS. The main CSS framework is
Pure CSS. 

### What to update?

In the `build.gradle` file you will find the project and deployable anmes, make sure you update those.

* Update or remote the `HelloWorld.java` controller for this application. 
* Update the `basePackages` under `ViewConfiguration` to match the actual packages of your application
* Update the `modules` under `/src/main/resources/public`, these are the items that drive the front-end

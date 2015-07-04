# Considered the following, powered by....

Here are the technologies that make this application possible.

* **User Interface**
    * AngularJS - MVC Framework
	* PureCSS - CSS Framework
	* LESS - CSS pre-compiler
	* C3 - Graphing library
	* Font Awesome - Custom font icons
	* Google Fonts (Lato and Orbitron) - Custom fonts for better readibility

* **Server-side**
	* Spring MVC and Data Framework (with Hibernate) - Backend MVC provider
	* Thymeleaf - HTML template engine 
	* H2 Database - Used for caching
	* Apache Commons - Utility and common libraries

* **Build Automation**
	* Gradle - Java build automation and deployment packager
	* Gulp - JavaScript build automation and pre-processing tasks

* **Dependency Management**
	* Gradle - Java depedencies manager
	* Bower - JavaScript dependency manager for UI libraries
	* Node Package Manager (NPM) - JavaScript dependency manager for tools used to process UI resources (for example Gulp)

* **Continous Integration**
	* TravisCI - Continuous Integration pipeline with Github integration

* **Continous Delivery**
	* TravisCI - Continous delivery pipeline with Github integration
	* AWS Code Deploy with build artifacts on AWS S3 - Deployment manager and orchestration for AWS EC2 instances

* **Platform/Infrastructure** 
	* AWS EC2 running Ubuntu Server 14.04 with Docker 
	* AWS AMI image was created for clonable environments 
	* AWS Elastic Load Balancer - Allows for maintaining the production environment running while deployments take place
	* AWS S3 - for build artifacts
	* Docker Hub - Repository for pre-built docker images

* **Monitoring**
	* AWS EC2 monitoring - Monitoring platform level metrics (memory usage, CPU usage, etc)
	* New Relic Browser - Monitoring platform for viewing browser metrics (Page load times, Ajax response time, global usage metrics) 


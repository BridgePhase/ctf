# Amazon Web Services environment

We deploy _Consider the following_ on Amazon's Web Services Elastic Cloud Compute (EC2) platform. We have two different environments created one for production and one for staging/testing. 

We chose AWS because it allowed us simple deployment integration via Travis CI. This also allowed us to maintain an archive of our build artifacts using Amazon S3 (since CodeDeploy requires artifacts be present in S3). AWS also allowed us to build up a single EC2 instance with our specifications which we later created into an AMI (Amazon Machine Image). All other future instances were clones of this which ensured equal configurations for all our environments. The AMI runs Ubuntu Server 14.04 and together with Docker, allows us to build up a Tomcat environment using the tomcat image [Tomcat:7.0.26-jre7](https://registry.hub.docker.com/_/tomcat/). We use Docker during our deploy operation to build an in-place image which deploys our artifacts to it.

## Production

Our production environment consists of two load balanced, using AWS ELB, AWS EC2 instances served through a domain http://considerbridgephase.com via an AWS Route53 DNS entry. 

## Staging

Our staging environment uses the same AMI image as our production environment. However, this is a single EC2 instance with no load balancing or special DNS name. 

## What about other platforms? 
Although our deployment infrastructure currently depends on Amazon, the application itself can be run as a standalone Java application either through Gradle or as a JAR file. what this means is that the application can be deployed on any system which has a JRE version 7+. To view our deployment options, look at our [Getting Started](documentation/GettingStarted.md) document.

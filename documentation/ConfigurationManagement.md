# Our Configuration Management Process

In order to control the code that we deployed on our production system, we created a simple process involving Git pull requests.

1. Developers work on stories and bug fixes under a certain milestone in feature branches. The stories and bug fixes are documented as Github issues
2. When code is stable, it is merged into a branch starting with `sprint_*`
3. This push triggers a Travis CI build that deploys to our staging server
4. Testers verify the stories and bug fixes in the staging environment so that issues can be closed. 
5. When they are all verified, a pull request is created from the `sprint_` branch into `master`. 
6. Our product owner approves the pull request which triggers a Travis CI deployment into our production system
7. For our staging and production server, Docker images are created that contain all the configuration necessary to run the application so no extra configuration is required on those environments. The Docker images are tagged and published to our [jramirez/bridgephase](https://registry.hub.docker.com/u/jramirez/bridgephase/tags/manage/#) Docker hub reposiory

The deployments to both staging and production are completely automated, and you can read about them in our [Continuous Integration/Deployment document](CI-CD.md) 
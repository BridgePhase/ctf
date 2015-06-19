#!/bin/bash
if [ "${TRAVIS_PULL_REQUEST}" -eq "false" ] && [ "${TRAVIS_BRANCH}" -eq "master" ]; then
	echo "Tagging release as $GIT_TAG"
  	git config --global user.email "builds@travis-ci.com"
  	git config --global user.name "Travis CI"
  	git tag $GIT_TAG -a -m "Generated tag from TravisCI build $TRAVIS_BUILD_NUMBER"
  	git push --quiet https://$TRAVIS_PERMISSIONS@github.com/BridgePhase/ctf $GIT_TAG > /dev/null 2>&1
fi
#!/usr/bin/env bash

# Run ssh-agent (inside the build environment)
eval $(ssh-agent -s)

# Add the SSH key stored in SSH_PRIVATE_KEY variable to the agent store
ssh-add <(echo "$DEPLOY_SSH_PRIVATE_KEY")
# Add deploy server host key to known_hosts to avoid ssh asking to trust new host
mkdir -p ~/.ssh
ssh-keyscan -H -p 8421 $DEPLOY_SERVER_IP >> ~/.ssh/known_hosts

scp -r ./docker-compose.autodeploy.yml $DEPLOY_SERVER_USER@$DEPLOY_SERVER_IP:~/
ssh $DEPLOY_SERVER_USER@$DEPLOY_SERVER_IP "docker login -u gitlab-ci-token -p $CI_BUILD_TOKEN ${CI_REGISTRY};"\
"docker-compose stop;"\
"docker-compose rm website --force;"\
"docker pull ${CI_REGISTRY}/${CI_PROJECT_PATH}:latest;"\
"docker-compose up -d"\
"docker logout"



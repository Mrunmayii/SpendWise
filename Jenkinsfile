pipeline {
    agent any
    environment {
            DOCKER_IMAGE_NAME = 'spendwise-backend'
            DOCKER_TAG = 'mrunmayi12/spendwise-backend:latest'
//             GITHUB_REPO_URL = 'https://github.com/Mrunmayii/SpendWise.git'
            DOCKER_CREDENTIALS = 'docker-cred'
    }
    stages {
        }
        stage('Retrieve .env from Jenkins') {
            steps {
                withCredentials([file(credentialsId: 'spendwise-env', variable: 'ENV_FILE')]) {
                    script {
                        sh "cp $ENV_FILE .env"
                    }
                }
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker --version'
                    sh "docker build -t ${DOCKER_IMAGE_NAME} ."
                }
            }
        }
        stage('Docker Hub Login') {
            steps {
                withCredentials([usernamePassword(credentialsId: env.DOCKER_CREDENTIALS, usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh '''
                        echo "Logging into Docker Hub..."
                        echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin || echo "Docker login failed!"
                    '''
                }
            }
        }
        stage('Push Docker Images') {
            steps {
                script {
                    sh "docker tag ${DOCKER_IMAGE_NAME} ${DOCKER_TAG}"
                    sh "docker push ${DOCKER_TAG}"
                }
            }
        }
        stage('Clean Up Docker Images') {
            steps {
                sh "docker rmi ${DOCKER_TAG} || true"
                sh "docker rmi ${DOCKER_IMAGE_NAME} || true"
            }
        }
        stage('Deploy using Ansible') {
            steps {
                withCredentials([file(credentialsId: 'spendwise-env', variable: 'ENV_FILE')]) {
                    script {
                        sh '''
                            ansible-playbook -i inventory.ini deploy.yml --extra-vars "ENV_PATH=$(pwd)/.env"
                        '''
                    }
                }
            }
        }
    }
}

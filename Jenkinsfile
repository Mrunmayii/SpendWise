pipeline {
    agent any
    environment {
            DOCKER_IMAGE_NAME = 'spendwise-backend'
            DOCKER_TAG = 'mrunmayi12/spendwise-backend:latest'
//             GITHUB_REPO_URL = 'https://github.com/Mrunmayii/SpendWise.git'
            DOCKER_CREDENTIALS = 'docker-cred'
            DB_USER = credentials('DB_USER')
            DB_PASS = credentials('DB_PASS')
            JWT_SECRET = credentials('JWT_SECRET')
    }
    stages {
        stage('Set Environment Variables') {
            steps {
                withCredentials([string(credentialsId: 'DB_USER', variable: 'DB_USER'),
                                 string(credentialsId: 'DB_PASS', variable: 'DB_PASS')]) {
                    script {
                        env.DB_USER = DB_USER
                        env.DB_PASS = DB_PASS
                    }
                }
            }
        }
//         stage('Setup Network & Start Database') {
//             steps {
//                 script {
//                     sh 'docker network create spendwise-network || true'
//
//                     sh '''
//                     docker stop spendwise-db || true
//                     docker rm spendwise-db || true
//                     '''
//
//                     sh '''
//                     docker run -d --name spendwise-db --network spendwise-network \
//                     -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=spendwise \
//                     -e MYSQL_USER=${DB_USER} -e MYSQL_PASSWORD=${DB_PASS} \
//                     -v mysql_data:/var/lib/mysql -p 3307:3306 mysql:latest
//                     '''
//
//                     sleep(15)
//                 }
//             }
//         }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker --version'
                    sh "docker build --build-arg DB_USER=${DB_USER} --build-arg DB_PASS=${DB_PASS} --build-arg JWT_SECRET=${JWT_SECRET} -t ${DOCKER_IMAGE_NAME} ."
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
                withCredentials([usernamePassword(credentialsId: 'local-cred', usernameVariable: 'ANSIBLE_USER', passwordVariable: 'ANSIBLE_PASS'),
                 string(credentialsId: 'DB_USER', variable: 'DB_USER'),
                 string(credentialsId: 'DB_PASS', variable: 'DB_PASS')
                ]) {
                      sh '''
                            ansible-playbook -i inventory.ini deploy.yml --extra-vars "ansible_user=$ANSIBLE_USER ansible_ssh_pass=$ANSIBLE_PASS DB_USER=$DB_USER DB_PASS=$DB_PASS JWT_SECRET=$JWT_SECRET"
                         '''
                }
            }
        }
    }
}

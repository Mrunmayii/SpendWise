pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t expense-tracker .'
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker run -p 9090:8080 -d expense-tracker'
            }
        }
    }
}

pipeline {
    agent any

    tools {
        maven 'mvn'
    }

    environment {
        SONARQUBE_TOKEN = credentials('my-key')
        SONARQUBE_SERVER = 'SonarQubeServer'
        DOCKER_COMPOSE_FILE = 'docker-compose.yml'
        DOCKER_CREDENTIALS_ID = 'docker-hub'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/OussamaCherkaoui/getInspired_Back.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean package'
                bat 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool 'SonarQube'
                    bat "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=GetInspired -Dsonar.sources=. -Dsonar.host.url=${SONARQUBE_SERVER} -Dsonar.login=${SONARQUBE_TOKEN} -Dsonar.java.binaries=target/classes"
                }
            }
        }

        stage('Build Docker Images & Push') {
            steps {
                script {
                    def dockerImage = docker.build("oussamacherkaoui/getinspired:${env.TAG_VERSION ?: 'latest'}")
                    docker.withRegistry('https://index.docker.io/v1/', 'docker-hub') {
                        dockerImage.push()
                    }
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                bat 'docker-compose up'
            }
        }
    }
}

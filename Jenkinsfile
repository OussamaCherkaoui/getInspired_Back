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
                        withSonarQubeEnv(SONARQUBE_SERVER) {
                                        bat "mvn sonar:sonar -Dsonar.token=${SONARQUBE_TOKEN}"
                        }
                }
            }
        }

        stage('Build Docker Images') {
                    steps {
                        script {
                                bat "docker build -t getinspired ."
                        }
                    }
        }

//        stage('Quality Gate Check') {
//                    steps {
//                        timeout(time: 5, unit: 'MINUTES') {
//                            waitForQualityGate abortPipeline: true
//                        }
//                    }
//        }

        stage('Tag and Push Docker Images') {
                    steps {
                        script {
                            docker.withRegistry('https://index.docker.io/v1/','docker-hub') {
                                    def imageName = "oussamacherkaoui/getinspired:getInspired"
                                    bat """
                                        docker tag getinspired:latest ${imageName}
                                        docker push ${imageName}
                                    """
                            }
                        }
                    }
        }

        stage('Run Docker Compose') {
            steps {
                script {
                    dir('getInspired') {
                        bat 'docker-compose up -d'
                    }
                }
            }
        }
    }
}

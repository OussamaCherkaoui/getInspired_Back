pipeline {
    agent any

    tools {
        maven 'mvn'
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('docker-hub')
        SONARQUBE_TOKEN = 'my-key'
        SONARQUBE_SERVER = 'SonarQubeServer'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/OussamaCherkaoui/getInspired_Back.git'
            }
        }

        stage('Build & Test Microservices') {
            parallel {
                stage('Build & Test GetInspired') {
                    steps {
                        dir('GetInspired') {
                            withMaven(maven: 'mvn') {
                                bat 'mvn clean package'
                            }
                        }
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool 'SonarQube'

                    dir('GetInspired') {
                        bat "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=GetInspired -Dsonar.sources=. -Dsonar.host.url=${SONARQUBE_SERVER} -Dsonar.login=${SONARQUBE_TOKEN} -Dsonar.java.binaries=target/classes"
                    }
                }
            }
        }

        stage('Build Docker Images & Push') {
            parallel {
                stage('Build Docker & Push for GetInspired') {
                    steps {
                        dir('GetInspired') {
                            script {
                                def dockerImage = docker.build("oussamacherkaoui/getinspired:${env.TAG_VERSION ?: 'latest'}")
                                docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                                    dockerImage.push()
                                }
                            }
                        }
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

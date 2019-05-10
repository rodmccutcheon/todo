pipeline {
    agent none
    options {
        ansiColor('xterm')
    }
    stages {
        stage('Build') {
            agent {
                docker {
                    image 'maven:3.6.1-jdk-8-alpine'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            agent {
                docker {
                    image 'maven:3.6.1-jdk-8-alpine'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'todo-web/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Sonarqube') {
            agent any
            environment {
                scannerHome = tool 'SonarQubeScanner'
            }
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh "${scannerHome}/bin/sonar-scanner"
                }
                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Test Terraform') {
            agent {
                docker {
                    image 'hashicorp/terraform:latest'
                    label 'LINUX-SLAVE'
                    args  '--entrypoint="" -u root -v /opt/jenkins/.aws:/root/.aws'
                }
            }
            steps {
                sh 'terraform version'
                //sh 'terraform init -backend-config="bucket=${ACCOUNT}-tfstate" -backend-config="key=${TF_VAR_stack_name}/terraform.tfstate" -backend-config="region=us-west-2"'
            }
        }
        stage('Deploy') {
            steps {
                echo 'TODO: deploy step'
            }
        }
    }
}

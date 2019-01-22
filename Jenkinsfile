pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            echo 'Todo: deploy step'
        }
    }
}

//git@bitbucket.org:organicresponse/tellus.git

pipeline {
    agent any
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    agent {
        dockerfile {
            filename 'docker/Dockerfile'
        }
    }
    triggers {
        cron('H H(13-16) * * *')
    }
    tools {
        maven 'apache-maven-3.5.4'
    }
    pre {
        always {
            cleanWs()
            copyArtifacts(
                    projectName: 'tellus-ui',
                    selector: 'lastSuccessful',
                    filter: 'dist/**',
                    target: '${WORKSPACE}/tellus-web/src/main/resources',
                    fingerprintArtifacts: true,
                    optional: true
            )
        }
    }
    stages {
        stage('Copy Archive') {
            steps {
                script {
                    step ([$class: 'CopyArtifact',
                           projectName: 'Create_archive',
                           filter: "packages/infra*.zip",
                           target: 'Infra']);
                }
            }
        }
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deliver') {
            steps {
                sh './jenkins/scripts/deliver.sh'
            }
        }
    }
}

// mv "${WORKSPACE}/tellus-web/src/main/resources/dist" "${WORKSPACE}/tellus-web/src/main/resources/frontend"

// mv "${WORKSPACE}/tellus-web/target/tellus-web-${POM_VERSION}.war" "${WORKSPACE}/tellus-web/target/tellus-web-${POM_VERSION}-dev-${BUILD_NUMBER}.war"



// aws elasticbeanstalk create-application-version --region ap-southeast-2 --application-name tellus --version-label build-${BUILD_NUMBER}-dev --source-bundle S3Bucket="tellus-build-artifacts",S3Key=tellus-web-${POM_VERSION}-dev-${BUILD_NUMBER}.war
//
// aws elasticbeanstalk update-environment --region ap-southeast-2 --environment-name tellus-dev --version-label build-${BUILD_NUMBER}-dev
pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
//    triggers {
//        cron('H H(13-16) * * *')
//    }
//    tools {
//        maven 'apache-maven-3.5.4'
//    }
//    pre {
//        always {
//            cleanWs()
//            copyArtifacts(
//                    projectName: 'tellus-ui',
//                    selector: 'lastSuccessful',
//                    filter: 'dist/**',
//                    target: '${WORKSPACE}/tellus-web/src/main/resources',
//                    fingerprintArtifacts: true,
//                    optional: true
//            )
//        }
//    }
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
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'TODO: deploy step'
            }
        }
    }
}

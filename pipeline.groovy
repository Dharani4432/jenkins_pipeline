pipeline {
    agent any

    environment {
        DIRECTORY_PATH = "/path/to/your/code"
        STAGING_ENVIRONMENT = "staging"
        PRODUCTION_ENVIRONMENT = "DHARANI_REDDY"
    }

    stages {
        stage('Build') {
            steps {
                echo "Building the code..."
                echo "Fetch the source code from the directory path: $DIRECTORY_PATH"
                echo "Compile the code using maven Tool"

            }
        }
        
        stage('Unit and Integration Tests') {
            steps {
                echo "Running unit tests..."
                echo "maven test tool" 
                echo "Running integration tests..."
                echo "selenium test tool"
            }
            post {
                success {
                    emailext subject: "Tests status email",
                    body: "Unit & Integration Tests were successful",
                    to: "dharanireddii08@gmail.com",
                    attachLog: true
                             
                            
                }
                failure {
                    emailext subject: "Tests status email",
                    body: "Unit & Integration Tests failed",
                    to: "dharanireddii08@gmail.com",
                    attachLog: true
                }
            }
        }
        
        stage('Code Analysis') {
            steps {
                echo 'Analyzing code quality...'
                echo 'excuting code with SonarQube'
            }
        }
        
        stage('Security Scan') {
            steps {
                echo 'Scanning for vulnerabilities...'
                echo 'Performing security scan with OWASP ZAP '
            }
            post {
                success {
                    emailext subject: "Security Scan status email",
                    body: "Security Scan was successful",
                    to: "dharanireddii08@gmail.com",
                     attachLog: true
                }
                failure {
                    emailext  subject: "Security Scan status email",
                    body: "Security Scan failed",
                    to: "dharanireddii08@gmail.com",
                     attachLog: true
                }
            }
        }
        
        stage('Deploy to Staging') {
            steps {
                echo "Deploying to staging environment...: $STAGING_ENVIRONMENT"
                echo "AWS CodeDeploy tool"
            }
        }

        stage('Integration Tests on Staging') {
            steps {
                script {
                echo 'Running integration tests on staging...'
                echo "selenium or postman tests"
                }
            }
        }
        
        stage('Deploy to Production') {
            steps {
                echo "Deploying to production environment...: $PRODUCTION_ENVIRONMENT"
                echo "AWS CodeDeploy tool"
            }
        }
        stage('Jenkins File Execution') {
            steps {
                echo "Pipeline Script has been Executed Successfully"
            }
        }
    }
}

pipeline { 
    agent any 
    stages { 
        
        stage ('Build') { 
            steps{
                echo "Building the test automation for demo cart app"
            }
        }
        
        stage('Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh "mvn clean install"
                }
            }
        }
                
        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
        
    }
}

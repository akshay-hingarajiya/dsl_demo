pipeline {
    agent any

    stages {
        stage('cloning repo ... ') {
            steps {
                git 'https://github.com/akshay-hingarajiya/dsl_demo.git'
            }
        }
        
        stage('build'){environment {
                HOME = '.'
            }
            agent{
                docker { 
                    image 'python:2' 
                    reuseNode true
                }    
            }
            steps{
                sh "pip install -r /$WORKSPACE/python_dsl_job_example/python/requirements.txt"
                sh "python /$WORKSPACE/python_dsl_job_example/python/main.py"
            }
        }
            
    }
    post{
        success{
            echo "Cool :)"
        }
    }
}

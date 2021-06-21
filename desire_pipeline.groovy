pipeline {
    agent any

    stages {
        stage('cloning repo ... ') {
            steps {
                git 'https://github.com/akshay-hingarajiya/dsl_demo.git'
            }
        }
         stage('build')
        {
            steps{
                    bat 'docker build -t python-demo .'
                    bat 'docker run -d -p 8181:8181 python-demo'
                    
            }
            
        }
        
  /*      stage('build'){environment {
                HOME = '.'
            }
            agent{
                docker { 
                    image 'python:2' 
                    reuseNode true
                }    
            }
            steps{
                sh "pip install -r $workspace/python_dsl_job_example/python/requirements.txt"
                sh "python $workspace/python_dsl_job_example/python/main.py"
            }
        }*/
            
    }
    post{
        success{
            echo "Cool :)"
        }
    }
}

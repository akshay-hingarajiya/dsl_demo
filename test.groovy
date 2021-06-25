//this is a groovy script for jenkins job dsl.
//in this script we define a job for two project,python and nodejs. 
//first we define a if else condition for which job is run.
//after that we write a function for 

def ispythonproject=true

    // create CI jobs
    if(ispythonProject==true) {
      createCIJobforPython(python,github.com/akshay-hingarajiya/dsl_demo.git,master)   //function call for run python project
    }
    else if(isNodeProject){
        // Pass params accordingly
      createDeployJob(node_demo,github.com/couchbaselabs/space-x-app.git,master)  //function call for run node project 
    }
    else {
      print "No CI Jobs will be generated for ${gitProjectName}"
    }
  
// this function def is for pyhon project 

def createCIJobforPython(def ciJobName,def projectGitSshUrlToRepo,def defaultBranch) {
print "run python function"
  pipelinejob(ciJobName) {
  
 
    scm {
      git {
        remote {
          url(projectGitSshUrlToRepo)
   //       credentials(gitCredentials)
        }
        wipeOutWorkspace(true)
        createTag(false)
        branch(defaultBranch)
      }
    }
    triggers {
      cron("H * * * 1-5")
    }
   
    steps {
       bat 'docker build -t python-demo .'
       bat 'docker run --name dsl -d -p 8181:8181 python-demo'
       sleep(time:30,unit:"SECONDS") 
       bat 'docker stop dsl'
       bat 'docker container rm dsl'
    }
 
  }
}

// this function def is for node project

def createDeployJob(def deployJobName,def projectGitSshUrlToRepo,def defaultBranch) {
  job(deployJobName) {
    logRotator {
      daysToKeep(-1)
      numToKeep(10)
    }
  //  label("ci-slave")
    scm {
      git {
        remote {
          url(projectGitSshUrlToRepo)
         // credentials(gitCredentials)
        }
        createTag(false)
        branch(defaultBranch)
       // relativeTargetDir(gitProjectName)
      }
    }
    steps {
      bat("mvn install")
    }
  }
}

//this is a groovy script for jenkins job dsl.
//in this script we define a job for two project,python and nodejs. 
//first we define a if else condition for which job is run.
//after that we write a function for

def P="${Project}"
print (P)
    // create CI jobs
if(P=='python_project') {
      createCIJobforPython('python_project','akshay-hingarajiya/dsl_demo','master')   //function call for run python project
    }
else if(P=='java_project'){
        // Pass params accordingly
      createDeployJob('java_project','jenkins-docs/simple-java-maven-app','master')  //function call for run node project 
    }
    else {
      print "No CI Jobs will be generated"
    }
  
// this function def is for pyhon project 

def createCIJobforPython(def ciJobName,def projectGitUrlToRepo,def defaultBranch) {
//echo "hello"
    pipelineJob("${ciJobName}") {
    definition {
        cps {
            script(readFileFromWorkspace('desire_pipeline.groovy'))
            sandbox()
        }
    }
}

// this function def is for java project
}
def createDeployJob(def deployJobName,def projectGitUrlToRepo,def defaultBranch) {
  job("${deployJobName}") {
    scm {
        github("${projectGitUrlToRepo}", "${defaultBranch}")
    }
    triggers {
        cron("H * * * 1-5")
    }
    steps {
        batchFile('mvn clean package')
    }
}
}


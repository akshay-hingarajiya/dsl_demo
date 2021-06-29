//this is a groovy script for jenkins job dsl.
//in this script we define a job for two project,python and nodejs. 
//first we define a if else condition for which job is run.
//after that we write a function for
/*package javaposse.jobdsl.plugin

import javaposse.jobdsl.dsl.Job
import groovy.transform.Field
import org.jenkinsci.plugins.workflow.job.*
import javaposse.jobdsl.dsl.jobs.MultibranchWorkflowJob
import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Folder
import javaposse.jobdsl.dsl.jobs.WorkflowJob
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.plugin.JenkinsJobManagement
import javaposse.jobdsl.dsl.ContextHelper
import javaposse.jobdsl.dsl.DslContext
import javaposse.jobdsl.dsl.JobParent
import javaposse.jobdsl.dsl.helpers.ConfigFilesContext
import javaposse.jobdsl.plugin.structs.DescribableListContext
*/
String num="${Project}"
print (num)
    // create CI jobs
if(num==10) {
      createCIJobforPython('python_job','akshay-hingarajiya/dsl_demo','master')   //function call for run python project
    }
else if(num==9){
        // Pass params accordingly
      createDeployJob('node_demo','jenkins-docs/simple-java-maven-app','master')  //function call for run node project 
    }
 /*   else {
      print "No CI Jobs will be generated for "
    }*/
  
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
/*    pipelineJob("${ciJobName}") {
        print"hello"
        
    scm {
      git {
        remote {
            url("${projectGitUrlToRepo}")
   //       credentials(gitCredentials)
        }
        wipeOutWorkspace(true)
        createTag(false)
          branch("${defaultBranch}")
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
*/
// this function def is for node project
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


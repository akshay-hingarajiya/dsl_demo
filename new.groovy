job(new) {
 /*   logRotator {
      daysToKeep(-1)
      numToKeep(10)
    }*/
   /* parameters {
      stringParam("project.name", gitProjectName)
      stringParam("project.version", defaultBranch)
      stringParam("web.context.root", webContextRoot)
      booleanParam("junit.skip.tests", false)
      stringParam("javac.version", projectJavacVersion)
    }*/
   //label("ci-slave")
    //jdk(projectjenkinsJdkVersion)
    scm {
      git {
        remote {
          url('https://github.com/robmazan/maven-nodejs-sample.git')
      //    credentials(gitCredentials)
        }
        wipeOutWorkspace(true)
        createTag(false)
        branch("master")
      }
    }
    triggers {
      cron("H * * * 1-5")
    }
   /* wrappers {
      preBuildCleanup()
    }*/
  /*  steps {
      ant("build") {
        buildFile "build.xml"
        antInstallation antVersion
        javaOpt("-Xmx1G -XX:MaxPermSize=512M")
      }
    }*/
	 steps {
        batchFile('mvn install')
   // publishers {
     // chucknorris()
     // archiveJunit("results/junit/**/*.xml")
    //  mailer(mailRecipients, true, true)
   // }
  }
}

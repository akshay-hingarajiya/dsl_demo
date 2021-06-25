pipelineJob('python_dsl_job_example') {
    definition {
        cps {
            script(readFileFromWorkspace('desire_pipeline.groovy'))
            sandbox()
        }
    }
}
job('Hello World') {
  steps {
    batchFile('echo Hello World!')
  }
}

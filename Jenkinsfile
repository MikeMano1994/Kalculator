#!groovy

pipeline {
  agent none
  stages {
    stage('Gradle Install') {
      agent {
        docker {
          image 'gradle:4.0'
        }
      }
      steps {
        sh 'gradle clean install'
      }
    } 
  }
}

#Maven Install
#mvn clean install

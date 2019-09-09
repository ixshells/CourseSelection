pipeline {
    triggers {
        pollSCM('H/5 8-23 * * 1-5')
    }

    stages {
        stage('Checkout') {
            steps {
               checkout scm
            }
        }
    }
}

# Simple Study Viewer Backend  

### Run with Intellij Idea

1. Open project with Intellij Idea and let the dependencies be imported through gradle
2. Click on Run button

### Run with any other IDE

1. Open as a gradle project or manually import gradle dependencies
2. Run the main method of `com.tayaba.simplestudyviewer.SimplestudyviewerApplication`

### Database Configuration

Application built to be ***Profile*** aware, meaning in the dev environment it will run on **H2 database** and run a set of test data migration. Find DB config at ***resources/application-dev.properties***

### Unit Tests

Unit tests are present for only the `StudyService` class. I have written **5 test cases** as a proof of concept.

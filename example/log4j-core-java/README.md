# **LM Log4j Appender Core Java Example**
This example sends log4j logs to LMLogs platform.


## To run app follow below steps:

#### Step 1 :
Clone this repository 

#### Step 2 :
As this project uses log4-appender-logicmonitor and it is published into github package, To use that package
export below env variable before building the project.
```unix
export GITHUB_USERNAME="<username>"
export GITHUB_TOKEN="<token>"
```

#### Step 3 :
Build the project with below command.
```unix
./gradlew clean build
````
it will generate fat jar in build/libs folder
#### Step 4 :
To send logs to your LM portal export below env variable before running the jar.
```unix
export LM_COMPANY="<portal-name>"
export LM_ACCESS_ID="<access-id>"
export LM_ACCESS_KEY="<access-key>"
```
#### Step 5 :
Run the jar from the same terminal where you exported above env variable.
```unix
java -jar build/libs/log4j-core-java-1.0-SNAPSHOT.jar
````

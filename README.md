# **LM Log4j Appender Java**
Use LM Log4j appender to export your java application logs to LM platform.


## To Use Log4j Appender follow below steps:

#### Step 1 :
Clone this repository and build the project locally.
```
./gradlew clean build publishToMavenLocal
```

#### Step 2 :
We can use LM Log4j Appender by adding dependency in build.gradle file.
##### Gradle
```groovy
dependencies {
    implementation 'com.logicmonitor:log4j-appender-logicmonitor:0.0.1-alpha'
}
```
##### Maven
```xml
 <dependency>
    <groupId>com.logicmonitor</groupId>
    <artifactId>log4j-appender-logicmonitor</artifactId>
    <version>0.0.1-alpha</version>
  </dependency>
 ```       
#### Step 4 :
Change the log4j2.xml as below
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN" packages="com.logicmonitor.log4j2appender">
  <Appenders>
    <LMAppender name="LmAppender">
        <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
    </LMAppender>
  </Appenders>
  <Loggers>
    <Root level="info">
      <AppenderRef ref="LmAppender"/>
    </Root>
  </Loggers>
</Configuration>
```

#### Step 5 :
Build the application.

#### Step 6 :
Export the authentication variable.
```
export LM_COMPANY="<company_name>"
export LM_ACCESS_ID="<accessId>"
export LM_ACCESS_KEY="<accessKey>"
```

#### Step 7 :
Run the application.

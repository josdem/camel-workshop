Mailbox Reader Apache Camel
------------------------------------

#### Invoke Spock test as follow:

```bash
gradle test
```

#### Configuration

In your computer's home directory: `${home}`, create a directory called: `.mailbox-reader` then inside create a file called `application.properties` with this content

```properties
runtime=60000
```

#### Build

```bash
gradle build
```

#### Run

```bash
 java -jar mailbox-reader-0.0.1-SNAPSHOT.jar
```

#### Features

* This approach is using Gmail account with IMAP enabled (Settings > Forwarding and POP/IMAP)
* Read body email usgin text/plain as ContentType
* Read body email using  multipart/mixed as ontenType
* Read and save an attachment file from the mail


#### Read this as reference

* http://josdem.io/techtalk/camel/mailbox_reader/
* http://josdem.io/techtalk/spring/spring_unit_testing_spock/
* http://josdem.io/techtalk/spring/spring_boot/
* http://josdem.io/techtalk/java/s3_aws_lambda/
* http://josdem.io/techtalk/spring/spring_gradle/
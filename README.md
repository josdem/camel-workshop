Hello World Apache Camel
------------------------------------

#### Read this as reference

* http://josdem.io/techtalk/camel/


Mailbox Reader Apache Camel
------------------------------------

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

package com.josdem.hello

import spock.lang.Specification

class FileCopierWithCamelSpec extends Specification {

  FileCopierWithCamel copier = new FileCopierWithCamel()

  void "should copy a file using camel"(){
  given:"An camel context intialization "
    copier.start()
  when:"We wait for camel copy a file and stop context"
    Thread.sleep(5000)
    copier.stop()
  then:"We expect file copied"
    File destination = new File("src/main/resources/destination/message.txt")
    destination.text.contains('Hello Camel')
  }

}

package com.josdem.hello

import spock.lang.Specification

class FileCopierWithCamelSpec extends Specification {

  FileCopierWithCamel copier = new FileCopierWithCamel()

  void "should copy a file using camel"(){
  given:"A source file"
    File source = new File("src/groovy/com/josdem/hello/resources/source.txt")
  and:"A destination file"
    File destination = new File("src/groovy/com/josdem/hello/resources/destination.txt")
  when:"We send a copy action"
    copier.copy(source,destination)
  then:"We expect file copied"
    destination.text = 'Hello Camel'
  }
}

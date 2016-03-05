package com.josdem.hello

import org.apache.camel.CamelContext
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.builder.RouteBuilder

class FileCopierWithCamel {
  CamelContext context
  String source = "src/main/resources/source"
  String destination = "src/main/resources/destination"

  FileCopierWithCamel(){
    context = new DefaultCamelContext()
    context.addRoutes(new RouteBuilder(){
      void configure(){
        from("file:${source}?noop=true").to("file:${destination}")
      }
    })
  }

  def start(){
    context.start()
  }

  def stop(){
    context.stop()
  }
}

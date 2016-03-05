package com.josdem.hello

class FileCopierWithCamel {
  CamelContext context = new DefaultCamelContext()

  def copy(String source, String destination){
    context.addRoutes(new RouteBuilder(){
      void configure(){
        from("file:${source}?noop=true".to("file:${destination}"))
      }
    })
  }
}

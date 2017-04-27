package com.jos.dem.mailbox.reader

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING

import org.springframework.stereotype.Component

import org.apache.camel.CamelContext
import org.apache.camel.Processor
import org.apache.camel.Exchange
import org.apache.camel.Message
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.builder.RouteBuilder

import javax.annotation.PostConstruct
import javax.activation.DataHandler
import javax.mail.internet.MimeMultipart
import javax.mail.internet.MimeMessage
import javax.mail.BodyPart

import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Component
class InboxReader {

	CamelContext context

  Logger log = LoggerFactory.getLogger(this.class)

	@PostConstruct
	void setup(){
		context = new DefaultCamelContext()
		context.addRoutes(new RouteBuilder(){
			void configure(){
				from("imaps://imap.gmail.com?username=contact@josdem.io&password=password"
					+ "&delete=false&unseen=true&consumer.delay=60000")
				.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						Message message = exchange.getIn()
						log.info "exchange: ${exchange.dump()}"
						log.info "message: ${message.dump()}"
						if(message.getBody() instanceof MimeMultipart){
							MimeMultipart mimeMultipart = message.getBody()
							log.info "mimeMultipart: ${mimeMultipart.dump()}"
						} else {
							String body = message.getBody()
							log.info "body: ${body}"
						}
					}	
					})
				.to("log:newmail");
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
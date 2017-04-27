package com.jos.dem.mailbox.reader

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING

import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.Files

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
						Map<String, DataHandler> attachments = exchange.getIn().getAttachments()
						if (attachments.size() > 0) {
						  for (String name : attachments.keySet()) {
						  	DataHandler dh = attachments.get(name);
						  	String filename = dh.getName();
						  	log.info "Attachement file name: ${filename}"
						  	InputStream objectData = dh.getInputStream();
						  	String destinationPath = "${filename}"
						  	Path target = Paths.get(destinationPath)
						  	Files.copy(objectData, target, REPLACE_EXISTING)
						  	target.toFile()
						  }
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
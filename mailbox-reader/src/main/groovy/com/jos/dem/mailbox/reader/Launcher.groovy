package com.jos.dem.mailbox.reader

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired

import com.jos.dem.mailbox.reader.InboxReader

import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Component
class Launcher{

  @Autowired
  InboxReader inboxReader

  Logger log = LoggerFactory.getLogger(this.class)

  void start(){
    log.info 'Reading message'
    inboxReader.start()
    Thread.sleep(60000)
    inboxReader.stop()
  }

}
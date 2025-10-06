package com.airiot.fi.service.ai.cmds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImportSetupSlashCommandImpl implements SlashCommandHandler {

  private static final Logger log = LoggerFactory.getLogger(ImportSetupSlashCommandImpl.class);

  @Override
  public String handle( String args) {
    log.debug("{} handle start wait: {}", this, args);
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    log.debug("wait done: {}", args);
    return "handled here! -> " + args;
  }
}

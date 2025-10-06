package com.airiot.fi.service.ai.cmds;

public enum Commands {

  IMPORT_SETUP(new ImportSetupSlashCommandImpl());

  private final SlashCommandHandler handler;

  Commands(SlashCommandHandler handler) {
    this.handler = handler;
  }

  public String handle(String args) {
    return handler.handle(args);
  }

}

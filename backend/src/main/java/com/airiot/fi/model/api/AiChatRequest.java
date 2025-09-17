package com.airiot.fi.model.api;

public class AiChatRequest {

  private String prompt;

  public AiChatRequest() {
  }

  public String getPrompt() {
    return this.prompt;
  }

  public void setPrompt(String prompt) {
    this.prompt = prompt;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof AiChatRequest)) return false;
    final AiChatRequest other = (AiChatRequest) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$prompt = this.getPrompt();
    final Object other$prompt = other.getPrompt();
    if (this$prompt == null ? other$prompt != null : !this$prompt.equals(other$prompt)) return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof AiChatRequest;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $prompt = this.getPrompt();
    result = result * PRIME + ($prompt == null ? 43 : $prompt.hashCode());
    return result;
  }

  public String toString() {
    return "AiChatRequest(prompt=" + this.getPrompt() + ")";
  }
}

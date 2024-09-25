package dev.tiltrikt.mediator.exception;

import org.jetbrains.annotations.NotNull;

public class CommandException extends RuntimeException {

  public CommandException(@NotNull String message) {
    super(message);
  }
}

package dev.tiltrikt.mediator.model;

import org.jetbrains.annotations.NotNull;

public interface CommandHandler<T, R> {

  @NotNull R handle(@NotNull T command);

}


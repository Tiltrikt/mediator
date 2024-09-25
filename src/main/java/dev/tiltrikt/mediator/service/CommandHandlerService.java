package dev.tiltrikt.mediator.service;

import org.jetbrains.annotations.NotNull;
import dev.tiltrikt.mediator.exception.CommandException;
import dev.tiltrikt.mediator.model.CommandHandler;

public interface CommandHandlerService {

  @NotNull <T, R> CommandHandler<T, R> getByCommandClassAndResponseClass(@NotNull Class<T> commandClass,
                                                                         @NotNull Class<R> responseClass) throws CommandException;
}

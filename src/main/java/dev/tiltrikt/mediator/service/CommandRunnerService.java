package dev.tiltrikt.mediator.service;

import org.jetbrains.annotations.NotNull;
import dev.tiltrikt.mediator.exception.CommandException;
import dev.tiltrikt.mediator.model.CommandRunner;

public interface CommandRunnerService {

  @NotNull <T> CommandRunner<T> getByCommandClass(@NotNull Class<T> commandClass) throws CommandException;
}

package dev.tiltrikt.mediator.service;

import dev.tiltrikt.mediator.exception.CommandException;
import dev.tiltrikt.mediator.model.CommandRunner;
import org.jetbrains.annotations.NotNull;

public interface CommandRunnerService {

  @NotNull <T> CommandRunner<T> getByCommandClass(@NotNull Class<T> commandClass) throws CommandException;
}

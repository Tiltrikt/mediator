package dev.tiltrikt.mediator.repository;

import dev.tiltrikt.mediator.exception.CommandException;
import dev.tiltrikt.mediator.model.CommandRunner;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface CommandRunnerRepository extends ContextRepository {

  @NotNull <T> Optional<CommandRunner<T>> findByCommandClass(@NotNull Class<T> commandClass) throws CommandException;
}

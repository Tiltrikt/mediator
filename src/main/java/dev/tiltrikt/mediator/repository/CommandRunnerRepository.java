package dev.tiltrikt.mediator.repository;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import dev.tiltrikt.mediator.exception.CommandException;
import dev.tiltrikt.mediator.model.CommandRunner;

public interface CommandRunnerRepository {

  @NotNull <T> Optional<CommandRunner<T>> findByCommandClass(@NotNull Class<T> commandClass) throws CommandException;
}

package dev.tiltrikt.mediator.repository;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import dev.tiltrikt.mediator.exception.CommandException;
import dev.tiltrikt.mediator.model.CommandHandler;

public interface CommandHandlerRepository {

  @NotNull <T, R> Optional<CommandHandler<T, R>> findByCommandClassAndResponseClass(
      @NotNull Class<T> commandClass,
      @NotNull Class<R> responseClass) throws CommandException;
}

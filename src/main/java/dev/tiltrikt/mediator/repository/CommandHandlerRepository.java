package dev.tiltrikt.mediator.repository;

import dev.tiltrikt.mediator.exception.CommandException;
import dev.tiltrikt.mediator.model.CommandHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface CommandHandlerRepository extends ContextRepository {

  @NotNull <T, R> Optional<CommandHandler<T, R>> findByCommandClassAndResponseClass(
      @NotNull Class<T> commandClass,
      @NotNull Class<R> responseClass) throws CommandException;
}

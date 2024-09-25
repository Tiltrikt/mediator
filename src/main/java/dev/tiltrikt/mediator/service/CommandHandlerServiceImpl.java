package dev.tiltrikt.mediator.service;

import dev.tiltrikt.mediator.exception.CommandException;
import dev.tiltrikt.mediator.model.CommandHandler;
import dev.tiltrikt.mediator.repository.CommandHandlerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommandHandlerServiceImpl implements CommandHandlerService {

  @NotNull CommandHandlerRepository repository;

  @Override
  public @NotNull <T, R> CommandHandler<T, R> getByCommandClassAndResponseClass(
      @NotNull Class<T> commandClass,
      @NotNull Class<R> responseClass) throws CommandException {

    return repository.findByCommandClassAndResponseClass(commandClass, responseClass)
        .orElseThrow(() -> new CommandException("No command handler found for command: %s".formatted(commandClass)));
  }
}

package dev.tiltrikt.mediator.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import dev.tiltrikt.mediator.exception.CommandException;
import dev.tiltrikt.mediator.model.CommandRunner;
import dev.tiltrikt.mediator.repository.CommandRunnerRepository;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommandRunnerServiceImpl implements CommandRunnerService {

  @NotNull CommandRunnerRepository repository;

  @Override
  public @NotNull <T> CommandRunner<T> getByCommandClass(@NotNull Class<T> commandClass) throws CommandException {

    return repository.findByCommandClass(commandClass)
        .orElseThrow(() -> new CommandException("No command runner found for command: %s".formatted(commandClass)));
  }
}

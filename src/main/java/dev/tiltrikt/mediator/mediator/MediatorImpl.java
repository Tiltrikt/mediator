package dev.tiltrikt.mediator.mediator;

import dev.tiltrikt.mediator.exception.CommandException;
import dev.tiltrikt.mediator.model.CommandHandler;
import dev.tiltrikt.mediator.model.CommandRunner;
import dev.tiltrikt.mediator.service.CommandHandlerService;
import dev.tiltrikt.mediator.service.CommandRunnerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MediatorImpl implements Mediator {

  @NotNull CommandRunnerService commandRunnerService;

  @NotNull CommandHandlerService commandHandlerService;

  @Override
  @SuppressWarnings("unchecked")
  public <T, R> @NotNull R dispatch(@NotNull T command, @NotNull Class<R> responseClass) throws CommandException {
    Class<T> commandClass = (Class<T>) command.getClass();
    CommandHandler<T, R> handler = commandHandlerService.getByCommandClassAndResponseClass(commandClass, responseClass);
    return handler.handle(command);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> void dispatch(@NotNull T command) throws CommandException {
    Class<T> commandClass = (Class<T>) command.getClass();
    CommandRunner<T> runner = commandRunnerService.getByCommandClass(commandClass);
    runner.run(command);
  }

}

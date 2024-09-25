package dev.tiltrikt.mediator.repository;

import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;
import dev.tiltrikt.mediator.exception.CommandException;
import dev.tiltrikt.mediator.model.CommandRunner;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ContextCommandRunnerRepository implements CommandRunnerRepository {

  @NotNull ApplicationContext applicationContext;

  @Override
  @SuppressWarnings("unchecked")
  public @NotNull <T> Optional<CommandRunner<T>> findByCommandClass(@NotNull Class<T> commandClass) throws CommandException {
    ResolvableType type = ResolvableType.forClassWithGenerics(CommandRunner.class, commandClass);
    String[] beanNames = applicationContext.getBeanNamesForType(type);
    if (beanNames.length > 1) {
      throw new CommandException(String.format("Multiple command runners found for command: %s", commandClass));
    }
    return Optional.ofNullable((CommandRunner<T>) applicationContext.getBeanProvider(type).getIfAvailable());
  }
}

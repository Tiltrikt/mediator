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
import dev.tiltrikt.mediator.model.CommandHandler;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ContextCommandHandlerRepository implements CommandHandlerRepository {

  @NotNull ApplicationContext applicationContext;

  @Override
  @SuppressWarnings("unchecked")
  public @NotNull <T, R> Optional<CommandHandler<T, R>> findByCommandClassAndResponseClass(
      @NotNull Class<T> commandClass,
      @NotNull Class<R> responseClass) throws CommandException {

    ResolvableType type = ResolvableType.forClassWithGenerics(CommandHandler.class, commandClass, responseClass);
    String[] beanNames = applicationContext.getBeanNamesForType(type);
    if (beanNames.length > 1) {
      throw new CommandException(String.format("Multiple command handlers found for command: %s", commandClass));
    }
    return Optional.ofNullable((CommandHandler<T, R>) applicationContext.getBeanProvider(type).getIfAvailable());
  }
}

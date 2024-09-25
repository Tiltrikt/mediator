package dev.tiltrikt.mediator.repository;

import dev.tiltrikt.mediator.exception.CommandException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SimpleContextRepository {

  @NotNull ApplicationContext applicationContext;

  @SuppressWarnings("unchecked")
  public <S> Optional<S> findBy(@NotNull Class<?> clazz, Class<?> @NotNull ... generics) {
    ResolvableType type = ResolvableType.forClassWithGenerics(clazz, generics);
    String[] beanNames = applicationContext.getBeanNamesForType(type);
    if (beanNames.length > 1) {
      throw new CommandException(String.format("Multiple command runners found for command: %s", generics[0]));
    }
    return Optional.ofNullable((S) applicationContext.getBeanProvider(type).getIfAvailable());
  }
}

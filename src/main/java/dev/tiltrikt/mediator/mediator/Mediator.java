package dev.tiltrikt.mediator.mediator;

import dev.tiltrikt.mediator.exception.CommandException;
import org.jetbrains.annotations.NotNull;

public interface Mediator {

  <T, R> @NotNull R dispatch(@NotNull T command, @NotNull Class<R> responseClass) throws CommandException;

  <T> void dispatch(@NotNull T command) throws CommandException;

}

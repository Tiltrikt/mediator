package dev.tiltrikt.mediator;

import org.jetbrains.annotations.NotNull;
import dev.tiltrikt.mediator.exception.CommandException;

public interface Mediatr {

  <T, R> @NotNull R dispatch(@NotNull T command, @NotNull Class<R> responseClass) throws CommandException;

  <T> void dispatch(@NotNull T command) throws CommandException;

}

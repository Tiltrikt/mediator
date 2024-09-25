package dev.tiltrikt.mediator.model;

import org.jetbrains.annotations.NotNull;

/**
 * CommandRunner interface is responsible for handling commands of type {@code T}
 * that does not return a result.
 *
 * <p>This interface should be implemented by classes that are meant to process
 * specific commands without returning any value.</p>
 *
 * @param <T> the type of the command to be run
 * @see CommandHandler
 * @since 0.1.4
 */
public interface CommandRunner<T> {

  /**
   * Runs the given command.
   *
   * @param command the command to be run
   */
  void run(@NotNull T command);

}


package company.ryzhkov.datatypes

import company.ryzhkov.implicits._
import company.ryzhkov.typeclasses.Monad.MonadOps

case class State[S, A](runState: S => (A, S))

object State {
  def get[S]: State[S, S] = State(s => (s, s))

  def set[S](s: S): State[S, Unit] = State(_ => ((), s))

  /**
    * Example:
    * <code>
    * def modify[S](f: S => S): State[S, Unit] = State(s => ((), f(s)))
    * </code>
    */
  def modify[S](f: S => S): State[S, Unit] =
    for {
      n <- get[S]
      _ <- set(f(n))
    } yield ()
}

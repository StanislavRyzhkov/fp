package company.ryzhkov.instances

import company.ryzhkov.datatypes.State
import company.ryzhkov.typeclasses.Monad

package object monad {

  class StateMonad[T] extends Monad[State[T, *]] {
    override def pure[A](a: A): State[T, A] = State(t => (a, t))

    override def flatMap[A, B](fa: State[T, A])(f: A => State[T, B]): State[T, B] =
      State(st => {
        val (a, st2) = fa.runState(st)
        val m2       = f(a)
        m2.runState(st2)
      })

    override def map[A, B](fa: State[T, A])(f: A => B): State[T, B] =
      State(st => {
        val (a, st2) = fa.runState(st)
        (f(a), st2)
      })
  }
}

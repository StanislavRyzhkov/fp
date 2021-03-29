package company.ryzhkov

import company.ryzhkov.datatypes.State
import company.ryzhkov.instances.monad.StateMonad
import company.ryzhkov.typeclasses.Monad

package object implicits {
  implicit def stateMonadInstance[T]: Monad[State[T, *]] = new StateMonad[T]
}

package company.ryzhkov

import company.ryzhkov.datatypes.State
import company.ryzhkov.typeclasses.Monad

object Application extends App {

//  val res = State.get.runState(5)
//  println(res)
//
//  val res2 = State.set(7).runState(5)
//  println(res2)

  import company.ryzhkov.datatypes.State._
  import company.ryzhkov.implicits._
  import company.ryzhkov.typeclasses.Monad.MonadOps

  def tick: State[Int, Int] =
    for {
      n <- get[Int]
      _ <- Monad[State[Int, *]].pure(())
      _ <- Monad[State[Int, *]].pure(())
      _ <- Monad[State[Int, *]].pure(())
      _ <- set(n + 1)
    } yield n

  def plus(n: Int, x: Int): Int = {
    val xxx = List.fill(n)(tick)
    ???
  }

//  val r = tick.flatMap(_ => tick).flatMap(_ => tick)

  val r = for {
    _ <- tick
    _ <- tick
    _ <- tick
    _ <- tick
    _ <- tick
  } yield ()

  println(r.runState(0)._2)
}

package company.ryzhkov.typeclasses

trait Monad[F[_]] extends Functor[F] {
  def pure[A](a: A): F[A]

  def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]
}

object Monad {
  def apply[F[_]](implicit ev: Monad[F]): Monad[F] = ev

  implicit class MonadOps[F[_]: Monad, A](fa: F[A]) {
    private val m = implicitly[Monad[F]]

    def flatMap[B](f: A => F[B]): F[B] = m.flatMap(fa)(f)

    def map[B](f: A => B): F[B] = m.map(fa)(f)
  }
}

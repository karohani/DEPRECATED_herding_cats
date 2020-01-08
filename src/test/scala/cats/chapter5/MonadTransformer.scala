package cats.chapter5

import org.scalatest.FlatSpec
import cats.data.OptionT
import cats.instances.list._
import cats.syntax.applicative._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class MonadTransformer extends FlatSpec {

  "Example" should "OptionT" in {
    type ListOption[A] = OptionT[List, A]
    val result1: OptionT[List, Int] = OptionT(List(Option(1)))
    val result2: OptionT[List, Int] = OptionT(List(Option(1)))

    val compose = result1.flatMap { (x: Int) =>
      result2.map { (y: Int) =>
        x + y
      }
    }
    print(compose)
  }

  it should "FutureEither" in {
    import cats.data.{EitherT, OptionT}
    import cats.instances.future._
    import scala.concurrent.ExecutionContext.Implicits.global
    type FutureEither[A] = EitherT[Future, String, A]
    type FutureEitherOption[A] = OptionT[FutureEither, A]
    val futureEitherOr: FutureEitherOption[Int] = for {
      a <- 10.pure[FutureEitherOption]
      b <- 20.pure[FutureEitherOption]
    } yield {
      a + b
    }
    println(Await.result(futureEitherOr.value.value, Duration.Inf))
    // Right(Some(30))

  }

}

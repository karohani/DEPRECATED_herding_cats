package herding.day3

import org.scalatest.{FlatSpec, FunSpec, Matchers}
import cats._, cats.data._, cats.implicits._

class ApplicativeTests extends FunSpec with Matchers {

  describe("HI") {
    describe("") {
      it("") {
        val hs = Functor[List].map(List(1, 2, 3, 4))({
          (_: Int) * (_: Int)
        }.curried)


        val a = {
          (_: Int) * (_: Int)
        }.curried
        println(a(1)); //scala.Function2$$Lambda$143/960733886@157853da
        println(a(1)(2))
        // currying 된 펑션을 만들어준다.
      }
    }
  }
  describe("Applicative") {
    it("") {
      val F = Applicative[Option]
      val what: Option[Int => Int] = F.pure((_: Int) + 3)
      val b: Option[Int] = F.ap({
        F.pure((_: Int) + 3)
      })(F.pure(9))
    }
    it("Util Function") {
      def sequenceA[F[_] : Applicative, A](list: List[F[A]]): F[List[A]] = list match {
        case Nil => Applicative[F].pure(Nil: List[A])
        case x :: xs => (x, sequenceA(xs)) mapN {
          _ :: _
        }
      }

      println(sequenceA(List(1.some, 2.some)))
    }
  }
}


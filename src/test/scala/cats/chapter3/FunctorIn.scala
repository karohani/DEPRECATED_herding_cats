package cats.chapter3

import org.scalatest.FlatSpec
import scala.language.higherKinds
import cats.Functor
import cats.instances.list._
import cats.instances.option._

class FunctorIn extends FlatSpec {
  "test" should "aaa" in {
    val list1 = List(1, 2, 3)
    val list2 = Functor[List].map(list1)(j => j * 2)
    print(list2)

    import cats.instances.function._
    import cats.syntax.functor._
    def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]): F[Int] = start.map(n => n + 1 * 2)


    import cats.instances.option._
    print(doMath(Option(20)))
    // SOME(22)
  }

}

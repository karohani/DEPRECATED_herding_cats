package document.`with`.cats.chapter4.monad

import cats.{FlatMap, Functor, Monad}
import org.scalatest.FunSpec

class Example extends FunSpec {

  describe("기본 문법 테스트") {
    describe("") {
      it("하하하") {
        import scala.language.higherKinds
        import cats.syntax.functor._
        import cats.syntax.flatMap._

        // 1. Ops라는 이름의 함수는 어떻게 붙은건지?
        // 2. def functionaName[T: TypeClass] 형태로 되어 잇는 구문이 어색합니다. 왜 T <: TypeClass가 아닐까요..
        //
        def sumSqure[F[_] : Monad](a: F[Int], b: F[Int]): F[Int] = {
          for {
            x <- a
            y <- b
          } yield x * x + y * y
        }
      }
      it("새롭게 타입을 정의해서 비슷한걸 해보기") {
      }
    }
  }

}

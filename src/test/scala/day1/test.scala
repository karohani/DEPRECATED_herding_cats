package day1

import org.scalatest.{FlatSpec, FunSpec, Matchers}

class test extends FunSpec with Matchers {

  describe("HI") {
    describe("") {
      it("") {
        import cats._, cats.data._, cats.implicits._
        val k = 1234 === "TRUE" // type mismatch error
        println(1 == "TRUE") // always false 컴파일 에러가 나지 않음

        import cats.data.Writer
        import cats.instances.vector._ // for Monoid

        Writer(Vector( "It was the best of times", "it was the worst of times" ), 1859) // res0: cats.data.WriterT[cats.Id,scala.collection.immutable.Vector[ String],Int] = WriterT((Vector(It was the best of times, it was the worst of times),1859))

      }
    }
  }
}

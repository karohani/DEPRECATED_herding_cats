package cats.chapter4

import cats.data.State
import cats.syntax.applicative._
import org.scalatest.FlatSpec

class StateMonad extends FlatSpec{


  type CalcState[A] = State[List[Int], A]

  def operator(function: (Int, Int) => Int): CalcState[Int] = State[List[Int], Int] {
    case b :: a :: tail =>
      val ans = function(a, b)
      (ans :: tail, ans)
    case _ => sys.error("FAIL")
  }
  def operand(num: Int): State[List[Int], Int] = State[List[Int], Int] { stack =>
    (num :: stack, num)
  }

  def evalOne(sym: String) : CalcState[Int] = 	sym match {
    case "+" => operator(_ + _)
    case "-" => operator(_ - _)
    case "*" => operator(_ * _)
    case "/" => operator(_ / _)
    case num => operand(num.toInt)
  }

  def evalAll(input: List[String]): CalcState[Int] =
    input.foldLeft(0.pure[CalcState]){ (a, b) =>
      a.flatMap(_ => evalOne(b))
    }

  "StateMonad" should "1개의 값만 평가할 때" in {
    println(evalOne("4").runA(Nil).value)
  }

  it should "2개의 값을 평가할 때 " in  {
    val program = for {
      _ <- evalOne("1")
      _ <- evalOne("2")
      ans <- evalOne("+")
    }yield ans

    println(s"program runnable : ${program.runA(Nil).value}")
  }
}

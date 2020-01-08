package herding.day3

import simulacrum._

@typeclass trait CanTruthly[A] {
  self =>
  def truthly(a: A): Boolean
}

object CanTruthly {
  def fromTruth[A](f: A => Boolean): CanTruthly[A] = new CanTruthly[A] {
    override def truthly(a: A): Boolean = f(a)
  }
}

class YesNoTypeClass {

}

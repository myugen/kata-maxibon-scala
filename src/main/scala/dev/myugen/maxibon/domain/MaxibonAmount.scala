package dev.myugen.maxibon.domain

case class MaxibonAmount private (value: Int) extends Ordered[MaxibonAmount] {
  def -(that: MaxibonAmount): Either[String, MaxibonAmount] =
    MaxibonAmount.of(this.value - that.value)

  override def compare(that: MaxibonAmount): Int = this.value compare that.value
}

object MaxibonAmount {
  def of(quantity: Int): Either[String, MaxibonAmount] = {
    if (quantity < 0) return Left("The amount of Maxibon cannot be negative")
    Right(MaxibonAmount(quantity))
  }

  val ZERO: MaxibonAmount = MaxibonAmount(0)
  val TWO: MaxibonAmount = MaxibonAmount(2)
  val TEN: MaxibonAmount = MaxibonAmount(10)
}

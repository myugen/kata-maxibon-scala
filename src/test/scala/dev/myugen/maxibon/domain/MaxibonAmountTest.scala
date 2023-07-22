package dev.myugen.maxibon.domain

import org.scalatest.EitherValues
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MaxibonAmountTest extends AnyFlatSpec with Matchers with EitherValues {

  behavior of "MaxibonQty"

  it should "not allow negative quantity of maxibons" in {
    val actual = MaxibonAmount.of(-1)

    actual.left.value should be("The amount of Maxibon cannot be negative")
  }

  it should "subtract the quantity of maxibons" in {
    val nineMaxibons = MaxibonAmount.of(9).value
    val twoMaxibons = MaxibonAmount.of(2).value

    val actual = nineMaxibons - twoMaxibons

    actual.value should be(MaxibonAmount.of(7).value)
  }

}

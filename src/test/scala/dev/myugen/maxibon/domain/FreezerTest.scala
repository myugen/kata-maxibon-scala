package dev.myugen.maxibon.domain

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class FreezerTest extends AnyFlatSpec with Matchers {

  behavior of "Freezer"

  it should "shrink the amount of maxibons" in {
    val initialMaxibons = MaxibonAmount.of(10).toOption.get
    val freezer = Freezer(initialMaxibons)

    val twoMaxibons = MaxibonAmount.of(2).toOption.get

    val expected = MaxibonAmount.of(8).toOption.get

    freezer.shrink(twoMaxibons)

    freezer.currentAmountOfMaxibon should be(expected)
  }

  it should "restock the amount of maxibons" in {
    val initialMaxibons = MaxibonAmount.of(5).toOption.get
    val freezer = Freezer(initialMaxibons)

    freezer.restock()

    freezer.currentAmountOfMaxibon should be(MaxibonAmount.TEN)
  }
}

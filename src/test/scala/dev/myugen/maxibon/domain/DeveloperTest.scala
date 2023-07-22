package dev.myugen.maxibon.domain

import org.scalatest.OptionValues
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DeveloperTest extends AnyFlatSpec with Matchers with OptionValues {

  behavior of "Developer"

  it should "take an amount of Maxibon from the freezer without asking for more Maxibon" in {
    val freezer = Freezer(MaxibonAmount.of(10).toOption.get)
    val developer = Developer("John Doe", MaxibonAmount.of(2).toOption.get)

    val expected = MaxibonAmount.of(8).toOption.get

    val maybeAskingForMoreMaxibons =
      developer.takeAnAmountOfMaxibonFrom(freezer)

    maybeAskingForMoreMaxibons.isEmpty should be(true)
    freezer.currentAmountOfMaxibon should be(expected)
  }

  it should "take an amount of Maxibon from the freezer asking for more Maxibon" in {
    val freezer = Freezer(MaxibonAmount.of(1).toOption.get)
    val developer = Developer("John Doe", MaxibonAmount.of(2).toOption.get)

    val expected = MaxibonAmount.of(0).toOption.get

    val maybeAskingForMoreMaxibons =
      developer.takeAnAmountOfMaxibonFrom(freezer)

    maybeAskingForMoreMaxibons.value should be(Message.templateFor(developer))
    freezer.currentAmountOfMaxibon should be(expected)
  }

}

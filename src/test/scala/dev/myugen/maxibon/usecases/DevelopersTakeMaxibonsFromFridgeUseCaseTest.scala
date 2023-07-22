package dev.myugen.maxibon.usecases

import dev.myugen.maxibon.domain.{Developer, Freezer, MaxibonAmount, Message}
import dev.myugen.maxibon.mocks.FakeSender
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, OptionValues}

class DevelopersTakeMaxibonsFromFridgeUseCaseTest
    extends AnyFlatSpec
    with Matchers
    with BeforeAndAfterEach
    with OptionValues {
  private val fakeNotifier = new FakeSender()
  private val sut = new DevelopersTakeMaxibonsFromFridgeUseCase(fakeNotifier)

  override def beforeEach(): Unit = {
    fakeNotifier.reset()
  }

  behavior of "DevelopersTakeMaxibonsFromFridgeUseCaseTest"

  it should "perform the taking of maxibons from the fridge by the developers without notifying" in {
    val maxibonsToTake = MaxibonAmount.of(2).toOption.get
    val developer = Developer("John Doe", maxibonsToTake)

    val initialMaxibons = MaxibonAmount.of(10).toOption.get
    val freezer = Freezer(initialMaxibons)

    val expectedAmountOfMaxibon = MaxibonAmount.of(8).toOption.get

    sut.execute(List(developer), freezer)

    fakeNotifier.sentMessages.isEmpty should be(true)
    freezer.currentAmountOfMaxibon should be(expectedAmountOfMaxibon)
  }

  it should "notify when there are 2 Maxibon or less in the fridge and restock with 10 Maxibon" in {
    val maxibonsToTake = MaxibonAmount.of(3).toOption.get
    val developer = Developer("John Doe", maxibonsToTake)

    val initialMaxibons = MaxibonAmount.of(2).toOption.get
    val freezer = Freezer(initialMaxibons)

    val expectedAmountOfMaxibon = MaxibonAmount.of(10).toOption.get

    sut.execute(List(developer), freezer)

    fakeNotifier.sentMessages should have length 1
    fakeNotifier.sentMessages.head should be(
      Message("Hi guys, I'm John Doe. We need more maxibons!")
    )
    freezer.currentAmountOfMaxibon should be(expectedAmountOfMaxibon)
  }
}

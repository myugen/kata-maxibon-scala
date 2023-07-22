package dev.myugen.maxibon

import dev.myugen.maxibon.MaxibonKataTest.{generatedDeveloper, generatedFreezer}
import dev.myugen.maxibon.domain.{Developer, Freezer, MaxibonAmount}
import dev.myugen.maxibon.mocks.FakeSender
import dev.myugen.maxibon.usecases.DevelopersTakeMaxibonsFromFridgeUseCase
import faker.ResourceLoader.Implicits._
import faker._
import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

class MaxibonKataTest
    extends AnyFlatSpec
    with Matchers
    with ScalaCheckDrivenPropertyChecks {
  private val fakeNotifier = new FakeSender()
  private val useCase = new DevelopersTakeMaxibonsFromFridgeUseCase(
    fakeNotifier)

  behavior of "Maxibon kata"

  it should "have a freezer with Maxibons to be taken for developers" in {
    forAll(Gen.listOfN(5, generatedDeveloper), generatedFreezer) {
      (developers: List[Developer], freezer: Freezer) =>
        useCase.execute(developers, freezer)
        freezer.currentAmountOfMaxibon should be >= MaxibonAmount.ZERO
    }
  }
}

object MaxibonKataTest {
  val nonNegativeNumbers: Gen[Int] = Arbitrary.arbitrary[Int] suchThat (_ >= 0)
  val generatedMaxibonsQty: Gen[MaxibonAmount] = Arbitrary(for {
    quantity <- nonNegativeNumbers
  } yield MaxibonAmount(quantity)).arbitrary
  val generatedFreezer: Gen[Freezer] = Arbitrary(for {
    maxibons <- generatedMaxibonsQty
  } yield Freezer(maxibons)).arbitrary
  val generatedDeveloper: Gen[Developer] = Arbitrary(for {
    name <- Arbitrary.arbitrary[name.FullName]
    maxibonsToTake <- generatedMaxibonsQty
  } yield Developer(name.value, maxibonsToTake)).arbitrary
}

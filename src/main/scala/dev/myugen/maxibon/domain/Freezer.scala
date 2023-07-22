package dev.myugen.maxibon.domain

case class Freezer(var currentAmountOfMaxibon: MaxibonAmount) {
  def shrink(amountOfMaxibon: MaxibonAmount): Unit = {
    currentAmountOfMaxibon =
      (currentAmountOfMaxibon - amountOfMaxibon).getOrElse(MaxibonAmount.ZERO)
  }

  def restock(): Unit = {
    currentAmountOfMaxibon = MaxibonAmount.TEN
  }
}

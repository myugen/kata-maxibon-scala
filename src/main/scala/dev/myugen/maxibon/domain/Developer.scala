package dev.myugen.maxibon.domain

case class Developer(name: String, amountOfMaxibonToTake: MaxibonAmount) {
  def takeAnAmountOfMaxibonFrom(freezer: Freezer): Option[Message] = {
    freezer.shrink(amountOfMaxibonToTake)
    freezer.currentAmountOfMaxibon match {
      case amountOfMaxibon if amountOfMaxibon < MaxibonAmount.TWO =>
        Some(Message.templateFor(this))
      case _ => None
    }
  }
}

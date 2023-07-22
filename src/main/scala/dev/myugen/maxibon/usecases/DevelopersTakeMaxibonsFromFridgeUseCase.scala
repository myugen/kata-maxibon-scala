package dev.myugen.maxibon.usecases

import dev.myugen.maxibon.domain.{Developer, Freezer, Sender}

class DevelopersTakeMaxibonsFromFridgeUseCase(private val sender: Sender) {
  def execute(developers: List[Developer], freezer: Freezer): Unit = {
    developers.foreach(developer => {
      val maybeMessageAskingForMoreMaxibon =
        developer.takeAnAmountOfMaxibonFrom(freezer)
      maybeMessageAskingForMoreMaxibon.map(message => {
        sender.send(message)
        freezer.restock()
      })
    })
  }
}

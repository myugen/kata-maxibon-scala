package dev.myugen.maxibon.mocks

import dev.myugen.maxibon.domain.{Message, Sender}

class FakeSender(var sentMessages: List[Message] = List.empty) extends Sender {
  override def send(message: Message): Unit = {
    sentMessages = sentMessages :+ message
  }

  def reset(): Unit = {
    sentMessages = List.empty
  }
}

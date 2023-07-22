package dev.myugen.maxibon.domain

case class Message(value: String)

object Message {
  def templateFor(developer: Developer): Message =
    Message(s"Hi guys, I'm ${developer.name}. We need more maxibons!")

}

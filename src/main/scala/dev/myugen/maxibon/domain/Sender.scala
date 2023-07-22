package dev.myugen.maxibon.domain

trait Sender {
  def send(message: Message): Unit
}

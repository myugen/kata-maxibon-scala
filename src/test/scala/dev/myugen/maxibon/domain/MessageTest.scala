package dev.myugen.maxibon.domain

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MessageTest extends AnyFlatSpec with Matchers {

  behavior of "Message"

  it should "prepare template for developer" in {
    val developer = Developer("John Doe", MaxibonAmount.TEN)
    val message = Message.templateFor(developer)

    message should be(Message("Hi guys, I'm John Doe. We need more maxibons!"))
  }

}

package part2actors

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.event.Logging

object ActorLoggingDemo extends App {

  class ActorLogger extends Actor {
    // explicit logging
    val logger = Logging(context.system, this) // system is a member of context

    override def receive: Receive = {
      /*
      Debug, Info, Warning and Error
       */
      case message => logger.info(message.toString)// Log it to see
    }
  }

  val system = ActorSystem("LoggingDemo")
  val actor = system.actorOf(Props[ActorLogger])

  actor ! "Logging a simple message"

  // ActorLogging
  class ActorWithLogging extends Actor with ActorLogging { //trait which provides logging by default
    override def receive: Receive = {
      case (a, b) => log.info("Two Things: {} and {}", a, b)
      case message => log.info(message.toString)
    }
  }

  val simplerActor = system.actorOf(Props[ActorWithLogging])
  simplerActor ! "Logging a msg by extending a trait"

  simplerActor ! (100, 200) // "!" tell method

}
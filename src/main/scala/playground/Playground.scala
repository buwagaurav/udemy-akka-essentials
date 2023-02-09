package playground

import akka.actor.{ActorSystem, ClassicActorSystemProvider}


object Playground extends App {
  val actorSystem = ActorSystem("Hello Akka")

}

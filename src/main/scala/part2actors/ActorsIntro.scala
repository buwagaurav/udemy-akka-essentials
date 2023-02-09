package part2actors

import akka.actor.{Actor, ActorSystem, Props}

object ActorsIntro extends App{

  // 1. Actor System

  val actorSystem = ActorSystem("firstActorSystem") // Error if there is a space in name
  println(actorSystem.name)

  // 2. create actors
  class WordCountActor extends Actor {
    //data
    var totalWords = 0

    //behavior
    def receive: PartialFunction[Any, Unit] = {
      case message: String =>
        println(s"[word counter] I have received: $message")
        totalWords += message.split(" ").length
        // if we receive anything else
      case msg => println(s"[word counter] I cannot understand ${msg.toString}")
    }
  }


  // 3. instantiate our actor
  // can't create actor instance using constructor new. So use actorOf method to create a new actor.

  val wordCounter = actorSystem.actorOf(Props[WordCountActor], "wordCounter")
  val anotherWordCounter = actorSystem.actorOf(Props[WordCountActor], "anotherWordCounter")

  //new WordCountActor  // Can't create using this

  // 4. communicate!
  wordCounter ! "Learning Akka! " // Exclaimation method is known as "tell"
  anotherWordCounter ! "A different message"
  // asynchronous

  // How to instantiate actors with constructors arguments


  //companion object
  object Person {
    def props(name: String) = Props(new Person(name))
  }

  class Person(name: String) extends Actor {
    override def receive: Receive = {
      case "hi" => println(s"Hi, my name is $name")
      case _ =>
    }
  }

  // created a person actor by passing it a props with actor instance inside of it.
  // often discouraged to use
  //val person = actorSystem.actorOf(Props(new Person("Gaurav")))

  // preferred way
  val person = actorSystem.actorOf(Person.props("Buwa"))

  person ! "hi"


// executor serv

}

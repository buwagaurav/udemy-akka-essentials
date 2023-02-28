package part3http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.util.Random

object RandomQuoteServer extends App {
  implicit val system = ActorSystem("RandomQuoteServer")
  implicit val materializer = ActorMaterializer()

  val quotes = Seq(
    "Everything is theoretically impossible until it is done.",
  "Everything is theoretically impossible until it is done.",
  "Everything is theoretically impossible until it is done."

  )

  /*val route =
    path("quote") {
      get {
        complete(Random.shuffle(quotes).head)
      }
    }*/

  val route =
    path("post") {
      post {
        entity(as[String]) { body =>
          complete(HttpResponse(StatusCodes.OK, entity = s"Received request body: $body"))
        }
      }
    }
  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

  println(s"Server online at http://localhost:8080/quote")
}

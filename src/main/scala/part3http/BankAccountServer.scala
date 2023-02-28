package part3http
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object BankAccountServer {
  var balance: Double = 1000

  implicit val system: ActorSystem = ActorSystem("bank-account-server")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val routes = {
    path("balance") {
      get {
        complete(balance.toString)
    } ~
    pathPrefix("withdraw") {
      post {
        parameter('amount.as[Double]) { amount =>
          if (balance >= amount) {
            balance -= amount
            complete(s"Withdrew $amount from account. New balance is $balance.")
          } else {
            complete(StatusCodes.BadRequest, s"Insufficient funds. Balance is $balance.")
          }
        }
      }
    } ~
    pathPrefix("deposit") {
      post {
        parameter('amount.as[Double]) { amount =>
          balance += amount
          complete(s"Deposited $amount to account. New balance is $balance.")
        }
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val bindingFuture = Http().bindAndHandle(routes, "localhost", 8080)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}


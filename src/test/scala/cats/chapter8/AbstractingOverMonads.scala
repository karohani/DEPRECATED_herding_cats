package cats.chapter8

import cats.Applicative
import org.scalatest.{FlatSpec, FunSpec, Matchers}
import cats.instances.future._
import cats.instances.list._
import cats.syntax.traverse._
import cats.syntax.functor._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AbstractingOverMonads extends FunSpec with Matchers {

  trait UptimeClient[F[_]] {
    def getUptime(hostName: String): F[Int]
  }

//  class UptimeService(client: UptimeClient) {
//    def getTotalUptime(hostNames: List[String]): Future[Int] =
//      hostNames.traverse(client.getUptime).map(_.sum) // ExecutionContext
//  }

  class UptimeService[F[_]](client: UptimeClient[F])
                           (implicit a: Applicative[F]) {

    def getTotalUptime(hostnames: List[String]): F[Int] =
      hostnames.traverse(client.getUptime).map(_.sum)
  }

  class TestUptimeClient(hosts: Map[String, Int]) extends UptimeClient[Future] {
    override def getUptime(hostname: String): Future[Int] = Future.successful(hosts.getOrElse(hostname, 0))
  }

}

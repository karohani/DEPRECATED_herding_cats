package cats.chapter8

import cats.Id

import scala.concurrent.Future
import cats.instances.future._
import cats.instances.list._
import cats.syntax.traverse._
import org.scalatest.{FlatSpec, Matchers}
import org.specs2.matcher.Matcher

import scala.concurrent.ExecutionContext.Implicits.global

class AbstractingOverTypeConstructor extends FlatSpec with Matchers {

  trait UptimeClient {
    def getUptime(hostname: String): Future[Int]
  }

  class UptimeService(client: UptimeClient) {
    def getTotalUptime(hostNames: List[String]): Future[Int] =
      hostNames.traverse(client.getUptime).map(_.sum) // ExecutionContext

  }

  class TestUptimeClient(hosts: Map[String, Int]) extends UptimeClient {
    override def getUptime(hostname: String): Future[Int] = Future.successful(hosts.getOrElse(hostname, 0))
  }

  "Example" should "OptionT" in {

    val hosts = Map("host1" -> 10, "host2" -> 2)
    val client = new TestUptimeClient(hosts)
    val service = new UptimeService(client)
    val actual = service.getTotalUptime(hosts.keys.toList)

    actual shouldBe hosts.values.sum
  }

  // Solution
  trait UptimeClient2[F[_]] {
    def getUptime(hostName: String): F[Int]
  }


  trait RealUptimeClient2 extends UptimeClient2[Future] {
    def getUptime(hostname: String): Future[Int]
  }

//  The final code is similar to our original implementation of TestUptimeClient, except we no longer need the call to Future.successful:
  // 최종적으로 획등하는 코드는 원래의 구현과 비슷하지만 FutureSucessful이 필요 없어진다.
  class TestUptimeClient2(hosts: Map[String, Int])
    extends UptimeClient2[Id] {
    def getUptime(hostname: String): Int =
      hosts.getOrElse(hostname, 0)
  }
}

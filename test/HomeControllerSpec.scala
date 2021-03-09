import java.util.concurrent.TimeUnit

import play.api.test.{PlaySpecification, WithServer, WsTestClient}

object HomeControllerSpec {
  val baseUrl = "http://0.0.0.0:8000/"
  val timeout = 10
}

class HomeControllerSpec extends PlaySpecification {

  import HomeControllerSpec._

  "Home Controller" should {
    "return 200 OK for root" in new WithServer {
      checkForOk(baseUrl)
    }

    "return 200 OK for up" in new WithServer {
      checkForOk(s"${baseUrl}up")
    }
  }

  private def checkForOk(url: String): Unit = {
    WsTestClient.withClient { ws =>
      val response =
        await(ws.url(url).get(), timeout, TimeUnit.SECONDS)
      response.status == 200
    }
  }
}

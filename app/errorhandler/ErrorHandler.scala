package errorhandler

import javax.inject.Singleton
import play.api.http.HttpErrorHandler
import play.api.mvc.Results._
import play.api.mvc.{RequestHeader, Result}

import scala.concurrent.Future

@Singleton
class ErrorHandler extends HttpErrorHandler {
  override def onClientError(request: RequestHeader,
                             statusCode: Int,
                             message: String): Future[Result] =
    Future.successful(
      Status(statusCode)(s"A client error occurred: $message")
    )

  override def onServerError(request: RequestHeader,
                             exception: Throwable): Future[Result] =
    Future.successful(
      InternalServerError(s"A server error occurred: ${exception.getMessage}")
    )
}

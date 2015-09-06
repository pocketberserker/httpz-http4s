package httpz
package http4sblaze

import scalaz.\/
import org.http4s._
import org.http4s.Method._
import scodec.bits.ByteVector
import scalaz.stream.Process

object Blaze {

  private[this] def urlWithParam(url: String, params: Map[String, String]) =
    if(params.isEmpty) url
    else {
      val query = params.toList
        .map{ case (key, value) => s"$key=$value" }
        .mkString("&")
      s"$url?$query"
    }

  def apply(req: httpz.Request): ParseFailure \/ org.http4s.Request = for {
    m <- Method.fromString(req.method)
    u <- Uri.fromString(urlWithParam(req.url, req.params))
  } yield org.http4s.Request(
    m,
    u,
    headers = Headers(req.headers.toList.map {
      case (k, v) => Header(k, v)
    } ++ (req.basicAuth match {
      case Some((key, value)) => List(Header(key, value))
      case None => Nil
    })),
    body = (m, req.body) match {
      case (POST | PUT | PATCH, Some(bytes)) => Process.emit(ByteVector.view(bytes))
      case _ => EmptyBody
    })
}

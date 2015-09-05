package httpz
package http4sblaze

import scalaz.\/
import org.http4s._
import org.http4s.Method._
import scodec.bits.ByteVector
import scalaz.stream.Process

object Blaze {

  def apply(req: httpz.Request): ParseFailure \/ org.http4s.Request = for {
    m <- Method.fromString(req.method)
    u <- Uri.fromString(req.url)
  } yield org.http4s.Request(
    m,
    u,
    headers = Headers(req.headers.toList.map {
      case (k, v) => Header(k, v)
    }),
    body = (m, req.body) match {
      case (POST | PUT | PATCH, Some(bytes)) => Process.emit(ByteVector.view(bytes))
      case _ => EmptyBody
    })
}

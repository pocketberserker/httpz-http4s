package httpz
package http4sblaze

import scalaz.Monoid
import scodec.bits.ByteVector
import org.http4s.Headers
import org.http4s.client.Client

final class BlazeInterpreter(factory: ClientFactory) extends InterpretersTemplate {

  val timeout = 30000

  private def using[A](client: Client, f: (org.http4s.Request, Client) => A)(req: org.http4s.Request): A = try {
    f(req, client)
  } finally {
    client.shutdown.run
  }

  private def execute(req: org.http4s.Request, client: Client) =
    client.toHttpService.run(req).runFor(timeout)

  private def headersToMap(headers: Headers): Map[String, List[String]] =
    headers.toList
      .groupBy(header => header.name.toString)
      .map { case (name, hs) => name -> hs.map(_.value) }

  private implicit val byteVectorMonoid: Monoid[ByteVector] =
    Monoid.instance(_ ++ _, ByteVector.empty)

  protected override def request2response(req: httpz.Request) =
    Blaze(req).map(using(factory.create(), execute)).fold(
      Function.const(Response(ByteArray.empty, 400, Map())),
      res => Response(new ByteArray(res.body.runFoldMap(identity).run.toArray), res.status.code, headersToMap(res.headers))
    )
}

package httpz
package http4sblaze

import org.http4s.client.Client
import org.http4s.client.blaze.SimpleHttp1Client

trait ClientFactory {
  def create(): Client
}

object ClientFactory {

  val default: ClientFactory = new ClientFactory {
    override def create() = SimpleHttp1Client()
  }
}

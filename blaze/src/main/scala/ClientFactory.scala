package httpz
package http4sblaze

import org.http4s.client.Client
import org.http4s.client.blaze.defaultClient

trait ClientFactory {
  def create(): Client
}

object ClientFactory {

  val default: ClientFactory = new ClientFactory {
    override def create() = defaultClient
  }
}

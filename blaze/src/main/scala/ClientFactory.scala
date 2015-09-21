package httpz
package http4sblaze

import org.http4s.client.blaze.{BlazeClient, defaultClient}

trait ClientFactory {
  def create(): BlazeClient
}

object ClientFactory {

  val default: ClientFactory = new ClientFactory {
    override def create() = defaultClient
  }
}

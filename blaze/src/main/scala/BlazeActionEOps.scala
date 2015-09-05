package httpz
package http4sblaze

import org.http4s.client.blaze.BlazeClient

final class BlazeActionEOps[E, A](
  val self: ActionE[E, A],
  val factory: () => BlazeClient) extends ActionOpsTemplate[E, A] {

  override def interpreter = new BlazeInterpreter(factory)
}

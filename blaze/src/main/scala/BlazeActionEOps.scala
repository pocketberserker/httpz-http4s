package httpz
package http4sblaze

final class BlazeActionEOps[E, A](
  val self: ActionE[E, A],
  val factory: ClientFactory) extends ActionOpsTemplate[E, A] {

  override def interpreter = new BlazeInterpreter(factory)
}

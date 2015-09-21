package httpz

package object http4sblaze {

  implicit def toBlazeActionEOps[E, A](a: ActionE[E, A])(implicit F: ClientFactory) =
    new BlazeActionEOps(a, F)
}


package httpz

import org.http4s.client.blaze.BlazeClient

package object http4sblaze {

  implicit def toBlazeActionEOps[E, A](a: ActionE[E, A])(implicit f: () => BlazeClient) =
    new BlazeActionEOps(a, f)
}


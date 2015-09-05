package httpz

object Test extends httpz.Tests(
  interpreter = new http4sblaze.BlazeInterpreter(() => org.http4s.client.blaze.defaultClient)
)

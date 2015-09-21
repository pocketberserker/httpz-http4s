package httpz

object Test extends httpz.Tests(
  interpreter = new http4sblaze.BlazeInterpreter(http4sblaze.ClientFactory.default)
)

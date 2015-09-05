// copy from https://github.com/xuwei-k/httpz
// license: MIT
// author: Kenji Yoshida <https://github.com/xuwei-k>
package httpz

/**
 * @see [[http://stackoverflow.com/questions/4371328/are-duplicate-http-response-headers-acceptable]]
 */
sealed abstract class HeaderType extends Product with Serializable
object HeaderType{
  case object Multi extends HeaderType
  case object CommaSeparated extends HeaderType
}

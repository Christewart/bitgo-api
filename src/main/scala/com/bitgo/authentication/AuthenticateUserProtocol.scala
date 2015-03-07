package com.bitgo.authentication

import spray.json._

/**
 * Created by chris on 3/6/15.
 */
case class AuthenticateUser(email : String, password : String, otp : String)
object AuthenticateUserProtocol extends DefaultJsonProtocol {
  implicit val authenticateUserFormat = jsonFormat3(AuthenticateUser.apply)
}

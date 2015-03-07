package com.bitgo.authentication

import spray.json._
/**
 * Created by chris on 3/6/15.
 */
case class AccessToken( access_token : String, expires_in : Long, token_type : String,
                        user : User )
object AccessTokenProtocol extends DefaultJsonProtocol {
  import UserProtocol._
  implicit val accessTokenFormat = jsonFormat4(AccessToken.apply)
}

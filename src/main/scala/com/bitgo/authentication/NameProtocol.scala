package com.bitgo.authentication

import spray.json._
/**
 * Created by chris on 3/6/15.
 */
case class Name(first : Option[String], full : String, last : Option[String])
object NameProtocol extends DefaultJsonProtocol {
  implicit val nameFormat = jsonFormat3(Name)
}

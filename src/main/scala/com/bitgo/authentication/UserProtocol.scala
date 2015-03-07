package com.bitgo.authentication

import spray.json.DefaultJsonProtocol
import spray.httpx.SprayJsonSupport._
/**
 * Created by chris on 3/6/15.
 */
case class User(id : String, name : Name, username : String, email : BitGoEmail,
                 phone : BitGoPhoneNumber, currency : BitGoCurrency,
                 timezone : String, isActive : Boolean)



case class BitGoEmail(email : String, verified : Boolean)
object BitGoEmailProtocol extends DefaultJsonProtocol {
  implicit val bitGoEmailFormat = jsonFormat2(BitGoEmail)
}

case class BitGoPhoneNumber(phone : String, verified : Boolean)
object BitGoPhoneNumberProtocol extends DefaultJsonProtocol {
  implicit val bitGoPhoneNumber = jsonFormat2(BitGoPhoneNumber)
}
case class BitGoCurrency(currency : String, bitcoinUnit : String)
object BitGoCurrencyProtocol extends DefaultJsonProtocol {
  implicit val bitGoCurrencyFormat = jsonFormat2(BitGoCurrency)
}
object UserProtocol extends DefaultJsonProtocol {


  import BitGoPhoneNumberProtocol._
  import BitGoEmailProtocol._
  import BitGoCurrencyProtocol._
  import NameProtocol._
  implicit val userFormat = jsonFormat8(User)
}
case class BitGoUser(user : User)
object BitGoUserProtocol extends DefaultJsonProtocol {

  import UserProtocol._
  implicit val bitGoUserFormat = jsonFormat(BitGoUser, "user")
}




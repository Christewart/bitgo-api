package com.bitgo.keychain

import spray.json.DefaultJsonProtocol

/**
 * Created by chris on 3/7/15.
 */
case class PublicKey(xpub : String, isBitGo : Option[Boolean])


object PublicKeyProtocol extends DefaultJsonProtocol {

  implicit val publicKeyFormat = jsonFormat2(PublicKey)
}

case class PublicKeychain(keychains : List[PublicKey])
object PublicKeychainProtocol extends DefaultJsonProtocol{
  import PublicKeyProtocol._
  implicit val publicKeychainFormat = jsonFormat(PublicKeychain, "keychains")
}

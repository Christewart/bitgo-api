package com.bitgo.keychain

import spray.json.DefaultJsonProtocol

/**
 * Created by chris on 3/7/15.
 */

case class KeyPair(xpub : String, encryptedXprv : Option[String], path : Option[String])
object KeyPairProtocol extends DefaultJsonProtocol {
  implicit val keyPairFormat = jsonFormat3(KeyPair)
}

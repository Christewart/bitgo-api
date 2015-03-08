package com.bitgo.keychain

import com.bitgo.environment.Environment
import spray.client.pipelining._
import spray.http.HttpRequest
import scala.concurrent.Future
import spray.httpx.SprayJsonSupport._
import spray.json._


/**
 * Created by chris on 3/7/15.
 */
trait Keychain { this : Environment =>

  import system._
  /**
   * Get the list of public keychains for the user
   * @return the public key chain for the given user
   */
  def publicKeychains : Future[PublicKeychain] = {
    import PublicKeychainProtocol._
  val uri = host + apiPath + version + keychain
    val pipeline: HttpRequest => Future[PublicKeychain] =
      addHeader("Authorization",token ) ~>
        sendReceive ~> unmarshal[PublicKeychain]
    pipeline(Get(uri))
  }

    /** Registers a new keychain for the user. You must supply at least the public key.
    An encrypted, private key may be uploaded, but it will be treated as opaque to the server.
    The purpose in providing an encrypted private key with the address is
    so users will be able to access their keys securely whenever they are connected to
    BitGo without the burden of storing it. It is highly recommended that you encrypt any
    private keys stored at the server with a strong password from the user.
    Encryption must be performed on the client.*/
  def addKeychain(keyPair : KeyPair) = {
    import KeyPairProtocol._
    val uri = host + apiPath + version + keychain
    val pipeline: HttpRequest => Future[KeyPair] =
      addHeader("Authorization",token ) ~>
        sendReceive ~> unmarshal[KeyPair]
    pipeline(Post(uri,keyPair) )
  }

  /**
   * Creates a new keychain on BitGo’s servers and returns the public keychain to the caller.
   */
  def createBitGoKeychain : Future[PublicKey] = {

    import PublicKeyProtocol._
    val uri = host + apiPath + version + keychainPath + "bitgo"
    val pipeline: HttpRequest => Future[PublicKey] =
      addHeader("Authorization",token ) ~>
        sendReceive ~> unmarshal[PublicKey]
    pipeline(Post(uri))
  }


  def keychain(publicKey: PublicKey) : Future[KeyPair] = {
    import KeyPairProtocol._
    val uri = host + apiPath + version + keychainPath + publicKey.xpub
    val pipeline: HttpRequest => Future[KeyPair] =
      addHeader("Authorization",token ) ~>
        sendReceive ~> unmarshal[KeyPair]
    pipeline(Post(uri))
  }

  /**
   * Update a keychain. This is used if you wish to store a new version
   * of the xprv (for example, if you changed the password used to encrypt the xprv).
   *
   * This operation requires the session to be unlocked using the Unlock API.
   *
   * WARNING !@!@!@!
   * If you change the encryptedXprv, the existing value is overwritten.
   * If the new value is incorrect, or you forget the password to the new value,
   * your ability to sign with this keychain will be lost forever.
   * !@!@!@
   */

  def updateKeychainXpriv(keyPair: KeyPair) : Future[KeyPair] = {
    require(keyPair.encryptedXprv.isDefined)
    import KeyPairProtocol._
    val uri = host + apiPath + version + keychainPath + keyPair.xpub
    val encryptedXprv = keyPair.encryptedXprv.get
    val msg = s"""
         { "encryptedXprv" : "$encryptedXprv" }""".parseJson.asJsObject
    val pipeline: HttpRequest => Future[KeyPair] =
      addHeader("Authorization",token ) ~>
        sendReceive ~> unmarshal[KeyPair]
    pipeline(Put(uri,msg
      ))
  }
}

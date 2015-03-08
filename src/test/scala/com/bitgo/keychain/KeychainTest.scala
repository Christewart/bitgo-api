package com.bitgo.keychain

import com.bitgo.environment.Test
import org.scalatest.{FlatSpec, MustMatchers}
import org.scalatest.concurrent.ScalaFutures
import scala.language.postfixOps
import scala.concurrent.duration.DurationInt
/**
 * Created by chris on 3/7/15.
 */
class KeychainTest extends FlatSpec with MustMatchers with ScalaFutures
  with Keychain with Test {

  "Keychain" must "get a public key for a user" in {

    val publicKeys = publicKeychains
    whenReady(publicKeys, timeout(2 seconds), interval(5 millis)) { keys =>
      keys.keychains.head.xpub must be
        ("xpub661MyMwAqRbcEhqWYmBqvfv5AL18MZXKZKVieJVJNTgGTbyryJVnhFcNnX8WbpGgf2orc12BJFr34rb7riEqDXXyAxTnCN6rdmZ9bMAw7mG")
    }
  }

  it must "adds a new keychain for a BitGo user" in {

    val keyPair = KeyPair("xpub661MyMwAqRbcEhqWYmBqvfv5AL18MZXKZKVieJVJNTgGTbyryJVnhFcNnX8WbpGgf2orc12BJFr34rb7riEqDXXyAxTnCN6rdmZ9bMAw7mG", None,None)
    val result = addKeychain(keyPair)
    whenReady(result, timeout(2 seconds), interval(5 millis)) { kp =>
      kp.xpub must be (keyPair.xpub)
    }
  }

  it must "create a bitgo keychain" in {

    val bitgoPubKeychain = createBitGoKeychain
    whenReady(bitgoPubKeychain, timeout(2 seconds), interval(5 millis)){ keychain =>
      keychain.isBitGo must be (Some(true))
    }
  }

  it must "get the keychain model corresponding to a public key" in {
    val pubKey = PublicKey("xpub661MyMwAqRbcEhqWYmBqvfv5AL18MZXKZKVieJVJNTgGTbyryJVnhFcNnX8WbpGgf2orc12BJFr34rb7riEqDXXyAxTnCN6rdmZ9bMAw7mG", Some(true))
    val keyPair = keychain(pubKey)
    whenReady(keyPair, timeout(2 seconds), interval(5 millis)) { kp =>
      kp.xpub must be(pubKey.xpub)
    }
  }

  it must "update the encryptedXpriv key for a public key" in {
    val keyPair = KeyPair("xpub661MyMwAqRbcEhqWYmBqvfv5AL18MZXKZKVieJVJNTgGTbyryJVnhFcNnX8WbpGgf2orc12BJFr34rb7riEqDXXyAxTnCN6rdmZ9bMAw7mG",
      Some("NEW_PRIVATE_KEY"),None)
    val updatedKeyPair = updateKeychainXpriv(keyPair)
    whenReady(updatedKeyPair, timeout(2 seconds), interval(5 millis)) { kp =>
      kp.encryptedXprv must be (keyPair.encryptedXprv)
    }
  }

}



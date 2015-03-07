package com.bitgo.authentication

import org.scalatest.FlatSpec
import org.scalatest.MustMatchers
import com.bitgo.environment.Test
import org.scalatest.concurrent.ScalaFutures
import scala.language.postfixOps
import scala.concurrent._
import duration.DurationInt

/**
 * Created by chris on 3/6/15.
 */
class AuthenticationTest extends FlatSpec with MustMatchers with ScalaFutures
  with Authentication with Test with SuredBitsAuthy {


  "Authentication" must "log a user into bitgo" in {
    val authUser = AuthenticateUser("chris@suredbits.com", "ZkHpoo4&33s", apiKey)
    val accessToken = login(authUser)
    whenReady(accessToken, timeout(5 seconds), interval(5 millis)) { t =>
      t.user.username must be ("chris@suredbits.com")
    }
  }


}

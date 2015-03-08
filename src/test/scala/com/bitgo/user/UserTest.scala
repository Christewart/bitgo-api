package com.bitgo.user

import com.bitgo.environment.Test
import org.scalatest.FlatSpec
import org.scalatest.MustMatchers
import org.scalatest.concurrent.ScalaFutures
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps
import com.bitgo.authentication.{BitGoEmail}

/**
 * Created by chris on 3/7/15.
 */
class UserApiTest extends FlatSpec with MustMatchers with ScalaFutures with UserApi with Test  {



  "UserApi" must "be able to get the current logged in user" in {
    val myself = me
    whenReady(myself, timeout(2 seconds), interval(5 millis)) { u =>
      u.user.username must be ("chris@suredbits.com")
      u.user.email must be (BitGoEmail("chris@suredbits.com",false))
    }
  }

}

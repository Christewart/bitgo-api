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
  with Authentication with Test {
}

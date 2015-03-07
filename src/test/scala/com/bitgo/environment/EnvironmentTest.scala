package com.bitgo.environment

import org.scalatest.{FlatSpec, MustMatchers}

/**
 * Created by chris on 3/6/15.
 */
class EnvironmentTest extends FlatSpec with MustMatchers with Production with Test  {
  val production = new Production {}
  val test = new Test {}

  "Test" must "have the host" in {
    test.host must be ("https://test.bitgo.com/")
  }
  "Production" must "have the host" in {
    production.host must be ("https://bitgo.com/")
  }


}

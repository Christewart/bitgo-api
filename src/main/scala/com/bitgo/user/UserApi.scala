package com.bitgo.user

import com.bitgo.environment.Environment
import spray.client.pipelining._
import spray.http.HttpRequest
import scala.concurrent.Future
import com.bitgo.authentication.{BitGoUser, BitGoUserProtocol}
import spray.httpx.SprayJsonSupport._

/**
 * Created by chris on 3/7/15.
 */
trait UserApi { this : Environment =>

  import system._

  /**
   * Get information about the current authenticated com.bitgo.user.
   * @return BitGoUser
   */
  def me : Future[BitGoUser]= {
    import BitGoUserProtocol._
    val uri = host + apiPath + version + userPath + "me"
    println(uri)
    val pipeline: HttpRequest => Future[BitGoUser] =
      addHeader("Authorization",token ) ~>
        sendReceive ~> unmarshal[BitGoUser]
    pipeline(Get(uri))
  }
}

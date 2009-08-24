package com.nileshk.gae

import javax.servlet.http.{HttpServlet,
                           HttpServletRequest => HSReq, HttpServletResponse => HSResp}
import scala.collection.mutable.{Map => MMap}

object Mapping {
  type Action = HSReq => String
}
import Mapping._

/**
 * This servlet is an experiment in how URLs can be mapped in Scala 
 */
class MappingServlet extends HttpServlet {

  val mappings = MMap[String, Mapping]()

  class Mapping(val action: Action) {
    def execute(request : HSReq): String = {
      action(request)
    }
  }

  override def doGet(req : HSReq, resp : HSResp) = doPost(req, resp)

  override def doPost(req : HSReq, resp : HSResp) = {
    val pathInfo = req.getPathInfo()
    val m = if (mappings contains pathInfo) mappings(pathInfo) execute req
            else "failure"
    resp.getWriter().print(m)
  }

  def map(url : String)(action: HSReq => String) : Unit  = {
    mappings += (url -> new Mapping(action))
  }

  map("/test") {
    request => {
      "a real test"
    }
  }

  map("/another") (request => {
    "another"
  })


}


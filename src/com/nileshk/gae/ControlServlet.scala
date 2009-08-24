package com.nileshk.gae

import javax.servlet.http.{HttpServlet,
                           HttpServletRequest => HSReq, HttpServletResponse => HSResp}
import scala.collection.mutable.{Map => MMap}
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

object Control {
  type Action = HSReq => String
}
import Control._

class ControlServlet extends HttpServlet {

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
  
  map("/cards") (request => {
    val pm = PMF.get().getPersistenceManager()
    val fc = new FlashCard("test", "blah")
    val query = pm.newQuery(classOf[FlashCard])
    val results = query.execute().asInstanceOf[java.util.List[FlashCard]]
    val resultsArray = results.toArray
    val cards : List[FlashCard] = List.fromArray(resultsArray).asInstanceOf[List[FlashCard]]
    if (!cards.isEmpty) {
      val output = for (card <- cards) yield card.getQuestion() + ":" + card.getAnswer()
      output.mkString("\n")
    } else {
      "not implemented yet"
    }
  })

}


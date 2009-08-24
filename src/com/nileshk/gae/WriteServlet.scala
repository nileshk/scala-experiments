package com.nileshk.gae

import javax.servlet.http.{HttpServlet,
                           HttpServletRequest => HSReq, HttpServletResponse => HSResp}
import scala.collection.mutable.{Map => MMap}
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

class WriteServlet extends HttpServlet {

  override def doGet(req : HSReq, resp : HSResp) = doPost(req, resp)

  override def doPost(req : HSReq, resp : HSResp) = {
    val question = req.getParameter("q").asInstanceOf[String]
    val answer = req.getParameter("a").asInstanceOf[String]
    resp.setContentType("text/plain");
    def println(str: String) = resp.getWriter().println(str)
    val pm = PMF.get().getPersistenceManager();
    
	if (question != null && answer != null && question.length() > 0 
			&& answer.length() > 0) {
		val card = new FlashCard(question, answer)
		try {
		  pm.makePersistent(card)
		  println("Saved: " + " question: " + card.question
				+ " answer: " + card.answer)
		} finally {
		  pm.close();
		}
			} else {
			  println("Empty answer or question")
			}
    
    println("Finished")
  }
  
  
}

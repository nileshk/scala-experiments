class DispatchServlet extends BaseServlet {

  override def message =
    if (validate(param))
    <HTML>
      <HEAD><TITLE>Hello, Scala!</TITLE></HEAD>
      <BODY>
        <p>Context Path: { param("contextPath") }</p>
        <p>Servlet Path: { param("servletPath") }</p>
        <p>Path Info: { param("pathInfo") }</p>
        <p>Path Translated: { param("pathTranslated") }</p>
        <p>Query String: { param("queryString") }</p>
        <p>Your name: { param("firstName") } { param("lastName") }</p>
        <p>It's now { currentDate }</p>
      </BODY>
    </HTML>
    else
      <html>
        <head><title>Error!</title></head>
        <body>Please enter a name</body>
      </html>
  
  def currentDate = java.util.Calendar.getInstance().getTime()

  def validate(p : Map[String, String]) : Boolean = {
    p foreach {
      case ("firstName", "") => return false
      case ("lastName", "") => return false
	  //case ("lastName", v) => if (v.contains("e")) return false
	  case (_, _) => ()
    }
    true
  }


/*
  override def doGet(req : HSReq, resp : HSResp) =
    doPost(req, resp)

  override def doPost(req : HSReq, resp : HSResp) = {
    val contextPath = req.getContextPath()
    val firstName = req.getParameter("firstName")
    val lastName = req.getParameter("lastName")

    resp.getWriter().print(message(contextPath, firstName, lastName))
  }
*/
}

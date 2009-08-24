import javax.servlet.http.{HttpServlet,
  HttpServletRequest => HSReq, HttpServletResponse => HSResp}
import scala.collection.mutable.{Map => MMap}

abstract class BaseServlet extends HttpServlet {
  
  def message : scala.xml.Node;
  
  protected var param : Map[String, String] = Map.empty
  
  override def doGet(req : HSReq, resp : HSResp) =
    doPost(req, resp)

  override def doPost(req : HSReq, resp : HSResp) = {
    // Extract parameters
    //
    val m = MMap[String, String]()
    val e = req.getParameterNames()
    while (e.hasMoreElements())
    {
      val name = e.nextElement().asInstanceOf[String]
      m += (name -> req.getParameter(name))
    }
    m += ("contextPath" -> req.getContextPath())
    m += ("servletPath" -> req.getServletPath())
    m += ("pathInfo" -> req.getPathInfo())
    m += ("pathTranslated" -> req.getPathTranslated())
    m += ("queryString" -> req.getQueryString())
    param = Map.empty ++ m

    resp.getWriter().print(message)
  }
}

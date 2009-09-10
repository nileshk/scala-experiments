import org.scalatest._
import org.scalatest.junit._
import org.scalatest.prop.Checkers
import com.nileshk.jdbc._
import java.sql.{Connection, DriverManager}

class DataAccessTest extends JUnit3Suite with Checkers {

  def getConnection = {
    Class.forName("org.postgresql.Driver")
    DriverManager.getConnection(
      "jdbc:postgresql://localhost/todo",
      "nil", "nil");
  }
  
  def testSelect() {
    val connection = getConnection
    val select = new DataAccess(connection).select
    val sql: String = select("id, title") from("todo_lists") where("position > 0") toString()
    println(sql)
    assert(sql === "select id, title from todo_lists where position > 0")
    connection.close()
  }

  def testSelectFromOrderBy() {
    val connection = getConnection
    val select = new DataAccess(connection).select
    val sql: String =
      select("id, title") from("todo_lists") orderBy("title") toString()
    println(sql)
    assert(sql === "select id, title from todo_lists order by title")
    connection.close()
  }

  def testSelectFromWhereOrderBy() {
    val connection = getConnection
    val select = new DataAccess(connection).select
    val sql: String =
      select("id, title") from("todo_lists") where("position > 0") orderBy("title") toString()
    println(sql)
    assert(sql === "select id, title from todo_lists where position > 0 order by title")
    connection.close()
  }

}

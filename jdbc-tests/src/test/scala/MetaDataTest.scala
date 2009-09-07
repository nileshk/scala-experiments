import org.scalatest._
import org.scalatest.junit._
import org.scalatest.prop.Checkers
import com.nileshk.jdbc._
import java.sql._

class MetaDataTest extends JUnit3Suite with Checkers {
  def testItPlease() {
    assert(true === true)
  }

  def testDbMetaData() {
    Class.forName("org.postgresql.Driver")
    val connection = DriverManager.getConnection(
      "jdbc:postgresql://localhost/todo",
      "nil", "nil");

    val m = new DbMetaData(connection)
    val results = m.tableNames("public")
    println(results.mkString("\n"))
    assert(!results.isEmpty)

    for(result <- results) {
      val tableDataList = m.tableData("public", result)
      assert(tableDataList.length === 1)
      for(tableData <- tableDataList) {
        println(tableData.toString())
      }
    }
    connection.close()
  }
  
}

package com.nileshk.jdbc

import java.sql.{Connection, DatabaseMetaData, ResultSet}

class DbMetaData(connection: Connection) {

  private val metaData: DatabaseMetaData = connection.getMetaData()
  private val tableTypes = Array("TABLE")
  
  def tableNames(schema: String): List[String] = {
    val rs = metaData.getTables(null, schema, null, tableTypes)
    val tables =
      processResultSet(rs,
        resultSet => resultSet.getString("TABLE_NAME"))
    rs.close()
    return tables
  }
  
  def tableData(schema: String, table: String): List[TableData] = {
    val rs = metaData.getTables(null, schema, table, tableTypes)
    val tables =
      processResultSet(rs,
        resultSet => {
          new TableData(schema,
                        resultSet.getString("TABLE_NAME"),
                        resultSet.getString("TABLE_TYPE"),
                        resultSet.getString("REMARKS"),
                        columnData(schema, table))})
    rs.close()
    return tables
      
  }

  def columnData(schema: String, table: String): List[ColumnData] = {
    val rs = metaData.getColumns(null, schema, table, null)
    val columns =
      processResultSet(rs,
        resultSet => {
          new ColumnData(resultSet.getString("COLUMN_NAME"))})
    rs.close()
    return columns
  }

  private def processResultSet[T](rs: ResultSet, mapFunc: ResultSet => T): List[T] = {
    var results = List[T]()
    while(rs.next()) {
      results = results ::: List(mapFunc(rs))
    }
    return results
  }
  
}

class TableData(
  schema: String,
  name: String,
  tableType: String,
  remarks: String,
  columns: List[ColumnData]) {

  override def toString() = 
    schema + ":" + name + ":" + tableType + ":" + remarks + "\n" +
      columns.mkString("   (", ", ", ")")
}

class ColumnData(name: String) {
  override def toString() =
    name
}

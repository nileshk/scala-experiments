package com.nileshk.jdbc

import java.sql.{Connection, ResultSet}

object DataAccess {
  def doSelect(connection: Connection, sql: String): String = sql // TODO
}
  
class DataAccess(connection: Connection) {

  def select: String => Select = {
    x => {
      new Select(connection, x)
    }
  }
  
}

class Select(connection: Connection, selectString: String) {
  override def toString = "select " + selectString
  
  def from(fromString: String): SelectFrom = {
    new SelectFrom(connection, this, fromString)
  }

  def * = new SelectFrom(connection, this, "*")
}

class SelectFrom(connection: Connection, select: Select, fromString: String) {

  override def toString: String = select + " from " + fromString
  
  def where(whereString: String): SelectFromWhere = {
    new SelectFromWhere(connection, this, whereString)
  }

//  def orderBy(orderByString: String): SelectFromOrderBy = {
//  }
}

class SelectFromWhere(
  connection: Connection, 
  selectFrom: SelectFrom, 
  whereString: String) {

  override def toString: String = selectFrom + " where " + whereString
}
/*
class SelectFromOrderBy(
  connection: Connection, 
  selectFrom: SelectFrom, 
  orderByString: String) {

  val sql = selectFrom.sql + " order by " + orderByString
  
  def exec = DataAccess.doSelect(connection, sql)
}
*/

package com.nileshk.gae

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

object PMF {
  val pmfInstance = JDOHelper.getPersistenceManagerFactory("transactions-optional")
  
  def get() = pmfInstance 
}

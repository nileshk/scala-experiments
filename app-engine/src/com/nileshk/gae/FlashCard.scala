package com.nileshk.gae

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import scala.reflect.BeanProperty;

@PersistenceCapable{val identityType = IdentityType.APPLICATION}
class FlashCard(
	@Persistent
	@BeanProperty
	var question: String,

	@Persistent
	@BeanProperty
	var answer: String
) {
 	@PrimaryKey
	@Persistent{val valueStrategy = IdGeneratorStrategy.IDENTITY}
	@BeanProperty
	var id: java.lang.Long = null
}

Scala Experiments
=================

These are my experiments with Scala to explore what is possible with
the language.

Each folder at the root level is a project:

`servlet-project`
-----------------

Trying out servlets with Scala.  `ControlServlet` is an attempt to
create something similar to [Step](http://github.com/alandipert/step/).
Built with Maven.
    
`app-engine`
------------

Trying out Scala on Google App Engine.  This includes some simple JDO
data access.  I reused the `ControlServlet` from `servlet-project`
(renamed it to `MappingServlet`).  Built with Eclipse using the Scala
and Google App Engine plugins (though I'd like to get this working
with a command line build tool).

`jdbc-tests`
------------

Trying out JDBC from Scala.  So far just working with database
metadata.

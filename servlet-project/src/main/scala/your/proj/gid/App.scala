package your.proj.gid;

/**
 * Hello world!
 *
 */
object App extends Application {

  println( "Starting up..." );
  start

  def start {
    val items = List("Item1", "Item2", "Item3")
    
    val anxml = <table>
    { items map ( i => <tr>{ i }</tr> ) }
    </table>

    println(anxml)
  }
}

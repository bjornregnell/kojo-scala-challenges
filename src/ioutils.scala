package scaboo

object ioutils {

  def fileSep = java.lang.System.getProperty("file.separator")

  implicit class StringSaver(s: String) {
    def save(fileName:String): Unit = {
      val outStream = new java.io.PrintStream(fileName,"UTF-8")
      try { outStream.println(s) } finally { outStream.close }
      println(s"\nSaved ${s.size} characters to file: $fileName") 
    }
  }
  
  def load(fileName:String): Vector[String] = 
    scala.io.Source.fromFile(fileName).getLines.toVector
  
  def mkdir(d: String): Boolean = new java.io.File(d).mkdirs   
  
  def exists(fileName: String): Boolean = ( new java.io.File(fileName) ).exists
  
}
object ioxtra {
  def fileSep = java.lang.System.getProperty("file.separator")
  implicit class StringIOExtra(s: String) {
    def save(fileName:String): Unit = {
      val outStream = new java.io.PrintStream(fileName,"UTF-8")
      try { outStream.println(s) } finally { outStream.close }
      println(s"\nSaved ${s.size} characters to file: $fileName") 
    }
    def slashify: String = s.replaceAllLiterally(fileSep, "/")
    def suffix(suf: String): String = if (!s.endsWith(suf)) s + suf else s
    def stripAnySuffix: String = 
      if (s.contains('.')) s.split('.').dropRight(1).mkString(".") else s
    def stripFileType: String = {
      val ss = s.slashify.split('/')
      val head = ss.dropRight(1)
      val tail = ss.lastOption.map(_.stripAnySuffix).getOrElse("")
      (head ++ Seq(tail)).mkString("/") 
    }
    def newFileType(suf: String): String = s.stripFileType.suffix(suf)
  }
    
  
  
  def load(fileName:String): Vector[String] = 
    scala.io.Source.fromFile(fileName).getLines.toVector
  
  def mkdir(d: String): Boolean = new java.io.File(d).mkdirs    
  def exists(fileName: String): Boolean = ( new java.io.File(fileName) ).exists
}
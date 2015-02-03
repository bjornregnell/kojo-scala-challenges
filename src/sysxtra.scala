import scala.sys.process._
import ioxtra._

object sysxtra {
  def isWindows = sys.props("os.name").startsWith("Windows")
  def fixCmd(cmd: Seq[String]): Seq[String] = 
    if (isWindows) Seq("cmd","/C",s"""${cmd.mkString(" ")}""") else cmd
  def runCmd(cmd: Seq[String]): Int = fixCmd(cmd).! 
  def desktopOpen(f: String) = java.awt.Desktop.getDesktop().open( new java.io.File(f))
  def isDotInstalled: Boolean = runCmd(Seq("dot","-V")) == 0
  def dotCmd(fileName: String, format: String = "pdf", layout: String = "dot", moreArgs: Seq[String] = Seq()): Seq[String] = {
    val q = if (isWindows) '"'.toString else "" 
    Seq("dot",s"-T$format",s"-K$layout") ++ moreArgs ++
      Seq("-o", s"""$q${fileName.newFileType("."+format)}$q""",
        s"""$q${fileName.newFileType(".dot")}$q""")
  }
}
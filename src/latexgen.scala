import scaboo._
import ioxtra._

object latexgen {
  
  def head(ch: ChapterLike) = s"\\chapter{${ch.head}}"
  
  def multicols(col1:String,col2:String) = 
    "\n\\begin{multicols}{2}\n" +
    col1 + "\n\n\\columnbreak\n\n" + col2 +
    "\n\\end{multicols}\n"
  
  def body(ch: ChapterLike): String = ch.contents.map(_.toLatex).mkString("\n") 
  def img(file: String): String = "\\includegraphics[width=14cm]{../img/fram.png}"  
  
  def toLatex(ch: ChapterLike): String = ch.template match {
    case DefaultChapterTemplate => head(ch) + body(ch)
    case TextWithImage(file) => head(ch) + multicols(body(ch),img(file))
    case ct => s"WARNING: Not yet implemented ChapterTemplate $ct"    
  }
  
  def make(chapters: Seq[ChapterLike], outFile: String): Unit = 
    chapters.map(toLatex).mkString("\n").save(outFile)
}
package scaboo

object latexgen { //generate latex from ChapterLike structures
  import model._
  import ioutils._

  implicit class DocItemLatexGen(d: DocItem) {
    def toLatex: String = d match {
      case p: Para => p.ps.mkString(s"\\\\\n")
      case Section(heading, color) => s"\\section*{\\color{$color}$heading}"
      case Code(code, size) => 
        val param = if (size>0) s"[basicstyle={\\ttfamily\\fontsize{$size}{$size}\\selectfont}]" else ""
        s"""
        |\\begin{lstlisting}$param
        |$code
        |\\end{lstlisting}
        """.stripMargin
      case HRef(url, text) => s"\\href{$url}{$text}"
      case NoItem => ""
    }
  }
  
  def head(ch: ChapterLike) = s"\\chapter{${ch.head}}"
  
  def multicols(col1:String,col2:String) = 
    "\n\\begin{multicols}{2}\n" +
    col1 + "\n\n\\columnbreak\n\n" + col2 +
    "\n\\end{multicols}\n"
  
  def body(ch: ChapterLike): String = ch.contents.map(_.toLatex).mkString("\n") 
  def img(file: String): String = s"\\includegraphics[width=14cm]{../img/$file}"  
  
  def toLatex(ch: ChapterLike): String = ch.template match {
    case DefaultChapterTemplate => head(ch) + body(ch)
    case TextWithImage(file) => head(ch) + multicols(body(ch),img(file))
    case ct => s"WARNING: Not yet implemented ChapterTemplate $ct"    
  }
  
  def make(chapters: Seq[ChapterLike], outFile: String): Unit = 
    chapters.map(toLatex).mkString("\n").save(outFile)
}
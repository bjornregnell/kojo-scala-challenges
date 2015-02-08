package scaboo
import model._
import ioutils._

object latexgen { //generate latex from Chapter structures
  
  object replace {
    val replacers = Seq(
      ("{*", "{\\bf "), ("*}","}"),   // {*bold*} => {\bf bold}
      ("{/", "{\\it "), ("/}","}"),   // {/ital/} => {\it ital}
      ("{:", "\\lstinline{"), (":}","}"),   // {:val x = 42:} => \lstinline{val x = 42}
      ("{!", "{\\bf\\color{blue}"), ("!}","}")   // {!hej!} => {\bf\color{blue}hej}
    )
    def apply(s: String): String = {
      var result = s
      replacers.collect{ case (from,to) => result = result.replaceAllLiterally(from,to) } 
      result
    }
  }

  val lineBreak = "\\\\\n"
  val colBreak = "\n\n\\columnbreak\n\n"
  
  implicit class DocItemLatexGen(d: DocItem) {
    def toLatex: String = d match {
      case p: Para => p.ps.map(replace(_)).mkString(lineBreak)
      case i: Itemize => items(i.ps.map(replace(_)))
      case Section(heading, color) => s"\\section*{\\color{$color}${replace(heading)}}"
      case Code(code, size) => 
        val param = if (size>0) s"[basicstyle={\\ttfamily\\fontsize{$size}{$size}\\selectfont}]" else ""
        s"""
        |\\begin{lstlisting}$param
        |$code
        |\\end{lstlisting}
        """.stripMargin
      case HRef(url, text) => s"\\href{$url}{${replace(text)}}"
      case Image(file, width) => img(file, width)
      case CenterImage(file, width) => center(img(file, width))
      case VSkip(size) => s"\\vskip ${size}em"
      case LineBreak => lineBreak
      case ColumnBreak => colBreak
      case NoItem => ""
    }
  }
  
  def head(ch: Chapter) = s"\\chapter{${ch.head}}"
  
  def twocols(col1:String,col2:String) = 
    "\n\\begin{multicols}{2}\n" +
    col1 + colBreak + col2 +
    "\n\\end{multicols}\n"

  def multicols(numCols: Int, body: String) = 
    s"\n\\begin{multicols}{$numCols}\n" +
    body +
    "\n\\end{multicols}\n"

  def items(ps:Seq[String]) = 
    "\n\n\\begin{itemize}\n\n" +
    ps.map(p => s"\\item {$p}").mkString("\n") +
    "\n\n\\end{itemize}\n\n"
    
  def body(ch: Chapter): String = ch.contents.map(_.toLatex).mkString("\n") 
  
  def center(text: String): String = s"\\begin{center}\n$text\n\\end{center}\n"  

  def img(file: String, width: Double): String = {
    val sizeParam = if (width>0) s"[width=${width}cm]" else ""
    s"\\includegraphics$sizeParam{../img/$file}"  
  }  
  
  def toLatex(ch: Chapter): String = ch.template match {
    case DefaultChapterTemplate => head(ch) + body(ch)
    case TextWithImage(file, width) => head(ch) + twocols(body(ch),center(img(file, width)))
    case MultiColumn(n) => head(ch) + multicols(n, body(ch))
    case ct => s"WARNING: Not yet implemented ChapterTemplate $ct"    
  }
  
  def make(chapters: Seq[Chapter], outFile: String): Unit = 
    chapters.map(toLatex).mkString("\n").save(outFile)
}
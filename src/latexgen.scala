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

  implicit class DocItemLatexGen(d: DocItem) {
    def toLatex: String = d match {
      case p: Para => p.ps.map(replace(_)).mkString(lineBreak)
      case i: Itemize => items(i.ps.map(replace(_)))
      case Section(heading, color) => s"\\section*{\\color{$color}${replace(heading)}}"
      case Code(code, size, isFramed, isNumbered) => 
        val numbersParam = if (isNumbered) "numbers=left" else "numbers=none"
        val frameParam = if (isFramed) "backgroundcolor=\\color{gray!15}" else ""
        val sizeParam = if (size>0) s"basicstyle={\\ttfamily${fontSize(size)}\\selectfont}" else ""
        val param = Seq(frameParam,sizeParam, numbersParam).filterNot(_.isEmpty).mkString("[",",","]")
        s"""
        |\\begin{lstlisting}$param
        |$code
        |\\end{lstlisting}
        """.stripMargin
      case HRef(url, text) => s"\\href{$url}{${replace(text)}}"
      case Image(file, width) => img(file, width)
      case CenterImage(file, width) => center(img(file, width))
      case OverlayImage(file, x, y, width) => overlay(img(file, width), x, y)
      case VSkip(size) => s"\\vskip ${size}em"
      case LineBreak => lineBreak
      case ColumnBreak => colBreak
      case NoItem => ""
    }
  }
  
  def lineBreak = "\\\\\n"

  def colBreak = "\n\n\\columnbreak\n\n"

  def fontSize(size: Double) = s"\\fontsize{${size.toInt}}{${((size+0.5)*1.2).toInt}}"

  def head(ch: Chapter) = s"\\chapter{${replace(ch.head)}}"
  
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
  
  def overlay(text: String, x: Double, y: Double) = s"""
  |\\begin{tikzpicture}[overlay]
  |\\node at (${x}cm,${y}cm) {$text};
  |\\end{tikzpicture}
  """.stripMargin
  
  def toLatex(ch: Chapter): String = ch.template match {
    case DefaultChapterTemplate => head(ch) + body(ch)
    case TextWithImage(file, width) => head(ch) + twocols(body(ch),center(img(file, width)))
    case MultiColumn(n) => head(ch) + multicols(n, body(ch))
  }
  
  def make(chapters: Seq[Chapter], outFile: String): Unit = 
    chapters.map(toLatex).mkString("\n").save(outFile)
}
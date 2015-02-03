object scaboo {

  trait TheBook { 
    def title: String 
  }

  trait ChapterTemplate
  case class TextWithImage(imageFileName: String) extends ChapterTemplate
  case object DefaultChapterTemplate extends ChapterTemplate

  trait DocItem { def toLatex: String }
  case class Para(ps: String*) extends DocItem {
    def toLatex = ps.mkString(s"\\\\\n")
  }
  case class Section(heading: String, color: String = "MidnightBlue") extends DocItem {
    def toLatex = s"\\section*{\\color{$color}$heading}"
  }
  case class Code(code: String, size: Int=14) extends DocItem {
    def toLatex = s"""
\\begin{lstlisting}[basicstyle={\\ttfamily\\fontsize{$size}{$size}\\selectfont}]
$code
\\end{lstlisting}
    """
  }

  trait ChapterLike {
    def id: String 
    def head: String
    def contents: Seq[DocItem]
    def template: ChapterTemplate
  }  
  
  case class Chapter(
    id: String, 
    head: String, 
    contents: Seq[DocItem],
    template: ChapterTemplate = DefaultChapterTemplate
  ) extends ChapterLike 
  
  case class Challenge(
    id: String, 
    head: String, 
    goals: Seq[DocItem] = Seq(),
    task: Seq[DocItem] = Seq(),
    template: ChapterTemplate = DefaultChapterTemplate
  ) extends ChapterLike {
    val goalHeading = if (!goals.isEmpty) Seq(Section("Du lär dig:")) else Seq()
    val taskHeading = if (!task.isEmpty) Seq(Section("Uppdrag:")) else Seq()
    override def contents = goalHeading ++ goals ++ taskHeading ++ task 
  }

}
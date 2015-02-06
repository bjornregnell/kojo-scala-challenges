package scaboo

object model {

  trait ChapterTemplate
  case class TextWithImage(imageFileName: String) extends ChapterTemplate
  case object DefaultChapterTemplate extends ChapterTemplate

  sealed trait DocItem 
  case class Para(ps: String*) extends DocItem 
  case class Section(heading: String, color: String = "MidnightBlue") extends DocItem
  //more latex xcolor names here: http://en.wikibooks.org/wiki/LaTeX/Colors  
  case class Code(code: String, size: Int = -1) extends DocItem 
  case class HRef(url:String, text: String) extends DocItem
  case object NoItem extends DocItem

  trait Lang
  case object SV extends Lang
  case object DE extends Lang
  case object EN extends Lang  
  case object NoLang extends Lang
  
  trait ChapterLike {
    def id: String 
    def head: String
    def contents: Seq[DocItem]
    def template: ChapterTemplate
    def lang: Lang
  }  
  
  case class Chapter(
    id: String, 
    head: String, 
    contents: Seq[DocItem],
    template: ChapterTemplate = DefaultChapterTemplate,
    lang: Lang = NoLang
  ) extends ChapterLike 
  
  case class Challenge(
    id: String, 
    head: String, 
    goal: Seq[DocItem] = Seq(),
    task: Seq[DocItem] = Seq(),
    hint: Seq[DocItem] = Seq(),
    template: ChapterTemplate = DefaultChapterTemplate,
    lang: Lang = NoLang
  ) extends ChapterLike {
    val goalHeading = if (!goal.isEmpty) Seq(Section(i18n("goalHeading", lang))) else Seq()
    val taskHeading = if (!task.isEmpty) Seq(Section(i18n("taskHeading", lang),color="BrickRed")) else Seq()
    val hintHeading = if (!hint.isEmpty) Seq(Section(i18n("hintHeading", lang),color="OliveGreen")) else Seq()
    override def contents = goalHeading ++ goal ++ taskHeading ++ task ++ hintHeading ++ hint
  }

}
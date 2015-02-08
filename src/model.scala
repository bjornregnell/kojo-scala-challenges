package scaboo

object model {

  sealed trait ChapterTemplate
  case class TextWithImage(file: String, width: Double = -1) extends ChapterTemplate
  case class MultiColumn(cols: Int) extends ChapterTemplate
  case object DefaultChapterTemplate extends ChapterTemplate

  sealed trait DocItem 
  case class Para(ps: String*) extends DocItem 
  case class Itemize(ps: String*) extends DocItem 
  case class Section(heading: String, color: String = "black") extends DocItem
  case class Code(code: String, size: Double = -1) extends DocItem 
  case class HRef(url:String, text: String) extends DocItem
  case class Image(file: String, width: Double = -1) extends DocItem
  case class CenterImage(file: String, width: Double = -1) extends DocItem 
  case class VSkip(size: Double = -1) extends DocItem
  case object LineBreak extends DocItem
  case object ColumnBreak extends DocItem
  case object NoItem extends DocItem
  
  case class Chapter(
    id: String, 
    head: String, 
    contents: Seq[DocItem],
    template: ChapterTemplate = DefaultChapterTemplate
  )  
  
}
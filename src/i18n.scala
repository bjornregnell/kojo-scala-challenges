package scaboo

object i18n {
  import model._
  val phrase = Map[(String, Lang),String](
    ("goalHeading", SV) -> "Du lär dig att:",
    ("goalHeading", DE) -> "Sie lernen:",
    ("goalHeading", EN) -> "You learn this:",
    ("taskHeading", SV) -> "Uppdrag:",
    ("taskHeading", DE) -> "Aufgabe:",
    ("taskHeading", EN) -> "Challenge:"
  )
  def apply(key: String, lang: Lang): String = phrase((key, lang))
}
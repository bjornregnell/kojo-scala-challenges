//scala script to generate book body from source
import scaboo.model._
import scaboo.latexgen
import scaboo.ioutils

println("\n\n*** scaboo: Scala Book Generator: Generating kojobook ***")
ioutils.mkdir("kojobook/tex")

val taskHead = Section("Aufgabe:", color="BrickRed")
  //more latex colors here: http://en.wikibooks.org/wiki/LaTeX/Colors  
val kojoDownloadUrl = HRef(url="http://www.kogics.net/kojo-download",text="www.kogics.net/kojo-download")
val lthProgrammeraUrl = HRef(url="http://lth.se/programmera",text="lth.se/programmera")
  
val chapters = {
  val kojoDownloadUrl = HRef(url="http://www.kogics.net/kojo-download",text="www.kogics.net/kojo-download")
  val lthProgrammeraUrl = HRef(url="http://lth.se/programmera",text="lth.se/programmera")
  Seq(
    Chapter(id="about-kojo", head="Über Kojo", template=TextWithImage("kojo.png"), 
      contents=Seq(
        Section("Was ist Kojo?",color="black"),
        Para("Kojo ist eine App die hilfen Sie programmieren zu lernen. Mit Kojo, Sie können in der modernen und leistungsfähigen Programmiersprache {!Scala!} kodieren. Kojo ist kostenlos und gibt auf Deutsch. Kojo funktioniert mit Linux, Windows und Mac OSX."),
        Section("Wo finde ich Kojo?",color="black"),
        Para("Download Kojo: "),kojoDownloadUrl,
        Para("Lesen Sie hier mehr: "),lthProgrammeraUrl
      )
    ),
    Chapter(id="forward", head="Ihr erstes Programm", template=TextWithImage("forward.png",14), 
    contents=Seq(
        Code("""
clear
forward
         """.trim, size=36),
        Para("Drücken Sie die grüne Play-Taste um kick off Programm.")
      )
    )
  )
}

//********************
println("********************* input:"+chapters)
latexgen.make(chapters, "kojobook/tex/body-de.tex")

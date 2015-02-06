//scala script to generate book body from source
import scaboo.model._
import scaboo.latexgen
import scaboo.ioutils

println("\n\n*** scaboo: Running script: kojobook/source.scala ***")

val chapters = {
  val kojoDownloadUrl = HRef(url="http://www.kogics.net/kojo-download",text="www.kogics.net/kojo-download")
  val lthProgrammeraUrl = HRef(url="http://lth.se/programmera",text="lth.se/programmera")
  Seq(
    Chapter(id="about-kojo", head="Om Kojo", template=TextWithImage("kojo.png"), lang=SV,
      contents=Seq(
        Section("Vad är Kojo?",color="black"),
        Para("Kojo är en app som hjälper dig att lära dig att programmera. Med Kojo kan du koda i det moderna och kraftfulla programspråket Scala. Kojo är gratis och finns på Svenska. Kojo fungerar med Linux, Windows och Mac OSX."),
        Section("Var hittar jag Kojo?",color="black"),
        Para("Ladda ner Kojo här: "),kojoDownloadUrl,
        Para("Läs mer här: "),lthProgrammeraUrl
      )
    ),
    Chapter(id="about-kojo", head="Über Kojo", template=TextWithImage("kojo.png"), lang=DE,
      contents=Seq(
        Section("Was ist Kojo?",color="black"),
        Para("Kojo ist eine App die hilfen Sie programmieren zu lernen. Mit Kojo, Sie können in der modernen und leistungsfähigen Programmiersprache Scala kodieren. Kojo ist kostenlos und gibt auf Deutsch. Kojo funktioniert mit Linux, Windows und Mac OSX."),
        Section("Wo finde ich Kojo?",color="black"),
        Para("Download Kojo: "),kojoDownloadUrl,
        Para("Lesen Sie hier mehr: "),lthProgrammeraUrl
      )
    ),
    Challenge(id="forward", head="Ditt första program", template=TextWithImage("fram.png"), lang=SV,
      goal=Seq(Para("Skriva kod i en editor.","Köra igång ett program.")),
      task=Seq(
        Para("Skriv detta program i editorn:"),
        Code("""
sudda
fram
         """.trim, size=24),
        Para("Tryck på den gröna play-knappen","för att köra igång ditt program.")
      )
    ),
    Challenge(id="forward", head="Ihr erstes Programm", template=TextWithImage("fram.png"), lang=DE,
      goal=Seq(Para("Schreiben in einem Editor.","Ein Programm starten.")),
      task=Seq(
        Para("Schreiben Sie das Programm im Editor:"),
        Code("""
clear
forward
         """.trim, size=24),
        Para("Drücken Sie die grüne Play-Taste "," um kick off Programm.")
      )
    ),
    Challenge(id="square", head="Rita en kvadrat", template=TextWithImage("kvadrat.png"), lang=SV,
      goal=Seq(Para("Satser i sekvens görs i tur och ordning.","Ordningen spelar stor roll.")),
      task=Seq(
        Para("Skriv in och utöka detta program så att paddan ritar en kvadrat:"),
        Code("""
sudda
fram
höger
         """.trim, size=24)
      )
    )
  )
}

ioutils.mkdir("kojobook/tex")
println("Generating swedish book...")
val swedish = chapters.filter(_.lang == SV)
println("********************* Swedish input:"+swedish)
latexgen.make(swedish, "kojobook/tex/body-sv.tex")

println("Generating german book...")
val german = chapters.filter(_.lang == DE)
println("********************* German input:"+german)
ioutils.mkdir("kojobook/tex")
latexgen.make(german, "kojobook/tex/body-de.tex")

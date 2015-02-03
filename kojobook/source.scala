println("*** Running script: kojobook/source.scala")

import scaboo._

val chapters = Seq(
  Challenge(id="fram", head="Ditt första program", template=TextWithImage("fram.png"), 
    goals=Seq(Para("Skriva kod i en editor.","Köra igång ett program.")),
    task=Seq(
      Para("Skriv detta program i editorn:"),
      Code("""
sudda
fram
       """.trim, size=24),
      Para("Tryck på den gröna play-knappen","för att köra igång ditt program.")
    )
  )
)

ioxtra.mkdir("kojobook/tex")
latexgen.make(chapters, "kojobook/tex/body.tex")

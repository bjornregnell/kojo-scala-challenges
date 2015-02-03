//scala script to generate book body from source
import scaboo._

println("\n\n*** scaboo: Running script: kojobook/source.scala ***")

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
  ),
  Challenge(id="kvadrat", head="Rita en kvadrat", template=TextWithImage("kvadrat.png"), 
    goals=Seq(Para("Att satser i sekvens körs i tur och ordning.","Att ordningen spelar stor roll.")),
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

ioxtra.mkdir("kojobook/tex")
latexgen.make(chapters, "kojobook/tex/body.tex")

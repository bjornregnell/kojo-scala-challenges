//scala script to generate book body from source
import scaboo.model._
import scaboo.latexgen
import scaboo.ioutils

println("\n\n*** scaboo: Scala Book Generator: Generating kojobook ***")
ioutils.mkdir("kojobook/tex")

val taskHead = Section("Uppdrag:", color="BrickRed")
val hintHead = Section("Tips:", color="OliveGreen")
  //more latex colors here: http://en.wikibooks.org/wiki/LaTeX/Colors  
val kojoDownloadUrl = HRef(url="http://www.kogics.net/kojo-download",text="www.kogics.net/kojo-download")
val lthProgrammeraUrl = HRef(url="http://lth.se/programmera",text="lth.se/programmera")
  
val chapters = Seq(
  Chapter(id="about-kojo", head="Om Kojo", template=TextWithImage("kojo.png",14), 
    contents=Seq(
      Section("Vad är Kojo?",color="black"),
      Para("Kojo är en app som hjälper dig att lära dig att programmera. Med Kojo kan du koda i det moderna och kraftfulla programspråket {!Scala!}. Kojo är gratis och finns på Svenska. Kojo fungerar med Linux, Windows och Mac OSX."),
      Section("Var hittar jag Kojo?",color="black"),
      Para("Ladda ner Kojo här: "), kojoDownloadUrl, 
      Para("Läs mer här: "),lthProgrammeraUrl
    )
  ), //------------------------------------------------------   
  Chapter(id="forward", head="Ditt första program", template=TextWithImage("fram.png",14), 
    contents=Seq(
      taskHead, Para("Skriv så här i Kojos skripteditor-fönster:"),
      Code("""
sudda
fram
       """.trim, size=48),
      Para("Tryck på den gröna play-knappen "), Image("play.png",1), LineBreak,
      Para("för att köra igång ditt program.")
    )
  ), //------------------------------------------------------   
  Chapter(id="square", head="Rita en kvadrat", template=TextWithImage("square.png"), 
    contents=Seq(
      Code("""
sudda
fram
höger
       """.trim, size=36),
    Para("Om du skriver {:vänster:} eller {:höger:} så vrider sig paddan."),
    taskHead, Para("Utöka programmet så att det blir en kvadrat.")
    )
  ),
  Chapter(id="stairs", head="Rita en trappa", template=TextWithImage("stairs.png"), 
    contents=Seq(
      Code("""
sudda
fram; vänster
fram; höger

       """.trim, size=36), VSkip(1), 
    Para("Med semikolon {:;:} kan du ha flera satser på samma rad."),
    taskHead, Para("Utöka programmet så att det blir en trappa.")
    )
  ), //------------------------------------------------------
  Chapter(id="square-repeat", head="Gör en loop", template=TextWithImage("square.png"), 
    contents=Seq(
      Code("""
sudda
upprepa(4){fram; höger}
       """.trim, size=36),
      taskHead, Itemize("Vad händer om du ändrar 4 till 100?","Rita en trappa med 100 trappsteg.") 
    )
  ), //------------------------------------------------------
  Chapter(id="alien", head="Rita en gubbe", template=MultiColumn(2), 
    contents=Seq(
      taskHead, Para("Rita en gubbe som du själv vill."),
      hintHead, 
      Code("""
hoppa
vänster(180)
fram(300)
hoppa(100)
hoppaTill(25,-28)
skriv("FELIX är bäst")
       """.trim, size=24),
      Para("Du kan se paddans läge nere till vänster medan du rör muspekaren i Ritfönstret:"), Image("mousepos.png", 6),
      ColumnBreak,
      CenterImage("man.png",4.5), VSkip(2), CenterImage("alien.png",9)
    )
  ), //------------------------------------------------------
  Chapter(id="def-square", head="Gör din egen funktion", 
    contents=Seq(
      Para("Med {:def:} kan du göra egna {/funktioner/} som du själv väljer namn på."), 
      Code("""
def kvadrat =  upprepa(4){fram; höger}  

sudda
kvadrat    //använd din kvadrat-funktion
hoppa
kvadrat   
       """.trim, size=24),
    taskHead, Itemize("Byt färg på kvadraterna.", "Gör fler kvadrater."),
    hintHead, Code("""
fyll(grön); färg(lila)
       """.trim)
    )
  ), //------------------------------------------------------
  Chapter(id="square-column", head="Stapla kvadrater", 
    template=TextWithImage("square-column.png"),
    contents=Seq(
    taskHead, Para("Gör en stapel med 10 kvadrater."),
    hintHead, VSkip(1),
    Code("""
def kvadrat =  upprepa(4){fram; höger}  

sudda; sakta(100)
upprepa(10){ ??? }""".trim)
    )
  ), //------------------------------------------------------
  Chapter(id="def-square-column", head="Gör en stapelfunktion", 
    template=TextWithImage("square-column.png"),
    contents=Seq(
      taskHead, Para("Gör en funktion som heter {:stapel:}, som ritar en stapel med 10 kvadrater."),
      hintHead,  
      Code("""
def kvadrat = upprepa(4){fram; höger}  
def stapel = ???

sudda; sakta(100)
stapel""".trim)
    )
  ), //------------------------------------------------------
  Chapter(id="square-grid", head="Gör ett rutnät", 
    template=TextWithImage("square-grid.png"),
    contents=Seq(
      taskHead, Para("Gör ett rutnät med 10*10 kvadrater."),
      hintHead, Itemize("Använd din stapelfunktion från tidigare.",
      "Du kan hoppa baklänges en hel stapelhöjd med {:hoppa(-10*25):}",
      "Du kan sedan hoppa till rätt plats med {:höger; hoppa; vänster:}") 
    )
  ), //------------------------------------------------------
  Chapter(id="def-square-param", head="Kvadrat-funktion med parameter", 
    template=MultiColumn(2),
    contents=Seq(
      taskHead, Para("Rita olika stora kvadrater."),
      hintHead, 
      Para("Ge din kvadrat-funktion en {/parameter/},","med namnet {:sidlängd:} och typen {:Heltal:}:"), 
      Code("""
def kvadrat(sidlängd : Heltal) = 
  upprepa(4){fram(sidlängd); höger}

sudda; sakta(100); osynlig
kvadrat(100) 
kvadrat(70)
kvadrat(40)
       """.trim,frame=true,size=16), 
      Para("Du kan byta färg med:","{:fyll(blå); färg(rosa):}"),
      ColumnBreak,
      CenterImage("square-param.png",5),
      CenterImage("square-param-color.png",5)
    )
  ), //------------------------------------------------------
  Chapter(id="def-square-man", head="Rita en kvadratgubbe", 
    contents=Seq(
      taskHead, Para("Rita en gubbe med hjälp av olika stora kvadrater."),
      LineBreak,
      Image("square-man.png",3),
      hintHead,
      Code("""
def kvadrat(sidlängd : Heltal) = upprepa(4){fram(sidlängd); höger}
def huvud = {fyll(rosa); kvadrat(200)}
def öga = {fyll(vit); färg(svart); kvadrat(40)}
def pupill = {fyll(svart); färg(svart); kvadrat(10) }
def näsa = {färg(genomskinlig); fyll(blå); kvadrat(30)}
def mun = {bredd(10); fyll(svart); färg(röd); kvadrat(40)}

sudda; sakta(20); osynlig
huvud
hoppaTill(40,100);  öga
hoppaTill(60,100);  pupill
???
       """.trim,frame=true,size=16)
    )
  )    
)

//********************
println("********************* input:"+chapters)
latexgen.make(chapters, "kojobook/tex/body-sv.tex")

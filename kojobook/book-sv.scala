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
      Para("Ladda ner Kojo här: "), LineBreak, kojoDownloadUrl, LineBreak,
      Para("Läs mer här: "), LineBreak, lthProgrammeraUrl
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
upprepa(4){ fram; höger }
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
def kvadrat =  upprepa(4){ fram; höger }  

sudda
kvadrat    //använd din kvadrat-funktion
hoppa
kvadrat   
       """.trim, size=20),
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
def kvadrat =  upprepa(4){ fram; höger }  

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
def kvadrat = upprepa(4){ fram; höger }  
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
  Chapter(id="def-square-param", head="Kvadrat med parameter", 
    template=MultiColumn(2),
    contents=Seq(
      taskHead, Para("Rita olika stora kvadrater."),
      hintHead, 
      Para("Ge din kvadrat-funktion en {/parameter/},","med namnet {:sidlängd:} och typen {:Heltal:}:"), 
      Code("""
def kvadrat(sidlängd : Heltal) = 
  upprepa(4){ fram(sidlängd); höger }

sudda; sakta(100); osynlig
kvadrat(100) 
kvadrat(70)
kvadrat(40)
       """.trim,size=16), 
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
      OverlayImage("square-man.png",x=20,y=0.5,width=5.5),
      hintHead,
      Code("""
def kvadrat(x: Heltal, y: Heltal, sidlängd: Heltal) = {
  hoppaTill(x, y)
  upprepa(4) { fram(sidlängd); höger }
}
def huvud(x: Heltal, y: Heltal) = { fyll(rosa); färg(röd); kvadrat(x, y, 200) }
def öga(x: Heltal, y: Heltal) = { fyll(vit); färg(svart); kvadrat(x, y, 40) }
def pupill(x: Heltal, y: Heltal) = { fyll(svart); färg(svart); kvadrat(x, y, 10) }
def näsa(x: Heltal, y: Heltal) = { fyll(blå); färg(genomskinlig); kvadrat(x, y, 30) }
def mun(x: Heltal, y: Heltal) = { bredd(10); fyll(svart); färg(röd); kvadrat(x, y, 40) }

sudda; sakta(20); osynlig
huvud(0, 0)
öga(40, 100); pupill(60, 100)
???
       """.trim,size=14)
    )
  ), //------------------------------------------------------
  Chapter(id="plygon", head="Rita en polygon", 
    contents=Seq(
      taskHead, Itemize(
        "Prova koden nedan. Rita olika slags polygoner.",
        "Lägg till en parameter {:sidlängd:} och rita olika stora polygoner.",
        "Hur stort behöver n vara för att det ska se ut som en cirkel?"),
      hintHead,
      Code("""
def polygon(n:Heltal) = upprepa(n){
  fram(100)
  vänster(360.0/n)
}

sudda; sakta(100)
polygon(7)
       """.trim,size=18),
      OverlayImage("polygon.png",x=20,y=3.5,width=8)
    )
  ), //------------------------------------------------------
  Chapter(id="plygons", head="Rita många polygoner", 
    contents=Seq(
      taskHead, Itemize(
        "Prova programmet nedan.",
        "Prova ändra antalet sidor och vinkel.",
        "Fyll polygonerna med färg."),
      OverlayImage("polygons-circle.png",x=21,y=1,width=12),
      Code("""
def polygon(n: Heltal, sidlängd: Heltal) = upprepa(n){
  fram(sidlängd)
  vänster(360.0/n)
}
def snurra(n: Heltal, vinkel: Heltal, sidlängd: Heltal) = 
  upprepa(360/vinkel){ polygon(n, sidlängd); vänster(vinkel) }

sudda; sakta(5)
snurra(7, 10, 100)
       """.trim,size=16)
    )
  ), //------------------------------------------------------
  Chapter(id="random-circles", head="Slumptal", 
    contents=Seq(
      taskHead, Itemize(
        "Kör programmet nedan många gånger. Vad händer?",
        "Vilket är det minsta och största möjliga värdet på radien {:r}?",
        "Ändra så att {:r:} blir ett slumptal mellan 3 och 200.",
        "Rita 100 cirklar med slumpmässig radie på slumpmässig plats, som bilden visar."),
      OverlayImage("random-circles.png",x=21,y= -5,width=8),
      Code("""
//värdet r blir ett slumptal mellan 10 och 89:
val r = slumptal(90) + 10   

sudda; sakta(10); osynlig
skriv("Radie = " + r)
cirkel(r)
       """.trim,size=20)
    )
  ), //------------------------------------------------------
  Chapter(id="mix-colors", head="Blanda dina egna färger", 
    contents=Seq(
      Itemize(
        "Med {:Color:} kan du blanda egna färger, till exempel {:Color(0, 70, 0):}", 
        "De tre parametrarna anger mängden {/rött/}, {/grönt/} och {/blått/}",
        "Du kan också lägga till en fjärde parameter som anger {/genomskinligheten/}",
        "Alla parametrar ska vara mellan 0 och 255" 
      ),
      taskHead, Para(
        "Prova programmet nedan. Ändra genomskinligheten."
      ),
      OverlayImage("color-circles.png",x=23,y= -2,width=7),
      Code("""
sudda; sakta(100)      

val olivgrön = Color(0,70,0)
val pistageglass = Color(0,255,0,100)

fyll(olivgrön); cirkel(100)
fyll(pistageglass); fram(100); cirkel(100)
       """.trim,size=16)
    )
  ), //------------------------------------------------------
  Chapter(id="color-picker", head="Prova färgväljaren", template=TextWithImage("color-chooser-rgb-sv.png",width=14),
    contents=Seq(
      taskHead, Itemize(
        """Högerklicka i editor-fönstret och klicka på "Välj färg".""", 
        "Om du väljer fliken {*RGB*} i färgväljaren kan du blanda nya RGB-färger.", 
        "Tryck OK och titta i Utdatafönstret. Där syns de tre RGB-värdena för rött, grönt och blått.",
        "Du kan använda dessa värden i ditt program för att rita med din nya färg med {:färg(Color(218, 153, 67)):}."
      )
    )
  ), //------------------------------------------------------
  Chapter(id="circle-of-circles", head="Rita slumpcirklar", 
    template=MultiColumn(2),
    contents=Seq(
      Code("""
def slump = slumptal(256)
def slumpfärg = Color(slump,10,slump,100) 

sudda; sakta(5)
bakgrund2(svart,vit)
bredd(6)

upprepa(100) {
    färg(slumpfärg)
    cirkel(100)
    hoppa(20)
    höger(35)
}
       """.trim,size=16), taskHead, Para("Prova olika slumpfärger och bakgrunder."),
       ColumnBreak, CenterImage("circle-of-circles.png",width=12)
    )
  ), //------------------------------------------------------
  Chapter(id="flower", head="Rita en blomma", 
    contents=Seq(
      taskHead, 
      Para("Programmet nedan ritar 100 slumpfärgade cirklar på slumpmässig plats med slumpmässig radie. " +
           "Prova att ändra de olika slumptalens gränser och försök förklara vad som händer."),
      OverlayImage("random-color-circles.png",x=21.5,y= -4,width=9),
      Code("""
sudda(); sakta(5)
bredd(2)
upprepa(100){
  färg(Color(slumptal(256),0,slumptal(256)))
  fyll(Color(slumptal(256),0,slumptal(256),slumptal(100)+50))
  vänster(slumptal(360))
  cirkel(slumptal(30)*4+10)
}
       """.trim,size=16)
    )
  ), //------------------------------------------------------
  Chapter(id="flowers", head="Rita många blommor", 
    contents=Seq(
      taskHead,       
      Itemize(
        "Gör en funktion som heter {:blomma:}, som ritar en krona och en grön stjälk från kronans mitt med ett grönt blad.",
        "Rita 5 blommor bredvid varandra."),
      hintHead,
      Para(
        "Du kan rita blad med {:båge(radie, vinkel):}.",
        "Låt funktionen {:blomma:} ha två parametrar x och y och använd {:hoppaTill(x,y):}",
        "Du kan loopa 5 gånger och räkna ut platsen så här:"
      ),
      OverlayImage("flowers.png",x=15, y= -2, width=16),
      Code("""
var i = 0          
upprepa(5){
  blomma(600*i,0)
  i = i + 1        
}
""".trim,size=20)    
    )
  )    
)

//********************
println("********************* input:"+chapters)
latexgen.make(chapters, "kojobook/tex/body-sv.tex")

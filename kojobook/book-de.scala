//scala script to generate book body from source
import scaboo.model._
import scaboo.latexgen
import scaboo.ioutils

println("\n\n*** scaboo: Scala Book Generator: Generating kojobook ***")
ioutils.mkdir("kojobook/tex")

val taskHead = Section("Aufgabe:", color="BrickRed")
val hintHead = Section("Tipps:", color="OliveGreen")
  //more latex colors here: http://en.wikibooks.org/wiki/LaTeX/Colors  
val kojoDownloadUrl = HRef(url="http://www.kogics.net/kojo-download",text="www.kogics.net/kojo-download")
val lthProgrammeraUrl = HRef(url="http://lth.se/programmera",text="lth.se/programmera")
  
val chapters = Seq(
  Chapter(id="about-kojo", head="Über Kojo", template=TextWithImage("kojo.png",14), 
    contents=Seq(
      Section("Was ist Kojo?",color="black"),
      Para("Kojo ist eine App, die Dir beibringt, wie man programmiert. Mit Kojo benutzt Du die moderne und leistungsfähige Programmiersprache {!Scala!}. Kojo gibt es kostenlos und auf Deutsch. Kojo läuft auf Linux, Windows und Mac OS X."),
      Section("Wo kannst Du Kojo finden?",color="black"),
      Para("Lade Kojo hier: "), LineBreak, kojoDownloadUrl, LineBreak,
      Para("Lies hier mehr darüber: "), LineBreak, lthProgrammeraUrl
    )
  ), 
//------------------------------------------------------   
  Chapter(id="forward", head="Dein erstes Programm", template=TextWithImage("fram_de.png",14), 
    contents=Seq(
      taskHead, Para("Schreibe diese Worte in Kojos Programmbearbeiter:"),
      Code("""
leeren
vor
       """.trim, size=30),
      Para("Klicke auf das grüne Ausführen-Symbol "), Image("play.png",1), LineBreak,
      Para("um das Programm zu starten."), LineBreak, VSkip(5)
    )
  ), 
//------------------------------------------------------   
  Chapter(id="square", head="Zeichne ein Quadrat", template=TextWithImage("square.png"), 
    contents=Seq(
      Code("""
leeren
vor
rechts
       """.trim, size=30),
    Para("Bei Eingabe von {:links:} oder {:rechts:} dreht sich die Kröte."),
    taskHead, Para("Erweitere das Programm so, dass ein Quadrat gezeichnet wird.")
    )
  ),
  //------------------------------------------------------
  Chapter(id="stairs", head="Zeichne eine Treppe", template=TextWithImage("stairs.png"), 
    contents=Seq(
      Code("""
leeren
vor; links
vor; rechts

       """.trim, size=30), VSkip(1), 
    Para("Mehrere Befehle in einer Zeile müssen durch ein Semikolon {:;:} getrennt werden."),
    taskHead, Para("Erweitere das Programm so, dass eine Treppe gezeichnet wird.")
    )
  ), 
//------------------------------------------------------
  Chapter(id="square-repeat", head="Nutze eine Schleife", template=TextWithImage("square.png"), 
    contents=Seq(
      Code("""
leeren
schleife(4){ vor; rechts }
       """.trim, size=30),
      taskHead, Itemize("Was passiert, wenn Du statt 4 die Zahl 100 eingibst?","Zeichne eine Treppe mit 100 Stufen.") 
    )
  ),
  //------------------------------------------------------
  Chapter(id="argument", head="Übergebe Parameter", template=TextWithImage("de/hexagon.png"),
    contents=Seq(
      Code("""
vor(40)
rechts(60)
links(40)
       """.trim, size=30),VSkip(1),
      Para("Du kannst Befehlen Parameter übergeben, damit sie nicht die Standardwerte verwenden."),
      taskHead, Itemize("Zeichne ein Vieleck")
    )
  ),
  //------------------------------------------------------
  Chapter(id="jump", head="Bewege dich zum Ziel", template=TextWithImage("de/quadrate.png"),
    contents=Seq(
      Code("""
springen
springen(90)
springen(100,200)
gehen(65,180)
       """.trim, size=30),VSkip(1),
      Para("Die Position der Maus im Zeichenbereich kannst Du links unter dem Programmbearbeiter ablesen:"),
      VSkip(1),Image("de/mousepos.png", 5),
      taskHead, Itemize("Zeichne ein Quadrat im Quadrat")
    )
  ),
  //------------------------------------------------------
  Chapter(id="alien", head="Zeichne eine farbige Figur", template=MultiColumn(2),
    contents=Seq(
      Code("""
schreiben("Mein Name ist...")
stiftfarbe(lila)
füllfarbe(grün)
       """.trim, size=25),VSkip(1),
      taskHead, Para("Zeichne eine einfache farbige Figur."),
      ColumnBreak,
      Image("de/head.png",5),Image("de/house.png",6)
    )
  ), 
//------------------------------------------------------
  Chapter(id="check-speed", head="Wie schnell ist Dein Computer?", 
    //template=MultiColumn(2),
    contents=Seq(
      Para( "Der erste Computer hieß {*ENIAC*} und konnte in einer Sekunde bis 5000 zählen.",
        "In Kojo gibt es eine Funktion {:zählzeitStoppen:} die misst, wie schnell Dein Computer zählen kann.",
        "Wenn ich {:zählzeitStoppen(5000):} ausführe, dann wird die Zeit ausgegeben, die mein Computer für das Zählen braucht:"),
      Code("""
*** Zählt von 1 bis ... 5000 *** FERTIG!
Es dauerte 0.32 Millisekunden.
      """),
      taskHead, 
      Itemize("Gib ein {:zählzeitStoppen(5000):} und prüfe, ob Dein Computer schneller ist als meiner.",
              "Wie lange braucht Dein Computer, um bis eine Million zu zählen?",
              "Wie weit kann Dein Rechner in einer Sekunde zählen?")      
    )
  ), 
//------------------------------------------------------
  Chapter(id="trace", head="Verfolge Dein Programm", template=TextWithImage("trace_de.png"), 
    contents=Seq(
      taskHead, Itemize(
        "Schreibe ein Programm, das eine Stufe zeichnet.",
        "Klicke auf das orange Verfolgen-Symbol.",
        "Klicke auf einen der Aufrufe: {:CALL vor ():}. Was passiert im Zeichenbereich?",
        "Im Programmbearbeiter wird der zugehörige Befehl blau markiert. Deaktiviere die Markierung, indem Du neben die Markierung klickst.",
        "Füge dem Programm weitere Befehle hinzu und probiere aus, was passiert, wenn Du sie verfolgst",
        "Schließe das Fenster {/Programmschritte verfolgen/} wenn Du fertig bist.")
    )
  ), 
//------------------------------------------------------
  Chapter(id="def-square", head="Erstelle Deine eigene Funktion mit {:def:}", 
    contents=Seq(
      Para("Mit {:def:} kannst Du einer eigenen {/Funktion/} einen Namen geben."), 
      Code("""
def quadrat =  schleife(4){ vor; rechts }  

leeren
quadrat    //Ruft die Quadrat-Funktion auf.
springen
quadrat   
       """.trim, size=20),
    taskHead, Itemize("Ändere die Farbe der Quadrate.", "Erstelle mehr Quadrate."),
    hintHead, Code("""
füllfarbe(grün); stiftfarbe(lila)
       """.trim)
    )
  ), 
//------------------------------------------------------
  Chapter(id="square-column", head="Stapel Quadrate", 
    template=TextWithImage("square-column.png"),
    contents=Seq(
    taskHead, Para("Erstelle einen Stapel von 10 Quadraten."),
    hintHead, VSkip(1),
    Code("""
def quadrat =  schleife(4){ vor; rechts }  

leeren; langsam(100)
schleife(10){ ??? }""".trim)
    )
  ), 
//------------------------------------------------------
  Chapter(id="def-square-column", head="Schreibe eine Stapelfunktion", 
    template=TextWithImage("square-column.png"),
    contents=Seq(
      taskHead, Para("Schreibe eine Funktion mit dem Namen {:stapel:}, die einen Stapel aus 10 Quadraten zeichnet."),
      hintHead,  
      Code("""
def quadrat = schleife(4){ vor; rechts }  
def stapel = ???

leeren; langsam(100)
stapel""".trim)
    )
  ), 
//------------------------------------------------------
  Chapter(id="square-grid", head="Erstelle ein Gitter", 
    template=TextWithImage("square-grid.png"),
    contents=Seq(
      taskHead, Para("Erstelle ein Gitter aus 10 * 10 Quadraten."),
      hintHead, Itemize("Verwende hierfür Deine Stapelfunktion von eben.",
      "Du kannst eine ganze Stapelhöhe zurück springen mit {:springen(-10 * 25):}",
      "Du kannst dann an die richtige Stelle springen mit {:rechts; springen; links:}") 
    )
  ), 
//------------------------------------------------------
  Chapter(id="def-square-param", head="Quadrat mit Parameter", 
    template=MultiColumn(2),
    contents=Seq(
      taskHead, Para("Zeichne verschiedene Quadrate."),
      hintHead, 
      Para("Gib Deiner Quadrat-Funktion einen {/Parameter/}","mit dem Namen {:seitenlänge:} und Typ {:Ganzzahl:}:"), 
      Code("""
def quadrat(seitenlänge : Ganzzahl) = 
  schleife(4){ vor(seitenlänge); rechts }

leeren; langsam(100); unsichtbar
quadrat(100) 
quadrat(70)
quadrat(40)
       """.trim,size=16), 
      Para("Du kannst die Farbe ändern mit:","{:füllfarbe(blau); stiftfarbe(rosa):}"),
      ColumnBreak,
      CenterImage("square-param.png",5),
      CenterImage("square-param-color.png",5)
    )
  ), 
//------------------------------------------------------
  Chapter(id="def-square-man", head="Zeichne einen Quadratkopf", 
    contents=Seq(
      taskHead, Para("Zeichne einen Kopf aus verschiedenen Quadraten."),
      LineBreak,
      OverlayImage("square-man.png",x = 20, y = -1, width = 5.5),
      hintHead,
      Code("""
def quadrat(x: Ganzzahl, y: Ganzzahl, seitenlänge: Ganzzahl) = {
  springen(x, y)
  schleife(4) { vor(seitenlänge); rechts }
}
def kopf(x: Ganzzahl, y: Ganzzahl) = { füllfarbe(rosa); stiftfarbe(rot); quadrat(x, y, 200) }
def auge(x: Ganzzahl, y: Ganzzahl) = { füllfarbe(weiß); stiftfarbe(schwarz); quadrat(x, y, 40) }
def pupille(x: Ganzzahl, y: Ganzzahl) = { füllfarbe(schwarz); stiftfarbe(schwarz); quadrat(x, y, 10) }
def nase(x: Ganzzahl, y: Ganzzahl) = { füllfarbe(blau); stiftfarbe(durchsichtig); quadrat(x, y, 30) }
def mund(x: Ganzzahl, y: Ganzzahl) = { stiftbreite(10); füllfarbe(schwarz); 
                                            stiftfarbe(rot); quadrat(x, y, 40) }

leeren; langsam(20); unsichtbar
kopf(0, 0)
auge(40, 100); pupille(60, 100)
???
       """.trim,size=14)
    )
  ), 
//------------------------------------------------------
  Chapter(id="polygon", head="Zeichne ein Vieleck", 
    contents=Seq(
      taskHead, Itemize(
        "Probiere das Programm unten aus. Zeichne verschiedene Vielecke.",
        "Füge einen Parameter {:seitenlänge:} hinzu und zeichne verschieden große Vielecke.",
        "Wie groß muss n sein, damit ein Kreis entsteht?"),
      hintHead,
      Code("""
def vieleck(n: Ganzzahl) = schleife(n){
  vor(100)
  links(360.0/n)
}

leeren; langsam(100)
vieleck(7)
       """.trim,size=18),
      OverlayImage("polygon.png",x=20,y=3.5,width=8)
    )
  ), 
//------------------------------------------------------
  Chapter(id="polygons", head="Zeichne viele Vielecke", 
    contents=Seq(
      taskHead, Itemize(
        "Probiere das Programm unten aus.",
        "Verändere die Anzahl der Seiten und den Winkel.",
        "Fülle die Vielecke mit Farbe."),
      OverlayImage("polygons-circle.png",x = 22, y = -0.5, width = 11),
      Code("""
def vieleck(n: Ganzzahl, seitenlänge: Ganzzahl) = 
  schleife(n){
    vor(seitenlänge)
    links(360.0/n)
  }
def drehen(n: Ganzzahl, winkel: Ganzzahl, seitenlänge: Ganzzahl) = 
  schleife(360/winkel){ 
    vieleck(n, seitenlänge) 
    links(winkel) 
  }

leeren; langsam(5)
drehen(7, 10, 100)
       """.trim,size=16)
    )
  ), 
//------------------------------------------------------
  Chapter(id="show-value", head="Werte und Ausdrücke", 
    template = MultiColumn(2),
    contents=Seq(
      taskHead, 
      Itemize("Schreibe {:1 + 1:} und klicke die blaue Ausführen-Taste. Dann erstellt Kojo einen grünen Kommentar.", 
        "Der Kommentar sagt, dass der Wert des Ausdrucks {:1 + 1:} gleich {:2:} ist und dass der Typ {:Int:} ist. {:Int:} ist eine Abkürzung für Integer, auf Deutsch: {:Ganzzahl:}.",
        "Führe weitere Berechnungen durch. Welcher Wert und Typ wird ausgegeben?"),
      Code("""
5 * 5
10 + 2 * 5
"Auf" + " Wieder" + "sehen"
5 / 2
5 / 2.0
5 % 2
      """.trim),
      ColumnBreak, Image("show-value_de.png", width=12),
      hintHead, Itemize("Steht {:/:} zwischen ganzen Zahlen, so wird eine ganzzahlige Division ausgeführt. Das heißt, die Nachkommastellen entfallen. Für eine Division mit Nachkommastellen muss mindestens eine Zahl eine Bruchzahl sein.","Mit {:%:} erhältst Du den Rest einer ganzzahligen Division.")
    )
  ), 
//------------------------------------------------------
  Chapter(id="val", head="Gebe einem Wert einen Namen mit {:val:}", 
    template=TextWithImage("val_de.png",width=12),
    contents=Seq(
      taskHead,
      Para("Mit {:val:} kannst Du einen Namen mit einem Wert koppeln. Der Name kann dann anstelle des Wertes verwendet werden. Probiere das folgende Programm aus. Was schreibt die Kröte?"),
      Code("""
val x = 10
val y = 5
val gurke = x + y
val banane = x * y

leeren
vor; schreiben(banane)
vor; schreiben(gurke)
vor; schreiben(y)
vor; schreiben(x)
      """.trim)
    )
  ), 
//------------------------------------------------------
  Chapter(id="random-circles", head="Zufallszahlen", 
    contents=Seq(
      taskHead, Itemize(
        "Führe das Programm unten mehrmals aus. Was passiert?",
        "Was ist der kleinste und der größte mögliche Wert des Radius {:r}?",
        "Ändere {:r:} zu einer Zufallszahl zwischen 3 und 200.",
        "Zeichne 100 Kreise mit zufälligem Radius und zufälliger Position wie im Bild rechts."),
      OverlayImage("random-circles.png",x=21,y= -5,width=8),
      Code("""
// zufall(90) liefert eine Zufallszahl zwischen 0 und 99:
val r = zufall(90) + 10   

leeren; langsam(10); unsichtbar
schreiben("Radius = " + r)
kreis(r)
       """.trim,size=20)
    )
  ), 
//------------------------------------------------------
  Chapter(id="mix-colors", head="Mische Deine eigenen Farben", 
    contents=Seq(
      Itemize(
        "Mit {:Color:} kannst Du Deine eigenen Farben erstellen, z.B. {:Color(0, 70, 0):}", 
        "Die drei Parameter geben den Farbanteil von {/rot/}, {/grün/} und {/blau/} an.",
        "Du kannst auch einen vierten Parameter für die {/Deckkraft/} angeben.",
        "Alle Parameterwerte müssen zwischen 0 und 255 liegen." 
      ),
      taskHead, Para(
        "Probiere das folgende Programm. Ändere die Deckkraft."
      ),
      OverlayImage("color-circles.png",x=23,y= -2,width=7),
      Code("""
leeren; langsam(100)      

val olivgrün = Color(0,70,0)
val pistazie = Color(0,255,0,100)

füllfarbe(olivgrün); kreis(100)
füllfarbe(pistazie); vor(100); kreis(100)
       """.trim,size=16)
    )
  ), 
//------------------------------------------------------
  Chapter(id="color-picker", head="Probiere die Farbauswahl", template=TextWithImage("color-chooser-rgb_de.png",width=12),
    contents=Seq(
      taskHead, Itemize(
        """Mache einen Rechtsklick im Programmbearbeiter und gehe auf {:Farbe auswählen...:}""", 
        "Unter {*RGB*} kannst Du eine neue RGB-Farbe mischen.", 
        "Drücke OK und schaue in den Ausgabebereich. Hier werden die RGB-Werte für Rot, Grün und Blau angezeigt.",
        "Du kannst diese Werte verwenden, um mit der ausgewählten Farbe zu zeichnen {:stiftfarbe(Color(218, 103, 67)):}."
      )
    )
  ), 
//------------------------------------------------------
  Chapter(id="circle-of-circles", head="Zeichne Zufallskreise", 
    template=MultiColumn(2),
    contents=Seq(
      Code("""
def zufällig = zufall(256)
def zufallsfarbe = 
       Color(zufällig,10, zufällig,100) 

leeren; langsam(5)
grundfarbeUO(schwarz,weiß)
stiftbreite(6)

schleife(100) {
    stiftfarbe(zufallsfarbe)
    kreis(100)
    springen(20)
    rechts(35)
}
       """.trim,size=16), taskHead, Para("Probiere verschiedene zufällige Stift- und Hintergrund-Farben aus."),
       ColumnBreak, CenterImage("circle-of-circles.png",width=12)
    )
  ), 
//------------------------------------------------------
  Chapter(id="flower", head="Zeichne eine Blume", 
    contents=Seq(
      taskHead, 
      Para("Das folgende Programm zeichnet 100 Kreise mit zufälliger Farbe, zufälligem Radius und zufälliger Ausrichtung. " +
           "Probiere verschiedene Zufallszahlen-Grenzen und versuche zu erklären, was hierbei passiert."),
      OverlayImage("random-color-circles.png",x=22,y= -4,width=8.5),
      Code("""
leeren(); langsam(5)
stiftbreite(2)
schleife(100){
  stiftfarbe(Color(zufall(256),0,zufall(256)))
  füllfarbe(Color(zufall(256),0,zufall(256),zufall(100)+50))
  links(zufall(360))
  kreis(zufall(30)*4+10)
}
       """.trim,size=16)
    )
  ), 
//------------------------------------------------------
  Chapter(id="var", head="Erstelle eine Variable mit {:var:}", 
    contents=Seq(
      Para("Mit {:var:} kannst Du einen Namen mit einem Wert koppeln.", 
        "Einer Variablen kann man jederzeit einen neuen Wert zuweisen:"),
      Code("""
var gurke = 1
gurke = 1 + 1   //erst wird 1 + 1 ausgerechnet, gurke bekommt dann den Wert 2        
        """),
     taskHead, 
       Para("Probiere das Programm aus. Was schreibt die Kröte?"),
      Code("""
var i = 0

leeren
schleife(10){
  i = i + 1
  vor; schreiben(i)
}
       """.trim,size=14),
      hintHead, Itemize("Die Zuweisung {:i = i + 1:} weist {:i:} einen neuen Wert zu, der sich aus dem {/alten/} Wert von {:i:} plus {:1:} berechnet.")
    )
  ), 
//------------------------------------------------------
  Chapter(id="flowers", head="Zeichne viele Blumen", 
    contents=Seq(
      taskHead,       
      Itemize(
        "Schreibe eine Funktion mit dem Namen {:blume:}, die eine Blüte zeichnet und einen Stiel mit einem grünen Blatt, der in der Mitte der Blüte beginnt.",
        "Zeichne 5 Blumen nebeneinander."),
      OverlayImage("flowers.png",x=15, y= -7, width=16),    
      hintHead, 
      Para(
        "Du kannst die Blätter zeichnen mit {:bogen(radius, winkel):}. ",
        "Gib der Funktion {:blume:} zwei Parameter x und y und benutze {:springen(x,y):}",
        "Du kannst die Schleife fünfmal ausführen und die Position wie folgt ausrechnen:"
      ), 
      Code("""
var i = 0          
schleife(5){
  blume(600*i,0)
  i = i + 1        
}
""".trim,size=18) 
    )
  ), 
//------------------------------------------------------
  Chapter(id="costume", head="Gebe der Kröte ein neues Kostüm", 
    contents=Seq(
      taskHead,       
      Para("Lade die Mediendateien von Kojos Webseite:"),
      HRef("http://www.kogics.net/kojo-download#media", "www.kogics.net/kojo-download\\#media"), 
      Itemize("Entpacke die Datei {:scratch-media.zip:} und finde das Krabbenbild {:crab1-b.png:} im Ordner {:Media/Costumes/Animals:}", 
      "Speichere die Datei {:crab1-b.png:} in dem Ordner, in dem auch das Programm liegt.",
      "Ziehe der Kröte ein Krabbenkostüm an:"),
      OverlayImage("crab1-b.png",x=12, y= -2.5, width = 3),
      Code("""
leeren
kostüm("crab1-b.png")  
langsam(2000)
vor(1000) 
      """.trim,size=12),
      hintHead,
      Itemize("Du kannst auch eigene Bilder vom Typ {:.png:} oder {:.jpg:} verwenden.",
      """Wenn Du das Bild in einem anderen Ordner speichern wilst, musst Du den Dateipfad angeben, z.B. {:kostüm("~/Kojo/Media/Costumes/Animals/crab1-b.png"):}. Das Zeichen {:~:} steht für Dein Home-Verzeichnis.""")      
    )
  ), 
//------------------------------------------------------
 Chapter(id="new", head="Erstelle weitere Kröten mit {:new:}", 
   contents=Seq(
     Para("Du kannst viele neue Kröten mit {:new:} hinzufügen:"),
     Code("""
leeren
val k1 = new Kröte(100,100)  //die neue Kröte k1 startet auf der Position (100, 100)
val k2 = new Kröte(100, 50)  //die neue Kröte k2 startet auf der Position (100, 50)
k1.vor(100)
k2.rück(100)  //Kröte k2 nach unten
      """.trim, size=18),
      OverlayImage("new.png", x = 22, y = -2, width = 5),
      taskHead, Itemize(
        "Erzeuge drei Kröten übereinander.","Alle drei sollen nach links schauen."
      ),
      hintHead, Itemize(
        "{:k1:} und {:k2:} sind die {/Namen/} der neuen Kröten. Du kannst die Namen ändern, wenn Du willst.", 
        "Mit {:k1:} gefolgt von einem Punkt kannst Du der Kröte k1 einen Befehl geben: {:k1.links:}",
        "Mit dem Befehl {:unsichtbar:} kannst Du eine Kröte verbergen."
      )
   )
 ), 
//------------------------------------------------------
 Chapter(id="race", head="Krötenrennen", 
   template = TextWithImage("race.png", width = 12),
   contents=Seq(
     Para("Mit Hilfe von Zufallszahlen kannst Du die Kröten um die Wette rennen lassen."),
      taskHead, Itemize(
        "Lass die drei Kröten laufen.",
        "Wenn alle Kröten 10 mal nach vorn laufen, welche Kröte ist dann die Erste?"
      ),
      hintHead, Itemize(
        "Mit {:k1.vor(zufall(100) + 1):} bewegt sich k1 zwischen 1 und 100 Schritten weit."
      )
   )
 ), 
//------------------------------------------------------
  Chapter(id="if", head="Alternative mit {:if:}", 
    contents=Seq(
      Para("Mit einer {:if:}-Anweisung kann man den Computer aufgrund einer Bedingung zwischen zwei Alternativen wählen lassen."),
      Code("""
leeren; unsichtbar
if (true) schreiben("wahr") else schreiben("falsch")
     """.trim, size = 20),
      taskHead, Itemize(
        "Ändere die Bedingung {:true:} in {:false:} und prüfe, was die Kröte schreibt.",
        "Ändere die Bedingung in {:2 > 1:} und prüfe, was die Kröte schreibt.",
        "Ändere die Bedingung in {:2 < 1:} und prüfe, was die Kröte schreibt.",
        "Erkläre wie eine {:if:}-Anweisung funktioniert."
      ),
      hintHead, Itemize(
        "Hinter {:if:} kommt immer eine Bedingung in Klammern.",
        "Wenn die Bedingung nach dem {:if:} gleich {:true:} ist, wird der Befehl hinter der Bedingung ausgeführt.",
        "Wenn die Bedingung nach dem {:if:} gleich {:false:} ist, wird der Befehl hinter {:else:} ausgeführt."
      )
    )
  ), 
//------------------------------------------------------
  Chapter(id="if-input", head="Reagiere auf das, was der Nutzer macht", 
    contents=Seq(
      Code("""
ausgabeLeeren; setOutputTextFontSize(35)
val passwort = "gurke"
val frage     = "Wie lautet mein Passwort?"
val richtig      = "Der Safe ist offen!"
val fehler       = "Du kommst nicht hinein!"
val antwort = einlesen(frage)  //Warten auf die Antwort des Nutzers
val meldung = if (antwort == passwort) richtig else fehler
ausgeben(meldung)
     """.trim, size = 20),
      taskHead, Itemize(
        "Probiere das Programm aus. Du musst im Ausgabebereich unten etwas eingeben und mit der Eingabetaste bestätigen. Erkläre, was passiert.",
        "Ändere das Passwort. Was wird ausgegeben, wenn man es richtig und wenn man es falsch eingibt?",
        "Frage auch nach einem Benutzernamen und gib den Namen aus."
      )
    )
  ), 
//------------------------------------------------------
  Chapter(id="while", head="Nutze eine {:Solange:}-Schleife", 
    contents=Seq(
      Para("Mit {:schleifeSolange:} wiederholt der Computer Anweisungen so lange, wie eine Bedingung wahr ist."),
      Code("""
leeren; unsichtbar; langsam(250); ausgabeLeeren
var x = 200
schleifeSolange (x > 0) {  //prüfe die Bedingung vor jeder Wiederholung 
  vor(x); rechts
  schreiben(x) 
  x = x - 12
}
ausgeben("x ist jetzt: " + x)
     """.trim, size = 22),
      taskHead, Itemize(
        "Was wird im Ausgabebereich ausgegeben? Warum?",
        "Starte das Programm mit dem orangen Verfolgen-Symbol und prüfe jeden Schritt.",
        "Ziehe statt {:12:} den Wert {:20:} von der Variablen {:x:} ab. Erkläre, was passiert."
      )
    )
  ),
//------------------------------------------------------
  Chapter(id="guess-the-number", head="Zahlenraten", 
    contents=Seq(
      Code("""
val geheimnis = zufall(100)+1
var antwort = einlesen("Rate eine Zahl zwischen 1 und 100! ")
var überspringen = true

schleifeSolange (überspringen) {
    if (antwort.toInt < geheimnis)
      antwort = einlesen(antwort + " ist zu KLEIN, rate erneut!")
    else if (antwort.toInt > geheimnis)
      antwort = einlesen(antwort + " ist zu GROSS, rate erneut!")
    else if (antwort.toInt == geheimnis)
      überspringen = false
}
ausgeben(geheimnis + " ist die RICHTIGE Antwort!")
      """.trim,size=16),
      taskHead,
      Para("Führe eine Variable {:var anzahlVersuche = 0:} ein und stelle sicher, dass am Ende ausgegeben wird:", 
      "{:Richtige Antwort! Du hast es mit 5 Versuchen geschafft!:}")
    )      
  ), 
//------------------------------------------------------
  Chapter(id="multiply", head="Übe Multiplikation", 
    contents=Seq(
      Code("""
var anzahlRichtig = 0
val startZeit = System.currentTimeMillis / 1000
schleife(12) {
  val zahl1 = zufall(12)+1
  val zahl2 = zufall(12)+1
  val antwort = einlesen("Was ist " + zahl1 + "*" + zahl2 + "?")
  if (antwort == (zahl1 * zahl2).toString) {
    ausgeben("Richtig!")
    anzahlRichtig = anzahlRichtig + 1
  }
  else ausgeben("Falsch. Die richtige Antwort ist " + (zahl1 * zahl2))
}
val stoppZeit = System.currentTimeMillis / 1000
val sekunde = stoppZeit - startZeit
ausgeben("Du hast " + anzahlRichtig + " richtige Antworten in " + sekunde + " Sekunden.")
      """.trim,size=16),  
      taskHead,
      Para("Ändere das Programm so, dass man nur noch die Multiplikation mit 8 und 9 üben muss.")      
    )
  ), 
//------------------------------------------------------
  Chapter(id="vector", head="Speichere Tiere in einem Vektor", 
    contents=Seq(
      Code("""
var tiere = Vector("Elch", "Kuh", "Kaninchen", "Möve")  //Variable tiere enthält einen Vektor mit 4 Tieren
ausgeben("Das erste Tier im Vektor ist: " + tiere(0))     //Nummerierung der Werte im Vektor beginnt bei 0
ausgeben("Ein anderes Tier im Vektor ist:  " + tiere(1))
ausgeben("Der Vektor enthält so viele Tiere: " + tiere.size)
ausgeben("Das letzte Tier im Vektor ist:  " + tiere(tiere.size-1))

val s = zufall(tiere.size)   //berechnet eine Zufallszahl zwischen 0 und der Anzahl der Tiere minus 1
ausgeben("Ein zufälliges Tier: " + tiere(s))

tiere = tiere :+ "Kamel"    //fügt das Tier am Ende des Vektors ein
tiere = "Dromedar" +: tiere //fügt das Tier am Anfang des Vektors ein
tiere = tiere.updated(2, "Nilpferd")  //Ändert das dritte Tier (Nummer 2 im Vektor)
ausgeben("Alle Tiere rückwärts:")
tiere.foreach{ x => ausgeben(x.reverse) } //für alle x im Vektor: gebe x rückwärts aus
      """.trim,size=14),   
      taskHead,
      Itemize("Was gibt das Programm im Ausgabebereich aus? Erkläre was passiert.","Füge dem Vektor ein weiteres Tier hinzu.")
    )
  ), 
//------------------------------------------------------
  Chapter(id="translate", head="Übe Vokabeln", 
    contents=Seq(
      Code("""
val deutsch = Vector("Rechner", "Schildkröte", "Kreis")
val englisch = Vector("computer", "turtle", "circle")
var anzahlRichtig = 0
schleife(5) {
  val i = zufall(deutsch.size)
  val vokabel = deutsch(i)
  val antwort = einlesen("Was heisst " + vokabel + " auf Englisch?")
  if (antwort == englisch(i)) {
    ausgeben("Richtige Antwort!")
    anzahlRichtig = anzahlRichtig + 1
  } else {
    ausgeben("Falsche Antwort. Die richtige Antwort lautet: " + englisch(i))
  }
}
ausgeben("Du hast " + anzahlRichtig + " richtige Antworten gegeben.")
      """.trim,size=14),   
      taskHead,
      Itemize("Füge mehr Vokabeln ein.",
      "Übe die Übersetzung vom Englischen ins Deutsche.", 
      """Lasse den Nutzer einstellen, wie viele Vokabeln er üben will. Tipp: {:val anzahl = einlesen("Gebe die Anzahl ein:").toInt:}""")
    )
  ), 
//------------------------------------------------------
  Chapter(id="capitols", head="Hauptstadt-Spiel", 
    contents=Seq(
      Code("""
def hauptstadtSpiel = {
  ausgeben("Willkommen zum Hauptstadt-Spiel!")
  //Eine Map ist eine Abbildung, hier von Landesnamen zu Hauptstadtnamen:
  val stadt = Map("Schweden" ->"Stockholm", "Frankreich" -> "Paris", "Spanien" -> "Madrid")
  var länder = stadt.keySet //keySet liefert die Menge aller Schlüssel in einer Map 
  def zufallsLand = scala.util.Random.shuffle(länder.toVector).head
  schleifeSolange(!länder.isEmpty) {
    val land = zufallsLand
    val antwort = einlesen("Was ist die Hauptstadt von " + land + "?")
    ausgeben(s"Du sagst: $antwort")
    if (antwort == stadt(land)) {
      länder = länder - land  //entfernt das Land aus der Menge der Länder
      ausgeben("Richtig! Du hast noch " + länder.size + " Länder!")
    } else ausgeben(s"Falsch! Die Hauptstadt von $land beginnt mit ${stadt(land).take(2)}...")
  }
  ausgeben("DANKE FÜR DIE TEILNAHME! (Drücke ESC)")
}

toggleFullScreenOutput;  
setOutputBackground(black); setOutputTextColor(green); setOutputTextFontSize(30)
schleife(100)(ausgeben()) //fülle den Ausgabebereich mit 100 Leerzeilen
hauptstadtSpiel
//AUFGABE: Lege mehr Paare Land -> Stadt an. Messe die Zeit und zähle die Punkte.
""".trim,size=13)
    )
  ), 
//------------------------------------------------------
  Chapter(id="timer", head="Erstelle eine Stoppuhr mit {:object:}", 
    contents=Seq(
      Code("""
object Stoppuhr {
  def jetzt = System.currentTimeMillis  //liefert die aktuelle Zeit in Millisekunden
  var zeit = jetzt
  def rücksetzen = { zeit = jetzt }
  def gemessen = jetzt - zeit
  def zufallsWarten(min: Int, max: Int) =  //wartet zwischen min und max Sekunden
    Thread.sleep((zufall(max-min)+min)*1000)  //Thread.sleep(1000) wartet 1 Sekunde
}

ausgeben("Klicke in den Ausgabebereich und warte...")
Stoppuhr.zufallsWarten(3, 6)   //wartet zwischen 3 und 6 Sekunden
Stoppuhr.rücksetzen
einlesen("Drücke die Eingabetaste, so schnell wie Du kannst.")
ausgeben("Reaktionszeit: " + (Stoppuhr.gemessen/1000.0) + " Sekunden")
      """.trim,size=14),   
      Para("Mit {:object:} kannst Du alles sammeln, was zu einem Objekt gehört.",
        "Du kannst mit einem Punkt auf etwas im Objekt zugreifen: {:Stoppuhr.rücksetzen:}"),
      taskHead,
      Itemize("Probiere das Programm aus und messe Deine Reaktionszeit. Wie schnell bist Du?",
             "Nutze {:Stoppuhr:} in der Aufgabe {/Zahlenraten/} und füge die folgende Ausgabe hinzu: {:Richtig geraten! Du hast es mit 5 Versuchen in 32 Sekunden geschafft!:}")
    )
  ), 
//------------------------------------------------------
  Chapter(id="traffic-lights", head="Simuliere eine Ampel", 
    contents=Seq(
      OverlayImage("traffic-lights.png",x=22, y= -6, width=3),
      Code("""
def alleLöschen = draw(penColor(gray) * fillColor(black) -> PicShape.rect(130,40))
def licht(c: Color, h: Int) = penColor(noColor) * fillColor(c) * trans(20,h) -> PicShape.circle(15)
def leuchtetRot = draw(licht(red, 100))
def leuchtetGelb = draw(licht(yellow, 65))
def leuchtetGrün = draw(licht(green, 30))
def warte(sekunden: Int) = Thread.sleep(sekunden*1000)

leeren; unsichtbar  
schleifeSolange (true) { //eine unendliche Schleife
  alleLöschen
  leuchtetRot;  warte(3)
  leuchtetGelb; warte(1) 
  alleLöschen
  leuchtetGrün; warte(3)
  leuchtetGelb; warte(1)
}
      """.trim,size=14),       taskHead,
      Itemize("Wie wechselt die Ampel? Versuche zu erklären, was hierbei passiert.",
      "Ändere das Programm so, dass die Ampel doppelt so lang auf grün bleibt.")   
    )
  ),
//------------------------------------------------------
  Chapter(id="key-control", head="Steuere die Kröte mit der Tastatur", 
    template=MultiColumn(2),
    contents=Seq(
      Code("""
leeren; langsam(0)
activateCanvas()

animate { vor(1) }

onKeyPress {
  case Kc.VK_LEFT  => links(5)
  case Kc.VK_RIGHT => rechts(5)
  case Kc.VK_SPACE => vor(5)
  case t => 
    ausgeben("Unbekannte Taste: " + t)
}
      """.trim, size = 18),
      ColumnBreak,
      taskHead,
      Itemize(
        "Schreibe {:Kc.:} und drücke {:Strg+Alt+Leertaste:}. Dann kannst Du sehen, wie die verschiedenen Tasten heißen.", 
        "Befehle {:stiftRauf:} wenn man Pfeil nach oben drückt",
        "Befehle {:stiftRunter:} wenn man Pfeil nach unten drückt",
        "Befehle {:stiftfarbe(blau):} wenn man B drückt",
        "Befehle {:stiftfarbe(rot):} wenn man R drückt",
        "Erhöhe bzw. Verringere die Geschwindigkeit mit den Tasten + bzw. -"
      )   
    )
  ),
//------------------------------------------------------
  Chapter(id="mouse-control", head="Steuere die Kröte mit der Maus", 
    template=MultiColumn(2),
    contents=Seq(
      Code("""
leeren; langsam(100)
activateCanvas()

var zeichnen = true

onKeyPress {
  case Kc.VK_DOWN =>
    stiftRunter()
    zeichnen = true
  case Kc.VK_UP =>
    stiftRauf()
    zeichnen = false
  case t =>
    ausgeben("Unbekannte Taste: " + t)
}

onMouseClick { (x, y) =>
  if (zeichnen) gehen(x, y) else springen(x, y)
}
      """.trim, size = 16),
      ColumnBreak,
      taskHead,
      Itemize(
        "Befehle {:füllfarbe(schwarz):} wenn man F drückt",
        "Führe die Variable {:var füllen = true:} ein und mache bei Taste {:Kc.VK_F:}:"
      ), Code("""
      if (füllen) {
        füllfarbe(schwarz)
        füllen=false
      } else {
        füllfarbe(durchsichtig)
        füllen=true
      }
      """)   
    )
  ),
//------------------------------------------------------
  Chapter(id="object-bankaccount", head="Erstelle Dein eigenes Bankkonto", 
    template=MultiColumn(2),
    contents=Seq(
      Code("""
object meinKonto {
  val nummer = 123456
  var stand = 0.0
  def einzahlen(betrag: Bruchzahl) = {
    stand = stand + betrag
  }
  def abheben(betrag: Bruchzahl) = {
    stand = stand - betrag
  }
  def anzeigen() = {
    ausgeben("Kontonummer: " + nummer)
    ausgeben(" Kontostand: " + stand)
  }
}

meinKonto.anzeigen()
meinKonto.einzahlen(100)
meinKonto.anzeigen()
meinKonto.abheben(10)
meinKonto.anzeigen()
      """.trim, size = 16),
      ColumnBreak,
      taskHead,
      Itemize(
        "Was ist der Kontostand nach Ausführung des Programms? Erkläre was passiert.",
        "Sorge mit {:if:} dafür, dass nicht mehr Geld abgehoben werden kann, als auf dem Konto ist.",
        "Lege eine Konstante {:val maxBetrag = 5000:} an und sorge mit {:if:} dafür, dass man nicht mehr abheben darf als {:maxBetrag:}."
      )
    )
  ),
//------------------------------------------------------
  Chapter(id="class-bankaccount", head="Erstelle viele Objekte mit {:class:}", 
    template=MultiColumn(2),
    contents=Seq(Para("Wenn Du viele verschiedene Konten erstellen willst, benötigst Du eine Klasse. Mit {:new:} kannst Du davon neue Objekte erstellen. Jedes Objekt erhält eine eigene Kontonummer und Kontostand."),
      Code("""
class Konto(nummer: Ganzzahl) {
  private var stand = 0.0 //private bedeutet "versteckt"  
  def einzahlen(betrag: Bruchzahl) = {
    stand = stand + betrag 
  }
  def abheben(betrag: Bruchzahl) = { 
    stand = stand - betrag 
  }
  def anzeigen() = 
    ausgeben(s"Konto $nummer: $stand")
}

val konto1 = new Konto(12345) //neues Objekt erstellen
val konto2 = new Konto(67890) //Noch ein Objekt

konto1.einzahlen(99)
konto2.einzahlen(88)
konto1.abheben(57)
konto1.anzeigen
konto2.anzeigen
      """.trim, size = 13),
      ColumnBreak,
      taskHead,
      Itemize(
        "Was ist der Stand der beiden Konten, nachdem das Programm ausgeführt wurde? Erkläre was passiert.",
        "Erstelle weitere Konto-Objekte und zahle Beträge ein und hebe Beträge ab.",
        "Füge einen Klassenparameter {:name: Text:} hinzu, der den Namen des Kontoinhabers aufnehmen soll, wenn Objekte erstellt werden.",
        "Sorge dafür, dass auch {:name:} ausgegeben wird, wenn {:anzeigen:} ausgeführt wird.",
        "Was passiert, wenn Du das befiehlst: {:konto1.stand = 10000000 } ?")
    )
  ),
//------------------------------------------------------
  Chapter(id="eliza", head="Sprich mit Deinem Computer", 
    contents=Seq(
      Code("""
setOutputBackground(black); setOutputTextFontSize(30); setOutputTextColor(green)
ausgeben("Schreibe interessante Antworten, auch wenn die Fragen seltsam sind. Verabschiede Dich mit 'Tschüss'")
def zufällig(xs: Vector[String]) = scala.util.Random.shuffle(xs).head
val aufforderung = Vector("Was bedeutet", "Gefällt Dir", "Wozu brauchen wir", "Erzähl mir mehr über")
var antwort = "?"
val eröffnung = "Worüber möchtest Du reden?"
var worte = Vector("Nabelschnur", "Ketchupeis", "Weihnachtsmann", "Kissenbezüge")
schleifeSolange(antwort != "tschüss") {
  val t = if (antwort == "?") eröffnung
  else if (antwort == "nein") "Nee."
  else if (antwort == "ja") "Nun ja."
  else if (antwort.length < 4) "Oh..."
  else zufällig(aufforderung) + " " + zufällig(worte) + "?"
  antwort = einlesen(t).toLowerCase
  worte = worte ++ antwort.split("[ ,;:.!?]").toList.filter(_.length > 3)
}
ausgeben("Danke für das Gespräch! Jetzt habe ich diese Worte gelernt: " + worte)

//Aufgabe:
// (1) Probiere das Programm aus und versuche herauszufinden, wie es funktioniert.
// (2) Wann wird die Solange-Schleife beendet?
// (3) Fülle weitere Sätze und Worte in die Vektoren aufforderung und worte.
// (4) Erweitere die Reaktion auf kurze Antworten über "nein" und "ja" hinaus.
      """.trim, size = 13)
    )
  ),
//------------------------------------------------------
  Chapter(id="modify-pong", head="Verändere das Tischtennis-Spiel", 
    template=TextWithImage("pong.png"),
    contents=Seq(
      taskHead,
      Itemize(
      "Wähle im Menü Beispiele > Animationen und Spiele > Tischtennis. Probiere es aus!",
      "Du kannst es steuern mit: Pfeil nach oben/unten und A/Z.",
      "Drücke ESC um das Spiel zu beenden und untersuche das Programm. Es nutzt die englischen Kojo-Befehle.",
      "Ändere das Programm so, dass man mit Y statt Z den linken Schläger nach unten bewegen kann. Dann ist das Spiel bei einer deutschen Tastatur bequemer für den linken Spieler.",
      "Ändere das Programm so, dass der Ball größer wird.",
      "Ändere das Spielfeld zu einem Tennistisch mit grünem Hintergrund, weißen Linien und einem gelben Ball.")   
    )
  )  
    
)

//********************
println("********************* input:"+chapters)
latexgen.make(chapters, "kojobook/tex/body-de.tex")

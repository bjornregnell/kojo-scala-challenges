//Solution for challenge "Zeichne viele Blumen" with german API
def blüte = schleife(100) {
  stiftbreite(2)
  stiftfarbe(Color(zufall(256), 0, zufall(256)))
  füllfarbe(Color(zufall(256), 0, zufall(256), zufall(100) + 50))
  links(zufall(360))
  kreis(zufall(30) * 4 + 10)
}

def stiel = { füllfarbe(durchsichtig); stiftbreite(10); vor(500) }
def blatt = {
  füllfarbe(Color(0, 70, 0)); stiftfarbe(grün);
  winkel(70); bogen(180, 100)
}

def blume(x: Ganzzahl, y: Ganzzahl) = {
  springen(x, y); blatt
  springen(x, y); winkel(90); stiel
  blüte

}

leeren(); langsam(5); unsichtbar; zoom(0.5)
var i = 0
schleife(5) {
  blume(600 * i, 0)
  i = i + 1
}



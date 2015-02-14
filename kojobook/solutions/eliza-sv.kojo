setOutputBackground(black); setOutputTextFontSize(30); setOutputTextColor(green)
utdata("Skriv intressanta svar även om frågorna är konstiga. Avsluta med 'hej då'")
def slumpa(xs: Vector[String]) = scala.util.Random.shuffle(xs).head
val ledtexter = Vector("Vad betyder", "Gillar du", "Varför behövs", "Berätta mer om")
var svar = "?"
val öppning = "Vad vill du prata om?"
var ord = Vector("navelludd", "ketchupglass", "jultomten", "örngott") 
while (svar != "hej då") {
  val t = if (svar == "?") öppning 
    else if (svar == "nej") "Nähä." 
    else if(svar == "ja") "Jaha." 
    else if (svar.length < 4) "Jasså..." 
    else slumpa(ledtexter) + " " + slumpa(ord) + "?"
  svar = indata(t).toLowerCase
  ord = ord ++ svar.split(" ").toList.filter(_.length > 3) 
} 
utdata("Tack för pratstunden! Jag kan nu dessa ord:" + ord)

//Uppdrag:
// (1) Prova programmet och försök att förklara vad som händer.
// (2) När avslutas while-loopen?
// (3) Lägg till fler strängar i vektorerna ledtexter och ord
// (4) Lägg till fler bra svar på några korta ord utöver "nej" och "ja"
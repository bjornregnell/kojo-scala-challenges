def huvudstadsspelet = {
  println("Välkommen till Huvudstadsspelet!")
  val stad = Map("Sverige" ->"Stockholm", "Danmark" -> "Köpenhamn", "Skåne" -> "Malmö")
  var länderKvar = stad.keySet //keySet ger en mängd av alla nycklar i en Map 
  while(!länderKvar.isEmpty) { //så länge inte länderKvar är tom
    val land = scala.util.Random.shuffle(länderKvar.toVector).head  //blanda och ta första
    val svar = indata("Vad heter huvudstaden i " + land + "?")
    utdata(s"Du skrev: $svar")
    if (svar == stad(land)) {
      utdata("Rätt svar! Du har " + länderKvar.size + " länder kvar!")
      länderKvar = länderKvar - land  //ta bort land ur mängden länderKvar
    } else {
      utdata(s"Fel svar. Huvudstaden i $land börjar på ${stad(land).take(2)}...")
    }
  }
  utdata(s"TACK FÖR ATT DU KÄMPADE! (Tryck ESC)")
}

toggleFullScreenOutput;  
setOutputBackground(black);setOutputTextColor(green); setOutputTextFontSize(30)
upprepa(100)(utdata("")) //scrolla utdafönstret med 100 blanka rader

huvudstadsspelet


// *** UPPDRAG: (1) Lägg till fler par: land -> stad  (2) Mät tid och räkna poäng. 
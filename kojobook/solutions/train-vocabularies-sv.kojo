val svenska = Vector("dator", "sköldpadda", "cirkel")
val engelska = Vector("computer", "turtle", "circle")
var antalRätt = 0
upprepa(5) {
  val s = slumptal(3)
  val glosa = svenska(s)
  val svar = indata("Vad heter " + glosa + " på engelska?")
  if (svar == engelska(s)) {
    utdata("Rätt svar!")
    antalRätt = antalRätt + 1
  } else {
    utdata("Fel svar. Rätt svar är: " + engelska(s))
  }
}
utdata("Du fick" + antalRätt + " rätt.")

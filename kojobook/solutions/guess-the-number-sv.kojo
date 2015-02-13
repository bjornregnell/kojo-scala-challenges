val hemlis = slumptal(100)+1
var svar = indata("Gissa ett tal mellan 1 och 100! ")
var fortsätt = true

while (fortsätt) {
    if (svar.toInt < hemlis)
      svar = indata(svar + " är för LITET, gissa igen!")
    else if (svar.toInt > hemlis)
      svar = indata(svar + " är för STORT, gissa igen!")
    else if (svar.toInt == hemlis)
      fortsätt = false
}
utdata(hemlis + " är RÄTT svar!")

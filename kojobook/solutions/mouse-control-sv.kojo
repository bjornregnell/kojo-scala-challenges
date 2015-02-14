sudda; sakta(100)
activateCanvas()

var rita = true
var fyllNästa = true

onKeyPress { k =>
  k match {
    case Kc.VK_DOWN => 
      penDown()
      rita = true
    case Kc.VK_UP => 
      penUp()
      rita = false
    case Kc.VK_F => 
      if (fyllNästa) {fyll(svart); fyllNästa=false}
      else {fyll(genomskinlig); fyllNästa=true}
    case _ => 
      utdata("Annan tangent: " + k)
  }
}

onMouseClick { (x, y) =>
  if (rita)
    moveTo(x, y)
  else
    jumpTo(x, y)
}

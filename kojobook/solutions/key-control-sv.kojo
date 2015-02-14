sudda; sakta(0)
activateCanvas()

animate {fram(1) }

onKeyPress { k =>
  k match {
    case Kc.VK_UP =>   
    case Kc.VK_LEFT =>   vänster(5)
    case Kc.VK_RIGHT =>  höger(5)
    case Kc.VK_SPACE =>  fram(5)
    case _ => 
      utdata("Other key: " + k)
  }
}
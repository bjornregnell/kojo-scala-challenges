val height = Map(red -> 100, yellow -> 65, green -> 30)
def light(c: Color) = penColor(noColor) * fillColor(c) * trans(20,height(c)) -> PicShape.circle(15)
def lightHouse = penColor(gray) * fillColor(black) -> PicShape.rect(130,40)
def wait(sec: Int) = Thread.sleep(sec*1000)
val phases = Vector(Set(red)->3,Set(red,yellow)->1,Set(green)->3,Set(green,yellow)->1)
def lightSequence = phases.foreach{ case (xs,sec) => 
  draw(lightHouse)
  xs.foreach(col => draw(light(col)))
  wait(sec)
}

cleari; zoom(2)
while(true){ lightSequence }
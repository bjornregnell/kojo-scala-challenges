def spring (p : Padda) = p.fram(slumptal(100) + 1) 

sudda
osynlig
val p1 = new Padda(100,100)
p1.vänster
val p2 = new Padda(100, 50)
p2.vänster
val p3 = new Padda(100, 0)
p3.vänster
upprepa(10){
  spring(p1)
  spring(p2)
  spring(p3)
}

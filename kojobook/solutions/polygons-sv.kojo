def polygon(n: Heltal, sidlängd: Heltal) = upprepa(n){
  fram(sidlängd)
  vänster(360.0/n)
}
def snurra(n: Heltal, vinkel: Heltal, sidlängd: Heltal) = 
  upprepa(360/vinkel){ polygon(n, sidlängd); vänster(vinkel) }

sudda; sakta(5)
snurra(7, 10, 100)
def krona = upprepa(100){
  bredd(2)
  färg(Color(slumptal(256),0,slumptal(256)))
  fyll(Color(slumptal(256),0,slumptal(256),slumptal(100)+50))
  vänster(slumptal(360))
  cirkel(slumptal(30)*4+10)
}

def stjälk = {fyll(genomskinlig); bredd(10); fram(500)}
def blad = {
   fyll(Color(0,70,0)); färg(grön); 
   sättVinkel(70); båge(180,100)}

def blomma(x: Heltal, y: Heltal) = {
  hoppaTill(x, y);  blad
  hoppaTill(x, y); sättVinkel(90); stjälk
  krona
  
}

sudda(); sakta(5); osynlig; zoom(0.5)
var i = 0
upprepa(5){
  blomma(600*i,0)
  i = i + 1
}



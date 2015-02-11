def kvadrat(x: Heltal, y: Heltal, sidlängd: Heltal) = {
  hoppaTill(x, y)
  upprepa(4) { fram(sidlängd); höger }
}
def huvud(x: Heltal, y: Heltal) = { fyll(rosa); färg(röd); kvadrat(x, y, 200) }
def öga(x: Heltal, y: Heltal) = { fyll(vit); färg(svart); kvadrat(x, y, 40) }
def pupill(x: Heltal, y: Heltal) = { fyll(svart); färg(svart); kvadrat(x, y, 10) }
def näsa(x: Heltal, y: Heltal) = { fyll(blå); färg(genomskinlig); kvadrat(x, y, 30) }
def mun(x: Heltal, y: Heltal) = { bredd(10); fyll(svart); färg(röd); kvadrat(x, y, 40) }

sudda; sakta(20); osynlig
huvud(0, 0)
öga(40, 100); pupill(60, 100)
öga(120, 100); pupill(130, 100)
näsa(85, 70)
mun(80, 20)


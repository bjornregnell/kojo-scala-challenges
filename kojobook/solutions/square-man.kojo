def init = {sudda; sakta(20); osynlig}
def kvadrat(sidlängd : Heltal) = upprepa(4){fram(sidlängd); höger}
def huvud = {fyll(rosa); kvadrat(200)}
def öga = {fyll(vit); färg(svart); kvadrat(40)}
def pupill = {fyll(svart); färg(svart); kvadrat(10) }
def näsa = {färg(genomskinlig); fyll(blå); kvadrat(30)}
def mun = {bredd(10); fyll(svart); färg(röd); kvadrat(40)}

init
huvud
hoppaTill(40,100);  öga
hoppaTill(60,100);  pupill
hoppaTill(120,100); öga 
hoppaTill(130,100); pupill
hoppaTill(85,70);   näsa
hoppaTill(80,20);   mun


call scala -cp bin kojobook/source.scala
cd kojobook/tex
if NOT exist book-sv.aux call xelatex book-sv.tex
if exist book-sv.aux call xelatex book-sv.tex
start book-sv.pdf
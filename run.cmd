call scala -cp bin kojobook/source.scala
cd kojobook/tex
if NOT exist book.aux call xelatex book.tex
if exist book.aux call xelatex book.tex
start book.pdf
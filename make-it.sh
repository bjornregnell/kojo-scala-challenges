set -o errexit
set -x
scala -cp ./bin kojobook/book-it.scala
cd kojobook/tex
xelatex book-it.tex
xelatex book-it.tex
xelatex book-it.tex
evince book-it.pdf

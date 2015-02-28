set -o errexit
set -x
scala -cp bin kojobook/book-en.scala
cd kojobook/tex
xelatex book-en.tex
evince book-en.pdf

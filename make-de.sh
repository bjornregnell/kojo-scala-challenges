#!/bin/sh
#
set -o errexit -o nounset 
set -o xtrace
LANG_CODE=de
scala -cp bin kojobook/book-$LANG_CODE.scala
cd kojobook/tex
pdflatex book-$LANG_CODE.tex
xdg-open book-$LANG_CODE.pdf

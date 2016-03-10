call scala -cp bin kojobook/book-it.scala

if %ERRORLEVEL% NEQ 0 goto error

cd kojobook/tex

call pdflatex book-it.tex
if %ERRORLEVEL% NEQ 0 goto error

start book-it.pdf

:error
echo *** Error level: %ERRORLEVEL%
:end
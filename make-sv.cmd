call scala -cp bin kojobook/book-sv.scala

if %ERRORLEVEL% NEQ 0 goto error

cd kojobook/tex

call pdflatex book-sv.tex
if %ERRORLEVEL% NEQ 0 goto error

start book-sv.pdf

:error
echo *** Error level: %ERRORLEVEL%
:end
call scala -cp bin kojobook/book-de.scala

if %ERRORLEVEL% NEQ 0 goto error

cd kojobook/tex

call xelatex book-de.tex
if %ERRORLEVEL% NEQ 0 goto error

start book-de.pdf

:error
echo *** Error level: %ERRORLEVEL%
:end
call scala -cp bin kojobook/book-en.scala

if %ERRORLEVEL% NEQ 0 goto error

cd kojobook/tex

call xelatex book-en.tex
if %ERRORLEVEL% NEQ 0 goto error

start book-en.pdf

:error
echo *** Error level: %ERRORLEVEL%
:end
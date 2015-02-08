call scala -cp bin kojobook/book-sv.scala
if %ERRORLEVEL% NEQ 0 goto error

REM call scala -cp bin kojobook/book-de.scala
REM if %ERRORLEVEL% NEQ 0 goto error

cd kojobook/tex

call xelatex book-sv.tex
if %ERRORLEVEL% NEQ 0 goto error

REM call xelatex book-de.tex
REM if %ERRORLEVEL% NEQ 0 goto error

start book-sv.pdf
REM start book-de.pdf

:error
echo *** Error level: %ERRORLEVEL%
:end
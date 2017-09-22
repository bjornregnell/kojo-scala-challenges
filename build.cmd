del /Q bin\*
call scalac src\* -d bin

REM to be sure flush the old classes in the fast scala compiler cache:
call fsc -reset
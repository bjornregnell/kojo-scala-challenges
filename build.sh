#Treat undefined variables and unhandled errors as error:
set -ue
set -x
rm -rf bin
mkdir bin
scalac src/* -d bin

# to be sure flush the old classes in the fast scala compiler cache:
# fsc -reset

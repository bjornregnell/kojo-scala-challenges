# scaboo #

A Scala book generator.

### What is this repository for? ###

* Write books using Scala case classes and generate latex and TODO: html

### How do I get set up? ###

* Dependencies: You need to have these things installed on your box
    * [Scala v2.11.x](http://scala-lang.org/download/) with the `scala` and `scalac` commands on your path.
    * [texlive](https://www.tug.org/texlive/acquire-netinstall.html) with the `xelatex` command on your path.

0. Clone this repo e.g. using Sourcetree (press the download icon next to the "Overview heading")
0. Build scaboo into `bin` using the scala compiler with these scripts
    * Windows open powershell and run `./build.cmd`
    * Unix-like: TODO `build.sh`
0. Generate the kojobook for Swedish by running `make-sv.cmd` or TODO `make-sv.sh`

### Contributions  ###

Pull requests are welcome. Contact bjorn.regnell@cs.lth.se
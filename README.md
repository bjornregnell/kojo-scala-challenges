# kojo-scala-challenges  #

This repo contains the the booklet "Challenges with Kojo" (called kojobook) that is used to teach programming in primary school using [Scala](http://scala-lang.org/) and [Kojo](http://www.kogics.net/kojo). The booklet is available in several languages, currently: 
* [English](https://github.com/bjornregnell/kojo-scala-challenges/blob/master/kojobook/tex/book-en.pdf)
  * **NEWS!** Updated challenges in English are now available as a [slide show, download from here](https://docs.google.com/presentation/d/1K94dJjU9vCORqtKtEt91xpWtneXIsAOINBqlERpRNH8/edit?usp=sharing). Many thanks to contributor Lalit Pant!
* [Swedish](https://github.com/bjornregnell/kojo-scala-challenges/blob/master/kojobook/tex/book-sv.pdf)
* [German](https://github.com/bjornregnell/kojo-scala-challenges/blob/master/kojobook/tex/book-de.pdf)
* [Italian](https://github.com/bjornregnell/kojo-scala-challenges/blob/master/kojobook/tex/book-it.pdf)

Contributions are welcome! Please contact [bjorn.regnell@cs.lth.se](mailto:bjorn.regnell@cs.lth.se) if you want to contribute.


The repo also contains the code of the simple scala booklet generator that generates tex booklets from scala case classes that is used to generate kojobook.

You find the source of each book in the [https://github.com/bjornregnell/kojo-scala-challenges/tree/master/kojobook] dir andthen compilet booklets in [kojobook/tex](https://github.com/bjornregnell/kojo-scala-challenges/tree/master/kojobook/tex).

The licence is: [CC BY-NC-SA 4.0](http://creativecommons.org/licenses/by-nc-sa/4.0/)

The repo was originally hosted [at bickbucket](https://bitbucket.org/bjornregnell/scaboo) but is now moved here and the bitbucket repo is in archived mode.

The book is generated by a scala program called `scaboo` that generates latex which compiles to pdf.

TODO: Update to Scala 3 (The courrent source is in old scala 2.11)

### What is scaboo? ###

The scaboo api consists of a simple document model based on Scala case classes. A minimalistic latex generator is provided that is invoked via a scala script. You write your document using Scala case classes and combined with a latex-wrapper document you create your book. The kojobook directory includes an example of how scaboo can be used. The aim is to, in the future, develop scaboo into a latex/pdf AND static-site-html-generator.

### How do I get set up? ###

* Dependencies: You need to have these things installed on your box
    * [Scala v2.11.x](http://scala-lang.org/download/) with the `scala` and `scalac` commands on your path.
    * [texlive](https://www.tug.org/texlive/acquire-netinstall.html) with the `pdflatex` command on your path. You should do a full texlive install to make sure that all packages and fonts are available. The kojobook example uses the helvet and inconsolata fonts. You can install inconsolata from here: [incosolata at ctan](http://www.ctan.org/tex-archive/fonts/inconsolata/)

0. Download zip or clone or fork this repo.
1. Build scaboo into `bin` using the scala compiler with these scripts
   * Linux/MacOS:  `source build.sh`
   * Windows: open powershell and run `./build.cmd` 
3. Generate the kojobook example for Swedish by running `make-sv.cmd` or TODO `make-sv.sh`

### Contributions  ###

Pull requests on the api, scripts or the kojobook challenges are welcome. Contact bjorn.regnell@cs.lth.se

If you want to contribute to the kojobook translation, you need to create a file similar to book-en.scala for your language. Contact bjorn.regnell@cs.lth.se

* Contributors: Björn Regnell, Lalit Pant, Sandra Nilsson, Maja Johansson, Simone Strippgen, Christoph Knabe, Massimo Maria Ghisalberti, Florian Schlüter.

### Set-Up Experiences on Linux Mint 17.2 (based on Ubuntu 14.04.3 LTS) for German Book ###

Necessary for minimal TeX installation (2017-09-19):

1. Install `scala` and `scalac` commands for Scala 2.11.11 manually, as the Debian package `scala` still has version 2.9.
2. Install Ubuntu package `texlive-latex-base` by Synaptic, as necessary for command `pdflatex`.
3. Install Ubuntu package `texlive-lang-german` in order to avoid error "language definition file german.ldf was not found".
4. Install Ubuntu package `texlive-fonts-recommended` in order to avoid error "mktexnam: Could not map source abbreviation  for ecrm1200.". The package contains among others "ec -- Computer modern fonts in T1 and TS1 encodings".
5. Install Ubuntu package `texlive-latex-extra` in order to avoid error "File 'titling.sty' not found"
6. Install Ubuntu package `texlive-fonts-extra` in order to avoid error "File 'inconsolata.sty' not found"
7. Install Ubuntu package `latex-xcolor`in order to avoid error "File 'xcolor.sty' not found."
8. Install Ubuntu package `pgf` in order to avoid error "File 'tikz.sty' not found."
9. Needs to run `pdflatex` or the wrapping `make-de.sh` two times in order to prepare/include the Table of Contents.

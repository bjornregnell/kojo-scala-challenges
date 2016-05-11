# scaboo and kojobook #
 
This repo contains the the booklet "Challenges with Kojo" (called kojobook) that is used to teach programming in primary school using [Scala](http://scala-lang.org/) and [Kojo](http://www.kogics.net/kojo). The booklet is translated to several languages and more translation contributions are welcome! Please contact [bjorn.regnell@cs.lth.se](mailto:bjorn.regnell@cs.lth.se) if you want to contribute.

The repo also contains the code of the simple scala booklet generator that generates tex booklets from scala case classes that is used to generate kojobook.

You can download the compiled kojobook in pdf by clicking on Source to the right and navigate to kojobook/tex and download the .pdf file for you language of choice.

The licence is: [CC BY-NC-SA 4.0](http://creativecommons.org/licenses/by-nc-sa/4.0/)

### What is scaboo? ###

The scaboo api consists of a simple document model based on Scala case classes. A minimalistic latex generator is provided that is invoked via a scala script. You write your document using Scala case classes and combined with a latex-wrapper document you create your book. The kojobook directory includes an example of how scaboo can be used. The aim is to, in the future, develop scaboo into a latex/pdf AND static-site-html-generator.

### How do I get set up? ###

* Dependencies: You need to have these things installed on your box
    * [Scala v2.11.x](http://scala-lang.org/download/) with the `scala` and `scalac` commands on your path.
    * [texlive](https://www.tug.org/texlive/acquire-netinstall.html) with the `pdflatex` command on your path. You should do a full texlive install to make sure that all packages and fonts are available. The kojobook example uses the helvet and inconsolata fonts. You can install inconsolata from here: [incosolata at ctan](http://www.ctan.org/tex-archive/fonts/inconsolata/)

0. [Download](https://bitbucket.org/bjornregnell/scaboo/downloads), clone or [fork](https://bitbucket.org/bjornregnell/scaboo/fork) this repo e.g. using [Sourcetree](http://www.sourcetreeapp.com/)
0. Build scaboo into `bin` using the scala compiler with these scripts
    * Windows: open powershell and run `./build.cmd`
    * Unix-like: `source build.sh`
0. Generate the kojobook example for Swedish by running `make-sv.cmd` or TODO `make-sv.sh`

### Contributions  ###

Pull requests on the api, scripts or the kojobook challenges are welcome. Contact bjorn.regnell@cs.lth.se

If you want to contribute to the kojobook translation, you need to create a file similar to book-en.scala for your language. Contact bjorn.regnell@cs.lth.se

* Contributors: * Björn Regnell, Lalit Pant, Sandra Nilsson, Maja Johansson, Simone Strippgen, Christoph Knabe, Massimo Maria Ghisalberti.

### Set-Up Experiences on Lubuntu 14.04 for German Book ###

Trying out what is necessary for minimal TeX installation.

1. Installed `scala` and `scalac` commands manually, as the Debian package `scala` still has version 2.9.
2. Installed Ubuntu package `texlive-latex-base` by Synaptic, as necessary for command `pdflatex`.
3. Installed Ubuntu package `texlive-lang-german` in order to avoid error "language definition file german.ldf was not found".
4. Installed Ubuntu package `texlive-fonts-recommended` in order to avoid error "mktexnam: Could not map source abbreviation  for ecrm1200.". The package contains among others "ec -- Computer modern fonts in T1 and TS1 encodings".
5. Installed Ubuntu package `texlive-latex-extra` in order to avoid error "File `titling.sty' not found"
6. Installed Debian package `texlive-fonts-extra` in order to avoid error "File `inconsolata.sty' not found"
7. Needs to run `pdflatex` or the wrapping `make-de.sh` two times in order to prepare/include the Table of Contents.
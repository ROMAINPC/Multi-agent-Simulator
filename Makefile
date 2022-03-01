# Ensimag 2A POO - TP 2015/16
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#
#  3) Les librairies déjà compilées (*.jar) se trouvent dans le repertoire lib
#     Ici gui.jar
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

all: balls GOL IG SM UB birds humans HB

# Compilation
testGUI:
	javac -d bin -classpath lib/gui.jar -sourcepath src src/TestGUI.java

balls:
	javac -d bin -classpath lib/gui.jar -sourcepath src src/games/launchers/BouncingBallsLauncher.java

GOL:
	javac -d bin -classpath lib/gui.jar -sourcepath src src/games/launchers/GameOfLifeLauncher.java

IG:
	javac -d bin -classpath lib/gui.jar -sourcepath src src/games/launchers/ImmigrationGameLauncher.java

SM:
	javac -d bin -classpath lib/gui.jar -sourcepath src src/games/launchers/SchellingModelLauncher.java

UB:
	javac -d bin -classpath lib/gui.jar -sourcepath src src/games/launchers/UnruledBoidsLauncher.java

birds:
	javac -d bin -classpath lib/gui.jar -sourcepath src src/games/launchers/BirdsLauncher.java

humans:
	javac -d bin -classpath lib/gui.jar -sourcepath src src/games/launchers/HumansLauncher.java

HB:
	javac -d bin -classpath lib/gui.jar -sourcepath src src/games/launchers/HumansAndBirdsLauncher.java

# Execution JVM
exeGUI:
	java -classpath bin:lib/gui.jar TestGUI

exeBalls:
	java -classpath bin:lib/gui.jar games/launchers/BouncingBallsLauncher

exeGOL:
	java -classpath bin:lib/gui.jar games/launchers/GameOfLifeLauncher

exeIG:
	java -classpath bin:lib/gui.jar games/launchers/ImmigrationGameLauncher

exeSM:
	java -classpath bin:lib/gui.jar games/launchers/SchellingModelLauncher

exeUB:
	java -classpath bin:lib/gui.jar games/launchers/UnruledBoidsLauncher

exeBirds:
	java -classpath bin:lib/gui.jar games/launchers/BirdsLauncher

exeHumans:
	java -classpath bin:lib/gui.jar games/launchers/HumansLauncher

exeHB:
	java -classpath bin:lib/gui.jar games/launchers/HumansAndBirdsLauncher

clean:
	rm -rf bin/*.class


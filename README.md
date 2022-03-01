# Simulation Orientée-Objet de systèmes multiagents
**TPTL Programmation Orientée Objet**

## Contenu du dépôt
- `src/`: Contient les sources des projets
- `src/launchers/`: Contient différents exécutables de jeux
- `lib/gui.jar`: Archive Java contenant les classes de l'interface graphique. Voir un exemple d'utilisation dans TestGUI.java
- `doc_gui/`: La documentation (API) des classes de l'interface graphique contenues dans gui.jar. Point d'entrée: index.html
- `Makefile`: Compilation en ligne de commande. L'utilisation d'un IDE reste possible grâce aux fichiers `.project` et `.classpath`.
- `bin/`: Dossier généré à la compilation.

## Liste des exécutables
Disponibles dans le dossier `src/launchers/`
- [BouncingBallsLauncher](src/games/launchers/BouncingBallsLauncher.java) Balles qui rebondissent
- [GameOfLifeLauncher](src/games/launchers/GameOfLifeLauncher.java) Jeu de la vie
- [ImmigrationGameLauncher](src/games/launchers/ImmigrationGameLauncher.java) Jeu de l'immigration (pas trop compris ce que ça doit faire)
- [SchellingModelLauncher](src/games/launchers/SchellingModelLauncher.java) Modèle de Schelling (formation de ghettos)
- [UnruledBoidsLauncher](src/games/launchers/UnruledBoidsLauncher.java) Boids individuels se déplaçant
- [BirdsLauncher](src/games/launchers/BirdsLauncher.java) Nuée d'oiseaux
- [HumansLauncher](src/games/launchers/HumansLauncher.java) Humains se baladant
- [HumansAndBirdsLauncher](src/games/launchers/HumansAndBirdsLauncher.java) Humains se baladant dans une nuée d'oiseaux


### Exécutions par Makefile
**Compilation**
```
make
```
**Exécutions**
```
make exeBalls 
make exeGOL
make exeIG
make exeSM
make exeUB
make exeBirds
make exeHumans
make exeHB
```

**Fin**
```
make clean
```
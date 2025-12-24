PROJET GALAGA - Jeu d'arcade inspiré de Galaga
================================================

AUTEURS:
--------
Développé avec GitHub Copilot
Date: Décembre 2025

FONCTIONNALITÉS RÉALISÉES:
---------------------------

1. SYSTÈME DE JEU COMPLET
   ✓ Écran de démarrage avec instructions
   ✓ Système de pause (touche P)
   ✓ 2 niveaux jouables
   ✓ Écrans de transition entre niveaux
   ✓ Écran de game over
   ✓ Écran de victoire
   ✓ Système de restart (touche R)

2. SYSTÈME DE SPRITES
   ✓ Chargement de sprites depuis fichiers .spr (ASCII art)
   ✓ Rendu graphique avec couleurs multiples
   ✓ Sprites pour joueur et 3 types d'ennemis

3. JOUEUR
   ✓ Vaisseau spatial avec sprite ship.spr
   ✓ Déplacement horizontal (flèches gauche/droite)
   ✓ Tir de projectiles (barre d'espace)
   ✓ Système de 3 vies
   ✓ Contraintes de bordures d'écran

4. ENNEMIS
   ✓ 3 types d'ennemis:
     - Bee (abeille): 100 points, mouvement oscillant
     - Butterfly (papillon): 200 points, mouvement en vague
     - Moth (mite): 300 points, mouvement lent et large
   ✓ Tir automatique des ennemis
   ✓ Patterns de mouvement uniques par type
   ✓ Sprites individuels par type

5. SYSTÈME DE COMBAT
   ✓ Collision projectiles joueur vs ennemis
   ✓ Collision projectiles ennemis vs joueur
   ✓ Collision directe ennemis vs joueur
   ✓ Système de points par ennemi détruit

6. SYSTÈME DE NIVEAUX
   ✓ Chargement de niveaux depuis fichiers .lvl
   ✓ Niveau 1: 44 ennemis (4 Moths, 16 Butterflies, 24 Bees)
   ✓ Niveau 2: 44 ennemis
   ✓ Progression automatique entre niveaux
   ✓ Écran de transition

7. INTERFACE UTILISATEUR
   ✓ Affichage du score en temps réel
   ✓ Compteur de vies
   ✓ Indicateur de niveau
   ✓ High score sauvegardé
   ✓ Écrans de jeu (start, pause, game over, victoire)

8. SYSTÈME DE HIGH SCORE
   ✓ Chargement du high score au démarrage
   ✓ Sauvegarde automatique du nouveau record
   ✓ Persistance dans ressources/highscore/highscore.sc

GUIDE D'EXÉCUTION DU PROJET:
-----------------------------

COMPILATION:
cd "Galaga squelette code/Galaga"
javac -d bin src/engine/*.java src/game/*.java src/game/actors/*.java

LANCEMENT:
java -cp bin engine.App

CONTRÔLES DU JEU:
-----------------

MENU PRINCIPAL:
- ESPACE : Commencer la partie

EN JEU:
- FLÈCHE GAUCHE (←) : Déplacer le vaisseau à gauche
- FLÈCHE DROITE (→) : Déplacer le vaisseau à droite
- BARRE D'ESPACE : Tirer un projectile
- P : Pause / Reprendre
- ÉCHAP : Quitter le jeu

APRÈS GAME OVER / VICTOIRE:
- R : Recommencer une nouvelle partie
- ÉCHAP : Quitter

OBJECTIFS DU JEU:
-----------------
- Détruire tous les ennemis du niveau 1
- Progresser vers le niveau 2
- Compléter le niveau 2 pour la victoire
- Éviter les projectiles ennemis et les collisions directes
- Maximiser votre score!
- Battre le high score!

SYSTÈME DE POINTS:
------------------
- Bee (abeille jaune) : 100 points
- Butterfly (papillon bleu) : 200 points
- Moth (mite rouge) : 300 points
- Score maximum possible : 13,600 points (2 niveaux)

ARCHITECTURE DU CODE:
---------------------

/src
  /engine
    - App.java : Point d'entrée du programme
    - StdDraw.java : Bibliothèque graphique
  /game
    - Game.java : Boucle de jeu principale, gestion états
    - Level.java : Chargeur de niveaux depuis fichiers .lvl
    /actors
      - Sprite.java : Système de chargement/rendu sprites
      - Player.java : Classe du vaisseau joueur
      - Bullet.java : Projectiles du joueur
      - EnemyBullet.java : Projectiles ennemis
      - Enemy.java : Classe abstraite pour ennemis
      - Bee.java : Ennemi de type abeille
      - Butterfly.java : Ennemi de type papillon
      - Moth.java : Ennemi de type mite

/ressources
  /sprites : Fichiers .spr (ASCII art)
  /levels : Fichiers .lvl (définitions de niveaux)
  /highscore : Fichier highscore.sc (meilleur score)

CARACTÉRISTIQUES TECHNIQUES:
-----------------------------
- Programmation orientée objet en Java
- Héritage (classe Enemy abstraite)
- Collections (ArrayList pour gestion dynamique)
- Gestion d'états (enum GameState)
- I/O fichiers (sprites, niveaux, high score)
- Boucle de jeu temps réel (30ms par frame)
- Double buffering pour fluidité

AMÉLIORATIONS POSSIBLES:
-------------------------
- Effets visuels d'explosion
- Effets sonores
- Power-ups (vies supplémentaires, armes)
- Plus de types d'ennemis
- Boss de fin de niveau
- Niveaux supplémentaires
- Écran de menu principal élaboré
- Système de combos
- Animations de sprites
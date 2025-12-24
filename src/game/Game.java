package game;

import engine.StdDraw;
import game.actors.Player;
import game.actors.Bullet;
import game.actors.EnemyBullet;
import game.actors.Enemy;
import game.actors.Bee;
import game.actors.Butterfly;
import game.actors.Moth;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * Classe du jeu principal.
 * Gère la création de l'espace de jeu et la boucle de jeu en temps réel.
 */
public class Game {
    // États du jeu
    private enum GameState {
        START,
        PLAYING,
        PAUSED,
        GAME_OVER,
        LEVEL_COMPLETE,
        VICTORY
    }
    
    public Player player; // Joueur
    private List<Bullet> bullets; // Liste des projectiles du joueur
    private List<EnemyBullet> enemyBullets; // Liste des projectiles ennemis
    private List<Enemy> enemies; // Liste des ennemis
    private int score; // Score du joueur
    private int highScore; // Meilleur score
    private GameState state; // État actuel du jeu
    private Level currentLevel; // Niveau actuel
    private int currentLevelNumber; // Numéro du niveau actuel
    private int transitionTimer; // Timer pour les transitions

    /**
     * Créé un jeu avec tous les éléments qui le composent
     */
    public Game() {
        player = new Player(0.5, 0.1, 0.05);
        player.setGame(this); // Définir la référence au jeu
        bullets = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        enemies = new ArrayList<>();
        score = 0;
        highScore = loadHighScore();
        state = GameState.START;
        currentLevelNumber = 1;
        transitionTimer = 0;
        
        // Charger le premier niveau
        loadLevel(currentLevelNumber);
    }
    
    /**
     * Charge un niveau
     * 
     * @param levelNumber numéro du niveau (1 ou 2)
     */
    private void loadLevel(int levelNumber) {
        String filename = "ressources/levels/level" + levelNumber + ".lvl";
        currentLevel = Level.loadFromFile(filename);
        enemies.clear();
        enemies.addAll(currentLevel.getEnemies());
        
        // Définir la référence au jeu pour chaque ennemi
        for (Enemy enemy : enemies) {
            enemy.setGame(this);
        }
        
        System.out.println("Niveau " + levelNumber + " chargé: " + enemies.size() + " ennemis");
    }

    /**
     * Initialise l'espace de jeu
     */
    private void init() {
        StdDraw.setCanvasSize(700, 700);
        StdDraw.enableDoubleBuffering();
    }

    /**
     * Initialise le jeu et lance la boucle de jeu en temps réel
     */
    public void launch() {
        init();

        while (isGameRunning()) {
            StdDraw.clear(); // On efface tous ce qu'il y a sur l'interface

            update(); // on met a jour les attributs de chaque éléments
            draw(); // on dessine chaques éléments

            StdDraw.show(); // on montre l'interface
            StdDraw.pause(30); // on attend 30 milisecondes avant de recommencer
        }
    }

    /**
     * Condition d'arrêt du jeu
     * 
     * @return true si le jeu continue
     */
    private boolean isGameRunning() {
        // Le jeu s'arrête si on appuie sur Échap ou si on ferme la fenêtre
        return !StdDraw.isKeyPressed(27); // 27 = touche Échap
    }

    /**
     * Dessin tous les éléments du jeu
     */
    public void draw() {
        if (state == GameState.START) {
            drawStartScreen();
        } else if (state == GameState.PLAYING || state == GameState.LEVEL_COMPLETE || state == GameState.PAUSED) {
            // Dessiner tous les ennemis
            for (Enemy enemy : enemies) {
                enemy.draw();
            }
            
            player.draw();
            
            // Dessiner tous les projectiles
            for (Bullet bullet : bullets) {
                bullet.draw();
            }
            
            // Dessiner tous les projectiles ennemis
            for (EnemyBullet bullet : enemyBullets) {
                bullet.draw();
            }
            
            // Afficher le score et les vies
            drawUI();
            
            if (state == GameState.PAUSED) {
                drawPauseScreen();
            } else if (state == GameState.LEVEL_COMPLETE) {
                drawLevelComplete();
            }
        } else if (state == GameState.GAME_OVER) {
            drawGameOver();
        } else if (state == GameState.VICTORY) {
            drawVictory();
        }
    }

    /**
     * Met a jour les attributs de tous les éléments du jeu
     */
    private void update() {
        if (state == GameState.START) {
            // Appuyer sur Espace pour commencer
            if (StdDraw.isKeyPressed(32)) { // Space
                state = GameState.PLAYING;
            }
        } else if (state == GameState.PLAYING) {
            // Appuyer sur P pour pause
            if (StdDraw.isKeyPressed(80)) { // P key
                state = GameState.PAUSED;
                try { Thread.sleep(200); } catch (InterruptedException e) {}
                return;
            }
            
            player.update();
            
            // Mettre à jour tous les projectiles
            for (Bullet bullet : bullets) {
                bullet.update();
            }
            
            // Mettre à jour tous les projectiles ennemis
            for (EnemyBullet bullet : enemyBullets) {
                bullet.update();
            }
            
            // Mettre à jour tous les ennemis
            for (Enemy enemy : enemies) {
                enemy.update();
            }
            
            // Vérifier les collisions
            checkCollisions();
            
            // Supprimer les projectiles inactifs
            bullets.removeIf(bullet -> !bullet.isActive());
            enemyBullets.removeIf(bullet -> !bullet.isActive());
            
            // Supprimer les ennemis inactifs
            enemies.removeIf(enemy -> !enemy.isActive());
            
            // Vérifier les conditions de victoire/défaite
            checkGameState();
        } else if (state == GameState.PAUSED) {
            // Appuyer sur P pour reprendre
            if (StdDraw.isKeyPressed(80)) { // P key
                state = GameState.PLAYING;
                try { Thread.sleep(200); } catch (InterruptedException e) {}
            }
        } else if (state == GameState.LEVEL_COMPLETE) {
            transitionTimer++;
            if (transitionTimer > 90) { // 3 secondes
                nextLevel();
            }
        } else if (state == GameState.GAME_OVER || state == GameState.VICTORY) {
            // Possibilité de redémarrer avec R
            if (StdDraw.isKeyPressed(82)) { // R key
                restart();
            }
        }
    }
    
    /**
     * Ajoute un projectile à la liste
     * 
     * @param bullet le projectile à ajouter
     */
    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }
    
    /**
     * Ajoute un projectile ennemi à la liste
     * 
     * @param bullet le projectile à ajouter
     */
    public void addEnemyBullet(EnemyBullet bullet) {
        enemyBullets.add(bullet);
    }
    
    /**
     * Vérifie les collisions entre projectiles et ennemis
     */
    private void checkCollisions() {
        // Collision projectiles joueur vs ennemis
        for (Bullet bullet : bullets) {
            if (!bullet.isActive()) continue;
            
            for (Enemy enemy : enemies) {
                if (!enemy.isActive()) continue;
                
                // Vérifier si le projectile touche l'ennemi
                if (enemy.collidesWith(bullet.getX(), bullet.getY())) {
                    enemy.takeDamage(1);
                    bullet.deactivate();
                    
                    // Ajouter des points si l'ennemi est détruit
                    if (!enemy.isActive()) {
                        score += enemy.getScoreValue();
                    }
                    break;
                }
            }
        }
        
        // Collision projectiles ennemis vs joueur
        for (EnemyBullet bullet : enemyBullets) {
            if (!bullet.isActive()) continue;
            
            double dx = bullet.getX() - player.getX();
            double dy = bullet.getY() - player.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);
            
            if (distance < player.getLength() / 2) {
                bullet.deactivate();
                player.loseLife();
                System.out.println("Joueur touché! Vies restantes: " + player.getLives());
            }
        }
        
        // Collision ennemis vs joueur (contact direct)
        for (Enemy enemy : enemies) {
            if (!enemy.isActive()) continue;
            
            if (enemy.collidesWith(player.getX(), player.getY())) {
                enemy.deactivate();
                player.loseLife();
                System.out.println("Collision avec ennemi! Vies restantes: " + player.getLives());
            }
        }
    }
    
    /**
     * Vérifie l'état du jeu (victoire, défaite, niveau complété)
     */
    private void checkGameState() {
        // Vérifier game over
        if (!player.isAlive()) {
            state = GameState.GAME_OVER;
            if (score > highScore) {
                highScore = score;
                saveHighScore(highScore);
            }
            return;
        }
        
        // Vérifier si tous les ennemis sont vaincus
        if (enemies.isEmpty()) {
            if (currentLevelNumber >= 2) {
                state = GameState.VICTORY;
                if (score > highScore) {
                    highScore = score;
                    saveHighScore(highScore);
                }
            } else {
                state = GameState.LEVEL_COMPLETE;
                transitionTimer = 0;
            }
        }
    }
    
    /**
     * Passe au niveau suivant
     */
    private void nextLevel() {
        currentLevelNumber++;
        if (currentLevelNumber <= 2) {
            loadLevel(currentLevelNumber);
            state = GameState.PLAYING;
            bullets.clear();
        } else {
            state = GameState.VICTORY;
        }
    }
    
    /**
     * Redémarre le jeu
     */
    private void restart() {
        currentLevelNumber = 1;
        score = 0;
        bullets.clear();
        enemyBullets.clear();
        enemies.clear();
        player = new Player(0.5, 0.1, 0.05);
        player.setGame(this);
        loadLevel(currentLevelNumber);
        state = GameState.PLAYING;
    }
    
    /**
     * Dessine l'interface utilisateur (score, vies)
     */
    private void drawUI() {
        // Afficher le score
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.textLeft(0.02, 0.98, "Score: " + score);
        
        // Afficher les vies
        StdDraw.textLeft(0.02, 0.95, "Vies: " + player.getLives());
        
        // Afficher le niveau
        StdDraw.textLeft(0.02, 0.92, "Niveau: " + currentLevelNumber);
        
        // Afficher le high score
        StdDraw.textRight(0.98, 0.98, "High Score: " + highScore);
    }
    
    /**
     * Dessine l'écran de démarrage
     */
    private void drawStartScreen() {
        StdDraw.setPenColor(Color.YELLOW);
        StdDraw.setFont(StdDraw.getFont().deriveFont(50f));
        StdDraw.text(0.5, 0.7, "GALAGA");
        
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(StdDraw.getFont().deriveFont(20f));
        StdDraw.text(0.5, 0.55, "High Score: " + highScore);
        
        StdDraw.setFont(StdDraw.getFont().deriveFont(16f));
        StdDraw.text(0.5, 0.45, "CONTRÔLES:");
        StdDraw.text(0.5, 0.40, "← → : Déplacer le vaisseau");
        StdDraw.text(0.5, 0.36, "ESPACE : Tirer");
        StdDraw.text(0.5, 0.32, "P : Pause");
        StdDraw.text(0.5, 0.28, "ESC : Quitter");
        
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.setFont(StdDraw.getFont().deriveFont(24f));
        StdDraw.text(0.5, 0.15, "Appuyez sur ESPACE pour commencer");
        
        // Réinitialiser la police
        StdDraw.setFont(StdDraw.getFont().deriveFont(12f));
    }
    
    /**
     * Dessine l'écran de pause
     */
    private void drawPauseScreen() {
        // Fond semi-transparent
        StdDraw.setPenColor(new Color(0, 0, 0, 180));
        StdDraw.filledRectangle(0.5, 0.5, 0.5, 0.5);
        
        StdDraw.setPenColor(Color.YELLOW);
        StdDraw.setFont(StdDraw.getFont().deriveFont(40f));
        StdDraw.text(0.5, 0.55, "PAUSE");
        
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(StdDraw.getFont().deriveFont(20f));
        StdDraw.text(0.5, 0.45, "Appuyez sur P pour continuer");
        
        // Réinitialiser la police
        StdDraw.setFont(StdDraw.getFont().deriveFont(12f));
    }
    
    /**
     * Dessine l'écran de game over
     */
    private void drawGameOver() {
        StdDraw.setPenColor(Color.RED);
        StdDraw.setFont(StdDraw.getFont().deriveFont(40f));
        StdDraw.text(0.5, 0.6, "GAME OVER");
        
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(StdDraw.getFont().deriveFont(20f));
        StdDraw.text(0.5, 0.5, "Score Final: " + score);
        StdDraw.text(0.5, 0.4, "Appuyez sur R pour recommencer");
        StdDraw.text(0.5, 0.35, "ou Échap pour quitter");
        
        // Réinitialiser la police
        StdDraw.setFont(StdDraw.getFont().deriveFont(12f));
    }
    
    /**
     * Dessine l'écran de niveau complété
     */
    private void drawLevelComplete() {
        StdDraw.setPenColor(Color.YELLOW);
        StdDraw.setFont(StdDraw.getFont().deriveFont(35f));
        StdDraw.text(0.5, 0.5, "NIVEAU " + currentLevelNumber + " COMPLÉTÉ!");
        
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(StdDraw.getFont().deriveFont(20f));
        StdDraw.text(0.5, 0.4, "Chargement du niveau suivant...");
        
        // Réinitialiser la police
        StdDraw.setFont(StdDraw.getFont().deriveFont(12f));
    }
    
    /**
     * Dessine l'écran de victoire
     */
    private void drawVictory() {
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.setFont(StdDraw.getFont().deriveFont(40f));
        StdDraw.text(0.5, 0.6, "VICTOIRE!");
        
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(StdDraw.getFont().deriveFont(20f));
        StdDraw.text(0.5, 0.5, "Score Final: " + score);
        StdDraw.text(0.5, 0.4, "Tous les niveaux complétés!");
        StdDraw.text(0.5, 0.35, "Appuyez sur R pour recommencer");
        
        // Réinitialiser la police
        StdDraw.setFont(StdDraw.getFont().deriveFont(12f));
    }
    
    /**
     * Retourne le score actuel
     * @return score
     */
    public int getScore() {
        return score;
    }
    
    /**
     * Charge le high score depuis le fichier
     * @return le high score
     */
    private int loadHighScore() {
        try (java.io.BufferedReader reader = new java.io.BufferedReader(
                new java.io.FileReader("ressources/highscore/highscore.sc"))) {
            String line = reader.readLine();
            if (line != null && !line.trim().isEmpty()) {
                return Integer.parseInt(line.trim());
            }
        } catch (Exception e) {
            System.out.println("Impossible de charger le high score: " + e.getMessage());
        }
        return 0;
    }
    
    /**
     * Sauvegarde le high score dans le fichier
     * @param score le score à sauvegarder
     */
    private void saveHighScore(int score) {
        try (java.io.PrintWriter writer = new java.io.PrintWriter(
                new java.io.FileWriter("ressources/highscore/highscore.sc"))) {
            writer.println(score);
            System.out.println("Nouveau high score sauvegardé: " + score);
        } catch (Exception e) {
            System.out.println("Impossible de sauvegarder le high score: " + e.getMessage());
        }
    }
}

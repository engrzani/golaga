package game;

import game.actors.Enemy;
import game.actors.Bee;
import game.actors.Butterfly;
import game.actors.Moth;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un niveau du jeu.
 * Charge les définitions d'ennemis depuis un fichier .lvl
 */
public class Level {
    private String name; // nom du niveau
    private double scrollSpeed; // vitesse de défilement
    private int timeLimit; // limite de temps (-1 = illimité)
    private int targetScore; // score cible
    private List<Enemy> enemies; // liste des ennemis du niveau
    
    /**
     * Crée un niveau vide
     */
    public Level() {
        enemies = new ArrayList<>();
    }
    
    /**
     * Charge un niveau depuis un fichier
     * 
     * @param filename chemin du fichier niveau
     * @return le niveau chargé
     */
    public static Level loadFromFile(String filename) {
        Level level = new Level();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true;
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                if (firstLine) {
                    // Première ligne: nom du niveau, vitesse, limite de temps, score cible
                    String[] params = line.split(" ");
                    level.name = params[0];
                    level.scrollSpeed = Double.parseDouble(params[1]);
                    level.timeLimit = Integer.parseInt(params[2]);
                    level.targetScore = Integer.parseInt(params[3]);
                    firstLine = false;
                } else {
                    // Lignes suivantes: définitions d'ennemis
                    level.parseEnemyLine(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du niveau: " + filename);
            e.printStackTrace();
        }
        
        return level;
    }
    
    /**
     * Parse une ligne de définition d'ennemi
     * Format: Type x y size score speed
     * 
     * @param line la ligne à parser
     */
    private void parseEnemyLine(String line) {
        String[] parts = line.split(" ");
        if (parts.length < 6) return;
        
        try {
            String type = parts[0];
            double x = Double.parseDouble(parts[1]);
            double y = Double.parseDouble(parts[2]);
            double size = Double.parseDouble(parts[3]);
            int score = Integer.parseInt(parts[4]);
            double speed = Double.parseDouble(parts[5]);
            
            Enemy enemy = null;
            
            switch (type.toLowerCase()) {
                case "bee":
                    enemy = new Bee(x, y, size, score, speed);
                    break;
                case "butterfly":
                    enemy = new Butterfly(x, y, size, score, speed);
                    break;
                case "moth":
                    enemy = new Moth(x, y, size, score, speed);
                    break;
                default:
                    System.err.println("Type d'ennemi inconnu: " + type);
            }
            
            if (enemy != null) {
                enemies.add(enemy);
            }
        } catch (NumberFormatException e) {
            System.err.println("Erreur de parsing de ligne d'ennemi: " + line);
        }
    }
    
    /**
     * Retourne la liste des ennemis du niveau
     * 
     * @return liste d'ennemis
     */
    public List<Enemy> getEnemies() {
        return new ArrayList<>(enemies); // Retourne une copie
    }
    
    /**
     * Retourne le nom du niveau
     * 
     * @return nom
     */
    public String getName() {
        return name;
    }
    
    /**
     * Retourne le score cible
     * 
     * @return score cible
     */
    public int getTargetScore() {
        return targetScore;
    }
    
    /**
     * Retourne la limite de temps
     * 
     * @return limite de temps (-1 si illimité)
     */
    public int getTimeLimit() {
        return timeLimit;
    }
}

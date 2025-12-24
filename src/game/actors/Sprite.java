package game.actors;

import engine.StdDraw;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un sprite chargé depuis un fichier .spr
 * Les sprites sont des grilles de caractères représentant des pixels colorés
 */
public class Sprite {
    private char[][] grid; // Grille de caractères représentant le sprite
    private int width; // Largeur du sprite
    private int height; // Hauteur du sprite

    /**
     * Charge un sprite depuis un fichier .spr
     * 
     * @param filename le chemin du fichier sprite relatif au répertoire ressources
     */
    public Sprite(String filename) {
        loadSprite(filename);
    }

    /**
     * Charge le sprite depuis un fichier
     * 
     * @param filename le chemin du fichier sprite
     */
    private void loadSprite(String filename) {
        List<String> lines = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du sprite: " + filename);
            e.printStackTrace();
            // Créer un sprite vide en cas d'erreur
            grid = new char[1][1];
            grid[0][0] = 'N';
            width = 1;
            height = 1;
            return;
        }

        if (lines.isEmpty()) {
            grid = new char[1][1];
            grid[0][0] = 'N';
            width = 1;
            height = 1;
            return;
        }

        height = lines.size();
        width = lines.get(0).length();
        grid = new char[height][width];

        for (int i = 0; i < height; i++) {
            String line = lines.get(i);
            for (int j = 0; j < Math.min(width, line.length()); j++) {
                grid[i][j] = line.charAt(j);
            }
        }
    }

    /**
     * Dessine le sprite à la position donnée avec la taille spécifiée
     * 
     * @param x      position x du centre du sprite (entre 0 et 1)
     * @param y      position y du centre du sprite (entre 0 et 1)
     * @param size   taille du sprite (largeur totale entre 0 et 1)
     */
    public void draw(double x, double y, double size) {
        double pixelSize = size / width; // Taille d'un pixel du sprite
        
        // Position de départ (coin supérieur gauche)
        double startX = x - size / 2;
        double startY = y + (height * pixelSize) / 2;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                char pixel = grid[i][j];
                if (pixel != 'N') { // N = transparent
                    StdDraw.setPenColor(getColor(pixel));
                    double px = startX + j * pixelSize + pixelSize / 2;
                    double py = startY - i * pixelSize - pixelSize / 2;
                    StdDraw.filledSquare(px, py, pixelSize / 2);
                }
            }
        }
    }

    /**
     * Convertit un caractère en couleur
     * 
     * @param c le caractère représentant la couleur
     * @return la couleur correspondante
     */
    private Color getColor(char c) {
        switch (c) {
            case 'W': return Color.WHITE;
            case 'R': return Color.RED;
            case 'B': return Color.BLUE;
            case 'G': return Color.GREEN;
            case 'Y': return Color.YELLOW;
            case 'O': return Color.ORANGE;
            case 'P': return Color.PINK;
            case 'M': return Color.MAGENTA;
            case 'C': return Color.CYAN;
            case 'K': return Color.BLACK;
            case 'D': return Color.DARK_GRAY;
            case 'L': return Color.LIGHT_GRAY;
            default: return Color.WHITE;
        }
    }

    /**
     * Retourne la largeur du sprite
     * 
     * @return largeur en pixels
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retourne la hauteur du sprite
     * 
     * @return hauteur en pixels
     */
    public int getHeight() {
        return height;
    }
}

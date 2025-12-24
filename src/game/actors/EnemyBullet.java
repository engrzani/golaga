package game.actors;

import engine.StdDraw;
import java.awt.Color;

/**
 * Classe représentant un projectile tiré par un ennemi
 */
public class EnemyBullet {
    private double x; // position x du projectile
    private double y; // position y du projectile
    private double speed; // vitesse de déplacement
    private double width; // largeur du projectile
    private double height; // hauteur du projectile
    private boolean active; // indique si le projectile est actif

    /**
     * Crée un nouveau projectile ennemi
     * 
     * @param x position x initiale
     * @param y position y initiale
     * @param speed vitesse de déplacement vers le bas
     */
    public EnemyBullet(double x, double y, double speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = 0.004;
        this.height = 0.015;
        this.active = true;
    }

    /**
     * Met à jour la position du projectile
     */
    public void update() {
        y -= speed; // Le projectile descend
        
        // Désactive le projectile s'il sort de l'écran
        if (y < 0.0 || y > 1.0) {
            active = false;
        }
    }

    /**
     * Dessine le projectile
     */
    public void draw() {
        if (active) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.filledRectangle(x, y, width, height);
        }
    }

    /**
     * Vérifie si le projectile est actif
     * 
     * @return true si le projectile est actif
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Désactive le projectile
     */
    public void deactivate() {
        active = false;
    }

    /**
     * Retourne la position x du projectile
     * 
     * @return position x
     */
    public double getX() {
        return x;
    }

    /**
     * Retourne la position y du projectile
     * 
     * @return position y
     */
    public double getY() {
        return y;
    }

    /**
     * Retourne la largeur du projectile
     * 
     * @return largeur
     */
    public double getWidth() {
        return width;
    }

    /**
     * Retourne la hauteur du projectile
     * 
     * @return hauteur
     */
    public double getHeight() {
        return height;
    }
}

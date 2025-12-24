package game.actors;

/**
 * Classe représentant un ennemi de type Butterfly (papillon).
 * Ennemi de difficulté moyenne avec un mouvement plus rapide.
 */
public class Butterfly extends Enemy {
    private double initialX; // position x initiale
    private double movementOffset; // décalage pour le mouvement
    
    /**
     * Crée un ennemi Butterfly
     * 
     * @param x position x initiale
     * @param y position y initiale
     * @param length taille
     * @param scoreValue points gagnés (200)
     * @param speed vitesse de déplacement
     */
    public Butterfly(double x, double y, double length, int scoreValue, double speed) {
        super(x, y, length, scoreValue, speed);
        this.sprite = new Sprite("ressources/sprites/butterfly.spr");
        this.initialX = x;
        this.movementOffset = 0;
    }
    
    /**
     * Met à jour la position du Butterfly avec un mouvement en vague
     */
    @Override
    public void update() {
        if (!active) return;
        
        // Mouvement en vague plus prononcé
        movementOffset += speed * 1.5;
        x = initialX + Math.cos(movementOffset) * 0.08;
        
        // Descente modérée
        y -= speed / 3;
        
        // Tenter de tirer
        tryShoot();
        
        // Désactive si sort de l'écran
        if (y < -0.1) {
            active = false;
        }
    }
}

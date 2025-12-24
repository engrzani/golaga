package game.actors;

/**
 * Classe représentant un ennemi de type Moth (mite/catcher).
 * C'est l'ennemi le plus difficile avec le plus de points.
 */
public class Moth extends Enemy {
    private double initialX; // position x initiale  
    private double movementOffset; // décalage pour le mouvement
    
    /**
     * Crée un ennemi Moth
     * 
     * @param x position x initiale
     * @param y position y initiale
     * @param length taille
     * @param scoreValue points gagnés (300)
     * @param speed vitesse de déplacement
     */
    public Moth(double x, double y, double length, int scoreValue, double speed) {
        super(x, y, length, scoreValue, speed);
        this.sprite = new Sprite("ressources/sprites/catcher.spr");
        this.initialX = x;
        this.movementOffset = 0;
    }
    
    /**
     * Met à jour la position du Moth avec un mouvement lent et large
     */
    @Override
    public void update() {
        if (!active) return;
        
        // Mouvement lent et large
        movementOffset += speed;
        x = initialX + Math.sin(movementOffset * 0.8) * 0.1;
        
        // Descente très lente
        y -= speed / 6;
        
        // Tenter de tirer
        tryShoot();
        
        // Désactive si sort de l'écran
        if (y < -0.1) {
            active = false;
        }
    }
}

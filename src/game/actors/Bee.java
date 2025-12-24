package game.actors;

/**
 * Classe représentant un ennemi de type Bee (abeille).
 * C'est l'ennemi de base avec un mouvement simple.
 */
public class Bee extends Enemy {
    private double initialX; // position x initiale
    private double movementOffset; // décalage pour le mouvement oscillant
    
    /**
     * Crée un ennemi Bee
     * 
     * @param x position x initiale
     * @param y position y initiale
     * @param length taille
     * @param scoreValue points gagnés (100)
     * @param speed vitesse de déplacement
     */
    public Bee(double x, double y, double length, int scoreValue, double speed) {
        super(x, y, length, scoreValue, speed);
        this.sprite = new Sprite("ressources/sprites/bee.spr");
        this.initialX = x;
        this.movementOffset = 0;
    }
    
    /**
     * Met à jour la position du Bee avec un mouvement oscillant
     */
    @Override
    public void update() {
        if (!active) return;
        
        // Mouvement oscillant horizontal
        movementOffset += speed * 2;
        x = initialX + Math.sin(movementOffset) * 0.05;
        
        // Descente lente
        y -= speed / 4;
        
        // Tenter de tirer
        tryShoot();
        
        // Désactive si sort de l'écran
        if (y < -0.1) {
            active = false;
        }
    }
}

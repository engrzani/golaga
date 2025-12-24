package game.actors;

import engine.StdDraw;

/**
 * Classe représentant le joueur.
 * Le joueur est représenté par un vaisseau spatial qui peut se déplacer
 * avec les flèches du clavier et tirer avec la barre d'espace.
 */
public class Player {
    private double x; // postion du joueur sur l'axe des abscisses
    private double y; // position du joueur sur l'axe des ordonnées
    private double length; // largeur du joueur
    private Sprite sprite; // sprite du vaisseau
    private int lives; // nombre de vies du joueur
    private boolean canShoot; // indique si le joueur peut tirer
    private game.Game game; // référence au jeu principal

    /**
     * Créé un joueur.
     * 
     * @param x      postion du joueur sur l'axe des abscisses
     * @param y      position du joueur sur l'axe des ordonnées
     * @param length largeur du joueur
     */
    public Player(double x, double y, double length) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.sprite = new Sprite("ressources/sprites/ship.spr");
        this.lives = 3;
        this.canShoot = true;
        this.game = null; // Sera défini plus tard
    }
    
    /**
     * Définit la référence au jeu principal
     * 
     * @param game le jeu principal
     */
    public void setGame(game.Game game) {
        this.game = game;
    }

    /**
     * Dessine le joueur avec son sprite
     */
    public void draw() {
        sprite.draw(x, y, length);
    }

    /**
     * Met à jour la position du joueur en fonction des touches préssées.
     */
    public void update() {
        double speed = 0.01; // vitesse de déplacement du joueur
        
        // Si la flèche gauche est préssée
        if (StdDraw.isKeyPressed(37)) { 
            x -= speed;
        }
        // Si la flèche droite est préssée
        if (StdDraw.isKeyPressed(39)) {
            x += speed;
        }
        
        // Limiter le mouvement aux bords de l'écran
        if (x < length / 2) {
            x = length / 2;
        }
        if (x > 1 - length / 2) {
            x = 1 - length / 2;
        }
        if (y < length / 2) {
            y = length / 2;
        }
        if (y > 1 - length / 2) {
            y = 1 - length / 2;
        }
        
        // Gestion du tir avec la barre d'espace (code 32)
        if (StdDraw.isKeyPressed(32)) {
            if (canShoot) {
                shoot();
                canShoot = false;
            }
        } else {
            canShoot = true; // Peut tirer à nouveau quand la touche est relâchée
        }
    }
    
    /**
     * Fait tirer le joueur (à implémenter avec la classe Bullet)
     */
    private void shoot() {
        if (game != null) {
            // Créer un nouveau projectile au-dessus du vaisseau
            Bullet bullet = new Bullet(x, y + length / 2, 0.015);
            game.addBullet(bullet);
        }
    }
    
    /**
     * Retourne la position x du joueur
     * @return position x
     */
    public double getX() {
        return x;
    }
    
    /**
     * Retourne la position y du joueur
     * @return position y
     */
    public double getY() {
        return y;
    }
    
    /**
     * Retourne la taille du joueur
     * @return taille
     */
    public double getLength() {
        return length;
    }
    
    /**
     * Retourne le nombre de vies du joueur
     * @return nombre de vies
     */
    public int getLives() {
        return lives;
    }
    
    /**
     * Réduit le nombre de vies du joueur
     */
    public void loseLife() {
        lives--;
    }
    
    /**
     * Vérifie si le joueur est encore en vie
     * @return true si le joueur a encore des vies
     */
    public boolean isAlive() {
        return lives > 0;
    }
}

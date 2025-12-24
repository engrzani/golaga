package game.actors;

/**
 * Classe abstraite représentant un ennemi dans le jeu.
 * Tous les types d'ennemis héritent de cette classe.
 */
public abstract class Enemy {
    protected double x; // position x de l'ennemi
    protected double y; // position y de l'ennemi
    protected double length; // taille de l'ennemi
    protected int health; // points de vie
    protected int scoreValue; // points gagnés en détruisant cet ennemi
    protected double speed; // vitesse de déplacement
    protected Sprite sprite; // sprite de l'ennemi
    protected boolean active; // indique si l'ennemi est actif
    protected game.Game game; // référence au jeu principal
    protected int shootCooldown; // temps avant de pouvoir tirer à nouveau
    
    /**
     * Constructeur d'un ennemi
     * 
     * @param x position x initiale
     * @param y position y initiale
     * @param length taille de l'ennemi
     * @param scoreValue points gagnés
     * @param speed vitesse de déplacement
     */
    public Enemy(double x, double y, double length, int scoreValue, double speed) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.scoreValue = scoreValue;
        this.speed = speed;
        this.health = 1; // Par défaut, un seul coup suffit
        this.active = true;
        this.game = null;
        this.shootCooldown = (int)(Math.random() * 200) + 100; // Cooldown aléatoire
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
     * Met à jour la position et l'état de l'ennemi
     */
    public abstract void update();
    
    /**
     * Dessine l'ennemi
     */
    public void draw() {
        if (active && sprite != null) {
            sprite.draw(x, y, length);
        }
    }
    
    /**
     * Inflige des dégâts à l'ennemi
     * 
     * @param damage nombre de points de vie perdus
     */
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            active = false;
        }
    }
    
    /**
     * Vérifie si l'ennemi est actif
     * 
     * @return true si l'ennemi est actif
     */
    public boolean isActive() {
        return active;
    }
    
    /**
     * Désactive l'ennemi
     */
    public void deactivate() {
        active = false;
    }
    
    /**
     * Retourne la position x
     * @return position x
     */
    public double getX() {
        return x;
    }
    
    /**
     * Retourne la position y
     * @return position y
     */
    public double getY() {
        return y;
    }
    
    /**
     * Retourne la taille
     * @return taille
     */
    public double getLength() {
        return length;
    }
    
    /**
     * Retourne la valeur en points
     * @return score
     */
    public int getScoreValue() {
        return scoreValue;
    }
    
    /**
     * Vérifie la collision avec un point donné
     * 
     * @param px position x du point
     * @param py position y du point
     * @return true si collision
     */
    public boolean collidesWith(double px, double py) {
        double distance = Math.sqrt(Math.pow(x - px, 2) + Math.pow(y - py, 2));
        return distance < length / 2;
    }
    
    /**
     * Tente de tirer si le cooldown est écoulé
     */
    protected void tryShoot() {
        shootCooldown--;
        if (shootCooldown <= 0 && game != null && Math.random() < 0.01) {
            EnemyBullet bullet = new EnemyBullet(x, y - length / 2, 0.008);
            game.addEnemyBullet(bullet);
            shootCooldown = (int)(Math.random() * 300) + 150;
        }
    }
}

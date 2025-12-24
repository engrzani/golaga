# 🚀 GALAGA - Classic Arcade Game

A Java implementation of the classic Galaga arcade game with modern features.

![Java](https://img.shields.io/badge/Java-17+-blue.svg)
![Status](https://img.shields.io/badge/Status-Complete-success.svg)
![License](https://img.shields.io/badge/License-MIT-green.svg)

## 🎮 Features

- **Classic Gameplay** - Authentic Galaga arcade experience
- **3 Enemy Types** - Bee (100pts), Butterfly (200pts), Moth (300pts)
- **2 Levels** - 44 enemies per level
- **Player Controls** - Arrow keys for movement, Space to shoot
- **Lives System** - Start with 3 lives
- **High Score** - Persistent high score tracking
- **Pause/Resume** - Press P to pause
- **ASCII Sprite System** - Custom sprite rendering

## 📥 Download & Play

### Quick Start (Windows)
1. Download `Galaga.jar` from [Releases](../../releases)
2. Double-click to run, or use: `java -jar Galaga.jar`

### From Source
```bash
git clone <repository-url>
cd Galaga
javac -d bin src/engine/*.java src/game/*.java src/game/actors/*.java
java -cp bin engine.App
```

## 🎯 Controls

| Key | Action |
|-----|--------|
| **SPACE** | Start game / Shoot |
| **←** | Move left |
| **→** | Move right |
| **P** | Pause/Resume |
| **R** | Restart (after game over) |
| **ESC** | Quit |

## 🏆 Scoring

- **Bee**: 100 points
- **Butterfly**: 200 points  
- **Moth**: 300 points
- **Maximum Score**: 13,600 points (both levels)

## 📋 Requirements

- Java JDK 8 or higher
- Windows/Linux/macOS

## 🛠️ Build from Source

### Compile
```bash
javac -d bin src/engine/*.java src/game/*.java src/game/actors/*.java
```

### Run
```bash
java -cp bin engine.App
```

### Build JAR
```bash
# Windows
build-jar.bat

# Manual
javac -d bin src/**/*.java
echo Main-Class: engine.App > manifest.txt
cd bin && jar cvfm ../Galaga.jar ../manifest.txt * && cd ..
jar uf Galaga.jar -C . ressources
```

## 📂 Project Structure

```
Galaga/
├── src/
│   ├── engine/          # Game engine
│   └── game/            # Game logic
│       └── actors/      # Game entities
├── ressources/
│   ├── sprites/         # ASCII art sprites
│   ├── levels/          # Level definitions
│   └── highscore/       # High score file
├── bin/                 # Compiled classes
└── Galaga.jar          # Executable JAR
```

## 🎨 Screenshots

*Game in action - Classic arcade gameplay!*

## 💻 Technologies

- **Language**: Java
- **Graphics**: StdDraw library
- **Architecture**: Object-oriented design
- **Patterns**: State pattern, Inheritance, Polymorphism

## 🤝 Contributing

Contributions welcome! Feel free to:
- Report bugs
- Suggest features
- Submit pull requests

## 📄 License

MIT License - Feel free to use and modify!

## 👥 Credits

Developed as an educational project demonstrating:
- Game development in Java
- Object-oriented programming
- Sprite-based graphics
- Game state management

---

**Enjoy the game! 🚀**

*Star ⭐ this repo if you enjoyed it!*

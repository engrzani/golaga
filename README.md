# 🚀 GALAGA - Classic Arcade Game

A Java implementation of the classic Galaga arcade game with modern features.

![Java](https://img.shields.io/badge/Java-17+-blue.svg)
![Status](https://img.shields.io/badge/Status-Complete-success.svg)
![License](https://img.shields.io/badge/License-MIT-green.svg)

---

## 🎮 PLAY NOW!

### ⬇️ **[DOWNLOAD GAME (Galaga.jar)](https://github.com/engrzani/golaga/raw/main/Galaga.jar)** ⬇️

**Quick Start:**
1. Click the download link above
2. Make sure Java is installed on your computer
3. Double-click `Galaga.jar` OR run: `java -jar Galaga.jar`
4. **Press SPACE to start playing!**

> 💡 **Don't have Java?** Download from: https://www.java.com/download/

---

## 🎮 Features

- **Classic Gameplay** - Authentic Galaga arcade experience
- **3 Enemy Types** - Bee (100pts), Butterfly (200pts), Moth (300pts)
- **2 Levels** - 44 enemies per level
- **Player Controls** - Arrow keys for movement, Space to shoot
- **Lives System** - Start with 3 lives
- **High Score** - Persistent high score tracking
- **Pause/Resume** - Press P to pause
- **ASCII Sprite System** - Custom sprite rendering

## 📥 Three Ways to Play

### 🎯 Method 1: Download JAR (Easiest)
**[👉 CLICK HERE TO DOWNLOAD GALAGA.JAR](https://github.com/engrzani/golaga/raw/main/Galaga.jar)**

Then run:
```bash
java -jar Galaga.jar
```

### 🔧 Method 2: Clone & Play
```bash
git clone https://github.com/engrzani/golaga.git
cd golaga
java -jar Galaga.jar
```

### 💻 Method 3: Build from Source
```bash
git clone https://github.com/engrzani/golaga.git
cd golaga
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
<img width="680" height="704" alt="image" src="https://github.com/user-attachments/assets/91a19076-2048-4316-841c-b6dfd6e849d2" />
<img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/6c08ef85-09c8-413c-9337-cc695fc17afd" />

<img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/8ef3acd5-0420-4d95-b669-f7d04dad1b78" />

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

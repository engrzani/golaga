@echo off
REM Build JAR file for Galaga game

echo ========================================
echo   GALAGA - Building JAR Package
echo ========================================
echo.

echo [1/4] Compiling Java files...
javac -d bin src/engine/*.java src/game/*.java src/game/actors/*.java

if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Compilation failed
    pause
    exit /b 1
)

echo [2/4] Creating manifest file...
echo Main-Class: engine.App > manifest.txt

echo [3/4] Creating JAR file...
cd bin
jar cvfm ../Galaga.jar ../manifest.txt engine game
cd ..

echo [4/4] Copying resources...
jar uf Galaga.jar -C . ressources

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================
    echo SUCCESS! JAR file created: Galaga.jar
    echo ========================================
    echo.
    echo To run: java -jar Galaga.jar
    echo.
) else (
    echo ERROR: JAR creation failed
)

del manifest.txt
pause

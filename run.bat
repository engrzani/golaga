@echo off
REM Script de compilation et lancement du jeu Galaga
REM Windows Batch Script

echo ========================================
echo   GALAGA - Compilation et Lancement
echo ========================================
echo.

REM Vérifier si Java est installé
where javac >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ERREUR: Java JDK n'est pas installé ou pas dans le PATH
    echo Veuillez installer Java JDK 8 ou supérieur
    echo Télécharger depuis: https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
)

echo [1/3] Compilation en cours...
javac -d bin src/engine/*.java src/game/*.java src/game/actors/*.java

if %ERRORLEVEL% NEQ 0 (
    echo ERREUR: La compilation a échoué
    pause
    exit /b 1
)

echo [2/3] Compilation réussie!
echo [3/3] Lancement du jeu...
echo.
echo Appuyez sur CTRL+C pour quitter le jeu
echo ========================================
echo.

java -cp bin engine.App

echo.
echo Jeu terminé.
pause

# Script PowerShell de compilation et lancement du jeu Galaga
# PowerShell Script

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  GALAGA - Compilation et Lancement" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Vérifier si Java est installé
$javaPath = Get-Command javac -ErrorAction SilentlyContinue
if (-not $javaPath) {
    Write-Host "ERREUR: Java JDK n'est pas installé ou pas dans le PATH" -ForegroundColor Red
    Write-Host "Veuillez installer Java JDK 8 ou supérieur"
    Write-Host "Télécharger depuis: https://www.oracle.com/java/technologies/downloads/"
    pause
    exit 1
}

Write-Host "[1/3] Compilation en cours..." -ForegroundColor Yellow
javac -d bin src/engine/*.java src/game/*.java src/game/actors/*.java

if ($LASTEXITCODE -ne 0) {
    Write-Host "ERREUR: La compilation a échoué" -ForegroundColor Red
    pause
    exit 1
}

Write-Host "[2/3] Compilation réussie!" -ForegroundColor Green
Write-Host "[3/3] Lancement du jeu..." -ForegroundColor Yellow
Write-Host ""
Write-Host "Appuyez sur CTRL+C pour quitter le jeu" -ForegroundColor Gray
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

java -cp bin engine.App

Write-Host ""
Write-Host "Jeu terminé." -ForegroundColor Green
pause

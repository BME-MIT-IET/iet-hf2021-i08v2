# Build keretrendszer és CI beüzemelése (dokumentáció)

## Elvégzett munka
- 'Java with Maven' munkafolyamat létrehozása
- yml (YAML) fájl tartalmának ellenőrzése
- Dokumentáció elkészítése
- Előkészülés commit-ra, valamint pull request nyitására

## Összefoglalás, tanulság
A GitHub által biztosított környezetben könnyedén lehet kezelni a forráskódot, valamint egyszerű automatikus kódfordítást és folytonos integrációt beállítani.

Előbbit alapvetően egy egyszerű szöveges konfigurációs állomány segítségével szabhatjuk testre, ahol eseményekre (pl. új commit vagy pull request, adott időnként) reagálva akciókból álló lépéseket hajthatjuk végre (pl. fordítás vagy telepítés). A most létrehozott beállítás szerint minden 'main' branch-re történő push és pull request esetén megtörténik a fordítás és kiértékelés.
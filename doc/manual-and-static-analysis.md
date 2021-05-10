# Manuális kód átvizsgálás, Statikus analízis eszköz futtatása és jelzett hibák átnézése, javítása

## Elvégzett munka
- Manuális ellenőrzés
- SonarCloud beüzemelése, statikus analízis futtatása
- Manuális ellenőrzés során feltárt hibák összevetése a SonarCloud által talált hibákkal
- Talált hibák javítása két lépésben

## Összefoglalás, tanulság

### Manuális átvizsgálás
A manuális átvizsgálás során nem találtunk kirívó eseteket, így pár apró javítást eszközöltünk a statikus javításokhoz kapcsolva.
- Abstract osztály konstruktora publikus volt, ezt protectedre cseréltük.
- Túl hosszú függvényeket feldaraboltuk több kisebb függvényre
- Kivételkezelésben debug számára használt funkciók eltávolításra kerültek (statikus analízis hibáinak javítása során)

### Statikus analízis
Kezdetben problémába ütköztünk a SonarCloud beüzemelése során. (Ez látható bizonyos commitokon.) A hiba a SonarCloud felületén található projektünkhöz való adminisztrátori jogosultság hiánya volt. Pár levélváltás és jogosultságbeállítás után sikeresen lefutott a statikus analízis, és láthattuk a talált hibákat.

Ezeket a hibákat felosztottuk, majd mindenki elvégezte a rá osztott feladatot. Későbbi csapatmegbeszélés során megállapodtunk arról, hogy a maradék Code Smell, valamint pár hiba javítása is megtörténik. A hibák főként olyan eredetűek voltak, amelyek vagy fejlesztői környezettől IDE-től függtek (manuális átvizsgálás során is megtaláltuk ezeket), vagy pedig Random-ok használatához kapcsolódtak. Előbbi alatt a diamond operátort kell érteni. Erre példa, hogy az Eclipse egy lista esetén inicializálásnál hibaként jelzi, ha nincs megadva a típus, míg mondjuk NetBeans esetében nem.
- List<Integer> szamok = new List<Integer>() // Eclipse elfogadja, de NetBeans hibának jelzi
- List<Integer> szamok = new List<>(); // Eclipse hibaként jelzi, NetBeans elfogadja

További hibák, amelyek javításra kerültek:
- Code Smell volt sok helyen a változók típusának megadása, ezeket 'var'-ra kellett cserélni
- Üres függvénytörzsek
- Hiányzó override annotációk
- Kivétel elkapása esetén üres catch törzsek

### Végső értékelés
- A SonarCloud értékelése szerint a legtöbb kategóriában 'A' értékelést kapott a projekt
- A commitok és pull requestek során a SonarCloud többször is 0.0%-os kódlefedettségről adott hibát, ami jelen esetben számunkra nem releváns.


### A munkát elvégezte:
Gyarmati Zalán (SMMOR2) és Csönge Bence (XGJR1U)

# IceField - Manual Testing

## Tesztelés céla
Ezen teszt célja a szoftvert egy felhasználó szemszögéből nézve kézzel tesztelni és így minél több hibát és bug-ot megtalálni. A tesztelés alapjául szolgálnak előre meghatározott tesztesetek amelyek segítenek a szoftver funkcionalitásainak minél nagyobb halmazát átnézni.

## Tesztesetek listája
1. Kimentés kötéllel (d)
2. Jegesmedve meglátogatása (g)
3. Betörik az unstable jégtábla (d)
4. Eltörik a törékeny ásó (g)
5. Hóvihar túlélése igluval (g)
6. DivingSuit működik-e (d)
7. Researcher képessége (g)
8. Hagyunk valakit megfagyni (g)
9. Játék megnyerése (d)
10. Negative Testing (d)

## Tesztesetek
---

### 1. Kimentés kötéllel

Addig kellett újragenerálnom a játékmezőt amíg nem volt a játék elején könnyen hozzáférhető kötél és lyuk közel egymáshoz. A kötél valóban kimentette a játékost azonban a folyamat nem volt teljesen bugmentes. A kötél akkor is le vont 1-et a staminánkból ha rossz irányban próbáltuk használni, valamint a kimentett játékos a kimentés után nem 4 hanem 3 staminával kezdte a körét.

### 2. Jegesmedve meglátogatása

A jegesmedvével való találkozást kétféle módon lehet megtenni. Az egyik lehetőség, hogy mi lépünk arra a mezőre, amelyiken a jegesmedve van. Ezt mindkét karakter típussal megpróbálta és azonnal vége lett a játéknak.

A másik lehetőség, hogy megvárjuk amíg a jegesmedve lép arra a táblára, amelyiken mi állunk. Ezt úgy teszteltem, hogy 4 játékossal körbeálltam a jegesmedvét, így bármelyikre lép a jegesmedve, úgy könnyű tesztelni. A tesztet többször elvégezve a jegesmedve lépése után vége lett a játéknak.

Ez alapján kijenthető, hogy a jegesmedvével való találkozás a specifikációnak megfelelően működik

### 3. Betörik az Unstable jégtábla

Ehhez kellett keresni egy instabil jégtáblát és rálépni a megfelelő számú játékossal. Ezutána a tábla valóban be is tört.

### 4. Eltörik a törékeny ásó

A törékeny ásó egy olyan felvehető eszköz, amely 1 munkabefektetéssel enged 2 egység havat kiásni. Azonban ezt csak 3-szor alkalmazhatjuk, utána el kell törnie (specifikáció szerint).

Ehhez keresnünk kell egy törékeny ásót az egyik jégtáblán. Miután ezt megtettük, ásnunk kell vele háromszor. Utána azt kell tapasztalnunk, hogy az ásó eltörik, azaz már nem tudjuk többet használni.

Ezeket a lépéseket elvégezve azt vettem észre, hogy az ásás funkció jól működik. Ténylegesen 1 munkaráfordítással el lehet távolítani 2 egység havat. Azonban mikor már negyedik alkalommal akartam használni már nem lett kevesebb a hóréteg a táblán, viszont egy munka egységet ekkor is levont tőlünk. Erre a problémára megoldás lehetne, hogy ha a törékeny ásó eltörik, akkor eltűnik az invetory-ból vagy ha használni akarjuk, akkor nem von le a játékostól munka egységet.

### 5. Hóvihar túlélése igluval

Itt azt teszteljük, hogy az helyesen működik-e. Ezt 2 eszkimóval tettem meg. Az egyikkel a kezdő jégtáblára raktam egy iglut és kiástam róla az összes havat. A második eszkimóval a melette lévő táblára léptem és kiástam onnan is az összes havat viszont ide nem raktam iglut. Ezután a körök léptetésével vártam, arra hogy mikor esik ezen a két mezőn hó. Ezt onnan vettem észre, hogy a UI-on is havas lett a tábla. Miután ez megtörtént megnéztem a két eszkimó testhőjét. Azt tapasztaltam, hogy az az eszkimó aki nem volt igluban, annak lement a testhője, viszont amelyik igluban volt annak a testhője többszöri hóvihar után sem csökkent.

Tehát ki lehet jelenteni, hogy az iglu specifikáció szerint betölti funkcióját.

### 6. DivingSuit működik-e

Ehhez addig generáltam újra a játékmezőt ameddig nem volt a játék elején már látható helyen búvár szemüveg. Ezt az 1-es játékossal felvettem és a HUD-on ekkor a `Diver` jelző megfelelően átváltott `true`-ra. Belesétáltam vele egy lyukba és megvártam amíg újra ő került sorra hogy megnézzem túléli-e. Túlélte és ki is tudott mászni a lyukból, a funkció megfelelően működik.

### 7. Researcher képessége

A Researcher képessége, hogy egy mellette lévő tetszőleges jégtábla kapacitását 1 munkabefektetéssel megtudja. Mivel 3 fajta jégtábla van, ezért mind a három típust tesztelni kell.

Először a lyukat (Hole) teszteltem. Egy Researcher karakterrel mellé a lyuk mellé álltam és használtam a képességét. Ezt a tesztet a jégtábla mind a 4 oldaláról elvégeztem és minden esetben a UI-on megjelent, hogy a lyuk kapacitása 0.

Ránézésre a UI-on a stabil és az instabil jégtábla közötti különbséget nem lehet észrevenni. Ezáltal csak a Researcher képességének használatával tudhatjuk meg, hogy melyik milyen. A stabil jégtáblák kapacitása mindig {játékosok_száma+2} volt míg az instabil jégtáblák kapacitása ennél kisebb pozitív egész. A stabil jégtáblákra ráállva az összes játékossal azt tapasztaltam, hogy nem szakad be. Amelyik jégtáblánál a Researcher képességét használva a kapott kapacitás kevesebb volt mint a játékosok száma, ott ráálltam több játékossal mint a kapacitás. Ennek eredményeképpen az beszakadt és a játék véget ért. Egy kisebb funkcionális hibát azonban felfedeztem. Az instabil jégtábla kapacitása lehet olyan nagy, hogy Stable-ként működjön. Ha {kapacitás=játékosok_száma+1}, akkor ugyan a jégtábla instabil, de ha az összes játékossal rá is állunk, akkor sem törik be, csak akkor ha a jegesmedve is rálép. Ekkor viszont vége van a játéknak mert a medve megtámadja a játékosokat. Ezáltal funkcionálisan tekintve az éppen adott instabil jégtábla stabilként működik.

### 8. Hagyunk valakit megfagyni

Ez a teszt testhő 0-ra csökkenésének eredményét vizsgálja. A testhő csak úgy csökkenhet, hogy hó esik arra a táblára, ahol a játékos éppen áll. Emiatt ez csak úgy tesztelhető, hogy a játékosokat különböző mezőkre állítjuk és a körök léptetésével várjuk a hóviharokat.

A fenti lépéseket megtettem többször is, mindig különböző mennyiségű játékossal. Mind az eszkimóknál, mind a sarkkutatóknál rendeltetésszerű működést tapasztaltam. Amikor esett hó az adott táblára, akkor mindig egyel csökkent az karakter testhője. Amikor nem esett hó akkor pedig nem csökkent a testhő. Amint a testhője bármelyik karakternek 0-ra csökkent, akkor a játék véget ért, ahogy azt a specifikáció alapján vártuk.

### 9. Játék megnyerése

Ennek a manuális tesztelése sok időt vitt volna el ezért annyi könnyítést adtam magamnak hogy fix táblákba helyeztem el az alkatrészeket. Miután begyűjtöttem mindet és egy helyen összegyüjtöttem a játékosokat azonban azzal szembesültem hogy nem lehet megépíteni a rakétát csak akkor ha még a jegesmedve is azon a mezőn van ahol a játékosok.

### 10. Negative Testing

Sok helyen az alkalmazáson belül nem lehet Negative Test-et végrehajtani azonban ahol lehet ott megcsináltuk. A főmenüben a játékos szám megadásánál csak számot enged beírni viszont enged negatív számot is. A játékot ez a továbbiakban nem befolyásolja azonban nem kéne akkor sem engedni, hogy negatív számot megadhassunk.

## Észrevételek és apróbb bug-ok összefoglalása

- A játékos akkor is tud ásni a mezőn ha nincsen rajta mit ásni/kiszabadítani és ebben az esetben is lejjebb megy a staminája.
- A tesztelés során olyan is előfordult hogy a bal felső sarokban levő mező minden szomszédja lyuk volt így az itt kezdő játékosoknak esélyük sem volt elindulniuk.
- A kötél akkor vont le a staminából ha rossz irányban próbáltuk használni.
- A kötéllel kimentett játékos a körét nem 4 hanem 3 staminával kezdte.
- Ha a játék vége előtt még kiadunk például egy mozgás parancsot akkor a következő játék elején csak mozogni fogunk tudni az első lépésünkkel.
- A főmenü enged negatív számú játékost megadni (bizonyos körülmények között).
- Mikor már negyedik alkalommal próbáljuk használni a törékeny ásót, akkor a hóréteg nem csökken, viszont egy munka egységet ekkor is levont tőlünk a játék.
- Az instabil jégtábla kapacitása lehet olyan nagy, hogy stabilként működjön. Ha {kapacitás=játékosok_száma+1}, akkor ugyan a jégtábla instabil, de ha az összes játékossal rá is állunk, akkor sem törik be kivéve, ha a jegesmedve is rálép. Ekkor viszont vége lenne a játéknak mert a medve megtámadja a játékosokat. Ezáltal funkcionálisan tekintve az instabil jégtábla stabilként is tud működni.

## Komoly bug-ok összefoglalása

- Ha azt a játékost leszámítva aki a jegesmedve után következik mindenki lyukban van és vele is belesétálok egy lyukba akkor ha elég gyorsan és megfelelő időben nyomom a mozgás gombokat akkor a jegesmedve után következő játékos másik mezőre kerül és nem ér véget a játék, ez igaz azokra a játékosokra is akik utána jönnek. Valószinűleg azért mert a játékos még mindig azt hiszi a jegesmedve köre van aki tud úszni, viszont a parancs a játékoshoz továbbítódik.
- A játékot megnyerni úgy lehet ha a játékosok megtalálják az összes rakéta alkatrészt és egy mezőn összegyűlve összeszerelik. Azonban ahhoz hogy valóban össze tudják szerelni, a jegesmedvének is rajta kell a mezőn lennie különben a játék nem engedi ezt megtenni.
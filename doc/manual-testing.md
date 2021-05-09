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

### 3. Betörik az Unstable jégtábla

Ehhez kellett keresni egy instabil jégtáblát és rálépni a megfelelő számú játékossal. Ezutána a tábla valóban be is tört.

### 6. DivingSuit működik-e

Ehhez addig generáltam újra a játékmezőt ameddig nem volt a játék elején már látható helyen búvár szemüveg. Ezt az 1-es játékossal felvettem és a HUD-on ekkor a `Diver` jelző megfelelően átváltott `true`-ra. Belesétáltam vele egy lyukba és megvártam amíg újra ő került sorra hogy megnézzem túléli-e. Túlélte és ki is tudott mászni a lyukból, a funkció megfelelően működik.

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

## Komoly bug-ok összefoglalása

- Ha azt a játékost leszámítva aki a jegesmedve után következik mindenki lyukban van és vele is belesétálok egy lyukba akkor ha elég gyorsan és megfelelő időben nyomom a mozgás gombokat akkor a jegesmedve után következő játékos másik mezőre kerül és nem ér véget a játék, ez igaz azokra a játékosokra is akik utána jönnek. Valószinűleg azért mert a játékos még mindig azt hiszi a jegesmedve köre van aki tud úszni, viszont a parancs a játékoshoz továbbítódik.
- A játékot megnyerni úgy lehet ha a játékosok megtalálják az összes rakéta alkatrészt és egy mezőn összegyűlve összeszerelik. Azonban ahhoz hogy valóban össze tudják szerelni, a jegesmedvének is rajta kell a mezőn lennie különben a játék nem engedi ezt megtenni.
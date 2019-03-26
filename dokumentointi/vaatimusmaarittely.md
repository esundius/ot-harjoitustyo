# Vaatimusmäärittely


## Sovelluksen tarkoitus

Sovelluksen avulla opiskelija voi seurata opiskelutyön etenemistä.


## Käyttäjät

Tällä hetkellä en näe tarvetta käyttäjille, mutta voihan sitä miettiä. Jos sovellus laitettaisiin webbiin (mikä ei ole tämän harjoitustyön tarkoitus), niin silloin voisi harkita rekisteröityjä käyttäjiä ja eri käyttäjärooleja. Toisaalta, sovelluksen sisältämä data on suhteellisen henkilökohtaista, joten silloin pitäisi miettiä hiukan tietoturvaa myös.


## Käyttöliittymäluonnos

Sovellus aukeaa alkunäkymään, jossa näkyy lista meneillään olevista kursseista (jos ei olla luotu mitään kursseja vielä, niin lista on luonnollisesti tyhjä). Alkunäkymässä voidaan luoda uusi kurssi, tai siirtyä olemassa olevaan kurssiin.

Kurssinäkymässä näkyy lista kurssiin liittyvistä työtehtävistä. Niitä voidaan lisätä täällä myös. Jokaiselle tehtävälle määritellään ainakin nimi (selite) ja tarvittaessa deadline. Kun tehtävä on lisätty listaan, sen voi merkitä aloitetuksi (jolloin aikaleima tallennetaan), ja kun tehtävä on tehty, merkataan se tehdyksi (jolloin aikaleima taas tallennetaan). Kurssinäkymässä näkyy myös kurssiin käytetyt kokonaistunnit.


## Perusversion tarjoama toiminnallisuus


### Alkunäkymä

- käyttäjä voi luoda uuden kurssin
  - kurssin tiedoissa ainakin kurssikoodi, kurssin nimi, laajuus, alku- ja loppupvm

- käyttäjä voi siirtyä kurssilistassa olevaan kurssin tietoihin
  - kurssilistassa näkyy meneillään olevat sekä mahdollisesti tulevaisuuden kurssit
  - vanhat kurssit piilotetaan

- nappula, josta päästään katsomaan vanhoja kursseja


### Kurssinäkymä

- käyttäjä näkee tekemättömät työtehtävät (mahdollisesti myös tehdyt)

- käyttäjä voi luoda uuden työtehtävän
  - työtehtäävälle määritellään ainakin nimi ja mahdollisesti deadline

- käyttäjä voi merkitä työtehtävän aloitetuksi sekä tehdyksi
  - molemmissa tapauksissa aikaleima tallennetaan

- käyttäjä näkee kokonaistuntimäärän, joka on tehnyt työtä kurssin eteen


## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään ajan salliessa esim. seuraavilla toiminnallisuuksilla

- tehtäväsarjan lisätys
  - esim. kirja (kirjan osatehtävä on tietty kappale, tms.) tai harjoitustehtäväsarja
  - sarjaan voi lisätä tehtäviä, johon voidaan määritellä esim. kappaleen sivumäärä tai harjoitustehtävien määrä
  - tästä voidaan tehdä tilastoa, esim. kuinka nopeasti lukee tietyn kirjan, tai tietyn tehtävätyypin tehtäviä

- ehkä tehtäväsarjakategorioita

- arvioitu aika tehdä tietty tehtävä

- tilastoa:
  - viikoittainen keskimääräinen tuntimäärä tietylle kurssille/kurssityypille
  - työmäärä per opintopiste tietylle kurssille/kurssityypille
  - jne.

- pistetilastoa harjoitustehtäville ja tenteille

- kurssin arvostelu
  - erillinen sivu, jossa tehdyt kurssit, niiden arvostelut, painotettu keskiarvo, jne.

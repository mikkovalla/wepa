# Documentaatio
---
### Esittely
- Sovelluksen tarkoitus on helpottaa duunien hakua ja listausta.
- Työnhakija voi selata työpaikkoja ja vastaa ilmoituksen jättäjälle sähköpostilla.
- Työnantaja voi julkaista työpaikka ilmoituksia ilmaiseksi.

Sovellus on rakennettu Javalla (JDK 1.8) käyttäen Spring Boot sovellus kirjastoa sekä Hibernate ORM kirjastoa, ja riippuvuuksien hallinta tapahtuu Maven:illa. Sovelluksen productio versio on asennettu Heroku PaaS palveluun.

---

### Tuotanto Profiilit

`pom.xml` tiedostosta löytyy `<profiles>` riippuvuus asetukset:
```xml
<profiles>
        <profile>
            <id>default</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <dependencies>
                <dependency>
                    <groupId>com.h2database</groupId>
                    <artifactId>h2</artifactId>
                    <scope>runtime</scope>
                </dependency>
            </dependencies>
        </profile>
        <profile>
            <id>production</id>
            <activation>
                <property>
                    <name>env.DYNO</name>
                </property>
            </activation>
            <dependencies>
                <dependency>
                    <groupId>org.postgresql</groupId>
                    <artifactId>postgresql</artifactId>
                    <scope>runtime</scope>
                </dependency>
            </dependencies>
        </profile>
    </profiles>
```

###### Default
- Tietokantana käytetään H2 lokaali tietokantaa testaukseen.
- Erilliset devloppement luokat on merkattu annotattiolla `@Default`

###### Production
- Ohjelman Heroku toiminnalisuuksia.
- Tietokantana käytetään Herokun tarjoamaa PostgreSql tietokanta.
- Erilliset devloppement luokat on merkattu annotattiolla `@Production`.
- Heroku yhteysluokka `DatabaseConfig.java` löytyy pakkauksesta `wad.config`.
- `Procfile` on herokun vaatima käynnistys tiedosto.
- Polussa `src/main/resources/` on tiedosto `application-production.properties` missä määritetään Heroku kohtaiset asetukset.

---

### Yleiskuvaus
![yleiskuvaus](https://github.com/mikkovalla/wepa/blob/master/documentation/yleiskuvaus.png)

##### Palvelu:
- Palvelu:
Palvelulla tarkoitetaan www palvelua jossa palvelu näkyy ja missä työnhakijat ja työnantajat voivat luoda työpaikkoja sekä etsiä työpaikkoja.
-	Työnhakija:
Työnhakijalla tarkoitetaan käyttäjää joka käyttää palvelua työnhakuun eli selailemaan ilmoituksia, rekisteröitymään ja hakemaan työpaikkoja.
-	Työnantaja:
Työnantajalla tarkoitetaan työntarjoajaa joka rekisteröityy ja ilmoittaa työpaikan.

##### Palvelu käyttötapaukset:

-	Ilmoituksien listaus:
Kuka tahansa voi lukea ilmoituksia, hakea ilmoituksia, ja selata sivuja, mutta hän ei voi hake työpaikkoja.
-	UI:
UI:lla tarkoitetaan sivujen ulkoasua joka on suunniteltu käyttäjän käyttökokemuksen maksimoimiseksi.
-	Ilmoituksen jättäminen:
Rekisteröitynyt työnantaja voi jättää työpaikka ilmoituksen omien sivujensa kautta.
-	Rekisteröityminen:
Kuka tahansa voi rekisteröityä palveluun joko työnantajana tai työnhakijana.

##### Työnhakijan käyttötapaukset:
-	Työnhakija voi rekisteröityä palveluun jonka jälkeen hän voi selata ja hakea työpaikkoja sekä muuttaa tietojaan.

##### Työnantajan käyttötapaukset:
-	Rekisteröityminen:
Työnantaja voi rekisteröityä palveluun.
-	Tietojen muutos:
Työnantaja voi rekisteröitymisen jälkeen muuttaa tietojaan.
-	Ilmoituksen jättäminen:
Työnantaja voi jättää ilmoituksen uudesta työpaikasta.

---

### Relaatiokaavio
![relatiokaavio](https://github.com/mikkovalla/wepa/blob/master/documentation/relatiokaavio.png)

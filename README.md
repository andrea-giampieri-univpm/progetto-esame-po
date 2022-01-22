# Today_S_Forecast


**Repository ufficiale per condivisione sorgente del progetto assegnato per esame di programmazione ad oggetti**
 
Finizii Francesco - Giampieri Andrea

&copy; 2021 

# Progetto
## Specifica orginiale

>
>MACRO TEMA: OPENWEATHER
>
>EXTERNAL API REFERENCE: https://openweathermap.org/current#cityid
>
>OBIETTIVO: "Sviluppare un'applicazione Java che data una città secondo il suo ID, calcoli le statistiche considerando le pressioni e temperatura effettiva. Come API si deve utilizzare escusivamente la current, per cui l'applicazione deve prevedere un salvataggio dei dati orari all interno di un file (JSON,CSV)  L'utente può avere la possibilità di selezionare un singolo giorno oppure una fascia oraria sulla quale eseguire "
>
>STATS E FILTRI: "Statistiche riguardanti valori minimi, massimi, media e varianza dei valori di pressione.  Filtraggio delle statistiche in base alla periodicità: range personalizzabile dall'utente si per le ore che per i giorni. (Es. 7 Giorni)"
    
## Overview

L'implementazione realizzata consente di: 
- visualizzare il meteo e ottenere statistiche tramite pagina web
- visualizzare meteo corrente di una città tramite id o nome
- salvare ogni ora i dati di determinate città su database(offerti dall'API Current di OpenWeatherMap)
- scegliere su quali città effettuare statistiche e impostarne delle predefinite(aggiungendo nel file config.json)
- effettuare operazioni sul database, come ad esempio aggiungere città o rimuoverle
- gestire la configurazione dell'applicazione

Il progetto viene realizzato in Java 17 su framework Spring con Maven gestore dei pacchetti.

### Folder structure
Per non perdersi nei meandri del codice

    .
    └── Today's Forecast             # Home progetto con configurazione Maven
        ├── src                     
        │   ├── main                
        │   │   ├── resources       # Risorse pagina web e configurazioni springboot
        │   │   ├── java            # Source files .java 
            │   └── webapp          
                    └──WEB-INF      # directory contente le pagine web
        │   └── test                # Classi di test
        └── doc                     # Documentazione javadoc
# Implementazione


### Index

| Tipo | Controller | Descrizione |
| :---: | :---: | :--: |
| Get | [/home] | Restituisce il meteo corrente tramite id in formato JSON |
| GET | [/data/currentWeatherById?id={}] | Restituisce il meteo corrente tramite id in formato JSON |
| GET | [/data/currentWeatherByName?name={}] | Restituisce il meteo corrente tramite nome in formato JSON |
| GET | [/data/all] | Restituisce tutti i dati presenti nel database in formato JSON|
| GET | [/data/allCities] | Restituisce tutte le città presenti nel database in formato JSON  |
| GET | [/data/weatherDataByCityId?id={}]| Restituisce in formato json tutti  dati meteo di una determinata città specificandone l'ID  |
| GET | [/data/weatherDataByCityName?name={}] | Restituisce in formato json tutti  dati meteo di una determinata città specificandone il nome  |
| GET | [/data/add] | Aggiunge una città nel database|
| DELETE | [/data/delete] | Rimuove una città dal database|
| DELETE | [/data/deleteAll] | Rimuove tutti i dati dal database|



## Classi

TodaySForecastApplication: Controller di tipo REST che ci permettere di svolgere le operazioni di gestione del database e di visualizzare il meteo corrente.

City : Classe che rappresenta il modello base di una città.

Weather : Classe che rappresenta il meteo di una città.

DataController: Controller di tipo REST che ci permettere di svolgere le operazioni di gestione del database e di visualizzare il meteo corrente.

WebController: Garantisce tramite navigazione web di visualizzare il meteo corrente ed eventualmente di visualizzarne le statistiche meteo nel caso in cui siano presenti i dati nel database relativi alla città in questione.

CityData : Classe che rappresenta una tabella relativa alle città salvate nel database.

WeatherData : Classe che rappresenta una tabella relativa ai dati meteo salvati nel database.
 
Config: Classe per la gestione del file di configurazione.

DataPolling: Gestore delle attività pianificate (chiamate api OWM).

DatabaseManagment: Classe che si occupa di gestire le principali operazioni del database.
 
ConfigException: Eccezioni per la classe Config.

WeatherException: Eccezioni generate in caso di errore durante la creazone di un oggetto Weather.

CityList: classe per il controllo della validità delle città fornite in input.

WeatherStats: sottoclasse di Stats per implementazione statistiche su oggetti CurrentWeather (aggiuntiva).

Statistics: classe che effettua statistiche a partire da una List Integer

Le specifiche delle classi sono contenute nella javadoc.

### Test cases

Classe ConfigTest:

- Case1: "Test lettura parametri errati" : Testo la risposta se cerco di legger un parametro inesistente, suppongo ritorni una stringa vuota.
- Case2:"Test conversione stringa in JSONObject" : Verifico l'effetiva conversione di una stringa in un JSONObject, suppongo non lanci errori.

Classe WeatherTest:

- Case1: "Test creazione oggeto meteo regolare" : Test creazione oggetto regolare, supposto nessun errore lanciato.
- Case2: "Test lettura id errato" : Test creazione oggetto da api owm con id errato, supporto errore.
- Case3: "Test lettura id corretto" : Test creazione oggetto da api owm con id corretto, supposto nessun errore lanciato.


# Istruzioni 

1- Clonare repository

2- Configurare file config.json (inserire apikey ed eventualmente le città su cui effettuare statistiche)

3- (facoltativo) eseguire "./mvnw test" dalla directory per eseguire i test

4- Eseguire con ./mvnw spring-boot:run

NOTA: Richiede jdk-17 installato per compilare

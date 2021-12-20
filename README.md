# PotrebbePiovere

<img src="logo2.png" alt="Logo" width="200" text-align="center"/>


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
- visualizzare temperatura e pressione correnti di una specifica città
- salvare ogni n ore i dati offerti dall'API Current di OpenWeatherMap su file
- richiedere delle statistiche sui dati salvati eventualmente filtrati per data e ora
- gestire la configurazione dell'applicazione

Il progetto viene realizzato in Java 17 su framework Spring con Maven gestore dei pacchetti.

### Folder structure
Per non perdersi nei meandri del codice

    .
    └── PotrebbePiovere             # Home progetto con configurazione Maven
        ├── src                     
        │   ├── main                
        │   │   ├── resources       # Risorse e configurazioni springboot
        │   │   └── java            # Source files .java
        │   └── test                # Classi di test
        ├── doc                     # Documentazione javadoc
        └── bin                     # Bytecode .class

# Implementazione

## API reference

Link Postman di esempio

>https://www.postman.com/agiampi92/workspace/pub/collection/18506679-16e47ed5-af48-4623-a4fa-32c4f0b8867d


### Index

| Tipo | Controller | Descrizione |
| :---: | :---: | :--: |
| GET | [/status](https://github.com/andrea-giampieri-univpm/progetto-esame-po#Status) | Restituisce la running-config come json |
| GET | [/addmonitoring?cityid={}](https://github.com/andrea-giampieri-univpm/progetto-esame-po#AddMonitoring) | Aggiunge una città passata come parametro al monitoraggio |
| GET | [/removemonitoring?cityid={}](https://github.com/andrea-giampieri-univpm/progetto-esame-po#RemoveMonitoring) | Rimuove una città dal monitoraggio  |
| GET | [/getinstant?cityid={}](https://github.com/andrea-giampieri-univpm/progetto-esame-po#GetInstant) | Restituisce in json il meteo corrente di una città data come id  |
| POST | [/getinstantarr](https://github.com/andrea-giampieri-univpm/progetto-esame-po#GetInstantArr) | Restituisce in json il meteo corrente di più id città passate come array json nel body della richiesta  |
| GET | [/getstats?cityid={}](https://github.com/andrea-giampieri-univpm/progetto-esame-po#GetStats) | Restituisce in json le statistiche di una città data come id utilizzando tutti i campioni disponibili |
| GET | [/getstatsfiltered?cityid={}](https://github.com/andrea-giampieri-univpm/progetto-esame-po#GetStatsFiltered) | Restituisce in json le statistiche di una città data come id utilizzando i campioni disponibili all'interno del periodo indicato|
| POST | [/getstatsfiltered/](https://github.com/andrea-giampieri-univpm/progetto-esame-po#GetStatsFilteredArr) | Restituisce in json le statistiche di una città data come id utilizzando i campioni disponibili all'interno dei periodi indicati in un array passato come json|

###  Controller specs

#### Status
Ritorna il json contenente la configurazione: 

    {
        "cities": [
            3183089,
            6540108,
            6542152
        ],
        "h_period": 2,
        "owm_apikey": "123123123123123123123",
        "data_path": "C:\\Users\\utente\\Desktop\\"
    }

#### AddMonitoring

Ritorna il json della nuova configurazione, errore 400 in json in caso di parametro non presente:

    {
        "timestamp": "2021-12-20T20:34:22.675+00:00",
        "status": 400,
        "error": "Bad Request",
        "path": "/addmonitoring"
    }

#### RemoveMonitoring
Ritorna il json della nuova configurazione, errore 400 in json in caso di parametro non presente.

#### GetInstant

Ritorna il json contenente i dati meteo della città richiesta:

    {
        "dt": 1640032281,
        "temp": 10.04,
        "id": 3183089,
        "pressure": 1015.0
    }
    
Errore generico 400 in json (come sopra), errore personalizzato in caso di problemi con la chiamata remota ad openweathermap:
{
    "errordesc": "id non numerico o sbagliato"
}

#### GetInstantArr

La richiesta deve contenere un array json nel body:

    [
            3183089,
            6540108,
            6542152
        ]

Ritorna array json con i dati delle varie città richieste, errore in caso di parametri errati (vedi sopra):

    [
        {
            "dt": 1640032281,
            "temp": 10.04,
            "id": 3183089,
            "pressure": 1015.0
        },
        {
            "dt": 1640032281,
            "temp": 9.24,
            "id": 6540108,
            "pressure": 1021.0
        },
        {
            "dt": 1640032281,
            "temp": 8.25,
            "id": 6542152,
            "pressure": 1023.0
        }
    ]

#### GetStats

#### GetStatsFiltered

#### GetStatsFilteredArr

## Classi

Le descrizioni delle classi sono contenute nella javadoc.

### Test cases

Classe Config:

- Case1: "Test lettura parametro vuoto" : Testo la risposta se cerco di legger un parametro inesistente, suppongo ritorni null.
- Case2:" Test lettura configurazione" : Verifico l'inizializzazione della configurazione, suppongo non lanci errori.

Classe CurrentWeather:

- Case1: "Test stringa regolare" : Test creazione oggetto regolare, supposto nessun errore lanciato.
- Case2: "Test lettura stringa vuota" : Test creazione oggetto da stringa vuota, supposto errore.
- Case3: "Test lettura id errato" : Test creazione oggetto da api owm con id errato, supporto errore.
- Case4: "Test lettura id corretto" : Test creazione oggetto da api owm con id corretto, supposto nessun errore lanciato.
- Case5: "Test tojsonstring" : Verifica output tojsonstring con stringa di esempio, supposto uguale.

# Interfaccia grafica

# Istruzioni 

1- Clonare repository

2- Configurare file config.json

3- (facoltativo) eseguire "./mvnw test" dalla directory per eseguire i test

4- Eseguire con ./mvnw spring-boot:run



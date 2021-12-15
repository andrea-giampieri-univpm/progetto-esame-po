# PotrebbePiovere

<img src="logo.png" alt="Logo" width="200" text-align="center"/>


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
        │   │   └── java            # Source files
        │   └── test                # Classi di tests
        └── ...

# Implementazione

## API reference

### Index

| Tipo | Controller | Descrizione | Parametri | Return |
| :---: | :---: | :--: | :---: | :---: |
| GET | [/status](https://github.com/andrea-giampieri-univpm/progetto-esame-po#Status) | Restituisce la running-config come json |
| GET | [/addmonitoring?cityid={}](https://github.com/andrea-giampieri-univpm/progetto-esame-po#AddMonitoring) | Aggiunge una città passata come parametro al monitoraggio |
| GET | [/removemonitoring?cityid={}](https://github.com/andrea-giampieri-univpm/progetto-esame-po#RemoveMonitoring) | Rimuove una città dal monitoraggio  |
| GET | [/getinstant?cityid={}](https://github.com/andrea-giampieri-univpm/progetto-esame-po#GetInstant) | Restituisce in json il meteo corrente di una città data come id  |
| GET | [/getstats?cityid={}](https://github.com/andrea-giampieri-univpm/progetto-esame-po#GetStats) | Restituisce in json le statistiche di una città data come id utilizzando tutti i campioni disponibili |
| GET | [/getstatsfiltered?cityid={}](https://github.com/andrea-giampieri-univpm/progetto-esame-po#GetStatsFiltered) | Restituisce in json le statistiche di una città data come id utilizzando i campioni disponibili all'interno del periodo indicato|
| POST | [/getstatsfiltered/](https://github.com/andrea-giampieri-univpm/progetto-esame-po#GetStatsFilteredArr) | Restituisce in json le statistiche di una città data come id utilizzando i campioni disponibili all'interno dei periodi indicati in un array passato come json|

###  Controller specs

#### Status

#### AddMonitoring

#### RemoveMonitoring

#### GetInstant

#### GetStats

#### GetStatsFiltered

#### GetStatsFilteredArr

## Classi

ConfigController: Mapping delle rotte per la gestione della configurazione 
CurrentWeatherController: Mapping delle rotte per il meteo attuale 
StatsController: Mapping delle rotte per le statistiche 
DataPolling: Implementazione tramite Scheduler Spring del salvataggio dati temporizzato
Config: Gestisce il modello della configurazione
CurrentWeather: Sottoclasse di OwmCurrentJson che specializza la classe per questa applicazione
OwmCurrentJson: Da json OWM, rappresenta l'oggetto di risposta ricevuto tramite le api "current" di OWM
OwmMain: Da json OWM, per il modello dati principale
OwmSys: Da json OWM, per il modello dati sistema
OwmWeather: Da json OWM, per il modello dati clima
OwmWind: Da json OWM, per il modello dati vento
OwmCoord: Da json OWM, per il modello dati coordinate
OwmClouds: Da json OWM, per il modello dati nuvolosità 

### Interfaces

### Test cases

### Exception handling

# Interfaccia grafica

# Istruzioni 

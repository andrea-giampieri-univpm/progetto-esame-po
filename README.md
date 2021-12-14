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

| Tipo | Controller | Descrizione | Parametri IN | OUT |
| :---: | :---: | :--: | :---: | :---: |
| GET | [ControllerName](https://github.com/andrea-giampieri-univpm/progetto-esame-po#ControllerName) | Restituisce oggetti  | none | json |

###  Controller specs

#### Status

## Classi
| Nome | Descrizione |
| :---: | :---: |
| OwmCurrentJson | Oggetto da json OWM che rappresenta l'oggetto di risposta ricevuto tramite le api "current" di OWM  |
| OwmMain | Oggetto da json OWM per il modello dati principale   |
| OwmSys | Oggetto da json OWM per il modello dati sistema  |
| OwmWeather | Oggetto da json OWM per il modello dati clima   |
| OwmWind | Oggetto da json OWM per il modello dati vento  |
| OwmCoord | Oggetto da json OWM per il modello dati coordinate  |
| OwmClouds | Oggetto da json OWM per il modello dati nuvolosità  |

### Test cases

### Exception handling

## Class Diagrams

# Interfaccia grafica

# Istruzioni 

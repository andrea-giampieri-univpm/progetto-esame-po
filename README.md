# PotrebbePiovere

**Repository ufficiale per condivisione sorgente del progetto assegnato per esame di programmazione ad oggetti**

# Progetto
>**SPECIFICA ORIGINALE**
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
> Per non perdersi nei meandri del codice

    .
    ├── ...
    ├── test                    # Test files (alternatively `spec` or `tests`)
    │   ├── benchmarks          # Load and stress tests
    │   ├── integration         # End-to-end, integration tests (alternatively `e2e`)
    │   └── unit                # Unit tests
    └── ...


# Specifiche

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

## Class Diagrams

# API reference

## Index

| Tipo | Controller | Descrizione | Parametri IN | OUT |
| :---: | :---: | :--: | :---: | :---: |
| GET | [ControllerName](https://github.com/andrea-giampieri-univpm/progetto-esame-po#ControllerName) | Restituisce oggetti  | none | json |

# Test cases

# Exception handling

# GUI

# Istruzioni

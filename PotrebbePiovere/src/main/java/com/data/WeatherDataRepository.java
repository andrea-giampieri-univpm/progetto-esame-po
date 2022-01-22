package com.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaccia che rappresenta un'estensione specifica di JPA.
 * Contiene API per le operazioni CRUD di base.
 * @see DatabaseManagment
 */
@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Integer> {

}

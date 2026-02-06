package it.epicode.gestioneprenotazioni.repositories;

import it.epicode.gestioneprenotazioni.entities.Postazione;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, Long> {

    Optional<Postazione> findByCodice(String codice);
}

package it.epicode.gestioneprenotazioni.repositories;

import it.epicode.gestioneprenotazioni.entities.Utente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    Optional<Utente> findByUsername(String username);
}

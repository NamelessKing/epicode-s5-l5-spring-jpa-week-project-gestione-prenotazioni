package it.epicode.gestioneprenotazioni.repositories;

import it.epicode.gestioneprenotazioni.entities.Prenotazione;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    @Query("""
            select (count(p) > 0)
            from Prenotazione p
            where p.postazione.id = :postazioneId
              and p.dataPrenotazione = :dataPrenotazione
            """)
    boolean existsByPostazioneIdAndDataPrenotazione(
            @Param("postazioneId") Long postazioneId,
            @Param("dataPrenotazione") LocalDate dataPrenotazione
    );

    @Query("""
            select (count(p) > 0)
            from Prenotazione p
            where p.utente.id = :utenteId
              and p.dataPrenotazione = :dataPrenotazione
            """)
    boolean existsByUtenteIdAndDataPrenotazione(
            @Param("utenteId") Long utenteId,
            @Param("dataPrenotazione") LocalDate dataPrenotazione
    );
}

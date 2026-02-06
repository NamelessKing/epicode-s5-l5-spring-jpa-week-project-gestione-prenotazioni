package it.epicode.gestioneprenotazioni.repositories;

import it.epicode.gestioneprenotazioni.entities.Postazione;
import it.epicode.gestioneprenotazioni.entities.TipoPostazione;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, Long> {

    Optional<Postazione> findByCodice(String codice);

    @Query("""
            select p
            from Postazione p
            where p.tipo = :tipo
              and p.edificio.citta = :citta
            """)
    List<Postazione> findByTipoAndCitta(
            @Param("tipo") TipoPostazione tipo,
            @Param("citta") String citta
    );
}

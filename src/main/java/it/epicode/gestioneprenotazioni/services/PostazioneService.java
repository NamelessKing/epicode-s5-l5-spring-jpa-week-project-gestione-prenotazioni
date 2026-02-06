package it.epicode.gestioneprenotazioni.services;

import it.epicode.gestioneprenotazioni.entities.Postazione;
import it.epicode.gestioneprenotazioni.entities.TipoPostazione;
import java.util.List;
import it.epicode.gestioneprenotazioni.repositories.PostazioneRepository;
import org.springframework.stereotype.Service;

@Service
public class PostazioneService {

    private final PostazioneRepository postazioneRepository;

    public PostazioneService(PostazioneRepository postazioneRepository) {
        this.postazioneRepository = postazioneRepository;
    }

    public Postazione save(Postazione postazione) {
        return postazioneRepository.save(postazione);
    }

    public Postazione getByCodice(String codice) {
        return postazioneRepository.findByCodice(codice)
                .orElseThrow(() -> new IllegalArgumentException("Postazione non trovata: " + codice));
    }

    public List<Postazione> findByTipoAndCitta(TipoPostazione tipo, String citta) {
        return postazioneRepository.findByTipoAndCitta(tipo, citta);
    }

    public long count() {
        return postazioneRepository.count();
    }
}

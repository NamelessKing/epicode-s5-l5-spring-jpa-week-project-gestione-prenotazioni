package it.epicode.gestioneprenotazioni.services;

import it.epicode.gestioneprenotazioni.entities.Postazione;
import it.epicode.gestioneprenotazioni.entities.Prenotazione;
import it.epicode.gestioneprenotazioni.entities.Utente;
import it.epicode.gestioneprenotazioni.exceptions.PostazioneNonDisponibileException;
import it.epicode.gestioneprenotazioni.exceptions.PostazioneNotFoundException;
import it.epicode.gestioneprenotazioni.exceptions.UtenteGiaPrenotatoException;
import it.epicode.gestioneprenotazioni.exceptions.UtenteNotFoundException;
import it.epicode.gestioneprenotazioni.repositories.PostazioneRepository;
import it.epicode.gestioneprenotazioni.repositories.PrenotazioneRepository;
import it.epicode.gestioneprenotazioni.repositories.UtenteRepository;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final UtenteRepository utenteRepository;
    private final PostazioneRepository postazioneRepository;

    public PrenotazioneService(
            PrenotazioneRepository prenotazioneRepository,
            UtenteRepository utenteRepository,
            PostazioneRepository postazioneRepository
    ) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.utenteRepository = utenteRepository;
        this.postazioneRepository = postazioneRepository;
    }

    @Transactional
    public Prenotazione creaPrenotazione(Long utenteId, Long postazioneId, LocalDate dataPrenotazione) {
        // controllo se la postazione e gia occupata per quella data
        if (prenotazioneRepository.existsByPostazioneIdAndDataPrenotazione(postazioneId, dataPrenotazione)) {
            throw new PostazioneNonDisponibileException("Postazione non disponibile per la data richiesta.");
        }
        // controllo se l'utente ha gia una prenotazione nella stessa data
        if (prenotazioneRepository.existsByUtenteIdAndDataPrenotazione(utenteId, dataPrenotazione)) {
            throw new UtenteGiaPrenotatoException("L'utente ha gia una prenotazione per questa data.");
        }

        // recupero utente e postazione dal db
        Utente utente = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new UtenteNotFoundException("Utente non trovato: " + utenteId));
        Postazione postazione = postazioneRepository.findById(postazioneId)
                .orElseThrow(() -> new PostazioneNotFoundException("Postazione non trovata: " + postazioneId));

        // creo e salvo la prenotazione
        Prenotazione prenotazione = new Prenotazione(dataPrenotazione, utente, postazione);
        return prenotazioneRepository.save(prenotazione);
    }
}

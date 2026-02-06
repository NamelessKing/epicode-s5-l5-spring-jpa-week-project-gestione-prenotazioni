package it.epicode.gestioneprenotazioni.services;

import it.epicode.gestioneprenotazioni.entities.Utente;
import it.epicode.gestioneprenotazioni.repositories.UtenteRepository;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;

    public UtenteService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    public Utente save(Utente utente) {
        return utenteRepository.save(utente);
    }

    public Utente getByUsername(String username) {
        return utenteRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato: " + username));
    }

    public long count() {
        return utenteRepository.count();
    }
}

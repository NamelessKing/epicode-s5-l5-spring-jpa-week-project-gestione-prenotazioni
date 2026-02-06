package it.epicode.gestioneprenotazioni.services;

import it.epicode.gestioneprenotazioni.entities.Edificio;
import it.epicode.gestioneprenotazioni.repositories.EdificioRepository;
import org.springframework.stereotype.Service;

@Service
public class EdificioService {

    private final EdificioRepository edificioRepository;

    public EdificioService(EdificioRepository edificioRepository) {
        this.edificioRepository = edificioRepository;
    }

    public Edificio save(Edificio edificio) {
        return edificioRepository.save(edificio);
    }

    public long count() {
        return edificioRepository.count();
    }
}

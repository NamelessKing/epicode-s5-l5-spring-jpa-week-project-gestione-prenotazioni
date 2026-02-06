package it.epicode.gestioneprenotazioni.runners;

import it.epicode.gestioneprenotazioni.entities.Edificio;
import it.epicode.gestioneprenotazioni.entities.Postazione;
import it.epicode.gestioneprenotazioni.entities.TipoPostazione;
import it.epicode.gestioneprenotazioni.entities.Utente;
import it.epicode.gestioneprenotazioni.services.EdificioService;
import it.epicode.gestioneprenotazioni.services.PostazioneService;
import it.epicode.gestioneprenotazioni.services.UtenteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class SeedDataRunner implements CommandLineRunner {

    private final EdificioService edificioService;
    private final PostazioneService postazioneService;
    private final UtenteService utenteService;

    public SeedDataRunner(
            EdificioService edificioService,
            PostazioneService postazioneService,
            UtenteService utenteService
    ) {
        this.edificioService = edificioService;
        this.postazioneService = postazioneService;
        this.utenteService = utenteService;
    }

    @Override
    public void run(String... args) {
        if (edificioService.count() > 0 || postazioneService.count() > 0 || utenteService.count() > 0) {
            return;
        }

        Edificio edificioMilano = edificioService.save(new Edificio(
                "ED Milano",
                "Via Roma 10",
                "Milano"
        ));
        Edificio edificioRoma = edificioService.save(new Edificio(
                "ED Roma",
                "Via Appia 22",
                "Roma"
        ));

        postazioneService.save(new Postazione(
                "P-001",
                "Postazione privata",
                TipoPostazione.PRIVATO,
                1,
                edificioMilano
        ));
        postazioneService.save(new Postazione(
                "P-002",
                "Open space",
                TipoPostazione.OPENSPACE,
                4,
                edificioMilano
        ));
        postazioneService.save(new Postazione(
                "P-003",
                "Sala riunioni",
                TipoPostazione.SALA_RIUNIONI,
                8,
                edificioRoma
        ));

        utenteService.save(new Utente(
                "mrossi",
                "Marco Rossi",
                "marco.rossi@example.com"
        ));
        utenteService.save(new Utente(
                "lgialli",
                "Luca Gialli",
                "luca.gialli@example.com"
        ));
    }
}

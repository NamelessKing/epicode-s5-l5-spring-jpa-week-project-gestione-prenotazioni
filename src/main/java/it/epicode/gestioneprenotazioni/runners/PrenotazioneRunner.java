package it.epicode.gestioneprenotazioni.runners;

import it.epicode.gestioneprenotazioni.entities.Postazione;
import it.epicode.gestioneprenotazioni.entities.Prenotazione;
import it.epicode.gestioneprenotazioni.entities.TipoPostazione;
import it.epicode.gestioneprenotazioni.entities.Utente;
import it.epicode.gestioneprenotazioni.services.PostazioneService;
import it.epicode.gestioneprenotazioni.services.PrenotazioneService;
import it.epicode.gestioneprenotazioni.services.UtenteService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class PrenotazioneRunner implements CommandLineRunner {

    // service usati per recuperare dati e creare prenotazioni
    private final PostazioneService postazioneService;
    private final UtenteService utenteService;
    private final PrenotazioneService prenotazioneService;

    public PrenotazioneRunner(
            PostazioneService postazioneService,
            UtenteService utenteService,
            PrenotazioneService prenotazioneService
    ) {
        this.postazioneService = postazioneService;
        this.utenteService = utenteService;
        this.prenotazioneService = prenotazioneService;
    }

    @Override
    public void run(String... args) {
        // prendo utenti e postazioni gia presenti nel db
        Utente utente1 = utenteService.getByUsername("mrossi");
        Utente utente2 = utenteService.getByUsername("lgialli");

        var postazioneA = postazioneService.getByCodice("P-001");
        var postazioneB = postazioneService.getByCodice("P-002");

        // prenotazione per il giorno dopo
        LocalDate data = LocalDate.now().plusDays(1);

        // creo una prenotazione valida
        Prenotazione prenotazione = prenotazioneService.creaPrenotazione(
                utente1.getId(),
                postazioneA.getId(),
                data
        );
        System.out.println("Prenotazione creata: " + prenotazione);

        // provo a prenotare la stessa postazione nella stessa data (fallisce)
        try {
            prenotazioneService.creaPrenotazione(utente2.getId(), postazioneA.getId(), data);
        } catch (IllegalStateException ex) {
            System.out.println("Prenotazione non creata (postazione occupata): " + ex.getMessage());
        }

        // provo a prenotare una seconda postazione per lo stesso utente e data (fallisce)
        try {
            prenotazioneService.creaPrenotazione(utente1.getId(), postazioneB.getId(), data);
        } catch (IllegalStateException ex) {
            System.out.println("Prenotazione non creata (utente gia prenotato): " + ex.getMessage());
        }

        // ricerca postazioni per tipo e citta
        List<Postazione> risultati = postazioneService.findByTipoAndCitta(TipoPostazione.OPENSPACE, "Milano");
        System.out.println("Postazioni OPENSPACE a Milano: " + risultati.size());
        risultati.forEach(postazione -> System.out.println(" - " + postazione));

        // esempio di ricerca senza risultati
        List<Postazione> nessunRisultato = postazioneService.findByTipoAndCitta(TipoPostazione.PRIVATO, "Roma");
        System.out.println("Postazioni PRIVATO a Roma: " + nessunRisultato.size());
    }
}

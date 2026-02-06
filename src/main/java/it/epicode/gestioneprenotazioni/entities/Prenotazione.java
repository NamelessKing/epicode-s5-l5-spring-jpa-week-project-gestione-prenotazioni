package it.epicode.gestioneprenotazioni.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;

@Entity
@Table(
        name = "prenotazione",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_prenotazione_postazione_data",
                        columnNames = {"postazione_id", "data_prenotazione"}
                ),
                @UniqueConstraint(
                        name = "uq_prenotazione_utente_data",
                        columnNames = {"utente_id", "data_prenotazione"}
                )
        }
)
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_prenotazione", nullable = false)
    private LocalDate dataPrenotazione;

    @ManyToOne(optional = false)
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "postazione_id", nullable = false)
    private Postazione postazione;

    public Prenotazione() {
    }

    public Prenotazione(LocalDate dataPrenotazione, Utente utente, Postazione postazione) {
        this.dataPrenotazione = dataPrenotazione;
        this.utente = utente;
        this.postazione = postazione;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(LocalDate dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Postazione getPostazione() {
        return postazione;
    }

    public void setPostazione(Postazione postazione) {
        this.postazione = postazione;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", dataPrenotazione=" + dataPrenotazione +
                ", utenteId=" + (utente != null ? utente.getId() : null) +
                ", postazioneId=" + (postazione != null ? postazione.getId() : null) +
                '}';
    }
}

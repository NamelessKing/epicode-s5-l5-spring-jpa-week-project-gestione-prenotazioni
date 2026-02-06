package it.epicode.gestioneprenotazioni.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "postazione",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_postazione_codice", columnNames = "codice")
        }
)
public class Postazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String codice;

    @Column(nullable = false, columnDefinition = "text")
    private String descrizione;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoPostazione tipo;

    @Column(name = "max_occupanti", nullable = false)
    private int maxOccupanti;

    @ManyToOne(optional = false)
    @JoinColumn(name = "edificio_id", nullable = false)
    private Edificio edificio;

    public Postazione() {
    }

    public Postazione(String codice, String descrizione, TipoPostazione tipo, int maxOccupanti, Edificio edificio) {
        this.codice = codice;
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.maxOccupanti = maxOccupanti;
        this.edificio = edificio;
    }

    public Long getId() {
        return id;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public TipoPostazione getTipo() {
        return tipo;
    }

    public void setTipo(TipoPostazione tipo) {
        this.tipo = tipo;
    }

    public int getMaxOccupanti() {
        return maxOccupanti;
    }

    public void setMaxOccupanti(int maxOccupanti) {
        this.maxOccupanti = maxOccupanti;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    @Override
    public String toString() {
        return "Postazione{" +
                "id=" + id +
                ", codice='" + codice + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", tipo=" + tipo +
                ", maxOccupanti=" + maxOccupanti +
                ", edificioId=" + (edificio != null ? edificio.getId() : null) +
                '}';
    }
}

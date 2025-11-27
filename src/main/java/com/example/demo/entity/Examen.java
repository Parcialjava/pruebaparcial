package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "examenes")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // NUEVO: relacionamos con el id interno del usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @Column(name = "total")
    private Integer total;

    @Column(name = "anulado")
    private Boolean anulado = false;

    @Column(name = "comunica")
    private Integer comunica;

    @Column(name = "razcuant")
    private Integer razcuant;

    @Column(name = "lectcrit")
    private Integer lectcrit;

    @Column(name = "compciud")
    private Integer compciud;

    @Column(name = "ingles")
    private Integer ingles;

    @Column(name = "proyingen")
    private Integer proyingen;

    @Column(name = "pcientifico")
    private Integer pcientifico;

    @Column(name = "disensoft")
    private Integer disensoft;

    @Column(name = "escalaingles", length = 10)
    private String escalaingles;

    public Examen() {}

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }

    public Boolean getAnulado() { return anulado; }
    public void setAnulado(Boolean anulado) { this.anulado = anulado; }

    public Integer getComunica() { return comunica; }
    public void setComunica(Integer comunica) { this.comunica = comunica; }

    public Integer getRazcuant() { return razcuant; }
    public void setRazcuant(Integer razcuant) { this.razcuant = razcuant; }

    public Integer getLectcrit() { return lectcrit; }
    public void setLectcrit(Integer lectcrit) { this.lectcrit = lectcrit; }

    public Integer getCompciud() { return compciud; }
    public void setCompciud(Integer compciud) { this.compciud = compciud; }

    public Integer getIngles() { return ingles; }
    public void setIngles(Integer ingles) { this.ingles = ingles; }

    public Integer getProyingen() { return proyingen; }
    public void setProyingen(Integer proyingen) { this.proyingen = proyingen; }

    public Integer getPcientifico() { return pcientifico; }
    public void setPcientifico(Integer pcientifico) { this.pcientifico = pcientifico; }

    public Integer getDisensoft() { return disensoft; }
    public void setDisensoft(Integer disensoft) { this.disensoft = disensoft; }

    public String getEscalaingles() { return escalaingles; }
    public void setEscalaingles(String escalaingles) { this.escalaingles = escalaingles; }
}

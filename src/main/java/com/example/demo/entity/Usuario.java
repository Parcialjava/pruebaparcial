package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // ID interno autogenerado

    @Column(name = "numdoc", nullable = false, unique = true, length = 50)
    private String numdoc; // identificación (CEDULA) - usada para login

    @Column(name = "tdoc", length = 20)
    private String tdoc;

    @Column(name = "papellido", length = 100)
    private String papellido;

    @Column(name = "sapellido", length = 100)
    private String sapellido;

    @Column(name = "pnomb", length = 100)
    private String pnomb;

    @Column(name = "snomb", length = 100)
    private String snomb;

    @Column(name = "correo", length = 150)
    private String correo;

    @Column(name = "password", length = 200)
    private String password;

    @Column(name = "telefono", length = 50)
    private String telefono;

    @Column(name = "rol", length = 50)
    private String rol; // "ADMIN", "COORDINADOR", "ALUMNO"

    @Column(name = "registro", length = 100)
    private String registro; // solo para alumnos

    public Usuario() {}

    public Usuario(String numdoc, String tdoc, String papellido, String sapellido,
                   String pnomb, String snomb, String correo, String password,
                   String telefono, String rol, String registro) {
        this.numdoc = numdoc;
        this.tdoc = tdoc;
        this.papellido = papellido;
        this.sapellido = sapellido;
        this.pnomb = pnomb;
        this.snomb = snomb;
        this.correo = correo;
        this.password = password;
        this.telefono = telefono;
        this.rol = rol;
        this.registro = registro;
    }

    // GETTERS / SETTERS (incluye el id)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumdoc() { return numdoc; }
    public void setNumdoc(String numdoc) { this.numdoc = numdoc; }

    public String getTdoc() { return tdoc; }
    public void setTdoc(String tdoc) { this.tdoc = tdoc; }

    public String getPapellido() { return papellido; }
    public void setPapellido(String papellido) { this.papellido = papellido; }

    public String getSapellido() { return sapellido; }
    public void setSapellido(String sapellido) { this.sapellido = sapellido; }

    public String getPnomb() { return pnomb; }
    public void setPnomb(String pnomb) { this.pnomb = pnomb; }

    public String getSnomb() { return snomb; }
    public void setSnomb(String snomb) { this.snomb = snomb; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getRegistro() { return registro; }
    public void setRegistro(String registro) { this.registro = registro; }
}

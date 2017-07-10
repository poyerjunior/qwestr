/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

/**
 *
 * @author pcstr
 */
public class Vaga {
    private int id;
    private String nome;
    private String descricao;
    private boolean prova;
    private VagaCategoria VagaCategoria;
    private Empresa Empresa;
    private List<Candidatura> lstcandidatura;
    private Prova Prova;

    public List<Candidatura> getLstcandidatura() {
        return lstcandidatura;
    }

    public void setLstcandidatura(List<Candidatura> lstcandidatura) {
        this.lstcandidatura = lstcandidatura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isProva() {
        return prova;
    }

    public void setProva(boolean prova) {
        this.prova = prova;
    }

    public VagaCategoria getVagaCategoria() {
        return VagaCategoria;
    }

    public void setVagaCategoria(VagaCategoria VagaCategoria) {
        this.VagaCategoria = VagaCategoria;
    }

    public Empresa getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(Empresa Empresa) {
        this.Empresa = Empresa;
    }

    public Prova getProva() {
        return Prova;
    }

    public void setProva(Prova Prova) {
        this.Prova = Prova;
    }
    
    
}

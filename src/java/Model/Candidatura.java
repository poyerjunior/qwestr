/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author sergio.poyer
 */
public class Candidatura {
    private int id;
    private Date date;
    private Candidato Candidato;
    private Vaga Vaga;
    private CandidaturaStatus CandidaturaStatus;

    public CandidaturaStatus getCandidaturaStatus() {
        return CandidaturaStatus;
    }

    public void setCandidaturaStatus(CandidaturaStatus CandidaturaStatus) {
        this.CandidaturaStatus = CandidaturaStatus;
    }

    public Boolean getAprovacao() {
        return aprovacao;
    }

    public void setAprovacao(Boolean aprovacao) {
        this.aprovacao = aprovacao;
    }
    
    private Boolean aprovacao;

    public int getId() {
        return id;
    }

    public Vaga getVaga() {
        return Vaga;
    }

    public void setVaga(Vaga Vaga) {
        this.Vaga = Vaga;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Candidato getCandidato() {
        return Candidato;
    }

    public void setCandidato(Candidato Candidato) {
        this.Candidato = Candidato;
    }

    public boolean isAprovacao() {
        return aprovacao;
    }

    public void setAprovacao(boolean aprovacao) {
        this.aprovacao = aprovacao;
    }
    
}

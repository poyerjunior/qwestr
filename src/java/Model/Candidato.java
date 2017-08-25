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
public class Candidato {
    private int id;
    private String nome;
    private String cpf;
    private String curriculo;
    private String email;
    private String senha;
    private List<Candidatura> lstcandidatura;
    private String questoes;

    public String getQuestoes() {
        return questoes;
    }

    public void setQuestoes(String questoes) {
        this.questoes = questoes;
    }

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(String curriculo) {
        this.curriculo = curriculo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}

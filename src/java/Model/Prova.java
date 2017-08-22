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
public class Prova {
    private int id;
    private String nome;
    private List<Questao> lstQuestao;

    public List<Questao> getLstQuestao() {
        return lstQuestao;
    }

    public void setLstQuestao(List<Questao> lstQuestao) {
        this.lstQuestao = lstQuestao;
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
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author sergio.poyer
 */
public class CandidatoQuestao {
    private Candidato Candidato;
    private Questao Questao;
    private String resposta;

    public Candidato getCandidato() {
        return Candidato;
    }

    public void setCandidato(Candidato Candidato) {
        this.Candidato = Candidato;
    }

    public Questao getQuestao() {
        return Questao;
    }

    public void setQuestao(Questao Questao) {
        this.Questao = Questao;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
    
    
}

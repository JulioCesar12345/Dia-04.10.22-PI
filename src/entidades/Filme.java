/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author julio
 */
public class Filme extends Item {

    private String diretor;
    private int duracao;

    public Filme(Categoria categoria) {
        super(categoria);
    }

    /**
     * @return the diretor
     */
    public String getDiretor() {
        return diretor;
    }

    /**
     * @param diretor the diretor to set
     */
    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    /**
     * @return the duracao
     */
    public int getDuracao() {
        return duracao;
    }

    /**
     * @param duracao the duracao to set
     */
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String toString() {
        return "Filme \n"
                + " ID: " + getId() + "\n"
                + " Diretor:  " + diretor + "\n"
                + " Duracao:  " + duracao + "\n"
                + " Titulo:  " + getTitulo() + "\n"
                + " Descrição:  " + getDescricao() + "\n"
                + " Preço:  " + getPreco() + "\n"
                + " N° Dias  " + getNumeroDias() + "\n"
                + " Categoria:  " + getCategoria() + "\n"
                + '}';
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/*******************************************************************************
Autores: Bianca Santana de Araújo Silva e Paulo Queiroz de Carvalho
Componente Curricular: MI programação
Concluido em: 20/03/2020
Declaramos que este código foi elaborado por nós, em dupla e não contém nenhum 
trecho de código de outro colega ou de outro autor, tais como provindos de livros e 
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a nossa está destacado com uma citação para o autor e a fonte
do código, e estamos ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

public class Aresta {
    private int peso;
    private Vertice v1;
    private Vertice v2;
    private boolean visitado = false;

    /**
     * Método construtor, da classe Aresta.
     * @param peso
     * @param origem
     * @param destino
     */
    public Aresta(int peso, Vertice origem, Vertice destino) {
            this.setPeso(peso);
            this.setV1(origem);
            this.setV2(destino);
    }

    /**
     * Método getPeso, da classe Aresta.
     * @return int
     */
    public int getPeso() {
        return peso;
    }

    /**
     * Método setPeso, da classe Aresta.
     * @param peso
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    /**
     * Método getV1, da classe Aresta.
     * @return Vertice
     */
    public Vertice getV1() {
        return v1;
    }

    /**
     * Método setV1, da classe Aresta.
     * @param v1
     */
    public void setV1(Vertice v1) {
        this.v1 = v1;
    }

    /**
     * Método getV2, da classe Aresta.
     * @return Vertice
     */
    public Vertice getV2() {
        return v2;
    }

    /**
     * Método setV2, da classe Aresta.
     * @param v2
     */
    public void setV2(Vertice v2) {
        this.v2 = v2;
    }

    /**
     * Método isVisitado, da classe Aresta.
     * @return boolean 
     */
    public boolean isVisitado() {
        return visitado;
    }

    /**
     * Método setVisitado, da classe Aresta.
     * @param visitado
     */
    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    /**
     * Método toString, da classe Aresta.
     * @return String
     */
    @Override
    public String toString() {
        String s = " ";
        s+= this.v1.getNome()+ this.v2.getNome();
        return s;
    }
}

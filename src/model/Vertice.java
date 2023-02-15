/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

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

public class Vertice implements Comparable<Vertice>  {
    private String nome;
    private int distancia = 0;
    private Vertice antecessor;
    private float x, y;
    private ArrayList<Aresta> arestaConectada = new ArrayList<Aresta>();
    private ArrayList<Vertice> verticeVizinho = new ArrayList<Vertice>();
    private boolean visitado = false;
    private boolean terminal;

    /**
     * Método construtor, da classe Vertice.
     * @param nome
     * @param terminal
     * @param x
     * @param y
     */
    public Vertice(String nome,boolean terminal, float x, float y) {
        this.x = x;
        this.y = y;
        this.nome = nome;
        this.antecessor = antecessor;
        this.terminal = terminal;
    }

    /**
     * Método getX, da classe Vertice.
     * @return float
     */
    public float getX() {
        return x;
    }

    /**
     * Método setX, da classe Vertice.
     * @param x
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Método getY, da classe Vertice.
     * @return float
     */
    public float getY() {
        return y;
    }

    /**
     * Método setY, da classe Vertice.
     * @param y
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Método getNome, da classe Vertice.
     * @return String
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método setNome, da classe Vertice.
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método getDistancia, da classe Vertice.
     * @return int
     */
    public int getDistancia() {
        return distancia;
    }

    /**
     * Método setDistancia, da classe Vertice.
     * @param distancia
     */
    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    /**
     * Método getAntecessor, da classe Vertice.
     * @return Vertice
     */
    public Vertice getAntecessor() {
        return antecessor;
    }

    /**
     * Método setAntecessor, da classe Vertice.
     * @param antecessor
     */
    public void setAntecessor(Vertice antecessor) {
        this.antecessor = antecessor;
    }

    /**
     * Método getArestaConectada, da classe Vertice.
     * @return ArrayList
     */
    public ArrayList<Aresta> getArestaConectada() {
        return arestaConectada;
    }

    /**
     * Método setArestaConectada, da classe Vertice.
     * @param arestaConectada
     */
    public void setArestaConectada(ArrayList<Aresta> arestaConectada) {
        this.arestaConectada = arestaConectada;
    }

    /**
     * Método getVerticeVizinho, da classe Vertice.
     * @return ArrayList
     */
    public ArrayList<Vertice> getVerticeVizinho() {
        return verticeVizinho;
    }

    /**
     *
     * @param verticeVizinho
     */
    public void setVerticeVizinho(ArrayList<Vertice> verticeVizinho) {
        this.verticeVizinho = verticeVizinho;
    }

    /**
     *
     * @return boolean
     */
    public boolean isVisitado() {
        return visitado;
    }

    /**
     *
     * @param visitado
     */
    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    /**
     *
     * @return boolean
     */
    public boolean isTerminal() {
        return terminal;
    }
    
    /**
     *
     * @param terminal
     */
    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    /**
     *
     * @param incide
     */
    public void addArestaConectada(Aresta incide) {
            this.arestaConectada.add(incide);

            //adicionando os vertices vizinhos a lista se existir v1 e não existir o v2 na lista 
            if ( (incide.getV1().getNome().equals(this.getNome())) &&(!this.isVizinho(incide.getV2())) ){
                this.addVizinhos(incide.getV2());
             //adicionando os vertices vizinhos a lista se existir v2 e não existir o v1 na lista 
            }else if ( (incide.getV2().getNome().equals(this.getNome())) && (!this.isVizinho(incide.getV1())) ){
                this.addVizinhos(incide.getV1());
            }
    }
    
    /**
     *
     * @param vizinho
     */
    public void addVizinhos(Vertice vizinho) {
            this.verticeVizinho.add(vizinho);
    }

    /**
     *
     * @return ArrayList
     */
    public ArrayList<Vertice> getVizinhos() {
            return verticeVizinho;
    }

    /**
     *
     * @param vizinho
     * @return boolean
     */
    public boolean isVizinho(Vertice vizinho){
        for (int i=0; i<this.verticeVizinho.size() ; i++)
            if (this.verticeVizinho.get(i).getNome().equals(vizinho.getNome()))
                return true;		

        return false;
    }
    
    /**
     *
     * @param vertice
     * @return int
     */
    @Override
    public int compareTo(Vertice vertice) {
        // se a distancia for menor que a distancia que existe no vertice ela não existe
        if(this.getDistancia() < vertice.getDistancia()) 
            return -1;
        else if(this.getDistancia() == vertice.getDistancia()) 
            return 0;

        return 1;  
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        String s = this.getNome();
        return s;
    }
}

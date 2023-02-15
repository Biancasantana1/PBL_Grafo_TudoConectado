/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Aresta;
import model.Vertice;
import java.util.ArrayList;
import java.util.Collections;

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

public class Grafo {
    private ArrayList<Aresta> arestas = new ArrayList<Aresta>();
    private ArrayList<Vertice> vertices = new ArrayList<Vertice>();
    
    /**
     * Método getArestas, da classe Grafo.
     * @return ArrayList
     */
    public ArrayList<Aresta> getArestas() {
        return arestas;
    }

    /**
     * Método setArestas, da classe Grafo.
     * @param arestas
     */
    public void setArestas(ArrayList<Aresta> arestas) {
        this.arestas = arestas;
    }

    /**
     * Método getVertices, da classe Grafo.
     * @return ArrayList
     */
    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    /**
     * Método getArestas, da classe Grafo.
     * @param vertices
     */
    public void setVertices(ArrayList<Vertice> vertices) {
        this.vertices = vertices;
    }

    /**
     * Método posicaoVertice, da classe Grafo.
     * Esse método procura na lista de vértices se aquele vertice já existe, caso exista retorna a posição.
     * @param nome
     * @return int
     */
    public int posicaoVertice(String nome){
        for (int i=0; i<this.vertices.size() ; i++)
            if (this.vertices.get(i).getNome().equals(nome))
                return i;
        //Caso nao encontre retorna o tamanho da lista vertices
        return this.vertices.size();
    }

    /**
     * Método addVertice, da classe Grafo.
     * Esse método adiciona os vértices na lista de vértices.
     * @param nome
     * @param terminal
     * @param x
     * @param y
     * @return int
     */
    public int addVertice(String nome, boolean terminal, float x, float y){
        int i = this.posicaoVertice(nome);
        // se a posição do vertice for igual a quantidade de vertices adiciona na lista e retorna o ultimo
        if(i == this.vertices.size()){
            this.vertices.add(new Vertice(nome,terminal,x,y));
            return (this.vertices.size() - 1);
        }
        else{
            System.out.println("Vétice já existe");
        return i;// retorna o vertice já existente
        }
    }
    
    /**
     * Método addAresta, da classe Grafo.
     * Esse método adiciona as arestas na lista de arestas.
     * @param peso
     * @param v1
     * @param v2
     */
    public void addAresta(int peso, String v1, String v2){
        int aux1,aux2,aux3;
        //adiciona vertices e retorna sua posicao salvando nas variavei
        aux1 = this.posicaoVertice(v1); // aux1 armazena origem
        aux2 = this.posicaoVertice(v2); // aux2 armazena destino
        if(this.acharAresta(this.acharVertice(v1), this.acharVertice(v2)) != null){
            System.out.println("Aresta ja existente");
            return;
        }
        if((aux1 != this.vertices.size()) && (aux2 != this.vertices.size())){
            if((aux1 == aux2)){
                System.out.println("Não é possível selecionar o vértice de origem igual ao vértice destino");
            }
            else{
                Aresta a = new Aresta(peso,this.vertices.get(aux1),this.vertices.get(aux2));
                this.arestas.add(a); //adiciona aresta na lista
                aux3 = this.arestas.size();// aux3 armazena a quantidade total de vertices
                //adiciona aresta na lista de arestas conectada com cada vertice
                this.vertices.get(aux1).addArestaConectada(this.arestas.get(aux3-1));
                this.vertices.get(aux2).addArestaConectada(this.arestas.get(aux3-1));
            }
        }
        
        else
            System.out.println("Vértice não existe, não foi possível adicionar aresta.");
    }
    
    /**
     * Método acharAresta, da classe Grafo.
     * Procura a posição da aresta na lista.
     * @param v1
     * @param v2
     * @return Aresta
     */
    public Aresta acharAresta(Vertice v1, Vertice v2){
        
        for(int i=0; i<this.arestas.size();i++){
            if( ((this.arestas.get(i).getV1().getNome().equals(v1.getNome())) && // procura a origem e 
                (this.arestas.get(i).getV2().getNome().equals(v2.getNome()))) || // procura o destino se existir retorna a 
                ((this.arestas.get(i).getV1().getNome().equals(v2.getNome())) && // posição da aresta
                (this.arestas.get(i).getV2().getNome().equals(v1.getNome()))) ){
                return this.arestas.get(i);
            }
        }
        return null;
    }
    
    public Vertice acharVertice(String chave){
        Vertice encontrado = null;
        for (int i = 0; i < this.vertices.size(); i++) {
            if (this.vertices.get(i).getNome().equals(chave)){
                encontrado = this.vertices.get(i);
                break;
            }
        }
        return encontrado;
    }
    
    
    
    /**
     *
     */
    protected void limparVerticesAntecessores(){
        for(int i=0; i<this.vertices.size() ;i++)
            this.vertices.get(i).setAntecessor(null);
    }
    
    /**
     *
     */
    protected void limparVerticesVisitados(){
        for(int i=0; i<this.vertices.size() ;i++)
            this.vertices.get(i).setVisitado(false);
    }
    //

    /**
     * Método encontrarMenorCaminhoDijkstra, da classe Grafo.
     * Esse método retorna o caminho menos custoso entre dois vertices a partida do algoritmo de Dijkstra.
     * @param ve1
     * @param ve2
     * @return Arraylist
     */
public ArrayList<Vertice> encontrarMenorCaminhoDijkstra(String ve1,String ve2) {
        // Lista que guarda os vertices pertencentes ao menor caminho encontrado
    	ArrayList<Vertice> menorCaminho = new ArrayList<Vertice>();
        // Variavel que recebe os vertices pertencentes ao menor caminho, o vertice que esta sendo visitado e  
        // marca o vizinho do vertice atualmente visitado
        Vertice verticeCaminho, atual ,vizinho;
        Aresta ligacao;// Aresta que liga o atual ao seu vizinho;
        ArrayList<Vertice> naoVisitados = new ArrayList<Vertice>();// Lista dos vertices que ainda nao foram visitados
        int aux1 = this.posicaoVertice(ve1);
        int aux2 = this.posicaoVertice(ve2);
        Vertice v1 = this.vertices.get(aux1);
        Vertice v2 = this.vertices.get(aux2);
        menorCaminho.add(v1); // Adiciona a origem na lista do menor caminho
        if(v1.getArestaConectada().size() == 0 || v2.getArestaConectada().size() == 0)
            return null;
        else{
            if(v1.isTerminal() != false){ // limitando para só realizar o dijkstra se for terminal          
                for (int i = 0; i < this.vertices.size(); i++) { // Colocando a distancias iniciais 
                    if (this.vertices.get(i).getNome().equals(v1.getNome())) // Vertice atual tem distancia zero, e todos os outros,
                        this.vertices.get(i).setDistancia(0);
                    else
                        this.vertices.get(i).setDistancia((int) Double.MAX_VALUE);// MAX_VOLUE = "infinito"
                    naoVisitados.add(this.vertices.get(i));// Insere o vertice na lista de vertices nao visitados
                }
                Collections.sort(naoVisitados);// ordena a lista de não visitados 
                while (!naoVisitados.isEmpty()) {// O algoritmo continua ate que todos os vertices sejam visitados  
                    atual = naoVisitados.get(0); // Toma-se sempre o vertice com menor distancia, que é o primeiro da lista
                    /*Para cada vizinho (cada aresta), calcula-se a sua possivel distancia, somando a distancia do vertice atual
                     *com a da aresta correspondente. Se essa distancia for menor que a distancia do vizinho, esta eh atualizada. */
                    for (int i = 0; i < atual.getVizinhos().size(); i++) {
                        vizinho = atual.getVizinhos().get(i);
                        if (!vizinho.isVisitado()) {
                            // Comparando a distância do vizinho com a possível distância
                                ligacao = this.acharAresta(atual,vizinho);
                            if (vizinho.getDistancia() > (atual.getDistancia() + ligacao.getPeso())) {
                                vizinho.setDistancia(atual.getDistancia() + ligacao.getPeso());
                                vizinho.setAntecessor(atual);
                                /* Se o vizinho é o vertice procurado, e foi feita uma mudanca na distancia, a lista com o menor
                                * caminho anterior é apagada, pois existe um caminho menor vertices antecessores, até o vertice origem.*/
                                if (vizinho == v2) {
                                    menorCaminho.clear();
                                    verticeCaminho = vizinho;
                                    menorCaminho.add(vizinho);
                                    while (verticeCaminho.getAntecessor() != null) {
                                        menorCaminho.add(verticeCaminho.getAntecessor());
                                        verticeCaminho = verticeCaminho.getAntecessor();
                                    }
                                    // Ordena a lista do menor caminho, para que ele seja exibido da origem ao destino.
                                    Collections.sort(menorCaminho);
                                }
                            }
                        }
                    }
                    atual.setVisitado(true);  // Marca o vertice atual como visitado e o retira da lista de nao visitados
                    naoVisitados.remove(atual);
                    Collections.sort(naoVisitados);// Ordena a lista, para que o vertice com menor distancia fique na primeira posicao
                }
                this.limparVerticesAntecessores();
                this.limparVerticesVisitados();
                 
            }
        }
      return menorCaminho;       
    }
    
    /**
     * Método remover, da classe Grafo.
     * Remove o vértice e as arestas ligadas a esse vértice.
     * @param nome
     * @return boolean
     */
    public boolean remover(String nome){
        
        if(this.vertices.isEmpty())
            return false;
        else{
            int posicao = this.posicaoVertice(nome);
            Vertice vert = this.vertices.get(posicao);

            for (int i=0; i<this.vertices.size() ; i++){
                for(int j = 0; j < this.vertices.get(i).getArestaConectada().size(); j++ ){
                        if(this.vertices.get(i).getArestaConectada().get(j).getV1().equals(vert) ||
                            this.vertices.get(i).getArestaConectada().get(j).getV2().equals(vert)){
                            this.vertices.get(i).getArestaConectada().remove(j);
                            j = j - 1;
                        }   
                    }  
                if(vertices.get(i).getVizinhos().contains(vert))
                    vertices.get(i).getVizinhos().remove(vert);     
            } 
            vertices.remove(vert);

            for(int a = 0; a < this.arestas.size(); a++){
                if(this.arestas.get(a).getV1().getNome().equals(nome) || this.arestas.get(a).getV2().getNome().equals(nome)){
                    this.arestas.remove(a);
                    a = a - 1;
                }
            }
            return true;       
        }
    }
    
    /**
     * Método indentificarCaminhos, da classe Grafo.
     * @param v1
     * @return ArrayList
     */
    public ArrayList<ArrayList<Vertice>> indentificarCaminhos(String v1){
        ArrayList<ArrayList<Vertice>> caminho = new ArrayList<ArrayList<Vertice>>();
        ArrayList<Vertice> aux = new ArrayList<Vertice>();
        for(int i = 0;i < this.vertices.size(); i ++){
//            System.out.println(aux);
            String v2 = this.vertices.get(i).getNome();
            aux = this.encontrarMenorCaminhoDijkstra(v1,v2);
//            System.out.println(this.encontrarMenorCaminhoDijkstra(v1,v2));
//            System.out.println(this.vertices.get(i).getNome());
            caminho.add(aux);
        }
        return caminho;
    }
     
//    public double distanciaEuclidiana(double x1, double y1, double x2, double y2){
//        double d;
//        d = Math.sqrt(Math.pow(x2-x1,x2-x1) + Math.pow(y2-y1, y2-y1));
//        return d;
//    }

    /**
     * Método imprimeGrafo, da classe Grafo.
     * 
     */
    
    public void imprimeGrafo(){
        for (int i=0; i<arestas.size();i++)
                System.out.print(this.arestas.get(i).getV1().getNome() + this.arestas.get(i).getV2().getNome() + " - " + this.arestas.get(i).getPeso() + " | ");
        System.out.println();
    }
    
}

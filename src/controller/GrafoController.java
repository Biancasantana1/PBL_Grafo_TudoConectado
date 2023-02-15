
package controller;

import model.Aresta;
import model.Vertice;
import model.Grafo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

public class GrafoController {

    /**
     *
     */
    public Grafo grafo; 
    
    /**
     *
     */
    public GrafoController(){
        this.grafo = new Grafo();
    }
    
    /**
     *
     * @param nome
     * @param terminal
     * @param x
     * @param y
     */
    public void addVertice(String nome, Boolean terminal, float x, float y){
        this.grafo.addVertice(nome, terminal, x, y);
        
    }
    
    /**
     *
     * @param peso
     * @param v1
     * @param v2
     */
    public void addAresta(int peso, String v1, String v2){
        this.grafo.addAresta(peso, v1, v2);
        
    }
    
    /**
     *
     * @param ve1
     * @param ve2
     * @return ArrayList
     */
    public ArrayList<Vertice> encontrarMenorCaminhoDijkstra(String ve1,String ve2){
        return this.grafo.encontrarMenorCaminhoDijkstra(ve1, ve2);
    }
    
    /**
     *
     * @param nome
     */
    public void remover(String nome){
        this.grafo.remover(nome);
    }
    
    /**
     *
     * @param v1
     * @return ArrayList
     */
    public ArrayList<ArrayList<Vertice>> indentificarCaminhos(String v1){
        return this.grafo.indentificarCaminhos(v1);
    }
    
    /**
     *
     * @param fileNameVertice
     * @param fileNameAresta
     */
    public void readFile(String fileNameVertice, String fileNameAresta){
        this.readFileVertice(fileNameVertice);
        this.readFileAresta(fileNameAresta);
    }
    
    /**
     *
     * @param fileNameVertice
     * @param fileNameAresta
     */
    public void saveFile(String fileNameVertice, String fileNameAresta){
        this.saveFileVertice(fileNameVertice);
        this.saveFileAresta(fileNameAresta);
    }
    
    /**
     *
     * @param fileName
     */
    public void readFileVertice(String fileName){   
        try{
            FileInputStream file = new FileInputStream(fileName+".txt");
            InputStreamReader input = new InputStreamReader(file, "ISO-8859-1");
            BufferedReader br = new BufferedReader(input);

            String linha;
            String[] linhaCortada;

            do{
                linha = br.readLine();
                if (linha != null){
                    linhaCortada = linha.split(";");
                    if (!"Sigla".equals(linhaCortada[0])){
                        if(linhaCortada[3].equals("true")){
                            String aux = linhaCortada[1];
                            String aux1 = linhaCortada[2];
                            float x = Float.parseFloat(aux);
                            float y = Float.parseFloat(aux1);
                            this.addVertice(linhaCortada[0], true, x , y );
                        } else {
                            String aux = linhaCortada[1];
                            String aux1 = linhaCortada[2];
                            float x = Float.parseFloat(aux);
                            float y = Float.parseFloat(aux1);                            
                            this.grafo.addVertice(linhaCortada[0], false, x , y );
                        }
                    }
                }
                
            }while(linha != null);
            br.close();
            input.close();
            file.close();
        
        }catch (Exception e){
            System.out.println("*Erro na leitura");
        }    
    }
    
    /**
     *
     * @param fileName
     */
    public void readFileAresta(String fileName){
        try{
            FileInputStream file = new FileInputStream(fileName+".txt");
            InputStreamReader input = new InputStreamReader(file, "ISO-8859-1");
            BufferedReader br = new BufferedReader(input);

            String linha;
            String[] linhaCortada;
            
            do{
                linha = br.readLine();
                if (linha != null){
                    linhaCortada = linha.split(";");
                    if (!"vertice1".equals(linhaCortada[0])){
                        String aux1 = linhaCortada[2];                     
                        int peso = Integer.parseInt(linhaCortada[2]);
                        this.addAresta(peso,linhaCortada[0], linhaCortada[1]);
                    }
                }
            }while(linha != null);
            br.close();
            input.close();
            file.close();
        
        }catch (Exception e){
            System.out.println("*Erro na leitura");
        }  
    }
    
    /**
     *
     * @param fileName
     */
    public void saveFileVertice(String fileName){
        try{
            File f2 = new File("vertice.txt");
            f2.delete();
            FileOutputStream file = new FileOutputStream(fileName+".txt");
            PrintWriter pw  = new PrintWriter(file);   
            //Coloca a linha padrão do arquivo
            pw.println("Sigla;X;Y;Terminal");
            for(int i =0;i<grafo.getVertices().size(); i++ ){
                Vertice aux = grafo.getVertices().get(i);
                pw.println(aux.getNome()+";"+aux.getX()+";"+aux.getY()+";"+aux.isTerminal());
            }
            pw.close();
            file.close();
        } catch (Exception e){
            System.out.println("Erro ao gravar");
        }
    }
    
    /**
     *
     * @param fileName
     */
    public void saveFileAresta(String fileName){
        try{
            File f2 = new File("aresta.txt");
            f2.delete();
            FileOutputStream file = new FileOutputStream(fileName+".txt");
            PrintWriter pw  = new PrintWriter(file);   
            //Coloca a linha padrão do arquivo
            pw.println("vertice1;vertice2;peso");
            for(int i =0;i<grafo.getArestas().size(); i++ ){
                Aresta aux = grafo.getArestas().get(i);
                pw.println(aux.getV1()+";"+aux.getV2()+";"+aux.getPeso());
            }
            pw.close();
            file.close();
        } catch (Exception e){
            System.out.println("Erro ao gravar");
        }
    }
    
    public ArrayList<String[]> getVerticeParaGUI(){
        ArrayList<Vertice> vertices = this.grafo.getVertices();
        ArrayList<String[]> verticesGUI = new ArrayList();
        Vertice vertice;
        for (int i = 0; i < vertices.size(); i++) {
            String[] verticeGUI = new String[4];
            vertice = vertices.get(i);
            verticeGUI[0] = vertice.getNome();
            verticeGUI[1] = Float.toString(vertice.getX());
            verticeGUI[2] = Float.toString(vertice.getY());
            if (vertice.isTerminal()) verticeGUI[3] = "True";
            else verticeGUI[3] = "False";
            verticesGUI.add(verticeGUI);
        }
        return verticesGUI;
    }
    
    public ArrayList<String[]> getArestasParaGUI(){
        ArrayList<Aresta> arestas = this.grafo.getArestas();
        ArrayList<String[]> arestasGUI = new ArrayList();
        Aresta aresta;
        
        for (int i = 0; i < arestas.size(); i++) {
            String[] arestaGUI = new String[3];
            aresta = arestas.get(i);
            arestaGUI[0] = aresta.getV1().getNome();
            arestaGUI[1] = aresta.getV2().getNome();
            arestaGUI[2] = Integer.toString(aresta.getPeso());
            
            arestasGUI.add(arestaGUI);
        }
        return arestasGUI;
    }
    
    public ArrayList<String> getMenorCaminhoParaGUI(String ve1, String ve2){
        ArrayList<String> caminho = new ArrayList();
        ArrayList<Vertice> caminhoVertice = this.encontrarMenorCaminhoDijkstra(ve1, ve2);
        if(caminhoVertice != null){
            for (int i = 0; i < caminhoVertice.size(); i++) {
                caminho.add(caminhoVertice.get(i).getNome());
            }
            return caminho;
        } else return null;
    }
    
    public ArrayList<ArrayList<String>> getMenoresCaminhosParaGUI(String ve1){
        ArrayList<ArrayList<String>> caminhos = new ArrayList();
        ArrayList<ArrayList<Vertice>> caminhosVertices = this.grafo.indentificarCaminhos(ve1);
        for (int i = 0; i < caminhosVertices.size(); i++) {
            if(caminhosVertices.get(i)!=null){
                ArrayList<String> novoCaminho = new ArrayList();
                if (caminhosVertices.get(i).get(caminhosVertices.get(i).size()-1).isTerminal()){
                    for (int j = 0; j < caminhosVertices.get(i).size(); j++) {
                        novoCaminho.add(caminhosVertices.get(i).get(j).getNome());
                    }
                    caminhos.add(novoCaminho);
                }
            }
        }
        return caminhos;
    }
}
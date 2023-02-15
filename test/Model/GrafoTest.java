/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import model.Grafo;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bia
 */
public class GrafoTest {
    private  Grafo g;
    public GrafoTest() {
        g = new Grafo();
    }
    
    @Before
    public void setUp() {
        g.addVertice("A", true, 0, 0);
        g.addVertice("B", false, 0, 0);
        g.addVertice("C", true, 0, 0);
        g.addVertice("D", false, 0, 0);
        g.addAresta(1, "A", "B");
        g.addAresta(2, "B", "C");
        g.addAresta(3, "C", "D");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Teste do método addVertice, da classe Grafo.
     */
    @Test
    public void testAddVertice() {
        assertEquals("A",g.getVertices().get(0).getNome());// verfica se inseriu o primeiro
        assertEquals("B",g.getVertices().get(1).getNome());// verifica se inseriu o segundo
        assertEquals("D",g.getVertices().get(3).getNome());// verifica se inseriu o ultimo
        assertTrue(g.getVertices().get(0).isTerminal());// verifica se inseriu os dados corretamente
        assertEquals(1,g.getVertices().get(0).getVerticeVizinho().size());// adicionando os vizinhos a lista
    }

    /**
     * Teste do método addAresta, da classe Grafo.
     */
    @Test
    public void testAddAresta() {
        assertEquals(1,g.getArestas().get(0).getPeso());// verfica se inseriu o primeiro
        assertEquals(2,g.getArestas().get(1).getPeso());// verfica se inseriu o segundo
        assertEquals(3,g.getArestas().get(2).getPeso());// verfica se inseriu o ultimo
        g.addAresta(4, "D", "E");
        assertEquals(3,g.getArestas().size());// verifica se adiciona uma aresta quando o destino não existe
        g.addAresta(4, "E", "D");
        assertEquals(3,g.getArestas().size());// verifica se adiciona uma aresta quando a origem não existe
        g.addAresta(4, "A", "A");
        assertEquals(4,g.getArestas().size());// verifica se adiciona uma aresta quando a origem e o destino são iguais
        assertEquals(2,g.getVertices().get(1).getArestaConectada().size());// adicionando a lista de aresta conectadas ao vértice
    }

    /**
     * Teste do método encontrarMenorCaminhoDijkstra, da classe Grafo.
     */
    @Test
    public void testEncontrarMenorCaminhoDijkstra() {
        ArrayList menorcaminho = g.encontrarMenorCaminhoDijkstra("A", "D");
        // De acordo com o que foi inserido o menor caminho seria "A,B,C,D"
        assertEquals(g.getVertices().get(0),menorcaminho.get(0));// verificando a lista de menor caminho está certa
        assertEquals(g.getVertices().get(1),menorcaminho.get(1));
        assertEquals(g.getVertices().get(2),menorcaminho.get(2));
        assertEquals(g.getVertices().get(3),menorcaminho.get(3));
        g.addAresta(1, "A", "D");
        menorcaminho = g.encontrarMenorCaminhoDijkstra("A", "D");
        assertEquals(g.getVertices().get(0),menorcaminho.get(0)); // verificando a lista de menor caminho está certa com
        assertEquals(g.getVertices().get(3),menorcaminho.get(1)); // dois valores
        assertNull(g.encontrarMenorCaminhoDijkstra("B", "D"));// testando se a origem não for terminal
    }

    /**
     * Teste do método remover, da classe Grafo.
     */
    @Test
    public void testRemover() {
        g.remover("A");
        assertEquals(3,g.getVertices().size());
        assertEquals(2,g.getArestas().size());
        assertEquals(1,g.getVertices().get(0).getArestaConectada().size());
        assertEquals(1,g.getVertices().get(0).getVerticeVizinho().size());
        assertEquals("C",g.getVertices().get(0).getVerticeVizinho().get(0).getNome());
    }
    
    /**
     * Test of indentificarCaminhos method, of class Grafo.
     */
//    @Test
//    public void testIndentificarCaminhos() {
//        ArrayList caminho = g.indentificarCaminhos("A");
//        ArrayList<Vertice> aux = new ArrayList<Vertice>();
//        aux.add(e);
//        assertEquals(aux, caminho.get(0));
//    }
    
}

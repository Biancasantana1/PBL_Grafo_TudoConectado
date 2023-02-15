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
package view;

import javafx.scene.paint.Paint;

/**
 *
 * @author Paulo
 */
public class Router extends Component{
    /**
    * Construtor vazio da classe Router
    */
    public Router(){
        super();
    }
    /**
    * Construtor da Classe Router que recebe um ponto
    * @param acronym sigla que representa o roteador
    * @param point ponto que o roteador contem
    */
    public Router(String acronym, Point point) {
        super(acronym, point, Paint.valueOf("blue"));
    }
    /**
    * Construtor da Classe Router que recebe uma coordenada
    * @Param acronym sigla que o representa
    * @param x Coordenada x
    * @param y Coordenada y
    */
    public Router(String acronym, double x, double y) {
        super(acronym, new Point(x, y), Paint.valueOf("blue"));
    }
    /**
    * Converte a Classe em uma String
    */
    @Override
    public String toString(){
        return this.acronym;
    }
}

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

import controller.FXMLMainInterfaceController;
import javafx.event.Event;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author Paulo
 */
public class Computer extends Component {
    
    private FXMLMainInterfaceController controller;
    
    /**
    * Construtor vazio da classe Computer
    */
    public Computer(){
        super();
        this.setOnMouseClicked((Event event) -> {
            clicked();
        });
        this.circle.setStroke(Paint.valueOf("yellow"));
        this.circle.setStrokeWidth(0);
        this.circle.setStrokeType(StrokeType.OUTSIDE);
        
    }
    /**
    * Construtor da Classe Computer que recebe um ponto
    * @param acronym sigla que representa o roteador
    * @param point ponto que o roteador conte
    * @param controller controler que o criou
    */
    public Computer(String acronym, Point point, FXMLMainInterfaceController controller) {
        super(acronym, point, Paint.valueOf("green"));
        this.setOnMouseClicked((Event event) -> {
            clicked();
        });
        this.controller = controller;
        this.circle.setStroke(Paint.valueOf("yellow"));
        this.circle.setStrokeWidth(0);
    }
    /**
    * Construtor da Classe Computer que recebe uma coordenada
    * @param acronym sigla que o representa
    * @param x Coordenada x
    * @param y Coordenada y
    * @param controller controler que o criou
    */
    public Computer(String acronym, double x, double y, FXMLMainInterfaceController controller) {
        super(acronym, new Point(x,y), Paint.valueOf("green"));
        this.setOnMouseClicked((Event event) -> {
            clicked();
        });
        this.controller = controller;
        this.circle.setStroke(Paint.valueOf("yellow"));
        this.circle.setStrokeWidth(0);
    }
    /**
    * Define o computer como selecionado ou desselecionado dependendo da situação atual dele
    */
    public void clicked(){
        if (this.circle.getStrokeWidth() != 2){
            if (!controller.hasSecond()) 
                this.selected();   
        }else
            this.deselected();
        
    }
    /**
    * Define o computer como selecionado
    */
    private void selected(){
        this.circle.setStrokeWidth(2);
        controller.selectedComputer(this);
    } 
    /**
    * Define o computer como desselecionado
    */
    private void deselected(){
        this.circle.setStrokeWidth(0);
        controller.deselectedComputer(this);
    }
    
    @Override
    public String toString(){
        return this.acronym;
    }
    
    @Override
    public Computer clone(){
        return new Computer(this.acronym, this.point,this.controller);
    }
}

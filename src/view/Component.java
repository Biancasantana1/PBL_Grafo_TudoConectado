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

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
/**
 *
 * @author Paulo
 */
class Component extends StackPane{
    protected String acronym;
    protected Point point;
    protected Text txtAcronym;
    protected Circle circle;
    
    /**
    * Construtor vazio da classe Component
    */
    public Component(){
        super();
    }
    /**
    * Construtor da Classe Component que recebe um ponto
    * @param acronym sigla que representa o roteador
    * @param point ponto que o roteador conte
    * @param paint Cor do circulo
    */
    public Component(String acronym, Point point, Paint paint){
        super();
        
        this.setWidth(30);
        this.setHeight(30);
        this.setLayoutX(point.getX()-15);
        this.setLayoutY(point.getY()-15);
        
        this.point = point;
        
        this.circle = new Circle(15, paint);
        this.acronym = acronym;
        this.txtAcronym = new Text(acronym);
        
        this.getChildren().add(this.circle);
        this.getChildren().add(this.txtAcronym);
    }
    /**
    * Construtor da Classe Component que recebe uma coordenada
    * @Param acronym sigla que o representa
    * @param x Coordenada x
    * @param y Coordenada y
    * @param paint Cor do circulo
    */
    public Component(String acronym, Point point, Paint paint, double strokeWhidth, Paint strokePaint){
        super();
        
        this.setWidth(30);
        this.setHeight(30);
        this.setLayoutX(point.getX()-15);
        this.setLayoutY(point.getY()-15);
        
        this.point = point;
        
        this.circle = new Circle(15, paint);
        this.acronym = acronym;
        this.txtAcronym = new Text(acronym);
        
        this.circle.setStroke(strokePaint);
        this.circle.setStrokeWidth(strokeWhidth);
        this.circle.setStrokeType(StrokeType.INSIDE);
        
        this.getChildren().add(this.circle);
        this.getChildren().add(this.txtAcronym);
    }
    /**
    * pega a sigla que representa o objeto
    * @return sigla do objeto
    */
    public String getAcronym() {
        return acronym;
    }
    /**
    * pega o ponto que pertence ao objeto
    * @return ponto do objeto
    */
    public Point getPoint() {
        return point;
    }
    /**
    * define a cor da borda do circulo
    * @param objeto do tipo Paint que representa a cor da borda 
    */
    public void setStrokeCircle(Paint paint){
        this.circle.setStroke(paint);
    }
    /**
    * Calcula a distancia de dois componentes na tela
    * @param componete Componete a se comparar a distancia
    * @return valor double da distancia
    */
    public double distanceTo(Component component){
        return this.point.distanceTo(component.getPoint());
    }
    
    @Override
    public Component clone(){
        return new Component(this.acronym,this.point,this.circle.getFill(),this.circle.getStrokeWidth(),this.circle.getStroke());
    }
}

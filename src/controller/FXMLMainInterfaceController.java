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
package controller;

import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import view.Computer;
import view.Conection;
import view.Router;


/**
 *
 * @author Paulo
 */


public class FXMLMainInterfaceController implements Initializable {
    
    private Computer first;
    private Computer second;
    
    private GrafoController grafoController;
    private ArrayList<Computer> computer;
    private ArrayList<Router> router;
    private ArrayList<Conection> conection;
    
    private ComboBox cbRemoveComponent;
    
    @FXML private Label lblCoordenatX;
    @FXML private Label lblCoordenatY;
    @FXML private AnchorPane APNetwork;
    @FXML private BorderPane pnMainInterface;
    @FXML private VBox vbAllComponents;
    @FXML private TextField tfSigla;
    @FXML private TextField tfCoordenadaX;
    @FXML private TextField tfCoordenadaY;
    @FXML private Button btnAddConection;
    @FXML private Button btnSaveNewComponet;
    @FXML private Button btnRemove;
    @FXML private Button btnRemoveConection;
    @FXML private RadioButton rbtComputador;
    @FXML private RadioButton rbtRoteador;
    
    /**
    * Adiciona as opções na tela para a criação de um novo componente da rede
    */
    @FXML
    private void addComponent(){
        vbAllComponents.getChildren().clear();
        
        Label sigla = new Label("Sigla");
        sigla.fontProperty().setValue(new Font(18));
        
        Label cooX = new Label("Coordenada X");
        cooX.fontProperty().setValue(new Font(18));
        
        Label cooY = new Label("Coordenada Y");
        cooY.fontProperty().setValue(new Font(18));

        
        vbAllComponents.getChildren().addAll(this.lblCoordenatX, this.lblCoordenatY, sigla, tfSigla, cooX, tfCoordenadaX, cooY, tfCoordenadaY, rbtComputador, rbtRoteador, btnAddConection, btnRemoveConection, btnSaveNewComponet);   
    }
    
    /**
    * Adiciona na tela de adicionar componente uma possivel conexão que o novo componente tem 
    */
    @FXML
    public void addConection(){
        System.out.println("addConection");
        this.vbAllComponents.getChildren().removeAll(this.btnAddConection, this.btnRemoveConection, this.btnSaveNewComponet);
        
        HBox newConection = new HBox();
        
        ComboBox<String> cbNewConection = new ComboBox<>();
        ArrayList<String> temp = new ArrayList();
        for (int i = 0; i < this.computer.size(); i++) {
            temp.add(this.computer.get(i).toString());
        }
        for (int i = 0; i < this.router.size(); i++) {
            temp.add(this.router.get(i).toString());
        }
        cbNewConection.setItems(FXCollections.observableArrayList(temp));
        
        TextField tfNewConection = new TextField();
        
        newConection.getChildren().addAll(cbNewConection, tfNewConection);
        this.vbAllComponents.getChildren().add(newConection);
        
        this.vbAllComponents.getChildren().addAll(this.btnAddConection, this.btnRemoveConection, this.btnSaveNewComponet);
        
    }
    
    /**
    * Retira uma aba de nova conexão na area de adicionar componente
    */
    @FXML
    public void removeConection(){
        try{
            HBox hbAux = (HBox) this.vbAllComponents.getChildren().get(this.vbAllComponents.getChildren().size()-4);
            this.vbAllComponents.getChildren().remove(this.vbAllComponents.getChildren().size()-4);
        } catch (Exception e){
            System.out.println("Erro ao remover conexão");
        }
        
    }
    
    /**
    * Salva o componente criado na aba de adicionar componente
    */
    @FXML
    private void saveComponent(){
        System.out.println("saveComponent");
        
        String name = this.tfSigla.getText(); 
        if (name.length() > 4){
           Alert invalidName = new Alert(Alert.AlertType.ERROR);
           invalidName.setTitle("Erro");
           invalidName.setHeaderText("Sigla Invalida");
           invalidName.setContentText("Sigla so pode ser no maximo 4 caracteres");
           invalidName.show();
           return; 
        }
        for (int i = 0; i < this.computer.size(); i++) {
            if (this.computer.get(i).getAcronym().equals(name)){
                Alert invalidName = new Alert(Alert.AlertType.ERROR);
                invalidName.setTitle("Erro");
                invalidName.setHeaderText("Sigla Invalida");
                invalidName.setContentText("A sigla colocada ja esta sendo utilizada");
                invalidName.show();
                return;
            }
        }
        for (int i = 0; i < this.router.size(); i++) {
            if (this.router.get(i).getAcronym().equals(name)){
                Alert invalidName = new Alert(Alert.AlertType.ERROR);
                invalidName.setTitle("Erro");
                invalidName.setHeaderText("Sigla Invalida");
                invalidName.setContentText("A sigla colocada ja esta sendo utilizada");
                invalidName.show();
                return;
            }
        }
        
        try{
            Float coordenatX = Float.parseFloat(this.tfCoordenadaX.getText());
            Float coordenatY = Float.parseFloat(this.tfCoordenadaY.getText());
        }catch(Exception e){
            Alert invalidNumber = new Alert(Alert.AlertType.ERROR);
            invalidNumber.setTitle("Erro");
            invalidNumber.setHeaderText("Numero Invalido");
            invalidNumber.setContentText("O valor colocado nas cordenadas é invalido");
            invalidNumber.show();
            return;
        }
        Float coordenatX = Float.parseFloat(this.tfCoordenadaX.getText());
        if (coordenatX > 760) coordenatX = (float) 760;
        else if (coordenatX < 0) coordenatX = (float) 0;
        Float coordenatY = Float.parseFloat(this.tfCoordenadaY.getText());
        if (coordenatY > 515) coordenatY = (float) 515;
        else if (coordenatY < 0) coordenatY = (float) 0;


        boolean terminal;
        terminal = this.rbtComputador.isSelected();
        
        HBox hb;
        ComboBox cb;
        
        ArrayList<String> arrayVertex = new ArrayList();
        ArrayList<Integer> arrayWheight = new ArrayList();
        
        for (int i = 10; i < this.vbAllComponents.getChildren().size()-3; i++) {
            hb = (HBox) this.vbAllComponents.getChildren().get(i);
            cb = (ComboBox) hb.getChildren().get(0);
            
            if (arrayVertex.contains((String) cb.getValue())){
                Alert invalidConection = new Alert(Alert.AlertType.ERROR);
                invalidConection.setTitle("Erro");
                invalidConection.setHeaderText("Conexão Invalida");
                invalidConection.setContentText("Duas ou mais conexões contem o mesmo destino");
                invalidConection.show();
                return;
            }
            if (cb.getValue()==null){
                Alert invalidConection = new Alert(Alert.AlertType.ERROR);
                invalidConection.setTitle("Erro");
                invalidConection.setHeaderText("Conexão inexistente");
                invalidConection.setContentText("Uma das conexões criadas não tem destino");
                invalidConection.show();
                return;
            }
            arrayVertex.add((String) cb.getValue());
            try{
                arrayWheight.add(Integer.parseInt(((TextField) hb.getChildren().get(1)).getText()));
            }catch(Exception e){
                Alert invalidNumber = new Alert(Alert.AlertType.ERROR);
                invalidNumber.setTitle("Erro");
                invalidNumber.setHeaderText("Numero Invalido");
                invalidNumber.setContentText("Peso da conexão com "+(String) cb.getValue()+" é invalido");
                invalidNumber.show();
                return;
            }
        }
        
        this.grafoController.addVertice(name, terminal, coordenatX, coordenatY);
        for (int i = 0; i < arrayWheight.size(); i++) {
            this.grafoController.addAresta(arrayWheight.get(i), name, arrayVertex.get(i));
        }
        
        
        
        this.vbAllComponents.getChildren().clear();
        this.vbAllComponents.getChildren().addAll(lblCoordenatX, lblCoordenatY);
        this.tfSigla.setText("");
        this.tfCoordenadaX.setText("");
        this.tfCoordenadaY.setText("");
        
        this.buildNetworkButton();
    }
    /**
    * Adiciona as opções na tela para a remoção de um componente da rede
    */
    @FXML
    private void removeComponent(){
        vbAllComponents.getChildren().clear();
        
        Label lblEscolhaOComponente = new Label("Escolha o componente: ");
        lblEscolhaOComponente.fontProperty().setValue(new Font(18));
        
        cbRemoveComponent = new ComboBox<>();
        ArrayList<String> temp = new ArrayList();
        for (int i = 0; i < this.computer.size(); i++) {
            temp.add(this.computer.get(i).toString());
        }
        for (int i = 0; i < this.router.size(); i++) {
            temp.add(this.router.get(i).toString());
        }
        cbRemoveComponent.setItems(FXCollections.observableArrayList(temp));
        
        vbAllComponents.getChildren().addAll(lblEscolhaOComponente, cbRemoveComponent, this.btnRemove);
    }
    /**
    * Comfirma a remoção de um componente da rede
    */
    @FXML
    private void confirmRemove(){
        if (cbRemoveComponent.getValue()==null){
            Alert invalidComponent = new Alert(Alert.AlertType.ERROR);
            invalidComponent.setTitle("Erro");
            invalidComponent.setHeaderText("Componente inexistente");
            invalidComponent.setContentText("Escolha um componente para ser removido");
            invalidComponent.show();
            return;
        }
        this.grafoController.remover((String) cbRemoveComponent.getValue());
        this.vbAllComponents.getChildren().clear();
        this.vbAllComponents.getChildren().addAll(this.lblCoordenatX, this.lblCoordenatY);
        this.buildNetworkButton();
    }
    /**
    * Atualiza o indicador de coordenada X e Y do mouse na rede
    * @param event o mouse que disparou o evento e tera suas coordenadas utilizadas
    */
    @FXML
    private void updateCoordinate(MouseEvent event) {
        this.lblCoordenatX.setText("X: "+event.getX());
        this.lblCoordenatY.setText("Y: "+event.getY());
    }
    /**
    * Abre, se existir, o arquivo da rede
    */
    @FXML
    private void openFile(){
        this.APNetwork.getChildren().clear();
        this.grafoController.readFile("vertice", "aresta");
        this.buildNetworkButton();
    }
    /**
    * Salva a rede criada para poder ser utilizada posteriormente
    */
    @FXML
    private void saveFile(){
        this.grafoController.saveFile("vertice", "aresta");
    }
    
    /**
    * Coleta as informaçoes do grafo, vertice e aresta, e chama um metodo que crie o grafo na GUI
    */
    @FXML
    private void buildNetworkButton(){
        ArrayList<String[]> vertex = this.grafoController.getVerticeParaGUI();
        ArrayList<String[]> edger = this.grafoController.getArestasParaGUI();
        this.buildNetwork(vertex, edger);
    }
    
    /**
    * Cria um grafo na GUI
    * @param vertex lista de vetores que representam cada vertice do grafo
    * @param adger lista de vetores que representam cada aresta do grafo
    */
    private void buildNetwork(ArrayList<String[]> vertex, ArrayList<String[]> edger){
        APNetwork.getChildren().clear();
        this.computer.clear();
        this.router.clear();
        this.conection.clear();
        
        String[] vertexVector, edgerVector;
        Iterator inteEdger = edger.iterator();
        Iterator inteVertex = vertex.iterator();
        
        
        while (inteVertex.hasNext()){
            vertexVector = (String[]) inteVertex.next();
            if (vertexVector[3].equals("True"))
                this.computer.add(new Computer(vertexVector[0], Float.parseFloat(vertexVector[1]), Float.parseFloat(vertexVector[2]), this));
            else 
                this.router.add(new Router(vertexVector[0], Float.parseFloat(vertexVector[1]), Float.parseFloat(vertexVector[2])));
        }
        
        Computer auxCom1 = null;
        Computer auxCom2 = null;
        Router auxRou1 = null;
        Router auxRou2 = null;
        
        while (inteEdger.hasNext()){
            edgerVector = (String[]) inteEdger.next();
            for (int i = 0; i < computer.size(); i++) {
                if (edgerVector[0].equals(computer.get(i).getAcronym()) || edgerVector[1].equals(computer.get(i).getAcronym())){
                    if (auxCom1 == null) auxCom1 = computer.get(i);
                    else{ 
                        auxCom2 = computer.get(i);
                        break;
                    }
                } 
            }
            if (auxCom1 == null || auxCom2 == null){
                for (int i = 0; i < router.size(); i++) {
                    if (edgerVector[0].equals(router.get(i).getAcronym()) || edgerVector[1].equals(router.get(i).getAcronym())){
                        if (auxRou1 == null){ 
                            auxRou1 = router.get(i);
                            if (auxCom1 != null) break;
                        } else{ 
                            auxRou2 = router.get(i);
                            break;
                        }
                    } 
                }
            }
            if (auxCom1 == null){
                conection.add(new Conection(auxRou1, auxRou2, Integer.parseInt(edgerVector[2])));
            } else if (auxCom2 == null){
                conection.add(new Conection(auxCom1, auxRou1, Integer.parseInt(edgerVector[2])));
            } else {
                conection.add(new Conection(auxCom1, auxCom2, Integer.parseInt(edgerVector[2])));
            }
            auxCom1 = null;
            auxCom2 = null;
            auxRou1 = null;
            auxRou2 = null;
        }
        
        APNetwork.getChildren().addAll(conection);
        APNetwork.getChildren().addAll(computer);
        APNetwork.getChildren().addAll(router);
        this.first = null;
        this.second = null;
                
    }
    /**
    * Analisa se ja contem dois computadores selecionados
    * @return true se existe dois computadores selecionados
    */
    public boolean hasSecond(){
        return null != second;
    }
    /**
    * Analisa se o computador selecionado e o primeiro selecionado, o segundo ou o terceiro,
    * que nesse caso e descelecionado logo em seguida
    */
    public void selectedComputer(Computer computer){
        if (this.computer.size()+this.router.size() < 30){
            Alert invalidComponent = new Alert(Alert.AlertType.INFORMATION);
            invalidComponent.setTitle("Alerta");
            invalidComponent.setHeaderText("Numero de Componente insuficiente");
            invalidComponent.setContentText("É preciso ter no minimo 30 componentes na rede para poder calcular o peso");
            invalidComponent.show();
            computer.clicked();
            return;
        }
        if (first == null)
            first = computer;
        else if (second == null)
            second = computer;
        this.drawPaths();
    }
    /**
    * Retira o computador selecionado da lista de computadores selecionados e, se conter dois,
    * coloca o segundo selecionado como primeiro
    */
    public void deselectedComputer (Computer computer){
        if (first == computer){
            first = second;
            second = null;
        } else if (second == computer)
            second = null;
        this.drawPaths();
    }
    /**
    * Seleciona o menor caminho de dois computadores ou de um computador para todos os outros 
    */
    private void drawPaths(){
        this.vbAllComponents.getChildren().clear();
        this.vbAllComponents.getChildren().addAll(this.lblCoordenatX, this.lblCoordenatY);
        this.deselectAllConnections();
        if (this.first != null && this.second != null){
            System.out.println(first.getAcronym()+" para "+ second.getAcronym()); 
            this.selectConnections(this.first.getAcronym(),this.second.getAcronym());
        } else if(this.first != null && this.second == null){
            System.out.println(first.getAcronym()+" para Todos");
            selectAllConnections(this.first.getAcronym());
        }
    }
    /**
    * Seleciona as conexão que conectam os componentes que contem as siglas mandadas por parametro
    * @param a Sigla do primeiro componente
    * @param b Sigla do segundo componente
    */
    private void selectConnections(String a, String b){
        ArrayList<String> paths = this.grafoController.getMenorCaminhoParaGUI(a, b);
        if (paths != null){
            for (int i = 1; i < paths.size(); i++) {
                this.findConection(paths.get(i), paths.get(i-1)).isWay();
            }
        }
        Label euclideanDistanceTxt = new Label(" Distancia Euclidiana: ");
        euclideanDistanceTxt.fontProperty().setValue(new Font(18));
        Label euclideanDistance = new Label(" - "+Double.toString(this.findComputer(a).distanceTo(this.findComputer(b))));
        euclideanDistance.fontProperty().setValue(new Font(18));
        Label path = new Label("Caminho de "+a+" para "+b);
        path.fontProperty().setValue(new Font(18));
        this.vbAllComponents.getChildren().addAll(new Label("----------------------------------------"),path , euclideanDistanceTxt, euclideanDistance );
    }
    /**
    * Seleciona as conecções que conectam o componente com a sigla informada há todos os computadores
    * @param a Sigla do primeiro componente
    */
    private void selectAllConnections(String a){
        ArrayList<ArrayList<String>> allPaths = this.grafoController.getMenoresCaminhosParaGUI(a);
        if(allPaths != null){
            this.vbAllComponents.getChildren().add(new Label("----------------------------------------"));
            Label lblAux = new Label("Todos os caminhos:");
            lblAux.fontProperty().setValue(new Font(16));
            this.vbAllComponents.getChildren().add(lblAux);
            String aux = a;

            for (int i = 0; i < allPaths.size(); i++) {
                Label path = new Label(a);
                path.fontProperty().setValue(new Font(14));
                System.out.println("tamanho: "+allPaths.get(i).size());                
                for (int j = 1; j < allPaths.get(i).size(); j++) {

                    path.setText(path.getText()+","+allPaths.get(i).get(j));
                    this.findConection(allPaths.get(i).get(j), allPaths.get(i).get(j-1)).isWay();
                    aux = allPaths.get(i).get(j);
                }
                 path.setText(a+"-"+aux+": "+path.getText());
                 this.vbAllComponents.getChildren().add(path);
                 aux = a;
            }
        }
    }
    /**
    * Desseleciona todas as conecções
    */
    private void deselectAllConnections(){
        for (int i = 0; i < this.conection.size(); i++) {
            this.conection.get(i).notWay();
        }
    }
    /**
    * Busca qual conexão liga os componentes das siglas informadas
    * @param first Sigla que representa o inicio da conexão
    * @param second Sigla que representa o fim da conexão
    * @return Retorna a conexão que liga os componentes informados, se existir 
    */
    private Conection findConection(String first, String second){
        for (int i = 0; i < this.conection.size(); i++) {
            if(this.conection.get(i).getConecteds()[0]== first && this.conection.get(i).getConecteds()[1] == second ||
               this.conection.get(i).getConecteds()[0]==second && this.conection.get(i).getConecteds()[1] == first){
                return this.conection.get(i);
            }
        }
        return null;
    }
    /**
    * Busca o computador que contem a sigla informada
    * @param key Sigla que representa o computador
    * @return Retorna o computador, se existir 
    */
    private Computer findComputer(String key){
        Computer computer = null;
        for (int i = 0; i < this.computer.size(); i++) {
            if (this.computer.get(i).getAcronym().equals(key)){
                computer = this.computer.get(i);
                break;
            }    
        }
        return computer;
    }
    
    /**
    * Inicializa quando a GUI for criada
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.grafoController = new GrafoController();
        
        this.computer = new ArrayList<>();
        this.router = new ArrayList<>();
        this.conection = new ArrayList<>();
        
        this.tfSigla = new TextField();
        this.tfCoordenadaX = new TextField();
        this.tfCoordenadaY = new TextField();
        
        this.vbAllComponents.getChildren().clear();
        this.vbAllComponents.getChildren().addAll(this.lblCoordenatX, this.lblCoordenatY);
    }    
}

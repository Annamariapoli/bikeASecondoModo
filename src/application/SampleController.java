package application;

import java.net.URL;
import java.util.ResourceBundle;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import bean.Station;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SampleController {
	
	private Model m = new Model();
	
	public void setModel(Model m ){
		this.m=m;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Station> combo;

    @FXML
    private Button btnConta;

    @FXML
    private TextField txtKm;

    @FXML
    private Button btnCerca;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCerca(ActionEvent event) {
    	Station s = combo.getValue();
    	
    	if(combo.getValue()==null){
    		txtResult.appendText("Seleziona una stazione!\n");
    		return;
    	}
    	
    	try{
    	       double km = Double.parseDouble(txtKm.getText());
    	       UndirectedGraph<Station, DefaultEdge> grafo =  m.buildGraph(km);
    	       
    	       //se volessi stampare elenco di stazioni poste a distanza inferiore con la relativa distanza
    	       
    	      txtResult.setText(String.format("E' stato costruito il grafo con %d vertici e %d archi ", grafo.vertexSet().size(), grafo.edgeSet().size()));
    	       
    	       for(Station staz1 : grafo.vertexSet()){
    	    	   for(Station staz2 : grafo.vertexSet()){
    	    		   txtResult.appendText("distanza : " +m.getDistanza(staz1, staz2)+ " \n");
    	    		  
    	    	   }
    	       }
    	       
    	    }catch(NumberFormatException e ){
    		txtResult.appendText("Il formato non è valido! \n");
    		return;
    	}
    
    }

    @FXML
    void doConta(ActionEvent event) {
    	txtResult.clear();
    	Station s = combo.getValue();
    	if(combo.getValue()==null){
    		txtResult.appendText("Seleziona una stazione!\n");
    		return;
    	}
    	
    	int contaStart = m.contoStart(s);
    	txtResult.appendText("Il numero di trip che partono da questa stazione è : "+contaStart+"\n");
    	int contaEnd = m.contoEnd(s);
    	txtResult.appendText("Il numero di trip che escono da questa stazione è : "+contaEnd+ "\n");
    	

    }

    @FXML
    void initialize() {
        assert combo != null : "fx:id=\"combo\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnConta != null : "fx:id=\"btnConta\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtKm != null : "fx:id=\"txtKm\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Sample.fxml'.";

        combo.getItems().addAll(m.getStation());
    }
}

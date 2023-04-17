package main.java.br.ProjetoPizzaria.gui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.exception.ElementoNaoExisteException;
import main.java.br.ProjetoPizzaria.negocio.ControladorAlimentos;
import main.java.br.ProjetoPizzaria.negocio.ControladorPromocao;
import main.java.br.ProjetoPizzaria.negocio.beans.Alimento;
import main.java.br.ProjetoPizzaria.negocio.beans.Promocao;

public class AlterarPromocaoTelaController {

    @FXML
    private TableColumn<Alimento, String> AlimentoNomeColumn;

    @FXML
    private TableView<Alimento> AlimentoTableView;

    @FXML
    private Button AlterarPromocaoButton;

    @FXML
    private DatePicker DataDeInicioDatePicker;

    @FXML
    private DatePicker DateDeFimDatePicker;

    @FXML
    private ChoiceBox<Promocao> PromocaoCB;

    @FXML
    private TextField ValorTextField;

    @FXML
    private Button VoltarButton;
    
    @FXML
    private ListView<Alimento> AlimentosListView1;
    
    public void initialize() throws ElementoJaExisteException 
    {
    	ObservableList<Promocao> listaDePromocao = FXCollections.observableArrayList();
    	listaDePromocao.addAll(ControladorPromocao.getInstance().listar());
        PromocaoCB.setItems(listaDePromocao);
        Pattern pattern = Pattern.compile("[0-9]*");
		TextFormatter<String> formatter = new TextFormatter<String>((UnaryOperator<TextFormatter.Change>) change -> {
    	    return pattern.matcher(change.getControlNewText()).matches() ? change : null;
    	});
		ValorTextField.setTextFormatter(formatter);
    	AlimentoNomeColumn.setCellValueFactory(new PropertyValueFactory<Alimento, String>("Nome")); 
    	AlimentoTableView.setEditable(true);
    	AlimentoTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	AlimentoTableView.setItems(listaDeAlimentos());
    	
    }
    
    public ObservableList<Alimento> listaDeAlimentos()
    {
    	ObservableList<Alimento> listaDeAlimentos = FXCollections.observableArrayList();
    	listaDeAlimentos.addAll(ControladorAlimentos.getInstance().listar());
    	return listaDeAlimentos;
    }

    @FXML
    void AlterarPromocaoFunction(ActionEvent event) throws ElementoNaoExisteException, ElementoJaExisteException {
    	
    	if(ValorTextField.getText().isBlank() || DataDeInicioDatePicker.getValue() == null || DateDeFimDatePicker.getValue() == null 
        	    || AlimentoTableView.getSelectionModel().getSelectedItems().isEmpty()) {
        		Alert alert = new Alert(AlertType.ERROR,"Um elemento está vazio"); 
        		alert.show();
        	     }
        	   else if(DataDeInicioDatePicker.getValue().isAfter(DateDeFimDatePicker.getValue()) || DataDeInicioDatePicker.getValue().isBefore(LocalDate.now())
        			   || DateDeFimDatePicker.getValue().isBefore(LocalDate.now()))
        	   {
        		   Alert alert = new Alert(AlertType.ERROR,"Data invalida");
        		   alert.show();
        	   }
        	   else if(Integer.parseInt(ValorTextField.getText()) <= 0 || Integer.parseInt(ValorTextField.getText()) > 100)
        	   {
        		   Alert alert = new Alert(AlertType.ERROR,"Valor invalido");
        		   alert.show();
        	   }
        	   else
        	   {
        		   int test = 0;
        		   int valor = Integer.parseInt(ValorTextField.getText());
        		    Promocao promocao = PromocaoCB.getValue();
        	    	LocalDate inicio = DataDeInicioDatePicker.getValue();
        	    	LocalDate fim = DateDeFimDatePicker.getValue();
        	    	ArrayList<Alimento> ListaDeAlimentosValidos = new ArrayList<Alimento>();
        	    	ListaDeAlimentosValidos.addAll(AlimentoTableView.getSelectionModel().getSelectedItems());
        	    	for(int a = 0; a < ListaDeAlimentosValidos.size();a++)
        	    	{
        	    		for(int b = 0; b < ControladorPromocao.getInstance().listar().size();b++)
     					{
    	                   if(ControladorPromocao.getInstance().listar().get(b).getProdutosValidos().contains(ListaDeAlimentosValidos.get(a)))
    	                   {
    	                	   test = 1;
    	                   }
     					}
        	    	}
        	    	
        	    	if(test == 0) {
        	    	ControladorPromocao.getInstance().atualizar(promocao, inicio, fim, valor, ListaDeAlimentosValidos); 
        	    	}
        	    	else
        	    	{
        	    		Alert alert = new Alert(AlertType.ERROR,"Um dos alimentos ja faz parte de outra promocao");
        	    		alert.show();
        	    	}        	    	
        	   }
    }
    @FXML
    void VoltarFunction(ActionEvent event) {
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) VoltarButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("TelaPromocao.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Promoção");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    @FXML
    void MostrarDetalhes(ActionEvent event)
    {
    	AlimentosListView1.setItems(FXCollections.observableArrayList(PromocaoCB.getValue().getProdutosValidos()));
    }

}

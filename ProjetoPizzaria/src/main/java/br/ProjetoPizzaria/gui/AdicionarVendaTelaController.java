package main.java.br.ProjetoPizzaria.gui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.exception.ElementoNaoExisteException;
import main.java.br.ProjetoPizzaria.exception.ObjetoVazioException;
import main.java.br.ProjetoPizzaria.negocio.ControladorAlimentos;
import main.java.br.ProjetoPizzaria.negocio.ControladorCliente;
import main.java.br.ProjetoPizzaria.negocio.ControladorFuncionario;
import main.java.br.ProjetoPizzaria.negocio.ControladorPromocao;
import main.java.br.ProjetoPizzaria.negocio.ControladorVenda;
import main.java.br.ProjetoPizzaria.negocio.beans.Alimento;
import main.java.br.ProjetoPizzaria.negocio.beans.Cliente;
import main.java.br.ProjetoPizzaria.negocio.beans.Funcionario;
import main.java.br.ProjetoPizzaria.negocio.beans.ItemVenda;
import main.java.br.ProjetoPizzaria.negocio.beans.Venda;

public class AdicionarVendaTelaController {

    @FXML
    private Button AdicionarCarrinhoButton;

    @FXML
    private TableColumn<ItemVenda, Alimento> AlimentoNomeCarrinho;

    @FXML
    private TableColumn<Alimento, String> AlimentosNomeColumn;
    
    @FXML
    private TableView<ItemVenda> CarrinhoTableView;

    @FXML
    private TableView<Alimento> AlimentosTableView;

    @FXML
    private ChoiceBox<Cliente> ClienteCB;

    @FXML
    private Button CriarVendaButton;

    @FXML
    private ChoiceBox<Funcionario> FuncionarioCB;

    @FXML
    private Button PromocoesButton;

    @FXML
    private TableColumn<ItemVenda, Integer> QuantidadeCarrinho;

    @FXML
    private TextField QuantidadeTextField;

    @FXML
    private Button RemoverCarrinhoButton;

    @FXML
    private Label ValorLabel;

    @FXML
    private Button VoltarButton;
    
    @FXML
    private Label label1;
    
    ObservableList<ItemVenda> listaItemVenda = FXCollections.observableArrayList();
    
    double precoTotal = 0;

    public void initialize() throws ElementoJaExisteException 
    {
    	
    	AlimentosNomeColumn.setCellValueFactory(new PropertyValueFactory<Alimento, String>("Nome"));
    	AlimentosTableView.setEditable(true);
    	AlimentosTableView.setItems(listaDeAlimentos()); 
    	
    	AlimentoNomeCarrinho.setCellValueFactory(new PropertyValueFactory<ItemVenda, Alimento>("Alimento"));
    	QuantidadeCarrinho.setCellValueFactory(new PropertyValueFactory<ItemVenda, Integer>("Quantidade"));
    	CarrinhoTableView.setEditable(true);
    	CarrinhoTableView.setItems(listaItemVenda);
    	
    	Pattern pattern = Pattern.compile("[0-9]*");
		TextFormatter<String> formatter = new TextFormatter<String>((UnaryOperator<TextFormatter.Change>) change -> {
    	    return pattern.matcher(change.getControlNewText()).matches() ? change : null;
    	});
         QuantidadeTextField.setTextFormatter(formatter);
         
        ObservableList<Funcionario> listaDeFuncionarios = FXCollections.observableArrayList();
     	listaDeFuncionarios.addAll(ControladorFuncionario.getInstance().listar());     	
     	FuncionarioCB.setItems(listaDeFuncionarios);
     	
     	ObservableList<Cliente> listaDeClientes = FXCollections.observableArrayList();
     	listaDeClientes.addAll(ControladorCliente.getInstance().listar());     	
     	ClienteCB.setItems(listaDeClientes);
    }
    
    public ObservableList<Alimento> listaDeAlimentos()
    {
    	ObservableList<Alimento> listaDeAlimentos = FXCollections.observableArrayList();
    	listaDeAlimentos.addAll(ControladorAlimentos.getInstance().listar());
    	return listaDeAlimentos;
    }
    
    @FXML
    void AdicionarCarrinhoFunction(ActionEvent event) throws ElementoJaExisteException {
    	
    	ItemVenda itemvenda = new ItemVenda();
    	if(AlimentosTableView.getSelectionModel().getSelectedItem() != null && !QuantidadeTextField.getText().isBlank()) {
    		
    	itemvenda.setAlimento(AlimentosTableView.getSelectionModel().getSelectedItem());
    	itemvenda.setQuantidade(Integer.parseInt(QuantidadeTextField.getText()));
    		if(Integer.parseInt(QuantidadeTextField.getText()) <= 0)
    		{   
    			Alert alert = new Alert(AlertType.ERROR,"Quantidade Invalida");
    			alert.show();
    		}
    		else
    		{
    			listaItemVenda.add(itemvenda);
       			CarrinhoTableView.setItems(listaItemVenda);
       			label1.setText("Adicionado ao Carrinho"); 

    			int test = 0;
    			for(int a = 0; a < ControladorPromocao.getInstance().listar().size();a++)
    			{
    				if(ControladorPromocao.getInstance().listar().get(a).getProdutosValidos().contains(itemvenda.getAlimento()) 
    						&& ControladorPromocao.getInstance().listar().get(a).getTempoDeInicio().isBefore(LocalDate.now().plus(1,ChronoUnit.DAYS))
    						&& ControladorPromocao.getInstance().listar().get(a).getTempoDeFinalizacao().isAfter(LocalDate.now()))
 
    				{
    					precoTotal += itemvenda.getPre�oTotal() * (100 - ControladorPromocao.getInstance().listar().get(a).getValor()) / 100;
    					test = 1;
    				}    				
    			}
    			if(test == 0)
    			{
    				precoTotal += itemvenda.getPre�oTotal();
    			}
    			ValorLabel.setText(Double.toString(precoTotal));
    		}
    	}
    	else
    	{
    		Alert alert = new Alert(AlertType.ERROR,"Campo vazio");
			alert.show();
    	}

    }

    @FXML
    void CriarVendaFunction(ActionEvent event) throws ElementoJaExisteException, ObjetoVazioException, ElementoNaoExisteException {
    	
    	if(FuncionarioCB.getValue() != null && listaItemVenda!= null && !listaItemVenda.isEmpty() && ClienteCB.getValue() != null)
    	{
    		Venda venda = new Venda();
    		ArrayList<ItemVenda> listaItems = new ArrayList<ItemVenda>();
    		listaItems.addAll(listaItemVenda);
    		
    		venda.setCliente(ClienteCB.getValue());
    		venda.setFuncionario(FuncionarioCB.getValue());
    		venda.setDataDeVenda(LocalDateTime.now());
    		venda.setAlimentos(listaItems);
    		venda.setPrecoTotal(Double.parseDouble(ValorLabel.getText()));
    		ControladorVenda.getInstance().inserir(venda);
    		label1.setText("Venda Concluida");     	
     	}
    	else
    	{
    		Alert alert = new Alert(AlertType.ERROR,"Campo vazio");
			alert.show();
    	}

    }

    @FXML
    void PromocoesFunction(ActionEvent event) {
    	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) VoltarButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("TelaPromocao.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Promo��o");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void RemoverCarrinhoFunction(ActionEvent event) throws ElementoJaExisteException {

    	int test = 0;
		for(int a = 0; a < ControladorPromocao.getInstance().listar().size();a++)
		{
			if(ControladorPromocao.getInstance().listar().get(a).getProdutosValidos().contains(CarrinhoTableView.getSelectionModel().getSelectedItem().getAlimento()) 
					&& ControladorPromocao.getInstance().listar().get(a).getTempoDeInicio().isBefore(LocalDate.now().plus(1,ChronoUnit.DAYS))
					&& ControladorPromocao.getInstance().listar().get(a).getTempoDeFinalizacao().isAfter(LocalDate.now()))

			{
				precoTotal -= CarrinhoTableView.getSelectionModel().getSelectedItem().getPre�oTotal() * (100 - ControladorPromocao.getInstance().listar().get(a).getValor()) / 100;
				test = 1;
			}    				
		}
		if(test == 0)
		{
			precoTotal -= CarrinhoTableView.getSelectionModel().getSelectedItem().getPre�oTotal();
		}
		ValorLabel.setText(Double.toString(precoTotal));
    	listaItemVenda.remove(CarrinhoTableView.getSelectionModel().getSelectedItem());
    	label1.setText("Removido do Carrinho");
    	
    }

    @FXML
    void VoltarFunction(ActionEvent event) {

    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) VoltarButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("TelaVendas.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Vendas");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

}

package main.java.br.ProjetoPizzaria.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.negocio.ControladorAlimentos;
import main.java.br.ProjetoPizzaria.negocio.ControladorVenda;
import main.java.br.ProjetoPizzaria.negocio.beans.Cliente;
import main.java.br.ProjetoPizzaria.negocio.beans.Funcionario;
import main.java.br.ProjetoPizzaria.negocio.beans.ItemVenda;
import main.java.br.ProjetoPizzaria.negocio.beans.Venda;

public class RelatorioTelaController {

    @FXML
    private TableColumn<Venda, Cliente> ClienteColumn;

    @FXML
    private TableColumn<Venda, Funcionario> FuncionarioColumn;

    @FXML
    private ListView<ItemVenda> RankingAlimentos;

    @FXML
    private Label NdeVendasLabel;

    @FXML
    private TextField NomeArquivoTextField;

    @FXML
    private Label PrecoLabel;

    @FXML
    private Button RelatorioDiarioButton;

    @FXML
    private Button RelatorioMensalButton;

    @FXML
    private Button RelatorioAnuallButton;

    @FXML
    private Button SalvarButton;

    @FXML
    private TableColumn<Venda, LocalDateTime> TempoDaVendaColumn;
    

    @FXML
    private TableColumn<Venda, Double> PrecoColumn;

    @FXML
    private TableView<Venda> VendaTableView;

    @FXML
    private Button VoltarButton;

    @FXML
    void RelatorioDiarioFunction(ActionEvent event) throws ElementoJaExisteException {
    	int precoTotal = 0;
    	
    	ObservableList<Venda> listaDeVendaDiaria = FXCollections.observableArrayList();
    	listaDeVendaDiaria.addAll(ControladorVenda.getInstance().listarVendasDiarias());
    	
    	FuncionarioColumn.setCellValueFactory(new PropertyValueFactory<Venda, Funcionario>("Funcionario"));
    	ClienteColumn.setCellValueFactory(new PropertyValueFactory<Venda, Cliente>("Cliente"));
    	TempoDaVendaColumn.setCellValueFactory(new PropertyValueFactory<Venda, LocalDateTime>("DataDeVenda"));
    	PrecoColumn.setCellValueFactory(new PropertyValueFactory<Venda,Double>("PrecoTotal"));
    	VendaTableView.setEditable(true);
    	VendaTableView.setItems(listaDeVendaDiaria); 
    	
    	NdeVendasLabel.setText(Integer.toString(listaDeVendaDiaria.size()));
    	
    	for(int a = 0; a < listaDeVendaDiaria.size();a++)
    	{
    		precoTotal += listaDeVendaDiaria.get(a).getPrecoTotal();
    	}
    	PrecoLabel.setText(Double.toString(precoTotal));
    	
    	 ArrayList<ItemVenda> listaItemVenda = new ArrayList<ItemVenda>();
    	 ArrayList<ItemVenda> listaItemVendaRanking = new ArrayList<ItemVenda>();
    	 	
    	for(int b = 0; b < listaDeVendaDiaria.size();b++)
    	{
    		for(int c = 0; c < listaDeVendaDiaria.get(b).getAlimentos().size();c++)
    		{
    			listaItemVenda.add(listaDeVendaDiaria.get(b).getAlimentos().get(c));
    		}
    	}
    	
    	for(int d = 0; d < ControladorAlimentos.getInstance().listar().size();d++)
    	{
    		ItemVenda itemvenda = new ItemVenda();
    		itemvenda.setAlimento(ControladorAlimentos.getInstance().listar().get(d));
    		itemvenda.setQuantidade(0);
    		listaItemVendaRanking.add(itemvenda);
    	}    	
    	for(int e = 0; e < listaItemVendaRanking.size();e++)
    	{
    	  for(int f = 0; f < listaItemVenda.size();f++)
    	  {
    		 if(listaItemVendaRanking.get(e).getAlimento().getNome().equals(listaItemVenda.get(f).getAlimento().getNome()))
    		 {
    			 listaItemVendaRanking.get(e).setQuantidade(listaItemVendaRanking.get(e).getQuantidade() + listaItemVenda.get(f).getQuantidade());
    		 }
    	  }
    	}
    	
    	Comparator<ItemVenda> ranking = (ItemVenda itemvenda1, ItemVenda itemvenda2) -> Integer.compare(itemvenda1.getQuantidade(), itemvenda2.getQuantidade());
    	Collections.sort(listaItemVendaRanking, ranking);
    	Collections.reverse(listaItemVendaRanking);
    	RankingAlimentos.setItems(FXCollections.observableArrayList(listaItemVendaRanking));
    
    	
    }
    
    @FXML
    void RelatorioMensalFunction(ActionEvent event) throws ElementoJaExisteException {
    	
        int precoTotal = 0;
    	
    	ObservableList<Venda> listaDeVendaMensal = FXCollections.observableArrayList();
    	listaDeVendaMensal.addAll(ControladorVenda.getInstance().listarVendasMensais());
    	
    	FuncionarioColumn.setCellValueFactory(new PropertyValueFactory<Venda, Funcionario>("Funcionario"));
    	ClienteColumn.setCellValueFactory(new PropertyValueFactory<Venda, Cliente>("Cliente"));
    	TempoDaVendaColumn.setCellValueFactory(new PropertyValueFactory<Venda, LocalDateTime>("DataDeVenda"));
    	PrecoColumn.setCellValueFactory(new PropertyValueFactory<Venda,Double>("PrecoTotal"));
    	VendaTableView.setEditable(true);
    	VendaTableView.setItems(listaDeVendaMensal); 
    	
    	NdeVendasLabel.setText(Integer.toString(listaDeVendaMensal.size()));
    	
    	for(int a = 0; a < listaDeVendaMensal.size();a++)
    	{
    		precoTotal += listaDeVendaMensal.get(a).getPrecoTotal();
    	}
    	PrecoLabel.setText(Double.toString(precoTotal));
    	
    	 ArrayList<ItemVenda> listaItemVenda = new ArrayList<ItemVenda>();
    	 ArrayList<ItemVenda> listaItemVendaRanking = new ArrayList<ItemVenda>();
    	 	
    	for(int b = 0; b < listaDeVendaMensal.size();b++)
    	{
    		for(int c = 0; c < listaDeVendaMensal.get(b).getAlimentos().size();c++)
    		{
    			listaItemVenda.add(listaDeVendaMensal.get(b).getAlimentos().get(c));
    		}
    	}
    	
    	for(int d = 0; d < ControladorAlimentos.getInstance().listar().size();d++)
    	{
    		ItemVenda itemvenda = new ItemVenda();
    		itemvenda.setAlimento(ControladorAlimentos.getInstance().listar().get(d));
    		itemvenda.setQuantidade(0);
    		listaItemVendaRanking.add(itemvenda);
    	}    	
    	for(int e = 0; e < listaItemVendaRanking.size();e++)
    	{
    	  for(int f = 0; f < listaItemVenda.size();f++)
    	  {
    		 if(listaItemVendaRanking.get(e).getAlimento().getNome().equals(listaItemVenda.get(f).getAlimento().getNome()))
    		 {
    			 listaItemVendaRanking.get(e).setQuantidade(listaItemVendaRanking.get(e).getQuantidade() + listaItemVenda.get(f).getQuantidade());
    		 }
    	  }
    	}
    	
    	Comparator<ItemVenda> ranking = (ItemVenda itemvenda1, ItemVenda itemvenda2) -> Integer.compare(itemvenda1.getQuantidade(), itemvenda2.getQuantidade());
    	Collections.sort(listaItemVendaRanking, ranking);
    	Collections.reverse(listaItemVendaRanking);
    	RankingAlimentos.setItems(FXCollections.observableArrayList(listaItemVendaRanking));

    }

    @FXML
    void RelatorioAnualFunction(ActionEvent event) throws ElementoJaExisteException {
    	
        int precoTotal = 0;
    	
    	ObservableList<Venda> listaDeVendaAnual = FXCollections.observableArrayList();
    	listaDeVendaAnual.addAll(ControladorVenda.getInstance().listarVendasAnuais());
    	
    	FuncionarioColumn.setCellValueFactory(new PropertyValueFactory<Venda, Funcionario>("Funcionario"));
    	ClienteColumn.setCellValueFactory(new PropertyValueFactory<Venda, Cliente>("Cliente"));
    	TempoDaVendaColumn.setCellValueFactory(new PropertyValueFactory<Venda, LocalDateTime>("DataDeVenda"));
    	PrecoColumn.setCellValueFactory(new PropertyValueFactory<Venda,Double>("PrecoTotal"));
    	VendaTableView.setEditable(true);
    	VendaTableView.setItems(listaDeVendaAnual); 
    	
    	NdeVendasLabel.setText(Integer.toString(listaDeVendaAnual.size()));
    	
    	for(int a = 0; a < listaDeVendaAnual.size();a++)
    	{
    		precoTotal += listaDeVendaAnual.get(a).getPrecoTotal();
    	}
    	PrecoLabel.setText(Double.toString(precoTotal));
    	
    	 ArrayList<ItemVenda> listaItemVenda = new ArrayList<ItemVenda>();
    	 ArrayList<ItemVenda> listaItemVendaRanking = new ArrayList<ItemVenda>();
    	 	
    	for(int b = 0; b < listaDeVendaAnual.size();b++)
    	{
    		for(int c = 0; c < listaDeVendaAnual.get(b).getAlimentos().size();c++)
    		{
    			listaItemVenda.add(listaDeVendaAnual.get(b).getAlimentos().get(c));
    		}
    	}
    	
    	for(int d = 0; d < ControladorAlimentos.getInstance().listar().size();d++)
    	{
    		ItemVenda itemvenda = new ItemVenda();
    		itemvenda.setAlimento(ControladorAlimentos.getInstance().listar().get(d));
    		itemvenda.setQuantidade(0);
    		listaItemVendaRanking.add(itemvenda);
    	}    	
    	for(int e = 0; e < listaItemVendaRanking.size();e++)
    	{
    	  for(int f = 0; f < listaItemVenda.size();f++)
    	  {
    		 if(listaItemVendaRanking.get(e).getAlimento().getNome().equals(listaItemVenda.get(f).getAlimento().getNome()))
    		 {
    			 listaItemVendaRanking.get(e).setQuantidade(listaItemVendaRanking.get(e).getQuantidade() + listaItemVenda.get(f).getQuantidade());
    		 }
    	  }
    	}
    	
    	Comparator<ItemVenda> ranking = (ItemVenda itemvenda1, ItemVenda itemvenda2) -> Integer.compare(itemvenda1.getQuantidade(), itemvenda2.getQuantidade());
    	Collections.sort(listaItemVendaRanking, ranking);
    	Collections.reverse(listaItemVendaRanking);
    	RankingAlimentos.setItems(FXCollections.observableArrayList(listaItemVendaRanking));

    }

    @FXML
    void SalvarFunction(ActionEvent event) throws FileNotFoundException {
    	
    	if(!NdeVendasLabel.getText().isBlank() && !PrecoLabel.getText().isBlank() && !RankingAlimentos.getItems().isEmpty()
    	    && !VendaTableView.getItems().isEmpty() && !NomeArquivoTextField.getText().isBlank())
    	{
    		File file = new File(NomeArquivoTextField.getText()+".txt");

            Formatter formatter = new Formatter(file);
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-uuu-HH-mm-ss");
            
  
        			try {
            			String data = LocalDateTime.now().format(formatador);
            			formatter.format("Relatorio: %s" + "\n\n", data);
            			formatter.format("Numero de Vendas: %s \n", NdeVendasLabel.getText());
            			formatter.format("Preço Total das Vendas: %s \n", PrecoLabel.getText());
            			formatter.format("Lista de vendas: " + "\n");
            			formatter.format("Tempo De Venda			Funcionario			Cliente				Preço" + "\n");
            			for(int a = 0; a < VendaTableView.getItems().size();a++)
            			{   Venda venda = VendaTableView.getItems().get(a);
            				formatter.format("%s		%s				%s				%.2f " + "\n", venda.getDataDeVenda().format(formatador), venda.getFuncionario().getNome(), venda.getCliente().getNome(), venda.getPrecoTotal());
            			}
            			formatter.format("Ranking Alimentos: \n");
            			for(int b = 0; b < RankingAlimentos.getItems().size();b++)
            			{
            				formatter.format("Alimento: %s          ,Quantidade de Vendas: %d \n", RankingAlimentos.getItems().get(b).getAlimento().getNome(),RankingAlimentos.getItems().get(b).getQuantidade());
            			}
            			
            			
            			formatter.close();
            		}
            		catch(Exception e)
            		{
            			e.getStackTrace();
            		}
    	}
    	else
    	{
    		Alert alert = new Alert(AlertType.ERROR,"Campos Vazios");
    		alert.show();
    	}
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

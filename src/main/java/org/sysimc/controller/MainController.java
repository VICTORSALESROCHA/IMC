package org.sysimc.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.sysimc.model.Pessoa;
import org.sysimc.utils.ArquivoUtil;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class MainController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtAltura;

    @FXML
    private TextField txtPeso;

    @FXML
    private Label lbIMC;

    @FXML
    private Label lbClassificacao;

    @FXML
    private TableView<Pessoa> tabelaPessoas;

    @FXML
    private TableColumn<Pessoa, Integer> colunaId;

    @FXML
    private TableColumn<Pessoa, String> colunaNome;

    @FXML
    private TableColumn<Pessoa, Float> colunaAltura;

    @FXML
    private TableColumn<Pessoa, Float> colunaPeso;

    @FXML
    private TableColumn<Pessoa, Float> colunaImc;

    private Pessoa pessoa = new Pessoa();

    @FXML
    public void initialize() {

        colunaNome.setCellValueFactory(
                new PropertyValueFactory<>("nome")
        );

        colunaAltura.setCellValueFactory(
                new PropertyValueFactory<>("altura")
        );

        colunaPeso.setCellValueFactory(
                new PropertyValueFactory<>("peso")
        );

        colunaImc.setCellValueFactory(
                new PropertyValueFactory<>("imc")
        );

        colunaId.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(
                        tabelaPessoas.getItems().indexOf(
                                cellData.getValue()
                        ) + 1
                )
        );
    }

    @FXML
    protected void onCalcularIMCClick() {

        try {

            pessoa.setNome(txtNome.getText());

            pessoa.setAltura(
                    Float.parseFloat(
                            txtAltura.getText().replace(",", ".")
                    )
            );

            pessoa.setPeso(
                    Float.parseFloat(
                            txtPeso.getText().replace(",", ".")
                    )
            );

            float imc = pessoa.calcularIMC();

            DecimalFormat df =
                    new DecimalFormat("#0.00");

            lbIMC.setText(
                    df.format(imc)
            );

            lbClassificacao.setText(
                    pessoa.classificacaoIMC()
            );

        } catch (NumberFormatException e) {

            mostrarAlerta(
                    "Erro",
                    "Digite valores válidos para altura e peso."
            );
        }
    }

    @FXML
    protected void onSalvarClick() {

        try {

            String nome = txtNome.getText();

            float altura =
                    Float.parseFloat(
                            txtAltura.getText().replace(",", ".")
                    );

            float peso =
                    Float.parseFloat(
                            txtPeso.getText().replace(",", ".")
                    );

            Pessoa pessoaSalvar =
                    new Pessoa();

            pessoaSalvar.setNome(nome);
            pessoaSalvar.setAltura(altura);
            pessoaSalvar.setPeso(peso);

            pessoaSalvar.calcularIMC();

            ArquivoUtil.salvar(
                    pessoaSalvar
            );

            mostrarAlerta(
                    "Sucesso",
                    "Dados salvos com sucesso."
            );

        } catch (NumberFormatException e) {

            mostrarAlerta(
                    "Erro",
                    "Digite valores válidos."
            );

        } catch (IOException e) {

            mostrarAlerta(
                    "Erro",
                    "Não foi possível salvar o arquivo."
            );
        }
    }

    @FXML
    protected void onCarregarDadosClick() {

        try {

            List<Pessoa> pessoas =
                    ArquivoUtil.carregar();

            ObservableList<Pessoa> dados =
                    FXCollections.observableArrayList(
                            pessoas
                    );

            tabelaPessoas.setItems(
                    dados
            );

        } catch (IOException e) {

            mostrarAlerta(
                    "Erro",
                    "Não foi possível carregar os dados."
            );
        }
    }

    private void mostrarAlerta(
            String titulo,
            String mensagem
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(titulo);

        alert.setHeaderText(null);

        alert.setContentText(
                mensagem
        );

        alert.showAndWait();
    }
}
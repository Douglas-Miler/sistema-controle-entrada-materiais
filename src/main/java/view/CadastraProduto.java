package view;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import controller.CadastraProdutoController;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.AlertUtil;

public class CadastraProduto extends Application {

	private VBox mainVBox;
	private HBox hBoxForInput, hBoxForButtons;
	private Button saveButton, cancelButton;
	private TextField codeBarField, productNameField, labelField, descriptionField, weightField, volumeField,
			unityField;
	private Label title, codeBarLabel, productNamelabel, productLabel, descriptionLabel, weightLabel, volumeLabel,
			unityLabel;
	private GridPane gridPane;
	private Stage stageOwner, primaryStage;
	private CadastraProdutoController cadastraProdutoController;

	public CadastraProduto(Stage stageOwner) {
		this.stageOwner = stageOwner;
		cadastraProdutoController = new CadastraProdutoController();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;

		setMainVBoxProperties();

		setPrimaryStageProperties(primaryStage, new Group(mainVBox));
		this.primaryStage.show();
	}

	private void setPrimaryStageProperties(Stage primaryStage, Group group) {
		primaryStage.setScene(new Scene(group, 650, 550));
		primaryStage.setX(358.0);
		primaryStage.setY(109.0);
		primaryStage.initModality(Modality.WINDOW_MODAL);
		primaryStage.initOwner(this.stageOwner);
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(event -> {
			this.setCloseStageAction();
			event.consume();
		});
	}

	private void setHBoxForButtonsProperties() {

		setSaveButtonProperties();
		setCancelButtonProperties();

		hBoxForButtons = new HBox(saveButton, cancelButton);
		hBoxForButtons.setPadding(new Insets(50, 0, 0, 0));
		hBoxForButtons.setAlignment(Pos.CENTER);
		HBox.setMargin(cancelButton, new Insets(0, 0, 0, 10));
	}

	private void setCancelButtonProperties() {
		cancelButton = new Button("Cancelar");

		cancelButton.setOnAction(event -> this.setCloseStageAction());
	}

	private void setSaveButtonProperties() {
		saveButton = new Button("Salvar");

		saveButton.setOnAction(event -> {
			boolean operationResult = cadastraProdutoController.inputProductValidator(codeBarField.getText(),
					productNameField.getText(), labelField.getText(), descriptionField.getText(), weightField.getText(),
					volumeField.getText(), unityField.getText());

			if (operationResult) {
				cadastraProdutoController.insertProductInDatabase();

				new AlertUtil().createAlert(Alert.AlertType.INFORMATION, "Produto inserido com sucesso", ButtonType.OK,
						"Atenção").showAndWait();
			} else
				new AlertUtil().createAlert(Alert.AlertType.INFORMATION,
						"Verifique se os campos com (*) estão preenchidos, pois são obrigatórios."
								+ "\nVerifique também se os demais campos estão preenchidos corretamente.",
						ButtonType.OK, "Atenção").showAndWait();

		});
	}

	private void setCloseStageAction() {

		List<TextField> fieldsList = Arrays.asList(codeBarField, productNameField, labelField, descriptionField,
				weightField, volumeField, unityField);

		boolean isClosable = true;

		for (TextField textField : fieldsList) {
			if (!textField.getText().isEmpty()) {

				Optional<ButtonType> result = new AlertUtil()
						.createAlert(Alert.AlertType.CONFIRMATION,
								"O trabalho não será salvo e será perdido \nDeseja continuar?", "Atenção")
						.showAndWait();

				if (result.get() == ButtonType.CANCEL) {
					isClosable = false;
				}
				
				break;
			}
		}

		if (isClosable)
			this.primaryStage.close();
	}

	private List<TextField> setFieldsProperties() {
		codeBarField = new TextField();
		productNameField = new TextField();
		labelField = new TextField();
		descriptionField = new TextField();
		weightField = new TextField();
		volumeField = new TextField();
		unityField = new TextField();

		List<TextField> fieldList = Arrays.asList(codeBarField, productNameField, labelField, descriptionField,
				weightField, volumeField, unityField);

		increaseWidthSize(fieldList);

		return fieldList;
	}

	private void increaseWidthSize(List<TextField> fieldList) {
		for (TextField field : fieldList) {
			field.setMinWidth(300);
		}
	}

	private List<Node> setInputLabelsProperties() {
		codeBarLabel = new Label("Código de Barras: (*)");
		productNamelabel = new Label("Nome: (*)");
		productLabel = new Label("Marca: (*)");
		descriptionLabel = new Label("Descrição:");
		weightLabel = new Label("Peso:");
		volumeLabel = new Label("Volume:");
		unityLabel = new Label("Unidades/Pct:");

		List<Node> labelList = Arrays.asList(codeBarLabel, productNamelabel, productLabel, descriptionLabel,
				weightLabel, volumeLabel, unityLabel);

		setStyles(labelList);

		return labelList;
	}

	private void setTitleProperties() {
		title = new Label("Cadastro de Produto");
		title.setPadding(new Insets(60, 0, 0, 0));
		title.setMinWidth(650);
		title.setAlignment(Pos.CENTER);
		title.setStyle("-fx-font-family: \"Verdana\"");
		title.setStyle("-fx-font-size: 20");
	}

	private void setStyles(List<Node> nodes) {
		for (Node node : nodes) {
			node.setStyle("-fx-font-family: \"Verdana\"");
			node.setStyle("-fx-font-size: 15");
		}
	}

	private void setMainVBoxProperties() {

		setTitleProperties();
		setHBoxForInputProperties();
		setHBoxForButtonsProperties();

		mainVBox = new VBox(title, hBoxForInput, hBoxForButtons);
	}

	private void setHBoxForInputProperties() {

		setGridPaneProperties();

		hBoxForInput = new HBox(gridPane);
		hBoxForInput.setAlignment(Pos.CENTER);
		hBoxForInput.setPadding(new Insets(50, 0, 0, 0));
	}

	private void setGridPaneProperties() {

		List<Node> labelsList = setInputLabelsProperties();

		List<TextField> fieldsList = setFieldsProperties();

		this.gridPane = new GridPane();

		addLabelsInGridPane(labelsList);
		addFieldsInGridPane(fieldsList);

		gridPane.setHgap(10);
		gridPane.setVgap(10);

		alignElements(labelsList);
	}

	private void addFieldsInGridPane(List<? extends Node> fieldsList) {
		for (int i = 0; i < fieldsList.size(); i++) {
			gridPane.add(fieldsList.get(i), 1, i, 2, 1);
		}

	}

	private void addLabelsInGridPane(List<Node> labelsList) {
		for (int i = 0; i < labelsList.size(); i++) {
			gridPane.add(labelsList.get(i), 0, i, 1, 1);
		}
	}

	private void alignElements(List<Node> nodes) {

		for (Node node : nodes) {
			GridPane.setHalignment(node, HPos.RIGHT);
		}

	}

}

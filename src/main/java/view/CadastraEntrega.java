package view;

import java.util.Optional;

import controller.CadastraEntregaController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ClienteDTO;
import model.ItemTabela;
import util.AlertUtil;

public class CadastraEntrega extends Application {

	private Stage stageOwner, primaryStage;
	private ClienteDTO clienteComIdENome;
	private Label productOwnerLabel, codeBarLabel, quantityLabel, dateLabel;
	private HBox baseHBox, inputQuantityHBox, lowerContainerHBox;
	private TextField codeBarTextField, quantityTextField;
	private Button addQuantityButton, goToPreviousWindowButton, removeItemFromTableButton, finishOperationButton;
	private TableView<ItemTabela> tableView;
	private VBox mainVBox, inputContainerVBox, tableContainerVBox;
	private ObservableList<ItemTabela> listOfTableItems;
	private CadastraEntregaController cadastraEntregaController;
	private DatePicker deliveryDatePicker;

	public CadastraEntrega(Stage stageOwner, ClienteDTO clienteComIdENome) {
		this.stageOwner = stageOwner;
		this.clienteComIdENome = clienteComIdENome;
		this.baseHBox = new HBox();
		this.mainVBox = new VBox();
		this.listOfTableItems = FXCollections.observableArrayList();
		this.cadastraEntregaController = new CadastraEntregaController();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;

		setProductOwnerLabelProperties();

		setInputContainerVBox();

		setTableContainerVBoxProperties();

		setLowerContainerHBox();

		this.baseHBox.getChildren().addAll(inputContainerVBox, tableContainerVBox);

		this.mainVBox.getChildren().addAll(this.productOwnerLabel, baseHBox, lowerContainerHBox);

		setCreateDeliveryStageProperties(this.primaryStage, new Group(mainVBox));

		this.primaryStage.show();
	}

	private void setLowerContainerHBox() {
		setGoToPreviousWindowButtonProperties();

		setFinishOperationButtonProperties();

		lowerContainerHBox = new HBox();
		lowerContainerHBox.setPadding(new Insets(10, 0, 0, 0));
		lowerContainerHBox.setAlignment(Pos.CENTER);
		lowerContainerHBox.getChildren().addAll(finishOperationButton, goToPreviousWindowButton);
		HBox.setMargin(goToPreviousWindowButton, new Insets(0, 0, 0, 10));
	}

	private void setGoToPreviousWindowButtonProperties() {
		goToPreviousWindowButton = new Button("Voltar");
		goToPreviousWindowButton.setOnAction(event -> {
			this.setCloseStageAction();
		});
	}

	private void setFinishOperationButtonProperties() {
		finishOperationButton = new Button("Finalizar");

		finishOperationButton.setOnAction(event -> {
			if (!listOfTableItems.isEmpty()) {

				cadastraEntregaController.createDeliveryInDatabase(listOfTableItems, clienteComIdENome,
						deliveryDatePicker.getValue());

				new AlertUtil().createAlert(Alert.AlertType.INFORMATION, "Itens cadastrados com sucesso!",
						ButtonType.OK, "Sucesso").showAndWait();

				this.primaryStage.close();
			}
		});

	}

	private void setTableContainerVBoxProperties() {
		setTableViewProperties();
		setRemoveItemFromTableButtonProperties();

		tableContainerVBox = new VBox();
		tableContainerVBox.setPadding(new Insets(0, 0, 0, 10));
		tableContainerVBox.setAlignment(Pos.TOP_RIGHT);
		tableContainerVBox.getChildren().addAll(tableView, removeItemFromTableButton);
		VBox.setMargin(removeItemFromTableButton, new Insets(10, 0, 0, 0));
	}

	private void setRemoveItemFromTableButtonProperties() {
		removeItemFromTableButton = new Button("Remover Item");
		removeItemFromTableButton.setOnAction(event -> {
			int initialIndex = 1;
			int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
			if (selectedIndex != -1) {
				listOfTableItems.remove(selectedIndex);
				for (ItemTabela item : listOfTableItems) {
					item.setIndiceLista(initialIndex++);
				}
			}
		});
	}

	public void populateTableView(ItemTabela item) {
		this.listOfTableItems.add(item);
		this.tableView.setItems(listOfTableItems);
	}

	private void setTableViewProperties() {
		this.tableView = new TableView<>();

		tableView.setMinWidth(800);
		tableView.setEditable(false);

		TableColumn<ItemTabela, String> itemColumn = new TableColumn<>("ITEM");
		itemColumn.setCellValueFactory(new PropertyValueFactory<>("indiceLista"));

		TableColumn<ItemTabela, String> nameColumn = new TableColumn<>("NOME");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
		nameColumn.setMinWidth(300);

		TableColumn<ItemTabela, String> labelColumn = new TableColumn<>("MARCA");
		labelColumn.setCellValueFactory(new PropertyValueFactory<>("marcaProduto"));
		labelColumn.setMinWidth(300);

		TableColumn<ItemTabela, String> quantityColumn = new TableColumn<>("QUANTDADE");
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

		tableView.getColumns().add(itemColumn);
		tableView.getColumns().add(nameColumn);
		tableView.getColumns().add(labelColumn);
		tableView.getColumns().add(quantityColumn);

	}

	private void setInputContainerVBox() {
		setCodeBarLabelProperties();
		setCodeBarTextFieldProperties();
		setQuantityLabelProperties();
		setInputQuantityHBoxProperties();
		setDateLabelProperties();
		setDatePickerProperties();

		inputContainerVBox = new VBox();
		inputContainerVBox.setPadding(new Insets(20, 0, 0, 10));
		inputContainerVBox.getChildren().addAll(codeBarLabel, codeBarTextField, quantityLabel, inputQuantityHBox,
				dateLabel, deliveryDatePicker);
		VBox.setMargin(codeBarTextField, new Insets(10, 0, 0, 0));
		VBox.setMargin(quantityLabel, new Insets(10, 0, 0, 0));
		VBox.setMargin(inputQuantityHBox, new Insets(10, 0, 0, 0));
		VBox.setMargin(dateLabel, new Insets(10, 0, 0, 0));
		VBox.setMargin(deliveryDatePicker, new Insets(10, 0, 0, 0));
	}

	private void setDateLabelProperties() {
		this.dateLabel = new Label("Data de Entrega");
	}

	private void setDatePickerProperties() {
		this.deliveryDatePicker = new DatePicker();
	}

	private void setInputQuantityHBoxProperties() {
		setQuantityTextFieldProperties();
		setAddQuantityButtonProperties();

		inputQuantityHBox = new HBox();
		inputQuantityHBox.getChildren().addAll(quantityTextField, addQuantityButton);
		HBox.setMargin(addQuantityButton, new Insets(0, 0, 0, 10));
	}

	private void setAddQuantityButtonProperties() {
		addQuantityButton = new Button("Adicionar");
		addQuantityButton.setOnAction(event -> {
			inputQuantityHandler();
		});
	}

	private void inputQuantityHandler() {
		if (!listOfTableItems.isEmpty()) {

			String quantity = quantityTextField.getText();

			if (!cadastraEntregaController.inputQuantityValidator(quantity)) {
				new AlertUtil().createAlert(Alert.AlertType.INFORMATION, "Insira um número válido", ButtonType.OK,
						"Informação").showAndWait();
			} else {
				listOfTableItems.get(listOfTableItems.size() - 1).setQuantidade(Integer.parseInt(quantity));
				ObservableList<ItemTabela> listAux = FXCollections.observableArrayList(listOfTableItems);
				tableView.getItems().clear();
				for (ItemTabela itemTabela : listAux) {
					populateTableView(itemTabela);
				}

				quantityTextField.clear();
			}
		} else {
			new AlertUtil().createAlert(Alert.AlertType.INFORMATION, "Insira ao menos um elemento na tabela",
					ButtonType.OK, "Informação").showAndWait();
		}
	}

	private void setQuantityTextFieldProperties() {
		quantityTextField = new TextField();
		quantityTextField.setOnAction(event -> inputQuantityHandler());
	}

	private void setQuantityLabelProperties() {
		this.quantityLabel = new Label("Quantidade");
	}

	private void setCodeBarTextFieldProperties() {
		codeBarTextField = new TextField();
		codeBarTextField.setOnAction(event -> {
			if (!cadastraEntregaController.inputCodeBarValidator(codeBarTextField.getText())) {
				new AlertUtil().createAlert(Alert.AlertType.INFORMATION, "Insira um código de barras válido",
						ButtonType.OK, "Informação").showAndWait();
			} else {
				ItemTabela itemTabela = cadastraEntregaController.searchItemInDataBase();
				if (itemTabela.getIdProduto() == -1) {

					Optional<ButtonType> result = new AlertUtil().createAlert(Alert.AlertType.CONFIRMATION,
							"Não há registro com o código de barras informado. \nDeseja cadastrar este novo produto?",
							"Atenção").showAndWait();

					if (result.get() == ButtonType.OK) {
						try {
							new CadastraProduto(this.primaryStage).start(new Stage());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				} else {
					codeBarTextField.clear();
					itemTabela.setIndiceLista(listOfTableItems.size() + 1);
					populateTableView(itemTabela);
				}
			}
		});
	}

	private void setCodeBarLabelProperties() {
		this.codeBarLabel = new Label("Código de Barras");
	}

	private void setCreateDeliveryStageProperties(Stage stage, Group group) {
		Scene scene = new Scene(group, 1050, 550);
		stage.setScene(scene);
		stage.setX(158.0);
		stage.setY(109.0);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(stageOwner);
		stage.setResizable(false);
		stage.setOnCloseRequest(event -> {
			this.setCloseStageAction();
			event.consume();
		});
	}

	private void setProductOwnerLabelProperties() {
		productOwnerLabel = new Label("Para " + this.clienteComIdENome.getNome());
		productOwnerLabel.setMinWidth(1050);
		productOwnerLabel.setPadding(new Insets(10, 0, 0, 10));
		productOwnerLabel.setStyle("-fx-font-family: \"Verdana\"");
		productOwnerLabel.setStyle("-fx-font-size: 15");
	}

	private void setCloseStageAction() {
		if (listOfTableItems.isEmpty()) {
			this.primaryStage.close();
		} else {
			Optional<ButtonType> result = new AlertUtil().createAlert(Alert.AlertType.CONFIRMATION,
					"O trabalho não será salvo e será perdido \nDeseja continuar?", "Atenção").showAndWait();

			if (result.get() == ButtonType.OK) {
				this.primaryStage.close();
			}
		}
	}
}

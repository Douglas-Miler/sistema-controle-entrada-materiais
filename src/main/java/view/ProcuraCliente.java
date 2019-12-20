package view;

import java.util.List;

import controller.ProcuraClienteController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ClienteDTO;
import util.AlertUtil;
import util.StringConstantUtil;

public class ProcuraCliente extends Application {

	private Label instructionToSearchNameLabel, instructionToMoveFowardLabel;
	private TextField searchNameTextField;
	private Button searchNameButton, readDeliveriesButton, createDeliveriesButton;
	private HBox searchNameContainerHBox, deliveryButtonsContainerHBox, scrollContainerHBox;
	private ListView<ClienteDTO> listViewOfNames;
	private ScrollPane scrollToListView;
	private ProcuraClienteController procuraClienteController;
	private VBox mainVBox;
	private Stage primaryStage;

	public ProcuraCliente() {
		this.procuraClienteController = new ProcuraClienteController();
		this.mainVBox = new VBox();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;
		
		setInstructionToSearchNameLabelProperties();

		setSearchNameContainerHBoxProperties();

		setScrollContainerHBoxProperties();

		setInstructionToMoveFowardLabelProperties();

		setDeliveryButtonsContainerHBoxProperties();

		this.mainVBox.getChildren().addAll(instructionToSearchNameLabel, searchNameContainerHBox, scrollContainerHBox,
				instructionToMoveFowardLabel, deliveryButtonsContainerHBox);

		setPrimaryStageProperties(this.primaryStage, new Group(this.mainVBox));
		
		this.primaryStage.show();
	}

	private void setPrimaryStageProperties(Stage primaryStage, Group group) {
		Scene scene = new Scene(group, 650, 550);
		primaryStage.setScene(scene);
		primaryStage.setX(358.0);
		primaryStage.setY(109.0);
		primaryStage.setTitle(StringConstantUtil.WINDOWS_TITLE.getConstantValue());
		primaryStage.setResizable(false);
	}

	private void setSearchNameTextFieldProperties() {
		searchNameTextField = new TextField();
		searchNameTextField.setOnAction(event -> {
			this.searchLogic();
		});
	}

	private void setDeliveryButtonsContainerHBoxProperties() {

		setReadDeliveriesButtonProperties();

		setCreateDeliveriesButtonProperties();

		deliveryButtonsContainerHBox = new HBox(/*readDeliveriesButton,*/ createDeliveriesButton);
		deliveryButtonsContainerHBox.setPadding(new Insets(10, 0, 0, 0));
		deliveryButtonsContainerHBox.setAlignment(Pos.CENTER);
		HBox.setMargin(createDeliveriesButton, new Insets(0, 0, 0, 10));
	}

	private void setCreateDeliveriesButtonProperties() {
		createDeliveriesButton = new Button("Cadastrar Entregas");
		createDeliveriesButton.setOnAction(event -> {
			if (listViewOfNames.getSelectionModel().getSelectedItem() == null) {
				new AlertUtil().createAlert(Alert.AlertType.INFORMATION, "Selecione um nome antes", ButtonType.OK,
						"Informação").showAndWait();
			} else {
				try {
					new CadastraEntrega(this.primaryStage, listViewOfNames.getSelectionModel().getSelectedItem())
							.start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void setReadDeliveriesButtonProperties() {
		readDeliveriesButton = new Button("Consultar Entregas");
		readDeliveriesButton.setDisable(true);
	}

	private void setInstructionToMoveFowardLabelProperties() {
		instructionToMoveFowardLabel = new Label("Selecione um nome e clique em uma das opções abaixo");
		instructionToMoveFowardLabel.setPadding(new Insets(15, 0, 0, 0));
		instructionToMoveFowardLabel.setMinWidth(650);
		instructionToMoveFowardLabel.setAlignment(Pos.CENTER);
		instructionToMoveFowardLabel.setStyle("-fx-font-family: \"Verdana\"");
		instructionToMoveFowardLabel.setStyle("-fx-font-size: 15");
	}

	private void setScrollContainerHBoxProperties() {

		setScrollToListViewProperties();

		scrollContainerHBox = new HBox(scrollToListView);
		scrollContainerHBox.setAlignment(Pos.CENTER);
		HBox.setMargin(scrollToListView, new Insets(15, 0, 0, 0));
	}

	private void setScrollToListViewProperties() {

		setListViewOfNamesProperties();

		scrollToListView = new ScrollPane(listViewOfNames);
		scrollToListView.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollToListView.setMaxHeight(200);
		scrollToListView.setMinWidth(500);
		scrollToListView.setFitToWidth(true);
	}

	private void setListViewOfNamesProperties() {
		listViewOfNames = new ListView<>();
		listViewOfNames.setEditable(false);
		listViewOfNames.setMaxHeight(200);
	}

	private void setSearchNameButtonProperties() {
		searchNameButton = new Button("Pesquisar");
		searchNameButton.setOnAction(event -> {
			this.searchLogic();
		});
	}

	private void searchLogic() {
		if (!procuraClienteController.inputStringFieldHandler(searchNameTextField.getText())) {
			new AlertUtil()
					.createAlert(Alert.AlertType.INFORMATION, "Digite um nome válido", ButtonType.OK, "Informação")
					.showAndWait();
		} else {
			listViewOfNames.getItems().clear();
			populateList(procuraClienteController.getSearchResult());
		}
	}

	private void setSearchNameContainerHBoxProperties() {

		setSearchNameTextFieldProperties();
		setSearchNameButtonProperties();

		searchNameContainerHBox = new HBox(searchNameTextField, searchNameButton);
		searchNameContainerHBox.setPadding(new Insets(10, 0, 0, 0));
		searchNameContainerHBox.setAlignment(Pos.CENTER);
		HBox.setMargin(searchNameButton, new Insets(0, 0, 0, 10));
	}

	private void setInstructionToSearchNameLabelProperties() {
		instructionToSearchNameLabel = new Label(StringConstantUtil.INSTRUCTION_TO_SEACH_NAME.getConstantValue());
		instructionToSearchNameLabel.setPadding(new Insets(80, 0, 0, 0));
		instructionToSearchNameLabel.setMinWidth(650);
		instructionToSearchNameLabel.setAlignment(Pos.CENTER);
		instructionToSearchNameLabel.setStyle("-fx-font-family: \"Verdana\"");
		instructionToSearchNameLabel.setStyle("-fx-font-size: 20");
	}

	private void populateList(List<ClienteDTO> clientes) {
		if (clientes.get(0).getId() != -1)
			for (ClienteDTO cliente : clientes)
				listViewOfNames.getItems().add(cliente);
		else
			new AlertUtil().createAlert(Alert.AlertType.INFORMATION, "Nenhum resultado para o nome pesquisado",
					ButtonType.OK, "Atenção").showAndWait();
	}

}

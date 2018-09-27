package view;

import java.io.File;
import java.io.FileInputStream;

import controller.FlexiRentSystem;
import controller.SQL;
import controller.Utility;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Apartment;
import model.Suite;

public class Main extends Application {
	ScrollPane contents = new ScrollPane();
	GridPane p0 = new GridPane();
	Stage stage;
	public MenuItem menuItem1_1_1 = new MenuItem("Add Aptartment");
	FlexiRentSystem admin = new FlexiRentSystem();

	@Override
	public void start(Stage primaryStage) {
		//Utility.importProp(admin, SQL.ViewData());
		try {
			primaryStage.setTitle("form1");
			try {
				FileInputStream input = new FileInputStream("res/f.png");
				Image image = new Image(input);
				primaryStage.getIcons().add(image);
			} catch (Exception e) {
				e.printStackTrace();
			}
			VBox menu = new VBox(addMenu());
			// FlowPane content = new FlowPane(contents);
			

			BorderPane p = new BorderPane();
			ImageView imageBox0 = Utility.drawImg("res/f.png", 300);
			ImageView imageBox1 = Utility.drawImg("res/logo3.png", 450);
			p.setTop(imageBox0);
			p.setAlignment(imageBox0, Pos.CENTER);
			
			p.setCenter(imageBox1);
			//p.setPadding(new Insets(50));
			contents.setContent(p);
			VBox root = new VBox(menu, contents);
			Scene scene = new Scene(root, 600, 600);
			primaryStage.setResizable(true);
			primaryStage.setScene(scene);
			primaryStage.show();
			stage = primaryStage;
			p.prefWidthProperty().bind(stage.widthProperty().subtract(35));
			contents.prefHeightProperty().bind(stage.heightProperty().subtract(90));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {
		launch(args);
		
	}

	private MenuBar addMenu() {
		MenuBar menuBar = new MenuBar();
		Menu menu1 = new Menu("Menu 1");
		Menu subMenu = new Menu("Add Property");
		MenuItem menuItem1_2 = new MenuItem("Return Property");
		MenuItem menuItem1_3 = new MenuItem("Exit");
		MenuItem menuItem1_1_1 = new MenuItem("Add Aptartment");
		MenuItem menuItem1_1_2 = new MenuItem("Add Suite");
		Menu menu2 = new Menu("Menu 2");
		MenuItem menuItem2_1 = new MenuItem("Item 2");
		menuBar.getMenus().add(menu1);
		menuBar.getMenus().add(menu2);
		menu1.getItems().addAll(subMenu,menuItem1_2,menuItem1_3);
		menu2.getItems().add(menuItem2_1);
		subMenu.getItems().addAll(menuItem1_1_1, menuItem1_1_2);
		menuItem1_1_1.setOnAction(new AddAptHandler());
		menuItem1_1_2.setOnAction(new AddSuiteHandler());
		menuItem1_2.setOnAction(new ReturnProperty(admin));
		menuItem1_3.setOnAction(new MenuItem1_2Handler());
		menuItem2_1.setOnAction(new ViewPropHandler());
		return menuBar;
	}

	class AddAptHandler implements EventHandler<ActionEvent> {
		@Override // Override the handle method
		public void handle(ActionEvent e) {

			System.out.println("Menu Item 1 Selected");
			// Pane container = contents;
			Pane container = new Pane();
			GridPane pane = new GridPane();
			pane.setAlignment(Pos.CENTER);
			pane.setPadding(new Insets(150, 50, 50, 150));
			pane.setHgap(10);
			pane.setVgap(6);
			pane.getColumnConstraints().add(new ColumnConstraints(150));
			pane.getColumnConstraints().add(new ColumnConstraints(150));

			// right column labels
			pane.add(new Label("Property ID:"), 0, 0);
			pane.add(new Label("Street Number:"), 0, 1);
			pane.add(new Label("Street Name:"), 0, 2);
			pane.add(new Label("Suburb:"), 0, 3);
			pane.add(new Label("Bedroom number:"), 0, 4);

			// left column textfields
			Text a = new Text();
			pane.add(new TextField(), 1, 0);
			pane.add(new TextField(), 1, 1);
			pane.add(new TextField(), 1, 2);
			pane.add(new TextField(), 1, 3);
			pane.add(new TextField(), 1, 4);

			pane.add(new Label("Photo:"), 0, 5);
			Button openButton = new Button("Open a Picture...");
			pane.add(openButton, 1, 5);
			pane.add(a, 1, 6);
			FileChooser photo = new FileChooser();
			Stage stage = new Stage();
			stage.setTitle("File Chooser Sample");
			openButton.setOnAction(t -> {
				File file = photo.showOpenDialog(stage);
				if (file != null) {
					String cwd = System.getProperty("user.dir");
					a.setText(new File(cwd).toURI().relativize(file.toURI()).getPath());
				}
			});
			Button addProp = new Button("add");
			addProp.setOnAction(new addPropHandler());
			addProp.setLayoutX(200);
			addProp.setLayoutY(400);

			Label lbl = new Label("ADD APARTMENT");
			lbl.setFont(Font.font("ADD APARTMENT", FontWeight.BOLD, 24));
			lbl.setLayoutX(180);
			lbl.setLayoutY(50);

			container.getChildren().add(addProp);
			container.getChildren().add(pane);
			p0 = pane;
			container.getChildren().add(lbl);
			contents.setContent(container);
		}
	}

	class AddSuiteHandler implements EventHandler<ActionEvent> {
		@Override // Override the handle method
		public void handle(ActionEvent e) {

			System.out.println("Menu Item 1 Selected");
			Pane container = new Pane();
			container.getChildren().clear();
			GridPane pane = new GridPane();
			pane.setPadding(new Insets(150, 50, 50, 150));
			pane.setHgap(10);
			pane.setVgap(6);
			pane.getColumnConstraints().add(new ColumnConstraints(150));
			pane.getColumnConstraints().add(new ColumnConstraints(150));

			// right column labels
			pane.add(new Label("Property ID:"), 0, 0);
			pane.add(new Label("Street Number:"), 0, 1);
			pane.add(new Label("Street Name:"), 0, 2);
			pane.add(new Label("Suburb:"), 0, 3);
			pane.add(new Label("Bedroom number:"), 0, 4);

			// left column textfields
			Text a = new Text();
			pane.add(new TextField(), 1, 0);
			pane.add(new TextField(), 1, 1);
			pane.add(new TextField(), 1, 2);
			pane.add(new TextField(), 1, 3);
			pane.add(new Text("3"), 1, 4);

			pane.add(new Label("Photo:"), 0, 5);
			Button openButton = new Button("Open a Picture...");
			pane.add(openButton, 1, 5);
			pane.add(a, 1, 6);
			FileChooser photo = new FileChooser();
			Stage stage = new Stage();
			stage.setTitle("File Chooser Sample");
			openButton.setOnAction((final ActionEvent t) -> {
				File file = photo.showOpenDialog(stage);
				if (file != null) {
					String cwd = System.getProperty("user.dir");
					a.setText(new File(cwd).toURI().relativize(file.toURI()).getPath());
				}
			});
			Button addProp = new Button("add");
			addProp.setOnAction(new addPropHandler());
			addProp.setLayoutX(200);
			addProp.setLayoutY(400);

			Label lbl = new Label("ADD SUITE");
			lbl.setFont(Font.font("ADD SUITE", FontWeight.BOLD, 24));
			lbl.setLayoutX(230);
			lbl.setLayoutY(50);

			container.getChildren().add(addProp);
			container.getChildren().add(pane);
			p0 = pane;
			container.getChildren().add(lbl);
			// admin.addProp();

			contents.setContent(container);
		}
	}

	class MenuItem1_2Handler implements EventHandler<ActionEvent> {
		@Override // Override the handle method
		public void handle(ActionEvent e) {

System.out.println("exiting"); System.exit(0);
			
			

		}
	}

	class ViewPropHandler implements EventHandler<ActionEvent> {
		@Override // Override the handle method
		public void handle(ActionEvent e) {
			System.out.println("Menu Item 2_1 Selected");
			ShowProperty a = new ShowProperty();
			// controller.SQL.ViewData();
			// VBox
			Pane container = new Pane();
			container.getChildren().clear();

			VBox vb = new VBox();
			vb.setSpacing(50);
			vb.setAlignment(Pos.TOP_CENTER);

			Label lbl = new Label("PROPERTY LIST");
			lbl.setFont(Font.font("PROPERTY LIST", FontWeight.BOLD, 24));
			// lbl.setAlignment(Pos.TOP_CENTER);

			// Buttons
			/*
			 * for (int i = 0; i < admin.getPropList().size(); i++) { Text propDetails = new
			 * Text(admin.getPropList().get(i).getDetails());
			 * vb.getChildren().add(propDetails); }
			 */

			FlowPane fp = a.show(admin);
			fp.prefWidthProperty().bind(stage.widthProperty().subtract(35));

			vb.getChildren().addAll(lbl, fp);
			// container.getChildren().addAll(vb,fp);

			contents.setContent(vb);
		}
	}

	class addPropHandler implements EventHandler<ActionEvent> {
		@Override // Override the handle method
		public void handle(ActionEvent e) {
			String id, snum, sname, suburb, imgpath;
			int bednum;
			GridPane pane = p0;
			id = ((TextField) pane.getChildren().get(5)).getText();
			snum = ((TextField) pane.getChildren().get(6)).getText();
			sname = ((TextField) pane.getChildren().get(7)).getText();
			suburb = ((TextField) pane.getChildren().get(8)).getText();
			imgpath = ((Text) pane.getChildren().get(12)).getText();
			try {
				bednum = Integer.parseInt(((TextField) pane.getChildren().get(9)).getText());
				Apartment apt = new Apartment(id, snum, sname, suburb, bednum, false,imgpath);
				System.out.println(id + " " + snum + " " + sname + " " + suburb + " " + bednum + " " + imgpath);
				SQL.insertData(apt);
			} catch (ClassCastException e2) {
				bednum = Integer.parseInt(((Text) pane.getChildren().get(9)).getText());
				Suite suite = new Suite(id, snum, sname, suburb, false,imgpath);
				System.out.println("SUTIE "+id + " " + snum + " " + sname + " " + suburb + " " + bednum + " " + imgpath);
				SQL.insertData(suite);
			}

		}
	}

	class ReturnPropHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
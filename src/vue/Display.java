package vue;
import java.io.File;
import java.security.acl.Group;
import java.util.List;

import application.Main;
import vue.CopyTask;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.PathTransition.OrientationType;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import logiciel.Processus;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.util.Duration;
;

public class Display implements Runnable {
	public static CopyTask copyTask;
	public static MenuBar menu() {
		MenuBar menuBar = new MenuBar();
		Menu file = new Menu("File");
		Menu help = new Menu("Help");
		MenuItem start = new MenuItem("Start");
		MenuItem cancel = new MenuItem("Cancel");
		//start.setOnAction();
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(e->{System.exit(0);});
		MenuItem about = new MenuItem("About DCJSimulOS");
		about.setOnAction(e->{System.out.println("en cours...");});
		menuBar.getMenus().add(file);
		menuBar.getMenus().add(help);
		file.getItems().add(start);
		file.getItems().add(cancel);
		file.getItems().add(exit);
		help.getItems().add(about);
		return menuBar;
	}

	public static PieChart pie_chart() {
		final PieChart chart = new PieChart(); 
		chart.setPrefSize(100, 100);
		chart.setLegendSide(Side.LEFT);
		chart.setLabelLineLength(5);
		chart.getData().setAll(new PieChart.Data("O/S", 50), new PieChart.Data("P1.exe", 30),  
				new PieChart.Data("P2.exe", 25), new PieChart.Data("P3.exe", 42), 
				new PieChart.Data("P4.exe", 5) ,new PieChart.Data("P5.exe", 10)
				); 
		return chart;	
	}

	////////////////////////////////////////////////////////////////////////////////
	public static ObservableList<Processus> getProcessus(){
		ObservableList<Processus> processus = FXCollections.observableArrayList();
		processus.add(new Processus(1, "P1.exe"));
		processus.add(new Processus(7, "P2.exe"));
		processus.add(new Processus(9, "P3.exe"));
		processus.add(new Processus(0, "P4.exe"));
		processus.add(new Processus(4, "P5.exe"));
		return processus;
	}

	public static TableView<Processus> tabview(){
		TableColumn<Processus, String> nameColumn =new TableColumn<>("Name");
		nameColumn.setMinWidth(100);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<Processus, String> pidColumn =new TableColumn<>("Pid");
		pidColumn.setMinWidth(100);
		pidColumn.setCellValueFactory(new PropertyValueFactory<>("pid_process"));
		TableView<Processus> table = new TableView<>();
		table.getColumns().add(pidColumn);
		table.getColumns().add(nameColumn);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		//table.setItems(getProcessus());
		return table;


	}

//	public static Tab info_process(){
//		final ProgressBar progressBar = new ProgressBar(0);
//		final ProgressIndicator progressIndicator = new ProgressIndicator(-1);
//		// Create a Task.
//		copyTask = new CopyTask();
//
//		// Unbind progress property
//		progressBar.progressProperty().unbind();
//
//		// Bind progress property
//		progressBar.progressProperty().bind(copyTask.progressProperty());
//
//		// Hủy bỏ kết nối thuộc tính progress
//		progressIndicator.progressProperty().unbind();
//
//		// Bind progress property.
//		progressIndicator.progressProperty().bind(copyTask.progressProperty());
//		// When completed tasks
//		copyTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, //
//				new EventHandler<WorkerStateEvent>() {
//
//			@Override
//			public void handle(WorkerStateEvent t) {
//				List<File> copied = copyTask.getValue();
//				System.out.println("papa");
//				TabPane tabpane = new TabPane();
//				//tabpane.setSide(Side.LEFT);
//				tabpane.setNodeOrientation(NodeOrientation.INHERIT);
//				Tab tab_disque = new Tab("Disk");
//				tab_disque.setClosable(false);
//				tabpane.getTabs().setAll(Display.OverView_Init(), Display.tabmemory(), tab_disque);
//				BorderPane.setAlignment(tabpane, Pos.TOP_RIGHT);
//				BorderPane.setMargin(tabpane, new Insets(5, 5, 3, 5));
//				//ajout de notre tabpane a droite de la scene
//			}
//		});
//
//		// Start the Task.
//		new Thread(copyTask).start();
//
//		Tab overview = new Tab("OverView");
//		///////////////////////////////////////////////////////////////////////////////
//		VBox label_io_ready_queue = new VBox();
//		label_io_ready_queue.setSpacing(10);
//		label_io_ready_queue.setAlignment(Pos.CENTER);
//		Label label1 = new Label("IO QUEUE");
//		label1.setTextFill(Color.BLUE);
//		label1.setFont(Font.font("", 15));
//		Label label_ready_queue = new Label("READY QUEUE");
//		label_ready_queue.setTextFill(Color.BLUE);
//		label_ready_queue.setFont(Font.font("", 15));
//		VBox global_vbox = new VBox();
//		HBox hbox = new HBox();
//		VBox label_vbox = new VBox();
//		VBox label_progress = new VBox();
//		label_progress.setSpacing(10);
//		label_progress.setAlignment(Pos.CENTER);
//		Label label11 = new Label("DCJsimulOS");
//		label11.setTextFill(Color.BLUE);
//		label11.setFont(Font.font("Cambria", 32));
//		label_vbox.setPadding(new Insets(3, 5, 0, 5));
//		label_vbox.setSpacing(5);
//		TextArea label_bottom = new TextArea();
//		label_bottom.setText("loading.............................\n..........................\n..........................\n............................\n.............................\n......................................\n..............................................................\n.......................................................................................................\n................................................................\n............................................................");
//		label_bottom.setPadding(new Insets(2, 2, 2, 2));
//		VBox.setMargin(label_bottom, new Insets(0, 0, 5, 0));
//		TableView<Processus> table = tabview();
//		hbox.setAlignment(Pos.CENTER);
//		HBox.setMargin(table, new Insets(0, 0, 0, 150));
//		HBox.setMargin(label_progress , new Insets(90, 0, 0, 170));
//		label_progress.getChildren().addAll(label11, progressBar, progressIndicator);
//		hbox.getChildren().addAll(label_progress, table);
//		hbox.setPadding(new Insets(7, 5, 5, 5));
//		hbox.setSpacing(5);
//		label_vbox.getChildren().add(label_bottom);
//		global_vbox.getChildren().addAll(hbox, label_vbox);
//		overview.setContent(global_vbox);
//		overview.setClosable(false);
//		return overview;			
//
//	}

	public static Tab OverView_Init(){
		Tab overview = new Tab("OverView");
		
		final Path path = new Path();
	      path.getElements().add(new MoveTo(90,90));
	      path.getElements().add(new CubicCurveTo(50, 100, 800, 100, 100, 200));
	      path.setOpacity(0.0);
	      BorderPane root = new BorderPane();

	      root.getChildren().add(path);
	      final Reflection reflection = new Reflection();
	      reflection.setFraction(1.0);
	      final Shape shape = TextBuilder.create()
	                        .text("DCJSimulos").x(20).y(20)
	                        .font(Font.font(java.awt.Font.SANS_SERIF, 25))
	                        .effect(reflection)
	                        .build();      
	      
	      root.getChildren().add(shape);

	      
	      final PathTransition pathTransition = new PathTransition();
	      pathTransition.setDuration(Duration.seconds(8.0));
	      pathTransition.setDelay(Duration.seconds(.5));
	      pathTransition.setPath(path);
	      pathTransition.setNode(shape);
	      pathTransition.setOrientation(OrientationType.NONE);
	      pathTransition.setCycleCount(Timeline.INDEFINITE);
	      pathTransition.setAutoReverse(true);

	      
	      
	      final ParallelTransition parallelTransition =
	         new ParallelTransition(pathTransition);
	      parallelTransition.play(); 
		
		
		
		///////////////////////////////////////////////////////////////////////////////
		VBox global_vbox = new VBox();
		HBox hbox = new HBox();
		VBox label_vbox = new VBox();
		label_vbox.setPadding(new Insets(3, 5, 0, 5));
		label_vbox.setSpacing(5);
		TextArea label_bottom = new TextArea();
		VBox.setMargin(label_bottom, new Insets(0, 0, 5, 0));
		label_bottom.setText("WELCOME TO DCJSimulOS\nGo to the menu bar file click on start to launch the simulation ");
		label_bottom.setPadding(new Insets(2, 2, 2, 2));
		TableView<Processus> table = tabview();
		hbox.setAlignment(Pos.CENTER_RIGHT);
		HBox.setMargin(table, new Insets(0, 0, 0, 50));
		hbox.getChildren().addAll(table);
		hbox.setPadding(new Insets(7, 5, 5, 5));
		hbox.setSpacing(5);
		label_vbox.getChildren().add(label_bottom);
		global_vbox.getChildren().addAll(root, hbox, label_vbox);
		overview.setContent(global_vbox);
		overview.setClosable(false);
		return overview;

	}
	
	public static Tab info_Process(){
		Tab overview = new Tab("OverView");
		///////////////////////////////////////////////////////////////////////////////
		VBox global_vbox = new VBox();
		HBox hbox = new HBox();
		VBox label_vbox = new VBox();
		VBox label_io_ready_queue = new VBox();
		label_io_ready_queue.setSpacing(10);
		label_io_ready_queue.setAlignment(Pos.CENTER);
		Label label = new Label("IO QUEUE");
		label.setTextFill(Color.BLUE);
		label.setFont(Font.font("", 15));
		Label label_ready_queue = new Label("READY QUEUE");
		label_ready_queue.setTextFill(Color.BLUE);
		label_ready_queue.setFont(Font.font("", 15));
		label_vbox.setPadding(new Insets(3, 5, 0, 5));
		label_vbox.setSpacing(5);
		//////////////////////////////////////////////////////////////////////////////////////////////////
		final TextArea textArea = TextAreaBuilder.create()
	               .prefWidth(400)
	               .wrapText(true)
	               .build();
	        
	       ScrollPane scrollPane = new ScrollPane();
	       scrollPane.setContent(textArea);
	       scrollPane.setFitToWidth(true);
	       scrollPane.setPrefWidth(400);
	       scrollPane.setPrefHeight(180);

	       VBox vBox = VBoxBuilder.create()
	               .children(scrollPane)
	               .build();
	       ///////////////////////////////////////////////////////////////////////////////////////////////////
		TextArea label_bottom = new TextArea();
		VBox.setMargin(label_bottom, new Insets(0, 0, 5, 0));
		label_bottom.setText("pmpm");
		label_bottom.setPadding(new Insets(2, 2, 2, 2));
		TableView<Processus> table = tabview();
		hbox.setAlignment(Pos.CENTER_RIGHT);
		HBox.setMargin(table, new Insets(0, 0, 0, 50));
		HBox.setMargin(label_io_ready_queue, new Insets(60, 0, 60, 5));
		label_io_ready_queue.getChildren().addAll(label, Io_Queue(),label_ready_queue, Ready_Queue());
		hbox.getChildren().addAll(label_io_ready_queue, table);
		hbox.setPadding(new Insets(7, 5, 5, 5));
		hbox.setSpacing(5);
		label_vbox.getChildren().add(vBox);
		global_vbox.getChildren().addAll(hbox, label_vbox);
		overview.setContent(global_vbox);
		overview.setClosable(false);
		return overview;

	}
	

	public static Tab tabmemory() {
		final Tab tab_memoireRam = new Tab("Memory");
		tab_memoireRam.setClosable(false);
		//on cree un piechart memoire q'on ajoute a la tab memoire
		tab_memoireRam.setContent(pie_chart());
		return tab_memoireRam;
	}

	public static ListView<Processus> Io_Queue() {
		ListView<Processus> io_queue = new ListView<Processus>();
		io_queue.setFocusTraversable(false);
		io_queue.setMaxHeight(50);
		io_queue.setMinWidth(400);
		io_queue.setDisable(false);
		io_queue.setOrientation(Orientation.HORIZONTAL);
		
		return io_queue;
	}

	public static ListView<Processus> Ready_Queue() {
		ListView<Processus> ready_queue = new ListView<Processus>();
		ready_queue.setFocusTraversable(false);
		ready_queue.setMaxHeight(50);
		ready_queue.setMinWidth(400);
		ready_queue.setDisable(false);
		ready_queue.setOrientation(Orientation.HORIZONTAL);
		return ready_queue;
	}
	
	
	public static Text text() {
		  final String content = "Lorem ipsum";
		    final Text text = new Text(10, 20, "");
		    
		    final Animation animation = new Transition() {
		        {
		            setCycleDuration(Duration.millis(2000));
		        }
		    
		        protected void interpolate(double frac) {
		            final int length = content.length();
		            final int n = Math.round(length * (float) frac);
		            text.setText(content.substring(0, n));
		        }
		    
		    };
		    
		    animation.play();
		return text;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}

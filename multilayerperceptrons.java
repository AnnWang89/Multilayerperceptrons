package nn.hw2;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import nn.hw2.Controller.MainController;


public class multilayerperceptrons extends Application {
	
	public static Stage mainStage;
	public static Scene mainScene;
	public static Slider _learning_rate;
	@Override
	
	public void start(Stage primaryStage) throws IOException{
		mainStage = primaryStage;
	    URL fxmlURL = this.getClass().getResource("views/main.fxml");
	    FXMLLoader loader = new FXMLLoader(fxmlURL);
	    Parent main = loader.load();    
	    mainScene = new Scene(main);
	    mainStage.setTitle("Multilayer Perceptrons");//title name
	    mainStage.setScene(mainScene);//set scene to mainScene
	   
	    //learning rate
	    /*
	    _learning_rate = new Slider(0,1,0.8);
	    _learning_rate.setShowTickLabels(true);
	    _learning_rate.setShowTickMarks(true);
	    _learning_rate.setBlockIncrement(0.01);
	    _learning_rate.setLayoutX(300);
	    _learning_rate.setLayoutY(30);
	    */
	    mainStage.show();//show the Stage
	    
	}

	
	public static void main(String[] args) throws Exception{			
	  		launch(args);
	}
	

	
	
}

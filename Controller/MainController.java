package nn.hw2.Controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nn.hw2.multilayerperceptrons;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;



public class MainController  implements Initializable{
	
	
	@FXML
	Pane _main_pane;
	@FXML
	CheckBox _w_checkbox;
	@FXML
	Line _line1;
	@FXML
	Line _line2;
	@FXML
	Line _line3;
	@FXML
	Line _line4;
	@FXML
	Line _line5;
	@FXML
	Line _line6;
	@FXML
	Text _synaptic_weights;
	@FXML
	Text _correct_rate_train;
	@FXML
	Text _correct_rate_test;
	@FXML
	Text _RMSE;
	@FXML
	Text _choose_file;
	@FXML
	Slider _learning_rate;
	@FXML
	Slider _learning_times;
	@FXML
	ComboBox choosefile;
	@FXML
	TextField _Hidden_input;
	@FXML
	TextField _Neurons_input;
	@FXML
	Text _dimension;
	@FXML
	TextArea _weight_long;
	public void initialize(URL arg0, ResourceBundle arg1) {
		choosefile.setValue("perceptron1");
		choosefile.setItems(choose_file_list);
	}

	public void onSliderChanged_learning_rate() throws Exception {//set learning rate
	    double sliderValue_learning_rate =  _learning_rate.getValue();
	    Learningrate = sliderValue_learning_rate;
	    System.out.println(sliderValue_learning_rate + " ");
	    Show_point();
	}
	public void onSliderChanged_learning_times() throws Exception {//set learning times
		
	    int sliderValue_learning_times =  (int) _learning_times.getValue();
	    learning_time = sliderValue_learning_times;
	    System.out.println(sliderValue_learning_times + " ");
	    Show_point();
	}
	
	//var
	public static int MAX_TERM=50000;
	public static double [][]y;
	public static int[] outputdesire;
	public static int outputdesire_kind=2;
	public static double[][] data_x;
	public static int[] desire_output=new int[MAX_TERM];//0 or 1
	public static double[] desire_output2=new double[MAX_TERM];//0 or 1
	public static int n=0;//the number of data
	public static int n_test;
	public static int n_train;
	public static double Learningrate=0.3 ;
	public static double [][]W_now;
	public static double [][]W_new;
	public static double output;
	public static int learning_time=800;
	public static double correct_rate_train=0;
	public static double correct_rate_test=0;
	public static Scanner input;
	public static double X_Y_max=2;
	public static double slope=0;
	public static int choose_file=0;
	public static int []R_list;
	public static boolean []R_list_find;
	public static int hidden_layer=3;
	public static int Neurons=5;
	public static int Neu_numbers=0;
	public static int Dimension=2;
	public static double rmse=2;
	public static String W_outputall="";
	ObservableList<String> choose_file_list =FXCollections.observableArrayList("perceptron1","perceptron2","2Ccircle1","2Circle1","2Circle2","2CloseS","2CloseS2","2CloseS3","2cring","2CS","2Hcircle1","2ring","xor","5CloseS1","123","perceptron3","perceptron4","C3D","IRIS","4satellite-6","8OX","C10D","Number","wine");
	//read File
	public static File _perceptron1 = new File("DataSet/perceptron1.txt");//2D
	public static File _perceptron2 = new File("DataSet/perceptron2.txt");//2D		
	public static File _2Ccircle1 = new File("DataSet/2Ccircle1.txt");//2D		
	public static File _2Circle1 = new File("DataSet/2Circle1.txt");//2D		
	public static File _2Circle2 = new File("DataSet/2Circle2.txt");//2D		
	public static File _2CloseS = new File("DataSet/2CloseS.txt");//2D		
	public static File _2CloseS2 = new File("DataSet/2CloseS2.txt");//2D
	public static File _2CloseS3 = new File("DataSet/2CloseS3.txt");//2D
	public static File _2cring = new File("DataSet/2cring.txt");//2D
	public static File _2CS = new File("DataSet/2CS.txt");//2D
	public static File _2Hcircle1 = new File("DataSet/2Hcircle1.txt");//2D
	public static File _2ring = new File("DataSet/2ring.txt");//2D
	public static File _xor = new File("DataSet/xor.txt");//2D
	public static File _5CloseS1 = new File("DataSet/5CloseS1.txt");//2D
	public static File _123 = new File("DataSet/123.txt");//2D
	
	
	public static File _perceptron3 = new File("DataSet/perceptron3.txt");//3D
	public static File _perceptron4 = new File("DataSet/perceptron4.txt");//3D
	public static File _C3D = new File("DataSet/C3D.TXT");//3D
	
	public static File _IRIS = new File("DataSet/IRIS.TXT");//4D
	public static File _4satellite_6 = new File("DataSet/4satellite-6.txt");//4D
	public static File _8OX = new File("DataSet/8OX.TXT");//8D
	public static File _C10D = new File("DataSet/C10D.TXT");//10D
	public static File _Number = new File("DataSet/Number.txt");//25D
	public static File _wine = new File("DataSet/wine.txt");//13D
	
	public void _Start_calculate(ActionEvent e) throws Exception {//set hidden_layer and Neurons
	    hidden_layer=Integer.parseInt(_Hidden_input.getText());
	    if(hidden_layer<1)
	    {
	    	Neurons=1;
	    }
	    Neurons=Integer.parseInt( _Neurons_input.getText());
	    if(Neurons<1)
	    {
	    	Neurons=1;
	    }
	    //System.out.println("hidden_layer: " +hidden_layer);
	    //System.out.println("Neurons: " +Neurons);
	    Show_point();
	    // _choose_file.setText(choose_file_list.get(choose_file_list.indexOf(choosefile.getValue())));
	}
	public void File_Choice(ActionEvent e) throws Exception  {
		choose_file=choose_file_list.indexOf(choosefile.getValue());
		hidden_layer=Integer.parseInt(_Hidden_input.getText());
	    if(hidden_layer<1)
	    {
	    	Neurons=1;
	    }
	    Neurons=Integer.parseInt( _Neurons_input.getText());
	    if(Neurons<1)
	    {
	    	Neurons=1;
	    }
		Show_point();
		//_choose_file.setText(choose_file_list.get(choose_file_list.indexOf(choosefile.getValue())));
	}
	public void Show_point() throws Exception  {	
		calculate();
		_choose_file.setText(choose_file_list.get(choose_file_list.indexOf(choosefile.getValue())));
		_dimension.setText(Dimension + " Dimension");
		_weight_long.setText(W_outputall);
		
		_correct_rate_train.setText("Correct rate train: \n" + correct_rate_train*100 +"%");
		_correct_rate_test.setText("Correct rate test: \n" + correct_rate_test*100 +"%");
		_RMSE.setText("RMSE: \n"+rmse );
		
		//draw the x,y Axis
		Rectangle Axis_board =new Rectangle(275,75,650,650);
		Axis_board.setStroke(Color.BLACK);
		Axis_board.setFill(Color.MINTCREAM);
		_main_pane.getChildren().add(Axis_board);
		
		Line lineX = new Line(300,400,900,400);
		lineX.setStrokeWidth(1);
		lineX.setStroke(Color.BLACK);
		
		Line lineY = new Line(600,100,600,700);
		lineY.setStrokeWidth(1);
		lineY.setStroke(Color.BLACK);
		
		Line lineX_up = new Line(900,400,880,380);
		lineX_up.setStrokeWidth(1);
		lineX_up.setStroke(Color.BLACK);

		Line lineX_down = new Line(900,400,880,420);
		lineX_down.setStrokeWidth(1);
		lineX_down.setStroke(Color.BLACK);
		
		Line lineY_left = new Line(600,100,620,120);
		lineY_left.setStrokeWidth(1);
		lineY_left.setStroke(Color.BLACK);
		
		Line lineY_right = new Line(600,100,580,120);
		lineY_right.setStrokeWidth(1);
		lineY_right.setStroke(Color.BLACK);
		
		//draw the points on the 2D
		
		_main_pane.getChildren().addAll(lineX,lineY,lineX_up,lineX_down,lineY_left,lineY_right);
		for(int i=0;i<n;i++) {
			Circle point = new Circle();
			point.setCenterX(600+data_x[R_list[i]][1]*250/X_Y_max);
			point.setCenterY(400-data_x[R_list[i]][2]*250/X_Y_max);
			
			if(n<=50)//the size of the point
			{
				point.setRadius(5);
			}else if(n<=500)
			{
				point.setRadius(3);
			}else if(n<=2000)
			{
				point.setRadius(2.75);
			}else 
			{
				point.setRadius(2.5);
			}
			//stroke
			if(desire_output2[R_list[i]]*(1/(double)outputdesire_kind)<= y[R_list[i]][Neu_numbers-1]&&(desire_output2[R_list[i]]+1)*(1/(double)outputdesire_kind)> y[R_list[i]][Neu_numbers-1])//correct
			{
				point.setStroke(Color.SLATEBLUE);
			}else//wrong
			{
				point.setStroke(Color.RED);
			}
			if(i>=n_train)//test -> dark color
			{
				if(desire_output2[R_list[i]]==0)
				{
					point.setFill(Color.web("#0879c9"));//blue
				}else if(desire_output2[R_list[i]]==1)
				{
					point.setFill(Color.web("#00cc6d"));//green
				}else if(desire_output2[R_list[i]]==2)
				{
					point.setFill(Color.web("#ffae00"));//orange
				}else if(desire_output2[R_list[i]]==3)
				{
					point.setFill(Color.web("#8106da"));//purple
				}else if(desire_output2[R_list[i]]==4)
				{
					point.setFill(Color.web("#ec5e4e"));//red
				}else  
				{
					point.setFill(Color.web("#0076a3"));
				}
			}else//train -> light color
			{
				if(desire_output2[R_list[i]]==0)
				{
					point.setFill(Color.web("#88cfff"));
				}else if(desire_output2[R_list[i]]==1)
				{
					point.setFill(Color.web("#acfcd7"));
				}else if(desire_output2[R_list[i]]==2)
				{
					point.setFill(Color.web("#ffeac0"));
				}else if(desire_output2[R_list[i]]==3)
				{
					point.setFill(Color.web("#e3c0ff"));
				}else if(desire_output2[R_list[i]]==4)
				{
					point.setFill(Color.web("#fcc1ba"));
				}else 
				{
					point.setFill(Color.web("#aee6fa"));
				}
			}
			
			_main_pane.getChildren().add(point);
		}
		//unit
		Line X_unit = new Line(600+250/X_Y_max,392,600+250/X_Y_max,408);
		X_unit.setStrokeWidth(1);
		X_unit.setStroke(Color.BLACK);
		
		Line Y_unit = new Line(592,400-250/X_Y_max,608,400-250/X_Y_max);
		Y_unit.setStrokeWidth(1);
		Y_unit.setStroke(Color.BLACK);
		
		Text X_1 = new Text(597+250/X_Y_max,420,"1");
		Text Y_1 = new Text(580,403-250/X_Y_max,"1");
		
		_main_pane.getChildren().addAll(X_unit,Y_unit,X_1,Y_1);
		
	}

	public static void calculate() throws Exception{
		
		
		switch(choose_file)//choose file and set dimension and the category it has
		{
		case 0:
			input=new Scanner(_perceptron1);
			Dimension=2;
			outputdesire_kind=2;
			break;
		case 1:
			input=new Scanner(_perceptron2);
			Dimension=2;
			outputdesire_kind=2;
			break;
		case 2:
			input=new Scanner(_2Ccircle1);
			Dimension=2;
			outputdesire_kind=2;
			break;
		case 3:
			input=new Scanner(_2Circle1);
			Dimension=2;
			outputdesire_kind=2;
			break;
		case 4:
			input=new Scanner(_2Circle2);
			Dimension=2;
			outputdesire_kind=3;
			break;
		case 5:
			input=new Scanner(_2CloseS);
			Dimension=2;
			outputdesire_kind=2;
			break;
		case 6:
			input=new Scanner(_2CloseS2);
			Dimension=2;
			outputdesire_kind=2;
			break;
		case 7:
			input=new Scanner(_2CloseS3);
			Dimension=2;
			outputdesire_kind=2;
			break;
		case 8:
			input=new Scanner(_2cring);
			Dimension=2;
			outputdesire_kind=2;
			break;
		case 9:
			input=new Scanner(_2CS);
			Dimension=2;
			outputdesire_kind=2;
			break;
		case 10:
			input=new Scanner(_2Hcircle1);
			Dimension=2;
			outputdesire_kind=2;
			break;
		case 11:
			input=new Scanner(_2ring);
			Dimension=2;
			outputdesire_kind=2;
			break;
		case 12:
			input=new Scanner(_xor);
			Dimension=2;
			outputdesire_kind=2;
			break;
		case 13:
			input=new Scanner(_5CloseS1);
			Dimension=2;
			outputdesire_kind=2;
			break;
		case 14:
			input=new Scanner(_123);
			Dimension=2;
			outputdesire_kind=2;
			break;
		case 15:
			input=new Scanner(_perceptron3);
			Dimension=3;
			outputdesire_kind=2;
			break;
		case 16:
			input=new Scanner(_perceptron4);
			Dimension=2;
			outputdesire_kind=3;
			break;
		case 17:
			input=new Scanner(_C3D);
			Dimension=3;
			outputdesire_kind=4;
			break;
		case 18:
			input=new Scanner(_IRIS);
			Dimension=4;
			outputdesire_kind=3;
			System.out.println("IN 18");
			break;
		case 19:
			input=new Scanner(_4satellite_6);
			Dimension=4;
			outputdesire_kind=6;
			System.out.println("IN 19");
			break;
		case 20:
			input=new Scanner(_8OX);
			Dimension=8;
			outputdesire_kind=3;
			System.out.println("IN 20");
			break;
		case 21:
			input=new Scanner(_C10D);
			Dimension=10;
			outputdesire_kind=4;
			break;
		case 22:
			input=new Scanner(_Number);
			Dimension=25;
			outputdesire_kind=4;
			break;
		case 23:
			input=new Scanner(_wine);
			Dimension=13;
			outputdesire_kind=3;
			break;
		}
		//initial
		data_x=new double[MAX_TERM][Dimension+1];
		outputdesire=new int[outputdesire_kind];
		for(int k=0;k<outputdesire_kind;k++)
		{
			outputdesire[k]=999;
		}
		n=0;
		X_Y_max=2;
		double tmp;
		double tmp2;

		
		//put input into array
		while (input.hasNext())
		{
			data_x[n][0] =-1; 
			for(int i=1;i<Dimension+1;i++)
			{
				tmp=input.nextDouble();
				data_x[n][i] =tmp; //x1
				//System.out.println("data_x[n][1]: "+ data_x[n][1]);
				if(data_x[n][i]>X_Y_max)
				{
					X_Y_max=data_x[n][i];
				}else if((-1)*data_x[n][i]>X_Y_max)
				{
					X_Y_max=(-1)*data_x[n][i];
				}
			}
			tmp2=input.nextDouble();
			desire_output[n]=(int)tmp2;//desire output
			for(int k=0;k<outputdesire_kind;k++)
			{
				if(outputdesire[k]==desire_output[n])
				{
					desire_output2[n]=k;
					break;
				}else if(outputdesire[k]==999)
				{
					desire_output2[n]=k;
					outputdesire[k]=desire_output[n];
					break;
				}
			}
		  	n++;
		 }
		 
		 //out put the file has read
		/*
		 for(int i=0;i<n;i++ )
		 {
			 System.out.println(i+"\t"+data_x[i][0]+"\t"+data_x[i][1]+"\t"+data_x[i][2]+"\t"+desire_output[i]);
		 }
		 */
		 Ran_list();//random all the data
		 /*
		 for(int i=0;i<n;i++ )
		 {
			 System.out.println("R_list "+i+" :"+ R_list[i]);
		 }
		 */
		 //choose the test and train 
		 if(n%3==0)
		 {
			 n_test=n/3;
		 }else if(n%3==1)
		 {
			 n_test=(n-1)/3;
		 }else
		 {
			 n_test=(n-2)/3;
		 }
		 n_train=n-n_test;
		 
		 //initial
		 Neu_numbers=hidden_layer*Neurons+1;
		 
		 W_now=new double[Neu_numbers][Math.max(Neurons+1, Dimension+1)];
		 y=new double[n][Neu_numbers];//output1 or output2
		 double v=0;
		 double Delta[]=new double[Neu_numbers];
		 initial_w(Neu_numbers);
		 
		 for(int times=0;times<learning_time;times++)
		 {
			 
			 //Feedforward
			 for(int j=0;j<Neu_numbers;j++)
			 {
				 v=0;
				 if(j<Neurons)//the first layer
				 {
					 
					 for(int k=0;k<Dimension+1;k++)//the first layer of neu
					 {
						 v=v+W_now[j][k]*data_x[R_list[times%n_train]][k];
						 //System.out.println("v=" + v);
					 }
				 }else//not the first layer
				 {
					 v=(-1)*W_now[j][0];
					 for(int k=0;k<Neurons;k++)
					 {
						 v=v+W_now[j][k+1]*y[R_list[times%n_train]][((j/Neurons)-1)*Neurons+k];//the layer calculate by the before output
						 //System.out.println("v=" + v);
					 }
					 
				 }
				 y[R_list[times%n_train]][j]=y_function(v);
				 //System.out.println("y["+(R_list[times%n_train])+"]["+j+"]=" + y[R_list[times%n_train]][j]);
			 }
			 
			 //Backward pass
			 for(int j=Neu_numbers-1;j>=0;j--) 
			 {
				 if(j==Neu_numbers-1)//out put layer
				 {//**//
					 Delta[j]=((desire_output2[R_list[times%n_train]]/((double)outputdesire_kind-1))-y[R_list[times%n_train]][j])*y[R_list[times%n_train]][j]*(1-y[R_list[times%n_train]][j]);
					 //System.out.println("Delta["+j+"]: " +Delta[j]);
				 }else if(j/Neurons==hidden_layer-1)//the last hidden layer
				 {
					 Delta[j]=y[R_list[times%n_train]][j]*(1-y[R_list[times%n_train]][j])*(Delta[Neu_numbers-1]*W_now[Neu_numbers-1][j%Neurons+1]);
					 //System.out.println("Delta["+j+"]: " +Delta[j]);
				 }else //other layer
				 {
					 double Delta_x_W=0;
					 int at_layer=j/Neurons;
					 for(int k=0;k<Neurons;k++)
					 {
						 Delta_x_W =Delta_x_W+Delta[(at_layer+1)*Neurons+k]*W_now[(at_layer+1)*Neurons+k][j%Neurons+1];
					 }
					 Delta[j]=y[R_list[times%n_train]][j]*(1-y[R_list[times%n_train]][j])*Delta_x_W;
					 //System.out.println("Delta["+j+"]: " +Delta[j]);
				 }
			 }
			 
			 //change W
			 for(int j=0;j<Neu_numbers;j++)
			 {
				 int at_layer=j/Neurons;
				 if(j/Neurons==0)
				 {
					 for(int i=0;i<Dimension+1;i++)
					 {
						 W_now[j][i]=W_now[j][i]+Learningrate*Delta[j]*data_x[R_list[times%n_train]][i];
					 }
				 }else
				 {
					 W_now[j][0]=W_now[j][0]+Learningrate*Delta[j]*(-1);
					 for(int i=0;i<Neurons;i++)
					 {
						 W_now[j][i+1]=W_now[j][i]+Learningrate*Delta[j]*y[R_list[times%n_train]][(at_layer-1)*Neurons+i];
						// System.out.println("W ["+j+"]["+(i+1)+"]="+ W_now[j][i+1]);
					 }
				 }
				 
			 }
		 }
		
		 
		//test 	
		for(int times=0;times<n_test;times++)
		 {
			 for(int j=0;j<Neu_numbers;j++)//first part
			 {
				 v=0;
				 if(j<Neurons)
				 {
					 for(int k=0;k<Dimension+1;k++)//the first layer of neu
					 {
						 v=v+W_now[j][k]*data_x[R_list[times+n_train]][k];
						 //System.out.println("v=" + v);
					 }
				 }else
				 {
					 v=(-1)*W_now[j][0];
					 for(int k=0;k<Neurons;k++)
					 {
						 v=v+W_now[j][k+1]*y[R_list[times+n_train]][((j/Neurons)-1)*Neurons+k];//the layer calculate by the before output
						 //System.out.println("v=" + v);
					 }
					 
				 }
				 y[R_list[times+n_train]][j]=y_function(v);
				 //System.out.println("y["+(R_list[times+n_train])+"]["+j+"]=" + y[R_list[times+n_train]][j]);
			 }
			 
		 }
		RMSE();
		correct_rate_train=correct(0,n_train)/(double)n_train;
		
		correct_rate_test=correct(n_train,n)/(double)n_test;
		//System.out.println("correct rate train: " + correct_rate_train);
		//System.out.println("correct rate test: " + correct_rate_test);
		
		W_outputall="";//show W
		for(int j=0;j<Neu_numbers;j++)
		 {
			W_outputall=W_outputall+"W"+j+" = (";
			 if(j<Neurons)
			 {
				 for(int k=0;k<Dimension+1;k++)//the first layer of neu
				 {
					 if(k != Dimension )
						 W_outputall=W_outputall+ W_now[j][k]+", ";
					 else
						 W_outputall=W_outputall+ W_now[j][k]+") ";
				 }
				 
			 }else
			 {
				 for(int k=0;k<Neurons+1;k++)
				 {
					 if(k != Neurons )
						 W_outputall=W_outputall+ W_now[j][k]+", ";
					 else
						 W_outputall=W_outputall+ W_now[j][k]+") ";
				 }
				 
			 }
			 W_outputall=W_outputall+"\n";		 
		 } 

	}
	public static void initial_w(int neu_num) {
		for(int i=0;i<neu_num;i++)
		{
			for(int j=0;j<Math.max(Neurons+1, Dimension+1);j++)
			{
				W_now[i][j]=Math.random();
				//System.out.println("W_now["+i+"]["+j+"]= " + W_now[i][j]);
				//W_best[i][j]=W_now[i][j];
			}
		}
	}
	public static double v_function(int neu_num,int time_n) {
		double v_out=W_now[neu_num][0]*(-1);
		int layer=neu_num/hidden_layer;
		int input_y_index=(layer-1)*2;
		for(int i=0;i<Neurons;i++)
		{
			v_out=v_out+(W_now[neu_num][i]*y[time_n][input_y_index+i]);
		}
		return v_out;
			
	}
	public static double y_function(double input_data) {
		double y_out= 1/(1+Math.exp(input_data*(-1)));
		return y_out;
			
	}
	public static double correct(int start,int end) {//calculate correct rate
		int correct=0;
		//System.out.println();
		//System.out.println();
		//System.out.println("W(in_correct): ( " + W_now[0]+", "+ W_now[1]+", "+ W_now[2]+")");
		for(int i=start;i<end;i++)
		{
			//System.out.println("desire_output["+R_list[i]+"]=" + desire_output[R_list[i]]);
			/*
			System.out.println();
			System.out.println("y["+R_list[i]+"]["+(Neu_numbers-1)+"]=" + y[R_list[i]][Neu_numbers-1]);
			System.out.println("desire_output["+R_list[i]+"]=" + desire_output2[R_list[i]]);
			System.out.println("from: "+desire_output2[R_list[i]]*(1/(double)outputdesire_kind));
			System.out.println("to: "+(desire_output2[R_list[i]]+1)*(1/(double)outputdesire_kind));
			*/
			if(desire_output2[R_list[i]]*(1/(double)outputdesire_kind)<= y[R_list[i]][Neu_numbers-1]&&(desire_output2[R_list[i]]+1)*(1/(double)outputdesire_kind)> y[R_list[i]][Neu_numbers-1])
			{
				correct++;
				//System.out.println("correct!" );
			}
		}
		//System.out.println("correct: " + correct);
		//System.out.println("n_train: " + n_train);
		return correct;
	}
	public static void Ran_list() {//calculate correct rate
		R_list = new int[n];
		R_list_find=new boolean[n];
		for(int i=0;i<n;i++)
		{
			R_list_find[i]=false;
		}

		int temp=0;
		for(int i=0;i<n;i++)
		{
			
			temp= temp+(int)(1000*Math.random());

			while(true)
			{
				temp=temp%n;
				if(!R_list_find[temp])
				{
					R_list[temp]=i;
					R_list_find[temp]=true;
					break;
				}else
				{
					temp++;
				}
			}
		}
	}
	public static void RMSE()
	{
		double sum_of_d_d=0;
		for(int i=0;i<n;i++)
		{
			sum_of_d_d = sum_of_d_d + (desire_output2[i]-output(y[i][Neu_numbers-1]))*(desire_output2[i]-output(y[i][Neu_numbers-1]));
		}
		sum_of_d_d=sum_of_d_d/(double)n;
		rmse=Math.sqrt(sum_of_d_d);
	}
	public static double output(double out)//the real output
	{
		for(int i=0;i<outputdesire_kind;i++)
		{
			if( i*(1/(double)outputdesire_kind)<= out && out<(i+1)*(1/(double)outputdesire_kind))
			{
				return i;
			}
		}
		return -1;
	}
}

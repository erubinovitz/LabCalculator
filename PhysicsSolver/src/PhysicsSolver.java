/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Evan
 */
import java.util.Scanner;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.Arrays;

import javax.swing.*;

import javafx.scene.control.TableColumn.CellEditEvent;
import javax.swing.JFrame;
import javafx.scene.control.Label;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;

import javafx.geometry.Pos;

import java.util.Optional;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Font;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.scene.text.Text;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import javafx.stage.Modality;
import javafx.stage.WindowEvent;
import java.sql.*;

import java.io.File;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import java.math.BigDecimal;
import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.setUserAgentStylesheet;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import java.text.DecimalFormat;
public class PhysicsSolver extends Application {
    private final ObservableList data =    FXCollections.observableArrayList();   

    /**
     * @param args the command line arguments
     */
 final HBox hb3= new HBox();   
    public void start (Stage stage){
         try { 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
         try{
         System.out.println(evaluate("5"));
         }
         catch (ArithmeticException e){
               final JPanel panel = new JPanel();
                    JOptionPane.showMessageDialog(panel, "Error with arithmetic of input values", "Error", JOptionPane.ERROR_MESSAGE);
         }
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        /*maximizes screen*/
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setWidth(bounds.getWidth()/4);
        stage.setHeight(bounds.getHeight()/4);
        stage.setMaximized(false);       
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() -stage.getWidth()) / 2 );
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        TextField numVariables = new TextField();
        TextField numTrials = new TextField();
        TextField numOutputs = new TextField();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(new Label("  Enter number of inputted\n  variables/constants:"), 0, 0);
        gridPane.add(numVariables, 1, 0);
        gridPane.add(new Label("  Enter number of trials:"), 0, 1);
        gridPane.add(numTrials,1,1);
        gridPane.add(new Label("  Enter number of outputted variables:"), 0, 2);
        gridPane.add(numOutputs,1,2);
        Button btCalculate = new Button("Next");
        gridPane.add(btCalculate, 1, 5);
        btCalculate.setOnAction(e ->
                inputValues(stage, Integer.parseInt(numVariables.getText()),
                        Integer.parseInt(numTrials.getText()), Integer.parseInt(numOutputs.getText())));
        /* Make VBox*/
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        /* Make some neat text */

        /*Create Start Button */

        /* Add start Button and resize it */
        /* Set title*/
        stage.setTitle("Physics");
        /* Set the scene (creates window)*/
  //    vbox.getChildren().addAll(hb3);
        Scene scene = new Scene(gridPane,100,50);
    //((Group) scene.getRoot()).getChildren().addAll(vbox);
        stage.setScene(scene);
        stage.show();
    }
    public void inputValues (Stage stage, int vals, int trials, int out){
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMaximized(true);       
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() -stage.getWidth()) / 2 );
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        Scene scene = new Scene(gridPane,300,250);
        stage.setScene(scene);
        stage.show();
        TextField[][] values = new TextField[trials][vals];
        for (int i=0; i<trials; i++){
            for (int j=0; j<vals; j++){
                values[i][j] = new TextField();
            }
        }
        
        TextField[] variables = new TextField[vals];
        for (int i=0; i<vals; i++)
            variables[i] = new TextField();
        for (int i =0; i<vals; i++){
            gridPane.add(new Label("  Enter symbol for\n  variable " + (i+1)+":"), i, 0);
            gridPane.add(variables[i], i, 2);
            for (int j=0; j<trials; j++){
                gridPane.add(new Label("  Enter value " + (j+1) +":"),i, 2*j+3);
                gridPane.add(values[j][i], i, 2*j+4);
            }
        }
        Button btCalculate = new Button("Next");
        gridPane.add(btCalculate, 0, trials*2+3);
        btCalculate.setOnAction(e ->
                outputValues(stage, values, variables, out));
    }
    public void outputValues(Stage stage, TextField[][] values, TextField[] variables, int out){
    /*    for (int i=0; i<values.length; i++){
            for (int j=0; j<values[0].length; j++){
                System.out.print(values[i][j].getText()+ " ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i=0; i<variables.length; i++){
            System.out.print(variables[i].getText()+ " ");
        } */
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMaximized(true);       
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() -stage.getWidth()) / 2 );
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        Scene scene = new Scene(gridPane,300,250);
        stage.setScene(scene);
        stage.show();
        TextField[] outputVars = new TextField[out];
        TextField[] outputEquations = new TextField[out];
        for (int i=0; i<out; i++){
            outputVars[i] = new TextField();
            outputEquations[i] = new TextField();
        }
        for (int i=0; i<out; i++){
           gridPane.add(new Label("  Enter symbol for\n  output variable " + (i+1)+":"), i, 0);
            gridPane.add(outputVars[i], i, 2);
        }
        for (int i=0; i<out; i++){
           gridPane.add(new Label("  Enter equation for\n  output variable " + (i+1)+":"), i, 3);
            gridPane.add(outputEquations[i], i, 5);
        }
        Button btCalculate = new Button("Next");
        gridPane.add(btCalculate, 0, out*2+3);
        btCalculate.setOnAction(e ->{
                try{
                table(stage, variables, values, outputVars,outputEquations);
                }
                catch (ArithmeticException ex){
                    final JPanel panel = new JPanel();
                    JOptionPane.showMessageDialog(panel, "Error with arithmetic of values", "Error", JOptionPane.ERROR_MESSAGE);
                }
        
        });
        
    }
    public void table (Stage stage, TextField[]inputVariables, TextField[][] inputVals, TextField[] outputVariables, TextField[] outputEquations) throws 
            ArithmeticException{
        System.out.println("Input values:");
        for (int i=0; i<inputVals.length; i++){
            for (int j=0; j<inputVals[0].length; j++){
                System.out.print(inputVals[i][j].getText()+ " ");
            }
            System.out.println();
        }
        System.out.println("Input variables:");
        for (int i=0; i<inputVariables.length; i++){
            System.out.print(inputVariables[i].getText()+ " ");
        }
        System.out.println("\nOutput variables:");
        for (int i=0; i<outputVariables.length; i++){
            System.out.print(outputVariables[i].getText()+ " ");
        }
        System.out.println("\nOutput equations:");
        for (int i=0; i<outputEquations.length; i++){
            System.out.print(outputEquations[i].getText()+ "\n");
        }
      /*  final TableView<Integer> table = new TableView<>();
        TableColumn<Integer, Number>[] columns = new TableColumn[outputVariables.length];
        
        for (int i=0; i<columns.length; i++){
            
            columns[i] = new TableColumn(outputVariables[i].getText());
            columns[i].setMinWidth(stage.getWidth()/columns.length);
            table.getColumns().add(columns[i]);
            
        }
        
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMaximized(true);       
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() -stage.getWidth()) / 2 );
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(table);
        Scene scene = new Scene(vbox,300,250);
        stage.setScene(scene);
   
        for (int i=0; i<outputVariables.length; i++){
            fin+=outputVariables[i].getText()+" ";
        }
        fin+="\n";
        stage.show();
        */
      String fin = "";
        double[][] outputs = new double[inputVals.length][inputVals[0].length];
        for (int i=0; i<inputVals.length; i++){
          //  table.getItems().add(i);
            for (int j=0; j<inputVals[0].length; j++){
                String s = outputEquations[i].getText();
                    for (int k=0; k<s.length(); k++){
                        char c = s.charAt(k);
                        char c2='.';
                        if (k<s.length()-1)
                            c2 = s.charAt(k+1);
                        if (!isNumeric(c)&&!isOperator(c)&&!isParenthesis(c)&&c!='.'){
                           try{
                           double num = getValue(c,inputVariables,inputVals[j]);
                           String s1 = s.substring(0,k);
                           String s2= Double.toString(num);
                           String s3 = s.substring(k+1,s.length());
                           s=s1+s2+s3;
                           //System.out.println("New string is " + s);
                           }
                           catch (ArrayIndexOutOfBoundsException e){
                               System.out.println("error with variables");
                           }
                        }
                        else if (isNumeric(c)&&(!isNumeric(c2)&&!isOperator(c2)&&!isParenthesis(c2)&&c2!='.')){
                           String s1 = s.substring(0,k);
                           String s2= c+"*";
                           String s3 = s.substring(k+1,s.length());
                           s=s1+s2+s3;
                        }
                    }
              //  System.out.println(s+"=");
              double s2=0;
              try{
                s2 =evaluate(s.trim());
              }
              catch (ArithmeticException e){
               throw new ArithmeticException();   
              }
                fin+=s2+" ";
                outputs[i][j]=s2;
                
               // System.out.println(s2);
               // System.out.println();
            }
            fin+="\n";
        }
        for (int i=0; i<outputVariables.length; i++){
            System.out.println("i is " + i + "inputvals.length is " + inputVals.length);
            for (int j=0; j<inputVals.length; j++){
                final int a =i;
                final int b=j;
                //System.out.println(j);
           /*     columns[j].setCellValueFactory(cellData -> {
                    System.out.print(outputs[a][b]+" ");
                    final double t = outputs[a][b];
                    return new  ReadOnlyDoubleWrapper(t);
                });
                */
                
            }
        }
        JOptionPane.showMessageDialog(null,fin);
    }
    public static void main(String[] args) {
     //   System.out.println(evaluate("2.0^9.81+.5(4.0)*3"));
        Application.launch(args);
  
    }

    public static double getValue (char c, TextField[] symbols, TextField[] values){
        for (int i=0; i<=values.length; i++){
            //System.out.println(arr[i]+ " " + c);
            if (symbols[i].getText().charAt(0)==c)
                return Double.parseDouble(values[i].getText());
            //else System.out.println(c + "/=" + values[i]);
        }
        return -1; // never used
    }
    
    public static boolean isNumeric(char c){
        return (c>='0' &&c<='9');
    }
    public static boolean isOperator(char c){
        return (c=='+'||c=='-'||c=='*'||c=='/'||c=='^');
    }
    public static int getPemdas(char operator){
        if (operator=='+')
            return 1;
        if (operator=='-')
            return 1;
        if (operator=='*')
            return 2;
        if (operator=='/')
            return 2;
        if (operator=='^')
            return 3;
        
        return -1;
    }
    public static boolean isParenthesis(char c){
        return (c==')'||c=='(');
    }
    public static double evaluate(double a, double b, char operator){
        DecimalFormat df = new DecimalFormat("#.###");
        if (operator=='+')
            return Double.parseDouble(df.format(a+b));
        if (operator=='-')
            return Double.parseDouble(df.format(a-b));
        if (operator=='*')
            return Double.parseDouble(df.format(a*b));
        if (operator=='/')
            return Double.parseDouble(df.format(a/b));
        if (operator=='^')
            return Double.parseDouble(df.format(Math.pow(a,b)));
        return -1;
    }
    public static boolean hasOperations(String s){
        return s.contains("+")||s.contains("-")||s.contains("*")||
                s.contains("/")||s.contains("^");
    }
    public static double evaluate(String s) throws ArithmeticException{
        
        /*Handles parenthesis multiplication, e.g. 4(5) = 20*/
        try{
            if (!s.matches(".*\\d+.*")){
                throw new ArithmeticException();
            }
           
        s=s.replaceAll("\\)\\(","\\)\\*\\(");
        /*Handles decimals that don't start with a zero, e.g. .5 is now 0.5*/
        s=s.replaceAll("\\+\\.","+0.");
        s=s.replaceAll("-\\.","-0.");
        s=s.replaceAll("\\*\\.","*0.");
        s=s.replaceAll("\\/\\.","/0.");
        s=s.replaceAll("\\^\\.","^0.");
        for (int i=0; i<s.length()-1; i++){
            if (isNumeric(s.charAt(i))&&s.charAt(i+1)=='('){
               String s1 = s.substring(0,i);
               String s2= s.charAt(i)+"*";
               String s3 = s.substring(i+1,s.length());
               s=s1+s2+s3;
            }
             if (s.charAt(i)==')'&&isNumeric(s.charAt(i+1))){
               String s1 = s.substring(0,i);
               String s2= s.charAt(i)+"*";
               String s3 = s.substring(i+1,s.length());
               s=s1+s2+s3;
            }
        }
        Stack stack = new Stack();     
        /* Stack for parenthesis*/
        int closeIndex=0;
        /* For loop to find parenthesises, and then recursively calculate
        them innermost to outermost*/
        for (int p=0; p<s.length(); p++){
           if (s.charAt(p)=='('){
               stack.push(p);          
           }
           else if (s.charAt(p)==')'){
               int openIndex=0;
               try{
                openIndex = stack.pop()+1; //index of first char after (
               }
               catch (Exception e){
               }
                closeIndex=p; //index of the )
                s=s.substring(0,openIndex-1)+Double.toString(evaluate(s.substring(openIndex,closeIndex)))+s.substring(closeIndex+1,s.length());  
                p=openIndex;
           }
        }
        /* If string is just a number with no operations, returns it. Used for 
        recursion with parenthesis and dealing with strings with just a number.
        */
        if (!hasOperations(s))
            return Double.parseDouble(s);        
        double answer=0; /* answer is value to be returned*/
        char operator=' '; /* Operator is operation being performed*/
        /*Pemdas represents the program going in pemdas order- evaluate exponents,
        then multiplication/division, then addition and subtraction. At 0, it will
        add all numbers together.*/
        for (int pemdas=3; pemdas>=0; pemdas--){
            if (pemdas==0){
                answer=0;
            }           
            /* i is index of s currently being examined*/
            int i=0;
            /*Term 1 and 2 are operands being used*/
            double term1=0;
            double term2=0;
            /*Index 1 and 2 represent the starting index of term1 and the
            ending index of term2.
            */
            int index1=0;
            int index2=0;
            /* While loop to go through each index of the string*/
            boolean sameOperation = false;
            while (i<s.length()){
                s=s.replaceAll("\\s",""); //trims all whitespace
                operator=' ';
                /*Dec counter is used to represent decimals being added, e.g. 
                if decCounter=4 and the index is 3, adds .0003 rather than 3
                */
                int decCounter=0;     
                /*Advances i if current symbol an operator or decimal point*/
                if(!sameOperation)
                    index1=i;
                while (isNumeric(s.charAt(i))){
                    /*While the index is a number or decimal sign, adds the appropriate value to term1*/
                    double nextDigit = Double.parseDouble(String.valueOf((s.charAt(i))));
                    /*If not a decimal, multiply term1 by 10 and add new value. 
                    Else increase decimal counter by 1 and add next digit divided y 10^decCounter
                    */
                    if (decCounter==0)
                        term1=(term1*10);
                    else 
                        nextDigit/=(Math.pow(10,decCounter++));
                    /* If number is negative, subtracts values instead*/
                    if (term1>=0)
                        term1=term1+nextDigit;
                    else
                        term2-=nextDigit;
                    i++;
                    /*Resolves double negatives*/
                    if (i>2&&s.charAt(i-2)=='-'&&s.charAt(i-1)=='-'){
                        term1*=-1;
                    }
                    /*Breaks if no other term*/
                    if (i>=s.length())
                        break;
                    /*Handles decimal signs*/
                    if (s.charAt(i)=='.'){
                        i++;
                        decCounter=1;
                    }
                }
               /*If pemdas is 0, adds term1 to previous answer*/
                if (i>=s.length()&&pemdas==0){
                    if (operator=='-')
                        term1*=-1;
                    answer += term1;
                }
                /*Breaks if no new terms*/
                if (i>=s.length())
                    break;
                decCounter=0;
                if (isOperator(s.charAt(i))){
                    operator=s.charAt(i);
                    i++;
                }
                int counter=0;
                boolean negative=false;
                if (s.charAt(i)=='-'){
                   if (s.charAt(i+1)!='-'){
                        i++;
                        negative = true;
                   }
                   else i+=2;
                }

                /* Identical to first nested while loop but for term 2 */
                while (isNumeric(s.charAt(i))){                    
                    double nextDigit = Double.parseDouble(String.valueOf((s.charAt(i))));
                    counter++;
                    if (decCounter==0)
                        term2=term2*10;
                    else 
                        nextDigit/=(Math.pow(10,decCounter++));
                    if (term2>=0)
                        term2+=nextDigit;
                    else
                        term2-=nextDigit;
                    if (i>2&&s.charAt(i-2)=='-'&&s.charAt(i-1)=='-'){
                        term2*=-1;
                    }
                    index2=i;
                    i++;                    
                    if (i==s.length())
                        break;
                    if (s.charAt(i)=='.'){
                        i++;
                        counter++;
                        decCounter++;
                    }                    
                }               
                if (negative)
                    term2*=-1;
                if (term1==0&&term2==0){
                    break;
                }
                /* Continues if pemdas value is correct. */
                boolean reset = true;
                if (getPemdas(operator)==pemdas){    
                    /*getPemdas returns 3 for ^, 2 for * or /, 1 for + or -*/
                    answer=evaluate(term1,term2,operator);
                    String s1= s.substring(0,index1);
                    String s2 = s.substring(index2+1,s.length());
                    String s3;
                    s3=s1+Double.toString(answer)+s2;                 
                    i=index1+Double.toString(answer).length();
                    s=s3;
                    if (i>=s.length())break;
                    if (getPemdas(s.charAt(i))==pemdas){
                        reset = false;
                        term1=answer;
                        sameOperation=true;
                    }
                    else sameOperation=false;                   
                }               
                else{ 
                    /*Sets method to check prev term 2 as new term 1*/
                    i-=counter;
                }               
                if (pemdas==0){
                   
                    answer+=evaluate(term1,term2,operator);
                    String s1= s.substring(0,index1);
                    String s2 = s.substring(index2+1,s.length());
                    String s3=s1+Double.toString(answer)+s2;                 
                    i=index1+Double.toString(answer).length();
                    s=s3;
                }
             
                if (reset)
                    term1=0;
                term2=0;               
            }
        }
      
        return answer;
        }
        catch (Exception e){
            throw new ArithmeticException();
        }
    }
}
class Stack{
    private Node first;
    private int top=0;
    public Stack(){
       
    }

    public void push(int item){       
        Node oldFirst = first;
        first = new Node();
        first.item=item;
        first.link=oldFirst;
        top++;
    }

    public int pop(){
        int item = first.item;
        first=first.link;

        return item;
    }
    public boolean isEmpty(){
        return top==0;
    }
}
class Node{
    int item;
    Node link;
    public Node(){
        link=null;
        item=0;
    }
    public Node( int index, Node link){
        this.item=index;
        this.link=link;
    }

    public void setLink(Node link){
        this.link=link;
    }
    public int getItem(){
        return item;
    }
    public void setItem(int item){
        this.item=item;
    }
    public Node getLink(){
        return link;
    }
}
class NotFoundException extends Exception{
            
        }
class ArithmeticException extends Exception{
    
}
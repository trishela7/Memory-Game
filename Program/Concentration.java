import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.geometry.Insets;
/*
Project1 CS56.
Design and implement your project base on following specification using FX or Swing. 
I  Preferred FX. Make sure you follow MVC design paradigm. 

Concentration, also known as Match Match, Match Up, Memory, Pelmanism, Shinkei-suijaku, 
Pexeso or simply Pairs, is a card game in which all of the cards are laid face 
down on a surface and two cards are flipped face up over each turn. ... 
Concentration can be played with any number of players or as solitaire. How do 
you play concentration with a deck of cards? To set up a game of concentration, 
first shuffle the cards well and then place each card face down in 4 rows of 13 
cards each. Each player takes a turn by turning two cards over. If the cards match, 
then the player picks up the cards and keeps them. If they don't match, the 
player turns the cards back over.
/**
 *
 * @author patriciashatz
 */
        
public class Concentration extends Application
{
    public static ArrayList<Integer> scores; 

    //
    @Override
    public void start(Stage primaryStage) 
    {
        
        String[] cardRank = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", 
            "Q", "K", "A"};
        String[] cardSuit = {"C", "D", "H", "S"};
        
        Alert a = new Alert(Alert.AlertType.INFORMATION, "WELCOME TO THE MEMORY "
                + "GAME!!");
        a.showAndWait();
        
        
        
        Stage stage = primaryStage;
        
         VBox vb = new VBox();
         Scene scene = new Scene(vb);
         vb.setPadding(new Insets(42, 42, 42, 42));
         GridPane pane = new GridPane();
         vb.getChildren().add(pane);
         vb.setStyle("-fx-background-color: #006400");
         vb.prefHeightProperty().bind(stage.heightProperty().multiply(1.05));         
         pane.setAlignment(Pos.CENTER);
     
       try
       {
         TextInputDialog dialog = new TextInputDialog();  
         dialog.setHeaderText("How Many People Are Playing?");
         dialog.setContentText("Number of Player(s):");
         Optional <String> result = dialog.showAndWait();
         {
         result.ifPresent( (String numPlayers) -> 
         {
            int np = Integer.parseInt(numPlayers);
            scores = new ArrayList<>(np);
            for(int i = 0; i < np; i++)
            {
                scores.add(0);
            
            }
            
          if(!result.isPresent())
          {    
              System.exit(0);
             
          }
              
    //Model
        ArrayList<Card> deck = new ArrayList<>();
            for (int i = 0; i < 4; i++) 
            { 
            //put the cards on the board
                for(int j = 0; j <13; j++ )
                {
                //
                deck.add( new Card(cardRank[j], cardSuit[i]) );
                
                }
                //end model
            }
        //part of Controller
        Collections.shuffle(deck);
        
         
         for(int i = 0; i<deck.size(); i++)
         {
             pane.add(deck.get(i), i%13, i/13);
  
         }
         
         stage.setTitle("WELCOME TO THE MEMORY GAME!!");
         stage.setScene(scene); 
         stage.sizeToScene(); 
         stage.show(); 
                                                    
      });                  
   
    }
       
    }catch(Exception e){}             

  }
        
    public static void main(String[] args)
    {
            launch(args);
            
    }        

}
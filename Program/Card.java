import java.util.Timer;
import java.util.TimerTask;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
/*
 * To change this license header, choose License åHeaders in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author patriciashatz
 */
public class Card extends ImageView 
{
     
   private static Image down = new Image("red_back.png");
   private static Card lastCardClicked = null;
   private Image up;
   private String suit;
   private String rank;
   private String color;
   private boolean valid = true;
   private int currentPlayer; //index into Concentration.scores array list
   public static int cardsRemaining = 52; 
   
   
   public Card(String cardRank, String cardSuit)
    {   
        super();
          
         up = new Image(cardRank + cardSuit + ".png");
         suit = cardSuit;
         rank = cardRank;
         valid = true;
        
         //set color
         if(cardSuit.equals("S") || cardSuit.equals("C"))
         {
             color = "BLACK";
             
         }
         
         else
             color = "RED";
           
         this.setImage(down);
         this.setFitWidth(92.0);
         this.setPreserveRatio(true);
         
         //this refers to the card itself
         Card outside = this;
         this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
         {
            @Override
            public void handle(MouseEvent e)
            {
                /*
                if it's not valid, it returns immediately, 
                which means it does nothing, doesn't respond to a click
                */
                
                        if(!outside.valid)
                {
                    return;
                    
                }
                
                //writing 'this' in here would refer to the event handler
                outside.setImage(up);
                
                if(lastCardClicked != null)
                {    
                    if(outside.rank.equals(lastCardClicked.rank) && 
                        (outside.color.equals(lastCardClicked.color)))
                    
                    {
                        System.out.println("You've found a match!!");
                        lastCardClicked.valid = false;  
                        outside.valid = false;
                        lastCardClicked.setImage(lastCardClicked.up);
                        outside.setImage(outside.up);
                        
                        //increment score
                        int currentScore = Concentration.scores.get(currentPlayer);
                        Concentration.scores.set(currentPlayer, currentScore+1);
                        cardsRemaining-= 2;
                        
                        if(cardsRemaining == 0)
                        {
                            int winner = 0;
                            for(int i = 0; i < Concentration.scores.size(); i++)
                            {   
                                //loop through all the players to see who has the highest score
                                if(Concentration.scores.get(i) > Concentration.scores.get(winner))
                                {
                                    winner = i;
                                    
                                }    
                                    
                            }    
                            
                            
                            Alert a = new Alert(Alert.AlertType.INFORMATION, "GAME OVER! "
                                    + "Player " + winner + " won! With score: " 
                                    + Concentration.scores.get(winner) 
                                    + ". All scores: " + Concentration.scores);
                           
                                a.showAndWait();
                                System.exit(0);    
                                
 
                        
                        }     
                            
                        
                    } 
                    else 
                    { //no match found, flip both over
                        Card currentLastClicked = lastCardClicked;
                        Card currentOutside = outside;
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() 
                        {
                            @Override
                            public void run() 
                            {

                               //just the "outside.valid" is part of the view 
                               //it's querying the model, asking if the card is valid
                               if(currentOutside.valid) 
                               {
                                   //this is the controller, controlling the card displayed 
                                   currentOutside.setImage(down);
                                   
                                   
                               }
                               if(currentLastClicked.valid)
                               {    
                                //tells the view that the card should be facing down   
                                currentLastClicked.setImage(down);

                               }     
                               


                            }
                        } , 3000);  

                    }
                    //save current lastcard, lastCardClicked is a static variable
                     lastCardClicked = null;
                    
                     currentPlayer++;
                     currentPlayer %= Concentration.scores.size();
                     System.out.println(Concentration.scores);
                
         
                     
                     
                } 
                else
                {
                    lastCardClicked = outside;
                }     
                
                
            } 
        });
         
        
    }





}
   

 
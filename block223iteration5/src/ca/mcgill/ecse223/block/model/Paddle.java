/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 98 "../../../../../Block223Persistence.ump"
// line 136 "../../../../../Block223 v3.ump"
public class Paddle implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int PADDLE_WIDTH = 5;
  public static final int VERTICAL_DISTANCE = 30;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Paddle Attributes
  private int maxPaddleLength;
  private int minPaddleLength;

  //Paddle Associations
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Paddle(int aMaxPaddleLength, int aMinPaddleLength, Game aGame)
  {
    maxPaddleLength = aMaxPaddleLength;
    minPaddleLength = aMinPaddleLength;
    if (aGame == null || aGame.getPaddle() != null)
    {
      throw new RuntimeException("Unable to create Paddle due to aGame");
    }
    game = aGame;
  }

  public Paddle(int aMaxPaddleLength, int aMinPaddleLength, String aNameForGame, int aNrBlocksPerLevelForGame, Admin aAdminForGame, Ball aBallForGame, Block223 aBlock223ForGame)
  {
    maxPaddleLength = aMaxPaddleLength;
    minPaddleLength = aMinPaddleLength;
    game = new Game(aNameForGame, aNrBlocksPerLevelForGame, aAdminForGame, aBallForGame, this, aBlock223ForGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMaxPaddleLength(int aMaxPaddleLength)
  {
    boolean wasSet = false;
    maxPaddleLength = aMaxPaddleLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinPaddleLength(int aMinPaddleLength)
  {
    boolean wasSet = false;
    minPaddleLength = aMinPaddleLength;
    wasSet = true;
    return wasSet;
  }

  public int getMaxPaddleLength()
  {
    return maxPaddleLength;
  }

  public int getMinPaddleLength()
  {
    return minPaddleLength;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }

  public void delete()
  {
    Game existingGame = game;
    game = null;
    if (existingGame != null)
    {
      existingGame.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "maxPaddleLength" + ":" + getMaxPaddleLength()+ "," +
            "minPaddleLength" + ":" + getMinPaddleLength()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 101 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 8062668971918939261L ;

  
}
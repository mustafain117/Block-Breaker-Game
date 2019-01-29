/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 39 "../../../../../umple.ump"
public class HallOfFame
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HallOfFame Attributes
  private String playerName;
  private int score;

  //HallOfFame Associations
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HallOfFame(String aPlayerName, int aScore, Game aGame)
  {
    playerName = aPlayerName;
    score = aScore;
    if (aGame == null || aGame.getHallOfFame() != null)
    {
      throw new RuntimeException("Unable to create HallOfFame due to aGame");
    }
    game = aGame;
  }

  public HallOfFame(String aPlayerName, int aScore, String aGameNameForGame, int aPlayAreaWidthForGame, int aPlayAreaHeightForGame, Admin aAdminForGame)
  {
    playerName = aPlayerName;
    score = aScore;
    game = new Game(aGameNameForGame, aPlayAreaWidthForGame, aPlayAreaHeightForGame, this, aAdminForGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPlayerName(String aPlayerName)
  {
    boolean wasSet = false;
    playerName = aPlayerName;
    wasSet = true;
    return wasSet;
  }

  public boolean setScore(int aScore)
  {
    boolean wasSet = false;
    score = aScore;
    wasSet = true;
    return wasSet;
  }

  public String getPlayerName()
  {
    return playerName;
  }

  public int getScore()
  {
    return score;
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
            "playerName" + ":" + getPlayerName()+ "," +
            "score" + ":" + getScore()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}
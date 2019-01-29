/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 30 "../../../../../umple.ump"
public class Game
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private String gameName;
  private int playAreaWidth;
  private int playAreaHeight;

  //Game Associations
  private List<Player> players;
  private HallOfFame hallOfFame;
  private Admin admin;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(String aGameName, int aPlayAreaWidth, int aPlayAreaHeight, HallOfFame aHallOfFame, Admin aAdmin)
  {
    gameName = aGameName;
    playAreaWidth = aPlayAreaWidth;
    playAreaHeight = aPlayAreaHeight;
    players = new ArrayList<Player>();
    if (aHallOfFame == null || aHallOfFame.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aHallOfFame");
    }
    hallOfFame = aHallOfFame;
    if (aAdmin == null || aAdmin.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aAdmin");
    }
    admin = aAdmin;
  }

  public Game(String aGameName, int aPlayAreaWidth, int aPlayAreaHeight, String aPlayerNameForHallOfFame, int aScoreForHallOfFame, User aUserForAdmin, Ball aBallForAdmin, Paddle aPaddleForAdmin)
  {
    gameName = aGameName;
    playAreaWidth = aPlayAreaWidth;
    playAreaHeight = aPlayAreaHeight;
    players = new ArrayList<Player>();
    hallOfFame = new HallOfFame(aPlayerNameForHallOfFame, aScoreForHallOfFame, this);
    admin = new Admin(aUserForAdmin, aBallForAdmin, aPaddleForAdmin, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGameName(String aGameName)
  {
    boolean wasSet = false;
    gameName = aGameName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPlayAreaWidth(int aPlayAreaWidth)
  {
    boolean wasSet = false;
    playAreaWidth = aPlayAreaWidth;
    wasSet = true;
    return wasSet;
  }

  public boolean setPlayAreaHeight(int aPlayAreaHeight)
  {
    boolean wasSet = false;
    playAreaHeight = aPlayAreaHeight;
    wasSet = true;
    return wasSet;
  }

  public String getGameName()
  {
    return gameName;
  }

  public int getPlayAreaWidth()
  {
    return playAreaWidth;
  }

  public int getPlayAreaHeight()
  {
    return playAreaHeight;
  }
  /* Code from template association_GetMany */
  public Player getPlayer(int index)
  {
    Player aPlayer = players.get(index);
    return aPlayer;
  }

  public List<Player> getPlayers()
  {
    List<Player> newPlayers = Collections.unmodifiableList(players);
    return newPlayers;
  }

  public int numberOfPlayers()
  {
    int number = players.size();
    return number;
  }

  public boolean hasPlayers()
  {
    boolean has = players.size() > 0;
    return has;
  }

  public int indexOfPlayer(Player aPlayer)
  {
    int index = players.indexOf(aPlayer);
    return index;
  }
  /* Code from template association_GetOne */
  public HallOfFame getHallOfFame()
  {
    return hallOfFame;
  }
  /* Code from template association_GetOne */
  public Admin getAdmin()
  {
    return admin;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlayers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Player addPlayer(User aUser)
  {
    return new Player(aUser, this);
  }

  public boolean addPlayer(Player aPlayer)
  {
    boolean wasAdded = false;
    if (players.contains(aPlayer)) { return false; }
    Game existingGame = aPlayer.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aPlayer.setGame(this);
    }
    else
    {
      players.add(aPlayer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlayer(Player aPlayer)
  {
    boolean wasRemoved = false;
    //Unable to remove aPlayer, as it must always have a game
    if (!this.equals(aPlayer.getGame()))
    {
      players.remove(aPlayer);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPlayerAt(Player aPlayer, int index)
  {  
    boolean wasAdded = false;
    if(addPlayer(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayerAt(Player aPlayer, int index)
  {
    boolean wasAdded = false;
    if(players.contains(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayerAt(aPlayer, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=players.size(); i > 0; i--)
    {
      Player aPlayer = players.get(i - 1);
      aPlayer.delete();
    }
    HallOfFame existingHallOfFame = hallOfFame;
    hallOfFame = null;
    if (existingHallOfFame != null)
    {
      existingHallOfFame.delete();
    }
    Admin existingAdmin = admin;
    admin = null;
    if (existingAdmin != null)
    {
      existingAdmin.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "gameName" + ":" + getGameName()+ "," +
            "playAreaWidth" + ":" + getPlayAreaWidth()+ "," +
            "playAreaHeight" + ":" + getPlayAreaHeight()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "hallOfFame = "+(getHallOfFame()!=null?Integer.toHexString(System.identityHashCode(getHallOfFame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "admin = "+(getAdmin()!=null?Integer.toHexString(System.identityHashCode(getAdmin())):"null");
  }
}
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 2 "StateMachine.ump"
public class GameSession
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GameSession State Machines
  public enum GameStatus { Idle, Playing, Paused, Done }
  private GameStatus gameStatus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GameSession()
  {
    setGameStatus(GameStatus.Idle);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getGameStatusFullName()
  {
    String answer = gameStatus.toString();
    return answer;
  }

  public GameStatus getGameStatus()
  {
    return gameStatus;
  }

  public boolean pausegame()
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case Playing:
        setGameStatus(GameStatus.Paused);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean resume()
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case Paused:
        setGameStatus(GameStatus.Playing);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setGameStatus(GameStatus aGameStatus)
  {
    gameStatus = aGameStatus;

    // entry actions and do activities
    switch(gameStatus)
    {
      case Done:
        // line 51 "StateMachine.ump"
        saveScoreAndDelete();
        break;
    }
  }

  public void delete()
  {}

  // line 56 "StateMachine.ump"
   private boolean hasEnoughBlocks(){
    return false;
  }

  // line 57 "StateMachine.ump"
   private void doDuplicateBlocks(){
    
  }

  // line 58 "StateMachine.ump"
   private void doFillRandomBlocks(){
    
  }

  // line 59 "StateMachine.ump"
   private boolean isballHitPaddle(){
    return false;
  }

  // line 60 "StateMachine.ump"
   private boolean isballHitWall(){
    return false;
  }

  // line 61 "StateMachine.ump"
   private boolean ballHitsBlock(){
    return false;
  }

  // line 62 "StateMachine.ump"
   private boolean ballIsOutOfBound(){
    return false;
  }

  // line 63 "StateMachine.ump"
   private boolean saveScoreAndDelete(){
    return false;
  }

  // line 64 "StateMachine.ump"
   private void doJumpToNextLevel(){
    
  }

  // line 65 "StateMachine.ump"
   private boolean isLastBlock(){
    return false;
  }

  // line 67 "StateMachine.ump"
   private boolean gameIsAlmostDone(){
    return false;
  }

  // line 68 "StateMachine.ump"
   private void doResetBallAndPaddle(){
    
  }

  // line 70 "StateMachine.ump"
   private boolean almostDead(){
    return false;
  }

  // line 71 "StateMachine.ump"
   private void doMoveBall(){
    
  }

  // line 72 "StateMachine.ump"
   private void doChangeDirection(){
    
  }

  // line 74 "StateMachine.ump"
   private boolean checkLineIntersect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4){
    return false;
  }

}
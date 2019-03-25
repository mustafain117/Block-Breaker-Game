 

 //  *ADD TO BLOCK223States.ump* 

 //hitLastBlockAndLastLevel
 private boolean hitLastBlockAndLastLevel(){
    
	   Game game = getGame();
	   
	   int nrLevels = game.numberOfLevels();
	   
	   setBounce(null);
	   
	   if (nrLevels == currentLevel)
	   {
		   int nrBlocks = game.numberOfBlocks();
		   
		   if(nrBlocks == 1)
		   {
		PlayedBlockAssignment block = getBlock(0);
			
	
			BouncePoint bp = calculateBouncePointBlock(block);
			
			setBounce(bp);  
		
			// bp null if no bounce points found

		return (bp!=null);
		   }
		  
		
	   }
	
   return false;
	   }

// hitLastBlock
	    private boolean hitLastBlock(){
    
	   int nrBlocks = numberOfBlocks();
	   setBounce(null);
	   if (nrBlocks == 1)
	   {
		   PlayedBlockAssignment block = getBlock(0);
		   
		   BouncePoint bp = calculateBouncePointBlock(block);
		   
		   setBounce(bp);
		   
		return (bp!=null);
	   }
	   return false;
   }
	   
	  
	//hitBlock  
	  
	    private boolean hitBlock(){
  
	   int nrBlocks = numberOfBlocks();
	   setBounce(null);
	   
	   for (int index = 0; index<= nrBlocks-1; index++)
	   {
		   PlayedBlockAssignment block = getBlock(index);
		   BouncePoint bp = calculateBouncePointBlock(block);
		
		   
		        BouncePoint bounce = getBounce();
		  
		  boolean closer = isCloser(bp, bounce);
		  
		  if (closer)
		  {
			setBounce(bp);
			
		  }
		   
	   }
	   return (getBounce()!= null);
	   
   }
	   

//doHitBlock
 
	     private void doHitBlock(){
   
	   int score = getScore();
	 BouncePoint bounce = getBounce();
	   
	    PlayedBlockAssignment pblock = bounce.getHitBlock();
	    Block block = pblock.getBlock();
	    
	    int points = block.getPoints();
	  setScore(score+points);
	  pblock.delete();
	  
	  
        bounceBall(); 
           
  }

//doHitBlockNextLevel
 private void doHitBlockNextLevel(){
	   doHitBlock();
	   int level = getCurrentLevel();
	   setCurrentLevel(level+1);
	   
	   setCurrentPaddleLength
	   (getGame().getPaddle().getMaxPaddleLength()-getGame().getPaddle().getMaxPaddleLength()
			   -(getGame().getPaddle().getMinPaddleLength())/(getGame().numberOfLevels()-1) *(getCurrentLevel()-1));
	   
	   setWaitTime( INITIAL_WAIT_TIME * getGame().getBall().getBallSpeedIncreaseFactor() *(getCurrentLevel()-1));
	   
  }
//isCloser
  private boolean isCloser(BouncePoint first, BouncePoint second)

{
	if (second==null)
	{
	return true;
	}
	if(first == null)
	{
		return false;
	}
	
	
double Dist_a =  Math.sqrt( ((first.getX() - getCurrentBallX())*(first.getX() - getCurrentBallX())) 
		+ ((first.getY() - getCurrentBallY())*(first.getY() - getCurrentBallY())));

		
double Dist_b =  Math.sqrt( ((second.getX() - getCurrentBallX())*(second.getX() - getCurrentBallX())) 
		+ ((second.getY() - getCurrentBallY())*(second.getY() - getCurrentBallY())));

if (Dist_a<Dist_b)
{
 return true;
}

	return false;		
	
}


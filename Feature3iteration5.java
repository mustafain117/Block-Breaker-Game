private boolean hitPaddle(){
    // TODO implement
	BouncePoint bp=calculateBouncePointPaddle();
	
	setBounce(bp);
    return (bp!=null);
  }
   
private BouncePoint calculateBouncePointPaddle()
   {
	    
	BouncePoint b = null;
	return b;   
   } 
   //Couldn;'t figure out how to calculate bouncepoint but set it to null

private boolean hitWall(){
	
	BouncePoint bp = calculateBouncePointWall();
	setBounce(bp); 
	   
	return (bp!=null);
  }
  
private BouncePoint calculateBouncePointWall(){
	    
	BouncePoint b = null;
	return b;  
		    
	  }
	 //Couldn;'t figure out how to calculate bouncepoint but set it to null  

private void bounceBall() {
	   
	   //FLIP_Y case
	   	BouncePoint.BounceDirection bDirection = this.bounce.getDirection();
	   	if(bDirection.equals(BouncePoint.BounceDirection.FLIP_Y)) {
	   		double in = bounce.getY() - currentBallY;
	   		double rem = ballDirectionY - in;
	   		
	   		if(rem == 0) {
	   			currentBallX = bounce.getX();
	   			currentBallY = bounce.getY();
	   		}
	   		
	   		double oldDirX = ballDirectionX;
	   		double oldDirY = ballDirectionY;
	   		ballDirectionX = oldDirX + (0.1 * oldDirY);
	   		ballDirectionY = (oldDirY * (-1));
	   		currentBallX = bounce.getX() + rem/oldDirX * ballDirectionX;
	   		currentBallY = bounce.getY() + rem/oldDirY * ballDirectionY;
	   	}
	   	//FLIP_X case
	   	if(bDirection.equals(BouncePoint.BounceDirection.FLIP_X)) {
	   		double in = bounce.getX() - currentBallX;
	   		double rem = ballDirectionX - in;
	   		if(rem == 0) {
	   			currentBallX = bounce.getX();
	   			currentBallY = bounce.getY();
	   		}
	   		double oldDirX = ballDirectionX;
	   		double oldDirY = ballDirectionY;
	   		ballDirectionX = (oldDirX * (-1));
	   		ballDirectionY = oldDirY + (0.1 * oldDirX);
	   		currentBallX = bounce.getX() + rem/oldDirX * ballDirectionX;
	   		currentBallY = bounce.getY() + rem/oldDirY * ballDirectionY;
	   	}
	   	//FLIP_BOTH case
	   	if(bDirection.equals(BouncePoint.BounceDirection.FLIP_BOTH)) {
	   		double inX = bounce.getX() - currentBallX;
	   		double remX = ballDirectionX - inX;
	   		double inY = bounce.getY() - currentBallY;
	   		double remY = ballDirectionY - inY;
	   		if(remX == 0 && remY == 0) {
	   			currentBallX = bounce.getX();
	   			currentBallY = bounce.getY();
	   		}
	   		double oldDirX = ballDirectionX;
	   		double oldDirY = ballDirectionY;
	   		ballDirectionX = (oldDirX * (-1));
	   		ballDirectionY = (oldDirY * (-1));
	   		currentBallX = bounce.getX() + remX/oldDirX * ballDirectionX;
	   		currentBallY = bounce.getY() + remY/oldDirY * ballDirectionY;
	   	}
	   }	  
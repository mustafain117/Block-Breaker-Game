  // *** QUERY ***


  //bounceBall
  
   private void bounceBall(){
	     double Y1 = bounce.getY()-currentBallY;
	     double Y2 = ballDirectionY-Y1;
	     double X1 = bounce.getX()-currentBallX;
	     double X2 = ballDirectionX-X1;
	     double newBallDirectionY;
	     double newBallDirectionX;
	     double newPositionY=0;
	     double newPositionX=0;
	     double xSign=Math.signum(ballDirectionX);
	     double ySign = Math.signum(ballDirectionY);
	    
	   if(xSign == 0) {
	       xSign=1;
	     }
	     
	    
	     if(ySign == 0) {
	       ySign=1;
	     }
	
	     else if (bounce.getDirection()== BounceDirection.FLIP_Y) {
	       if (Y2 == 0) {
	         setCurrentBallX(bounce.getX());
	         setCurrentBallY(bounce.getY());
	         return;
	       }
	       newBallDirectionY= ballDirectionY* -1;
	       newBallDirectionX= ballDirectionX + (xSign *0.1 * Math.abs(ballDirectionY));
	       newPositionY= bounce.getY()+Y2/ballDirectionY * newBallDirectionY;
	       newPositionX= bounce.getX()+Y2/ballDirectionY * newBallDirectionX;
	       setBallDirectionY(newBallDirectionY);
	       setBallDirectionX(newBallDirectionX);
	     }
	     
	     else  if (bounce.getDirection()== BounceDirection.FLIP_X) {
	       if (Y2 == 0) {
	         setCurrentBallX(bounce.getX());
	         setCurrentBallY(bounce.getY());
	         return;
	       }
	       newBallDirectionX= ballDirectionX* -1;
	       newBallDirectionY= ballDirectionY + (ySign *0.1 * Math.abs(ballDirectionX));
	       newPositionY= bounce.getY()+X2/ballDirectionX * newBallDirectionY;
	       newPositionX= bounce.getX()+X2/ballDirectionX * newBallDirectionX;
	       setBallDirectionY(newBallDirectionY);
	       setBallDirectionX(newBallDirectionX);
	     }
	     else  if (bounce.getDirection()== BounceDirection.FLIP_BOTH) {
	       if ((Y2 == 0)|| (X2 == 0)) {
	         setCurrentBallX(bounce.getX());
	         setCurrentBallY(bounce.getY());
	         return;
	       }
	       newBallDirectionY= ballDirectionY* -1;
	       newBallDirectionX= ballDirectionX*-1;
	       newPositionY= bounce.getY()+Y2/ballDirectionY * newBallDirectionY;
	       newPositionX= bounce.getX()+X2/ballDirectionX * newBallDirectionX;
	       setBallDirectionY(newBallDirectionY);
	       setBallDirectionX(newBallDirectionX);
	     }
	     
	     
	     setCurrentBallX(newPositionX);
	     setCurrentBallY(newPositionY);
	     
	     }


// calculateIntersectPoint

   private Point2D calculateIntersectPoint(Line2D line1, Line2D line2){
	    if (line1.getX2() == line1.getX1()) {	
	    		return (new Point2D.Double(line1.getX1(), line2.getY1()));
	    	}
	    
		  
		   if (line2.getX2() == line2.getX1()) {  
			   return (new Point2D.Double(line2.getX1(), line1.getY1()));
		   }
		   
		   
		      double a1 = ((line1.getY2() - line1.getY1()) / (line1.getX2() - line1.getX1()));
			  double a2 = ((line2.getY2() - line2.getY1()) / (line2.getX2() - line2.getX1()));
			  double b1 = (line1.getY1() - a1 * line1.getX1());
			  double b2 = (line2.getY1() - a2 * line2.getX1());
			  double c = (b2 - b1) / (a1 - a2);
			  double d = a1 * c + b1;
			  
			  return (new Point2D.Double(c, d));
	  }

 //calculateBouncePointBlock

    @SuppressWarnings("deprecation")
private BouncePoint calculateBouncePointBlock(PlayedBlockAssignment block){
	           double BallX = getCurrentBallX();
			   double BallY = getCurrentBallY();
			   double nextBallX = (BallX + getBallDirectionX());
			   double nextBallY = (BallY + getBallDirectionY());
			   
			   Line2D ballTrajectory = new Line2D.Double(BallX, BallY, nextBallX, nextBallY);
			   
				int ballRadius = Ball.BALL_DIAMETER / 2;
				
				Rectangle2D boxA = new Rectangle2D.Double(block.getX(), (block.getY() - ballRadius + 0.01), Block.SIZE, ballRadius);
				Rectangle2D boxB = new Rectangle2D.Double((block.getX() - ballRadius + 0.01), block.getY(), ballRadius, Block.SIZE);
				Rectangle2D boxC = new Rectangle2D.Double((block.getX() + Block.SIZE - 0.01), block.getY(), ballRadius, Block.SIZE);
				Rectangle2D boxD = new Rectangle2D.Double(block.getX(), (block.getY() + Block.SIZE - 0.01), Block.SIZE, ballRadius);
				Rectangle2D boxE = new Rectangle2D.Double((block.getX() - ballRadius), (block.getY() - ballRadius), ballRadius, ballRadius);
				Rectangle2D boxF = new Rectangle2D.Double((block.getX() + Block.SIZE), (block.getY() - ballRadius), ballRadius, ballRadius);
				Rectangle2D boxG = new Rectangle2D.Double((block.getX() - ballRadius), (block.getY() + Block.SIZE), ballRadius, ballRadius);
				Rectangle2D boxH = new Rectangle2D.Double((block.getX() + Block.SIZE), (block.getY() + Block.SIZE), ballRadius, ballRadius);

				BouncePoint bp = null;
// BOX A:
				
				if (boxA.intersectsLine(ballTrajectory)) {
					Line2D lineA = new Line2D.Double(block.getX(), (block.getY() - ballRadius), (block.getX() + Block.SIZE), (block.getY() - ballRadius));
					Point2D intersection = calculateIntersectPoint(ballTrajectory, lineA);
                   bp = new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_Y);
				}
// BOX B:
				
				else if (boxB.intersectsLine(ballTrajectory)) {
					Line2D lineB = new Line2D.Double((block.getX() - ballRadius), block.getY(), (block.getX() - ballRadius), (block.getY() + Block.SIZE));
					Point2D intersection = calculateIntersectPoint(ballTrajectory, lineB);
					bp = new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_X);
				}
// BOX C:
				
				else if (boxC.intersectsLine(ballTrajectory)) {
					Line2D lineC = new Line2D.Double((block.getX() + Block.SIZE + ballRadius), block.getY(), (block.getX() + Block.SIZE + ballRadius), (block.getY() + Block.SIZE));
					Point2D intersection = calculateIntersectPoint(ballTrajectory, lineC);
					bp = new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_X);
				}
// BOX D:
								
				else if (boxD.intersectsLine(ballTrajectory)) {
					Line2D lineD = new Line2D.Double(block.getX(), (block.getY() + Block.SIZE + ballRadius), (block.getX() + Block.SIZE), (block.getY() + Block.SIZE + ballRadius));
					Point2D intersection = calculateIntersectPoint(ballTrajectory, lineD);
					bp = new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_Y);
				}
// BOX E:
				
				else if (boxE.intersectsLine(ballTrajectory)) {
					CircleArc2D arc = new CircleArc2D(block.getX(), block.getY(), ballRadius, 89, 92);

					math.geom2d.line.Line2D traj = new math.geom2d.line.Line2D(BallX, BallY, nextBallX, nextBallY);
					double intX = 0;
					double intY = 0;
					Collection<math.geom2d.Point2D> intersects = arc.intersections(traj);
					if (intersects.size() != 0) {
						for (math.geom2d.Point2D point : intersects) {
							intX = point.getX(); // -- Reminder: Have to look into this
							intY = point.getY();
						}
					}
					
					else { return null;
					 }
					
					if (getBallDirectionX() < 0) {
						bp = new BouncePoint(intX, intY, BounceDirection.FLIP_Y);
					}
					else {
						bp = new BouncePoint(intX, intY, BounceDirection.FLIP_X);
					}
				}
//BOX F
				else if (boxF.intersectsLine(ballTrajectory)) {
					CircleArc2D arc = new CircleArc2D((block.getX() + Block.SIZE), block.getY(), ballRadius, -1, 92);
					
					math.geom2d.line.Line2D traj = new math.geom2d.line.Line2D(BallX, BallY, nextBallX, nextBallY);
					double intX = 0;
					double intY = 0;
					Collection<math.geom2d.Point2D> intersects = arc.intersections(traj);
					if (intersects.size() != 0) {
						for (math.geom2d.Point2D point : intersects) {
							intX = point.getX(); // Look into this
							intY = point.getY();
						}
					}
					else { return null;
					}
					
					if (getBallDirectionX() < 0) {
						bp = new BouncePoint(intX, intY, BounceDirection.FLIP_X);
					}
					else {
						bp = new BouncePoint(intX, intY, BounceDirection.FLIP_Y);
					}
				}
//BOX G
				else if (boxG.intersectsLine(ballTrajectory)) {
					CircleArc2D arc = new CircleArc2D(block.getX(), (block.getY() + Block.SIZE), ballRadius, -89, -92);
					math.geom2d.line.Line2D traj = new math.geom2d.line.Line2D(BallX, BallY, nextBallX, nextBallY);
					double intX = 0;
					double intY = 0;
					Collection<math.geom2d.Point2D> intersects = arc.intersections(traj);
					if (intersects.size() != 0) {
						for (math.geom2d.Point2D point : intersects) {
							intX = point.getX();
							intY = point.getY();
						}
					}
					else { return null;
					}
					
					if (getBallDirectionX() < 0) {
						bp = new BouncePoint(intX, intY, BounceDirection.FLIP_Y);
					}
					else {
						bp = new BouncePoint(intX, intY, BounceDirection.FLIP_X);
					}
				}
				
//BOX H
				else if (boxH.intersectsLine(ballTrajectory)) {
					CircleArc2D arc = new CircleArc2D((block.getX() + Block.SIZE), (block.getY() + Block.SIZE), ballRadius, 269, 92);

					math.geom2d.line.Line2D traj = new math.geom2d.line.Line2D(BallX, BallY, nextBallX, nextBallY);
					double intX = 0;
					double intY = 0;
					Collection<math.geom2d.Point2D> intersects = arc.intersections(traj);
					if (intersects.size() != 0) {
						for (math.geom2d.Point2D point : intersects) {
							intX = point.getX();
							intY = point.getY();
						}
					}
					else { return null;
					}
					
					if (getBallDirectionX() < 0) {
						bp = new BouncePoint(intX, intY, BounceDirection.FLIP_X);
					}
					else {
						bp = new BouncePoint(intX, intY, BounceDirection.FLIP_Y);
					}
				}
			return bp;
	  }

// *** CONTROLLER ***

//hitLastBlockAndLastLevel

private boolean hitLastBlockAndLastLevel(){

    Game game = getGame();
	int nrLevels = game.numberOfLevels();
	setBounce(null);
	   
	   if (nrLevels == currentLevel)
	   {
		   int nrBlocks = numberOfBlocks();
		   if(nrBlocks == 1)
		   {
		     PlayedBlockAssignment block = getBlock(0);
		      BouncePoint bp = calculateBouncePointBlock(block);
			
// Have to correct the NullPointerException - Done
		
			if(bp!=null)
			{
				bp.setHitBlock(block); 
			}
			setBounce(bp);  
		   return (bp!=null);
		   }
	   }
	 return false;
  }


//hitLastBlock

   private boolean hitLastBlock(){
	   int nrBlocks = numberOfBlocks();
	   setBounce(null);

	   if (nrBlocks == 1)
	   {
		   PlayedBlockAssignment block = getBlock(0);
		   BouncePoint bp = calculateBouncePointBlock(block);

		   if(bp!=null)
		   {
			   bp.setHitBlock(block);
		   }
		   setBounce(bp);
		   return (bp!=null);
	   }
	   return false;
  }

//hitBlock

   private boolean hitBlock(){
	 
    int nrBlocks = numberOfBlocks();
	  setBounce(null);
	   
	   
	   for (int index = 0; index< nrBlocks-1; index++)
	   {
		   PlayedBlockAssignment block = getBlock(index);
		   BouncePoint bp = calculateBouncePointBlock(block);
		   bounce = getBounce();
		  
		  boolean closer = isCloser(bp, bounce);
		 if (closer)
		  {
			 if(bp!=null)
			 {
			 bp.setHitBlock(block); // NullPointer correction!
			 }
				 setBounce(bp); 
	   }
	   return (getBounce()!= null);
  }
	return false;
   }

 //isCloser

   private boolean isCloser(BouncePoint first, BouncePoint second){

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

//doHitBlock

private void doHitBlock(){
	   
// Reminder: IMPLEMENT GUARDS!
	   
	       double Y1 = bounce.getY() - currentBallY;
		   double Y2 = ballDirectionY - Y1;
		   double X1 = bounce.getX() - currentBallX;
		   double X2 = ballDirectionX - X1;
		   if (bounce.getDirection() == BounceDirection.FLIP_Y) {
			   if (Y2 == 0) {
				   setCurrentBallX(bounce.getX());
				   setCurrentBallY(bounce.getY());
				   return;
			   }
		   }
		   else if (bounce.getDirection() == BounceDirection.FLIP_X) {
			   if (X2 == 0) {
				   setCurrentBallX(bounce.getX());
				   setCurrentBallY(bounce.getY());
				   return;
			   }
		   }
		   else if (bounce.getDirection() == BounceDirection.FLIP_BOTH) {
			   if ((Y2 == 0) || (X2 == 0)) {
				   return;
			   }
		   }
	
	    int score = getScore();
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
	  ((getGame().getPaddle().getMaxPaddleLength()-(getGame().getPaddle().getMaxPaddleLength()
			   -getGame().getPaddle().getMinPaddleLength())/(getGame().numberOfLevels()-1) *(getCurrentLevel()-1)));
	   
	   setWaitTime( INITIAL_WAIT_TIME * Math.pow(getGame().getBall().getBallSpeedIncreaseFactor(), (getCurrentLevel()-1)));
  }
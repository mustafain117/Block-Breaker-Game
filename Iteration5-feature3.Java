private BouncePoint calculateBouncePointPaddle(){
	      double BallX = getCurrentBallX();
	      double BallY = getCurrentBallY();
	      double nextBallX = (BallX + getBallDirectionX());
	      double nextBallY = (BallY + getBallDirectionY());
	      Line2D ballTrajec = new Line2D.Double(BallX, BallY, nextBallX, nextBallY);     
	      double ballRadius = Ball.BALL_DIAMETER / 2;
	      
	      Rectangle2D boxA = new Rectangle2D.Double(getCurrentPaddleX(), (getCurrentPaddleY() - ballRadius), getCurrentPaddleLength(), ballRadius);
	      Rectangle2D boxB = new Rectangle2D.Double((getCurrentPaddleX() - ballRadius), getCurrentPaddleY(), ballRadius, Paddle.PADDLE_WIDTH);
	      Rectangle2D boxC = new Rectangle2D.Double((getCurrentPaddleX() + getCurrentPaddleLength()), getCurrentPaddleY(), ballRadius, Paddle.PADDLE_WIDTH);
	      Rectangle2D boxE = new Rectangle2D.Double((getCurrentPaddleX() - ballRadius), (getCurrentPaddleY() - ballRadius), ballRadius, ballRadius);
	      Rectangle2D boxF = new Rectangle2D.Double((getCurrentPaddleX() + getCurrentPaddleLength()), (getCurrentPaddleY() - ballRadius), ballRadius, ballRadius);
	       
	      BouncePoint bounceP = null;
	      
	      if (boxA.intersectsLine(ballTrajec)) {
	        if (BallY >= 350) {
	          Line2D lineA = new Line2D.Double((getCurrentPaddleX() - getCurrentPaddleLength()), (getCurrentPaddleY() - ballRadius), getCurrentPaddleX(), (getCurrentPaddleY() - ballRadius));
	          Point2D intersection = calculateIntersectPoint(ballTrajec, lineA);
	          bounceP = new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_Y);
	        }
	      }
	      else if (boxB.intersectsLine(ballTrajec)) {
	        if (BallX >= getCurrentPaddleX() - ballRadius) {
	          Line2D lineB = new Line2D.Double((getCurrentPaddleX() - ballRadius), getCurrentPaddleY(), (getCurrentPaddleX() - ballRadius), (getCurrentPaddleY() + Paddle.PADDLE_WIDTH));
	          Point2D intersection = calculateIntersectPoint(ballTrajec, lineB);
	          bounceP = new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_X);
	        }
	      }
	      else if (boxC.intersectsLine(ballTrajec)) {
	        if (BallX <= getCurrentPaddleX() + getCurrentPaddleLength() + ballRadius) {
	          Line2D lineC = new Line2D.Double((getCurrentPaddleX() + getCurrentPaddleLength() + ballRadius), getCurrentPaddleY(), (getCurrentPaddleX() + getCurrentPaddleLength() + ballRadius), (getCurrentPaddleY() + Paddle.PADDLE_WIDTH));
	          Point2D intersection = calculateIntersectPoint(ballTrajec, lineC);
	          bounceP = new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_X);
	        }
	      }
	      else if (boxE.intersectsLine(ballTrajec)) {
	        CircleArc2D arcE = new CircleArc2D(getCurrentPaddleX(), getCurrentPaddleY(), ballRadius, 89, 92);
	        math.geom2d.line.Line2D traj1 = new math.geom2d.line.Line2D(BallX, BallY, nextBallX, nextBallY);
	        double intX = 0;
	        double intY = 0;
	        Collection<math.geom2d.Point2D> intersects = arcE.intersections(traj1);
	        if (intersects.size() != 0) {
	          for (math.geom2d.Point2D point : intersects) {
	            intX = point.getX();
	            intY = point.getY();
	          }
	        }
	        else{ 
	        	return null;}
	        
	        if (getBallDirectionX() < 0) {
	          bounceP = new BouncePoint(intX, intY, BounceDirection.FLIP_Y);
	        }
	        else {
	          bounceP = new BouncePoint(intX, intY, BounceDirection.FLIP_X);
	        }
	      }
	      else if (boxF.intersectsLine(ballTrajec)) {
	        CircleArc2D arcF = new CircleArc2D((getCurrentPaddleX() + getCurrentPaddleLength()), getCurrentPaddleY(), ballRadius, -1, 92);
	        math.geom2d.line.Line2D traj2 = new math.geom2d.line.Line2D(BallX, BallY, nextBallX, nextBallY);
	        double intX = 0;
	        double intY = 0;
	        Collection<math.geom2d.Point2D> intersects = arcF.intersections(traj2);
	        if (intersects.size() != 0) {
	          for (math.geom2d.Point2D point : intersects) {
	            intX = point.getX();
	            intY = point.getY();
	          }
	        }
	        else { return null; }
	        
	        if (getBallDirectionX() < 0) {
	          bounceP = new BouncePoint(intX, intY, BounceDirection.FLIP_X);
	        }
	        else {
	          bounceP = new BouncePoint(intX, intY, BounceDirection.FLIP_Y);
	        }
	      }
	    return bounceP;
	    }


	    private BouncePoint calculateBouncePointWall(){
	       BouncePoint bounceP = null;
		   double ballRadius = Ball.BALL_DIAMETER / 2;
		   double BallX =  getCurrentBallX();
		   double BallY =  getCurrentBallY();
		   double nextBallX = (BallX + getBallDirectionX());
		   double nextBallY = (BallY + getBallDirectionY());
		   Line2D ballTrajec = new Line2D.Double(BallX, BallY, nextBallX, nextBallY);
		
		   Rectangle2D boxA = new Rectangle2D.Double(0, 0, ballRadius, (Game.PLAY_AREA_SIDE - ballRadius));
		   Rectangle2D boxB = new Rectangle2D.Double(ballRadius, 0, (Game.PLAY_AREA_SIDE - Ball.BALL_DIAMETER), ballRadius);
		   Rectangle2D boxC = new Rectangle2D.Double((Game.PLAY_AREA_SIDE - ballRadius), 0, ballRadius, (Game.PLAY_AREA_SIDE - ballRadius));

		   if (boxA.intersectsLine(ballTrajec) && boxB.intersectsLine(ballTrajec)) {
			   bounceP = new BouncePoint(5, 5, BounceDirection.FLIP_BOTH);
		   }
		   else if (boxB.intersectsLine(ballTrajec) && boxC.intersectsLine(ballTrajec)) {
			   bounceP = new BouncePoint(385, 5, BounceDirection.FLIP_BOTH);
		   }
		   else if (boxA.intersectsLine(ballTrajec)) {
		         if (BallX <= ballRadius ) {
		          Line2D lineA = new Line2D.Double(ballRadius, ballRadius, ballRadius, (Game.PLAY_AREA_SIDE - ballRadius));
		          Point2D intersection = calculateIntersectPoint(ballTrajec, lineA);
		          bounceP = new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_X);
		         }
		      }
		   	   
		   else if (boxB.intersectsLine(ballTrajec)) {
			   	 if (BallY <= 10) {
				  Line2D lineB = new Line2D.Double(ballRadius, ballRadius, (Game.PLAY_AREA_SIDE - ballRadius), ballRadius);
				  Point2D intersection = calculateIntersectPoint(ballTrajec, lineB);
				  bounceP = new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_Y);
			   	 }
			  }
		   else if (boxC.intersectsLine(ballTrajec)) {
			   	 if (BallX >= Game.PLAY_AREA_SIDE - ballRadius) {
				   Line2D lineC = new Line2D.Double((Game.PLAY_AREA_SIDE - ballRadius), ballRadius, (Game.PLAY_AREA_SIDE - ballRadius), (Game.PLAY_AREA_SIDE - ballRadius));
				   Point2D intersection = calculateIntersectPoint(ballTrajec, lineC);
				   bounceP = new BouncePoint(intersection.getX(), intersection.getY(), BounceDirection.FLIP_X);
			   	 }
			  }
			   
		   return bounceP;
	  }
   
 
   
   private Point2D calculateIntersectPoint(Line2D line1, Line2D line2){
	    if (line1.getX2() == line1.getX1()) {	// vertical line
	    		return (new Point2D.Double(line1.getX1(), line2.getY1()));
	    	}
		   double m1 = ((line1.getY2() - line1.getY1()) / (line1.getX2() - line1.getX1()));
		   if (line2.getX2() == line2.getX1()) {  // vertical line
			   return (new Point2D.Double(line2.getX1(), line1.getY1()));
		   }
			  double m2 = ((line2.getY2() - line2.getY1()) / (line2.getX2() - line2.getX1()));
			  double b1 = (line1.getY1() - m1 * line1.getX1());
			  double b2 = (line2.getY1() - m2 * line2.getX1());
			  
			  double x = (b2 - b1) / (m1 - m2);
			  double y = m1 * x + b1;
			  
			  return (new Point2D.Double(x, y));
	  }

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


	     private boolean hitPaddle(){
    	     	BouncePoint bounceP=calculateBouncePointPaddle();
  		setBounce(bounceP);
    		return (bounceP!=null);
 	     }

	     private void doHitPaddleOrWall(){
    		bounceBall();
 	     }
	     private boolean hitWall(){
   	        BouncePoint bounceP = calculateBouncePointWall();
		setBounce(bounceP);   
		return (bounceP!=null);
             }
   

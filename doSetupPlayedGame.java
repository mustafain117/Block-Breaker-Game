 private void doSetup(){
    resetCurrentBallX();
	   resetCurrentBallY();
	   resetBallDirectionX();
	   resetBallDirectionY();
	   resetCurrentPaddleX();
	   Game game = getGame();
	   Level level = game.getLevel(currentLevel-1);
	   List<BlockAssignment> assignments = level.getBlockAssignments();
	   for(BlockAssignment assignment: assignments) {
		   PlayedBlockAssignment pblock = new PlayedBlockAssignment(Game.WALL_PADDING+(Block.SIZE+Game.COLUMNS_PADDING)*(assignment.getGridHorizontalPosition()-1),Game.WALL_PADDING+(Block.SIZE+Game.ROW_PADDING)*(assignment.getGridVerticalPosition()-1), assignment.getBlock(), this);
	   }
	   while(numberOfBlocks() < game.getNrBlocksPerLevel()) {
		   
		   	 int n = game.getNrBlocksPerLevel();
		   
		   	 int maxColumn = (Game.PLAY_AREA_SIDE - 2*(Game.WALL_PADDING) + Game.COLUMNS_PADDING)/Game.COLUMNS_PADDING+Block.SIZE;
			 int minColumn = 1;
			 Random rC = new Random();
			 int index_X = rC.nextInt((maxColumn - minColumn) + 1) + minColumn;
			 
			 int maxRow = n/maxColumn;
			 int minRow = 1;
			 Random rR = new Random();
			  int index_Y = rR.nextInt(Math.abs(maxRow - minRow) + 1) + minRow;
			 
			 
			 int positionX = Game.WALL_PADDING+(Block.SIZE+Game.COLUMNS_PADDING)*(index_X-1);
			 
			 int positionY = Game.WALL_PADDING+(Block.SIZE+Game.COLUMNS_PADDING)*(index_Y-1);
	
			 Boolean positionFound = isPositionFree(positionX,positionY);
			 while(!positionFound) {
				 if(index_X<15) {
					 index_X++;
					 positionX = Game.WALL_PADDING+(Block.SIZE+Game.COLUMNS_PADDING)*(index_X-1);
					 }
					 else if(index_Y<maxRow){
						 index_Y++;
						 index_X=1;
						 positionY= Game.WALL_PADDING+(Block.SIZE+Game.COLUMNS_PADDING)*(index_Y-1);
					 }
					 else {
						 index_X=1;
						 index_Y=1;
						 positionX = Game.WALL_PADDING+(Block.SIZE+Game.COLUMNS_PADDING)*(index_X-1);
						 positionY= Game.WALL_PADDING+(Block.SIZE+Game.COLUMNS_PADDING)*(index_Y-1);
					 }
				 positionFound = isPositionFree(positionX,positionY);
			 }
		     
			 PlayedBlockAssignment pblock = new PlayedBlockAssignment(positionX,positionY,game.getRandomBlock(),this);
		   
		   
	   }
  }

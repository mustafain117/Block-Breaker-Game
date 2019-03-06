package ca.mcgill.ecse223.block.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.TOUserMode.Mode;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Ball;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.BlockAssignment;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Level;
import ca.mcgill.ecse223.block.model.Paddle;
import ca.mcgill.ecse223.block.model.Player;
import ca.mcgill.ecse223.block.model.User;
import ca.mcgill.ecse223.block.model.UserRole;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;

public class Block223Controller {

	// **********
	// Modifier methods
	// **********
	public static void createGame(String name) throws InvalidInputException { 
	    if(!(Block223Application.getCurrentUserRole() instanceof Admin)){
			throw new InvalidInputException("Admin priveleges are required to create a game");
		}
		if(name==null) {
			throw new InvalidInputException("Game name cannot be empty");
		}	
		
		Block223 block223 = Block223Application.getBlock223();
		Admin admin = (Admin) Block223Application.getCurrentUserRole(); 
		Game aGame=block223.findGame(name);	
		
		if(aGame!=null) {
				throw new InvalidInputException("The name of a game must be unique");
		}
		aGame = new Game(name,1,admin,1,1,1.0,1,1,block223);  //need help in this line
		Block223Application.setCurrentGame(aGame);
	}

	public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
        String error = "";

        if(nrLevels < 1 || nrLevels > 99) {
        	 error = "Then number of levels must be between 1 and 99.";
        }
        if(nrBlocksPerLevel < 1) {
          error = error + "The number of blocks per level must be greater than zero.";
        }
        if(minBallSpeedX < 1) {
            error = error + "The minimum speed of the ball must be greater than zero.";
          }
        if(minBallSpeedY < 1) {
            error = error + "The minimum speed of the ball must be greater than zero.";
          }
        if(ballSpeedIncreaseFactor <= 0.0) {
            error = error + "The speed increase factor of the ball must be greater than zero.";
          }
        if(maxPaddleLength < 1 || maxPaddleLength > 400) {
            error = error + "The maximum length of the paddle must be greater than zero and less than or equal to 400.";
          }
        if(minPaddleLength < 1 ) {
            error = error + "The minimum length of the paddle must be greater than zero .";
          }
        if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
        Game game = Block223Application.getCurrentGame();
        
        if(game == null) {
        	throw new InvalidInputException("A game must be selected to define game settings.");
        }
        
        if(!(Block223Application.getCurrentUserRole() instanceof Admin)){
			throw new InvalidInputException("Admin privileges are required to define game settings.");
		}
        
        Admin admin = (Admin) Block223Application.getCurrentUserRole();
        if(!(admin.equals(game.getAdmin()))){
        	throw new InvalidInputException("Only the admin who created the game can define its game settings.");
        }
        
        Ball ball = game.getBall();
        ball.setMinBallSpeedX(minBallSpeedX);
        ball.setMinBallSpeedY(minBallSpeedY);
        ball.setBallSpeedIncreaseFactor(ballSpeedIncreaseFactor);
        
        Paddle paddle = game.getPaddle();
        paddle.setMaxPaddleLength(maxPaddleLength);
        paddle.setMinPaddleLength(minPaddleLength);
        
        List<Level> levels = game.getLevels();
        int size = levels.size();
        
        while(nrLevels > size) {
        	game.addLevel();
        	size = levels.size();
        }
        
        while(nrLevels < size) {
        	 Level level = game.getLevel(size-1);
        	 level.delete();
        	 size = levels.size(); 
        }
}

	public static void deleteGame(String name) throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin priveleges required to delete a game");
		}
	    if(name==null) {
		throw new InvalidInputException("The name of a game must be specified");	//not in sequence diag
		}
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		
		Block223 block223 = Block223Application.getBlock223();
		Game aGame = block223.findGame(name);
		
		if(!(aGame.getAdmin().equals(admin))) {
			throw new InvalidInputException("Only the admin who created the game can delete the game");
		}
			if(aGame != null) {
				aGame.delete();
				Block223Persistence.save(block223);
			}
	}

	public static void selectGame(String name) throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();
		
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin priveleges are required to select a game");
		}
		if(name == null){
			throw new InvalidInputException("A game name needs to be specified.");
		}
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		Game game = block223.findGame(name);
		if(game==null){
			throw new InvalidInputException("A game with the name" + name + "does not exist");
		}
		if(!(admin.equals(game.getAdmin()))){
        	throw new InvalidInputException("Only the admin who created the game can select the game.");
        }    
		Block223Application.setCurrentGame(game);
	}

	public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
		
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin priveleges are required to define game settings");
		}
		
		Game game = Block223Application.getCurrentGame();
		if(game==null) {
			throw new InvalidInputException("A game must be selected to define game settings");
		}
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		if(!(admin.equals(game.getAdmin()))){
        	throw new InvalidInputException("Only the admin who created the game can define its game settings.");
        }    
		String currentName = game.getName();
		if(!currentName.equals(name)) {
			Boolean result = game.setName(name);
			if(result == false) {
	        	throw new InvalidInputException("The name of a game must be unique.");
			}
		}
		if(name==null || name.equals("")){
		 	throw new InvalidInputException("The name of a game must be specified.");
		}
		setGameDetails(nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY, ballSpeedIncreaseFactor,maxPaddleLength,minPaddleLength);
	}

	public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to add a block.");
		}
		Game game = Block223Application.getCurrentGame();
		if(game==null) {
			throw new InvalidInputException("A game must be selected to add a block.");
		}
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		if(!(admin.equals(game.getAdmin()))){
        	throw new InvalidInputException("Only the admin who created the game can add a block.");
        }  
		List<Block> blocks = game.getBlocks();
		for(Block block: blocks) {
			if((block.getRed()==red) && (block.getGreen()==green) && (block.getBlue()==blue)) {
				throw new InvalidInputException("A block with the same color already exists for the game.");
			}
		}
		if(red < 0 || red > 255) {
			throw new InvalidInputException("Red must be between 0 and 255.");
		}
		if(green < 0 || green > 255) {
			throw new InvalidInputException("Green must be between 0 and 255.");
		}
		if(blue < 0 || blue > 255) {
			throw new InvalidInputException("Blue must be between 0 and 255.");
		}
		if(points < 1 || points > 1000) {
			throw new InvalidInputException("Points must be between 1 and 1000.");
		}
		game.addBlock(red,green,blue,points);
	}

	public static void deleteBlock(int id) throws InvalidInputException {

		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Rights reserved by admin to delete a game");
		}
		Admin admin = (Admin) Block223Application.getCurrentUserRole(); 
		Game aGame= Block223Application.getCurrentGame();
		
		if(aGame==null){
			throw new InvalidInputException("A game must be selected");
		}
		
		if(!aGame.getAdmin().equals(admin)) {
			throw new InvalidInputException("Rights reserved by admin of game to delete it");
		}
		Block aBlock=aGame.findBlock(id);
		if(aBlock!=null) {
			aBlock.delete();
		}
		
	}
	

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
		if(Block223Application.getCurrentUserRole() instanceof Admin==false) {
			throw new InvalidInputException("Admin privilleges are required to select a game");
		}
		
		Game game = Block223Application.getCurrentGame();
		
		if (game == null) {
			throw new InvalidInputException("A game must be selected to update a game");
		}
		
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		
		if (!game.getAdmin().equals(admin)) {
			throw new InvalidInputException("Only the Admin who created game can update the block");
		}
		
		
		Block block = game.findBlock(id);
		
		
		if(block == null) {
			throw new InvalidInputException("The block does not exist");
		}
		
		if(red < 0 || red > 255) {
			throw new InvalidInputException("Red must be between 0 and 255.");
		}
		if(green < 0 || green > 255) {
			throw new InvalidInputException("Green must be between 0 and 255.");
		}
		if(blue < 0 || blue > 255) {
			throw new InvalidInputException("Blue must be between 0 and 255.");
		}
		if(points < 1 || points > 1000) {
			throw new InvalidInputException("Points must be between 1 and 1000.");
		}
		
		block.setRed(red);
		block.setGreen(green);
		block.setBlue(blue);
		block.setPoints(points);
		
	}

	public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
		
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin  privileges  are  required  to  position a block");
		}
		Block223 block223 = Block223Application.getBlock223();
		Admin admin = (Admin) Block223Application.getCurrentUserRole(); 
		Game aGame= Block223Application.getCurrentGame();
		
		if(aGame==null){
			throw new InvalidInputException("A game must be selected to position a block");
		}
		
		if(!aGame.getAdmin().equals(admin)) {
			throw new InvalidInputException("Rights reserved by admin of game to delete it");
		}
		Level alevel= aGame.getLevel(level-1);
		if(level<1 || level>aGame.numberOfLevels()-1) {	
			throw new InvalidInputException("Level" + level + "does not exist for the game.");
		}
		if(aGame.numberOfBlocks()==aGame.getNrBlocksPerLevel()) {
			throw new InvalidInputException("The number of blocks has reached the maximum number"  + aGame.getNrBlocksPerLevel() + " allowed for this game");
		}
	    for(int index =0; index<alevel.getBlockAssignments().size();index++) {
		    if(gridHorizontalPosition==alevel.getBlockAssignment(index).getGridHorizontalPosition() || gridVerticalPosition==alevel.getBlockAssignment(index).getGridVerticalPosition()) {
		    	throw new InvalidInputException("A block already  exists  at  location"  + gridHorizontalPosition +  gridVerticalPosition + ".");
		         }
		}
  
	    Block aBlock=aGame.findBlock(id);
		if(aBlock==null) {
			throw new InvalidInputException("The block does not exist");
		}
		
		if(gridHorizontalPosition<=0 || gridHorizontalPosition>15) {
			throw new InvalidInputException("The horizontal position must be between 1 and 15.");
		}
		if(gridVerticalPosition<=0 || gridVerticalPosition>15) {
			throw new InvalidInputException("The vertical position must be between 1 and 15.");
			}
		BlockAssignment aBlockassignment = new BlockAssignment(gridHorizontalPosition, gridVerticalPosition, alevel, aBlock, aGame);
			
	}

	public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
			int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {
	
		
		Game currentGame = Block223Application.getCurrentGame(); 
		UserRole currentUserRole = Block223Application.getCurrentUserRole();
		
		if (!(currentUserRole instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to move a block.");
		}
		if (currentGame == null) {
			throw new InvalidInputException("A game must be selected to move a block.");
		}
		if (!(currentGame.getAdmin().equals(currentUserRole))) {
			throw new InvalidInputException("Only the admin who created the game can move a block.");
		}
		
		Level foundLevel;
		try {
			foundLevel = currentGame.getLevel(level);
		} catch (IndexOutOfBoundsException e) {
			throw new InvalidInputException("The level with index number" + level + "does not exist for the game.");
		}
		
		BlockAssignment foundAssignment = foundLevel.findBlockAssignment(oldGridHorizontalPosition, oldGridVerticalPosition);
		if (foundAssignment == null) {
			throw new InvalidInputException("A block does not exist at location" + oldGridHorizontalPosition + "/" + oldGridVerticalPosition + ".");
		}
		
		if (foundLevel.findBlockAssignment(newGridHorizontalPosition, newGridVerticalPosition) != null) {
			throw new InvalidInputException("A block already exists at location" + newGridHorizontalPosition + "/" + newGridVerticalPosition + ".");
		}
		
		int maxNumberOfHorizontalBlocks = (Game.PLAY_AREA_SIDE - Game.WALL_PADDING * 2 + Game.COLUMNS_PADDING) / (Game.COLUMNS_PADDING + Block.SIZE);
		int maxNumberOfVerticalBlocks = (Game.PLAY_AREA_SIDE - Game.WALL_PADDING + Game.ROW_PADDING - Paddle.VERTICAL_DISTANCE - Paddle.PADDLE_WIDTH - Ball.BALL_DIAMETER) / (Game.ROW_PADDING + Block.SIZE);
		if (newGridHorizontalPosition < 1 || newGridHorizontalPosition > maxNumberOfHorizontalBlocks) {
			throw new InvalidInputException("The horizontal position must be between 1 and " + maxNumberOfHorizontalBlocks + ".");
		}	
		if (newGridVerticalPosition < 1 || newGridVerticalPosition > maxNumberOfVerticalBlocks) {
			throw new InvalidInputException("The vertical position must be between 1 and " + maxNumberOfVerticalBlocks + ".");
		}
		
		foundAssignment.setGridHorizontalPosition(newGridHorizontalPosition);
		foundAssignment.setGridVerticalPosition(newGridVerticalPosition);
	}

	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
		Game game = Block223Application.getCurrentGame();
			
		if( game == null){
			throw new InvalidInputException("A game must be selected to remove a block.");
		}
		if (Block223Application.getCurrentUserRole() instanceof Admin == false){
			throw new InvalidInputException("Admin privileges are required to select a game.");	
		}	
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		if(!game.getAdmin().equals(admin)){
	        throw new InvalidInputException("Only the admin who created the game can remove a block.");
		}
		Level lvl = game.getLevel(level);
		
		BlockAssignment assignment = lvl.findBlockAssignment(gridHorizontalPosition, gridVerticalPosition);
		
		if (assignment != null){
			assignment.delete();
		}
	}

	public static void saveGame() throws InvalidInputException {
		 Game game = Block223Application.getCurrentGame();
		 if(game == null) {
	        	throw new InvalidInputException("A game must be selected to save it.");
	        }
	        
	        if(!(Block223Application.getCurrentUserRole() instanceof Admin)){
				throw new InvalidInputException("Admin privileges are required to define game settings.");
			}
	        
	        Admin admin = (Admin) Block223Application.getCurrentUserRole();
	        if(!(admin.equals(game.getAdmin()))){
	        	throw new InvalidInputException("Only the admin who created the game can save it.");
	        }
		Block223 block223 = Block223Application.getBlock223();
		try {
		Block223Persistence.save(block223);
		} catch(RuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	public static void register(String username, String playerPassword, String adminPassword)
			throws InvalidInputException {
		if (Block223Application.getCurrentUserRole() != null) { 
			throw new InvalidInputException("Cannot register a new user while a user is logged in");
		}
		if (playerPassword.equals(adminPassword)) {
			throw new InvalidInputException("The passwords must be different");
		}
		if (playerPassword == null || playerPassword.equals("")) {
			throw new InvalidInputException("The player password needs to be specified");
		}
		if (username == null || username.equals("")) {
			throw new InvalidInputException("The username must be specified");
		}
		
		Block223 block223 = Block223Application.getBlock223(); 
		Player player = new Player(playerPassword, block223);
		
		User user;
		try {
			user = new User(username, block223, player);
		} catch (RuntimeException e) {
			player.delete();
			throw new InvalidInputException("The username has already been taken");
		}
		if (adminPassword != null && adminPassword != "") {
			Admin admin = new Admin(adminPassword, block223);
			user.addRole(admin);
		}
		Block223Persistence.save(block223);
	}

	public static void login(String username, String password) throws InvalidInputException {

		Block223Application.resetBlock223(); 
		
		if (Block223Application.getCurrentUserRole() != null) {
			throw new InvalidInputException("Cannot login a user while a user is already logged in");
		}

		User user = User.getWithUsername(username);
		
		if (user == null) {
			throw new InvalidInputException("The username or password is incorrect");
		}
		
		List<UserRole> roles = user.getRoles();
		
		for (UserRole role : roles) {
			String rolePassword = role.getPassword();
			
			if (rolePassword.equals(password)) {
				Block223Application.setCurrentUserRole(role); 
				Block223Application.resetBlock223();
				return;
			}
		}
		
		throw new InvalidInputException("The username or password is incorrect");
	}

	public static void logout() {
		Block223Application.setCurrentUserRole(null); 
	}

	// **********
	// Query methods
	// **********
	public static List<TOGame> getDesignableGames() throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)){
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		
		List<TOGame> result = new ArrayList<TOGame>();
	
		List<Game> games = block223.getGames();
		for(Game game: games) {
			Admin gameAdmin = game.getAdmin();
			if(gameAdmin.equals(admin)) {
				TOGame to = new TOGame(game.getName(),game.getLevels().size(),game.getNrBlocksPerLevel(),game.getBall().getMinBallSpeedX(),game.getBall().getMinBallSpeedY(),game.getBall().getBallSpeedIncreaseFactor(),game.getPaddle().getMaxPaddleLength(),game.getPaddle().getMinPaddleLength());
				result.add(to);
			}
		}
		return result;
	}

	public static TOGame getCurrentDesignableGame() throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)){
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		Game game = Block223Application.getCurrentGame();
		if(game==null) {
			throw new InvalidInputException("A game must be selected to access its information");
		}
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		if(!(game.getAdmin().equals(admin))) {
			throw new InvalidInputException("Only the admin who created the game can access its information");
		}
		TOGame to = new TOGame(game.getName(),game.getLevels().size(),game.getNrBlocksPerLevel(),game.getBall().getMinBallSpeedX(),
				game.getBall().getMinBallSpeedY(),game.getBall().getBallSpeedIncreaseFactor(),game.getPaddle().getMaxPaddleLength(),
				game.getPaddle().getMinPaddleLength());
		return to;
	}

	public static List<TOBlock> getBlocksOfCurrentDesignableGame() throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)){
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		Game game = Block223Application.getCurrentGame();
		if(game==null) {
			throw new InvalidInputException("A game must be selected to access its information");
		}
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		if(!(game.getAdmin().equals(admin))) {
			throw new InvalidInputException("Only the admin who created the game can access its information");
		}
		List<TOBlock> result = new ArrayList<TOBlock>();
		List<Block> blocks = game.getBlocks();
		
		for(Block block: blocks) {
			TOBlock to = new TOBlock(block.getId(),block.getRed(),block.getGreen(),block.getBlue(),block.getPoints());
			result.add(to);
		}
		return result;
	}

	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)){
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		Game game = Block223Application.getCurrentGame();
		if(game==null) {
			throw new InvalidInputException("A game must be selected to access its information");
		}
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		if(!(game.getAdmin().equals(admin))) {
			throw new InvalidInputException("Only the admin who created the game can access its information");
		}
		Block block = game.findBlock(id);//find block implemented by talha
		if(block==null) {
			throw new InvalidInputException("The block does not exist");
		}
		TOBlock to = new TOBlock(block.getId(),block.getRed(),block.getGreen(),block.getBlue(),block.getPoints());
		return to;
	}

	public static List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)){
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		Game game = Block223Application.getCurrentGame();
		if(game==null) {
			throw new InvalidInputException("A game must be selected to access its information");
		}
		Admin admin = (Admin) Block223Application.getCurrentUserRole();
		if(!(game.getAdmin().equals(admin))) {
			throw new InvalidInputException("Only the admin who created the game can access its information");
		}
		
		List<TOGridCell> result = new ArrayList<TOGridCell>();
		Level lvl = game.getLevel(level);
		
		List<BlockAssignment> assignments = lvl.getBlockAssignments();
		for(BlockAssignment assignment: assignments) {
			TOGridCell to = new TOGridCell(assignment.getGridHorizontalPosition(),assignment.getGridVerticalPosition(),assignment.getBlock().getId(),assignment.getBlock().getRed(),assignment.getBlock().getGreen(),assignment.getBlock().getBlue(),assignment.getBlock().getPoints());
			result.add(to);
		}
		return result;	}

	public static TOUserMode getUserMode() {

		UserRole userRole = Block223Application.getCurrentUserRole();
		
		if (userRole == null) {
			return new TOUserMode(Mode.None);
		}
		
		if (userRole instanceof Player) {
			return new TOUserMode(Mode.Play);
		}
		
		else {
			return new TOUserMode(Mode.Design);
		}
	}
	
	

}
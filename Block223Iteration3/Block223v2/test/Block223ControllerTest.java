
import org.junit.Test;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Player;
import ca.mcgill.ecse223.block.model.User;
import ca.mcgill.ecse223.block.model.UserRole;

public class Block223ControllerTest {
String name = "Game 1";
/*@Test(expected=InvalidInputException.class)
public void testCreateGame() throws InvalidInputException{
	Block223 block223 = new Block223();
	Admin admin = new Admin("password", block223);
	//UserRole userRole = (UserRole) admin;
	//Player player = new Player("pass", block223);
User user = new User("talha", block223, admin);
user.addRole(admin);
System.out.println(user.indexOfRole(admin));
	Block223Application.setCurrentUserRole(admin);
	Block223Controller.createGame(name);
}*/
@Test(expected=InvalidInputException.class)
public void testDefineSettings() throws InvalidInputException {
	int nrLevels=1;
	int nrBlocksPerLevel=5;
	int minBallSpeedX= 5;
	int minBallSpeedY=5;
	Double ballSpeedIncreaseFactor=5.0;
	int maxPaddleLength=5;
	int minPaddleLength=5;
	
	Block223Controller.setGameDetails(nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY, ballSpeedIncreaseFactor, maxPaddleLength, minPaddleLength);
}
}

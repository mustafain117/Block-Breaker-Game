
//Feature 6 View hall of fame
public static TOHallOfFame getHallOfFame(int start, int end) throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Player)){
			throw new InvalidInputException("Player privileges are required to access a game's hall of fame.");
		}
		PlayedGame pgame = Block223Application.getCurrentPlayableGame();
		if(pgame==null) {
			throw new InvalidInputException("A game must be selected to view its hall of fame.");
		}
		Game game = pgame.getGame();
		TOHallOfFame result = new TOHallOfFame(game.getName());
		if(start<1) {
			start=1;
		}
		if(end>game.numberOfHallOfFameEntries()) {
			end = game.numberOfHallOfFameEntries();
		}
		start=start-1;
		end= end-1;
		for(int i = start; i <= end ; i++){
			TOHallOfFameEntry to = new TOHallOfFameEntry(i+1, game.getHallOfFameEntry(i).getPlayername(), game.getHallOfFameEntry(i).getScore(), result);
		}
		return result;
	}

	public static TOHallOfFame getHallOfFameWithMostRecentEntry(int numberOfEntries) throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Player)){
			throw new InvalidInputException("Player privileges are required to access a game's hall of fame.");
		}
		PlayedGame pgame = Block223Application.getCurrentPlayableGame();
		if(pgame==null) {
			throw new InvalidInputException("A game must be selected to view its hall of fame.");
		}
		Game game = pgame.getGame();
		TOHallOfFame result = new TOHallOfFame(game.getName());
		HallOfFameEntry mostRecent = game.getMostRecentEntry();
		int index = game.indexOfHallOfFameEntry(mostRecent);
		int start = index - numberOfEntries/2;
		if(start<1) {
			start=1;
		}
		int end = start+numberOfEntries-1;
		if(end>game.numberOfHallOfFameEntries()) {
			end = game.numberOfHallOfFameEntries();
		}
		start=start-1;
		end=end-1;
		for(int i = start; i <= end ; i++){
			TOHallOfFameEntry to = new TOHallOfFameEntry(i+1, game.getHallOfFameEntry(i).getPlayername(), game.getHallOfFameEntry(i).getScore(), result);
		}
		return result;
	}	
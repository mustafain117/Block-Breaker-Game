public static List<TOPlayableGame> getPlayableGames() throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();
		if(!(Block223Application.getCurrentUserRole() instanceof Player)){
			throw new InvalidInputException("Player privileges are required to play a game.");
		}
		Player player = (Player) Block223Application.getCurrentUserRole();
		
		List<TOPlayableGame> result = new ArrayList<TOPlayableGame>();
		List<Game> games = block223.getGames();
		
		for(Game game: games) {
			Boolean published = game.isPublished();
			if(published) {
				TOPlayableGame to = new TOPlayableGame(game.getName(), -1, 0);
				result.add(to);
			}
		}
		List<PlayedGame> playedGames = player.getPlayedGames();
		for(PlayedGame playedGame : playedGames) {
			TOPlayableGame to = new TOPlayableGame(playedGame.getGame().getName(), playedGame.getId(), playedGame.getCurrentLevel());
			result.add(to);
		}
		
		return result;
	}
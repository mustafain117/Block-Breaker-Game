public static void selectPlayableGame(String name, int id) throws InvalidInputException  {
			if(!(Block223Application.getCurrentUserRole() instanceof Player)) {
				throw new InvalidInputException("Player privileges are required to play a game.");
			}
			Block223 block223 = Block223Application.getBlock223();
			Game game = block223.findGame(name);
			PlayedGame pgame;
			Player player = (Player) Block223Application.getCurrentUserRole();
			if(game!=null) {
				String username = User.findUsername(player);
				//findUsername(...) needs to be implemented in the User class
				pgame = new PlayedGame(username,game,block223);
				pgame.setPlayer(player);
			}else {
				 pgame = block223.findPlayableGame(id);
				 if(pgame == null) {
						throw new InvalidInputException("The game does not exist.");
				 }
				 if(!pgame.getPlayer().equals(player)) {
					 throw new InvalidInputException("Only the player that started a game can continue the game.");
				 }
				//findPlayableGame needs to be implemented	
			}
			Block223Application.setCurrentPlayableGame(pgame);
		}
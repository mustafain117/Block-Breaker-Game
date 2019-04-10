public static void startGame(Block223PlayModeInterface ui) throws InvalidInputException {
			PlayedGame game = Block223Application.getCurrentPlayableGame();
			if(game==null) {
				throw new InvalidInputException("A game must be selected to play it.");
			}
			if((Block223Application.getCurrentUserRole() instanceof Admin) && game.getPlayer()!=null ) {
				throw new InvalidInputException("Player privileges are required to play a game.");
			}
			if((Block223Application.getCurrentUserRole() instanceof Admin) && !game.getGame().getAdmin().equals(Block223Application.getCurrentUserRole())){
				throw new InvalidInputException("Only the admin of a game can test the game.");
			}
			
			if((Block223Application.getCurrentUserRole() instanceof Player) && game.getPlayer()==null) {
				throw new InvalidInputException("Admin privileges are required to test a game.");
			}
			if((Block223Application.getCurrentUserRole() ==null)) {
				throw new InvalidInputException("Player privileges are required to play a game.");
			}
			//need to add InvalidInputException
			game.play();
			ui.takeInputs();
			while(game.getPlayStatus()==PlayStatus.Moving) {
				String userInputs = ui.takeInputs();
				updatePaddlePosition(userInputs);
				//needs to be implemented
				game.move();	
				
				if(userInputs.contains(" ")) {
					game.pause();
				}
				
				try {
					TimeUnit.MILLISECONDS.sleep((long) game.getWaitTime());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ui.refresh();	
			}
			if(game.getPlayStatus()==PlayStatus.GameOver) {
				Block223Application.setCurrentPlayableGame(null);
			}else if(game.getPlayer()!=null){
				Block223 block223 = Block223Application.getBlock223();
				Block223Persistence.save(block223);
			}
	}
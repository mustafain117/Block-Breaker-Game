package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Properties;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOGridCell;
import ca.mcgill.ecse223.block.controller.TOUserMode;


public class MoveBlockPage extends JFrame { //UI for move block (feature 9)
	private static final long serialVersionUID = 1L;
	
	private JLabel errorMessage;
	private String error;
	private JLabel successMessage;
	String success;
	
	private JComboBox<String> gamesList;
	private JLabel gamesLabel;
	private JButton gamesButton;
	private JComboBox<String> allBlocksList;
	private JLabel allBlocksLabel;
	private JComboBox<String> levelCellsList;
	private JLabel levelCellsLabel;
	private JTextField levelCellsTextField;
	private JButton levelCellsButton;
	private JTextField xiTextField, yiTextField, xfTextField, yfTextField;
	private JLabel xiLabel, yiLabel, xfLabel, yfLabel;
	private JButton moveButton;
	
	private HashMap<Integer, String> games;
	private HashMap<Integer, TOBlock> allBlocks;
	private HashMap<Integer, TOGridCell> levelCells;
	
	public MoveBlockPage() {
		initComponents();
		refreshData();
	}
	
	private void initComponents() {
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		successMessage = new JLabel();
		successMessage.setForeground(Color.GREEN);
		
		gamesList = new JComboBox<String>(new String[0]);
		gamesLabel = new JLabel();
		gamesLabel.setText("Select Game:");
		gamesButton = new JButton();
		gamesButton.setText("Generate List of Games");
		allBlocksList = new JComboBox<String>(new String[0]);
		allBlocksLabel = new JLabel();
		allBlocksLabel.setText("List of All Blocks at Selected Game:");
		levelCellsList = new JComboBox<String>(new String[0]);
		levelCellsLabel = new JLabel();
		levelCellsLabel.setText("List of All Blocks at Selected Level");
		levelCellsTextField = new JTextField();
		levelCellsButton = new JButton();
		levelCellsButton.setText("Generate List of Blocks at this Level");
		xiTextField = new JTextField();
		yiTextField = new JTextField();
		xfTextField = new JTextField();
		yiTextField = new JTextField();
		xiLabel = new JLabel();
		xiLabel.setText("Initial Grid Horizontal Position:");
		xfLabel = new JLabel();
		xfLabel.setText("Final Grid Horizontal Position:");
		yiLabel = new JLabel();
		yiLabel.setText("Initial Grid Vertical Position:");
		yfLabel = new JLabel();
		yfLabel.setText("Final Grid Vertical Position:");
		moveButton = new JButton();
		moveButton.setText("Move Block");
		
		gamesList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event) {
				gamesListActionPerformed(event);
			}
		});
		
		gamesButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event) {
				gamesButtonActionPerformed(event);
			}
		});
		
		levelCellsButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event) {
				levelCellsButtonActionPerformed(event);
			}
		});
		
		moveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event) {
				moveButtonActionPerformed(event);
			}
		});
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addComponent(successMessage)
				.addGroup(
						layout.createSequentialGroup()
						.addGroup(
								layout.createParallelGroup()
								.addComponent(gamesLabel)
								.addComponent(gamesList)
								.addComponent(gamesButton))
						.addGroup(
								layout.createParallelGroup()
								.addComponent(allBlocksLabel)
								.addComponent(allBlocksList))
						.addGroup(
								layout.createParallelGroup()
								.addComponent(levelCellsLabel)
								.addComponent(levelCellsList)
								.addGroup(
										layout.createSequentialGroup()
										.addComponent(levelCellsButton)
										.addComponent(levelCellsTextField))
								)
						)
				.addGroup(
						layout.createSequentialGroup()
						.addGroup(
								layout.createParallelGroup()
								.addGroup(
										layout.createSequentialGroup()
										.addComponent(xiLabel)
										.addComponent(xiTextField))
								.addGroup(
										layout.createSequentialGroup()
										.addComponent(xfLabel)
										.addComponent(xfTextField))
								)
						.addGroup(
								layout.createParallelGroup()
								.addGroup(
										layout.createSequentialGroup()
										.addComponent(yiLabel)
										.addComponent(yiTextField))
								.addGroup(
										layout.createSequentialGroup()
										.addComponent(yfLabel)
										.addComponent(yfTextField))
								)
						)
				.addComponent(moveButton)
				);
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addComponent(successMessage)
				.addGroup(
						layout.createParallelGroup()
						.addGroup(
								layout.createSequentialGroup()
								.addComponent(gamesLabel)
								.addComponent(gamesList)
								.addComponent(gamesButton))
						.addGroup(
								layout.createSequentialGroup()
								.addComponent(allBlocksLabel)
								.addComponent(allBlocksList))
						.addGroup(
								layout.createSequentialGroup()
								.addComponent(levelCellsLabel)
								.addComponent(levelCellsList)
								.addGroup(
										layout.createParallelGroup()
										.addComponent(levelCellsButton)
										.addComponent(levelCellsTextField))
								)
						)
				.addGroup(
						layout.createParallelGroup()
						.addGroup(
								layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup()
										.addComponent(xiLabel)
										.addComponent(xiTextField))
								.addGroup(
										layout.createParallelGroup()
										.addComponent(xfLabel)
										.addComponent(xfTextField))
								)
						.addGroup(
								layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup()
										.addComponent(yiLabel)
										.addComponent(yiTextField))
								.addGroup(
										layout.createParallelGroup()
										.addComponent(yfLabel)
										.addComponent(yfTextField))
								)
						)
				.addComponent(moveButton)
				);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {gamesLabel, gamesList, gamesButton});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {allBlocksLabel, allBlocksList});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {levelCellsLabel, levelCellsList});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {xiLabel, xfLabel, yiLabel, yfLabel});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {xiTextField, xfTextField, yiTextField, yfTextField});
		
		pack();
	}
	
	private void refreshData() {
		errorMessage.setText(error);
		successMessage.setText(success);
		
		if (error == null || error.length() == 0) {
			levelCellsTextField.setText("");
			xiTextField.setText("");
			xfTextField.setText("");
			yiTextField.setText("");
			yfTextField.setText("");
			
			games = new HashMap<Integer, String>();
			gamesList.removeAllItems();
			Integer index = 0;
			List<TOGame> list1;
			List<TOBlock> list2;
			try {
				list1 = Block223Controller.getDesignableGames();
			} catch (InvalidInputException e) {
				list1 = null;
			}
			try {
				list2 = Block223Controller.getBlocksOfCurrentDesignableGame();
			} catch (InvalidInputException e) {
				list2 = null;
			}
			
			for (TOGame game : list1) {
				games.put(index, game.getName());
				gamesList.addItem(game.getName());
				index++;
			}
			gamesList.setSelectedIndex(-1);
			
			allBlocks = new HashMap<Integer, TOBlock>();
			allBlocksList.removeAllItems();
			index = 0;
			for (TOBlock block : list2) {
				allBlocks.put(index, block);
				allBlocksList.addItem("(Red = " + block.getRed() + ", " + "Green = " + block.getGreen() + ", " + "Blue = " + block.getBlue() + ")");
				index++;
			}
			allBlocksList.setSelectedIndex(-1);
			
			levelCells = new HashMap<Integer, TOGridCell>();
			levelCellsList.removeAllItems();
			allBlocksList.setSelectedIndex(-1);
			
			pack();
		}
	}
	
	private void gamesButtonActionPerformed(java.awt.event.ActionEvent event) {
		error = null;
		success = "Generated List of Games";
		refreshData();
	}
	
	private void gamesListActionPerformed(java.awt.event.ActionEvent event) {
		error = null;
		success = "Game" + games.get(gamesList.getSelectedIndex()) + "has been selected";
		
		try {
			Block223Controller.selectGame(games.get(gamesList.getSelectedIndex()));
		} catch (InvalidInputException e) {
			error = e.getMessage();
			success = null;
		}
		
		refreshData();
	}
	
	private void levelCellsButtonActionPerformed(java.awt.event.ActionEvent event) {
		error = null;
		success = "Level #" + levelCellsTextField.getText() + " has been selected";
		
		levelCells = new HashMap<Integer, TOGridCell>();
		levelCellsList.removeAllItems();
		levelCellsList.setSelectedIndex(-1);
		int level;
		try {
			level = Integer.parseInt(levelCellsTextField.getText()) - 1; //The level number entered in the text field is greater than the level index by 1
		} catch (NumberFormatException e) {
			error = "An integer must be entered in the text field";
			success = null;
			refreshData();
			return;
		}
		
		try { 
			Integer index = 0;
			for (TOGridCell cell : Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(level)) {
				levelCells.put(index, cell);
				levelCellsList.addItem("(Red = " + cell.getRed() + ", " + "Green = " + cell.getGreen() + ", " + "Blue = " + cell.getBlue() + ")" + " - " + "X Pos = " + cell.getGridHorizontalPosition() + "Y Pos = " + cell.getGridVerticalPosition());
				index++;
			}
		} catch (InvalidInputException e) {
			error = e.getMessage();
			success = null;
			refreshData();
		}
	}
	
	private void moveButtonActionPerformed(java.awt.event.ActionEvent event) {
		error = null;
		success = "The block at position " + xiTextField.getText() + "/" + yiTextField.getText() + "has been moved to position" + xfTextField.getText() + "/" + yfTextField.getText();
		int level, xi, yi, xf, yf;
		try {
			level = Integer.parseInt(levelCellsTextField.getText());
			xi = Integer.parseInt(xiTextField.getText());
			xf = Integer.parseInt(xfTextField.getText());
			yi = Integer.parseInt(yiTextField.getText());
			yf = Integer.parseInt(yfTextField.getText());
		} catch (NumberFormatException e) {
			error = "Only integers can be entered in the text fields";
			success = null;
			refreshData();
			return;
		}
		
		try {
			Block223Controller.moveBlock(level, xi, yi, xf, yf);
		} catch (InvalidInputException e) {
			error = e.getMessage();
			success = null;
		}
		
		refreshData();
		
	}
	
}

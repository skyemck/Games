import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;




public class boardDisplay extends JPanel{

	private aiTicTacToe ai2;
	private aiTicTacToe ai1;
	private char difficulty = 'm';

	int player1wins = 0; 
	int player2wins = 0;
	int x;
	int y;
	int z;

	private static java.util.List<java.util.List<positionTicTacToe>>  winningLines = runTicTacToe.initializeWinningLines(); 
	private static java.util.List<positionTicTacToe> board = new ArrayList<>();

	int turn;


	JLabel p1turn = new JLabel("Player 1's Turn");
	JLabel p1wins = new JLabel("Player 1 Wins: "+ player1wins);
	JLabel p2wins = new JLabel("Player 2 Wins: "+ player2wins);
	JLabel title = new JLabel("4x4x4 Tic-Tac-Toe");
	JLabel xyz = new JLabel("x: y: z:");

	JButton playAgainButton = new JButton("Play Again");

	Color navy = new Color(015,025,112);
	Color periwinkle = new Color(176,184,255);

	public boardDisplay(){
		//Image gametitle = Toolkit.getDefaultToolkit().getImage("atari-title.png");
		board = runTicTacToe.createTicTacToeBoard();
		turn = 1;
		setBackground(navy);
		setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		add(p1turn);

		addMouseListener(new XOListener());

		ai1 = new aiTicTacToe(1,difficulty);
		ai2 = new aiTicTacToe(2,difficulty);

		
		add(playAgainButton);

		playAgainButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				board = runTicTacToe.createTicTacToeBoard();
				turn = 1;
				repaint();
				//run();
			}
		});
	}
			
	public void paintComponent(Graphics page){
		super.paintComponent(page);

		Image gametitle = Toolkit.getDefaultToolkit().getImage("title.png");
		page.drawImage(gametitle,0,0,null);

		Graphics2D page2 = (Graphics2D)page;

		int i = 5;
		int j = 5;
		int width = 62;
		int height = 40;
		int xpos = 300;
		int xpos2 = 300 - 75;
		int xpos3 = 300 - 150;
		int xpos4 = 300 - 225;
		int ypos1 = 30;
		int ypos2 = 120;
		int ypos3 = 210;
		int ypos4 = 300;

		if(turn == 1){
			p1turn.setText("Player 1's Turn");
		}else{
			p1turn.setText("Player 2's Turn");
		}

		page2.shear(.8,0);

		page2.setColor(periwinkle);
		page2.drawRect(xpos ,ypos1,width,height);
		page2.drawRect(xpos + width,ypos1+height,width,height);

		page2.drawRect(xpos,ypos1 + height,width,height);
		page2.drawRect(xpos,ypos1 + (height/2),width,height);

		
		page2.drawRect(xpos + width,ypos1,width,height);
		page2.drawRect(xpos + (width/2),ypos1,width,height);

		page2.drawRect(xpos + width,ypos1 + (height/2),width,height);
		page2.drawRect(xpos + (width/2),ypos1+height,width,height);

		page2.drawRect(xpos2 ,ypos2,width,height);
		page2.drawRect(xpos2 + width,ypos2+height,width,height);

		page2.drawRect(xpos2,ypos2 + height,width,height);
		page2.drawRect(xpos2,ypos2 + (height/2),width,height);

		page2.drawRect(xpos2 + width,ypos2,width,height);
		page2.drawRect(xpos2 + (width/2),ypos2,width,height);

		page2.drawRect(xpos2 + width,ypos2 + (height/2),width,height);
		page2.drawRect(xpos2 + (width/2),ypos2+height,width,height);

		page2.drawRect(xpos3 ,ypos3,width,height);
		page2.drawRect(xpos3 + width,ypos3+height,width,height);

		page2.drawRect(xpos3,ypos3 + height,width,height);
		page2.drawRect(xpos3,ypos3 + (height/2),width,height);

		page2.drawRect(xpos3 + width,ypos3,width,height);
		page2.drawRect(xpos3 + (width/2),ypos3,width,height);

		page2.drawRect(xpos3 + width,ypos3 + (height/2),width,height);
		page2.drawRect(xpos3 + (width/2),ypos3+height,width,height);

		page2.drawRect(xpos4 ,ypos4,width,height);
		page2.drawRect(xpos4 + width,ypos4+height,width,height);

		page2.drawRect(xpos4,ypos4 + height,width,height);
		page2.drawRect(xpos4,ypos4 + (height/2),width,height);

		page2.drawRect(xpos4 + width,ypos4,width,height);
		page2.drawRect(xpos4 + (width/2),ypos4,width,height);

		page2.drawRect(xpos4 + width,ypos4 + (height/2),width,height);
		page2.drawRect(xpos4 + (width/2),ypos4+height,width,height);

		ypos4 += 20;
		ypos3 += 20;
		ypos1 += 20;
		ypos2 += 20;
		page2.setFont(new Font("TimesRoman",Font.PLAIN,20));
		for(int k = 0; k < board.size();k++){
			if(board.get(k).state == 1){
				char[] c = {'X'};
				if(board.get(k).z == 0)
					page2.drawChars(c,0,1,xpos +(width/2) *board.get(k).y,ypos1 +(height/2) *board.get(k).x);
				else if(board.get(k).z == 1)
					page2.drawChars(c,0,1,xpos2 +(width/2) *board.get(k).y,ypos2 +(height/2) *board.get(k).x);
				else if(board.get(k).z == 2)
					page2.drawChars(c,0,1,xpos3 +(width/2) *board.get(k).y,ypos3 +(height/2) *board.get(k).x);
				else if(board.get(k).z == 3)
					page2.drawChars(c,0,1,xpos4 +(width/2) *board.get(k).y,ypos4 +(height/2) *board.get(k).x);
			}else if(board.get(k).state == 2){
				char[] c = {'O'};
				if(board.get(k).z == 0)
					page2.drawChars(c,0,1,xpos +(width/2) *board.get(k).y,ypos1 +(height/2) *board.get(k).x);
				else if(board.get(k).z == 1)
					page2.drawChars(c,0,1,xpos2 +(width/2) *board.get(k).y,ypos2 +(height/2) *board.get(k).x);
				else if(board.get(k).z == 2)
					page2.drawChars(c,0,1,xpos3 +(width/2) *board.get(k).y,ypos3 +(height/2) *board.get(k).x);
				else if(board.get(k).z == 3)
					page2.drawChars(c,0,1,xpos4 +(width/2) *board.get(k).y,ypos4 +(height/2) *board.get(k).x);

			}
		}
		

		//check for winner 
		int result = isEnded();
		page.setFont(new Font("Courier New",Font.BOLD,40));
		//page.setColor(Color.black);
		if(result == 1){
			page.drawString("CONGRATS",500,100);
			page.drawString("PLAYER 1",450,150);
			page.drawString("YOU WIN",400,200);
		}else if(result == 2){
			page.drawString("CONGRATS",500,100);
			page.drawString("PLAYER 2",450,150);
			page.drawString("YOU WIN",400,200);
		}else if(result == -1){
			page.setFont(new Font("Monospaced",Font.BOLD,40));
			page.drawString("IT'S A DRAW",575,200);
		}
	}

	public void reset(){
		board = runTicTacToe.createTicTacToeBoard();
		turn = 1;
	}
	
	private class XOListener implements MouseListener{
		
		public void mousePressed(MouseEvent event){}
		public void mouseReleased(MouseEvent event){}
		public void mouseEntered(MouseEvent event){}
		public void mouseExited(MouseEvent event){}

		public void mouseClicked(MouseEvent event){
			if(isEnded() < 1){
				int a = event.getX();
				int b = event.getY();
				if(a < 310 || a < 515){
					repaint(); //out of bounds
				}if(a > 335 && a < 360 && b > 30 && b < 45){
					x = 0;
					y = 0;
					z = 0;
					System.out.printf("X is %d and Y is %d",a,b);
				}if(a > 365 && a < 390 && b > 30 && b < 45){
					x = 0;
					y = 1;
					z = 0;
					System.out.printf("X is %d and Y is %d",a,b);
				}if(a > 395 && a < 420 && b > 30 && b < 45){
					x = 0;
					y = 2;
					z = 0;
					System.out.printf("X is %d and Y is %d",a,b);
				}if(a > 425 && a < 450 && b > 30 && b < 45){
					x = 0;
					y = 3;
					z = 0;
					System.out.printf("X is %d and Y is %d",a,b);
				}if(a > 350 && a < 375 && b > 50 && b < 65){
					x = 1;
					y = 0;
					z = 0;
					System.out.printf("X is %d and Y is %d",a,b);
				}if(a > 380 && a < 405 && b > 50 && b < 65){
					x = 1;
					y = 1;
					z = 0;
					System.out.printf("X is %d and Y is %d",a,b);
				}if(a > 410 && a < 435 && b > 50 && b < 65){
					x = 1;
					y = 2;
					z = 0;
				}if(a > 440 && a < 465 && b > 50 && b < 65){
					x = 1;
					y = 3;
					z = 0;
				}if(a > 365 && a < 390 && b > 70 && b < 85){
					x = 2;
					y = 0;
					z = 0;
				}if(a > 395 && a < 420 && b > 70 && b < 85){
					x = 2;
					y = 1;
					z = 0;
				}if(a > 425 && a < 450 && b > 70 && b < 85){
					x = 2;
					y = 2;
					z = 0;
				}if(a > 455 && a < 480 && b > 70 && b < 85){
					x = 2;
					y = 3;
					z = 0;
				}if(a > 380 && a < 405 && b > 90 && b < 105){
					x = 3;
					y = 0;
					z = 0;
				}if(a > 410 && a < 435 && b > 90 && b < 105){
					x = 3;
					y = 1;
					z = 0;
				}if(a > 440 && a < 465 && b > 90 && b < 105){
					x = 3;
					y = 2;
					z = 0;
				}if(a > 470 && a < 495 && b > 90 && b < 105){
					x = 3;
					y = 3;
					z = 0;
					//plane z-1
				}if(a > 335 && a < 360 && b > 120 && b < 135){
					x = 0;
					y = 0;
					z = 1;
				}if(a > 365 && a < 390 && b > 120 && b < 135){
					x = 0;
					y = 1;
					z = 1;
				}if(a > 395 && a < 420 && b > 120 && b < 135){
					x = 0;
					y = 2;
					z = 1;
				}if(a > 425 && a < 450 && b > 120 && b < 135){
					x = 0;
					y = 3;
					z = 1;
				}if(a > 350 && a < 375 && b > 140 && b < 155){
					x = 1;
					y = 0;
					z = 1;
				}if(a > 380 && a < 405 && b > 140 && b < 155){
					x = 1;
					y = 1;
					z = 1;
				}if(a > 410 && a < 435 && b > 140 && b < 155){
					x = 1;
					y = 2;
					z = 1;
				}if(a > 440 && a < 465 && b > 140 && b < 155){
					x = 1;
					y = 3;
					z = 1;
				}if(a > 365 && a < 390 && b > 160 && b < 175){
					x = 2;
					y = 0;
					z = 1;
				}if(a > 395 && a < 420 && b > 160 && b < 175){
					x = 2;
					y = 1;
					z = 1;
				}if(a > 425 && a < 450 && b > 160 && b < 175){
					x = 2;
					y = 2;
					z = 1;
				}if(a > 455 && a < 480 && b > 160 && b < 175){
					x = 2;
					y = 3;
					z = 1;
				}if(a > 380 && a < 405 && b > 180 && b < 195){
					x = 3;
					y = 0;
					z = 1;
				}if(a > 410 && a < 435 && b > 180 && b < 195){
					x = 3;
					y = 1;
					z = 1;
				}if(a > 440 && a < 465 && b > 180 && b < 195){
					x = 3;
					y = 2;
					z = 1;
				}if(a > 470 && a < 495 && b > 180 && b < 195){
					x = 3;
					y = 3;
					z = 1;
					//plane z-2
				}if(a > 335 && a < 360 && b > 210 && b < 225){
					x = 0;
					y = 0;
					z = 2;
				}if(a > 365 && a < 390 && b > 210 && b < 225){
					x = 0;
					y = 1;
					z = 2;
				}if(a > 395 && a < 420 && b > 210 && b < 225){
					x = 0;
					y = 2;
					z = 2;
				}if(a > 425 && a < 450 && b > 210 && b < 225){
					x = 0;
					y = 3;
					z = 2;
				}if(a > 350 && a < 375 && b > 230 && b < 245){
					x = 1;
					y = 0;
					z = 2;
				}if(a > 380 && a < 405 && b > 230 && b < 245){
					x = 1;
					y = 1;
					z = 2;
				}if(a > 410 && a < 435 && b > 230 && b < 245){
					x = 1;
					y = 2;
					z = 2;
				}if(a > 440 && a < 465 && b > 230 && b < 245){
					x = 1;
					y = 3;
					z = 2;
				}if(a > 365 && a < 390 && b > 250 && b < 265){
					x = 2;
					y = 0;
					z = 2;
				}if(a > 395 && a < 420 && b > 250 && b < 265){
					x = 2;
					y = 1;
					z = 2;
				}if(a > 425 && a < 450 && b > 250 && b < 265){
					x = 2;
					y = 2;
					z = 2;
				}if(a > 455 && a < 480 && b > 250 && b < 265){
					x = 2;
					y = 3;
					z = 2;
				}if(a > 380 && a < 405 && b > 270 && b < 285){
					x = 3;
					y = 0;
					z = 2;
				}if(a > 410 && a < 435 && b > 270 && b < 285){
					x = 3;
					y = 1;
					z = 2;
				}if(a > 440 && a < 465 && b > 270 && b < 285){
					x = 3;
					y = 2;
					z = 2;
				}if(a > 470 && a < 495 && b > 270 && b < 285){
					x = 3;
					y = 3;
					z = 2;
					//plane z-3
				}if(a > 335 && a < 360 && b > 300 && b < 315){
					x = 0;
					y = 0;
					z = 3;
				}if(a > 365 && a < 390 && b > 300 && b < 315){
					x = 0;
					y = 1;
					z = 3;
				}if(a > 395 && a < 420 && b > 300 && b < 315){
					x = 0;
					y = 2;
					z = 3;
				}if(a > 425 && a < 450 && b > 300 && b < 315){
					x = 0;
					y = 3;
					z = 3;
				}if(a > 350 && a < 375 && b > 320 && b < 335){
					x = 1;
					y = 0;
					z = 3;
				}if(a > 380 && a < 405 && b > 320 && b < 335){
					x = 1;
					y = 1;
					z = 3;
				}if(a > 410 && a < 435 && b > 320 && b < 335){
					x = 1;
					y = 2;
					z = 3;
				}if(a > 440 && a < 465 && b > 320 && b < 335){
					x = 1;
					y = 3;
					z = 3;
				}if(a > 395 && a < 390 && b > 340 && b < 355){
					x = 2;
					y = 0;
					z = 3;
				}if(a > 395 && a < 420 && b > 340 && b < 355){
					x = 2;
					y = 1;
					z = 3;
				}if(a > 425 && a < 450 && b > 340 && b < 355){
					x = 2;
					y = 2;
					z = 3;
				}if(a > 455 && a < 480 && b > 340 && b < 355){
					x = 2;
					y = 3;
					z = 3;
				}if(a > 380 && a < 405 && b > 360 && b < 375){
					x = 3;
					y = 0;
					z = 3;
				}if(a > 410 && a < 435 && b > 360 && b < 375){
					x = 3;
					y = 1;
					z = 3;
				}if(a > 440 && a < 465 && b > 360 && b < 375){
					x = 3;
					y = 2;
					z = 3;
				}if(a > 470 && a < 495 && b > 360 && b < 375){
					x = 3;
					y = 3;
					z = 3;
				}


				positionTicTacToe nextMove1 = new positionTicTacToe(x,y,z);
				if(makeMove(nextMove1,1,board))
					turn = 2;
					System.out.println("turn 2");

			}

		}
	}

	public void run()
	{	
		int result;
		while((result = isEnded())<1) //game loop
			{
				if(turn==1)
				{
					repaint();
					//System.out.printf("Player 1 turn %d\n", count);
					//count++;
	
					
				}
				else if(turn==2)
				{
					repaint();
					//printBoardTicTacToe(board);
					//System.out.printf("Player 2 turn %d\n", count);
					positionTicTacToe player2NextMove = ai2.myAIAlgorithm(board,2); //2 stands for player 2
					if(makeMove(player2NextMove,2,board))
						turn = 1;
				}
				else 
				{
				//exception occurs, stop
					System.out.println("Error!");
				}
			}
			if(result==1)
		{
			//game ends, player 1 wins 
			System.out.println("Player1 Wins");
			//printBoardTicTacToe(board);
			repaint();
		}
		else if(result==2)
		{
			//game ends, player 1 wins 
			System.out.println("Player2 Wins");
			//printBoardTicTacToe(board);
			repaint();
		}
		else if(result==-1)
		{
			//game ends, it's a draw 
			System.out.println("This is a draw.");
			repaint();
		}
		else
		{
			//exception occurs, stop
			System.out.println("Error!");
		}
		}

public boolean makeMove(positionTicTacToe position, int player, java.util.List<positionTicTacToe> tagergetBoard)
	{
		//make move on Tic-Tac-Toe board, given position and player 
		//player 1 = 1, player 2 = 2
		
		//brute force (obviously not a wise way though)
		for(int i=0;i<tagergetBoard.size();i++)
		{
			if(tagergetBoard.get(i).x==position.x && tagergetBoard.get(i).y==position.y && tagergetBoard.get(i).z==position.z) //if this is the position
			{
				if(tagergetBoard.get(i).state==0)
				{
					tagergetBoard.get(i).state = player;
					return true;
				}
				else
				{
					System.out.println("Error: this is not a valid move.");
				}
			}
			
		}
		return false;
	}


		public static int isEnded()
	{
		//test whether the current game is ended
		
		//brute-force
		for(int i=0;i<winningLines.size();i++)
		{
			
			positionTicTacToe p0 = winningLines.get(i).get(0);
			positionTicTacToe p1 = winningLines.get(i).get(1);
			positionTicTacToe p2 = winningLines.get(i).get(2);
			positionTicTacToe p3 = winningLines.get(i).get(3);
			
			int state0 = getStateOfPositionFromBoard(p0,board);
			int state1 = getStateOfPositionFromBoard(p1,board);
			int state2 = getStateOfPositionFromBoard(p2,board);
			int state3 = getStateOfPositionFromBoard(p3,board);
			
			//if they have the same state (marked by same player) and they are not all marked.
			if(state0 == state1 && state1 == state2 && state2 == state3 && state0!=0)
			{
				//someone wins
				p0.state = state0;
				p1.state = state1;
				p2.state = state2;
				p3.state = state3;
				
				//print the satisified winning line (one of them if there are several)
				p0.printPosition();
				p1.printPosition();
				p2.printPosition();
				p3.printPosition();
				return state0;
			}
		}
		for(int i=0;i<board.size();i++)
		{
			if(board.get(i).state==0)
			{
				//game is not ended, continue
				return 0;
			}
		}
		return -1; //call it a draw
	}

private static int getStateOfPositionFromBoard(positionTicTacToe position, java.util.List<positionTicTacToe> targetBoard)
	{
		//a helper function to get state of a certain position in the Tic-Tac-Toe board by given position TicTacToe
		int index = position.x*16+position.y*4+position.z;
		return targetBoard.get(index).state;
	}




	public static void main(String[]args){
		JFrame frame = new JFrame("TicTacToe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension DimMax = new Dimension(800,400);
		frame.setMaximumSize(DimMax);

		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		boardDisplay game = new boardDisplay();
		frame.getContentPane().add(game);
		frame.pack();
		frame.setVisible(true);
		game.run();
	}
	
}

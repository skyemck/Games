import java.util.*;
import java.util.Scanner;

public class humanTicTacToe{
	private int player;
	private int getStateOfPositionFromBoard(positionTicTacToe position, List<positionTicTacToe> board)
	{
		//a helper function to get state of a certain position in the Tic-Tac-Toe board by given position TicTacToe
		int index = position.x*16+position.y*4+position.z;
		return board.get(index).state;
	}
	private boolean checker(int x, int y, int z){
		if(x != 0 && x != 1 && x != 2 && x != 3){
			System.out.println("Out of bound x");
			return false;
		}
		if(y != 0 && y != 1 && y != 2 && y != 3){
			System.out.println("Out of bound y");
			return false;
		}
		if(z != 0 && z != 1 && z != 2 && z != 3){
			System.out.println("Out of bound z");
			return false;
		}
		return true;
	}

	public positionTicTacToe myAIAlgorithm(List<positionTicTacToe> board, int player)
	{
		positionTicTacToe myNextMove = new positionTicTacToe(0,0,0);
		int x, y, z;
		int ret = 0;
		do{
			if(ret != 0)
				System.out.println("Already taken position on board");
			do {
				System.out.println("Enter your x coordinate: ");
				Scanner sx = new Scanner(System.in);
				x = sx.nextInt();
				System.out.println("Enter your y coordinate: ");
				Scanner sy = new Scanner(System.in);
				y = sy.nextInt();
				System.out.println("Enter your z coordinate: ");
				Scanner sz = new Scanner(System.in);
				z = sz.nextInt();
			}while(!checker(x,y,z));
			myNextMove = new positionTicTacToe(x,y,z);
			ret = getStateOfPositionFromBoard(myNextMove,board);
		}while(ret != 0);
		return myNextMove;
	}

	public humanTicTacToe(int setPlayer)
	{
		player = setPlayer;
		
	}
}

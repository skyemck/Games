import java.util.*;
import java.util.concurrent.TimeUnit;

public class aiTicTacToe {

	private int player; //1 for player 1 and 2 for player 2
	private int diff;
	private List<positionTicTacToe> personalBoard;
	private List<List<positionTicTacToe>>  winningLines = initializeWinningLines(); 
	private int getStateOfPositionFromBoard(positionTicTacToe position, List<positionTicTacToe> board)
	{
		//a helper function to get state of a certain position in the Tic-Tac-Toe board by given position TicTacToe
		int index = position.x*16+position.y*4+position.z;
		return board.get(index).state;
	}
	public positionTicTacToe myAIAlgorithm(List<positionTicTacToe> board, int player)
	{
		//TODO: this is where you are going to implement your AI algorithm to win the game. The default is an AI randomly choose any available move.
		positionTicTacToe myNextMove = new positionTicTacToe(0,0,0);

		if(diff < 0){
			do
			{
				Random rand = new Random();
				int x = rand.nextInt(4);
				int y = rand.nextInt(4);
				int z = rand.nextInt(4);
				myNextMove = new positionTicTacToe(x,y,z);
				positionTicTacToe last = new positionTicTacToe(0,0,0);
				last = lastMin(board);
				if(last.state == 1){
					System.out.println("last resort was reached");
					myNextMove = last;
				}
			}while(getStateOfPositionFromBoard(myNextMove,board)!=0);
		}
		else{
			long startTime = System.nanoTime();

			myNextMove = nextMove(board,player);

			long endTime = System.nanoTime();

			long timeElapsed = endTime - startTime;

			System.out.println("Execution time in seconds: " + timeElapsed/1000000000);
		}
		myNextMove.printPosition();
		return myNextMove;
			
		
	}
	private List<List<positionTicTacToe>> initializeWinningLines()
	{
		//create a list of winning line so that the game will "brute-force" check if a player satisfied any 	winning condition(s).
		List<List<positionTicTacToe>> winningLines = new ArrayList<List<positionTicTacToe>>();
		
		//48 straight winning lines
		//z axis winning lines
		for(int i = 0; i<4; i++)
			for(int j = 0; j<4;j++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(i,j,0,-1));
				oneWinCondtion.add(new positionTicTacToe(i,j,1,-1));
				oneWinCondtion.add(new positionTicTacToe(i,j,2,-1));
				oneWinCondtion.add(new positionTicTacToe(i,j,3,-1));
				winningLines.add(oneWinCondtion);
			}
		//y axis winning lines
		for(int i = 0; i<4; i++)
			for(int j = 0; j<4;j++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(i,0,j,-1));
				oneWinCondtion.add(new positionTicTacToe(i,1,j,-1));
				oneWinCondtion.add(new positionTicTacToe(i,2,j,-1));
				oneWinCondtion.add(new positionTicTacToe(i,3,j,-1));
				winningLines.add(oneWinCondtion);
			}
		//x axis winning lines
		for(int i = 0; i<4; i++)
			for(int j = 0; j<4;j++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(0,i,j,-1));
				oneWinCondtion.add(new positionTicTacToe(1,i,j,-1));
				oneWinCondtion.add(new positionTicTacToe(2,i,j,-1));
				oneWinCondtion.add(new positionTicTacToe(3,i,j,-1));
				winningLines.add(oneWinCondtion);
			}
		
		//12 main diagonal winning lines
		//xz plane-4
		for(int i = 0; i<4; i++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(0,i,0,-1));
				oneWinCondtion.add(new positionTicTacToe(1,i,1,-1));
				oneWinCondtion.add(new positionTicTacToe(2,i,2,-1));
				oneWinCondtion.add(new positionTicTacToe(3,i,3,-1));
				winningLines.add(oneWinCondtion);
			}
		//yz plane-4
		for(int i = 0; i<4; i++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(i,0,0,-1));
				oneWinCondtion.add(new positionTicTacToe(i,1,1,-1));
				oneWinCondtion.add(new positionTicTacToe(i,2,2,-1));
				oneWinCondtion.add(new positionTicTacToe(i,3,3,-1));
				winningLines.add(oneWinCondtion);
			}
		//xy plane-4
		for(int i = 0; i<4; i++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(0,0,i,-1));
				oneWinCondtion.add(new positionTicTacToe(1,1,i,-1));
				oneWinCondtion.add(new positionTicTacToe(2,2,i,-1));
				oneWinCondtion.add(new positionTicTacToe(3,3,i,-1));
				winningLines.add(oneWinCondtion);
			}
		
		//12 anti diagonal winning lines
		//xz plane-4
		for(int i = 0; i<4; i++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(0,i,3,-1));
				oneWinCondtion.add(new positionTicTacToe(1,i,2,-1));
				oneWinCondtion.add(new positionTicTacToe(2,i,1,-1));
				oneWinCondtion.add(new positionTicTacToe(3,i,0,-1));
				winningLines.add(oneWinCondtion);
			}
		//yz plane-4
		for(int i = 0; i<4; i++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(i,0,3,-1));
				oneWinCondtion.add(new positionTicTacToe(i,1,2,-1));
				oneWinCondtion.add(new positionTicTacToe(i,2,1,-1));
				oneWinCondtion.add(new positionTicTacToe(i,3,0,-1));
				winningLines.add(oneWinCondtion);
			}
		//xy plane-4
		for(int i = 0; i<4; i++)
			{
				List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
				oneWinCondtion.add(new positionTicTacToe(0,3,i,-1));
				oneWinCondtion.add(new positionTicTacToe(1,2,i,-1));
				oneWinCondtion.add(new positionTicTacToe(2,1,i,-1));
				oneWinCondtion.add(new positionTicTacToe(3,0,i,-1));
				winningLines.add(oneWinCondtion);
			}
		
		//4 additional diagonal winning lines
		List<positionTicTacToe> oneWinCondtion = new ArrayList<positionTicTacToe>();
		oneWinCondtion.add(new positionTicTacToe(0,0,0,-1));
		oneWinCondtion.add(new positionTicTacToe(1,1,1,-1));
		oneWinCondtion.add(new positionTicTacToe(2,2,2,-1));
		oneWinCondtion.add(new positionTicTacToe(3,3,3,-1));
		winningLines.add(oneWinCondtion);
		
		oneWinCondtion = new ArrayList<positionTicTacToe>();
		oneWinCondtion.add(new positionTicTacToe(0,0,3,-1));
		oneWinCondtion.add(new positionTicTacToe(1,1,2,-1));
		oneWinCondtion.add(new positionTicTacToe(2,2,1,-1));
		oneWinCondtion.add(new positionTicTacToe(3,3,0,-1));
		winningLines.add(oneWinCondtion);
		
		oneWinCondtion = new ArrayList<positionTicTacToe>();
		oneWinCondtion.add(new positionTicTacToe(3,0,0,-1));
		oneWinCondtion.add(new positionTicTacToe(2,1,1,-1));
		oneWinCondtion.add(new positionTicTacToe(1,2,2,-1));
		oneWinCondtion.add(new positionTicTacToe(0,3,3,-1));
		winningLines.add(oneWinCondtion);
		
		oneWinCondtion = new ArrayList<positionTicTacToe>();
		oneWinCondtion.add(new positionTicTacToe(0,3,0,-1));
		oneWinCondtion.add(new positionTicTacToe(1,2,1,-1));
		oneWinCondtion.add(new positionTicTacToe(2,1,2,-1));
		oneWinCondtion.add(new positionTicTacToe(3,0,3,-1));
		winningLines.add(oneWinCondtion);	
		
		return winningLines;
		
	}
	public aiTicTacToe(int setPlayer,char difficulty)
	{
		player = setPlayer;
		switch (difficulty){
			case 'e':
				diff = -1;
				break;
			case 'm' :
				diff = 0;
				break;
			case 'x':
				diff = 4;
				break;
			case 'h' :
				diff = 2;
				break;
			default :
				diff = 1;
		}
		
	}

	public boolean winning(List<positionTicTacToe> board){
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
			if(state0 == state1 && state1 == state2 && state2 == state3 && state0!=0)
			{
				return true;
			}
		}
		return false;
	}

	public boolean draw(List<positionTicTacToe> board){
		for(int i=0;i<board.size();i++)
		{
			if(board.get(i).state==0)
			{
				//game is not ended, continue
				return false;
			}
		}
		return true; //call it a draw
	}

	private List<positionTicTacToe> deepCopyATicTacToeBoard(List<positionTicTacToe> board)
	{
		//deep copy of game boards
		List<positionTicTacToe> copiedBoard = new ArrayList<positionTicTacToe>();
		for(int i=0;i<board.size();i++)
		{
			copiedBoard.add(new positionTicTacToe(board.get(i).x,board.get(i).y,board.get(i).z,board.get(i).state));
		}
		return copiedBoard;
	}

	public int alphaBeta(positionTicTacToe position,List<positionTicTacToe> board, int player,int alpha,int beta,boolean max,int count,List<positionTicTacToe> plane){
		int sign = 1;
		if(!max){
			sign = -1;
		}
		for(int i = 0; i < board.size(); i++){
			if(board.get(i).x==position.x && board.get(i).y==position.y && board.get(i).z==position.z){
				if(board.get(i).state==0){
					board.get(i).state = player;
					break;
				}
			}
		}
		if(winning(board)){
				return 1000 * sign;
		}
		if(draw(board)){
			return 0;
		}
		for(int i = 0; i < plane.size(); i++){
			if(plane.get(i).x==position.x && plane.get(i).y==position.y && plane.get(i).z==position.z){
				if(plane.get(i).state==0){
					plane.get(i).state = player;
					break;
				}
			}
		}
		List<positionTicTacToe> moves = moveList(plane);
		int ret = 0;		
		positionTicTacToe curr = new positionTicTacToe(0,0,0);
		List<positionTicTacToe> copy = new ArrayList<>();
		List<positionTicTacToe> planeCopy = new ArrayList<>();
		int newplayer = 0;
		if(player == 1){
			newplayer = 2;
		}
		else {
			newplayer = 1;
		}
		int best = 0;
		if (count < diff || moves.isEmpty()){
			if(max){
				best = -1000;
				for(int i=0;i< plane.size();i++){
					int x = plane.get(i).x;
					int y = plane.get(i).y;
					int z = plane.get(i).z;
					curr = new positionTicTacToe(x,y,z);
					if(getStateOfPositionFromBoard(curr,board) == 0){
						copy = deepCopyATicTacToeBoard(board);
						planeCopy = deepCopyATicTacToeBoard(plane);
						int val = alphaBeta(curr,copy,newplayer,alpha,beta,false,count + 1,planeCopy);
						best = Math.max(best,val);
						alpha = Math.max(alpha,best);
						if(beta <= alpha)
							break;
					}
				}
			}	
			else{
				best = 1000;
				for(int i=0;i< board.size();i++){
					int x = board.get(i).x;
					int y = board.get(i).y;
					int z = board.get(i).z;
					curr = new positionTicTacToe(x,y,z);
					if(getStateOfPositionFromBoard(curr,board) == 0){
						copy = deepCopyATicTacToeBoard(board);
						planeCopy = deepCopyATicTacToeBoard(plane);
						int val= alphaBeta(curr,copy,newplayer,alpha,beta,true,count+1,planeCopy);
						best = Math.min(best,val);
						beta = Math.min(beta,best);
						if(beta <= alpha)
							break;
					}
				}
			}
		return best;
	}
	else{
		return evaluation(board,player);
	}

	}	

	public positionTicTacToe nextMove(List<positionTicTacToe> board, int player){
		positionTicTacToe ret = new positionTicTacToe(0,0,0);
		positionTicTacToe curr = new positionTicTacToe(0,0,0);
		List<positionTicTacToe> pxy = new ArrayList<>();
		List<positionTicTacToe> pyz = new ArrayList<>();
		List<positionTicTacToe> pxz = new ArrayList<>();
		List<positionTicTacToe> pdiag = new ArrayList<>();
		int temp,x,y,z,count;
		int score = -1000;
		List<positionTicTacToe> copy = new ArrayList<>();
		List<positionTicTacToe> planeCopy = new ArrayList<>();
		List<List<positionTicTacToe>> planes = planesFourteen(board);
		for(int i=0;i< 4;i++)
		{
			pxy = planes.get(i);
			for(int j = 0; j < pxy.size();j++){
				x = pxy.get(j).x;
				y = pxy.get(j).y;
				z = pxy.get(j).z;
				curr = new positionTicTacToe(x,y,z);
				if(getStateOfPositionFromBoard(curr,board) == 0){
					count = 3;
					copy = deepCopyATicTacToeBoard(board);
					planeCopy = deepCopyATicTacToeBoard(pxy);
					temp = alphaBeta(curr,copy,player,-10000,10000,true,0,planeCopy);

					
					copy = deepCopyATicTacToeBoard(board);
					planeCopy = deepCopyATicTacToeBoard(planes.get(4 + x));
					temp += alphaBeta(curr,copy,player,-10000,10000,true,0,planeCopy);

					copy = deepCopyATicTacToeBoard(board);
					planeCopy = deepCopyATicTacToeBoard(planes.get(8 + y));
					temp += alphaBeta(curr,copy,player,-10000,10000,true,0,planeCopy);

					if((i - x) == 0){
						count++;
						copy = deepCopyATicTacToeBoard(board);
						planeCopy = deepCopyATicTacToeBoard(planes.get(12));
						temp += alphaBeta(curr,copy,player,-1000,1000,true,0,planeCopy);
					}else if(((3-i)-x)==0){
						count++;
						copy = deepCopyATicTacToeBoard(board);
						planeCopy = deepCopyATicTacToeBoard(planes.get(13));
						temp += alphaBeta(curr,copy,player,-1000,1000,true,0,planeCopy);
					}
					if((x - y) == 0){
						count++;
						copy = deepCopyATicTacToeBoard(board);
						planeCopy = deepCopyATicTacToeBoard(planes.get(15));
						temp += alphaBeta(curr,copy,player,-1000,1000,true,0,planeCopy);
					}else if(((3-x)-y)==0){
						count++;
						copy = deepCopyATicTacToeBoard(board);
						planeCopy = deepCopyATicTacToeBoard(planes.get(14));
						temp += alphaBeta(curr,copy,player,-1000,1000,true,0,planeCopy);
					}
					temp = temp/count;
					if(temp > score){
						ret = new positionTicTacToe(x,y,z);
						score = temp;
						if(score == 1000)
							break;
						System.out.printf("current max ab score %d\n",score);
					/*}else if(temp == score){
						Random rand = new Random();
						int turn = rand.nextInt(2)+1;
						if(turn == 2){
							ret = new positionTicTacToe(x,y,z);
							score = temp;
							//System.out.println("Randomized");
						}*/
					}
				}
			}
		}
		positionTicTacToe last = new positionTicTacToe(0,0,0);
		if(score < 1000){
			last = lastMin(board);
			if(last.state == 1){
				//System.out.println("last resort was reached");
				ret = last;
			}
		}	
		return ret;
	}


private int evaluation(List<positionTicTacToe> board,int player){
	int ret;
	int score = 0;
	int opp = 0;
	if(player==1){
		opp = 2;
	}else {
		opp = 1;
	}
	for(int i=0;i<winningLines.size();i++){
		ret = 0;
		positionTicTacToe p0 = winningLines.get(i).get(0);
		positionTicTacToe p1 = winningLines.get(i).get(1);
		positionTicTacToe p2 = winningLines.get(i).get(2);
		positionTicTacToe p3 = winningLines.get(i).get(3);
			
		int state0 = getStateOfPositionFromBoard(p0,board);
		int state1 = getStateOfPositionFromBoard(p1,board);
		int state2 = getStateOfPositionFromBoard(p2,board);
		int state3 = getStateOfPositionFromBoard(p3,board);
		if(state0 == player){
			ret = 1;
		}
		else if(state0 == opp){
			ret = -1;
		}

		if(state1 == player){
			if(ret == 1){
				ret = 5;
			} else if (ret == -1){
				ret = 0;
			}else {
				ret = 1;
			}
		}else if(state1 == opp) {
			if(ret == -1){
				ret = -5;
			}else if(ret == 1){
				ret = 0;
			}else {
				ret = -1;
			}
		}

		if(state2 == player){
			if(ret == 1){
				ret = 5;
			}else if(ret == -1){
				ret = 0;
			}else if(ret == -5){
				ret = 0;
			}else if(ret == 5){
				ret = 10;
			}
			else {
				ret = 1;
			}
		}else if(state2 == opp) {
			if(ret == -1){
				ret = -5;
			}else if(ret == 1){
				ret = 0;
			}else if(ret == -5){
				ret = -10;
			}else if(ret == 5){
				ret = 0;
			}
			else {
				ret = -1;
			}
		}

		if(state3 == player){
			if(ret > 0){
				ret *= 10;
			}else if(ret < 0){
				if (ret == -1){
					ret = 0;
				}else if(ret == -5){
					ret = 0;
				}else if(ret ==-10){
					ret = 0;
				}
			}else {
				ret = 1;
			}
		} else if(state3 == opp){
			if(ret < 0){
				ret *= 10;
			}else if(ret > 0){
				if (ret == 1){
					ret = 0;
				}else if(ret == 5){
					ret = 0;
				}else if(ret == 10){
					ret = 0;
				}
			}else {
				ret = -1;
			}
		}
		score += ret;		
	}
	return score;
}

private List<List<positionTicTacToe>> planesFourteen(List<positionTicTacToe> board){
	List<positionTicTacToe> first = new ArrayList<>();
	List<positionTicTacToe> second = new ArrayList<>();
	List<positionTicTacToe> third = new ArrayList<>();
	List<positionTicTacToe> fourth = new ArrayList<>();
	List<positionTicTacToe> fifth = new ArrayList<>();
	List<positionTicTacToe> sixth = new ArrayList<>();
	List<positionTicTacToe> seventh = new ArrayList<>();
	List<positionTicTacToe> eigth = new ArrayList<>();
	List<positionTicTacToe> ninth = new ArrayList<>();
	List<positionTicTacToe> tenth = new ArrayList<>();
	List<positionTicTacToe> once = new ArrayList<>();
	List<positionTicTacToe> doce = new ArrayList<>();
	List<positionTicTacToe> threce = new ArrayList<>();
	List<positionTicTacToe> court = new ArrayList<>();
	List<positionTicTacToe> quinc = new ArrayList<>();
	List<positionTicTacToe> seis = new ArrayList<>();
	positionTicTacToe temp = new positionTicTacToe(0,0,0);
	int state;
	for(int i = 0; i < 4; i++){
		for(int j = 0; j< 4; j++){
			//xy plane
			temp = new positionTicTacToe(i,j,0);
			state = getStateOfPositionFromBoard(temp,board);
			first.add(new positionTicTacToe(i,j,0,state));
			temp = new positionTicTacToe(i,j,1);
			state = getStateOfPositionFromBoard(temp,board);
			second.add(new positionTicTacToe(i,j,1,state));
			temp = new positionTicTacToe(i,j,2);
			state = getStateOfPositionFromBoard(temp,board);
			third.add(new positionTicTacToe(i,j,2,state));
			temp = new positionTicTacToe(i,j,3);
			state = getStateOfPositionFromBoard(temp,board);
			fourth.add(new positionTicTacToe(i,j,3,state));

			//yz plane
			temp = new positionTicTacToe(0,j,3-i);
			state = getStateOfPositionFromBoard(temp,board);
			fifth.add(new positionTicTacToe(0,j,3-i,state));
			temp = new positionTicTacToe(1,j,3-i);
			state = getStateOfPositionFromBoard(temp,board);
			sixth.add(new positionTicTacToe(1,j,3-i,state));
			temp = new positionTicTacToe(2,j,3-i);
			state = getStateOfPositionFromBoard(temp,board);
			seventh.add(new positionTicTacToe(2,j,3-i,state));
			temp = new positionTicTacToe(3,j,3-i);
			state = getStateOfPositionFromBoard(temp,board);
			eigth.add(new positionTicTacToe(3,j,3-i,state));

			//xz plane
			temp = new positionTicTacToe(i,0,j);
			state = getStateOfPositionFromBoard(temp,board);
			ninth.add(new positionTicTacToe(i,0,j,state));
			temp = new positionTicTacToe(i,2,j);
			state = getStateOfPositionFromBoard(temp,board);
			tenth.add(new positionTicTacToe(i,1,j,state));
			temp = new positionTicTacToe(i,2,j);
			state = getStateOfPositionFromBoard(temp,board);
			once.add(new positionTicTacToe(i,2,j,state));
			temp = new positionTicTacToe(i,3,j);
			state = getStateOfPositionFromBoard(temp,board);
			doce.add(new positionTicTacToe(i,3,j,state));

			//diagnol first is top to bottom then its bottom to top from xy z=0 perspective
			temp = new positionTicTacToe(3-i,j,3-i);
			state = state = getStateOfPositionFromBoard(temp,board);
			threce.add(new positionTicTacToe(3-i,j,3-i,state));
			temp = new positionTicTacToe(i,j,3-i);
			state = state = getStateOfPositionFromBoard(temp,board);
			court.add(new positionTicTacToe(i,j,3-i,state));
			temp = new positionTicTacToe(3-i,i,j);
			state = state = getStateOfPositionFromBoard(temp,board);
			quinc.add(new positionTicTacToe(3-i,i,j,state));
			temp = new positionTicTacToe(i,i,j);
			state = state = getStateOfPositionFromBoard(temp,board);
			seis.add(new positionTicTacToe(i,i,j,state));
		}
	}

	List<List<positionTicTacToe>> ret = new ArrayList<List<positionTicTacToe>>();
	ret.add(first);
	ret.add(second);
	ret.add(third);
	ret.add(fourth);
	ret.add(fifth);
	ret.add(sixth);
	ret.add(seventh);
	ret.add(eigth);
	ret.add(ninth);
	ret.add(tenth);
	ret.add(once);
	ret.add(doce);
	ret.add(threce);
	ret.add(court);
	ret.add(quinc);
	ret.add(seis);
	return ret;
}

private List<positionTicTacToe> moveList(List<positionTicTacToe> plane){
	List<positionTicTacToe> moveList = new ArrayList<positionTicTacToe>();
	int x = plane.get(5).x;
	int y = plane.get(5).y;
	int z = plane.get(5).z;
	if(plane.get(5).state ==0)
		moveList.add(new positionTicTacToe(x,y,z));

	x = plane.get(9).x;
	y = plane.get(9).y;
	z = plane.get(9).z;
	if(plane.get(9).state ==0)
		moveList.add(new positionTicTacToe(x,y,z));
	
	x = plane.get(6).x;
	y = plane.get(6).y;
	z = plane.get(6).z;
	if(plane.get(6).state ==0)
		moveList.add(new positionTicTacToe(x,y,z));
	
	x = plane.get(1).x;
	y = plane.get(1).y;
	z = plane.get(1).z;
	if(plane.get(1).state ==0)
		moveList.add(new positionTicTacToe(x,y,z));

	x = plane.get(4).x;
	y = plane.get(4).y;
	z = plane.get(4).z;
	if(plane.get(4).state ==0)
		moveList.add(new positionTicTacToe(x,y,z));

	x = plane.get(0).x;
	y = plane.get(0).y;
	z = plane.get(0).z;
	if(plane.get(0).state ==0)
		moveList.add(new positionTicTacToe(x,y,z));

	x = plane.get(10).x;
	y = plane.get(10).y;
	z = plane.get(10).z;
	if(plane.get(10).state ==0)
		moveList.add(new positionTicTacToe(x,y,z));

	x = plane.get(15).x;
	y = plane.get(15).y;
	z = plane.get(15).z;
	if(plane.get(15).state ==0)
		moveList.add(new positionTicTacToe(x,y,z));

	x = plane.get(7).x;
	y = plane.get(7).y;
	z = plane.get(7).z;
	if(plane.get(7).state ==0)
		moveList.add(new positionTicTacToe(x,y,z));	

	x = plane.get(13).x;
	y = plane.get(13).y;
	z = plane.get(13).z;
	if(plane.get(13).state ==0)
		moveList.add(new positionTicTacToe(x,y,z));

	x = plane.get(2).x;
	y = plane.get(2).y;
	z = plane.get(2).z;
	if(plane.get(2).state ==0)
		moveList.add(new positionTicTacToe(x,y,z));

	x = plane.get(8).x;
	y = plane.get(8).y;
	z = plane.get(8).z;
	if(plane.get(8).state ==0)
		moveList.add(new positionTicTacToe(x,y,z));

	x = plane.get(3).x;
	y = plane.get(3).y;
	z = plane.get(3).z;
	if(plane.get(3).state ==0)
		moveList.add(new positionTicTacToe(x,y,z));

	x = plane.get(12).x;
	y = plane.get(12).y;
	z = plane.get(12).z;
	if(plane.get(12).state ==0)
		moveList.add(new positionTicTacToe(x,y,z));

	x = plane.get(11).x;
	y = plane.get(11).y;
	z = plane.get(11).z;
	if(plane.get(11).state ==0)
		moveList.add(new positionTicTacToe(x,y,z));

	x = plane.get(14).x;
	y = plane.get(14).y;
	z = plane.get(14).z;
	if(plane.get(14).state ==0)
		moveList.add(new positionTicTacToe(x,y,z));

	return moveList;
}

private positionTicTacToe lastMin(List<positionTicTacToe> board){
	positionTicTacToe ret = new positionTicTacToe(0,0,0,-1);
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
			if(state0 == state1 && state1 == state2 && state3 != state0 && state3 == 0){
				//System.out.println("Changed");
				ret = new positionTicTacToe(p3.x,p3.y,p3.z,1);
				break;
			}else if(state0 == state1 && state1 == state3 && state2 != state0 && state2 == 0){
				//System.out.println("Changed");
				ret = new positionTicTacToe(p2.x,p2.y,p2.z,1);
				break;
			}else if(state0 == state2 && state2 == state3 && state1 != state0 && state1 == 0){
				//System.out.println("Changed");
				ret = new positionTicTacToe(p1.x,p1.y,p1.z,1);
				break;
			}else if(state1 == state2 && state2 == state3 && state2 != state0 && state0 == 0){
				//System.out.println("Changed");
				ret = new positionTicTacToe(p0.x,p0.y,p0.z,1);
				break;
			}
		}
		return ret;
	}

}

// required imports
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


/**
 *  Inspector class 
 *   - it will help us to track down Crime Master GOGO's current location
 * 
 */
class Inspector {
	
	/**
     *  Point class 
     *  - it will help us to hold 2D point (x,y)
     * 
     */
	private class Point {
		
		private int x; // holds x value of the Point
        private int y; // holds y value of the Point
		
        /**
         *  Parameterized Constructor
         * @param x : int
         * @param y : int
         */
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
        // getter for x
		public int getX() {
			return x;
		}
		
        // getter for y
		public int getY() {
			return y;
		}
		
	}
	
	
	private int caseNumber; // to store current case number for robbery
	
	private int width; // to store width of the city
	
	private int height; // to store height of the city
	
	private int time; // to store length of time during which the city is locked
	

	private List<List<Point>> position; // to track movement(visited points) of Crime Master GOGO

	
    /**
     * to mark presence of Crime Master GOGO at any point of the city at any specific time
     * 
     * it can store 3 values : -1 (no one visited that point)
     *                          0 (police has visited that point)
     *                          1 (Crime Master GOGO has visited that point)
     */
	private int[][][] visited; 
	
	
    // possible movements of Crime Master GOGO in city
	private int[] dw = {0, 0, 1, -1, 0};
	private int[] dh = {1, -1, 0, 0, 0};


	private boolean gogoEscaped; // to track if Crime Master GOGO escaped or not
	
	
	/**
     * Parameterized Constructor
     * 
     * @param width : int
     * @param height : int
     * @param time : int
     * @param caseNumber : int
     */
	Inspector (int width, int height, int time, int caseNumber) {

        // initialising values

		this.width = width;
		
		this.height = height;
		
		this.time = time;
		
		this.caseNumber = caseNumber;



        // initialising position 

        position = new ArrayList<>();
		
		for(int i = 0; i <= time ; ++i) {
			
			position.add(new ArrayList<Point>());
			
		}		
		
		
        // initialising visited

		visited = new int[width+1][height+1][time+1];
				
		for(int i = 0; i <= width; ++i) {
			
			for(int j = 0; j <= height; ++j) {
				
				Arrays.fill(visited[i][j], -1); // marks all points as non visited by anyone
				
			}
		}
		
		gogoEscaped = true; // initially we are assuming that Crime Master GOGO has escaped from the city
		
	}
	
	
    /**
     *  to take messages from from different searching teams
     *   who have searched for Crime Master GOGO in a rectangle area
     * 
     * @param scanner : Scanner : to take inputs
     */
	public void takeMessage (Scanner scanner) {
		
		int messageCount = scanner.nextInt(); // number of searching messages received
		
		
		for(int count = 1; count <= messageCount; ++count) {
			
			int t = scanner.nextInt(); // time-stamp for the searching event
			

            // input left top point of the searching area

			int left = scanner.nextInt();
			
			int top = scanner.nextInt();
			


            // input right bottom point of the searching area

			int right = scanner.nextInt();
			
			int bottom = scanner.nextInt();
			

            
            // marking all points in the rectangle = 0 for that time-stamp
            // as police has searched those points but didn't found Crime Master GOGO

			for(int w = left; w <= right; ++w) {
				
				for(int h = top; h <= bottom; ++h) {
					
					visited[w][h][t] = 0;
					
				}
			}
			
		}
		
	}
    

    /**
     *  to search for Crime Master GOGO
     *    based on the given inputs
     * 
     */
    public void searchGOGO () {
		
        /**
         *  if any point in the city has not visited yet
		 *  then we will do depth first search on that point
		 *  and try to find if Crime Master GOGO can leave the city
		 * 	from that point or not in given time limit 
		 *  before all the out going roads gets blocked
		 *  
         */

		for(int w = 1; w <= width; ++w) {
			
			for(int h = 1; h <= height; ++h) {
				
				if( visited[w][h][1] == -1) {
					
					depthFirstSearch(w, h, 1);
				
				}
			}
		}
		
	}
	
	
	/**
	 * do depth first search 
	 * on the city points based current possition and given time stamp
	 * 
	 * @param w : int : value of x axis of current possition
	 * @param h : int : value of y axis of current possition
	 * @param t : int : value of current time-stamp
	 * @return
	 */
	private int depthFirstSearch (int w, int h, int t) {
		
		// if (w,h) is already visited on time t
		if( visited[w][h][t] != -1) return visited[w][h][t];
		
		/**
		 * if we can reach the last timestamp
		 * that means there is atleast one situation when
		 * Crime Master GOGO could not go outside the city in the given time
		 */
		if( t == time ) {
			
			gogoEscaped = false;
			
			visited[w][h][t] = 1; // Crime Master GOGO can reach this position
			
			position.get(t).add(new Point(w,h)); // tracked the location
			
			return 1;
			
		}
		
		
		visited[w][h][t] = 0; // marked as visited but not confirmed if GOGO has visited it 
		
		
		for(int i = 0; i < 5; ++i) {
			
			int ww = w + dw[i];
			int hh = h + dh[i];
			
			if(ww >= 1 && ww <= width && hh >= 1 && hh <= height) {
				
				if( depthFirstSearch(ww, hh, t+1) == 1 ) {
					
					visited[w][h][t] = 1; // Crime Master GOGO can reach this position
					
				}
				
			}
			
		}
		
		
		// if Crime Master GOGO can reach this position
		//  then track the location
		if( visited[w][h][t] == 1) {
			
			position.get(t).add(new Point(w,h));
			
		}
		
		// returns reachable status of the position(w,h) at time t
		return visited[w][h][t];
		
	}
	
	
	/**
	 *  it shows all the required output based on the searchings
	 * 
	 */
	public void showStats () {
		
		System.out.println("Robbery #" + caseNumber + ":");
		
		// Crime Master GOGO successfully escapped from the city
		if( gogoEscaped ) {
			
			System.out.println("Crime Master Gogo has escaped.");
			
		}
		else {
			
			boolean notKnown = true;
			
			for(int t = 1; t <= time; ++t) {

				// if one time stamp has more than one position,
				// then we cann't determine which one gogo has choosed
				// so if and only if one point is there,
				// then we can say gogo's position
				
				if(position.get(t).size() == 1) {
					
					notKnown = false;
					
					Point p = position.get(t).get(0);
					
					System.out.println("Time step " + t + ": Crime Master Gogo has been at " + p.getX() + "," + p.getY() + ".");
					
				}
			}
			
			// if no time stamp has exactly one location
			// that mean police can not track gogo's movement
			if( notKnown ) {
				
				System.out.println("Nothing known.");
			}
			
		}
		
		System.out.println();
		
	}
	
}



public class CrimeMaster {
	
	public static void main (String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		int width, height, time;
		
		int caseNumber = 0;
		
		
		while(true) {
			
			width = scanner.nextInt();
			
			height = scanner.nextInt();
			
            time = scanner.nextInt();
			

			// as per input if width = height = time = 0
			// then no furthur execution will happen
			if( width == 0 && height == 0 && time == 0 ) {
				break;
		    }
		  
			// new case
		    caseNumber++;
		  
		  
		    Inspector robostop = new Inspector( width, height, time, caseNumber); // Inspector Object
		  
		 
		    robostop.takeMessage(scanner); // receive serching messages
		  
		  
		    robostop.searchGOGO(); // search for Crime Master GOGO
		  
		  
		    robostop.showStats(); // print stats of searching
		  
		}
		
	}
	  
}
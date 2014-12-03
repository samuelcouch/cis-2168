import java.util.*;
import java.awt.Point;
import java.util.LinkedList;

public class YourMazeWithPath2
{       
    //Using InputGraphicMaze7 because it scales gracefully
    private InputGraphicMaze2 maze;
    private int R, C;
    private boolean[][] visited;

    public YourMazeWithPath2() 
    {       
       maze = new InputGraphicMaze2();
       R=maze.Rows(); 
       C=maze.Cols();
       //Keep track of where we've visited
       visited = new boolean[R][C]; //Initializes to false by default
       LinkedList<Point> Path = new LinkedList<Point>();
       CreatePath(maze, 1, 1, R, C, Path);
       maze.showPath(Path);
    }

    // Creates the path through maze, starting at cell (srow, scol)
    // and ending at cell (erow and ecol),  in L
    public boolean CreatePath(InputGraphicMaze2 maze,      
      int srow, int scol, int erow, int ecol, LinkedList<Point> L){
        System.out.printf("srow = %d scol = %d erow = %d ecol = %d\n", srow, scol, erow, ecol);
        visited[srow-1][scol-1] = true;
        L.push(new Point(srow, scol));
        
        if(srow == erow && scol == ecol) 
            return true;
       
       if(scol > 1 && maze.can_go(srow, scol, 'L') && !visited[srow-1][scol-2] &&
               CreatePath(maze, srow, scol-1, erow, ecol, L)) 
            return true;
        
       if(scol < ecol && maze.can_go(srow, scol, 'R') && !visited[srow-1][scol] &&
                CreatePath(maze, srow, scol+1, erow, ecol, L)) 
           return true;
       
       if(srow > 1 && maze.can_go(srow, scol, 'U') && !visited[srow-2][scol-1] &&
                CreatePath(maze, srow-1, scol, erow, ecol, L)) 
            return true;
       
        if(srow < erow && maze.can_go(srow, scol, 'D') && !visited[srow][scol-1] &&
                CreatePath(maze, srow+1, scol, erow, ecol, L)) 
            return true;
        
        //Dead end, pop it off the path list
        L.pop(); 
        return false;
    }

    public static void main(String[] args){
        new YourMazeWithPath2();
    }
}
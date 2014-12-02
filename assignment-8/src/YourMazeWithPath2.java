import java.util.*;
import java.awt.Point;
import java.util.LinkedList;

public class YourMazeWithPath2
{       
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
        //So we can follow the DFS path
        System.out.printf("srow = %d scol = %d erow = %d ecol = %d\n", srow, scol, erow, ecol);
        
        //mark current as visited;
        //visit all of current's unvisited neighbors by calling CreatePath(neighbors)
        
        //Push the point we're at onto the stack
        L.push(new Point(srow, scol));
        
        //Mark it as visited right away
        visited[srow-1][scol-1] = true;        
        
        if(srow == erow && scol == ecol)
            //We're at the exit
            return true;

        if(scol < C 
                && !visited[srow-1][scol] 
                && maze.can_go(srow, scol, 'R') 
                && CreatePath(maze, srow, scol+1, erow, ecol, L))
            //scol < C: We're within the bounds of columns
            //!visited[srow-1][scol]: We haven't visited upwards cell yet
            //maze.can_go(srow, scol, 'R'): Right is not a wall
            //CreatePath(maze, srow, scol+1, erow, ecol, L)): Recursively 
            //search to the right
            return true;
        
        if(srow < R 
                && !visited[srow][scol-1] 
                && maze.can_go(srow, scol, 'D') 
                && CreatePath(maze, srow+1, scol, erow, ecol, L))
            //srow < R: We're within the bounds of rows
            //!visited[srow][scol-1]: We haven't visited to the left
            //maze.can_go(srow, scol, 'D'): Down is not a wall
            //CreatePath(maze, srow+1, scol, erow, ecol, L)): Recursively 
            //search down
            return true;
        
        if(scol > 1 
                && !visited[srow-1][scol-2] 
                && maze.can_go(srow, scol, 'L') 
                && CreatePath(maze, srow, scol-1, erow, ecol, L))
            //scol > 1: It isn't the first call
            //!visited[srow-1][scol-2]: We haven't visited up one row, to the left
            //maze.can_go(srow, scol, 'L'): Left is not a wall
            //CreatePath(maze, srow, scol-1, erow, ecol, L)): Recursively 
            //search to the left
            return true;
        
        if(srow > 1 
                && !visited[srow-2][scol-1] 
                && maze.can_go(srow, scol, 'U') 
                && CreatePath(maze, srow-1, scol, erow, ecol, L))
            //srow > 1: It isn't the first call
            //!visited[srow-2][scol-1]: Haven't visited the first row, one left
            //maze.can_go(srow, scol, 'U'): Up isn't a wall
            //CreatePath(maze, srow-1, scol, erow, ecol, L)): Recursively 
            //search up
            return true;
        
        //If we don't meet any of the above criteria, pop off the point from 
        //the stack because it isn't part of our path
        L.pop(); 
        return false;
    }

    public static void main(String[] args){
        new YourMazeWithPath2();
    }
}
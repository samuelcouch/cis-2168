import java.util.*;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Deque;

public class MazePath
{       
    private InputGraphicMaze7 maze;
    private int R, C; 

    public MazePath() 
    {       
        // an R rows x C columns maze
        maze = new InputGraphicMaze7();
        R=maze.Rows(); C=maze.Cols();  
        // Path holds the cells of the path
        LinkedList<Point> Path = new LinkedList<Point>();
        // Create the path
        CreatePath(maze, 1, 1, R, C, Path);
        // show the path in the maze
        maze.showPath(Path);
    }

    // Creates the path through maze, starting at cell (srow, scol)
    // and ending at cell (erow and ecol),  in L
    public boolean CreatePath(InputGraphicMaze7 maze,
            int srow, int scol, int erow, int ecol, LinkedList<Point> L)
    {
        Deque<int[]> stack = new LinkedList<int[]>();
        int crow = srow;
        int ccol = scol;
        int cdir = 0;
        int pdir = -1;

        char dirs[] = new char[]{'R','D','L','U'};
        while(crow != erow || ccol != ecol) {
            if(cdir==0) {
                System.out.println(crow+","+ ccol+","+cdir+","+pdir);
                if(ccol<C && maze.can_go(crow, ccol, dirs[cdir]) && pdir!=cdir) {
                    System.out.println("first");
                    stack.addFirst(new int[]{crow,ccol,cdir,pdir});
                    pdir=(cdir+2)%4;
                    ccol++; cdir = 0;
                } else cdir++;
            }
            if(cdir==1) {
                System.out.println(crow+","+ ccol+","+cdir+","+pdir);
                if(crow<R && maze.can_go(crow, ccol, dirs[cdir]) && pdir!=cdir) {
                    System.out.println("second");
                    stack.addFirst(new int[]{crow,ccol,cdir,pdir});
                    pdir=(cdir+2)%4;
                    crow++; cdir = 0;
                } else cdir++;
            }
            if(cdir==2) {
                System.out.println(crow+","+ ccol+","+cdir+","+pdir);
                if(ccol>1 && maze.can_go(crow, ccol, dirs[cdir]) && pdir!=cdir) {
                    System.out.println("third");
                    stack.addFirst(new int[]{crow,ccol,cdir,pdir});
                    pdir=(cdir+2)%4;
                    ccol--; cdir = 0;
                } else cdir++;
            }
            if(cdir==3) {
                System.out.println(crow+","+ ccol+","+cdir+","+pdir);
                if(crow > 1 && maze.can_go(crow, ccol, dirs[cdir]) && pdir!=cdir) {
                    System.out.println("fourth");
                    stack.addFirst(new int[]{crow,ccol,cdir,pdir});
                    pdir=(cdir+2)%4;
                    crow--; cdir = 0;
                } else cdir++;
            }
            if(cdir==4) {
                if(stack.size() == 0) break;
                int[] tmp = stack.removeFirst();
                crow = tmp[0]; ccol = tmp[1]; cdir = 1+tmp[2]; pdir = tmp[3];
                System.out.println("pop:"+crow+","+ ccol+","+cdir+","+pdir);
            }

        }

        if(crow != erow || ccol != ecol) {
            System.err.print("Could not find path");
            return false;
        }

        L.add(new Point(crow, ccol));
        for(int i=stack.size(), data[]; i>0; i--) {
            data = stack.removeFirst();
            L.add(new Point(data[0], data[1]));
        }
        return true;
    }

    public static void main(String[] args)
    {new MazePath();}
}

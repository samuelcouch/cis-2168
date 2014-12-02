//"Centers" the maze.
//Allows clicking cells(they turn blue, red, then white) 
//to experiment for a path. Allowsfor showing the path.
import java.awt.*;
import java.awt.event.*; 
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import java.awt.Point;
import java.util.LinkedList;
import javax.swing.JButton;

public class InputGraphicMaze7 extends JFrame  
{
    Mazepanel mazepanel;
    private int M, N, BH, BW, H, W; 
    //JScrollPane scrolledmaze;


    public InputGraphicMaze7()  
    {
        super("A MAZE");
 	String integers=JOptionPane.showInputDialog(
                "Enter #rows and #columns.");
	Scanner in=new Scanner(integers); 
        M=in.nextInt(); N=in.nextInt(); 
        mazepanel=new Mazepanel(); 
        setLayout(new BorderLayout());
        add(mazepanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	JButton button=new JButton("Show Path");
	add(button, BorderLayout.NORTH);
	ActionListener buttonlistener=new ShowPathListener();
	button.addActionListener(buttonlistener); 
        JScrollPane scrolledmaze=new JScrollPane(mazepanel);
        add(scrolledmaze);
        setSize(1200, 800);       
        setVisible(true);        
    }//end of constructor

    private class ShowPathListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
    	{
           mazepanel.show=1; setTitle("A MAZE WITH PATH"); repaint();
        }
    }

    public int Rows() {return M;}
    public int Cols() {return N;}
    public boolean can_go(int i, int j, char c)
    {
        int D=M*(N-1); boolean result=false; //int K=N*(M-1)+(N-1)*M;

        if (c=='U') result=!mazepanel.status[D+(i-2)*N+j];
        if (c=='R') result=!mazepanel.status[(i-1)*(N-1)+j];
        if (c=='D') result=!mazepanel.status[D+(i-1)*N+j];
        if (c=='L') result=!mazepanel.status[(i-1)*(N-1)+j-1];
        return result;
    }

    public void showPath(LinkedList<Point> P) {
        mazepanel.Path=P; repaint();
    }

    private class Mazepanel extends JPanel 
    {
	private Intcoll[] C; 
    	private Line2D Top, Bottom, Left, Right; 
    	private int[] Line;  
    	private int show, delta, startH, startW;;
    	private Stroke drawingStroke=new BasicStroke(2);
        private boolean[] status;
        private LinkedList<Node> L;
        private LinkedList<Point> Path; 


        private Mazepanel() 
        {
           setPreferredSize(new Dimension(N*17, M*17));  
	   C=new Intcoll[N*M+1]; int k; 
	   for (k=1; k<=M*N; k++){
                  C[k]=new Intcoll(M*N+1); 
                  C[k].insert(k);
           }
           show=0; delta=15; startH=M; startW=N-10;
         
           Top=new Line2D.Double(startW+delta, startH, startW+N*delta, startH);
	   Bottom=new Line2D.Double(startW, startH+M*delta,startW+(N-1)*delta, 
         	startH+M*delta);
	   Left=new Line2D.Double(startW, startH, startW, 
		startH+M*delta);
	   Right=new Line2D.Double(startW+N*delta, startH, startW+N*delta, 
		startH+M*delta);
           int i=0, j=0; int size=M*(N-1)+(M-1)*N+1;  
           //Add all internal lines
           Line=new int[size]; status=new boolean[size];
           for (k=1; k<size; k++) {Line[k]=k; status[k]=true;}
           // Randomly generate the next line to remove if its adjacent
           // cells are not already connected
	   Random gen=new Random(); int end=M*(N-1)+(M-1)*N; int S=1; 
           int cell1, cell2; int temp; int count=0, a, b, slot; 
           //remove enough lines to generate the maze  
           while (count<M*N-1)
	   {   	       
               slot=gen.nextInt(end)+1; k=Line[slot];
               int D=M*(N-1);
               if (k<=D)
               {               
                  i=(k-1)/(N-1)+1;j=k-(i-1)*(N-1);
                  cell1=(i-1)*N+j; cell2=cell1+1;
               }
               else 
               {
                  int K=k-D; i=(K-1)/N+1; j=K-N*(i-1);
                  cell1=(i-1)*N+j; cell2=cell1+N;
               } 
               //Find a and b - the sets of adjacent cells cell1 and cell2
	       a=1; while (!C[a].belongs(cell1)) a++; 
	       b=1; while (!C[b].belongs(cell2)) b++;
               //if not in same set - take the union of the sets
               //and remove the line between the two cells
	       if (a!=b)
   	       {
	          if (a<b) {union(C[a], C[b], C); C[b]=C[M*N-count];}
                  else {union(C[b], C[a], C); C[a]=C[M*N-count];}
		  status[k]=false; count++; Line[slot]=Line[end]; end--;
               }
	
           } //end of while 
           L=new LinkedList<Node>();
           SlotMouseListener slotlistener=new SlotMouseListener();
           addMouseListener(slotlistener);
      }    
  
       public void paintComponent(Graphics g) 
       {
           Graphics2D graph = (Graphics2D) g;
           graph.clearRect(0,0,getWidth(),getHeight());
           graph.setStroke(drawingStroke);
           graph.setPaint(Color.red);

           graph.draw(Top); graph.draw(Bottom); 
           graph.draw(Left); graph.draw(Right); 
		
           int K=N*(M-1)+(N-1)*M; int D=M*(N-1), i, j;
           for (int s=1; s<=K-M*N+1; s++)
           {
              int k=Line[s], kk; Line2D line;
              if (k<=D)
              {
                  i=(k-1)/(N-1)+1; j=k-(i-1)*(N-1);
                  line=new Line2D.Double(startW+j*delta, 
		  startH+(i-1)*delta, startW+j*delta, startH+i*delta);
              }
              else
              {
                  kk=k-D; i=(kk-1)/N+1; j=kk-N*(i-1);
                  line=new Line2D.Double(startW+(j-1)*delta, startH+i*delta, startW+j*delta, startH+i*delta);
	      }
              graph.setPaint(Color.black); graph.draw(line);       
           }
           if (show==1)
           {
              L.clear(); graph.setPaint(Color.green);  
              Iterator I=Path.iterator();
              while (I.hasNext())
              {
                 Point p=(Point) I.next(); 
		 i=(int) p.getX(); j=(int) p.getY();
                 graph.fillRect(startW+(j-1)*delta+1, 
		    startH+(i-1)*delta+1, delta-2, delta-2);
              }
           }
           else
           {
              Iterator J=L.iterator();
              while (J.hasNext())
              {
  	         Node n=(Node) J.next(); 
                 Point q=n.p; i=(int) 
                 q.getX(); j=(int) q.getY();             
                 Color C = n.c; graph.setPaint(C); 
                 graph.fillRect(startW+(j-1)*delta+1, 
		    startH+(i-1)*delta+1, delta-2, delta-2);
              }
          }
      }

      class SlotMouseListener implements MouseListener
      {
          public void mouseClicked(MouseEvent e) {
}

   	  public void mouseEntered(MouseEvent e) {
}
        
        
   	  public void mouseExited(MouseEvent e) {
}
        
        
   	  public void mousePressed(MouseEvent e) 
   	  {
           
             // get mouse position
            
             int x=e.getX()-startW; int y=e.getY()-startH;
            
             int col=1+(int)Math.floor(x/delta);
 
             int row=1+(int)Math.floor(y/delta);
  
	     if ((row>0)&&(row<=M)&&(col>0)&&(col<=N))
             { 
                Node sred=new Node(row, col, Color.red); 
                Node sblue=new Node(row, col, Color.blue);
                int i, j, h=-1; boolean found=false; Node nn=null; 
                Iterator J=L.iterator();
                while ((J.hasNext())&&(!found))
                {
                   h++; Node n=(Node) J.next(); 
                   Point q = n.p; i=(int) q.getX(); j=(int) q.getY();             
                   Color C = n.c; 
                   if (q.equals(sred.p)) {found=true; nn=n;}
                }
  	        if (found) 
	        {
    	           if ((nn.c).equals(sblue.c)) nn.c=Color.red;
                   else if ((nn.c).equals(sred.c)) L.remove(h);
	        }
	        else L.add(sblue);
	        repaint();  
             }
         }

         public void mouseReleased(MouseEvent e) {
}
  
     }

     private void union(Intcoll A, Intcoll B, Intcoll[] C)
     {
        int i=1; int n=B.get_howmany();
        while (n > 0) {
            if (B.belongs(i)){
                A.insert(i); 
                n--;
            }
            i++;
        }
     }

    }//end Mazepanel

    private class Node 
    {
       private Point p; private Color c;

       public Node(int X, int Y, Color color){
           p=new Point(X, Y); 
           c=color;
       }
    }

    public static void main(String args[]) {new InputGraphicMaze7();}

} //end of class
	
 
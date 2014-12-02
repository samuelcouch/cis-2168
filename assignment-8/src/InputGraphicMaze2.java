import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import java.awt.Point;
import java.util.LinkedList;
import javax.swing.JButton;

public class InputGraphicMaze2 extends JFrame  
{
    private int M, N; private Intcoll[] C; 
    private Line2D Top, Bottom, Left, Right; 
    private int[] Line; private boolean[] status; 
    private int show; ; private LinkedList<Point> Path;
    private LinkedList<Node> L; private int start, delta;
    private Stroke drawingStroke=new BasicStroke(2);

    public InputGraphicMaze2()  
    {
        super("A MAZE");
        String integers=JOptionPane.showInputDialog(
                "Enter #rows and #columns.");
	Scanner in=new Scanner(integers); 
        M=in.nextInt(); 
        N=in.nextInt(); 
        L=new LinkedList<Node>();   
 
	C=new Intcoll[N*M+1];
        int k; 
        
	for (k=1; k<=M*N; k++){
            C[k]=new Intcoll(M*N+1); 
            C[k].insert(k);
        }
 
        start=50; 
        delta=15;              	
        Top=new Line2D.Double(start+delta, start, start+N*delta, start);
	Bottom=new Line2D.Double(start, start+M*delta,start+(N-1)*delta, 
         	start+M*delta);
	Left=new Line2D.Double(start, start, start, 
		start+M*delta);
	Right=new Line2D.Double(start+N*delta, start, start+N*delta, 
		start+M*delta);
        int i=0, j=0; 
        int size=M*(N-1)+(M-1)*N+1;  
        //Add all internal lines
        Line=new int[size];
        status=new boolean[size];
        for (k=1; k<size; k++) {
            Line[k]=k; status[k]=true;
        }
        // Randomly generate the next line to remove if its adjacent
        // cells are not already connected
	Random gen=new Random(); int L=M*(N-1)+(M-1)*N; int S=1; 
        int cell1, cell2; int temp; int count=0, a, b, slot; 
        //remove enough lines to generate the maze  
        while (count<M*N-1){	       
            slot=gen.nextInt(L)+1; k=Line[slot];
            int D=M*(N-1);
            if (k<=D){               
                i=(k-1)/(N-1)+1;j=k-(i-1)*(N-1);
                cell1=(i-1)*N+j; cell2=cell1+1;
            }
            else{
                int K=k-D;  
                i=(K-1)/N+1; 
                j=K-N*(i-1);
                cell1=(i-1)*N+j; 
                cell2=cell1+N;
            } 
            //Find a and b - the sets of adjacent cells cell1 and cell2
	    a=1;
            while (!C[a].belongs(cell1))
                a++; 
	    b=1; 
            while (!C[b].belongs(cell2))
                b++;
            //if not in same set - take the union of the sets
            //and remove the line between the two cells
	    if (a!=b){
	        if (a<b){ 
                    union(C[a], C[b], C); 
                    C[b]=C[M*N-count];
                }
                else {
                    union(C[b], C[a], C); 
                    C[a]=C[M*N-count];
                }
		status[k]=false; count++; Line[slot]=Line[L]; L--;
            }
        } //end of while 

        Mazepanel mazepanel=new Mazepanel(); 
        setLayout(new BorderLayout());
        add(mazepanel, BorderLayout.CENTER);
	SlotMouseListener slotlistener=new SlotMouseListener();
	mazepanel.addMouseListener(slotlistener);

   	JButton button=new JButton("Show Path");
	add(button, BorderLayout.NORTH);
	ActionListener buttonlistener=new ShowPathListener();
	button.addActionListener(buttonlistener);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(1200, 800);     
        setVisible(true);         
     }//end of constructor

     class ShowPathListener implements ActionListener
     {
        public void actionPerformed(ActionEvent e){
           show=1; setTitle("A MAZE WITH PATH"); repaint();
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
            
           int x=e.getX()-start;
           int y=e.getY()-start;
            
           int col=1+(int)Math.floor(x/delta);
 
           int row=1+(int)Math.floor(y/delta);
  
	   if ((row>0)&&(row<=M)&&(col>0)&&(col<=N)){ 
               Node sred=new Node(row, col, Color.red); 
               Node sblue=new Node(row, col, Color.blue);
               int i, j, h=-1; boolean found=false; Node nn=null; 
               Iterator J=L.iterator();
               while ((J.hasNext())&&(!found)){
                   h++; Node n=(Node) J.next(); 
                   Point q = n.p; i=(int) q.getX(); j=(int) q.getY();             
                   Color C = n.c; 
                   if (q.equals(sred.p)) {found=true; nn=n;}
               }
  	       if (found){
    	          if ((nn.c).equals(sblue.c)) 
                      nn.c=Color.red;
                  
                  else if ((nn.c).equals(sred.c)) L.remove(h);
	       }
	       else L.add(sblue);
	       repaint();
           }
  	} 
        
        
        public void mouseReleased(MouseEvent e) {
}
    
     }

     public class Mazepanel extends JPanel 
     {
        public Mazepanel() {}  
  
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
              if (k<=D){
                  i=(k-1)/(N-1)+1; j=k-(i-1)*(N-1);
                  line=new Line2D.Double(start+j*delta, 
		  start+(i-1)*delta, start+j*delta, start+i*delta);
              }
              else{
                  kk=k-D; i=(kk-1)/N+1; j=kk-N*(i-1);
                  line=new Line2D.Double(start+(j-1)*delta,                 
                    start+i*delta, start+j*delta, start+i*delta);
	      }
              graph.setPaint(Color.black); graph.draw(line);       
           }
           if (show==1){
              L.clear(); graph.setPaint(Color.green);  
              Iterator I=Path.iterator();
              while (I.hasNext()){
                 Point p=(Point) I.next(); 
		 i=(int) p.getX(); j=(int) p.getY();
                 graph.fillRect(start+(j-1)*delta+1, 
		    start+(i-1)*delta+1, delta-2, delta-2);
              }
           }
           else
           {
              Iterator J=L.iterator();
              while (J.hasNext()){
  	         Node n=(Node) J.next(); 
                 Point q=n.p; i=(int) 
                 q.getX(); j=(int) q.getY();             
                 Color C = n.c; graph.setPaint(C); 
                 graph.fillRect(start+(j-1)*delta+1, 
		    start+(i-1)*delta+1, delta-2, delta-2);
              }
          }
       }
    }

    public static void main(String args[]){
        new InputGraphicMaze2();
    }
	
    public int Rows() {return M;}
    public int Cols() {return N;}
    public boolean can_go(int i, int j, char c)
    {
       int D=M*(N-1); boolean result=false; int K=N*(M-1)+(N-1)*M; 
       
       if (c=='U') 
           result=!status[D+(i-2)*N+j];
       if (c=='R') 
           result=!status[(i-1)*(N-1)+j];
       if (c=='D') 
           result=!status[D+(i-1)*N+j];
       if (c=='L') 
           result=!status[(i-1)*(N-1)+j-1];
       
       return result;
    }

    public void showPath(LinkedList<Point> P){
        Path=P;
        repaint();
    }
    
    private void union(Intcoll A, Intcoll B, Intcoll[] C){
        int i=1; int n=B.get_howmany();
        
        while (n > 0){
            if (B.belongs(i)){
                A.insert(i);
                n--;
            } 
            i++;
        }
    }

    private class Node 
    {
       private Point p; private Color c;

       public Node(int X, int Y, Color color){
           p=new Point(X, Y);
           c=color;
       }
    }

} //end of class
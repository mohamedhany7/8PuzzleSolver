package puzzle;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.List;

public class Node {
    enum move{
        up,
        down,
        right,
        left
    }
    private int[][] state;
    private Node [] neighbors;
    private Node parent;
    private int cost;
    private move [] moves;
    private int coordinates[] = new int[2]; 
    private int position;
    private List<int[][]> neighbor=new ArrayList<int[][]>();
    private move swap;
    private int distance;


    public Node(int[][] state,Node parent, int cost, move swap) {
        this.state = state;
        this.cost = cost;
        this.parent = parent;
        this.swap = swap;
        find_position();
        find_moves();
        find_neighbor();
    }

    public int getDistance() {
        return distance;
    }

    public move getSwap() {
        return swap;
    }
    
    public int[][] getState() {
        return state;
    }

    public Node[] getNeighbors() {
        return neighbors;
    }

    public Node getParent() {
        return parent;
    }

    public int getCost() {
        return cost;
    }
    
    private void find_position(){
        int i,j;
        int x=0,y=0;
        for (i=0;i<3;i++)
            for (j=0;j<3;j++)
                if (state[i][j] == 0){
                    x =i;
                    y = j;
                    break;
                }
        coordinates[0]=x;
        coordinates[1]=y;
        position= (x)*3+(y);
    }
    
    private void find_moves(){
        switch (position){
            case 0: 
                moves = new move[2];
                neighbors = new Node[2];
                moves[0] = move.down;
                moves[1] = move.right;
                break;
            case 1: 
                moves = new move[3];
                neighbors = new Node[3];
                moves[0] = move.down;
                moves[1] = move.left;
                moves[2] = move.right;
                break;
            case 2: 
                moves = new move[2];
                neighbors = new Node[2];
                moves[0] = move.down;
                moves[1] = move.left;
                break;
            case 3: 
                moves = new move[3];
                neighbors = new Node[3];
                moves[0] = move.up;
                moves[1] = move.down;
                moves[2] = move.right;
                break;
            case 4: 
                moves = new move[4];
                neighbors = new Node[4];
                moves[0] = move.up;
                moves[1] = move.down;
                moves[2] = move.left;
                moves[3] = move.right;
                break;
            case 5: 
                moves = new move[3];
                neighbors = new Node[3];
                moves[0] = move.up;
                moves[1] = move.down;
                moves[2] = move.left;
                break;
            case 6: 
                moves = new move[2];
                neighbors = new Node[2];
                moves[0] = move.up;
                moves[1] = move.right;
                break;
            case 7: 
                moves = new move[3];
                neighbors = new Node[3];
                moves[0] = move.up;
                moves[1] = move.left;
                moves[2] = move.right;
                break;
            case 8: 
                moves = new move[2];
                neighbors = new Node[2];
                moves[0] = move.up;
                moves[1] = move.left;
                break;
        }
    }

    public void find_neighbor(){
        int i;
        int x = coordinates[0];
        int y = coordinates[1];
                
        for (i=0;i<moves.length;i++){
            int [][] temp = {{0,0,0},{0,0,0},{0,0,0}};
            for(int l=0;l<3;l++)
                for(int k=0;k<3;k++)
                    temp[l][k]=state[l][k];
            Node n;
            switch (moves[i]){
                case up:
                    temp[x][y] = state[x-1][y];
                    temp[x-1][y] = 0;
                    //n = new Node(temp,node,cost);
                    //neighbors[i] = n;
                    neighbor.add(temp);
                    break;
                case down:
                    temp[x][y] = state[x+1][y];
                    temp[x+1][y] = 0;
                    //n = new Node(temp,node,cost);
                    //neighbors[i] = n;
                    neighbor.add(temp);
                    break;
                case left:
                    temp[x][y] = state[x][y-1];
                    temp[x][y-1] = 0;
                    //n = new Node(temp,node,cost);
                    //neighbors[i] = n;
                    neighbor.add(temp);
                    break;
                case right:
                    temp[x][y] = state[x][y+1];
                    temp[x][y+1] = 0;
                    //n = new Node(temp,node,cost);
                    //neighbors[i] = n;
                    neighbor.add(temp);
                    break;
            }
        }
    }
    
    public void find_neighbors(Node node,int c){
        switch(c){
            case 0:{
                for(int i=0;i<neighbor.size();i++){
                    Node n = new Node(neighbor.get(i),node,1,moves[i]);
                        neighbors[i] = n;       
                }
                break;
            }
            case 1:{
                for(int i=0;i<neighbor.size();i++){
                    Node n = new Node(neighbor.get(i),node,1,moves[i]);
                        calcDistance(1);
                        neighbors[i] = n;       
                }
                break;
            }
            case 2:{
                for(int i=0;i<neighbor.size();i++){
                    Node n = new Node(neighbor.get(i),node,1,moves[i]);
                    calcDistance(2);    
                    neighbors[i] = n;       
                }
                break;
            }
        }
    }
    
    public String get_move(){
        String s = "";
        if (swap != null)
            switch (swap){
                case up:
                    s = "up";
                    break;
                case down:
                    s = "down";
                    break;
                case left:
                    s = "left";
                    break;
                case right:
                    s = "right";
                    break;
            }
        return s;
    }
    
    private void calcDistance(int sw){
       int x = coordinates[0];
       int y = coordinates[1];
       switch (sw){
          case 1:{
               distance =abs(x)+ abs(y);
          }break;
          case 2:{
               distance = (int) sqrt(x*x + y*y);
          }break; 
        }
    }
}

package puzzle;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

class The_Comparator implements Comparator<Node> { 
    @Override
    public int compare(Node node1, Node node2){ 
        if (node1.getCost()>node2.getCost())
            return 0;
        else if (node1.getCost()>node2.getCost())
           return 1;
        return 0;
    } 
} 

public class Logic {
    
    private int [][] goal = {{0,1,2},{3,4,5},{6,7,8}};
    private int [][] goal2 = {{1,2,3},{8,0,4},{7,6,5}};
    
    private String str = "";
    private int path =1;

    public boolean bfs(){
        long start =0;
        Puzzle.cost =0;
        Puzzle.step =0;
        Puzzle.puzzle.clear();
        Puzzle.output.clear();
        Node node = new Node(Puzzle.state,null,0,null);
        Queue<Node> queue = new ArrayDeque<>();
        Queue<int [][]> explored = new ArrayDeque<>();

        boolean frontier = queue.add(node);
        
        while (!queue.isEmpty()){
            start = System.currentTimeMillis();
            Node temp = queue.poll();
            explored.add(temp.getState());
            
            print(temp.getState());
            
            if (compGoal(temp.getState())){
                findPAth(temp);
                long end = System.currentTimeMillis();
                Puzzle.elapsedTime = end - start;
                return true;
            }
            
            temp.find_neighbors(temp,0);
            Node[] neighbor =temp.getNeighbors();
            
            if(neighbor!=null)
                for (int i=0;i<neighbor.length;i++)
                    if (!inQueue(neighbor[i], queue) && !inExp(neighbor[i].getState(), explored))
                        queue.add(neighbor[i]);
        }
        
        long end = System.currentTimeMillis();
        Puzzle.elapsedTime = end - start;
        return false;
    }
    
    public boolean dfs (){
        long start=0;
        Puzzle.cost =0;
        Puzzle.step =0;
        Puzzle.puzzle.clear();
        Puzzle.output.clear();
        Node node = new Node(Puzzle.state,null,0,null);
        Stack<Node> stack = new Stack<>();
        Queue<int [][]> explored = new ArrayDeque<>();

        boolean frontier = stack.add(node);
        
        while (!stack.isEmpty()){
            start = System.currentTimeMillis();
            Node temp = stack.pop();
            explored.add(temp.getState());
            
            print(temp.getState());
            
            if (compGoal(temp.getState())){
                findPAth(temp);
                long end = System.currentTimeMillis();
                Puzzle.elapsedTime = end - start;
                return true;
            }
            
            temp.find_neighbors(temp,0);
            Node[] neighbor =temp.getNeighbors();
            
            if(neighbor!=null)
                for (int i=0;i<neighbor.length;i++)
                    if (!inStack(neighbor[i], stack) && !inExp(neighbor[i].getState(), explored))
                        stack.add(neighbor[i]);
        }
        long end = System.currentTimeMillis();
        Puzzle.elapsedTime = end - start;
        return false;
    }
    
    public boolean aStar(int mode){
        long start=0;
        Puzzle.cost =0;
        Puzzle.step =0;
        Puzzle.puzzle.clear();
        Puzzle.output.clear();
        Node node = new Node(Puzzle.state,null,0,null);
	PriorityQueue<Node> queue = new PriorityQueue<Node>(1000, (a, b) -> (a.getCost() + a.getDistance()) - (b.getCost() + b.getDistance()));
        PriorityQueue<Node> pq = new PriorityQueue<Node>(1000, (a, b) -> (a.getCost() + a.getDistance()) - (b.getCost() + b.getDistance()));
        Queue<int [][]> explored = new ArrayDeque<>();

        boolean frontier = queue.add(node);
        
        while (!queue.isEmpty()){
            start = System.currentTimeMillis();
            Node temp = queue.remove();
            explored.add(temp.getState());
            
            print(temp.getState());
            
            if (compGoal(temp.getState())){
                findPAth(temp);
                long end = System.currentTimeMillis();
                Puzzle.elapsedTime = end - start;
                return true;
            }
            
            temp.find_neighbors(temp,mode);
            Node[] neighbor =temp.getNeighbors();
            
            if(neighbor!=null)
                for (int i=0;i<neighbor.length;i++){
                    if (!inPQueue(neighbor[i], queue) && !inExp(neighbor[i].getState(), explored))
                        queue.add(neighbor[i]);
                    else if (inPQueue(neighbor[i], queue)){
                        Node n = compPQueue(neighbor[i], queue);
                        if(n!=null){
                            queue.remove(n);
                            queue.add(neighbor[i]);
                            }
                    }
                }
        }
        long end = System.currentTimeMillis();
        Puzzle.elapsedTime = end - start;
        return false;
}
        
    
    private boolean compGoal(int[][]state){
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(state[i][j]!=goal[i][j])
                    return false;
        return true;
    }
    
    private boolean inExp(int[][]state,Queue<int[][]> explored){
        Queue<int [][]> exp = new ArrayDeque<>();
        exp.addAll(explored);
        boolean flag = false;
        for (int z=0;z<explored.size();z++){
            if (flag)
                return true;
            flag = true;
            int[][] temp = exp.remove();
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++)
                    if(state[i][j]!=temp[i][j]){
                        flag = false;
                        break;
                    }
                if (flag==false)
                    break;
                }
        }
        return false;
    }
    
    private boolean inQueue(Node node,Queue<Node> queue){
        Queue<Node> q = new ArrayDeque<>();
        q.addAll(queue);
        for(int i=0;i<queue.size();i++){
            Node temp = q.remove();
            if(temp.equals(node))
                return true;
        }
        return false;
    }
    
    private boolean inPQueue(Node node,PriorityQueue<Node> queue){
	PriorityQueue<Node> q = new PriorityQueue<Node>(1000, (a, b) -> (a.getCost() + a.getDistance()) - (b.getCost() + b.getDistance()));
        q.addAll(queue);
        for(int i=0;i<queue.size();i++){
            Node temp = q.remove();
            if(temp.equals(node))
                return true;
        }
        return false;
    }
    
    private Node compPQueue(Node node,PriorityQueue<Node> queue){
	PriorityQueue<Node> q = new PriorityQueue<Node>(1000, (a, b) -> (a.getCost() + a.getDistance()) - (b.getCost() + b.getDistance()));
        q.addAll(queue);
        for(int i=0;i<queue.size();i++){
            Node temp = q.remove();
            if(temp.equals(node))
                if((temp.getCost() + temp.getDistance()) > (node.getCost() + node.getDistance()))
                    return temp;
        }
        return null;
    }
    
    private boolean inStack(Node node,Stack<Node> stack){
        Stack<Node> q = new Stack<>();
        q.addAll(stack);
        for(int i=0;i<stack.size();i++){
            Node temp = q.pop();
            if(temp.equals(node))
                return true;
        }
        return false;
    }

    
    private void print(int [][]puzzle){
        str = "Step " + Puzzle.step + ":\n";
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++)
                str += "[" + puzzle[i][j] +"]\t";
            str +="\n";
        }
        str += "-------------------------\n\n";
        //Puzzle.output.add(str);
        System.out.println(str);
        Puzzle.step++;
    }
    
    private void findPAth2(Node node){
        while(node != null){
            str = "Step " + path + ":\n";
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++)
                    str += "[" + node.getState()[i][j] +"]\t";
                str +="\n";
            }
            str += "-------------------------\n\n";
            Puzzle.output.add(str);
            Puzzle.cost += node.getCost();
            node = node.getParent();
            path++;
        }
    }
    
    private void findPAth(Node node){
        while(node != null){
            str = node.get_move();
            if(str != null){
                str += "\n";
                Puzzle.output.add(str);
                Puzzle.puzzle.add(node.getState());
                Puzzle.cost += node.getCost();
                node = node.getParent();
                path++;
            }
        }
    }
}
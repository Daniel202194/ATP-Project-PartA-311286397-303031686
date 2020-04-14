package algorithms.search;

import java.util.ArrayList;


/**
 * This  class  represent the solution of the problem
 */
public class Solution {
    private AState solutionState;

    /**
     * constructor
     * This constructor get state
     * @param solutionState
     */
    public Solution(AState solutionState){
        this.solutionState = solutionState;
    }

    /**
     * default constructor
     */
    public Solution(){}

    /**
     *
     * @return  path of the solution
     */
    public ArrayList<AState> getSolutionPath(){
        AState current = this.solutionState;
        ArrayList<AState> path = new ArrayList<>() ;
        ArrayList<AState> reversdPath = new ArrayList<>();
        while(current!=null){
            reversdPath.add(current);
            current=current.getCameFrom();
        }
        for(int index = reversdPath.size()-1;index>=0 ;index--){
            path.add(reversdPath.get(index));
        }
        return path;
    }
}

package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * MazeState class , this class extends abstract class AState
 */
public class MazeState extends AState {
    private Position position;

    /**
     * constructor
     * @param stateName
     * @param position
     */
    public MazeState(String stateName ,Position position) {
        super(stateName);
        if(position != null)
            this.position = position;
        else
            this.position = null;
    }

    /**
     *
     * @return row of the state
     */
    public int getRow(){
        if(this.position == null)
            return -1;
        return this.position.getRowIndex();
    }

    /**
     *
     * @return column of the state
     */
    public int getColumn(){
        if(this.position == null)
            return -1;
        return this.position.getColumnIndex();
    }


}

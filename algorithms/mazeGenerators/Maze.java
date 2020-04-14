
package algorithms.mazeGenerators;
import com.sun.prism.shader.Texture_ImagePattern_AlphaTest_Loader;

import java.util.Random;

/**
 * class that representing the maze
 */
public class Maze {
    private int rows;
    private int cols;
    private int theMaze[][];
    private Position start;
    private Position goal;

    /**
     * Maze constractor , build the maze with given size of rows and column,
     * the columns number and rows number must be at least 3 , otherwise maze
     * reference to null
     *
     * @param colNum
     * @param rowNum
     */
    public Maze(int colNum,int rowNum){
        this.cols=colNum;
        this.rows=rowNum;
        if(colNum < 3 || rowNum < 3)
            this.theMaze = null;
        else
            theMaze=new int[rowNum][colNum];
        start=null;
        goal=null;
    }

    /**
     * This function define randomly start position on 1 of the 4 edge
     * of the maze
     */
    public void startPositionSelect(){
        Random row = new Random();
        Random column = new Random();
        int rowIndex= row.nextInt(this.rows);
        int colIndex;
        if(rowIndex!=0 && rowIndex!=this.rows-1){
            colIndex = column.nextInt(2);
            if(colIndex==0)
                start = new Position(rowIndex,0);
            else
                start = new Position(rowIndex,this.cols-1);
        }
        else if(rowIndex==this.rows-1){
            colIndex = column.nextInt(this.cols);
            start = new Position(rowIndex,colIndex);
        }
        else{
            colIndex = column.nextInt(this.cols);
            start = new Position(0, colIndex);
        }
    }

    /**
     * This function get row and column and return 'cell value' of the maze in the given row,column position.
     * if row and col not in the maze limit it returns  -1
     * otherwise return 1(wall) or 0(path)
     *
     * @param row
     * @param col
     * @return  int - cell value
     */
    public int getCellValue(int row,int col) {
        if(row < 0 || col < 0 || row > this.rows-1 || col > this.cols-1 || this.theMaze==null)
            return -1;
        return this.theMaze[row][col];
    }

    /**
     * Set goal position with given row index and column index
     * @param row
     * @param col
     */
    public void setGoalPosition(int row,int col){
        this.goal = new Position(row,col);
    }

    /**
     * This function returns columns number of the maze
     * @return int - maze columns number
     */
    public int getColNumbers(){
        return this.cols;
    }

    /**
     * This function returns rows number of the maze
     * @return int - maze rows number
     */
    public int getRowNumbers(){
        return this.rows;
    }

    /**
     * This function get row index and column index and if the given column and row is in
     * the limit of the maze size the function set in the cell value=1 (wall) in
     * the given row,column position
     * @param row
     * @param col
     */
    protected void setWall(int row, int col){
        if(row < 0 || col < 0)
            throw new IndexOutOfBoundsException("Can not set: 'row or col is out of the maze' ");
        if(row >= this.rows || col >= this.cols)
            throw new IndexOutOfBoundsException("Can not set: 'row or col is out of the maze' ");
        if(this.theMaze==null)
            throw new NullPointerException("The maze is not declared or reference to null");
        this.theMaze[row][col]=1;
    }

    /**
     * This function get row index and column index and if the given column and row is in
     * the limit of the maze size the function set in the cell value=0 (delete wall) in
     * the given row,column position
     * @param row
     * @param col
     */
    protected void deleteWall(int row, int col){
        if(row < 0 || col < 0)
            throw new IndexOutOfBoundsException("Can not delete: 'row or col is out of the maze' ");
        if(row >= this.rows || col >= this.cols)
            throw new IndexOutOfBoundsException("Can not delete: 'row or col is out of the maze' ");
        if(this.theMaze==null)
            throw new NullPointerException("The maze is not declared or reference to null");
        this.theMaze[row][col]=0;
    }


    /**
     * This function return start Position
     * @return Position
     */
    public Position getStartPosition(){
        return this.start;
    }

    /**
     * This function return Goal position
     * @return
     */
    public Position getGoalPosition(){
        return this.goal;
    }

    /**
     * This function print the maze when '1' is wall , '0' is path
     * 'S' is the start position and 'E' is the goal position
     */
    public void print(){
        for(int row=0;row<getRowNumbers();row++){
            for(int col=0;col<getColNumbers();col++){
                if(row==start.getRowIndex() && col==start.getColumnIndex()){
                    System.out.print("S");
                }
                else if(row==goal.getRowIndex() && col==goal.getColumnIndex()){
                    System.out.print("E");
                }
                else {
                    System.out.print(String.format("%s",getCellValue(row,col)));
                }
            }
            System.out.println("");

        }
    }

    /**
     * This function define randomly but not on the same edge
     * the goal position of the maze
     */
    public void DefineGoalPosition(){
        int startRow = getStartPosition().rowPosition;
        int startCol = getStartPosition().colPosition;
        Random rand = new Random();
        int colIndex,rowIndex;

        setGoalPosition(0,0);

        if(startRow==0){
            colIndex = rand.nextInt(getColNumbers()-2)+1;
            while(getCellValue(getRowNumbers()-1,colIndex)!=0){
                colIndex = rand.nextInt(getColNumbers()-2)+1;
            }
            setGoalPosition(getRowNumbers()-1,colIndex);
        }
        else if(startRow==getRowNumbers()-1){
            colIndex = rand.nextInt(getColNumbers()-2)+1;
            while(getCellValue(0,colIndex)!=0){
                colIndex = rand.nextInt(getColNumbers()-2)+1;
            }
            setGoalPosition(0,colIndex);
        }
        else if(startCol==0){
            rowIndex = rand.nextInt(getRowNumbers()-2)+1;
            while(getCellValue(rowIndex,getColNumbers()-1)!=0){
                rowIndex = rand.nextInt(getRowNumbers()-2)+1;
            }
            setGoalPosition(rowIndex,getColNumbers()-1);
        }
        else if(startCol==getColNumbers()-1) {
            rowIndex = rand.nextInt(getRowNumbers()-2)+1;
            while(getCellValue(rowIndex,0)!=0){
                rowIndex = rand.nextInt(getRowNumbers()-2)+1;
            }
            setGoalPosition(rowIndex,0);
        }
    }
}

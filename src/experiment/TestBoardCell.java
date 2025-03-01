package experiment;

import java.util.HashSet;
import java.util.Set;

public class TestBoardCell {
    private int row;
    private int col;
    private boolean isRoom;
    private boolean isOccupied;
    private Set<TestBoardCell> adjacencyList;

    public TestBoardCell(int row, int col) {
        this.row = row;
        this.col = col;
        this.isRoom = false;
        this.isOccupied = false;
        this.adjacencyList = new HashSet<>();
    }

    public void addAdjacency(TestBoardCell cell) {
        adjacencyList.add(cell);
    }

    public Set<TestBoardCell> getAdjList() {
        return adjacencyList;
    }

 
    public void setRoom(boolean room) {
        this.isRoom = room;
    }


    public boolean isRoom() {
        return isRoom;
    }


    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
    
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}

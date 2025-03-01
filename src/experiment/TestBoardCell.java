package experiment;

import java.util.HashSet;
import java.util.Set;

// Represents a single cell on the game board. Each cell stores its row, column, adjacency list, and status flags for rooms and occupancy.
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

    // Adds an adjacent cell to the adjacency list.
    public void addAdjacency(TestBoardCell cell) {
        adjacencyList.add(cell);
    }

    // Returns the set of adjacent cells.
    public Set<TestBoardCell> getAdjList() {
        return adjacencyList;
    }

    // Marks this cell as a room.
    public void setRoom(boolean room) {
        this.isRoom = room;
    }

    // Checks if this cell is a room.
    public boolean isRoom() {
        return isRoom;
    }

    // Marks this cell as occupied.
    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    // Checks if this cell is occupied.
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

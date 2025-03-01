package experiment;

import java.util.HashSet;
import java.util.Set;

// Represents the game board and manages movement calculations.
public class TestBoard {
    private Set<TestBoardCell> targets;
    private TestBoardCell[][] grid;
    private Set<TestBoardCell> visited;

    private void findTargets(TestBoardCell cell, int steps) {
        System.out.println("Visiting cell (" + cell.getRow() + ", " + cell.getCol() + "), Steps left: " + steps);

        if (cell.isOccupied()) {
            System.out.println("Cell (" + cell.getRow() + ", " + cell.getCol() + ") is occupied, stopping movement.");
            return; // Stop movement completely
        }

        if (steps == 0) {
            System.out.println("Adding cell (" + cell.getRow() + ", " + cell.getCol() + ") to targets.");
            targets.add(cell);
            return;
        }

        // Add the room cell but do not continue past it
        if (cell.isRoom()) {
            System.out.println("Adding room cell (" + cell.getRow() + ", " + cell.getCol() + ") to targets and stopping.");
            targets.add(cell);
            return;
        }

        visited.add(cell);

        for (TestBoardCell adj : cell.getAdjList()) {
            if (!visited.contains(adj)) {
                findTargets(adj, steps - 1);
            }
        }

        visited.remove(cell);
    }

    // Initializes the board and sets up targets as an empty set.
    public TestBoard(int rows, int cols) {
        targets = new HashSet<>();
        grid = new TestBoardCell[rows][cols];
        visited = new HashSet<>();

        // Initialize board cells
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = new TestBoardCell(row, col);
            }
        }

        // Build adjacency lists
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                TestBoardCell cell = grid[row][col];
                if (row > 0) { // Up
                    cell.addAdjacency(grid[row - 1][col]);
                }
                if (row < rows - 1) { // Down (Fixed condition)
                    cell.addAdjacency(grid[row + 1][col]);
                }
                if (col > 0) { // Left
                    cell.addAdjacency(grid[row][col - 1]);
                }
                if (col < cols - 1) { // Right (Fixed condition)
                    cell.addAdjacency(grid[row][col + 1]);
                }
            }
        }
    }

    // Returns the board cell at the given row and column.
    public TestBoardCell getCell(int row, int col) {
        return grid[row][col];
    }

    // Calculates movement targets for a given cell and number of steps.
    public void calcTargets(TestBoardCell startCell, int steps) {
        targets.clear();
        visited.clear();
        findTargets(startCell, steps);
    }

    // Returns the set of valid movement targets.
    public Set<TestBoardCell> getTargets() {
        return targets;
    }
}

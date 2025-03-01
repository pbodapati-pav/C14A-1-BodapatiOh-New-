package experiment;

import java.util.HashSet;
import java.util.Set;

public class TestBoard {
    private Set<TestBoardCell> targets;
    private TestBoardCell[][] grid;
    private Set<TestBoardCell> visited;

    private void findTargets(TestBoardCell cell, int steps) {
        System.out.println("Visiting cell (" + cell.getRow() + ", " + cell.getCol() + "), Steps left: " + steps);

        if (cell.isOccupied()) {
            System.out.println("Cell (" + cell.getRow() + ", " + cell.getCol() + ") is occupied, stopping movement.");
            return; 
        }

        if (steps == 0) {
            System.out.println("Adding cell (" + cell.getRow() + ", " + cell.getCol() + ") to targets.");
            targets.add(cell);
            return;
        }

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

    public TestBoard(int rows, int cols) {
        targets = new HashSet<>();
        grid = new TestBoardCell[rows][cols];
        visited = new HashSet<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = new TestBoardCell(row, col);
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                TestBoardCell cell = grid[row][col];
                if (row > 0) { 
                    cell.addAdjacency(grid[row - 1][col]);
                }
                if (row < rows - 1) { 
                    cell.addAdjacency(grid[row + 1][col]);
                }
                if (col > 0) { 
                    cell.addAdjacency(grid[row][col - 1]);
                }
                if (col < cols - 1) { 
                    cell.addAdjacency(grid[row][col + 1]);
                }
            }
        }
    }


    public TestBoardCell getCell(int row, int col) {
        return grid[row][col];
    }

    public void calcTargets(TestBoardCell startCell, int steps) {
        targets.clear();
        visited.clear();
        findTargets(startCell, steps);
    }

    public Set<TestBoardCell> getTargets() {
        return targets;
    }
}

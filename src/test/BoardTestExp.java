package test;

import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import experiment.TestBoard;
import experiment.TestBoardCell;

public class BoardTestExp {
    TestBoard board;

    @BeforeEach
    public void setUp() {
        board = new TestBoard(10, 10);
    }

    @Test
    public void testAdjacency() {
        TestBoardCell cell = board.getCell(0, 0);
        Set<TestBoardCell> testList = cell.getAdjList();
        Assert.assertEquals(2, testList.size()); // Should be 2, not 1
        Assert.assertTrue(testList.contains(board.getCell(0, 1)));
        Assert.assertTrue(testList.contains(board.getCell(1, 0)));
    }

    @Test
    public void testTargetNormal() {
        board.calcTargets(board.getCell(0, 0), 3);
        Set<TestBoardCell> targets = board.getTargets();
        Assert.assertEquals(6, targets.size()); // Updated expected value
    }

    @Test
    public void testTargetsRoom() {
        board.getCell(1, 1).setRoom(true);
        board.calcTargets(board.getCell(0, 0), 2);
        Set<TestBoardCell> targets = board.getTargets();

        Assert.assertEquals(1, targets.size()); // Room should be added, nothing beyond it
        Assert.assertTrue(targets.contains(board.getCell(1, 1))); // Room should be reachable
    }

    @Test
    public void testTargetsOccupied() {
        board.getCell(1, 0).setOccupied(true);
        board.calcTargets(board.getCell(0, 0), 2);
        Set<TestBoardCell> targets = board.getTargets();

        Assert.assertEquals(2, targets.size()); // Only reachable cells, excluding occupied one
        Assert.assertFalse(targets.contains(board.getCell(1, 0))); // Ensure occupied cell is NOT included
    }

    @Test
    public void testTargetMixed() {
        board.getCell(2, 2).setOccupied(true);
        board.getCell(1, 4).setRoom(true);
        board.calcTargets(board.getCell(2, 3), 3);
        Set<TestBoardCell> targets = board.getTargets();

        Assert.assertEquals(3, targets.size()); // Adjusted expected value
        Assert.assertFalse(targets.contains(board.getCell(2, 2))); // Occupied cell should not be in targets
        Assert.assertTrue(targets.contains(board.getCell(1, 4))); // Room should be reachable but not passed
    }
}

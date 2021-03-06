package test.java;

import main.java.InvalidPositionException;
import main.java.Model;
import main.java.Position;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelTest
{
    private static final Logger logger = LoggerFactory.getLogger(ModelTest.class);

    /**
     * When it is white's turn, a black piece cannot be moved.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCantMovePieceOfOtherColor()
    {
        Model model = new Model();

        Position currPosition = null;
        Position newPosition = null;
        try
        {
            currPosition = new Position(1, 0);
            newPosition = new Position(2, 0);
            model.movePiece(currPosition, newPosition);
        } catch (InvalidPositionException ex)
        {
            /*An InvalidPositionException should not be thrown, b/c this test should only create valid positions*/
            logger.error(ex.getMessage());
        }
    }
}
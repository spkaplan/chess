package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import main.java.InvalidPositionException;
import main.java.Position;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PositionTest
{
    final Logger logger = LoggerFactory.getLogger(PositionTest.class);

    @Test()
    public void testErrorThrownIfPositionIsCreatedOutsideOfGrid()
    {
        int numExceptions = 0;

        /*Try to create positions above and below the grid*/
        for (int column = -1; column <= 8; column++)
        {
            try
            {
                Position position = new Position(-1, column);
            } catch (InvalidPositionException ex)
            {
                numExceptions += 1;
            }
            try
            {
                Position position = new Position(8, column);
            } catch (InvalidPositionException ex)
            {
                numExceptions += 1;
            }
        }

        /*Try to create positions to the right and left of the grid*/
        for (int row = 0; row <= 7; row++)
        {
            try
            {
                Position position = new Position(row, -1);
            } catch (InvalidPositionException ex)
            {
                numExceptions += 1;
            }
            try
            {
                Position position = new Position(row, 8);
            } catch (InvalidPositionException ex)
            {
                numExceptions += 1;
            }
        }
        /*36 b/c there are 36 positions outlining the board that were tested*/
        assertEquals(numExceptions, 36);
    }

    @Test()
    public void testNoErrorThrownIfPositionIsCreatedInsideGrid()
    {
        for (int row = 0; row < 8; row++)
        {
            for (int column = 0; column < 8; column++)
            {
                try
                {
                    Position position = new Position(row, column);
                } catch (InvalidPositionException ex)
                {
                    logger.error(ex.getMessage());
                    System.exit(1);
                }
            }
        }
    }

    @Test()
    public void testEquals()
    {
        try
        {
            Position position1 = new Position(0, 0);
            Position position2 = new Position(0, 0);
            Position position3 = new Position(0, 1);
            Position position4 = new Position(1, 0);
            Position position5 = new Position(1, 1);

            assertTrue(position1.equals(position2));

            assertFalse(position2.equals(position3));
            assertFalse(position3.equals(position4));
            assertFalse(position4.equals(position5));
        } catch (InvalidPositionException ex)
        {
            logger.error(ex.getMessage());
            System.exit(1);
        }
    }
}
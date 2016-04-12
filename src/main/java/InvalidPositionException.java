package main.java;

public class InvalidPositionException extends Exception
{
    public InvalidPositionException(String message)
    {
        super(message);
    }

    public InvalidPositionException(String message, Throwable cause)
    {
        super(message, cause);
    }
}

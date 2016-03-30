package main.java;

public class CannotPlacePieceException extends Exception
{

	public CannotPlacePieceException(String message)
	{
		super(message);
	}

	public CannotPlacePieceException(String message, Throwable throwable)
	{
		super(message, throwable);
	}
}

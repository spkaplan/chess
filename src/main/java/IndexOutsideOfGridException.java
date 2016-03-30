package main.java;

public class IndexOutsideOfGridException extends Exception
{
	public IndexOutsideOfGridException(String message)
	{
		super(message);
	}

	public IndexOutsideOfGridException(String message, Throwable throwable)
	{
		super(message, throwable);
	}
}

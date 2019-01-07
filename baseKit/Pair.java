package baseKit;


public class Pair<T1,T2> extends Object{
	
	private final T1 first;
	private final T2 second;
	
	public Pair(T1 firstArg, T2 secondArg) {
		first = firstArg;
		second = secondArg;
	}
	
	public T1 First()
	{
		return first;
	}
	
	public T2 Second()
	{
		return second;
	}
	
	public boolean equals(Pair<T1, T2> compare)
	{
		if(compare.first.equals(first) && compare.second.equals(second))
			return true;
		else
			return false;
	}
}

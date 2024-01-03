package com.levkip.fibpet.api.model;

public class Fib
{
	private int index;
	private long value;
	
	public Fib(int index, long value)
	{
		this.index = index;
		this.value = value;
	}

	public int getIndex()
	{
		return index;
	}

	public long getValue()
	{
		return value;
	}

	@Override
	public String toString()
	{
		return "Fib [index=" + getIndex() + ", value=" + getValue() + "]";
	}
	
	
	
}

package utils;


class Adder<T extends Number>
{

public static T zero()
{
	
	return new T(0);
}

public static T add(T lhs, T rhs)
{
	
	return lhs + rhs;
}

}
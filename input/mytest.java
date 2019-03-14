class myclass{
    public static void main(String[] a){
		int x;
		A b;
		b=new A();	
	}
}




class A{
	int x;
	boolean y;
	A a;

	public int getX(){
		a=new A();
		return x;	
	}

	public boolean getY(){
		return y;
	}

	public int setX(int c){
		x=c;
		return 1;
	}




}

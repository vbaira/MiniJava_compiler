class ShadowBaseField {

    public static void main(String[] args){
        A a;
		C c;
		B b;
        a = new A();
        System.out.println(a.getX());
		//System.out.println(a.get());
        b = new B();
        System.out.println(b.getX());
		System.out.println(b.getY());
		System.out.println(b.get());
		c = new C();
		//System.out.println(c.getX());
		System.out.println(c.getY());
		
    }

}


class A {

    int x;
	int y;

	 public int getX(){
		x=50;
        return x;
    }

	public int getY(){
		y=100;
		return y;
	}
	
   


}


class B extends A {
	int x;
 	
	public int getX(){
		x=200;
        return x;
    }

	public int get(){
		return 1000;
	}

      
}



class C {
	int y;
	public int getY(){
		y=53;
		return y;
	}

}





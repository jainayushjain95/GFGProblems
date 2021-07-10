package inheru;

public class Dog extends Animal {

	public int tailLength;
	
	public Dog(String breed, String name, int tailLength) {
		super(breed, name);
		this.tailLength = tailLength;
	}

//	@Override
//	public void walk() {
//		System.out.println("Dog Walking\n");
//	}
}

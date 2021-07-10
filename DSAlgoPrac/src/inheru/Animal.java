package inheru;

public class Animal {

	String breed;
	String name;
	
	public Animal(String breed, String name) {
		super();
		this.breed = breed;
		this.name = name;
	}
	
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void walk() {
		System.out.println("Animal Walking\n");
	}
	
}

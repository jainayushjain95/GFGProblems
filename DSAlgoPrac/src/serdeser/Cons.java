package serdeser;

public class Cons {
	public static void main(String[] args) {
		Student student = new Student(10, "Ayush");
	}
}


class Person {
	private int age;

	public Person() {
		System.out.println("Person Created");
	}

	public Person(int age) {
		this.age = age;
		System.out.println("Person Created with Age = " + age);
	}
}

class Student extends Person{
	private String name;

	public Student() {
		System.out.println("Student Created");
	}

	public Student(int i, String n) {
		super(i); // super class constructor called
		this.name = n;
		System.out.println("Student Created with name = " + n);
	}
}

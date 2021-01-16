package serdeser;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public class Deser {

	public static void main(String[] args) {
		try {
			System.out.println("Deserializing AppleProduct...");
			String serializedObj = "rO0ABXNyABVzZXJkZXNlci5BcHBsZVByb2R1Y3QAAAAAALxhTgIAAkwADWhlYWRwaG9uZVBvcnR0ABJMamF2YS9sYW5nL1N0cmluZztMAA90aHVuZGVyYm9sdFBvcnRxAH4AAXhwdAARaGVhZHBob25lUG9ydDIwMjB0ABN0aHVuZGVyYm9sdFBvcnQyMDIw";
			AppleProduct deserializedObj = (AppleProduct) deSerializeObjectFromString(serializedObj);

			System.out.println( "Headphone port of AppleProduct:" + deserializedObj.headphonePort);

			System.out.println("Thunderbolt port of AppleProduct:"+ deserializedObj.thunderboltPort);	
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static Object deSerializeObjectFromString(String s)
			throws IOException, ClassNotFoundException {

		byte[] data = Base64.getDecoder().decode(s);
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
		Object o = ois.readObject();
		ois.close();
		return o;
	}

}

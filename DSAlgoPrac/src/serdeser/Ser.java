package serdeser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public class Ser {

	public static void main(String[] args) {
		AppleProduct macBook = new AppleProduct();
        macBook.headphonePort = "headphonePort2020";
        macBook.thunderboltPort = "thunderboltPort2020";
        try {
        	String serializedObj = serializeObjectToString(macBook);
        	 
            System.out.println("Serialized AppleProduct object to string:");
            System.out.println(serializedObj);	
        } catch(Exception e) {
        	
        }
	}

	public static String serializeObjectToString(Serializable o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}

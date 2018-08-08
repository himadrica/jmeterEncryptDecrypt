import com.walmart.jmeter.functions.AES;

public class RunAES {

	public static void main(String[] args) {
		if (args == null || args.length < 3) {
			System.out.println("Wrong number of parameters");
			System.exit(1);
		}

		String type = args[0];
		String stringToOperate = args[1];
		String secret = args[2];
		String result = "";
		if(type.toUpperCase().equals("DEC")){
			 result = AES.decrypt(stringToOperate, secret);
		}else if(type.toUpperCase().equals("ENC")){
			 result = AES.encrypt(stringToOperate, secret);
		}else{
			System.out.println("Wrong operation code: Valid only DEC = decrypt, ENC = encrypt");
		}
		
		System.out.println(result);
	}

}

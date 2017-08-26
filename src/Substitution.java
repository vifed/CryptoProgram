import java.io.*;
import java.util.Scanner;

/**
*
* @author Viola Federico
* 
* @category Academic Project / Computer Security
* @version 1.0
*/

public class Substitution {
	
	private static int encryptionKey;
    private static String inputFile;
    private static String encrypted;
    private static String decrypted;
    private static String encryptFileName="Encrypted_File_Subs.txt";
    private static String decryptFileName="Decrypted_File_Subs.txt";
    
    public Substitution() {
		encryptionKey=0;
		inputFile = encrypted = decrypted ="";
	}
		
	public void encode() {
		showMsg(0);
		encryptionKey = checKey();
		
		showMsg(1);
		inputFile = selectFile();
		
		encrypted = encrypt(inputFile, encryptionKey);
		
		saveFile(encrypted, encryptFileName);
		System.out.println("\n\t\t\t\t\t***°°Encoding done successfully!°°***\n\n");
	
	}
	
	public void decode() {
		showMsg(2);
		encryptionKey = checKey();
		
		showMsg(3);
		inputFile = selectFile();
		
		decrypted = decrypt(inputFile, encryptionKey);
		
		saveFile(decrypted, decryptFileName);
		
		System.out.println("\n\t\t\t\t\t***°°Decoding done successfully!°°***\n\n");
		
	}
	
	private static void showMsg(int t) {
		switch(t) {
		case 0: System.out.println("\n\t\t\t\t\t**|Encryption|**\n\t\t\t"
				+ "Insert the Size of the encryption key! The size must be from 0 to 255. \n\t\t\t"
				+ "And make sure that the text file that you want to encrypt is in the folder of the project!!\n");
			break;
		case 1: System.out.println("\n\t\t\tNow write the name of the text file that you want to encrypt: for e.g 'input.txt'\n"
		        + "\t\t\tBut make sure that the file exist inside the folder of the project!\n");
			break;
		case 2: System.out.println("\n\t\t\t\t\t**|Decryption|**\n\t\t\t"
				+ "Now insert the key size from 0-255, the KEY MUST BE THE SAME that you used to encrypt the file!\n"
				+ "\t\t\tInsert your key now:\n");
			break;
		case 3: System.out.println("\n\t\t\tNow write the name of the text file that you want to decrytpt!"
                + "\n\t\t\tBy the default the file name is: 'Encrypted_File_Subs.txt' use that name if you have not modified manually!!\n"
                + "\t\t\tBut make sure that the file exist inside the folder of the project!!");
			break;
		}
	}
	
	private static int checKey() {
		int key;
		Scanner in = new Scanner(System.in);
		System.out.println("Insert your key:\n");

		key = in.nextInt();

		while(key < 0 || key > 255){    
		    System.out.println("\n\t\t\tWrong key inserted, please try again;\nInsert a key between 0 to 255:\n");
		    key = in.nextInt();
		}
		return key;
	}
	
	private static String selectFile() {
		Scanner in = new Scanner(System.in);
		String toPerform="", tmp="";
		Boolean correct=false;
		while(!correct){
		    tmp = in.nextLine();
		    if(!(tmp.isEmpty()))correct = true;
		}
		try{
		    FileReader filereader = new FileReader(tmp);
		    BufferedReader bufferedreader = new BufferedReader(filereader);
		    tmp="";
		    while((tmp = bufferedreader.readLine()) != null ){ 
		            toPerform = toPerform + tmp;
		    }
		    
		}
		catch(IOException er){
		    System.out.println("Error: " + er);
		    System.exit(0);
		}
		return toPerform;
	}
	
	private void saveFile(String s, String f) {
		String toSave = s;
		try{
		    FileOutputStream out = new FileOutputStream(f); 
		    PrintStream write = new PrintStream(out);
		    
		    write.println(toSave);
		    
		}
		catch(IOException ex){
		    System.out.println("Error: " +  ex);
		    System.exit(0);
		}
	}
	
	private static String encrypt(String _input, int _key){ 
		char[] string2char = _input.toCharArray(); 
	    String res="";
	    int cod; 
	    char c;
	       
	    for( int i=0; i< string2char.length; i++) {
	    	c= string2char[i];
	        cod=(int)c; 
	        c = (char)( (cod + _key) %255);  
	        res += c; 
	    }
	        return res;
	}
	
	private static String decrypt(String _input, int _key){
        char[] string2char = _input.toCharArray();
        String res="";
        int cod; 
        char c;
        
        for( int i=0; i< string2char.length; i++){
            c= string2char[i];
            cod=(int)c;
            if(_key <= cod){ 
                res += (c = (char)( (cod - _key))); 
            }
            else{
                int tmp = _key - cod; 
                res += (c = (char)(255- (tmp%256) )); 
            }
        }
        return res;
    }
	
}

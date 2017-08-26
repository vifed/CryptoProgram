import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Viola Federico
 * 
 * @category Academic Project / Computer Security
 * @version 1.0
 */

public class Transposition {
		
	private static String encryptionKey;
    private static String inputFile;
    private static String encrypted;
    private static String decrypted;
    private static String encryptFileName="Encrypted_File_Trans.txt";
    private static String decryptFileName="Decrypted_File_Trans.txt"; 
    
    public Transposition() {
		encryptionKey = inputFile = encrypted = decrypted ="";
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
		case 0: System.out.println("\n\t\t\t\t\t**|Encrytion|**"
				+ "\n\t\t\tNow isert a key to perform the encrypt of the text file!"
				+ "\n\t\t\tThe key must be composed by a letter form 'a' to 'z'\n\n");
			break;
		case 1: System.out.println("\n\t\t\tNow insert the name of the text file that you want to encrypt: "
                + "make sure that the text file is in the same folder of the project!\n");
			break;
		case 2: System.out.println("\n\t\t\t\t\t**|Decrytion|**"
				+ "\n\t\t\tNow isert a key in order to decrypt the message, "
                + "keep attention that the key must be the same used during the encryption process!!\n");
			break;
		case 3: System.out.println("\n\t\t\tNow insert the name of the text file that you want to decrypt: "
				+ "make sure that the file text is in the same folder of the project!"
				+ "\n\t\t\tBy the default the decrypted file name is: 'Encrypted_File_Trans.txt' ");
			break;
		}
	}
    
    private static String checKey() {
    	Scanner in = new Scanner(System.in);
		System.out.println("Insert your key:\n");
		String strKey = in.nextLine();
        strKey = strKey.toLowerCase(); 
        Boolean correct=false;         
        
        while(!correct){
            for(int i=0; i < strKey.length(); i++){
                if(strKey.charAt(i) >= 'a' && strKey.charAt(i) <= 'z') correct = true;
                else correct = false;
            }
            
            if(!correct){
                System.out.println("Wrong key inserted, please try again;\nInsert a key between a to z;\n");
                strKey = in.nextLine();
            }
        }
        return strKey;
	}
    
    private static String selectFile() {
		Scanner in = new Scanner(System.in);
		String toPerform="", tmp="";
		Boolean correct= false;
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
    
    private static String encrypt(String _input, String _key){
        String res1, res2;
        
        res1=divide_str( _input, 0, _input.length()/2); 
        res2=divide_str( _input,  _input.length()/2, _input.length());

        res1= mini_transposition(res1, _key.length()); 
        res2= mini_transposition(res2, _key.length()); 

        
        res1 = reverse_transp(res1, res1.length()); 
        res2 = reverse_transp(res2, res2.length());
        
        res1+=res2; 
        
        return res1;
    }
    
    private static String decrypt(String _input, String _key) {
    	String str1, str2, res; 
        str1 = divide_str( _input, 0, _input.length()/2);
        str2 = divide_str( _input,  _input.length()/2, _input.length()); 
        
        str1 = reverse_transp(str1, str1.length());  
        str2 = reverse_transp(str2, str2.length()); 
        
        str1= mini_transposition(str1, (str1.length()-_key.length())); 
        str2= mini_transposition(str2, (str2.length()-_key.length())); 
        
        str1+=str2;
        
        return str1;
    }
    
    private static String divide_str(String _in, int start, int end){
        String result="";
        for(int i=start; i<end; i++){ 
            result+= _in.charAt(i);
        }
        return result;
    }
    
    private static String mini_transposition(String _input, int _key){
        int size =_input.length(), index=_key, pos; 
        String res="";
        
        pos = index%size;
        for(int i=size-pos; i<size; i++){ 
            res += _input.charAt(i);
        }
        for(int x=0; x<size-pos; x++){ 
            res += _input.charAt(x);    
        }
        return res;
    }
    
    private static String reverse_transp(String _input, int size){ 
        String res="";
        
        for(int i=size-1; i>=0; i--){
            res += _input.charAt(i);
        }

        return res;
    }
    
    
    
}


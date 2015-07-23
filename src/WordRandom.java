import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class WordRandom {

	String path;
	String generateWord;
	String generateExplanation;
	Map<String,String> dicMap;
	Object[] keys;
	public WordRandom(String path) throws IOException{
		this.path=path;
		
		init();
		
		
	}
	private void init() {
		try {
			BufferedReader br=new BufferedReader(new FileReader(new File(path)));
			String line=br.readLine();
			String word = null;
			StringBuffer exp=new StringBuffer();			
			dicMap=new HashMap<String,String>();
			while(line!=null){
			    if(line.contains("Q:")){
			        exp.setLength(0);
			        exp.append("<html><body>");
			        String[] temp=line.split(" ");
		        
			        if(temp.length>=2){
//			            System.out.println(temp[1]);
			            word="<html><body>"+temp[1]+"</body></html>";			            
			        }
			        	
			        line=br.readLine();
			        while(line!=null&& !line.contains("【考法")){
			            line=br.readLine();
			        }
			        while(line!=null&&line.contains("【考法")){
			            exp.append(line);
			            exp.append("<br>");
			            line=br.readLine();
			        }
			        exp.append("</body></html>");
//			        System.out.println(exp.toString());
			        dicMap.put(word, exp.toString());
			    }else{
			        line=br.readLine();
			    }
			}
			keys=dicMap.keySet().toArray();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}
	
	public String[] generateRandom(){
	    String[] result=new String[2];
	    int limit=keys.length;
	    Random random=new Random();
	    int index=random.nextInt(limit);
	    result[0]=keys[index].toString();
	    result[1]=dicMap.get(result[0]);
	    return result;
	    
	}
	public void removeElement(String word){
	    dicMap.remove(word);
	}
	public static void main(String[] args) throws IOException {
		WordRandom wordRandom=new WordRandom("materials/words2.txt");
//		System.out.println(wordRandom.dicMap.size());
	System.out.println(wordRandom.generateRandom()[1]);
	
	}
}

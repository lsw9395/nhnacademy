
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Scanner;

public class Dictionary{
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("./words.txt")));
        String line ="";
        String[] lines;
        String value;
        Hashtable<String, String> table = new Hashtable<>();
        while((line=reader.readLine())!=null){
            int target = line.indexOf('.');
            line = line.substring(target+1).trim();
            value = line;
            line = line.replaceAll("[^a-zA-Z()]"," ");
            line = line.replaceFirst("\\s{2,}","/").trim();
            lines = line.split("/");
            value = value.replaceFirst(lines[0],"").trim();
            value = value.replaceFirst("\\w{1,}(?:[(]|[-])(.*?)(?:[)]|.)\\w{1,}","").trim();
            lines[0] = lines[0].replaceAll("[()]","");
            table.put(lines[0],value);
        }
        String key = "";
        Scanner sc = new Scanner(System.in);
        while(!Thread.interrupted()){
            System.out.print("NHN 사전입니다. 검색할 단어를 입력해주세요.: ");
            key = sc.nextLine().trim();
            if(table.containsKey(key)){
                System.out.print("검색 결과: ");
                System.out.println(table.get(key));
            } else if(key.equals("exit()")){
                System.out.println("프로그램을 종료합니다."); return ;
            } else {
                System.out.println("사전에 존재하지 않는 단어입니다.");
            }
        }
    }
}

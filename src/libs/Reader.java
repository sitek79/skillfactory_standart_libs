package libs;

import java.io.FileReader;
import java.io.IOException;

public class Reader {
    char cch;

    public char readFile(String src) {
        try(FileReader reader = new FileReader(src))
        {
            // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){
                //System.out.print((char)c);
                char cch = (char)c;
                System.out.print(cch);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        //System.out.println(cch);
        return cch;
    }
}

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PhoneBook {
    HashMap<String,String> phoneBook = new HashMap<>();

    public void add(String number,String name){
        phoneBook.put(number,name);

    }

    public void get (String name){
        Iterator it = phoneBook.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            if (pair.getValue().equals(name)){
                System.out.println("Найдено: "+ pair.getKey() +" "+ pair.getValue());
            }

        }

    }
}

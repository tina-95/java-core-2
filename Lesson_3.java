import java.util.*;

public class Lesson_3 {
    public static void main(String[] args) {
        System.out.println("Задание 1");
        ArrayList<String> words = new ArrayList<>();
        words.add("А");
        words.add("Б");
        words.add("В");
        words.add("А");
        words.add("А");
        words.add("Г");
        words.add("Г");
        words.add("Ж");
        words.add("Е");
        words.add("З");
        words.add("Л");
        words.add("Д");
        words.add("Ф");
        words.add("А");
        words.add("Л");


        Map<String,Integer> unique = new HashMap<>();
        for (String word : words){
            if ( unique.containsKey(word)){
                int a = unique.get(word);
                a++;
                unique.replace(word,a);
            } else {
                unique.put(word,1);
            }

        }

        for (Map.Entry pair : unique.entrySet()){
            System.out.println(pair.getKey() +" встречается " + pair.getValue() +" раз");
        }


        System.out.println("Задание 2");

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add( "899", "Сидоров");
        phoneBook.add( "98888", "Петров");
        phoneBook.add( "890", "Сидоров");
        phoneBook.add( "8888", "Иванов");

        phoneBook.get("Сидоров");
    }
}

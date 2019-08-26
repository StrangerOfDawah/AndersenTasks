package main.java.lambda;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class lambdaTask {
    public static void main(String[] args) throws IOException {

     /*   String pathToDespair = "C://Users//Stranger//IdeaProjects//AndersenTask//src//main//java//lambda//t1024";
        String symbol = "a";
        Map<Character, Long> result = Files.lines(Paths.get(pathToDespair))
                .flatMap()
                .filter(symbol::equals).count();
        System.out.println(count);


*/
        System.out.println((String)  Just.get(String.class));




    }

    static class Just{


        public static <T> T get(Object obg){
            return (T) obg;

        }

    }
}




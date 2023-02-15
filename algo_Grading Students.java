import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    private static final int PASS_GRADE = 40; // passing grade
    private static final int MODULO = 5; // the rounding factor
    private static final int MAX_MODULO = 3;

    /*
     * Complete the 'gradingStudents' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY grades as parameter.
     */

    public static List<Integer> gradingStudents(List<Integer> grades) {
    // Write your code here
    List<Integer> result = new ArrayList<Integer> ();
    for(int i = 0; i < grades.size(); i++)
    {
        int modulo = grades.get(i) % MODULO; // get modulo 5 of current grade (this will get the difference of grade and previous multiple of 5)
        if(grades.get(i) + MODULO < PASS_GRADE) // if even adding the maximum (MODULO itself) is less than 40, no rounding
        {
            result.add(grades.get(i));
        }
        else if(modulo < MAX_MODULO) // if modulo is less than 3 which means the difference between next multiple of 5 is greater than 3, no rounding
        {
            result.add(grades.get(i));
        }
        else if(grades.get(i) % MODULO >= MAX_MODULO) // if modulo is more than or equal to 3 which means the difference between next multiple of 5 is less than 3, we round
        {
            result.add(grades.get(i) + (MODULO - modulo));
        }
    }
    
    return result;
    }

}

// PRODUCED BY HACKERRANK NOT WRITTEN BY ME

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int gradesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> grades = IntStream.range(0, gradesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.gradingStudents(grades);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
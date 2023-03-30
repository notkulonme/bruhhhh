import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String fullFile = ReadFromFile();
        System.out.println(fullFile);

    }
    public static String ReadFromFile() throws FileNotFoundException {
        String fullFile = "";
        Scanner sysin = new Scanner(System.in);
        System.out.println("the location of the file:");
        String fileLocation = sysin.nextLine();
        File file = new File(fileLocation);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine())
        {
            String Line = scanner.nextLine();
            fullFile = fullFile +" "+ Line;
        }
        fullFile = convertStringToBinary(fullFile);
        return fullFile;
    }




    public static String convertStringToBinary(String input) {

        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            result.append(
                    String.format("%8s", Integer.toBinaryString(aChar))   // char -> int, auto-cast
                            .replaceAll(" ", "0")                         // zero pads
            );
        }
        return prettyBinary(result.toString(), 8, " ");
    }
    public static String prettyBinary(String binary, int blockSize, String separator) {

        ArrayList<String> result = new ArrayList<>();
        int index = 0;
        while (index < binary.length()) {
            result.add(binary.substring(index, Math.min(index + blockSize, binary.length())));
            index += blockSize;
        }

        return result.stream().collect(Collectors.joining(separator));
    }
}
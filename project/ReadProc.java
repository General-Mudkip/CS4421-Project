import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class ReadProc {
    public static void OutputProc() throws InterruptedException {
        while (true) {
            try {
                File proc = new File("/proc/cpuinfo");
                Scanner reader = new Scanner(proc);

                Thread.sleep(100);

                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    // The file lines have three pieces of info (usually):
                    // [Name of data] [a lot of spaces] [Data] [unit (optional)]
                    // In this case we couldn't use a single space as the delimiter, since that'd
                    // split the line into a ton of empty strings. The following code splits the
                    // line by whitespace, regardless of the length of the whitespace.
                    String[] split = data.split(":");

                    split[0] = split[0].replaceAll("\\s+", "");
                    split[1] = split[1].trim();

                    if (split[0].equals("cpuMHz")) {
                        System.out.println(split[0] + ": " + split[1]);
                        break;
                    }
                }

                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error occured!.");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OutputProc();
    }
}

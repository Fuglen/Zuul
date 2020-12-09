package data;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileBackend {
    String filename;

    public FileBackend (String filename) {
        this.filename = filename;
    }

    public boolean store (List<String> entries) {
        try {
            File file = new File(filename);
            PrintWriter pw   = new PrintWriter(file);

            for (String entry: entries) {
                pw.println(entry);
            }

            pw.close();
        } catch (Exception ex) {
            return false;
        }

        return true;
    }

    public List<String> load () {
        List<String> entries = new ArrayList<String>();

        try {
            File    file    = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                entries.add(scanner.nextLine());
            }

            scanner.close();
        } catch (Exception e) {
            return null;
        }

        return entries;
    }
}

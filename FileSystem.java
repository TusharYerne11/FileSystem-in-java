package FileSystem;

import java.io.*;
import java.util.Scanner;

public class FileSystem {
    private static final String DIRECTORY = "fileSystem/";
    private int fileCounter; // Variable to count the number of files added during this session

    public FileSystem() {
        // Create directory if it doesn't exist
        File dir = new File(DIRECTORY);
        if (!dir.exists()) {
            dir.mkdir();
        }

        // Initialize the file counter to 0
        this.fileCounter = 0;  // Start counting from 0
    }

    // Create or write to a file
    public void createFile(String fileName, String content) throws IOException {
        File file = new File(DIRECTORY + fileName);

        if (file.exists()) {
            System.out.println("File already exists. Writing new content to the file...");
        } else {
            fileCounter++; // Increment the counter when a new file is created
        }

        FileWriter writer = new FileWriter(file);
        writer.write(content);
        writer.close();
        System.out.println("File created/updated successfully: " + fileName);
    }

    // Read a file
    public void readFile(String fileName) throws IOException {
        File file = new File(DIRECTORY + fileName);
        if (!file.exists()) {
            System.out.println("File not found: " + fileName);
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        System.out.println("Reading file: " + fileName);
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }

    // Update a file
    public void updateFile(String fileName, String newContent) throws IOException {
        File file = new File(DIRECTORY + fileName);
        if (!file.exists()) {
            System.out.println("File not found: " + fileName);
            return;
        }
        FileWriter writer = new FileWriter(file);
        writer.write(newContent);
        writer.close();
        System.out.println("File updated successfully: " + fileName);
    }

    // Delete a file
    public void deleteFile(String fileName) {
        File file = new File(DIRECTORY + fileName);
        if (!file.exists()) {
            System.out.println("File not found: " + fileName);
            return;
        }

        if (file.delete()) {
            fileCounter--; // Decrement the counter when a file is deleted
            System.out.println("File deleted successfully: " + fileName);
        } else {
            System.out.println("Failed to delete file: " + fileName);
        }
    }

    // Display the number of files added during the current session
    public void displayFileCount() {
        System.out.println("Number of files added during this session: " + fileCounter);
    }

    // Main method to test the FileSystem
    public static void main(String[] args) throws IOException {
        FileSystem fs = new FileSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nFile System Operations: ");
            System.out.println("1. Create File");
            System.out.println("2. Read File");
            System.out.println("3. Update File");
            System.out.println("4. Delete File");
            System.out.println("5. Display File Count");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter file name: ");
                    String createFileName = scanner.nextLine();
                    System.out.print("Enter file content: ");
                    String content = scanner.nextLine();
                    fs.createFile(createFileName, content);
                    break;
                case 2:
                    System.out.print("Enter file name: ");
                    String readFileName = scanner.nextLine();
                    fs.readFile(readFileName);
                    break;
                case 3:
                    System.out.print("Enter file name: ");
                    String updateFileName = scanner.nextLine();
                    System.out.print("Enter new file content: ");
                    String newContent = scanner.nextLine();
                    fs.updateFile(updateFileName, newContent);
                    break;
                case 4:
                    System.out.print("Enter file name: ");
                    String deleteFileName = scanner.nextLine();
                    fs.deleteFile(deleteFileName);
                    break;
                case 5:
                    fs.displayFileCount();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

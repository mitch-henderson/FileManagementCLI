import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("File Manager Started. Enter 'help' to display available commands.");
        
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] commands = input.split(" ");
            switch (commands[0].toLowerCase()) {
                case "list":
                    listFilesInDirectory(".");
                    break;
                case "create":
                    if (commands.length > 1) {
                        createNewFile(commands[1]);
                    } else {
                        System.out.println("Error: Missing file name.");
                    }
                    break;
                case "delete":
                    if (commands.length > 1) {
                        deleteFile(commands[1]);
                    } else {
                        System.out.println("Error: Missing file name.");
                    }
                    break;
                case "help":
                    printHelp();
                    break;
                case "exit":
                    System.out.println("Exiting File Manager.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command. Enter 'help' to display available commands.");
                    break;
            }
        }
    }

    private static void listFilesInDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null && files.length > 0) {
            System.out.println("Files in directory '" + directoryPath + "':");
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("The directory is empty or does not exist.");
        }
    }

    private static void createNewFile(String fileName) {
        File file = new File(fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the file, or file does not exist.");
        }
    }

    private static void printHelp() {
        System.out.println("Available commands:");
        System.out.println("list - Lists all files in the current directory.");
        System.out.println("create <file name> - Creates a new file with the given name.");
        System.out.println("delete <file name> - Deletes the file with the given name.");
        System.out.println("exit - Exits the program.");
        System.out.println("help - Displays this help message.");
    }
}

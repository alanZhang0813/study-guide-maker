package cs3500.pa01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Class to process a markdown file
 */
public class MdFileProcessor {
  private ArrayList<MyFile> listOfFiles;
  private String newContent;
  private String content;
  private String origin;
  private String orderingFlag;
  private String destination;

  MdFileProcessor(ArrayList<MyFile> listOfFiles, String origin, String orderingFlag,
                  String destination)
      throws IOException {
    this.listOfFiles = listOfFiles;
    this.origin = origin;
    this.orderingFlag = orderingFlag;
    this.destination = destination;

    //block of code to arrange the list of files accordingly, and then operate on that list
    this.sort(orderingFlag);

    //generates a String result composed of all the file objects
    content = this.buildString();

    //filter out the strings
    newContent = this.buildNewString(content);
  }

  /**
   * Method for compiling all the strings in the file path
   *
   * @return String
   */
  public String buildString() {
    Scanner scanner = null;
    StringBuilder sb = new StringBuilder();
    for (MyFile mf : listOfFiles) {
      Path path = Path.of(mf.getFile().toString());
      try {
        scanner = new Scanner(path);
        while (scanner.hasNext()) {
          String nextLine = scanner.nextLine();
          sb.append(nextLine);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

      sb.append("\n");
      sb.append("\n");
    }

    return sb.toString();
  }

  /**
   * Method for creating the new string with hashtags and no brackets
   *
   * @param content Provided content of the original files
   * @return String
   */
  public String buildNewString(String content) {
    String[] contentAsLines = content.split("\n"); //splits each line into a new item
    StringBuilder result = new StringBuilder();
    int rowNum = 0;

    for (String s : contentAsLines) {
      if (s.startsWith("#")) {
        if (rowNum != 0) {
          result.append("\n");
        }
        result.append(s);
        result.append("\n");
      }

      System.out.println(result.toString());
      while (s.contains("[[") && s.contains("]]")) {
        int openBracketIndex = s.indexOf("[[");
        int closeBracketIndex = s.indexOf("]]");
        result.append("- ");
        result.append(s.substring(openBracketIndex + 2, closeBracketIndex));
        result.append("\n");
        s = s.substring(closeBracketIndex + 1);
      }

      rowNum++;
    }

    return result.toString();
  }

  /**
   * Generates the resulting study guide
   *
   * @throws IOException If the destination is not found
   */
  public void generateStudyGuide() throws IOException {
    //block of code for outputting the result in the designated folder and as an .md file
    FileWriter fw = null;
    String fileName = "Study Guide.md";
    File dest = new File(destination);
    File result = new File(dest, fileName);
    fw = new FileWriter(result);

    System.out.println(newContent);
    fw.write(newContent);
    fw.close();
  }

  /**
   *
   * @param orderingFlag String
   * @throws IOException If the provided string does not fit
   */
  public void sort(String orderingFlag) throws IOException {
    if (orderingFlag.equals("filename")) {
      Collections.sort(listOfFiles, new NameComparator());
    } else if (orderingFlag.equals("created")) {
      Collections.sort(listOfFiles, new CreateComparator());
    } else if (orderingFlag.equals("modified")) {
      Collections.sort(listOfFiles, new ModifyComparator());
    } else {
      throw new IOException(
          "Ordering flag string must be: filename, created, or modified");
    }
  }

  public ArrayList<MyFile> getListOfFiles() {
    return listOfFiles;
  }

  public void setListOfFiles(ArrayList<MyFile> listOfFiles) {
    this.listOfFiles = listOfFiles;
  }

  public String getNewContent() {
    return newContent;
  }

  public void setNewContent(String newContent) {
    this.newContent = newContent;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getOrderingFlag() {
    return orderingFlag;
  }

  public void setOrderingFlag(String orderingFlag) {
    this.orderingFlag = orderingFlag;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }
}
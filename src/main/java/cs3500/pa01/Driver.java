package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) throws IOException {
    ArrayList<MyFile> listOfFiles = new ArrayList<>();

    String origin = args[0];
    String orderingFlag = args[1];
    String destination = args[2];

    MdFileVisitor mdfv = new MdFileVisitor(listOfFiles);

    Path p = Path.of(origin);
    Files.walkFileTree(p, mdfv);

    MdFileProcessor mdfp = new MdFileProcessor(mdfv.getList(), origin, orderingFlag, destination);
    mdfp.generateStudyGuide();
  }
}
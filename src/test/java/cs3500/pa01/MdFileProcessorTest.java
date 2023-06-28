package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to represent processing a Markdown File
 */
public class MdFileProcessorTest {
  MdFileProcessor mdfp;
  MdFileProcessor mdfpDateCreated;
  MdFileProcessor mdfpDateModified;

  final MyFile mf1 = new MyFile();
  final MyFile mf2 = new MyFile();
  ArrayList<MyFile> filesList;

  /**
   * Class for resetting before every test
   *
   * @throws IOException if the file is not found or is filename is not allowed
   */
  @BeforeEach
  public void setup() throws IOException {
    filesList = new ArrayList<>();
    mdfp = new MdFileProcessor(new ArrayList<>(),
        "src/test/resources/{inputs}",
        "filename",
        "src/test/resources/{inputs}");
    mdfpDateCreated = new MdFileProcessor(new ArrayList<>(),
        "src/test/resources/{inputs}",
        "created",
        "src/test/resources/{inputs}");
    mdfpDateModified = new MdFileProcessor(new ArrayList<>(),
        "src/test/resources/{inputs}",
        "modified",
        "src/test/resources/{inputs}");
  }

  @Test
  public void buildString() {
    mdfp.setListOfFiles(filesList);
    mf1.setFile(Path.of("src/test/resources/{outputs}/MF1.md"));
    mf2.setFile(Path.of("src/test/resources/{outputs}/MF2.md"));
    filesList.add(mf1);
    filesList.add(mf2);
    String result = mdfp.buildString();
    assertEquals("Hey there how's it goin\n"
        + "\nIt's going well" + "\n" + "\n", result);
  }

  /*
  Cam the TA said this exception would be too hard to test

  @Test
  public void buildStringException() {
    mdfp.setListOfFiles(filesList);
    mf1.setFile(Path.of("src/test/resources/{inputs}/MF1.md"));
    mf2.setFile(Path.of("src/test/resources/{outputs}/MF2.md"));
    filesList.add(mf1);
    filesList.add(mf2);
    String result = mdfp.buildString();

    assertEquals("", result);
  }
   */

  @Test
  public void buildNewString() {
    String content = "## Creating an Array (Instantiation)\n"
        + "- [[General form:  arrayName = new type[numberOfElements];]]\n"
        + "- ex: myData = new int[100];\n \n"
        + "- Data types of the reference and array need to match. \n"
        + "- [[numberOfElements must be a positive Integer.]]\n"
        + "- [[Gotcha: Array size is not modifiable once instantiated.]]";
    String newResult = mdfp.buildNewString(content);
    String expected = "## Creating an Array (Instantiation)\n"
        + "- General form:  arrayName = new type[numberOfElements];\n"
        + "- numberOfElements must be a positive Integer.\n"
        + "- Gotcha: Array size is not modifiable once instantiated."
        + "\n";

    assertEquals(expected, newResult);
  }

  @Test
  public void orderingFlagException() {
    try {
      mdfp = new MdFileProcessor(
          new ArrayList<>(),
          "src/test/resources/{inputs}",
          "something else",
          "src/test/resources/{inputs}");
      fail("Exception not thrown");
    } catch (IOException e) {
      assertEquals(
          "Ordering flag string must be: filename, created, or modified",
          e.getMessage());
    }
  }

  @Test
  public void sortListCreated() throws IOException {
    mdfpDateCreated.setListOfFiles(filesList);
    mf1.setDateCreated(FileTime.from(Instant.parse("2007-12-03T10:15:30Z")));
    mf2.setDateCreated(FileTime.from(Instant.parse("2008-12-03T10:15:30Z")));
    filesList.add(mf1);
    filesList.add(mf2);
    mdfpDateCreated.sort(mdfpDateCreated.getOrderingFlag());
    assertEquals(mf1, filesList.get(0));
    assertEquals(mf2, filesList.get(1));
  }

  @Test
  public void sortListModified() throws IOException {
    mdfpDateModified.setListOfFiles(filesList);
    mf1.setDateModified(FileTime.from(Instant.parse("2008-12-03T10:15:30Z")));
    mf2.setDateModified(FileTime.from(Instant.parse("2007-12-03T10:15:30Z")));
    filesList.add(mf1);
    filesList.add(mf2);
    mdfpDateModified.sort(mdfpDateModified.getOrderingFlag());
    assertEquals(mf2, filesList.get(0));
    assertEquals(mf1, filesList.get(1));
  }

  @Test
  public void generateStudyGuide() throws IOException {
    mdfp.setContent("# Java Arrays\n"
        + "- [[An **array** is a collection of variables of the same type]], referred to \n"
        + "by a common name. \n- In Java, arrays are objects, and must be created dynamically "
        + "(at runtime).\n\n## Declaring an Array\n- [[General Form: type[] arrayName;]]\n"
        + "- ex: int[] myData;\n\n- The above [[only creates a reference]] to an array object, "
        + "but [[no array has actually been created yet]]. ");
    mdfp.setNewContent(mdfp.buildNewString(mdfp.getContent()));
    mdfp.generateStudyGuide();
    assertEquals("# Java Arrays\n- An **array** is a collection of variables of the same "
            + "type\n\n## Declaring an Array\n- General Form: type[] arrayName;\n"
            + "- only creates a reference\n- no array has actually been created yet\n",
        mdfp.getNewContent());
  }

  @Test
  public void testGenerateException() {
    mdfp.setDestination("The Moon");
    mdfp.setContent("# Java Arrays\n"
        + "- [[An **array** is a collection of variables of the same type]], referred to \n"
        + "by a common name. \n"
        + "- In Java, arrays are objects, and must be created dynamically (at runtime).");
    mdfp.setNewContent(mdfp.buildNewString(mdfp.getContent()));
    assertThrows(FileNotFoundException.class,
        () -> mdfp.generateStudyGuide());
  }

  @Test
  public void setListOfFiles() {
    mdfp.setListOfFiles(new ArrayList<>());
    assertEquals(new ArrayList<>(), mdfp.getListOfFiles());
  }

  @Test
  public void getListOfFiles() {
    mdfp.setListOfFiles(new ArrayList<>());
    assertEquals(new ArrayList<>(), mdfp.getListOfFiles());
  }

  @Test
  public void setOrigin() {
    mdfp.setOrigin("newOrigin");
    assertEquals("newOrigin", mdfp.getOrigin());
  }

  @Test
  public void getOrigin() {
    mdfp.setOrigin("newOrigin");
    assertEquals("newOrigin", mdfp.getOrigin());
  }

  @Test
  public void setOrderingFlag() {
    mdfp.setOrderingFlag("created");
    assertEquals("created", mdfp.getOrderingFlag());
  }

  @Test
  public void getOrderingFlag() {
    mdfp.setOrderingFlag("created");
    assertEquals("created", mdfp.getOrderingFlag());
  }

  @Test
  public void setDestination() {
    mdfp.setDestination("The Moon");
    assertEquals("The Moon", mdfp.getDestination());
  }

  @Test
  public void getDestination() {
    mdfp.setDestination("The Moon");
    assertEquals("The Moon", mdfp.getDestination());
  }

  @Test
  public void setNewContent() {
    mdfp.setNewContent("A lot of cool strings");
    assertEquals("A lot of cool strings", mdfp.getNewContent());
  }

  @Test
  public void getNewContent() {
    mdfp.setNewContent("A lot of cool strings");
    assertEquals("A lot of cool strings", mdfp.getNewContent());
  }

  @Test
  public void setContent() {
    mdfp.setContent("Even cooler strings");
    assertEquals("Even cooler strings", mdfp.getContent());
  }

  @Test
  public void getContent() {
    mdfp.setContent("Even cooler strings");
    assertEquals("Even cooler strings", mdfp.getContent());
  }
}

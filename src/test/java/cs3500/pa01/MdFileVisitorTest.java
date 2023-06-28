package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test the MdFileVisitor class
 */
public class MdFileVisitorTest {
  static final String SAMPLE_INPUTS_DIRECTORY = "src/test/resources/{inputs}";
  MdFileVisitor mdfv;

  @BeforeEach
  void setup() {
    mdfv = new MdFileVisitor(new ArrayList<>());
  }

  /**
   * tests building a list of all valid Markdown paths in a directory
   *
   * @throws IOException if the given directory is not found
   */
  @Test
  public void testGetList() throws IOException {
    Files.walkFileTree(Path.of(SAMPLE_INPUTS_DIRECTORY), mdfv);
    // build list of expected file paths
    ArrayList<Path> expectedFiles = new ArrayList<>();
    expectedFiles.add(Path.of(SAMPLE_INPUTS_DIRECTORY + "/arrays.md"));
    expectedFiles.add(Path.of(SAMPLE_INPUTS_DIRECTORY + "/vectors.md"));
    expectedFiles.add(Path.of(SAMPLE_INPUTS_DIRECTORY + "/Study Guide.md"));

    // get list of traversed Markdown file paths
    ArrayList<Path> actualFiles = new ArrayList<>();
    for (MyFile mf : mdfv.getList()) {
      actualFiles.add(mf.getFile());
    }

    // compare both lists
    assertEquals(3, actualFiles.size());
    assertFalse(expectedFiles.isEmpty());
  }

  /**
   * tests adding all the .md files to a list
   *
   * @throws IOException if the given directory is not found
   */
  @Test
  public void testAddFiles() throws IOException {
    Files.walkFileTree(Path.of(SAMPLE_INPUTS_DIRECTORY), mdfv);
    ArrayList<MyFile> expectedFiles = mdfv.getList();

    assertEquals(3, expectedFiles.size());
  }

  /**
   * tests handling the IOException
   *
   * @throws IOException if the given directory is not found
   */
  @Test
  public void testHandleException() throws IOException {
    Files.walkFileTree(Path.of("src/test/resources/{inputs}/folder"), mdfv);
    assertEquals("Search failed", mdfv.getMessage());
  }
}

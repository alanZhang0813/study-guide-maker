package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing the MyFile class
 */
public class MyFileTest {
  MyFile mf;

  @BeforeEach
  void setup() {
    mf = new MyFile();
  }

  @Test
  void getFileName() {
    mf.setFile(Paths.get("abc"));
    assertEquals(Paths.get("abc"), mf.getFile());
  }

  @Test
  void setFileName() {
    mf.setFile(Paths.get("bcd"));
    assertEquals(Paths.get("bcd"), mf.getFile());
  }

  @Test
  void getDateCreated() {
    CharSequence text1 = "2007-12-03T10:15:30Z";
    mf.setDateCreated(FileTime.from(Instant.parse(text1)));
    assertEquals(FileTime.from(Instant.parse("2007-12-03T10:15:30Z")), mf.getDateCreated());
  }

  @Test
  void setDateCreated() {
    CharSequence text1 = "2007-12-03T10:15:30Z";
    mf.setDateCreated(FileTime.from(Instant.parse(text1)));
    assertEquals(FileTime.from(Instant.parse("2007-12-03T10:15:30Z")), mf.getDateCreated());
  }

  @Test
  void getDateModified() {
    CharSequence text1 = "2007-12-03T10:15:30Z";
    mf.setDateModified(FileTime.from(Instant.parse(text1)));
    assertEquals(FileTime.from(Instant.parse("2007-12-03T10:15:30Z")), mf.getDateModified());
  }

  @Test
  void setDateModified() {
    CharSequence text1 = "2007-12-03T10:15:30Z";
    mf.setDateModified(FileTime.from(Instant.parse(text1)));
    assertEquals(FileTime.from(Instant.parse("2007-12-03T10:15:30Z")), mf.getDateModified());
  }
}
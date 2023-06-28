package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModifyComparatorTest {
  ModifyComparator mc;
  MyFile mf1 = new MyFile();
  MyFile mf2 = new MyFile();
  MyFile mf3 = new MyFile();
  MyFile mf4 = new MyFile();

  @BeforeEach
  void setup() {
    mc = new ModifyComparator();
  }

  @Test
  void compare() {
    final CharSequence text1 = "2007-12-03T10:15:30.00Z";
    final CharSequence text2 = "2008-12-03T10:15:30.00Z";
    final CharSequence text3 = "2009-12-03T10:15:30.00Z";
    final CharSequence text4 = "2007-12-03T10:15:30.00Z";
    mf1.setDateModified(FileTime.from(Instant.parse(text1)));
    mf2.setDateModified(FileTime.from(Instant.parse(text2)));
    mf3.setDateModified(FileTime.from(Instant.parse(text3)));
    mf4.setDateModified(FileTime.from(Instant.parse(text4)));
    assertEquals(-1, mc.compare(mf1, mf2));
    assertEquals(1, mc.compare(mf3, mf1));
    assertEquals(0, mc.compare(mf1, mf4));
  }
}
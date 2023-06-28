package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NameComparatorTest {
  NameComparator nc;
  MyFile mf1 = new MyFile();
  MyFile mf2 = new MyFile();
  MyFile mf3 = new MyFile();
  MyFile mf4 = new MyFile();

  @BeforeEach
  void setup() {
    nc = new NameComparator();
  }

  @Test
  void compare() {
    mf1.setFile(Paths.get("abc"));
    mf2.setFile(Paths.get("bcd"));
    mf3.setFile(Paths.get("cde"));
    mf4.setFile(Paths.get("abc"));
    assertEquals(-1, nc.compare(mf1, mf2));
    assertTrue(nc.compare(mf3, mf1) > 0);
    assertEquals(0, nc.compare(mf1, mf4));
  }
}
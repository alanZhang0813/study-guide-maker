package cs3500.pa01;

import java.util.Comparator;

/**
 * Class for testing the ModifyComparator class
 */
public class ModifyComparator implements Comparator<MyFile> {
  @Override
  public int compare(MyFile f1, MyFile f2) {
    return f1.getDateModified().compareTo(f2.getDateModified());
  }
}

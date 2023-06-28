package cs3500.pa01;

import java.util.Comparator;

/**
 * Class for testing the CreateComparator class
 */
public class CreateComparator implements Comparator<MyFile> {
  @Override
  public int compare(MyFile f1, MyFile f2) {
    return f1.getDateCreated().compareTo(f2.getDateCreated());
  }
}

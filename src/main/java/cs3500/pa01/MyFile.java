package cs3500.pa01;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

/**
 * Class for handling the Path information as well as
 * FileTime information for dateCreated and dateModified
 */
public class MyFile {
  private Path file;
  private FileTime dateCreated;
  private FileTime dateModified;

  MyFile() {}

  public Path getFile() {
    return file;
  }

  public void setFile(Path file) {
    this.file = file;
  }

  public FileTime getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(FileTime dateCreated) {
    this.dateCreated = dateCreated;
  }

  public FileTime getDateModified() {
    return dateModified;
  }

  public void setDateModified(FileTime dateModified) {
    this.dateModified = dateModified;
  }
}

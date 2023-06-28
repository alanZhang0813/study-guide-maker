package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class DriverTest {
  Driver driver = new Driver();
  String[] args;

  @Test
  public void testMain() throws IOException {
    args = new String[] {
        "src/test/resources/{inputs}",
        "created",
        "src/test/resources/{inputs}"};
    Driver.main(args);

    assertEquals(
        4,
        Path.of("src/test/resources/{inputs}").getNameCount());
  }

  @Test
  public void testArgsException() {
    args = new String[] {"src/test/resources/{inputs}", "created"};
  }
}
package org.alveo;

import org.junit.Test;
import static org.junit.Assert.*;

public class ServerTest{
  @Test public void testWorks(){
    assertEquals(1, new Server().test(), 1);
  }
}

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package eu.mihosoft.nativefx;

import org.junit.Test;
import static org.junit.Assert.*;

public class NativeBindingTest {
    @Test public void testNativeMethodsSendMsg() {
        NativeBinding.init();
        String response = NativeBinding.sendMsg(0, "hello native");
        assertTrue("sendMsg should return 'hello from native'", "hello from native!".equals(response));
    }
}

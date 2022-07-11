package sunw.demo.transitional;

import java.util.*;

/**
 * This interface describes the method that gets called when
 * an OurButton gets pushed.
 */

public interface ButtonPushListener extends EventListener
{

    public void push(ButtonPushEvent e);

}

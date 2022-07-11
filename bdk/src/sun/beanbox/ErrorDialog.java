
/**
 * Pop up a (modal) error dialog and wait for a user to press "continue".
 */

package sun.beanbox;

import java.awt.*;

public class ErrorDialog extends MessageDialog
{

    /**
     *
     */
    private static final long serialVersionUID = 9090360392260724822L;

    public ErrorDialog(Frame frame, String message)
    {
        super(frame, "Error", message);
    }

}

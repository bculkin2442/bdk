
/**
 * Pop up a (modal) dialog that talks about the BeanBox.
 */

package sun.beanbox;

import java.awt.*;

public class AboutDialog extends MessageDialog
{
    /**
     *
     */
    private static final long serialVersionUID = 6860417408543855519L;
    private final static String message = "Beans Development Kit 1.0 ["
            + BeanBoxFrame.getVersionID() + "]\n" + "\n" + "BeanBox\n"
            + "Reginald Adkins, Graham Hamilton, Eduardo Pelegri-Llopart\n" + "\n"
            + "Example Beans\n"
            + "Larry Cable, Jerome Dochez, Graham Hamilton, Hans Muller\n" + "\n"
            + "Quality Assurance\n" + "Stuart Moore\n" + "\n"
            + "Tutorial & Documentation\n" + "Alden Desoto, Andrew Quinn\n" + "\n"
            + "Fearless Product Marketing\n"
            + "Gina Centoni, Onno Kluyt, Frank Rimalovski\n" + "\n";

    public AboutDialog(Frame frame)
    {
        super(frame, "About", message);
    }

}

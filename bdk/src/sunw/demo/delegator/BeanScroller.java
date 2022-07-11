
/*
 * This bean composes a bean and a ScrollPane.
 */

package sunw.demo.delegator;

import java.applet.*;
import java.awt.*;

public class BeanScroller extends ScrollPane
{

    /**
     *
     */
    private static final long serialVersionUID = -9020037925087394530L;
    private Component bean;
    private transient boolean started;

    public BeanScroller()
    {
    }

    public void slurp(Component b) {
        System.err.println("BeanScroller.slurp");
        bean = b;
        add(bean);
        if (bean instanceof Applet)
        {
            ((Applet) bean).start();
        }
    }

    @Override
    public void doLayout() {
        super.doLayout();
        if (!started && bean instanceof Applet)
        {
            Applet applet = (Applet) bean;
            applet.start();
            started = true;
        }
    }

    public void setPaneBackground(Color c) {
        if (bean == null)
        {
            return;
        }
        bean.setBackground(c);
    }

    public Color getPaneBackground() {
        if (bean == null)
        {
            return getBackground();
        }
        return bean.getBackground();
    }
}

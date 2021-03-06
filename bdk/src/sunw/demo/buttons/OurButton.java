
package sunw.demo.buttons;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;

/**
 * A simple Java Beans button. OurButton is a "from-scratch"
 * lightweight AWT component. It's a good example of how to
 * implement bound properties and support for event listeners.
 *
 * Parts of the source are derived from sun.awt.tiny.TinyButtonPeer.
 */

public class OurButton extends Component
implements MouseListener, MouseMotionListener
{

    /**
     *
     */
    private static final long serialVersionUID = -1747362668176872302L;

    /**
     * Constructs a Button with the a default label.
     */
    public OurButton()
    {
        this("press");
    }

    /**
     * Constructs a Button with the specified label.
     *
     * @param label the label of the button
     */
    public OurButton(String label)
    {
        super();
        this.label = label;
        setFont(new Font("Dialog", Font.PLAIN, 12));
        setBackground(Color.lightGray);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    // ----------------------------------------------------------------------

    /**
     * Paint the button: the label is centered in both dimensions.
     *
     */
    @Override
    public synchronized void paint(Graphics g) {
        int width = getSize().width;
        int height = getSize().height;

        g.setColor(getBackground());
        g.fill3DRect(0, 0, width - 1, height - 1, !down);

        g.setColor(getForeground());
        g.setFont(getFont());

        g.drawRect(2, 2, width - 4, height - 4);

        FontMetrics fm = g.getFontMetrics();
        g.drawString(label, (width - fm.stringWidth(label)) / 2,
                (height + fm.getMaxAscent() - fm.getMaxDescent()) / 2);
    }

    // ----------------------------------------------------------------------

    // Mouse listener methods.

    @Override
    public void mouseClicked(MouseEvent evt) {
    }

    @Override
    public void mousePressed(MouseEvent evt) {
        if (!isEnabled())
        {
            return;
        }
        down = true;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent evt) {
        if (!isEnabled())
        {
            return;
        }
        if (down)
        {
            fireAction();
            down = false;
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent evt) {
    }

    @Override
    public void mouseExited(MouseEvent evt) {
    }

    @Override
    public void mouseDragged(MouseEvent evt) {
        if (!isEnabled())
        {
            return;
        }
        // Has the mouse been dragged outside the button?
        int x = evt.getX();
        int y = evt.getY();
        int width = getSize().width;
        int height = getSize().height;
        if (x < 0 || x > width || y < 0 || y > height)
        {
            // Yes, we should deactivate any pending click.
            if (down)
            {
                down = false;
                repaint();
            }
        } else if (!down)
        {
            down = true;
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent evt) {
    }

    // ----------------------------------------------------------------------

    // Methods for registering/deregistering event listeners

    /**
     * The specified ActionListeners <b>actionPerformed</b> method will
     * be called each time the button is clicked. The ActionListener
     * object is added to a list of ActionListeners managed by
     * this button, it can be removed with removeActionListener.
     * Note: the JavaBeans specification does not require ActionListeners
     * to run in any particular order.
     *
     * @see #removeActionListener
     *
     * @param l the ActionListener
     */

    public synchronized void addActionListener(ActionListener l) {
        pushListeners.addElement(l);
    }

    /**
     * Remove this ActionListener from the buttons internal list. If the
     * ActionListener isn't on the list, silently do nothing.
     *
     * @see #addActionListener
     *
     * @param l the ActionListener
     */
    public synchronized void removeActionListener(ActionListener l) {
        pushListeners.removeElement(l);
    }

    /**
     * The specified PropertyChangeListeners <b>propertyChange</b> method will
     * be called each time the value of any bound property is changed.
     * The PropertyListener object is addded to a list of PropertyChangeListeners
     * managed by this button, it can be removed with removePropertyChangeListener.
     * Note: the JavaBeans specification does not require PropertyChangeListeners
     * to run in any particular order.
     *
     * @see #removePropertyChangeListener
     *
     * @param l the PropertyChangeListener
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    /**
     * Remove this PropertyChangeListener from the buttons internal list.
     * If the PropertyChangeListener isn't on the list, silently do nothing.
     *
     * @see #addPropertyChangeListener
     *
     * @param l the PropertyChangeListener
     */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

    // ----------------------------------------------------------------------

    /**
     * This method has the same effect as pressing the button.
     *
     * @see #addActionListener
     */
    public void fireAction() {
        if (debug)
        {
            System.err.println("Button " + getLabel() + " pressed.");
        }
        Vector targets;
        synchronized (this)
        {
            targets = (Vector) pushListeners.clone();
        }
        ActionEvent actionEvt = new ActionEvent(this, 0, null);
        for (int i = 0; i < targets.size(); i++)
        {
            ActionListener target = (ActionListener) targets.elementAt(i);
            target.actionPerformed(actionEvt);
        }

    }

    /**
     * Enable debugging output. Currently a message is printed each time
     * the button is clicked. This is a bound property.
     *
     * @see #getDebug
     * @see #addPropertyChangeListener
     */
    public void setDebug(boolean x) {
        boolean old = debug;
        debug = x;
        changes.firePropertyChange("debug", new Boolean(old), new Boolean(x));
    }

    /**
     * Returns true if debugging output is enabled.
     *
     * @see #setDebug
     */
    public boolean getDebug() {
        return debug;
    }

    /**
     * Set the font size to 18 if true, 12 otherwise. This property overrides
     * the value specified with setFontSize. This is a bound property.
     *
     * @see #isLargeFont
     * @see #addPropertyChangeListener
     */
    public void setLargeFont(boolean b) {
        if (isLargeFont() == b)
        {
            return;
        }
        int size = 12;
        if (b)
        {
            size = 18;
        }
        Font old = getFont();
        setFont(new Font(old.getName(), old.getStyle(), size));
        changes.firePropertyChange("largeFont", new Boolean(!b), new Boolean(b));
    }

    /**
     * Returns true if the font is "large" in the sense defined by setLargeFont.
     *
     * @see #setLargeFont
     * @see #setFont
     */
    public boolean isLargeFont() {
        if (getFont().getSize() >= 18)
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * Set the point size of the current font. This is a bound property.
     *
     * @see #getFontSize
     * @see #setFont
     * @see #setLargeFont
     * @see #addPropertyChangeListener
     */
    public void setFontSize(int x) {
        Font old = getFont();
        setFont(new Font(old.getName(), old.getStyle(), x));
        changes.firePropertyChange("fontSize", new Integer(old.getSize()),
                new Integer(x));
    }

    /**
     * Return the current font point size.
     *
     * @see #setFontSize
     */
    public int getFontSize() {
        return getFont().getSize();
    }

    /**
     * Set the current font and change its size to fit. This is a
     * bound property.
     *
     * @see #setFontSize
     * @see #setLargeFont
     */
    @Override
    public void setFont(Font f) {
        Font old = getFont();
        super.setFont(f);
        sizeToFit();
        changes.firePropertyChange("font", old, f);
        repaint();
    }

    /**
     * Set the buttons label and change it's size to fit. This is a
     * bound property.
     *
     * @see #getLabel
     */
    public void setLabel(String newLabel) {
        String oldLabel = label;
        label = newLabel;
        sizeToFit();
        changes.firePropertyChange("label", oldLabel, newLabel);
    }

    /**
     * Returns the buttons label.
     *
     * @see #setLabel
     */
    public String getLabel() {
        return label;
    }

    @Override
    public Dimension getPreferredSize() {
        FontMetrics fm = getFontMetrics(getFont());
        return new Dimension(fm.stringWidth(label) + TEXT_XPAD,
                fm.getMaxAscent() + fm.getMaxDescent() + TEXT_YPAD);
    }

    /**
     * @deprecated provided for backward compatibility with old layout managers.
     */
    @Deprecated
    @Override
    public Dimension preferredSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    /**
     * @deprecated provided for backward compatibility with old layout managers.
     */
    @Deprecated
    @Override
    public Dimension minimumSize() {
        return getMinimumSize();
    }

    private void sizeToFit() {
        Dimension d = getSize();
        Dimension pd = getPreferredSize();

        if (pd.width > d.width || pd.height > d.height)
        {
            int width = d.width;
            if (pd.width > width)
            {
                width = pd.width;
            }
            int height = d.height;
            if (pd.height > height)
            {
                height = pd.height;
            }
            setSize(width, height);

            Component p = getParent();
            if (p != null)
            {
                p.invalidate();
                p.validate();
            }
        }
    }

    /**
     * Set the color the buttons label is drawn with. This is a bound property.
     */
    @Override
    public void setForeground(Color c) {
        Color old = getForeground();
        super.setForeground(c);
        changes.firePropertyChange("foreground", old, c);
        // This repaint shouldn't really be necessary.
        repaint();
    }

    /**
     * Set the color the buttons background is drawn with. This is a bound property.
     */
    @Override
    public void setBackground(Color c) {
        Color old = getBackground();
        super.setBackground(c);
        changes.firePropertyChange("background", old, c);
        // This repaint shouldn't really be necessary.
        repaint();
    }

    private boolean debug;
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private Vector pushListeners = new Vector();
    private String label;
    private boolean down;
    private boolean sized;

    static final int TEXT_XPAD = 12;
    static final int TEXT_YPAD = 8;
}

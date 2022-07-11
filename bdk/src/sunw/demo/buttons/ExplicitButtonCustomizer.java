
package sunw.demo.buttons;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;

public class ExplicitButtonCustomizer extends Panel implements Customizer, KeyListener
{

    /**
     *
     */
    private static final long serialVersionUID = -1587220040610561606L;

    public ExplicitButtonCustomizer()
    {
        setLayout(null);
    }

    @Override
    public void setObject(Object obj) {
        target = (ExplicitButton) obj;

        Label t1 = new Label("Caption:", Label.RIGHT);
        add(t1);
        t1.setBounds(10, 5, 60, 30);

        labelField = new TextField(target.getLabel(), 20);
        add(labelField);
        labelField.setBounds(80, 5, 100, 30);

        labelField.addKeyListener(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 40);
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
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String txt = labelField.getText();
        target.setLabel(txt);
        support.firePropertyChange("", null, null);
    }

    // ----------------------------------------------------------------------

    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        support.addPropertyChangeListener(l);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        support.removePropertyChangeListener(l);
    }

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    // ----------------------------------------------------------------------

    private ExplicitButton target;
    private TextField labelField;
}

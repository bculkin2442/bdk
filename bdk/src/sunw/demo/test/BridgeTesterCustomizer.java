
package sunw.demo.test;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;

public class BridgeTesterCustomizer extends Panel implements Customizer, KeyListener
{

	/**
	 *
	 */
	private static final long serialVersionUID = -5231804099221756450L;

	public BridgeTesterCustomizer()
	{
		setLayout(null);
	}

	@Override
	public void setObject(Object obj) {
		target = (BridgeTester) obj;

		Label t1 = new Label("String :", Label.RIGHT);
		add(t1);
		t1.setBounds(10, 5, 60, 30);

		labelField = new TextField(target.getStringValue(), 20);
		add(labelField);
		labelField.addKeyListener(this);
		labelField.setBounds(80, 5, 100, 30);

		Label t2 = new Label("Double :", Label.RIGHT);
		add(t2);
		t2.setBounds(10, 40, 60, 70);

		doubleField = new TextField(String.valueOf(target.getDoubleValue()), 20);
		add(doubleField);
		doubleField.addKeyListener(this);
		doubleField.setBounds(80, 40, 100, 70);

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200, 80);
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
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

		Object source = e.getSource();
		if (source == labelField)
		{
			String txt = labelField.getText();
			target.setStringValue(txt);
			support.firePropertyChange("", null, null);
			return;
		}
		if (source == doubleField)
		{
			String txt = doubleField.getText();
			try
			{
				target.setDoubleValue((new Double(txt)));
			} catch (java.lang.NumberFormatException ex)
			{
				doubleField.setText(String.valueOf(target.getDoubleValue()));
			}
			support.firePropertyChange("", null, null);
		}
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

	private BridgeTester target;
	private TextField labelField, doubleField;
}

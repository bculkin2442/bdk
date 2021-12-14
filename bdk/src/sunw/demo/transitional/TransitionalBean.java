
package sunw.demo.transitional;

/**
 * A very simple example bean.
 *
 * We have a screen appearance that is a colour oval, and we have one
 * property "color".
 */

import java.awt.*;
import java.io.*;

public class TransitionalBean extends Canvas implements Serializable
{

	/**
	 *
	 */
	private static final long serialVersionUID = -1162086836141886127L;

	public TransitionalBean()
	{
		resize(60, 40);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(ourColor);
		g.fillArc(5, 5, 30, 30, 0, 360);
		g.fillArc(25, 5, 30, 30, 0, 360);
		g.fillRect(20, 5, 20, 30);
	}

	public Color getColor() {
		return ourColor;
	}

	public void setColor(Color newColor) {
		ourColor = newColor;
		repaint();
	}

	@Override
	public boolean handleEvent(Event evt) {
		if (evt.id == Event.MOUSE_UP)
		{
			if (ourColor == Color.orange)
			{
				setColor(Color.green);
			} else
			{
				setColor(Color.orange);
			}
		}
		return false;
	}

	private Color ourColor = Color.orange;
}

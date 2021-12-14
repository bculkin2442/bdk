package sunw.demo.transitional;

import java.util.*;

/**
 * JDK1.1
 * This class describes the event that gets generated when
 * OurButton gets pushed.
 */

public class ButtonPushEvent extends EventObject
{
	/**
	 *
	 */
	private static final long serialVersionUID = 5072240537381989081L;

	public ButtonPushEvent(java.awt.Component source)
	{
		super(source);
	}
}

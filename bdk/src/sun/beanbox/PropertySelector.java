
// Support for PropertyEditors that use tags.

package sun.beanbox;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;

class PropertySelector extends Choice implements ItemListener
{

    /**
     *
     */
    private static final long serialVersionUID = -3271457212470170497L;

    PropertySelector(PropertyEditor pe)
    {
        editor = pe;
        String tags[] = editor.getTags();
        for (String tag : tags)
        {
            addItem(tag);
        }
        select(0);
        // This is a noop if the getAsText is not a tag.
        select(editor.getAsText());
        addItemListener(this);
    }

    @Override
    public void itemStateChanged(ItemEvent evt) {
        String s = getSelectedItem();
        editor.setAsText(s);
    }

    @Override
    public void repaint() {
        select(editor.getAsText());
    }

    PropertyEditor editor;
}

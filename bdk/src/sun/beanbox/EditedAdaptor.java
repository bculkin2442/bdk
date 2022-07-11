
package sun.beanbox;

import java.beans.*;

class EditedAdaptor implements PropertyChangeListener
{

    EditedAdaptor(PropertySheet t)
    {
        sink = t;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        sink.wasModified(evt);
    }

    PropertySheet sink;
}

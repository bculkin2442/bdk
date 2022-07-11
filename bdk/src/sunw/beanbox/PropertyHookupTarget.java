package sunw.beanbox;

import java.io.*;
import java.lang.reflect.*;

// Information for an event delivery target.
public class PropertyHookupTarget implements Serializable
{

    static final long serialVersionUID = -8352305996623495352L;

    private static int ourVersion = 1;
    Object object;
    Method setter;

    PropertyHookupTarget(Object object, Method setter)
    {
        this.object = object;
        this.setter = setter;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        // Because Method objects aren't serializable, we
        // serialize the name of the setter method.
        s.writeInt(ourVersion);
        s.writeObject(setter.toString());
        s.writeObject(object);
    }

    private void readObject(ObjectInputStream s)
            throws ClassNotFoundException, IOException {
        int version = s.readInt();
        String setterName = (String) s.readObject();
        object = s.readObject();

        // We do a rather expensive search for a setter method
        // matching the given setterName.
        setter = null;
        Method methods[] = object.getClass().getMethods();
        for (Method method : methods)
        {
            if (method.toString().equals(setterName))
            {
                setter = method;
                break;
            }
        }
        if (setter == null)
        {
            throw new IOException("PropertyHookupTarget : no suitable setter" + "\n    "
                    + setterName + "\n    in class " + object.getClass());
        }
    }
}
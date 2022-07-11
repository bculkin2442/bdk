package sunw.beanbox;

import java.applet.*;
import java.awt.*;
import java.io.*;

/**
 * Class needed to support instantiation of Applets in the generated Applets
 */

import java.net.*;
import java.util.*;

public class AppletSupport
{

    // Guess a codebase for a class in a classloader

    public static void assignStub(Applet applet, ClassLoader cl, Class<?> k) {
        String className = k.getName();
        String resourceName = className.replace('.', '/').concat(".class");

        // This code is identical to portions of Beans.intantiate()
        URL objectUrl = null;
        URL codeBase = null;
        URL docBase = null;

        // Now get the URL correponding to the resource name.
        if (cl == null)
        {
            objectUrl = ClassLoader.getSystemResource(resourceName);
        } else
        {
            objectUrl = cl.getResource(resourceName);
        }

        // If we found a URL, we try to locate the docbase by taking
        // of the final path name component, and the code base by taking
        // of the complete resourceName.
        // So if we had a resourceName of "a/b/c.class" and we got an
        // objectURL of "file://bert/classes/a/b/c.class" then we would
        // want to set the codebase to "file://bert/classes/" and the
        // docbase to "file://bert/classes/a/b/"

        if (objectUrl != null)
        {
            String s = objectUrl.toExternalForm();
            if (s.endsWith(resourceName))
            {
                try
                {
                    int ix = s.length() - resourceName.length();
                    codeBase = new URL(s.substring(0, ix));
                    docBase = codeBase;
                    ix = s.lastIndexOf('/');
                    if (ix >= 0)
                    {
                        docBase = new URL(s.substring(0, ix + 1));
                    }
                } catch (Exception ex)
                {
                    docBase = codeBase = null;
                }
            }
        }

        // Setup a default context and stub.
        BeanBoxAppletContext context = new BeanBoxAppletContext(applet);
        BeanBoxAppletStub stub
        = new BeanBoxAppletStub(applet, context, codeBase, docBase);
        applet.setStub(stub);

        // object is always deserialized
        stub.active = true;
    }
}

/**
 * Package private support class. This provides a default AppletContext
 * for beans which are applets.
 */

class BeanBoxAppletContext implements AppletContext
{
    Applet target;
    Hashtable<URL, Object> imageCache = new Hashtable<>();

    BeanBoxAppletContext(Applet target)
    {
        this.target = target;
    }

    @Override
    public AudioClip getAudioClip(URL url) {
        // We don't currently support audio clips in the Beans.instantiate
        // applet context, unless by some luck there exists a URL content
        // class that can generate an AudioClip from the audio URL.
        try
        {
            return (AudioClip) url.getContent();
        } catch (Exception ex)
        {
            return null;
        }
    }

    @Override
    public synchronized Image getImage(URL url) {
        Object o = imageCache.get(url);
        if (o != null)
        {
            return (Image) o;
        }
        try
        {
            o = url.getContent();
            if (o == null)
            {
                return null;
            }
            if (o instanceof Image)
            {
                imageCache.put(url, o);
                return (Image) o;
            }
            // Otherwise it must be an ImageProducer.
            Image img = target.createImage((java.awt.image.ImageProducer) o);
            imageCache.put(url, img);
            return img;

        } catch (Exception ex)
        {
            return null;
        }
    }

    @Override
    public Applet getApplet(String name) {
        return null;
    }

    @Override
    public Enumeration<Applet> getApplets() {
        Vector<Applet> applets = new Vector();
        applets.addElement(target);
        return applets.elements();
    }

    @Override
    public void showDocument(URL url) {
        // We do nothing.
    }

    @Override
    public void showDocument(URL url, String target) {
        // We do nothing.
    }

    @Override
    public void showStatus(String status) {
        // We do nothing.
    }

    @Override
    public void setStream(String key, InputStream stream) throws IOException {
        // TODO Auto-generated method stub

    }

    @Override
    public InputStream getStream(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<String> getStreamKeys() {
        // TODO Auto-generated method stub
        return null;
    }
}

/**
 * This provides an AppletStub for beans which are applets.
 */
class BeanBoxAppletStub implements AppletStub
{
    transient boolean active;
    transient Applet target;
    transient AppletContext context;
    transient URL codeBase;
    transient URL docBase;

    BeanBoxAppletStub(Applet target, AppletContext context, URL codeBase, URL docBase)
    {
        this.target = target;
        this.context = context;
        this.codeBase = codeBase;
        this.docBase = docBase;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public URL getDocumentBase() {
        // use the root directory of the applet's class-loader
        return docBase;
    }

    @Override
    public URL getCodeBase() {
        // use the directory where we found the class or serialized object.
        return codeBase;
    }

    @Override
    public String getParameter(String name) {
        return null;
    }

    @Override
    public AppletContext getAppletContext() {
        return context;
    }

    @Override
    public void appletResize(int width, int height) {
        // we do nothing.
    }
}

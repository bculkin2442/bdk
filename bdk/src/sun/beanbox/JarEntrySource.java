package sun.beanbox;

/**
 * Used to request creating Jars
 */
import java.io.*;

public class JarEntrySource
{
    public JarEntrySource(File file)
    {
        markOnly = false;
        name = file.getPath().replace(File.separatorChar, '/');
        this.file = file;
    }

    public JarEntrySource(String name, File file)
    {
        markOnly = false;
        this.name = name;
        this.file = file;
    }

    public JarEntrySource(String name, InputStream is)
    {
        markOnly = false;
        this.name = name;
        this.is = is;
    }

    public JarEntrySource(String name)
    {
        markOnly = true;
        this.name = name;
    }

    /**
     * Accessors
     */
    public boolean isMarkOnly() {
        return markOnly;
    }

    public long getTime() {
        if (file != null)
        {
            return file.lastModified();
        } else
        {
            return 0; // ??
        }
    }

    public long getLength() {
        if (file != null)
        {
            return file.length();
        } else
        {
            return 0; // ??
        }
    }

    public String getName() {
        return name;
    }

    public InputStream getInputStream() {
        if (file != null)
        {
            try
            {
                return new FileInputStream(file);
            } catch (Exception ex)
            {
                return null;
            }
        } else
        {
            return is;
        }
    }

    private boolean markOnly; // only mark in the ZIP
    private String name; // the name of this entry
    private InputStream is; // the input stream for the entry
    private File file; // if a file
    private long modifiedTime; // if an inputstream
}

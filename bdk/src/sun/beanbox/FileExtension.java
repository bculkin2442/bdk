
package sun.beanbox;

import java.io.*;

/**
 * Filter class
 */

public class FileExtension implements FilenameFilter
{
	private String extension;

	public FileExtension(String ext)
	{
		extension = ext;
	}

	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(extension);
	}
}

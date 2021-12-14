package sun.beanbox.simpleresource;

import java.io.*;
import java.net.*;

public class Handler extends URLStreamHandler
{
	@Override
	public URLConnection openConnection(URL u) throws IOException {
		return new SimpleResourceConnection(u);
	}
}

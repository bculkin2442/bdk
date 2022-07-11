
package sunw.demo.quote;

import java.io.*;

public class HttpProxy implements Serializable
{
    /**
     *
     */
    private static final long serialVersionUID = 2526406707310104573L;
    private String host;
    private int port;

    HttpProxy(String hostArg, int portArg)
    {
        host = hostArg;
        port = portArg;
    }

    String getHost() {
        return host;
    }

    int getPort() {
        return port;
    }
}


package sunw.demo.quote;

import java.util.*;

public class HttpProxyChangedEvent extends EventObject
{
	/**
	 *
	 */
	private static final long serialVersionUID = -9103572661828475975L;
	private HttpProxy httpProxy;

	HttpProxyChangedEvent(QuoteServer source, HttpProxy x)
	{
		super(source);
		httpProxy = x;
	}

	HttpProxy getHttpProxy() {
		return httpProxy;
	}
}

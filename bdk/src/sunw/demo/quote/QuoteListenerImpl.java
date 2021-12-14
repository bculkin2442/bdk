package sunw.demo.quote;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

/**
 * The QuoteListener implementation used by the QuoteMonitor bean. When
 * quote events arrive we notify the QuoteMonitor directly.
 *
 * @see sunw.demo.quote.QuoteMonitor
 * @see sunw.demo.quote.QuoteListener
 */

class QuoteListenerImpl extends UnicastRemoteObject
		implements QuoteListener, EventListener
{
	/**
	 *
	 */
	private static final long serialVersionUID = 5401399529922540845L;
	private QuoteMonitor quoteMonitor;

	/**
	 * Construct a remote object reference.
	 */
	public QuoteListenerImpl(QuoteMonitor x) throws RemoteException
	{
		super();
		quoteMonitor = x;
	}

	/**
	 * Just forward the QuoteEvent to the QuoteMonitor.
	 */
	@Override
	public void quoteChanged(QuoteEvent x) throws RemoteException {
		quoteMonitor.quoteChanged(x);
	}

	/**
	 * @return the QuoteMonitors stock symbol.
	 */
	@Override
	public String getStockSymbol() throws RemoteException {
		return quoteMonitor.getStockSymbol();
	}
}

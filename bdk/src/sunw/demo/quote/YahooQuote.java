
package sunw.demo.quote;

import java.io.*;
import java.net.*;
import java.util.*;

public class YahooQuote implements Serializable
{
	/**
	 *
	 */
	private static final long serialVersionUID = -2906623692399677016L;

	static Hashtable getQuotes(QuoteServer x, Vector symbols) {
		String symbolsString = "";
		for (int i = 0; i < symbols.size(); i++)
		{
			String symbol = (String) symbols.elementAt(i);
			symbolsString += ((i != 0) ? "," : "") + symbol.toUpperCase();
		}

		String quoteURLString = "http://quote.yahoo.com/download/javasoft.beans?SYMBOLS="
				+ symbolsString + "&format=sl";

		Hashtable rv = new Hashtable();
		BufferedReader in = null;

		// SimpleDateFormat dateFormat = new SimpleDateFormat("M/dd/yy");
		// SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mma");

		try
		{
			URL quoteURL = new URL(quoteURLString);
			in = new BufferedReader(new InputStreamReader(quoteURL.openStream()));

			String line;

			while (null != (line = in.readLine()))
			{
				StringTokenizer t = new StringTokenizer(line, ",\"");

				String symbol = t.nextToken();
				double price = Double.valueOf(t.nextToken());

				// Date date = dateFormat.parse(t.nextToken());
				t.nextToken();
				Date date = new Date();

				// Date lastTrade = timeFormat.parse(t.nextToken());
				t.nextToken();
				Date lastTrade = date;

				double change = Double.valueOf(t.nextToken());
				double open = Double.valueOf(t.nextToken());
				double bid = Double.valueOf(t.nextToken());
				double ask = Double.valueOf(t.nextToken());
				int volume = Integer.valueOf(t.nextToken());

				QuoteEvent qe
						= new QuoteEvent(x, symbol, date, price, bid, ask, open, volume);
				rv.put(symbol.toUpperCase(), qe);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (in != null)
					in.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		return rv;
	}
}

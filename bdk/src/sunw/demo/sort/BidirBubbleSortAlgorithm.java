/*
 * @(#)BidirBubbleSortAlgorithm.java	1.3 96/12/06
 *
 * Copyright (c) 1994-1996 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

package sunw.demo.sort;

/**
 * A bi-directional bubble sort demonstration algorithm
 * SortAlgorithm.java, Thu Oct 27 10:32:35 1994
 *
 * @author James Gosling
 *
 * @version 1.6f, 31 Jan 1995
 */
class BidirBubbleSortAlgorithm extends SortAlgorithm
{
    @Override
    void sort(int a[]) throws Exception {
        int j;
        int limit = a.length;
        int st = -1;
        while (st < limit)
        {
            st++;
            limit--;
            boolean swapped = false;
            for (j = st; j < limit; j++)
            {
                if (stopRequested)
                {
                    return;
                }
                if (a[j] > a[j + 1])
                {
                    int T = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = T;
                    swapped = true;
                }
                pause(st, limit);
            }
            if (!swapped)
            {
                return;
            } else
                swapped = false;
            for (j = limit; --j >= st;)
            {
                if (stopRequested)
                {
                    return;
                }
                if (a[j] > a[j + 1])
                {
                    int T = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = T;
                    swapped = true;
                }
                pause(st, limit);
            }
            if (!swapped)
            {
                return;
            }
        }
    }
}

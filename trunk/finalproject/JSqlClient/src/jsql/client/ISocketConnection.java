/**
 * 
 */
package jsql.client;

import jsql.data.Result;

/**
 * @author tmkhanh
 *
 */
public interface ISocketConnection {
    public void hasSocketConnected();
    public void hasSocketDisconnect();
    public void hasResponse(Result result, int iCount);
}

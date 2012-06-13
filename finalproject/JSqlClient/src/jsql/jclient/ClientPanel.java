package jsql.jclient;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;

import jsql.client.MyClient;
import jsql.data.Request;
import jsql.data.Result;

public class ClientPanel extends JPanel implements ActionListener, ISocketConnection {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HeaderPanel headerPanel;
	private StatementPanel statementPanel;
	private ResultPanel resultPanel;
	private MyClient client;
	/**
	 * Create the panel.
	 */
	public ClientPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setMinimumSize(new Dimension(800, 600));
		
		headerPanel = new HeaderPanel();
		add(headerPanel);
		
		statementPanel = new StatementPanel();
		add(statementPanel);
		
		resultPanel = new ResultPanel();
		add(resultPanel);

		headerPanel.btnConnect.addActionListener(this);
		headerPanel.btnDisconect.addActionListener(this);
		headerPanel.btnExecute.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent aEvent) {
		try {
			if ("Connect".equals(aEvent.getActionCommand())) {
				String address = headerPanel.textField.getText();
				if (address == null || address.length() < 10)
					throw new Exception("Address có độ dài không đủ!");
				int mid = address.lastIndexOf(":");
				if (mid < 7)
					throw new Exception(
							"Address có độ dài không đủ hoặc không đúng định dạng!");
				String ip = address.substring(0, mid);
				String port = address.substring(mid + 1, address.length());
				int iPort = 0;
				try {
					iPort = Integer.parseInt(port);
				} catch (Exception e) {
					throw new Exception("Port không đúng định dạng!");
				}

				client = new MyClient(ip, iPort, this);
				client.start();
			}
			if("Disconnect".equals(aEvent.getActionCommand())){
				client.stopClient();
			}
			if("Execute".equals(aEvent.getActionCommand())){
				//gửi lệnh query lên server
				if (!client.isConnected()) {
					throw new Exception("Hiện không kết nối với server!");
				}
				String statement = statementPanel.textArea.getText();
				Vector<Request> requests = Request.create(statement);
				client.sendRequest(requests);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Lỗi", 1);
		}
	}
	@Override
	public void hasSocketDisconnect() {
		headerPanel.btnConnect.setEnabled(true);
		headerPanel.btnDisconect.setEnabled(false);
		headerPanel.btnExecute.setEnabled(false);
		headerPanel.textField.setEnabled(true);
		statementPanel.textArea.setEditable(false);
	}
	@Override
	public void hasSocketConnected() {
		headerPanel.btnConnect.setEnabled(false);
		headerPanel.btnDisconect.setEnabled(true);
		headerPanel.btnExecute.setEnabled(true);
		headerPanel.textField.setEnabled(false);
		statementPanel.textArea.setEditable(true);
	}
	@Override
	public void hasResponse(Result result, int iCount) {
		System.out.println(iCount);
	}

}

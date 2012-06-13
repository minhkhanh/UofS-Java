/**
 * 
 */
package jsql.client;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author tmkhanh
 *
 */
public class ClientFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton jB_Connect;
	private JButton jB_Discon;
    private JButton jB_Execute;
    private JLabel jLabel1;
    private JPanel jP_Result;
    private JPanel jP_Statement;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTextArea jTA_Statement;
    private JTextField jTF_IP;
    private JTable jTable1;
    
    private MyClient client;
    
    public ClientFrame() {    	
    	setSize(800, 600);
    	setResizable(false);
		setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2
				- getWidth() / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2
						- getHeight() / 2);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		jLabel1 = new javax.swing.JLabel();
        jTF_IP = new javax.swing.JTextField();
        jB_Connect = new javax.swing.JButton();
        jB_Execute = new javax.swing.JButton();
        jP_Statement = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTA_Statement = new javax.swing.JTextArea();
        jP_Result = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jB_Discon = new javax.swing.JButton();
        

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("JSql Server Address:");

        jTF_IP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTF_IP.setText("localhost:3456");
        jTF_IP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jB_Connect.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jB_Connect.setText("Connect");
        jB_Connect.setActionCommand("Connect");
        jB_Connect.addActionListener(this);

        jB_Discon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jB_Discon.setText("Disconnect");
        jB_Discon.setEnabled(false);
        jB_Discon.setActionCommand("Disconnect");
        jB_Discon.addActionListener(this);

        jB_Execute.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jB_Execute.setText("Execute");
        jB_Execute.setActionCommand("Execute");
        jB_Execute.addActionListener(this);

        jP_Statement.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Statement"));

        jTA_Statement.setColumns(20);
        jTA_Statement.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTA_Statement.setRows(5);
        jScrollPane1.setViewportView(jTA_Statement);

        javax.swing.GroupLayout jP_StatementLayout = new javax.swing.GroupLayout(jP_Statement);
        jP_Statement.setLayout(jP_StatementLayout);
        jP_StatementLayout.setHorizontalGroup(
            jP_StatementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jP_StatementLayout.setVerticalGroup(
            jP_StatementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_StatementLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jP_Result.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Result"));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Age"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jP_ResultLayout = new javax.swing.GroupLayout(jP_Result);
        jP_Result.setLayout(jP_ResultLayout);
        jP_ResultLayout.setHorizontalGroup(
            jP_ResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jP_ResultLayout.setVerticalGroup(
            jP_ResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jP_Result, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jP_Statement, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTF_IP, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jB_Connect, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jB_Discon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jB_Execute, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTF_IP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jB_Connect)
                    .addComponent(jB_Execute)
                    .addComponent(jB_Discon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jP_Statement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jP_Result, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );        
        pack();
	}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if("Connect".equals(e.getActionCommand())){
			if(jTF_IP.getText() != null){
					int mid = jTF_IP.getText().lastIndexOf(":");
					String ip = jTF_IP.getText().substring(0, mid);
					int port = Integer.parseInt(jTF_IP.getText().substring(mid + 1, jTF_IP.getText().length()));
					
					//client = new MyClient(ip, port, this);
					client.run();
					if(client.isConnected() == true){
						jB_Connect.setEnabled(false);
						jB_Discon.setEnabled(true);
					}
			}
		}
		
		if("Disconnect".equals(e.getActionCommand())){
				client.stop();
				jB_Connect.setEnabled(true);
				jB_Discon.setEnabled(false);
		}

		if("Execute".equals(e.getActionCommand())){
			//gửi lệnh query lên server
		}

	}
}

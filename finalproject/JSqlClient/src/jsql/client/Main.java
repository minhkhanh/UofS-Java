package jsql.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener{

	private JButton jB_Connect;
    private JButton jB_Execute;
    private JLabel jLabel1;
    private JPanel jP_Result;
    private JPanel jP_Statement;
    private JScrollPane jScrollPane1;
    private JTextArea jTA_Statement;
    private JTextField jT_IP;
	
	public Main(){
		jLabel1 = new JLabel();
        jT_IP = new JTextField();
        jB_Connect = new JButton();
        jB_Execute = new JButton();
        jP_Statement = new JPanel();
        jScrollPane1 = new JScrollPane();
        jTA_Statement = new JTextArea();
        jP_Result = new JPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("JSql Server Address:");

        jT_IP.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        jT_IP.setText("172.0.0.1:3456");

        jB_Connect.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        jB_Connect.setText("Connect");
        jB_Connect.setActionCommand("connect");
        jB_Connect.addActionListener(this);

        jB_Execute.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        jB_Execute.setText("Execute");
        jB_Execute.setActionCommand("execute");
        jB_Execute.addActionListener(this);

        jP_Statement.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Statement"));

        jTA_Statement.setColumns(20);
        jTA_Statement.setFont(new Font("Monospaced", 0, 16)); // NOI18N
        jTA_Statement.setRows(5);
        jScrollPane1.setViewportView(jTA_Statement);

        GroupLayout jP_StatementLayout = new GroupLayout(jP_Statement);
        jP_Statement.setLayout(jP_StatementLayout);
        jP_StatementLayout.setHorizontalGroup(
            jP_StatementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jP_StatementLayout.setVerticalGroup(
            jP_StatementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jP_StatementLayout.createSequentialGroup()
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jP_Result.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Result"));

        GroupLayout jP_ResultLayout = new GroupLayout(jP_Result);
        jP_Result.setLayout(jP_ResultLayout);
        jP_ResultLayout.setHorizontalGroup(
            jP_ResultLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jP_ResultLayout.setVerticalGroup(
            jP_ResultLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 178, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jP_Result, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jP_Statement, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jT_IP, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jB_Connect, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jB_Execute, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jT_IP, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jB_Connect)
                    .addComponent(jB_Execute))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jP_Statement, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jP_Result, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException: " + ex.getMessage());
        } catch (InstantiationException ex) {
        	 System.out.println("InstantiationException: " + ex.getMessage());
        } catch (IllegalAccessException ex) {
        	 System.out.println("IllegalAccessException: " + ex.getMessage());
        } catch (UnsupportedLookAndFeelException ex) {
        	 System.out.println("UnsupportedLookAndFeelException: " + ex.getMessage());
        }
		
		EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Main().setVisible(true);
            }
        });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
		case "connect":
			if(jT_IP.getText() == null){
				break;
			}
		case "execute":
			if(jTA_Statement.getText() == null){
				break;
			}
		}
	}

}

package jsql.client;

import javax.swing.*;
import java.awt.*;


public class Main extends JFrame{

	private JButton jB_Execute;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane jScrollPane2;
    private JTextArea jTA_State;
    private JTextField jT_IP;
	
	public Main(){
		jLabel1 = new  JLabel();
        jT_IP = new  JTextField();
        jB_Execute = new JButton();
        jPanel1 = new JPanel();
        jScrollPane2 = new JScrollPane();
        jTA_State = new JTextArea();
        jPanel2 = new JPanel();

        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("JSql Server address:");

        jT_IP.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        jT_IP.setText("172.0.0.1:3456");
        jT_IP.setBorder(new javax.swing.border.LineBorder(new Color(0, 0, 0), 1, true));

        jB_Execute.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        jB_Execute.setText("Execute");

        jPanel1.setBorder( BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder( javax.swing.border.EtchedBorder.RAISED), "Statement", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jTA_State.setColumns(20);
        jTA_State.setRows(5);
        jScrollPane2.setViewportView(jTA_State);

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        jPanel2.setBorder( BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder( javax.swing.border.EtchedBorder.RAISED), "Result", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGap(0, 482, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGap(0, 212, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGroup( GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup( GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jT_IP)
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jB_Execute, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jB_Execute)
                    .addComponent(jT_IP, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch ( UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		
		EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Main().setVisible(true);
            }
        });
	}

}

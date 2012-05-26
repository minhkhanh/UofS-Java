package jsql.server;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class temp_FrameMain extends JFrame implements ActionListener {
	private JTextField jTF_AdrrFolder;
	private JTextField jTF_Port;
	private JLabel jLbl_AdrrFolder;
	private JLabel jLbl_Port;
	private JButton jBtn_Browse;
	private JButton jBtn_Edit;
	private JButton jBtn_Listen;
	private JButton jBtn_Stop;

    private JPanel jPanel_Log;
	
	private A_EditTable a_edittable;

	/**
	 * Create the frame.
	 */
	public temp_FrameMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
jPanel_Log = new JPanel();
		jTF_AdrrFolder = new JTextField();
		jTF_Port = new JTextField();
		jLbl_AdrrFolder = new JLabel();
		jLbl_Port = new JLabel();
		jBtn_Browse = new JButton();
		jBtn_Edit = new JButton();
		jBtn_Listen = new JButton();
		jBtn_Stop = new JButton();
		
		a_edittable = new A_EditTable();

		jLbl_AdrrFolder.setFont(new Font("Tahoma", 0, 14));
		jLbl_AdrrFolder.setText("Thu muc du lieu:");

		jLbl_Port.setFont(new Font("Tahoma", 0, 14));
		jLbl_Port.setText("Port:");

		jTF_AdrrFolder.setFont(new Font("Tahoma", 0, 14));
		jTF_AdrrFolder.setText("D:\\DB\\");
		jTF_AdrrFolder.setBorder(new javax.swing.border.LineBorder(new Color(0,
				0, 0), 1, true));

		jTF_Port.setFont(new Font("Tahoma", 0, 14));
		jTF_Port.setText("3456");
		jTF_Port.setBorder(new javax.swing.border.LineBorder(
				new Color(0, 0, 0), 1, true));

		jBtn_Browse.setFont(new Font("Tahoma", 0, 14));
		jBtn_Browse.setText("Browse");
		jBtn_Browse.setActionCommand("a_browse");
		jBtn_Browse.addActionListener(this);

		jBtn_Edit.setFont(new Font("Tahoma", 0, 14));
		jBtn_Edit.setText("Edit");
		jBtn_Edit.setAction(a_edittable);
		jBtn_Edit.addActionListener(this);

		jBtn_Listen.setFont(new Font("Tahoma", 0, 14));
		jBtn_Listen.setText("Listen");
		jBtn_Listen.setActionCommand("a_listen");
		jBtn_Listen.addActionListener(this);

		jBtn_Stop.setFont(new Font("Tahoma", 0, 14));
		jBtn_Stop.setText("Stop");
		jBtn_Stop.setActionCommand("a_stop");
		jBtn_Stop.addActionListener(this);


        jPanel_Log.setBorder( BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder( javax.swing.border.EtchedBorder.RAISED), 
        		"Log", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        GroupLayout jPanel2Layout = new GroupLayout(jPanel_Log);
        jPanel_Log.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGap(0, 482, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGap(0, 212, Short.MAX_VALUE)
        );
		
		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
							.addComponent(jPanel_Log, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
							.addComponent(jLbl_AdrrFolder, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jTF_AdrrFolder, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jBtn_Browse, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
							.addComponent(jBtn_Edit, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(jLbl_Port, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jTF_Port, 220, 220, 220)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jBtn_Listen, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addComponent(jBtn_Stop, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(jLbl_AdrrFolder, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(jBtn_Browse)
						.addComponent(jBtn_Edit)
						.addComponent(jTF_AdrrFolder, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(jLbl_Port, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
						.addComponent(jBtn_Listen)
						.addComponent(jBtn_Stop)
						.addComponent(jTF_Port, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(jPanel_Log, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		//layout.setLayoutStyle(null);
		getContentPane().setLayout(layout);
		setBounds(50,50,600,450);
		//pack();

		//pack();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	private class A_EditTable extends AbstractAction {
		
		public void actionPerformed(ActionEvent e) {
			new Frame_EditTable().setVisible(true);
		}
	}
}

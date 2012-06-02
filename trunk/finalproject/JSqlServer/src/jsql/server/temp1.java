package jsql.server;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.sun.xml.internal.ws.api.server.Container;

public class temp1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					temp1 frame = new temp1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	    protected SimpleModel tableData;
	    protected JTable table;
	    protected JTextField textField;


	    public temp1(){
	        Container pane = getContentPane();
	        pane.setLayout(new BorderLayout());
	        tableData = new SimpleModel();
	        table = new JTable(tableData);
	        table.getColumnModel().getColumn(0).setPreferredWidth(300);
	        JScrollPane jsp = new JScrollPane(table);
	        pane.add(jsp, BorderLayout.CENTER);
	        textField = new JTextField();
	        textField.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent event) {
	                addLineToTable();
	            }
	        });
	        pane.add(textField, BorderLayout.SOUTH);
	    }

	    protected void addLineToTable(){
	        tableData.addText(textField.getText());
	        textField.setText("");
	    }

	    class SimpleModel extends AbstractTableModel{
	        protected Vector textData = new Vector();

	        public void addText(String text){
	            textData.addElement(text);
	            fireTableDataChanged();
	        }

	        public int getRowCount(){
	            return textData.size();
	        }

	        public int getColumnCount(){
	            return 1;
	        }

	        public Object getValueAt(int row, int column){
	            return textData.elementAt(row);
	        }
	    }
	}

}

package jsql.server;

import java.awt.BorderLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Frame_Browse extends JFrame {

	private JPanel contentPane;

	private JFileChooser _FileChooser;

	/**
	 * Create the frame.
	 */
	public Frame_Browse() {
		this.InitFrame();
	}

	public void InitFrame() {

		setBounds(200, 100, 600, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		_FileChooser = new JFileChooser();
		_FileChooser.setDialogTitle("Chon DataBase");
		contentPane.add(_FileChooser);
	}

}

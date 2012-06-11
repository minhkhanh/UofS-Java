package jsql.server;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author DWater
 * 
 */
@SuppressWarnings("serial")
public class Panel_Manager extends JPanel implements ActionListener {

	public Panel_Manager() {
		this.InitFrame();
		this.Init();
	}

	public void InitFrame() {
		this.setSize(784, 439);
		this.setLayout(null);
		this.setName("Manager Table");
	}

	public void Init() {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public Boolean CheckChooseDataBase() {
		if (Main.GetDataBase() == null) {
			JOptionPane.showMessageDialog(this,
					"Xin chọn file Database trước !", "Warning",
					JOptionPane.WARNING_MESSAGE);

			return false;
		}
		return true;
	}

	public void ChoosenDatabase() {
		Refresh();

	}

	public void Refresh() {
		MiniTable tMiniTable;
		Point pos;
		int nTable = Main.GetDataBase().getTables().size();

		for (int i = 0; i < nTable; i++) {

			if (i > 3)
				pos = new Point(15 + (i - 4) * 189, 5 + 220);
			else
				pos = new Point(15 + i * 189, 5);

			tMiniTable = new MiniTable(this, Main.GetDataBase().getTable(i),
					pos);
			add(tMiniTable);
		}
	}

	public void RemoveMiniTable(MiniTable mt) {
		this.remove(mt);
		this.repaint();
	}
}

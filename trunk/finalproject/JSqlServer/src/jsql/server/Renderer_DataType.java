/**
 * 
 */
package jsql.server;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * @author DWater 
 * tham khao: http://nghexuanchien.wordpress.com/2011/07/13/s%E1%BB
 *         %AD-d%E1%BB%A5ng-jtable-c%E1%BB%A7a-swing-trong-java-ph%E1%BA%A7n-4/
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class Renderer_DataType extends JComboBox implements TableCellRenderer {

	@SuppressWarnings("unchecked")
	public Renderer_DataType() {
		super();
		addItem("INT");
		addItem("STRING");
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			super.setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(table.getBackground());
		}

		if (value.equals("INT"))
			setSelectedIndex(0);
		else
			setSelectedIndex(1);

		return this;
	}
}
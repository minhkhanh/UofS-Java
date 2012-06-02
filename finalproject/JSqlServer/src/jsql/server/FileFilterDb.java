package jsql.server;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * @author DWater
 *
 */
public abstract class FileFilterDb extends FileFilter {
	
	@Override
	public String getDescription() {
		return "DataBase";
	}

	@Override
	public boolean accept(File f) {
		String extension = getExtension(f);
		if (extension != null && extension.equals("db")) {
			return true;
		}

		if (f.isDirectory()) {
			return true;
		}

		return false;
	}
	
	public String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}

}

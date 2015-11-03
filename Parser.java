import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class is thread safe.
 */
public class Parser {

	/** The file attribute */
	private File file;

	/**
	 * getContent method.
	 * @return content
	 */
	public String getContent(boolean WithoutUnicode) {
		String output = "";
		try {
			FileInputStream i = new FileInputStream(file);
			int data;
			while ((data = i.read()) > 0) {
				if (WithoutUnicode && (data < 0x80)) {
					output += (char) data;
				} else {
					output += (char) data;
				}
			}
		} catch (FileNotFoundException fnfException) {
			System.err.println("File not fount : " + fnfException.getMessage());
		} catch (IOException ioException) {
			System.err.println("caught exception: " + ioException.getMessage());
		}
		return output;
	}

	/**
	 * saveContent method.
	 * @throws IOException IOException
	 */
	public void saveContent(String content) throws IOException {
		FileOutputStream o = new FileOutputStream(file);
		for (int i = 0; i < content.length(); i += 1) {
			o.write(content.charAt(i));
		}
	}
	
	/**
	 * Setter of file attribute.
	 * @param f File
	 */
	public synchronized void setFile(File f) {
		this.file = f;
	}

	/**
	 * Getter of file attribute.
	 * @return file
	 */
	public synchronized File getFile() {
		return this.file;
	}
}

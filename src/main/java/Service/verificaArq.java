package Service;

import java.io.File;

public class verificaArq {
	public boolean existe(String link) {
		return (new File(link)).exists();
	}
}

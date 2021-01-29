package utils;

import java.io.File;

public class Directory {
	public static void make(File dir) {
	  if (!dir.exists()) {
	    dir.mkdir();
	    dir.setReadable(true, false);
	    dir.setWritable(true, false);
	    dir.setExecutable(true, false);
	  }
	}
}

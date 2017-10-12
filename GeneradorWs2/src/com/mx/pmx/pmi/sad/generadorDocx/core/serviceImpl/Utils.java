package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public final class Utils {

	public static void closeStream(Object obj)
	{
		if (obj == null) {
			return;
		}
		
		try
		{
			if (obj instanceof OutputStream)
			{
				((OutputStream) obj).close();
			}
			else if (obj instanceof InputStream)
			{
				((InputStream) obj).close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static byte[] clone(byte[] arr) {
		byte[] a = new byte[arr.length];
		for (int i = 0; i < arr.length; i++) {
			a[i] = arr[i];
		}
		return a;
	}
	
	public static void dumpToFile(byte[] data, String prefix, String ext)
	{
		File f;
		int i = 1;
		
		do
		{
			String fileName = prefix+i+"."+ext;
			f = new File(fileName);
			i++;
		} while (f.exists());
		
		try {
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(data);
			fos.flush();
			fos.close();
			//System.out.println("File dumped: " + f.getAbsolutePath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
    public static final int get16(byte b[], int off) {
    	return (b[off] & 0xff) | ((b[off+1] & 0xff) << 8);
    }

    public static final long get32(byte b[], int off) {
    	return get16(b, off) | ((long)get16(b, off+2) << 16);
    }
    


}

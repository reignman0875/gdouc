package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;


public class LEDataInputStream extends DataInputStream {

	public LEDataInputStream(InputStream in) {
		super(in);
	}
	
	public final int readLEShort() throws IOException {
		int ch1 = in.read();
		int ch2 = in.read();
		return (ch1 & 0xff) | ((ch2 & 0xff) << 8);
    }
	
	public final long readLEInt() throws IOException {
		return readLEShort() | ((long) readLEShort() << 16);
    }

}

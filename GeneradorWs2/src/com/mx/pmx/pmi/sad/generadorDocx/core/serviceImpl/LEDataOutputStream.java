package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class LEDataOutputStream extends DataOutputStream {

	public LEDataOutputStream(OutputStream out) {
		super(out);
	}
	
    public void writeLEShort(int v) throws IOException {
		super.write((v >>> 0) & 0xff);
		super.write((v >>> 8) & 0xff);
    }

    public void writeLEInt(long v) throws IOException {
		super.write((int)((v >>>  0) & 0xff));
		super.write((int)((v >>>  8) & 0xff));
		super.write((int)((v >>> 16) & 0xff));
		super.write((int)((v >>> 24) & 0xff));
    }
}

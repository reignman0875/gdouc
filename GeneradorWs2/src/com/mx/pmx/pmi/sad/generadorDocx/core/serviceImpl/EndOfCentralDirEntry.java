package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;
import java.io.IOException;


public class EndOfCentralDirEntry {
	
	public static long SIGNATURE = 0x06054b50;
	
	private int diskNumber;
	private int diskCentralDirStart;
	private int centralDirRecordsDiskTotal;
	private int centralDirRecordsTotal;
	private long centralDirectorySize;
	private long centralDirectoryStartOffset;
	private int zipCommentLength;
	private byte[] zipComment;

	public void write(LEDataOutputStream out) throws IOException {
		out.writeLEShort(diskNumber);
		out.writeLEShort(diskCentralDirStart);
		out.writeLEShort(centralDirRecordsDiskTotal);
		out.writeLEShort(centralDirRecordsTotal);
		out.writeLEInt(centralDirectorySize);
		out.writeLEInt(centralDirectoryStartOffset);
		out.writeLEShort(zipCommentLength);
		out.write(zipComment);
	}

	public void read(LEDataInputStream in) throws IOException {
		diskNumber = in.readLEShort();
		diskCentralDirStart = in.readLEShort();
		centralDirRecordsDiskTotal = in.readLEShort();
		centralDirRecordsTotal = in.readLEShort();
		centralDirectorySize = in.readLEInt();
		centralDirectoryStartOffset = in.readLEInt();
		zipCommentLength = in.readLEShort();
		zipComment = new byte[zipCommentLength];
		in.read(zipComment);
		
//		System.out.println("---");
//		System.out.println("EndOfCentralDirEntry");
//		System.out.println("zipCommentLength: " + zipCommentLength);
	}
	
	public long getTotalSize() {
		return
			4L+(2L*5L)+(4L*2L)+2L+
			((long) zipComment.length);
	}

	public int getDiskNumber() {
		return diskNumber;
	}

	public void setDiskNumber(int diskNumber) {
		this.diskNumber = diskNumber;
	}

	public int getDiskCentralDirStart() {
		return diskCentralDirStart;
	}

	public void setDiskCentralDirStart(int diskCentralDirStart) {
		this.diskCentralDirStart = diskCentralDirStart;
	}

	public int getCentralDirRecordsDiskTotal() {
		return centralDirRecordsDiskTotal;
	}

	public void setCentralDirRecordsDiskTotal(int centralDirRecordsDiskTotal) {
		this.centralDirRecordsDiskTotal = centralDirRecordsDiskTotal;
	}

	public int getCentralDirRecordsTotal() {
		return centralDirRecordsTotal;
	}

	public void setCentralDirRecordsTotal(int centralDirRecordsTotal) {
		this.centralDirRecordsTotal = centralDirRecordsTotal;
	}

	public long getCentralDirectorySize() {
		return centralDirectorySize;
	}

	public void setCentralDirectorySize(long centralDirectorySize) {
		this.centralDirectorySize = centralDirectorySize;
	}

	public long getCentralDirectoryStartOffset() {
		return centralDirectoryStartOffset;
	}

	public void setCentralDirectoryStartOffset(long centralDirectoryStartOffset) {
		this.centralDirectoryStartOffset = centralDirectoryStartOffset;
	}

	public int getZipCommentLength() {
		return zipCommentLength;
	}

	public void setZipCommentLength(int zipCommentLength) {
		this.zipCommentLength = zipCommentLength;
	}

	public byte[] getZipComment() {
		return zipComment;
	}

	public void setZipComment(byte[] zipComment) {
		this.zipComment = zipComment;
	}
}

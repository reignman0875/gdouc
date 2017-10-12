package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class FileEntry {
	
	public static long SIGNATURE = 0x04034b50;
	
	public static final long EXT_SIGNATURE = 0x08074b50;

	private CentralDirEntry dirEntry;
	
	private int version;
	private int bitFlag;
	private int compressionMethod;
	private int modificationTime;
	private int modificationDate;
	private long crc32;
	private long compressedSize;
	private long uncompressedSize;
	private int fileNameLength;
	private int extraFieldLength;
	private byte[] fileName; 
	private byte[] extraField;
	private byte[] compressedFile;
	
	private boolean hasExt;
	private long extCrc32;
	private long extCompressedSize;
	private long extUncompressedSize;
	
	public FileEntry(CentralDirEntry dirEntry)
	{
		if (dirEntry == null)
			throw new RuntimeException("null dirEntry");
		
		this.dirEntry = dirEntry;
		this.hasExt = false;
	}
	
	public void copy(FileEntry entry)
	{
		version = entry.version;
		bitFlag = entry.bitFlag;
		compressionMethod = entry.compressionMethod;
		modificationTime = entry.modificationTime;
		modificationDate = entry.modificationDate;
		crc32 = entry.crc32;
		compressedSize = entry.compressedSize;
		uncompressedSize = entry.uncompressedSize;
		fileNameLength = entry.fileNameLength;
		extraFieldLength = entry.extraFieldLength;		
		fileName = Utils.clone(entry.fileName);
		extraField = Utils.clone(entry.extraField);
		compressedFile = Utils.clone(entry.compressedFile);
	}
	
	public void writeStd(LEDataOutputStream out) throws IOException {
		
		long crc32 = hasExt ? extCrc32 : this.crc32;
		crc32 = (crc32 != 0 ? crc32 : dirEntry.getCrc32());
		
		long compressedSize = hasExt ? extCompressedSize : this.compressedSize;
		compressedSize = (compressedSize != 0 ? compressedSize : dirEntry.getCompressedSize());
		
		long uncompressedSize = hasExt ? extUncompressedSize : this.uncompressedSize;
		uncompressedSize = (uncompressedSize != 0 ? uncompressedSize : dirEntry.getUncompressedSize());
		
		out.writeLEShort(version);
		out.writeLEShort(bitFlag);
		out.writeLEShort(compressionMethod);
		out.writeLEShort(modificationTime);
		out.writeLEShort(modificationDate);
		out.writeLEInt(crc32);
		out.writeLEInt(compressedSize);
		out.writeLEInt(uncompressedSize);
		out.writeLEShort(fileNameLength);
		out.writeLEShort(extraFieldLength);
		out.write(fileName);
		out.write(extraField);
		out.write(compressedFile);
	}
	
	public void write(LEDataOutputStream out) throws IOException {
		out.writeLEShort(version);
		out.writeLEShort(bitFlag);
		out.writeLEShort(compressionMethod);
		out.writeLEShort(modificationTime);
		out.writeLEShort(modificationDate);
		out.writeLEInt(crc32);
		out.writeLEInt(compressedSize);
		out.writeLEInt(uncompressedSize);
		out.writeLEShort(fileNameLength);
		out.writeLEShort(extraFieldLength);
		out.write(fileName);
		out.write(extraField);
		out.write(compressedFile);
	}
	
	public void writeExt(LEDataOutputStream out) throws IOException {
		out.writeLEInt(extCrc32);
		out.writeLEInt(extCompressedSize);
		out.writeLEInt(extUncompressedSize);
	}
	
	public void readExt(LEDataInputStream in) throws IOException {
		hasExt = true;
		extCrc32 = in.readLEInt();
		extCompressedSize = in.readLEInt();
		extUncompressedSize = in.readLEInt();
	}
	
	public void read(LEDataInputStream in) throws IOException {
		version = in.readLEShort();
		bitFlag = in.readLEShort();
		compressionMethod = in.readLEShort();
		modificationTime = in.readLEShort();
		modificationDate = in.readLEShort();
		crc32 = in.readLEInt();
		compressedSize = in.readLEInt();
		uncompressedSize = in.readLEInt();
		fileNameLength = in.readLEShort();
		extraFieldLength = in.readLEShort();
		
		fileName = new byte[fileNameLength];
		in.readFully(fileName);
		
		extraField = new byte[extraFieldLength];
		in.readFully(extraField);
		
		compressedFile = new byte[(int) (compressedSize == 0 ? dirEntry.getCompressedSize() : compressedSize)];
		in.readFully(compressedFile);
		
//		System.out.println("---");
//		System.out.println("FileEntry " + getFileNameStr());
//		System.out.println("extraFieldLength: " + extraFieldLength);
//		System.out.println("compressedFile.length: " + compressedFile.length);
	}
	
	public boolean hasExt() {
		return hasExt;
	}
	
	public long getTotalStdSize() {
		return 
		4L+(2L*5L)+(4L*3L)+(2L*2L)+
		((long) fileName.length)+
		((long) extraField.length)+
		((long) compressedFile.length);
	}
	
	/**
	 * Tamaño total incluyendo signature.
	 */
	public long getTotalSize() {
		return 
			 4L+(2L*5L)+(4L*3L)+(2L*2L)+
			 ((long) fileName.length)+
			 ((long) extraField.length)+
			 ((long) compressedFile.length)
			 +(hasExt?(4L*4L) : 0L);
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getBitFlag() {
		return bitFlag;
	}

	public void setBitFlag(int bitFlag) {
		this.bitFlag = bitFlag;
	}

	public int getCompressionMethod() {
		return compressionMethod;
	}

	public void setCompressionMethod(int compressionMethod) {
		this.compressionMethod = compressionMethod;
	}

	public int getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(int modificationTime) {
		this.modificationTime = modificationTime;
	}

	public int getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(int modificationDate) {
		this.modificationDate = modificationDate;
	}

	public long getCrc32() {
		return crc32;
	}

	public void setCrc32(long crc32) {
		this.crc32 = crc32;
	}

	public long getCompressedSize() {
		return compressedSize;
	}

	public void setCompressedSize(long compressedSize) {
		this.compressedSize = compressedSize;
	}

	public long getUncompressedSize() {
		return uncompressedSize;
	}

	public void setUncompressedSize(long uncompressedSize) {
		this.uncompressedSize = uncompressedSize;
	}

	public int getFileNameLength() {
		return fileNameLength;
	}

	public void setFileNameLength(int fileNameLength) {
		this.fileNameLength = fileNameLength;
	}

	public int getExtraFieldLength() {
		return extraFieldLength;
	}

	public void setExtraFieldLength(int extraFieldLength) {
		this.extraFieldLength = extraFieldLength;
	}
	
	public String getFileNameStr() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < fileName.length; i++) {
			sb.appendCodePoint(fileName[i]);
		}
		return sb.toString();
	}
	
	public void setFileNameStr(String fileNameStr) {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (int i = 0; i < fileNameStr.length(); i++) {
			int cp = fileNameStr.codePointAt(i);
			baos.write((byte) cp);
		}
		try {
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setFileName(baos.toByteArray());
	}
	
	public byte[] getFileName() {
		return fileName;
	}
	
	public void setFileName(byte[] fileName) {
		this.fileName = fileName;
	}

	public byte[] getExtraField() {
		return extraField;
	}

	public void setExtraField(byte[] extraField) {
		this.extraField = extraField;
	}

	public byte[] getCompressedFile() {
		return compressedFile;
	}

	public void setCompressedFile(byte[] compressedFile) {
		this.compressedFile = compressedFile;
	}

	public long getExtCrc32() {
		return extCrc32;
	}

	public void setExtCrc32(long extCrc32) {
		this.extCrc32 = extCrc32;
	}

	public long getExtCompressedSize() {
		return extCompressedSize;
	}

	public void setExtCompressedSize(long extCompressedSize) {
		this.extCompressedSize = extCompressedSize;
	}

	public long getExtUncompressedSize() {
		return extUncompressedSize;
	}

	public void setExtUncompressedSize(long extUncompressedSize) {
		this.extUncompressedSize = extUncompressedSize;
	}
	
	
}

package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;
import java.io.IOException;

public class CentralDirEntry {
	
	public static long SIGNATURE = 0x02014b50;
	
	private int versionMadeBy;
	private int versionNeeded;
	private int bitFlag;
	private int compressionMethod;
	private int modificationTime;
	private int modificationDate;
	private long crc32;
	private long compressedSize;
	private long uncompressedSize;
	private int fileNameLength;
	private int extraFieldLength;
	private int fileCommentLength;
	private int diskNumberStart;
	private int internalFileAttributes;
	private long externalFileAttributes;
	private long relativeOffsetOfFileHeader;
	private byte[] fileName;
	private byte[] extraField;
	private byte[] fileComment;
	
	public void copy(CentralDirEntry e) {
		versionMadeBy = e.versionMadeBy;
		versionNeeded = e.versionNeeded;
		bitFlag = e.bitFlag;
		compressionMethod = e.compressionMethod;
		modificationTime = e.modificationTime;
		modificationDate = e.modificationDate;
		crc32 = e.crc32;
		compressedSize = e.compressedSize;
		uncompressedSize = e.uncompressedSize;
		fileNameLength = e.fileNameLength;
		extraFieldLength = e.extraFieldLength;
		fileCommentLength = e.fileCommentLength;
		diskNumberStart = e.diskNumberStart;
		internalFileAttributes = e.internalFileAttributes;
		externalFileAttributes = e.externalFileAttributes;
		relativeOffsetOfFileHeader = e.relativeOffsetOfFileHeader;
		fileName = Utils.clone(e.fileName);
		extraField = Utils.clone(e.extraField);
		fileComment = Utils.clone(e.fileComment);
	}

	public long getTotalSize() {
		return
			4L+(2L*6L)+(4L*3L)+(2L*5L)+(4L*2L)+
			((long) fileName.length)+
			((long) extraField.length)+
			((long) fileComment.length);
	}
	
	public void write(LEDataOutputStream out) throws IOException {
		out.writeLEShort(versionMadeBy);
		out.writeLEShort(versionNeeded);
		out.writeLEShort(bitFlag);
		out.writeLEShort(compressionMethod);
		out.writeLEShort(modificationTime);
		out.writeLEShort(modificationDate);
		out.writeLEInt(crc32);
		out.writeLEInt(compressedSize);
		out.writeLEInt(uncompressedSize);
		out.writeLEShort(fileNameLength);
		out.writeLEShort(extraFieldLength);
		out.writeLEShort(fileCommentLength);
		out.writeLEShort(diskNumberStart);
		out.writeLEShort(internalFileAttributes);
		out.writeLEInt(externalFileAttributes);
		out.writeLEInt(relativeOffsetOfFileHeader);
		out.write(fileName);
		out.write(extraField);
		out.write(fileComment);
	}

	public void read(LEDataInputStream in) throws IOException {
		versionMadeBy = in.readLEShort();
		versionNeeded = in.readLEShort();
		bitFlag = in.readLEShort();
		compressionMethod = in.readLEShort();
		modificationTime = in.readLEShort();
		modificationDate = in.readLEShort();
		crc32 = in.readLEInt();
		compressedSize = in.readLEInt();
		uncompressedSize = in.readLEInt();
		fileNameLength = in.readLEShort();
		extraFieldLength = in.readLEShort();
		fileCommentLength = in.readLEShort();
		diskNumberStart = in.readLEShort();
		internalFileAttributes = in.readLEShort();
		externalFileAttributes = in.readLEInt();
		relativeOffsetOfFileHeader = in.readLEInt();
		
		fileName = new byte[fileNameLength];
		in.read(fileName);
		
		extraField = new byte[extraFieldLength];
		in.read(extraField);
		
		fileComment = new byte[fileCommentLength];
		in.read(fileComment);
		
//		System.out.println("---");
//		System.out.println("CentralDirEntry " + getFileNameStr());
//		System.out.println("extraFieldLength: " + extraFieldLength);
//		System.out.println("fileCommentLength: " + fileCommentLength);
	}

	public int getVersionMadeBy() {
		return versionMadeBy;
	}

	public void setVersionMadeBy(int versionMadeBy) {
		this.versionMadeBy = versionMadeBy;
	}

	public int getVersionNeeded() {
		return versionNeeded;
	}

	public void setVersionNeeded(int versionNeeded) {
		this.versionNeeded = versionNeeded;
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

	public int getFileCommentLength() {
		return fileCommentLength;
	}

	public void setFileCommentLength(int fileCommentLength) {
		this.fileCommentLength = fileCommentLength;
	}

	public int getDiskNumberStart() {
		return diskNumberStart;
	}

	public void setDiskNumberStart(int diskNumberStart) {
		this.diskNumberStart = diskNumberStart;
	}

	public int getInternalFileAttributes() {
		return internalFileAttributes;
	}

	public void setInternalFileAttributes(int internalFileAttributes) {
		this.internalFileAttributes = internalFileAttributes;
	}

	public long getExternalFileAttributes() {
		return externalFileAttributes;
	}

	public void setExternalFileAttributes(long externalFileAttributes) {
		this.externalFileAttributes = externalFileAttributes;
	}

	public long getRelativeOffsetOfFileHeader() {
		return relativeOffsetOfFileHeader;
	}

	public void setRelativeOffsetOfFileHeader(long relativeOffsetOfFileHeader) {
		this.relativeOffsetOfFileHeader = relativeOffsetOfFileHeader;
	}

	public byte[] getFileName() {
		return fileName;
	}
	
	public String getFileNameStr() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < fileName.length; i++) {
			sb.appendCodePoint(fileName[i]);
		}
		return sb.toString();
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

	public byte[] getFileComment() {
		return fileComment;
	}

	public void setFileComment(byte[] fileComment) {
		this.fileComment = fileComment;
	}
}

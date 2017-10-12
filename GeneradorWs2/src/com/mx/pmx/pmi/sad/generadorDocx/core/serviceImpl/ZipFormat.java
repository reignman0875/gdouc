package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.Deflater;

public class ZipFormat {
	
	private List<FileEntry> fileEntries = new LinkedList<FileEntry>();
	
	private Map<String, FileEntry> fileEntryMap = new HashMap<String, FileEntry>();
	
	private List<CentralDirEntry> centralDirectoryEntries = new LinkedList<CentralDirEntry>();
	
	private Map<String, CentralDirEntry> centralDirEntryMap = new HashMap<String, CentralDirEntry>();
	
	private EndOfCentralDirEntry endOfCentralDirectoryEntry = new EndOfCentralDirEntry();
	
	private boolean forceStandardFormatWrite = false;
	
    public static final int METHOD_STORED = 0;
    public static final int METHOD_DEFLATED = 8;
	
	public void write(LEDataOutputStream out) throws IOException {

		for (FileEntry entry : fileEntries)
		{
			out.writeLEInt(FileEntry.SIGNATURE);
			
			if (forceStandardFormatWrite) {
				entry.writeStd(out);
			}
			else {
				entry.write(out);
				if (entry.hasExt()) {
					out.writeLEInt(FileEntry.EXT_SIGNATURE);
					entry.writeExt(out);
				}
			}
		}
		
		for (CentralDirEntry entry : centralDirectoryEntries)
		{
			out.writeLEInt(CentralDirEntry.SIGNATURE);
			entry.write(out);
		}
		
		out.writeLEInt(EndOfCentralDirEntry.SIGNATURE);
		endOfCentralDirectoryEntry.write(out);
	}
	
	private long getCentralDirOffset(byte[] buff)
	{
		byte sig0 = 0x50;
		byte sig1 = 0x4b;
		byte sig2 = 0x05;
		byte sig3 = 0x06;
		
		for (int i = (buff.length-4); i >= 0; i--)
		{
			if ((buff[i+3] == sig3) && 
					(buff[i+2] == sig2) &&
					(buff[i+1] == sig1) &&
					(buff[i] == sig0))
			{
				int offset = i+16;
				long r = Utils.get32(buff, offset);
				return r;
				
//				int a = buff[offset];
//				int b = buff[offset+1];
//				int c = buff[offset+2];
//				int d = buff[offset+3];
//				
//				int centralDirOffset = (d << 24)+(c << 16)+(b << 8)+(a << 0);
//				return centralDirOffset;
			}
		}
		
		return -1;
	}

	public void read(byte[] buff) throws IOException
	{
		long cdOffset = getCentralDirOffset(buff);
		
		if (cdOffset == -1)
		{
			throw new RuntimeException("Offset del directorio cetral no encotnrado");
		}
		
		LEDataInputStream in = new LEDataInputStream(new ByteArrayInputStream(buff));
		
		in.mark(Integer.MAX_VALUE);
		in.skipBytes((int) cdOffset);
		
		centralDirectoryEntries.clear();
		centralDirEntryMap.clear();
		
		Map<Long, CentralDirEntry> centralDirEntryMapByOffset = new HashMap<Long, CentralDirEntry>();
		
		long signature = in.readLEInt();
		
		while (signature == CentralDirEntry.SIGNATURE)
		{
			CentralDirEntry entry = new CentralDirEntry();
			entry.read(in);
			//System.out.println("DirEntry: " + entry.getFileNameStr());
			centralDirectoryEntries.add(entry);
			centralDirEntryMap.put(entry.getFileNameStr(), entry);
			centralDirEntryMapByOffset.put(entry.getRelativeOffsetOfFileHeader(), entry);
			
			signature = in.readLEInt();
		}
		
		if (centralDirectoryEntries.isEmpty())
		{
			throw new RuntimeException("Directorio central vacío");
		}
		
		//System.out.println(centralDirectoryEntries.size() + " central dir entries");
		
		if (signature == EndOfCentralDirEntry.SIGNATURE)
		{
			endOfCentralDirectoryEntry.read(in);
		}
		
		if (in.read() != -1) {
			throw new RuntimeException("EOF esperado");
		}
		
		in.reset();
		
		long offset = 0;
		signature = in.readLEInt();
		
		fileEntries.clear();
		
		while (signature == FileEntry.SIGNATURE)
		{
			FileEntry entry = new FileEntry(centralDirEntryMapByOffset.get(offset));
			entry.read(in);
			fileEntries.add(entry);
			fileEntryMap.put(entry.getFileNameStr(), entry);
			
			signature = in.readLEInt();
			
			if (signature == FileEntry.EXT_SIGNATURE) {
				entry.readExt(in);
				signature = in.readLEInt();
			}
			
			offset += entry.getTotalSize();
		}
		
		if (fileEntries.isEmpty()) {
			throw new RuntimeException("No hay archivos.");
		}
		
		//System.out.println(fileEntries.size() + " file entries");
		
		if (signature != CentralDirEntry.SIGNATURE)
			throw new RuntimeException("dir central esperado");
	}
	
	public void duplicate(String fileName1, String fileName2) {
		
		FileEntry entry1 = fileEntryMap.get(fileName1);
		FileEntry entry2 = fileEntryMap.get(fileName2);
		
		if ((entry1 == null) || (entry2 == null)) {
			throw new RuntimeException();
		}
		
		entry2.setCompressedFile(entry1.getCompressedFile());
		entry2.setCompressedSize(entry1.getCompressedSize());
		entry2.setUncompressedSize(entry1.getUncompressedSize());
		entry2.setCrc32(entry1.getCrc32());
		
		CentralDirEntry dirEntry1 = centralDirEntryMap.get(fileName1);
		CentralDirEntry dirEntry2 = centralDirEntryMap.get(fileName2);
		
		if ((dirEntry1 == null) || (dirEntry2 == null)) {
			throw new RuntimeException();
		}
		
		dirEntry2.setCompressedSize(dirEntry1.getCompressedSize());
		dirEntry2.setUncompressedSize(dirEntry1.getUncompressedSize());
		dirEntry2.setCrc32(dirEntry1.getCrc32());
		
		endOfCentralDirectoryEntry.setCentralDirectoryStartOffset(getStartOfCentralDir());
		endOfCentralDirectoryEntry.setCentralDirectorySize(getSizeOfCentralDir());
		
		calcFileEntriesOffsets();
	}
	
	private long getStartOfCentralDir() {
		long start = 0;
		for (FileEntry e : fileEntries) {
			start += forceStandardFormatWrite ? e.getTotalStdSize() : e.getTotalSize();
		}
		return start;
	}
	
	private long getSizeOfCentralDir() {
		long size = 0;
		for (CentralDirEntry e : centralDirectoryEntries) {
			size += e.getTotalSize();
		}
		return size;
	}
	
	private void calcFileEntriesOffsets() {
		long offset = 0;
		for (FileEntry fileEntry : fileEntries)
		{
			String fileName = fileEntry.getFileNameStr();
			CentralDirEntry dirEntry = centralDirEntryMap.get(fileName);
			dirEntry.setRelativeOffsetOfFileHeader(offset);
			offset += (forceStandardFormatWrite ? fileEntry.getTotalStdSize() : fileEntry.getTotalSize());
		}
	}
	
	public void insertFile(String fileName, File file) throws IOException {
		
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;
		
		try
		{
			byte[] buffer = new byte[1024];
			
			fis = new FileInputStream(file);
			baos = new ByteArrayOutputStream();
			
			int read;
			while ((read = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, read);
			}
			baos.flush();
			
			insertFile(fileName, baos.toByteArray());
		}
		finally
		{
			Utils.closeStream(fis);
			Utils.closeStream(baos);
		}
		
		
	}
	
	public void insertFile(String fileName, byte[] data) throws IOException {
		
		FileEntry fileEntry = fileEntryMap.get(fileName);
		CentralDirEntry dirEntry = centralDirEntryMap.get(fileName);
		
		if ((fileEntry == null) || (dirEntry == null)) {
			throw new RuntimeException();
		}
		
		CRC32 crc = new CRC32();
		for (int i = 0; i < data.length; i++)
		{
			crc.update(data[i]);
		}
		
		byte[] uncompressedFile = data;
		byte[] compressedFile;
		
		if (fileEntry.getCompressionMethod() == METHOD_STORED)
		{
			compressedFile = uncompressedFile;
		}
		else if (fileEntry.getCompressionMethod() == METHOD_DEFLATED)
		{
			compressedFile = compress(uncompressedFile);
		}
		else
		{
			throw new RuntimeException("Método de compresión desconocido.");
		}
		
		// Utils.dumpToFile(uncompressedFile, "uncompressed", "txt");
		// Utils.dumpToFile(compressedFile, "compressed", "txt");

		fileEntry.setCompressedFile(compressedFile);
		
		long compressedSize = compressedFile.length;
		long uncompressedSize = uncompressedFile.length;
		long crc32 = crc.getValue();
		
		fileEntry.setCompressedSize(compressedSize);
		fileEntry.setUncompressedSize(uncompressedSize);
		fileEntry.setCrc32(crc32);
		
		if (fileEntry.hasExt()) {
			fileEntry.setExtCompressedSize(compressedSize);
			fileEntry.setExtUncompressedSize(uncompressedSize);
			fileEntry.setExtCrc32(crc32);
		}
		
		dirEntry.setCompressedSize(compressedSize);
		dirEntry.setUncompressedSize(uncompressedSize);
		dirEntry.setCrc32(crc32);
		
		endOfCentralDirectoryEntry.setCentralDirectoryStartOffset(getStartOfCentralDir());
		endOfCentralDirectoryEntry.setCentralDirectorySize(getSizeOfCentralDir());
		
		calcFileEntriesOffsets();
	}
	
	static public byte[] compress(byte[] input) {
		Deflater deflater = new Deflater(Deflater.BEST_SPEED, true);
		deflater.setInput(input, 0, input.length);
		deflater.finish();
		byte[] buff = new byte[input.length + 1024];
		int wsize = deflater.deflate(buff);
		
		int compressedSize = deflater.getTotalOut();
		
		if (wsize == 0)
			throw new RuntimeException();
		else if (wsize != compressedSize)
			throw new RuntimeException();

		if (deflater.getTotalIn() != input.length)
			throw new RuntimeException();
		if (compressedSize >= input.length - 4)
			throw new RuntimeException();
		
		byte[] output = new byte[compressedSize];
		System.arraycopy(buff, 0, output, 0, compressedSize);
		return output;
	}
	
	

}

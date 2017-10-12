package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;

import java.util.LinkedList;
import java.util.List;

/**
 * Proveé funciones de ayuda para resolver rutas de archivos absolutas y
 * relativas.
 * 
 * @author Ernesto Badillo <edmundo-ernesto.badillo.fernandez@hp.com>
 */
public final class PathUtils {

	/**
	 * Representa al directorio actual.
	 */
	public static final String CURRENT_DIR = ".";

	/**
	 * Representa el directorio padre.
	 */
	public static final String PARENT_DIR = "..";

	/**
	 * Representa el separador de directorios.
	 */
	public static final String DIR_SEPARATOR = "/";

	/**
	 * Regresa una ruta relativa al directorio actual a partir de la ruta absoluta.
	 * 
	 * @param currentDirPath
	 *            el directorio actual
	 * @param absoluteFilePath
	 *            la ruta absoluta del archivo
	 * @return la ruta relativa del archivo
	 */
	public static String getRelativeFilePath(String currentDirPath,
			String absoluteFilePath) {

		List<String> relPathList = new LinkedList<String>();

		int lastSep = absoluteFilePath.lastIndexOf(DIR_SEPARATOR);
		String absoluteDirPath = absoluteFilePath.substring(0, lastSep);
		String fileName = absoluteFilePath.substring(lastSep + 1);

		String[] absDirTokens = absoluteDirPath.split(DIR_SEPARATOR);
		String[] curDirTokens = currentDirPath.split(DIR_SEPARATOR);

		int lastEqIdx = -1;
		for (int i = 0; i < curDirTokens.length; i++) {
			if (i < absDirTokens.length
					&& absDirTokens[i].equals(curDirTokens[i])) {
				lastEqIdx = i;
			} else {
				break;
			}
		}

		for (int i = lastEqIdx + 1; i < curDirTokens.length; i++) {
			relPathList.add(PARENT_DIR);
		}

		for (int i = lastEqIdx + 1; i < absDirTokens.length; i++) {
			relPathList.add(absDirTokens[i]);
		}

		relPathList.add(fileName);

		return pathToString(relPathList);
	}

	/**
	 * Regresa la ruta absoluta del archivo dadas la ruta absoluta del
	 * directorio actual y la ruta relativa al directorio actual del archivo.
	 * 
	 * @param currentDirPath
	 *            el path del directorio actual
	 * @param relativeFilePath
	 *            el path relativo
	 * @return el path absoluto
	 */
	public static String getAbsoluteFilePath(String currentDirPath,
			String relativeFilePath) {

		String[] relTokens = relativeFilePath.split(DIR_SEPARATOR);
		String[] curTokens = currentDirPath.split(DIR_SEPARATOR);

		List<String> absPathList = new LinkedList<String>();

		for (String dir : curTokens) {
			absPathList.add(dir);
		}

		for (String dir : relTokens) {
			if (dir.equals(CURRENT_DIR)) {
				continue;
			} else if (dir.equals(PARENT_DIR)) {
				absPathList.remove(absPathList.size() - 1);
			} else {
				absPathList.add(dir);
			}
		}

		return pathToString(absPathList);
	}

	/**
	 * Método de ayuda. Convierte un path dado como una lista de strings en su
	 * representación como string.
	 * 
	 * @param listPath
	 *            la representación del path en una lista de strings
	 * @return la representación del path en string
	 */
	private static String pathToString(List<String> listPath) {
		StringBuffer absPath = new StringBuffer();
		for (String dir : listPath) {
			if (absPath.length() > 0) {
				absPath.append(DIR_SEPARATOR);
			}
			absPath.append(dir);
		}

		return absPath.toString();
	}
}

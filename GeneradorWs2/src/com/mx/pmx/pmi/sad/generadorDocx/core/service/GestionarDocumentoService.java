package com.mx.pmx.pmi.sad.generadorDocx.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocumentoDto;
import com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocumentoV2Dto;
import com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocumentoV3Dto;

public interface GestionarDocumentoService {

	public void guardarDocumento(final DocumentoDto documentoDto, String userLT);
//	public void guardarDocumento(final DocumentoDto documentoDto, String tipoDocumental, String userLT);
	public void guardarDocumento(final DocumentoDto documentoDto, String tipoDocumental, String userLT, Map<String, String> attr);
	public void guardarDocumento(final List<DocumentoDto> documentoList, String userLT);	
	public DocumentoDto recuperarDocumento(final String idDocumento, String userLT);
	public List<DocumentoDto> recuperarDocumento(final List<String> idDocumentoList, String userLT);

	
	public DocumentoDto recuperarDocumentoVisible(final String idDocumento);
	public DocumentoDto recuperarRendicion(String idDocumento, String formato,
			String nombreArchivo);
	public String obtenerPropiedad(String idDocumento, String nombrePropiedad,String tipoDocumental);
	public void moverDocumento(final List<String> idDocumentoList, final String directorioDestino);
	public void copiarDocumento(final List<String> idDocumentoList, final String directorioDestino);
	public List<DocumentoV2Dto> consultarDocumentosPorRuta(String ruta);
	public List<DocumentoV3Dto> consultarDocumentosPorRuta(String documentType, String ruta, String[] atributos);
	public void enviarMail(String idUsuario, String idDocumento, String mensaje, String titulo);
	public void guardarDocumentoVirtual(String documentoVirtualId, List<DocumentoDto> docVirtualHijoIdList);
	public void guardarDocumentoVirtual(String documentoVirtualId, DocumentoDto documentoDto);
	public void borrarDocumento(String documentoVirtualId);
	public void modificarDocumento(List<DocumentoDto> documentoDtoList);
	public void modificarDocumento(DocumentoDto documentoDto);
	public IDfCollection ejecutarConsulta(final String dqlQuery);
	public List<String> consultarIdDocumento(final String dql);
	public List<String> consultarIdDocumento(final String[] paramName, final Object[] paramValue);
	public List<String> consultarIdDocumento(final String dql, final String[] paramName, final Object[] paramValue);
	public DocumentoDto generarRendicion(String idDocumento);
	public DocumentoDto generarRendicion(String idDocumentoXml,String idDocumentoOriginal, DocumentoDto documentoDtoRendicion);
	public void eliminarDocumento(List<String> idDocumentoList);
	public void eliminarDocumento(String idDocumento);
	
	public void setDocumentumService(DocumentumService documentumService);
	public void setDocumentType(String documentType);
	public void setQualifierAttribute(String qualifierAttribute);
	public DocumentumService getDocumentumService();
	public String getDocumentType();
	public String getQualifierAttribute();
	public String getVirtualDocumentType();
	
	public enum DocumentumMimeTypes {
		afp("AFP"), ai("illustrator"), aifc("aiff-c"), aiff("aiff"), asf("asf"), asm(
				"ptcasm"), asp("asp"), asx("asx"), atd("atd"), att("att"), au(
				"au"), avi("avi"), bas("vbbas"), bin("bin"), bk("mbook"), bmp(
				"bmp"), cfm("cfm"), cfml("cfml"), cgi("cgi"), cgm("cgm"), chm(
				"htmlhelp"), css("css"), ctm("ctm"), cvs("canvas"), daf("daf"), dar(
				"DAR"), dcm("dicom"), dec("dec"), dgn("ustn"), dib("dib"), dita(
				"dita"), ditamap("ditamap"), dll("win32shrlib"), dng("dng"), doc(
				"msw8"), docm("msw12me"), docx("msw12"), dot("msw8template"), dotm(
				"msw12metemplate"), dotx("msw12template"), drw("ptcdrw"), dtd(
				"dtd"), dv("dv"), dwf("dwf"), dwg("acad"), dwt("mmdwt"), dxf(
				"dxf"), eap("eap"), elm("elm"), emcmf("emcmf"), emfo("emf"), eml(
				"eml"), emz("emz"), ent("ent"), eps("eps"), fdf("fdf"), fla(
				"flash"), fm("mdoc"), fos("fos"), fpx("fpx"), fp3(
				"filemakerpro3"), fp4("filemakerpro4"), frm("vbfrm"), gif("gif"), gxf(
				"gxf"), hdml("hdml"), hhf("hhf"), htm("html"), idl("idl"), incd(
				"incopy3"), incx("incopy5"), indd("indesign5"), jar("jar"), java(
				"java"), jhtml("jhtml"), jnt("jnt"), jpg("jpeg"), jp2(
				"jpeg2000"), js("js"), jsp("jsp"), lbi("mmlbi"), lxf("lxf"), mak(
				"vbmak"), map("imagemap"), mcr("mcr"), mdb("ms_access"), mde(
				"ms_access8_mde"), mht("mht"), mif("mif"), mmap("mmap"), mmas(
				"mmas"), mmat("mmat"), mod("mod"), mov("quicktime"), mpc(
				"msproject_calendar"), mpg("mpeg"), mpg2("mpeg2"), mpp(
				"msproject"), mps("mps"), mpt("mpt"), mpv("msproject_view"), mpw(
				"mpw"), mp3("mp3"), mp4("mpeg-4a"), msg("msg"), mss("mss"), mxf(
				"mxf"), odt("odt"), oft("oft"), one("one"), onetoc("onetoc"), opml(
				"opml"), ov("cals2"), pcd("pcd"), pcl("pcl"), pct("pict"), pcx(
				"pcx"), pdf("pdf"), pen("pen"), pff("tpz_pff"), pgm("pgm"), php(
				"php"), php3("php3"), phtml("phtml"), pm6("pagemaker"), png(
				"png"), pnm("pnm"), pot("ppt8_template"), potm(
				"ppt12metemplate"), potx("ppt12template"), ppm("ppm"), ppsm(
				"ppt12meslideshow"), ppsx("ppt12slideshow"), ppt("ppt8"), pptm(
				"ppt12me"), pptx("ppt12"), pre("freelance"), pro("pro"), properties(
				"property"), prt("ptcprt"), ps("ps"), psd("photoshop8"), ptd(
				"ptd"), pub("pub"), qxd("quark"), qxp("quark6"), ra("ra"), ram(
				"ram"), ras("ras"), rft("dca"), rf10("rsrc10"), rf9("rsrc9"), rle(
				"rle"), rls("rls"), rlx("rlx"), rm("rm"), rmh("rmh"), rmm("rmm"), rnx(
				"rnx"), rtf("rtf"), rv("rv"), sam("amipro"), scm("scam"), sct(
				"sct"), sgi("sgi"), sgm("sgml"), shtml("shtml"), sl(
				"hpuxshrlib"), smil("smil"), so("ibmshrlib"), soc("soc"), spl(
				"spl"), spml("spml"), stm("stm"), sun("sun"), svg("svg"), swf(
				"swf"), tbr("tbr"), tga("tga"), tif("tiff"), txt("text"), ump(
				"ump"), voc("voc"), vrf("vrf"), vsd("vsd"), vss("vss"), vst(
				"vst"), vsw("vsw"), wav("wave"), wax("wax"), wbmp("wbmp"), wk4(
				"123w"), wma("wma"), wmf("wmf"), wmv("wmv"), wpd("wp8"), wri(
				"winwrite"), wsdl("wsdl"), wvx("wvx"), xfdf("xfdf"), xfm("xfm"), xls(
				"excel"), xlsm("excel12mebook"), xlsx("excel12book"), xlt(
				"excel8template"), xltm("excel12metemplate"), xltx(
				"excel12template"), xlw("excel4book"), xmi("xmi"), xml("xml"), xsd(
				"xsd"), xsf("xsf"), xsl("xsl"), xsn("xsn"), xtg("quark6_report"), zip(
				"zip");

		private final String documentumMimeType;
        
        
		DocumentumMimeTypes(String documentumMimeType) {
			this.documentumMimeType = documentumMimeType;
		}

		public String getDocumentumMimeType() {
			return documentumMimeType;
		}
	}
}

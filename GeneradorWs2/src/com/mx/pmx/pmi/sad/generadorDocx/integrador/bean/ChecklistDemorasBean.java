package com.mx.pmx.pmi.sad.generadorDocx.integrador.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ChecklistDemoras")
public class ChecklistDemorasBean extends PadreBean {

	public ChecklistDemorasBean() {
		crudo=productos=fecha=ordenPmi=ordenesRelacionadas=nombreBarco=ordenFletamento=reclamoInicialCalculoInicialPmi
				=reclamoInicialCalculoInicialPmiC=reclamoIncialCalculoInicialContraparte=reclamoIncialCalculoInicialContraparteC
				=respaldoNegociacion=respaldoNegociacionC=documentacionSoportePago=documentacionSoportePagoC=documentacionSoporteCobro
				=documentacionSoporteCobroC=hojaCalculoFinal=hojaCalculoFinalC=solicitudCobroTesoreria=solicitudCobroTesoreriaC
				=solicitudPagoTesoreria=solicitudPagoTesoreriaC=notaInformativa=notaInformativaC=otrosEspecificar=otrosEspecificar2
				=numeroFolios=numeroHojas=numeroPaginas=integroNombreFirma=analistaNombreRubrica 
				=demoraFacturaCuentasCobrar=demoraFacturaCuentasCobrarC=demoraFacturaCuentasPagar=demoraFacturaCuentasPagarC = new String();
	}
	public String getDemoraFacturaCuentasCobrar() {
		return demoraFacturaCuentasCobrar;
	}
	public void setDemoraFacturaCuentasCobrar(String demoraFacturaCuentasCobrar) {
		this.demoraFacturaCuentasCobrar = demoraFacturaCuentasCobrar;
	}
	public String getDemoraFacturaCuentasCobrarC() {
		return demoraFacturaCuentasCobrarC;
	}
	public void setDemoraFacturaCuentasCobrarC(String demoraFacturaCuentasCobrarC) {
		this.demoraFacturaCuentasCobrarC = demoraFacturaCuentasCobrarC;
	}
	public String getDemoraFacturaCuentasPagar() {
		return demoraFacturaCuentasPagar;
	}
	public void setDemoraFacturaCuentasPagar(String demoraFacturaCuentasPagar) {
		this.demoraFacturaCuentasPagar = demoraFacturaCuentasPagar;
	}
	public String getDemoraFacturaCuentasPagarC() {
		return demoraFacturaCuentasPagarC;
	}
	public void setDemoraFacturaCuentasPagarC(String demoraFacturaCuentasPagarC) {
		this.demoraFacturaCuentasPagarC = demoraFacturaCuentasPagarC;
	}
	private String crudo;
	private String productos;
	private String fecha;
	private String ordenPmi;
	private String ordenesRelacionadas;
	private String nombreBarco;
	private String ordenFletamento;
	private String reclamoInicialCalculoInicialPmi;
	private String reclamoInicialCalculoInicialPmiC;
	private String reclamoIncialCalculoInicialContraparte;
	private String reclamoIncialCalculoInicialContraparteC;
	private String respaldoNegociacion;
	private String respaldoNegociacionC;
	private String documentacionSoportePago;
	private String documentacionSoportePagoC;
	private String documentacionSoporteCobro;
	private String documentacionSoporteCobroC;
	private String hojaCalculoFinal;
	private String hojaCalculoFinalC;
	private String solicitudCobroTesoreria;
	private String solicitudCobroTesoreriaC;
	private String solicitudPagoTesoreria;
	private String solicitudPagoTesoreriaC;
	private String notaInformativa;
	private String notaInformativaC;
	private String demoraFacturaCuentasCobrar;
	private String demoraFacturaCuentasCobrarC;
	private String demoraFacturaCuentasPagar;
	private String demoraFacturaCuentasPagarC;
	private String otrosEspecificar;
	private String otrosEspecificar2;
	private String numeroFolios;
	private String numeroHojas;
	private String numeroPaginas;
	private String integroNombreFirma;
	private String analistaNombreRubrica;
	public String getCrudo() {
		return crudo;
	}
	public void setCrudo(String crudo) {
		this.crudo = crudo;
	}
	public String getProductos() {
		return productos;
	}
	public void setProductos(String productos) {
		this.productos = productos;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getOrdenPmi() {
		return ordenPmi;
	}
	public void setOrdenPmi(String ordenPmi) {
		this.ordenPmi = ordenPmi;
	}
	public String getOrdenesRelacionadas() {
		return ordenesRelacionadas;
	}
	public void setOrdenesRelacionadas(String ordenesRelacionadas) {
		this.ordenesRelacionadas = ordenesRelacionadas;
	}
	public String getNombreBarco() {
		return nombreBarco;
	}
	public void setNombreBarco(String nombreBarco) {
		this.nombreBarco = nombreBarco;
	}
	public String getOrdenFletamento() {
		return ordenFletamento;
	}
	public void setOrdenFletamento(String ordenFletamento) {
		this.ordenFletamento = ordenFletamento;
	}
	public String getReclamoInicialCalculoInicialPmi() {
		return reclamoInicialCalculoInicialPmi;
	}
	public void setReclamoInicialCalculoInicialPmi(String reclamoInicialCalculoInicialPmi) {
		this.reclamoInicialCalculoInicialPmi = reclamoInicialCalculoInicialPmi;
	}
	public String getReclamoInicialCalculoInicialPmiC() {
		return reclamoInicialCalculoInicialPmiC;
	}
	public void setReclamoInicialCalculoInicialPmiC(String reclamoInicialCalculoInicialPmiC) {
		this.reclamoInicialCalculoInicialPmiC = reclamoInicialCalculoInicialPmiC;
	}
	public String getReclamoIncialCalculoInicialContraparte() {
		return reclamoIncialCalculoInicialContraparte;
	}
	public void setReclamoIncialCalculoInicialContraparte(String reclamoIncialCalculoInicialContraparte) {
		this.reclamoIncialCalculoInicialContraparte = reclamoIncialCalculoInicialContraparte;
	}
	public String getReclamoIncialCalculoInicialContraparteC() {
		return reclamoIncialCalculoInicialContraparteC;
	}
	public void setReclamoIncialCalculoInicialContraparteC(String reclamoIncialCalculoInicialContraparteC) {
		this.reclamoIncialCalculoInicialContraparteC = reclamoIncialCalculoInicialContraparteC;
	}
	public String getRespaldoNegociacion() {
		return respaldoNegociacion;
	}
	public void setRespaldoNegociacion(String respaldoNegociacion) {
		this.respaldoNegociacion = respaldoNegociacion;
	}
	public String getRespaldoNegociacionC() {
		return respaldoNegociacionC;
	}
	public void setRespaldoNegociacionC(String respaldoNegociacionC) {
		this.respaldoNegociacionC = respaldoNegociacionC;
	}
	public String getDocumentacionSoportePago() {
		return documentacionSoportePago;
	}
	public void setDocumentacionSoportePago(String documentacionSoportePago) {
		this.documentacionSoportePago = documentacionSoportePago;
	}
	public String getDocumentacionSoportePagoC() {
		return documentacionSoportePagoC;
	}
	public void setDocumentacionSoportePagoC(String documentacionSoportePagoC) {
		this.documentacionSoportePagoC = documentacionSoportePagoC;
	}
	public String getDocumentacionSoporteCobro() {
		return documentacionSoporteCobro;
	}
	public void setDocumentacionSoporteCobro(String documentacionSoporteCobro) {
		this.documentacionSoporteCobro = documentacionSoporteCobro;
	}
	public String getDocumentacionSoporteCobroC() {
		return documentacionSoporteCobroC;
	}
	public void setDocumentacionSoporteCobroC(String documentacionSoporteCobroC) {
		this.documentacionSoporteCobroC = documentacionSoporteCobroC;
	}
	public String getHojaCalculoFinal() {
		return hojaCalculoFinal;
	}
	public void setHojaCalculoFinal(String hojaCalculoFinal) {
		this.hojaCalculoFinal = hojaCalculoFinal;
	}
	public String getHojaCalculoFinalC() {
		return hojaCalculoFinalC;
	}
	public void setHojaCalculoFinalC(String hojaCalculoFinalC) {
		this.hojaCalculoFinalC = hojaCalculoFinalC;
	}
	public String getSolicitudCobroTesoreria() {
		return solicitudCobroTesoreria;
	}
	public void setSolicitudCobroTesoreria(String solicitudCobroTesoreria) {
		this.solicitudCobroTesoreria = solicitudCobroTesoreria;
	}
	public String getSolicitudCobroTesoreriaC() {
		return solicitudCobroTesoreriaC;
	}
	public void setSolicitudCobroTesoreriaC(String solicitudCobroTesoreriaC) {
		this.solicitudCobroTesoreriaC = solicitudCobroTesoreriaC;
	}
	public String getSolicitudPagoTesoreria() {
		return solicitudPagoTesoreria;
	}
	public void setSolicitudPagoTesoreria(String solicitudPagoTesoreria) {
		this.solicitudPagoTesoreria = solicitudPagoTesoreria;
	}
	public String getSolicitudPagoTesoreriaC() {
		return solicitudPagoTesoreriaC;
	}
	public void setSolicitudPagoTesoreriaC(String solicitudPagoTesoreriaC) {
		this.solicitudPagoTesoreriaC = solicitudPagoTesoreriaC;
	}
	public String getNotaInformativa() {
		return notaInformativa;
	}
	public void setNotaInformativa(String notaInformativa) {
		this.notaInformativa = notaInformativa;
	}
	public String getNotaInformativaC() {
		return notaInformativaC;
	}
	public void setNotaInformativaC(String notaInformativaC) {
		this.notaInformativaC = notaInformativaC;
	}
	public String getOtrosEspecificar() {
		return otrosEspecificar;
	}
	public void setOtrosEspecificar(String otrosEspecificar) {
		this.otrosEspecificar = otrosEspecificar;
	}
	public String getOtrosEspecificar2() {
		return otrosEspecificar2;
	}
	public void setOtrosEspecificar2(String otrosEspecificar2) {
		this.otrosEspecificar2 = otrosEspecificar2;
	}
	public String getNumeroFolios() {
		return numeroFolios;
	}
	public void setNumeroFolios(String numeroFolios) {
		this.numeroFolios = numeroFolios;
	}
	public String getNumeroHojas() {
		return numeroHojas;
	}
	public void setNumeroHojas(String numeroHojas) {
		this.numeroHojas = numeroHojas;
	}
	public String getNumeroPaginas() {
		return numeroPaginas;
	}
	public void setNumeroPaginas(String numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	public String getIntegroNombreFirma() {
		return integroNombreFirma;
	}
	public void setIntegroNombreFirma(String integroNombreFirma) {
		this.integroNombreFirma = integroNombreFirma;
	}
	public String getAnalistaNombreRubrica() {
		return analistaNombreRubrica;
	}
	public void setAnalistaNombreRubrica(String analistaNombreRubrica) {
		this.analistaNombreRubrica = analistaNombreRubrica;
	}

}

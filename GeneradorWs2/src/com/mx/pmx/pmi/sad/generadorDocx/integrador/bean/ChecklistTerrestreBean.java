package com.mx.pmx.pmi.sad.generadorDocx.integrador.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ChecklistTerrestre")
public class ChecklistTerrestreBean extends PadreBean {
	
	public ChecklistTerrestreBean() {
		autotransporte=tFerroviario=ducto=multimodal=fecha=ordenPmi=ordenesRelacionadas=allocation=transportista=nominacionInspeccion
				=nominacionInspeccionC=nominacionTransportista=nominacionTransportistaC=nominacionCarga=nominacionCargaC=nominacionDescarga
				=nominacionDescargaC=nominacionServiciosTerminal=nominacionServiciosTerminalC=instruccionEmbarqueAgenteAduanal
				=instruccionEmbarqueAgenteAduanalC=instruccionAditivacionProducto=instruccionAditivacionProductoC
				=confirmacionBookingMultimodal=confirmacionBookingMultimodalC=aceptacionServicioInspeccion=aceptacionServicioInspeccionC
				=correoAutorizacionEmbarqueTipoPago=correoAutorizacionEmbarqueTipoPagoC=facturaProforma=facturaProformaC=certificadoOrigen
				=certificadoOrigenC=certificadoOrigenCamaraComercio=certificadoOrigenCamaraComercioC=avisoCargaEntrega=avisoCargaEntregaC
				=reporteInspeccionCalidadCantidadCarga=reporteInspeccionCalidadCantidadCargaC=reporteInspeccionCalidadCantidadDescarga
				=reporteInspeccionCalidadCantidadDescargaC=talonEmbarqueCartaPorte=talonEmbarqueCartaPorteC=ticketBombeoDucto
				=ticketBombeoDuctoC=envioDocumentosCliente=envioDocumentosClienteC=hojaCalculoPrecio=hojaCalculoPrecioC=ajusteVolumenPrecio
				=ajusteVolumenPrecioC=cartaProtesta=cartaProtestaC=otrosEspecificar=otrosEspecificar2=numeroHojas=numeroPaginas=numeroFolios
				=integroNombre=operadorNombre = new String();
	}

	private String autotransporte;
	private String tFerroviario;
	private String ducto;
	private String multimodal;
	private String fecha;
	private String ordenPmi;
	private String ordenesRelacionadas;
	private String allocation;
	private String transportista;
	private String nominacionInspeccion;
	private String nominacionInspeccionC;
	private String nominacionTransportista;
	private String nominacionTransportistaC;
	private String nominacionCarga;
	private String nominacionCargaC;
	private String nominacionDescarga;
	private String nominacionDescargaC;
	private String nominacionServiciosTerminal;
	private String nominacionServiciosTerminalC;
	private String instruccionEmbarqueAgenteAduanal;
	private String instruccionEmbarqueAgenteAduanalC;
	private String instruccionAditivacionProducto;
	private String instruccionAditivacionProductoC;
	private String confirmacionBookingMultimodal;
	private String confirmacionBookingMultimodalC;
	private String aceptacionServicioInspeccion;
	private String aceptacionServicioInspeccionC;
	private String correoAutorizacionEmbarqueTipoPago;
	private String correoAutorizacionEmbarqueTipoPagoC;
	private String facturaProforma;
	private String facturaProformaC;
	private String certificadoOrigen;
	private String certificadoOrigenC;
	private String certificadoOrigenCamaraComercio;
	private String certificadoOrigenCamaraComercioC;
	private String avisoCargaEntrega;
	private String avisoCargaEntregaC;
	private String reporteInspeccionCalidadCantidadCarga;
	private String reporteInspeccionCalidadCantidadCargaC;
	private String reporteInspeccionCalidadCantidadDescarga;
	private String reporteInspeccionCalidadCantidadDescargaC;
	private String talonEmbarqueCartaPorte;
	private String talonEmbarqueCartaPorteC;
	private String ticketBombeoDucto;
	private String ticketBombeoDuctoC;
	private String envioDocumentosCliente;
	private String envioDocumentosClienteC;
	private String hojaCalculoPrecio;
	private String hojaCalculoPrecioC;
	private String ajusteVolumenPrecio;
	private String ajusteVolumenPrecioC;
	private String cartaProtesta;
	private String cartaProtestaC;
	private String otrosEspecificar;
	private String otrosEspecificar2;
	private String numeroHojas;
	private String numeroPaginas;
	private String numeroFolios;
	private String integroNombre;
	private String operadorNombre;
	public String getAutotransporte() {
		return autotransporte;
	}
	public void setAutotransporte(String autotransporte) {
		this.autotransporte = autotransporte;
	}
	public String gettFerroviario() {
		return tFerroviario;
	}
	public void settFerroviario(String tFerroviario) {
		this.tFerroviario = tFerroviario;
	}
	public String getDucto() {
		return ducto;
	}
	public void setDucto(String ducto) {
		this.ducto = ducto;
	}
	public String getMultimodal() {
		return multimodal;
	}
	public void setMultimodal(String multimodal) {
		this.multimodal = multimodal;
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
	public String getAllocation() {
		return allocation;
	}
	public void setAllocation(String allocation) {
		this.allocation = allocation;
	}
	public String getTransportista() {
		return transportista;
	}
	public void setTransportista(String transportista) {
		this.transportista = transportista;
	}
	public String getNominacionInspeccion() {
		return nominacionInspeccion;
	}
	public void setNominacionInspeccion(String nominacionInspeccion) {
		this.nominacionInspeccion = nominacionInspeccion;
	}
	public String getNominacionInspeccionC() {
		return nominacionInspeccionC;
	}
	public void setNominacionInspeccionC(String nominacionInspeccionC) {
		this.nominacionInspeccionC = nominacionInspeccionC;
	}
	public String getNominacionTransportista() {
		return nominacionTransportista;
	}
	public void setNominacionTransportista(String nominacionTransportista) {
		this.nominacionTransportista = nominacionTransportista;
	}
	public String getNominacionTransportistaC() {
		return nominacionTransportistaC;
	}
	public void setNominacionTransportistaC(String nominacionTransportistaC) {
		this.nominacionTransportistaC = nominacionTransportistaC;
	}
	public String getNominacionCarga() {
		return nominacionCarga;
	}
	public void setNominacionCarga(String nominacionCarga) {
		this.nominacionCarga = nominacionCarga;
	}
	public String getNominacionCargaC() {
		return nominacionCargaC;
	}
	public void setNominacionCargaC(String nominacionCargaC) {
		this.nominacionCargaC = nominacionCargaC;
	}
	public String getNominacionDescarga() {
		return nominacionDescarga;
	}
	public void setNominacionDescarga(String nominacionDescarga) {
		this.nominacionDescarga = nominacionDescarga;
	}
	public String getNominacionDescargaC() {
		return nominacionDescargaC;
	}
	public void setNominacionDescargaC(String nominacionDescargaC) {
		this.nominacionDescargaC = nominacionDescargaC;
	}
	public String getNominacionServiciosTerminal() {
		return nominacionServiciosTerminal;
	}
	public void setNominacionServiciosTerminal(String nominacionServiciosTerminal) {
		this.nominacionServiciosTerminal = nominacionServiciosTerminal;
	}
	public String getNominacionServiciosTerminalC() {
		return nominacionServiciosTerminalC;
	}
	public void setNominacionServiciosTerminalC(String nominacionServiciosTerminalC) {
		this.nominacionServiciosTerminalC = nominacionServiciosTerminalC;
	}
	public String getInstruccionEmbarqueAgenteAduanal() {
		return instruccionEmbarqueAgenteAduanal;
	}
	public void setInstruccionEmbarqueAgenteAduanal(String instruccionEmbarqueAgenteAduanal) {
		this.instruccionEmbarqueAgenteAduanal = instruccionEmbarqueAgenteAduanal;
	}
	public String getInstruccionEmbarqueAgenteAduanalC() {
		return instruccionEmbarqueAgenteAduanalC;
	}
	public void setInstruccionEmbarqueAgenteAduanalC(String instruccionEmbarqueAgenteAduanalC) {
		this.instruccionEmbarqueAgenteAduanalC = instruccionEmbarqueAgenteAduanalC;
	}
	public String getInstruccionAditivacionProducto() {
		return instruccionAditivacionProducto;
	}
	public void setInstruccionAditivacionProducto(String instruccionAditivacionProducto) {
		this.instruccionAditivacionProducto = instruccionAditivacionProducto;
	}
	public String getInstruccionAditivacionProductoC() {
		return instruccionAditivacionProductoC;
	}
	public void setInstruccionAditivacionProductoC(String instruccionAditivacionProductoC) {
		this.instruccionAditivacionProductoC = instruccionAditivacionProductoC;
	}
	public String getConfirmacionBookingMultimodal() {
		return confirmacionBookingMultimodal;
	}
	public void setConfirmacionBookingMultimodal(String confirmacionBookingMultimodal) {
		this.confirmacionBookingMultimodal = confirmacionBookingMultimodal;
	}
	public String getConfirmacionBookingMultimodalC() {
		return confirmacionBookingMultimodalC;
	}
	public void setConfirmacionBookingMultimodalC(String confirmacionBookingMultimodalC) {
		this.confirmacionBookingMultimodalC = confirmacionBookingMultimodalC;
	}
	public String getAceptacionServicioInspeccion() {
		return aceptacionServicioInspeccion;
	}
	public void setAceptacionServicioInspeccion(String aceptacionServicioInspeccion) {
		this.aceptacionServicioInspeccion = aceptacionServicioInspeccion;
	}
	public String getAceptacionServicioInspeccionC() {
		return aceptacionServicioInspeccionC;
	}
	public void setAceptacionServicioInspeccionC(String aceptacionServicioInspeccionC) {
		this.aceptacionServicioInspeccionC = aceptacionServicioInspeccionC;
	}
	public String getCorreoAutorizacionEmbarqueTipoPago() {
		return correoAutorizacionEmbarqueTipoPago;
	}
	public void setCorreoAutorizacionEmbarqueTipoPago(String correoAutorizacionEmbarqueTipoPago) {
		this.correoAutorizacionEmbarqueTipoPago = correoAutorizacionEmbarqueTipoPago;
	}
	public String getCorreoAutorizacionEmbarqueTipoPagoC() {
		return correoAutorizacionEmbarqueTipoPagoC;
	}
	public void setCorreoAutorizacionEmbarqueTipoPagoC(String correoAutorizacionEmbarqueTipoPagoC) {
		this.correoAutorizacionEmbarqueTipoPagoC = correoAutorizacionEmbarqueTipoPagoC;
	}
	public String getFacturaProforma() {
		return facturaProforma;
	}
	public void setFacturaProforma(String facturaProforma) {
		this.facturaProforma = facturaProforma;
	}
	public String getFacturaProformaC() {
		return facturaProformaC;
	}
	public void setFacturaProformaC(String facturaProformaC) {
		this.facturaProformaC = facturaProformaC;
	}
	public String getCertificadoOrigen() {
		return certificadoOrigen;
	}
	public void setCertificadoOrigen(String certificadoOrigen) {
		this.certificadoOrigen = certificadoOrigen;
	}
	public String getCertificadoOrigenC() {
		return certificadoOrigenC;
	}
	public void setCertificadoOrigenC(String certificadoOrigenC) {
		this.certificadoOrigenC = certificadoOrigenC;
	}
	public String getCertificadoOrigenCamaraComercio() {
		return certificadoOrigenCamaraComercio;
	}
	public void setCertificadoOrigenCamaraComercio(String certificadoOrigenCamaraComercio) {
		this.certificadoOrigenCamaraComercio = certificadoOrigenCamaraComercio;
	}
	public String getCertificadoOrigenCamaraComercioC() {
		return certificadoOrigenCamaraComercioC;
	}
	public void setCertificadoOrigenCamaraComercioC(String certificadoOrigenCamaraComercioC) {
		this.certificadoOrigenCamaraComercioC = certificadoOrigenCamaraComercioC;
	}
	public String getAvisoCargaEntrega() {
		return avisoCargaEntrega;
	}
	public void setAvisoCargaEntrega(String avisoCargaEntrega) {
		this.avisoCargaEntrega = avisoCargaEntrega;
	}
	public String getAvisoCargaEntregaC() {
		return avisoCargaEntregaC;
	}
	public void setAvisoCargaEntregaC(String avisoCargaEntregaC) {
		this.avisoCargaEntregaC = avisoCargaEntregaC;
	}
	public String getReporteInspeccionCalidadCantidadCarga() {
		return reporteInspeccionCalidadCantidadCarga;
	}
	public void setReporteInspeccionCalidadCantidadCarga(String reporteInspeccionCalidadCantidadCarga) {
		this.reporteInspeccionCalidadCantidadCarga = reporteInspeccionCalidadCantidadCarga;
	}
	public String getReporteInspeccionCalidadCantidadCargaC() {
		return reporteInspeccionCalidadCantidadCargaC;
	}
	public void setReporteInspeccionCalidadCantidadCargaC(String reporteInspeccionCalidadCantidadCargaC) {
		this.reporteInspeccionCalidadCantidadCargaC = reporteInspeccionCalidadCantidadCargaC;
	}
	public String getReporteInspeccionCalidadCantidadDescarga() {
		return reporteInspeccionCalidadCantidadDescarga;
	}
	public void setReporteInspeccionCalidadCantidadDescarga(String reporteInspeccionCalidadCantidadDescarga) {
		this.reporteInspeccionCalidadCantidadDescarga = reporteInspeccionCalidadCantidadDescarga;
	}
	public String getReporteInspeccionCalidadCantidadDescargaC() {
		return reporteInspeccionCalidadCantidadDescargaC;
	}
	public void setReporteInspeccionCalidadCantidadDescargaC(String reporteInspeccionCalidadCantidadDescargaC) {
		this.reporteInspeccionCalidadCantidadDescargaC = reporteInspeccionCalidadCantidadDescargaC;
	}
	public String getTalonEmbarqueCartaPorte() {
		return talonEmbarqueCartaPorte;
	}
	public void setTalonEmbarqueCartaPorte(String talonEmbarqueCartaPorte) {
		this.talonEmbarqueCartaPorte = talonEmbarqueCartaPorte;
	}
	public String getTalonEmbarqueCartaPorteC() {
		return talonEmbarqueCartaPorteC;
	}
	public void setTalonEmbarqueCartaPorteC(String talonEmbarqueCartaPorteC) {
		this.talonEmbarqueCartaPorteC = talonEmbarqueCartaPorteC;
	}
	public String getTicketBombeoDucto() {
		return ticketBombeoDucto;
	}
	public void setTicketBombeoDucto(String ticketBombeoDucto) {
		this.ticketBombeoDucto = ticketBombeoDucto;
	}
	public String getTicketBombeoDuctoC() {
		return ticketBombeoDuctoC;
	}
	public void setTicketBombeoDuctoC(String ticketBombeoDuctoC) {
		this.ticketBombeoDuctoC = ticketBombeoDuctoC;
	}
	public String getEnvioDocumentosCliente() {
		return envioDocumentosCliente;
	}
	public void setEnvioDocumentosCliente(String envioDocumentosCliente) {
		this.envioDocumentosCliente = envioDocumentosCliente;
	}
	public String getEnvioDocumentosClienteC() {
		return envioDocumentosClienteC;
	}
	public void setEnvioDocumentosClienteC(String envioDocumentosClienteC) {
		this.envioDocumentosClienteC = envioDocumentosClienteC;
	}
	public String getHojaCalculoPrecio() {
		return hojaCalculoPrecio;
	}
	public void setHojaCalculoPrecio(String hojaCalculoPrecio) {
		this.hojaCalculoPrecio = hojaCalculoPrecio;
	}
	public String getHojaCalculoPrecioC() {
		return hojaCalculoPrecioC;
	}
	public void setHojaCalculoPrecioC(String hojaCalculoPrecioC) {
		this.hojaCalculoPrecioC = hojaCalculoPrecioC;
	}
	public String getAjusteVolumenPrecio() {
		return ajusteVolumenPrecio;
	}
	public void setAjusteVolumenPrecio(String ajusteVolumenPrecio) {
		this.ajusteVolumenPrecio = ajusteVolumenPrecio;
	}
	public String getAjusteVolumenPrecioC() {
		return ajusteVolumenPrecioC;
	}
	public void setAjusteVolumenPrecioC(String ajusteVolumenPrecioC) {
		this.ajusteVolumenPrecioC = ajusteVolumenPrecioC;
	}
	public String getCartaProtesta() {
		return cartaProtesta;
	}
	public void setCartaProtesta(String cartaProtesta) {
		this.cartaProtesta = cartaProtesta;
	}
	public String getCartaProtestaC() {
		return cartaProtestaC;
	}
	public void setCartaProtestaC(String cartaProtestaC) {
		this.cartaProtestaC = cartaProtestaC;
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
	public String getNumeroFolios() {
		return numeroFolios;
	}
	public void setNumeroFolios(String numeroFolios) {
		this.numeroFolios = numeroFolios;
	}
	public String getIntegroNombre() {
		return integroNombre;
	}
	public void setIntegroNombre(String integroNombre) {
		this.integroNombre = integroNombre;
	}
	public String getOperadorNombre() {
		return operadorNombre;
	}
	public void setOperadorNombre(String operadorNombre) {
		this.operadorNombre = operadorNombre;
	}


}

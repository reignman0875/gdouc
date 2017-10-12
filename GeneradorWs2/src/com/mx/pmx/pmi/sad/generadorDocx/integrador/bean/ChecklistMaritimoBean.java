package com.mx.pmx.pmi.sad.generadorDocx.integrador.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ChecklistMaritimo")
public class ChecklistMaritimoBean extends PadreBean {

	public ChecklistMaritimoBean() {
		crudo=productos=fecha=ordenPmi=ordenesRelacionadas=allocation=nombreBarco=ordenFletamento=aceptacionBuqueTanque
				=aceptacionBuqueTanqueC=autorizacionEmbarqueTipoPago=autorizacionEmbarqueTipoPagoC=instruccionDocumentacion
				=instruccionDocumentacionC=nominacionInspeccionCarga=nominacionInspeccionCargaC=instruccionCargamento
				=instruccionCargamentoC=instruccionesViaje=instruccionesViajeC=instruccionAditivacionProducto
				=instruccionAditivacionProductoC=instruccionEmbarqueAgenteAduanal=instruccionEmbarqueAgenteAduanalC
				=aceptacionServicioInspeccion=aceptacionServicioInspeccionC=etasEmbarcacion=etasEmbarcacionC=avisoListoPuertoCarga
				=avisoListoPuertoCargaC=reporteInspeccionPuertoCarga=reporteInspeccionPuertoCargaC=avisoImportacionPemex
				=avisoImportacionPemexC=nominacionInspeccionDescarga=nominacionInspeccionDescargaC=manifiestoCarga=manifiestoCargaC
				=conocimientoEmbarque=conocimientoEmbarqueC=cartaIndemnidad=cartaIndemnidadC=certificadoOrigen=certificadoOrigenC
				=certificadoOrigenCamaraComercio=certificadoOrigenCamaraComercioC=estadoHechosPuertoCarga=estadoHechosPuertoCargaC
				=envioDocumentosCliente=envioDocumentosClienteC=facturaProforma=facturaProformaC=reporteInspeccionPuertoDescarga
				=reporteInspeccionPuertoDescargaC=estadoHechosPuertoDescarga=estadoHechosPuertoDescargaC=acuseReciboCapitan
				=acuseReciboCapitanC=avisoListoPuertoDescarga=avisoListoPuertoDescargaC=cartaProtesta=cartaProtestaC=hojasCalculoPrecio
				=hojasCalculoPrecioC=confirmacionVolumenPrecioCompraVenta=confirmacionVolumenPrecioCompraVentaC=modificacionesPrograma
				=modificacionesProgramaC=otrosEspecificar=otrosEspecificar2=numeroHojas=numeroPaginas=numeroFolios=integroNombre
				=operadorNombre = new String();
	}
	private String crudo;
	private String productos;
	private String fecha;
	private String ordenPmi;
	private String ordenesRelacionadas;
	private String allocation;
	private String nombreBarco;
	private String ordenFletamento;
	private String aceptacionBuqueTanque;
	private String aceptacionBuqueTanqueC;
	private String autorizacionEmbarqueTipoPago;
	private String autorizacionEmbarqueTipoPagoC;
	private String instruccionDocumentacion;
	private String instruccionDocumentacionC;
	private String nominacionInspeccionCarga;
	private String nominacionInspeccionCargaC;
	private String instruccionCargamento;
	private String instruccionCargamentoC;
	private String instruccionesViaje;
	private String instruccionesViajeC;
	private String instruccionAditivacionProducto;
	private String instruccionAditivacionProductoC;
	private String instruccionEmbarqueAgenteAduanal;
	private String instruccionEmbarqueAgenteAduanalC;
	private String aceptacionServicioInspeccion;
	private String aceptacionServicioInspeccionC;
	private String etasEmbarcacion;
	private String etasEmbarcacionC;
	private String avisoListoPuertoCarga;
	private String avisoListoPuertoCargaC;
	private String reporteInspeccionPuertoCarga;
	private String reporteInspeccionPuertoCargaC;
	private String avisoImportacionPemex;
	private String avisoImportacionPemexC;
	private String nominacionInspeccionDescarga;
	private String nominacionInspeccionDescargaC;
	private String manifiestoCarga;
	private String manifiestoCargaC;
	private String conocimientoEmbarque;
	private String conocimientoEmbarqueC;
	private String cartaIndemnidad;
	private String cartaIndemnidadC;
	private String certificadoOrigen;
	private String certificadoOrigenC;
	private String certificadoOrigenCamaraComercio;
	private String certificadoOrigenCamaraComercioC;
	private String estadoHechosPuertoCarga;
	private String estadoHechosPuertoCargaC;
	private String envioDocumentosCliente;
	private String envioDocumentosClienteC;
	private String facturaProforma;
	private String facturaProformaC;
	private String reporteInspeccionPuertoDescarga;
	private String reporteInspeccionPuertoDescargaC;
	private String estadoHechosPuertoDescarga;
	private String estadoHechosPuertoDescargaC;
	private String acuseReciboCapitan;
	private String acuseReciboCapitanC;
	private String avisoListoPuertoDescarga;
	private String avisoListoPuertoDescargaC;
	private String cartaProtesta;
	private String cartaProtestaC;
	private String hojasCalculoPrecio;
	private String hojasCalculoPrecioC;
	private String confirmacionVolumenPrecioCompraVenta;
	private String confirmacionVolumenPrecioCompraVentaC;
	private String modificacionesPrograma;
	private String modificacionesProgramaC;
	private String otrosEspecificar;
	private String otrosEspecificar2;
	private String numeroHojas;
	private String numeroPaginas;
	private String numeroFolios;
	private String integroNombre;
	private String operadorNombre;
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
	public String getAllocation() {
		return allocation;
	}
	public void setAllocation(String allocation) {
		this.allocation = allocation;
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
	public String getAceptacionBuqueTanque() {
		return aceptacionBuqueTanque;
	}
	public void setAceptacionBuqueTanque(String aceptacionBuqueTanque) {
		this.aceptacionBuqueTanque = aceptacionBuqueTanque;
	}
	public String getAceptacionBuqueTanqueC() {
		return aceptacionBuqueTanqueC;
	}
	public void setAceptacionBuqueTanqueC(String aceptacionBuqueTanqueC) {
		this.aceptacionBuqueTanqueC = aceptacionBuqueTanqueC;
	}
	public String getAutorizacionEmbarqueTipoPago() {
		return autorizacionEmbarqueTipoPago;
	}
	public void setAutorizacionEmbarqueTipoPago(String autorizacionEmbarqueTipoPago) {
		this.autorizacionEmbarqueTipoPago = autorizacionEmbarqueTipoPago;
	}
	public String getAutorizacionEmbarqueTipoPagoC() {
		return autorizacionEmbarqueTipoPagoC;
	}
	public void setAutorizacionEmbarqueTipoPagoC(String autorizacionEmbarqueTipoPagoC) {
		this.autorizacionEmbarqueTipoPagoC = autorizacionEmbarqueTipoPagoC;
	}
	public String getInstruccionDocumentacion() {
		return instruccionDocumentacion;
	}
	public void setInstruccionDocumentacion(String instruccionDocumentacion) {
		this.instruccionDocumentacion = instruccionDocumentacion;
	}
	public String getInstruccionDocumentacionC() {
		return instruccionDocumentacionC;
	}
	public void setInstruccionDocumentacionC(String instruccionDocumentacionC) {
		this.instruccionDocumentacionC = instruccionDocumentacionC;
	}
	public String getNominacionInspeccionCarga() {
		return nominacionInspeccionCarga;
	}
	public void setNominacionInspeccionCarga(String nominacionInspeccionCarga) {
		this.nominacionInspeccionCarga = nominacionInspeccionCarga;
	}
	public String getNominacionInspeccionCargaC() {
		return nominacionInspeccionCargaC;
	}
	public void setNominacionInspeccionCargaC(String nominacionInspeccionCargaC) {
		this.nominacionInspeccionCargaC = nominacionInspeccionCargaC;
	}
	public String getInstruccionCargamento() {
		return instruccionCargamento;
	}
	public void setInstruccionCargamento(String instruccionCargamento) {
		this.instruccionCargamento = instruccionCargamento;
	}
	public String getInstruccionCargamentoC() {
		return instruccionCargamentoC;
	}
	public void setInstruccionCargamentoC(String instruccionCargamentoC) {
		this.instruccionCargamentoC = instruccionCargamentoC;
	}
	public String getInstruccionesViaje() {
		return instruccionesViaje;
	}
	public void setInstruccionesViaje(String instruccionesViaje) {
		this.instruccionesViaje = instruccionesViaje;
	}
	public String getInstruccionesViajeC() {
		return instruccionesViajeC;
	}
	public void setInstruccionesViajeC(String instruccionesViajeC) {
		this.instruccionesViajeC = instruccionesViajeC;
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
	public String getEtasEmbarcacion() {
		return etasEmbarcacion;
	}
	public void setEtasEmbarcacion(String etasEmbarcacion) {
		this.etasEmbarcacion = etasEmbarcacion;
	}
	public String getEtasEmbarcacionC() {
		return etasEmbarcacionC;
	}
	public void setEtasEmbarcacionC(String etasEmbarcacionC) {
		this.etasEmbarcacionC = etasEmbarcacionC;
	}
	public String getAvisoListoPuertoCarga() {
		return avisoListoPuertoCarga;
	}
	public void setAvisoListoPuertoCarga(String avisoListoPuertoCarga) {
		this.avisoListoPuertoCarga = avisoListoPuertoCarga;
	}
	public String getAvisoListoPuertoCargaC() {
		return avisoListoPuertoCargaC;
	}
	public void setAvisoListoPuertoCargaC(String avisoListoPuertoCargaC) {
		this.avisoListoPuertoCargaC = avisoListoPuertoCargaC;
	}
	public String getReporteInspeccionPuertoCarga() {
		return reporteInspeccionPuertoCarga;
	}
	public void setReporteInspeccionPuertoCarga(String reporteInspeccionPuertoCarga) {
		this.reporteInspeccionPuertoCarga = reporteInspeccionPuertoCarga;
	}
	public String getReporteInspeccionPuertoCargaC() {
		return reporteInspeccionPuertoCargaC;
	}
	public void setReporteInspeccionPuertoCargaC(String reporteInspeccionPuertoCargaC) {
		this.reporteInspeccionPuertoCargaC = reporteInspeccionPuertoCargaC;
	}
	public String getAvisoImportacionPemex() {
		return avisoImportacionPemex;
	}
	public void setAvisoImportacionPemex(String avisoImportacionPemex) {
		this.avisoImportacionPemex = avisoImportacionPemex;
	}
	public String getAvisoImportacionPemexC() {
		return avisoImportacionPemexC;
	}
	public void setAvisoImportacionPemexC(String avisoImportacionPemexC) {
		this.avisoImportacionPemexC = avisoImportacionPemexC;
	}
	public String getNominacionInspeccionDescarga() {
		return nominacionInspeccionDescarga;
	}
	public void setNominacionInspeccionDescarga(String nominacionInspeccionDescarga) {
		this.nominacionInspeccionDescarga = nominacionInspeccionDescarga;
	}
	public String getNominacionInspeccionDescargaC() {
		return nominacionInspeccionDescargaC;
	}
	public void setNominacionInspeccionDescargaC(String nominacionInspeccionDescargaC) {
		this.nominacionInspeccionDescargaC = nominacionInspeccionDescargaC;
	}
	public String getManifiestoCarga() {
		return manifiestoCarga;
	}
	public void setManifiestoCarga(String manifiestoCarga) {
		this.manifiestoCarga = manifiestoCarga;
	}
	public String getManifiestoCargaC() {
		return manifiestoCargaC;
	}
	public void setManifiestoCargaC(String manifiestoCargaC) {
		this.manifiestoCargaC = manifiestoCargaC;
	}
	public String getConocimientoEmbarque() {
		return conocimientoEmbarque;
	}
	public void setConocimientoEmbarque(String conocimientoEmbarque) {
		this.conocimientoEmbarque = conocimientoEmbarque;
	}
	public String getConocimientoEmbarqueC() {
		return conocimientoEmbarqueC;
	}
	public void setConocimientoEmbarqueC(String conocimientoEmbarqueC) {
		this.conocimientoEmbarqueC = conocimientoEmbarqueC;
	}
	public String getCartaIndemnidad() {
		return cartaIndemnidad;
	}
	public void setCartaIndemnidad(String cartaIndemnidad) {
		this.cartaIndemnidad = cartaIndemnidad;
	}
	public String getCartaIndemnidadC() {
		return cartaIndemnidadC;
	}
	public void setCartaIndemnidadC(String cartaIndemnidadC) {
		this.cartaIndemnidadC = cartaIndemnidadC;
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
	public String getEstadoHechosPuertoCarga() {
		return estadoHechosPuertoCarga;
	}
	public void setEstadoHechosPuertoCarga(String estadoHechosPuertoCarga) {
		this.estadoHechosPuertoCarga = estadoHechosPuertoCarga;
	}
	public String getEstadoHechosPuertoCargaC() {
		return estadoHechosPuertoCargaC;
	}
	public void setEstadoHechosPuertoCargaC(String estadoHechosPuertoCargaC) {
		this.estadoHechosPuertoCargaC = estadoHechosPuertoCargaC;
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
	public String getReporteInspeccionPuertoDescarga() {
		return reporteInspeccionPuertoDescarga;
	}
	public void setReporteInspeccionPuertoDescarga(String reporteInspeccionPuertoDescarga) {
		this.reporteInspeccionPuertoDescarga = reporteInspeccionPuertoDescarga;
	}
	public String getReporteInspeccionPuertoDescargaC() {
		return reporteInspeccionPuertoDescargaC;
	}
	public void setReporteInspeccionPuertoDescargaC(String reporteInspeccionPuertoDescargaC) {
		this.reporteInspeccionPuertoDescargaC = reporteInspeccionPuertoDescargaC;
	}
	public String getEstadoHechosPuertoDescarga() {
		return estadoHechosPuertoDescarga;
	}
	public void setEstadoHechosPuertoDescarga(String estadoHechosPuertoDescarga) {
		this.estadoHechosPuertoDescarga = estadoHechosPuertoDescarga;
	}
	public String getEstadoHechosPuertoDescargaC() {
		return estadoHechosPuertoDescargaC;
	}
	public void setEstadoHechosPuertoDescargaC(String estadoHechosPuertoDescargaC) {
		this.estadoHechosPuertoDescargaC = estadoHechosPuertoDescargaC;
	}
	public String getAcuseReciboCapitan() {
		return acuseReciboCapitan;
	}
	public void setAcuseReciboCapitan(String acuseReciboCapitan) {
		this.acuseReciboCapitan = acuseReciboCapitan;
	}
	public String getAcuseReciboCapitanC() {
		return acuseReciboCapitanC;
	}
	public void setAcuseReciboCapitanC(String acuseReciboCapitanC) {
		this.acuseReciboCapitanC = acuseReciboCapitanC;
	}
	public String getAvisoListoPuertoDescarga() {
		return avisoListoPuertoDescarga;
	}
	public void setAvisoListoPuertoDescarga(String avisoListoPuertoDescarga) {
		this.avisoListoPuertoDescarga = avisoListoPuertoDescarga;
	}
	public String getAvisoListoPuertoDescargaC() {
		return avisoListoPuertoDescargaC;
	}
	public void setAvisoListoPuertoDescargaC(String avisoListoPuertoDescargaC) {
		this.avisoListoPuertoDescargaC = avisoListoPuertoDescargaC;
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
	public String getHojasCalculoPrecio() {
		return hojasCalculoPrecio;
	}
	public void setHojasCalculoPrecio(String hojasCalculoPrecio) {
		this.hojasCalculoPrecio = hojasCalculoPrecio;
	}
	public String getHojasCalculoPrecioC() {
		return hojasCalculoPrecioC;
	}
	public void setHojasCalculoPrecioC(String hojasCalculoPrecioC) {
		this.hojasCalculoPrecioC = hojasCalculoPrecioC;
	}
	public String getConfirmacionVolumenPrecioCompraVenta() {
		return confirmacionVolumenPrecioCompraVenta;
	}
	public void setConfirmacionVolumenPrecioCompraVenta(String confirmacionVolumenPrecioCompraVenta) {
		this.confirmacionVolumenPrecioCompraVenta = confirmacionVolumenPrecioCompraVenta;
	}
	public String getConfirmacionVolumenPrecioCompraVentaC() {
		return confirmacionVolumenPrecioCompraVentaC;
	}
	public void setConfirmacionVolumenPrecioCompraVentaC(String confirmacionVolumenPrecioCompraVentaC) {
		this.confirmacionVolumenPrecioCompraVentaC = confirmacionVolumenPrecioCompraVentaC;
	}
	public String getModificacionesPrograma() {
		return modificacionesPrograma;
	}
	public void setModificacionesPrograma(String modificacionesPrograma) {
		this.modificacionesPrograma = modificacionesPrograma;
	}
	public String getModificacionesProgramaC() {
		return modificacionesProgramaC;
	}
	public void setModificacionesProgramaC(String modificacionesProgramaC) {
		this.modificacionesProgramaC = modificacionesProgramaC;
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

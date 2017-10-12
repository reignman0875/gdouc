package com.mx.pmx.pmi.sad.generadorDocx.core.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.SystemOutLogger;

import com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocumentoDto;
import com.mx.pmx.pmi.sad.generadorDocx.core.bean.DocxDocumentDto;
import com.mx.pmx.pmi.sad.generadorDocx.core.exception.BusinessException;
import com.mx.pmx.pmi.sad.generadorDocx.core.service.GestionarDocumentoService;
import com.mx.pmx.pmi.sad.generadorDocx.core.service.GestionarPlantilla;

public class GestionarPlantillaImpl implements GestionarPlantilla {
	

	private static final Log log = LogFactory.getLog(TransformarDocumentoServiceImpl.class);
	private volatile static Long ultimaComprovacion; 
	
	private ArrayBlockingQueue<String>       idPlantillas;
	private HashMap<String, PoolDto>         documentoDtoMP;
	private GestionarDocumentoService        gestionarDocumentoService = null;
	
	
	private static final String ERROR_POOL_RECUPERAR_PLANTILLA = "ERROR_POOL_RECUPERAR_PLANTILLA";
	
	private static final String ERROR_POOL_RECUPERAR_INSERTAR_PLANTILLA = "ERROR_POOL_RECUPERAR_INSERTAR_PLANTILLA";
	
	private static final String ERROR_POOL_RECUPERAR_PLANTILLA_DOCUMENTUM = "ERROR_POOL_RECUPERAR_PLANTILLA_DOCUMENTUM";

	private Integer                          capacity = 1;
	private Long                             periodoRevision = 1L;
	private Long                             periodoPermanencia = 1L;
	
	public GestionarPlantillaImpl() {
		// IOMH constructor
		//TODO: Cambio a versión 6
		idPlantillas = new ArrayBlockingQueue(1);
		//TODO: Cambio a versión 6
		documentoDtoMP = new HashMap();
		gestionarDocumentoService = new GestionarDocumentoServiceImpl();
	}
	/**
	 * La función basica de este metodo es obtener un plantilla, pero dentro de este metodo se incluye la logica para revisar que el FIFO y el mapa no esten vacios
	 * y que la periocidad de las plantillas que se tienen en el pool, por lo que cuenta con logica para validar si el tiempo de revision ya paso y es necesario hacer
	 * la revisión de las plantillas guardadas.
	 * 
	 * Otra funcionalidad inmersa es la validación de las plantillas contenidas dentro del pool, de esta manera se busca evitar que exista un desface entre las plantillas
	 * contenidas en CS de Documentum y las plantillas cargadas en el POOL de plantillas.
	 * 
	 * Por ultimo esta la funcionalidad de recuperar la plantilla, donde encontraremos que si la plantilla ya esta en el pool la regresa pero sino esta va y la recupera 
	 * del CS de documentum y si pasa esto la carga en el pool, ademas de manejar una logica sencilla de prioridad, la cual consiste en que la ultima plantilla que es 
	 * requerida se mueve al final de la fila y en el caso de no existir mas lugar se borra a la primera de la cola.
	 * 
	 * Cabe señalar que el pool no esta cargando las plantillas en memoria, sino que cuando recupera cualquiera del CS de Documentum hace una copia en los archivos 
	 * temporales y la siguiente vez que es requerida evita el viaje al CS y la carga de la carpeta temporal.
	 * 
	 * @author ortegaos 
	 * @param idPlantilla
	 * 
	 */
	public DocxDocumentDto obtenerPlantilla(String idPlantilla) {
		
			/*IOMH codigo para obtener la plantilla */
			long t1 = System.currentTimeMillis();
			log.info("La plantilla que se busca se va a recuperar y a cargar en memoria"+t1);
			InputStream is = null;
			InputStream inputStream = null;
			DocxDocumentDto docxDocumentDtoPaso = null;
			try{				
				
				DocumentoDto documentoDto = gestionarDocumentoService.recuperarDocumento(idPlantilla,"");
				if((documentoDto == null) || (documentoDto.getUrlDocumento() == null)){
					throw new BusinessException(ERROR_POOL_RECUPERAR_PLANTILLA_DOCUMENTUM); 
				}
				System.out.println("Impresion del ACS:"+documentoDto.getUrlDocumento());
				is = new URL(documentoDto.getUrlDocumento()).openStream();
				
				File temp = File.createTempFile("Plantilla", "docx");
				DocxUtils.copiarDocx(is, temp);
				inputStream = new FileInputStream(temp);	
				
				docxDocumentDtoPaso = DocxUtils.readDocument(inputStream);
				docxDocumentDtoPaso.setNombreDocumento(documentoDto.getNombreDocumento());
				
				PoolDto poolDto = new PoolDto();
				poolDto.setIdPlantilla(idPlantilla);
				poolDto.setRutaTemporal(temp.getPath());
				poolDto.setFechaCreacion(new Date().getTime());
				poolDto.setNombreDocumento(documentoDto.getNombreDocumento());
				documentoDtoMP.put(idPlantilla, poolDto);
				if(idPlantillas.size()==capacity){
					String idPlantillasPaso = idPlantillas.poll();
					PoolDto poolDtoEliminar = documentoDtoMP.get(idPlantillasPaso); 
					documentoDtoMP.remove(idPlantillasPaso);
					File fileElimina = new File(poolDtoEliminar.getRutaTemporal());
					if(fileElimina.exists()&&fileElimina.isFile()){						
						if (!fileElimina.delete())
						{
							log.error("No se pudo eliminar el archivo: " + fileElimina.getAbsolutePath());
						}
					}else{
						log.error("Error al intenar borrar alguna plantilla en la carpeta temporal");
						throw new  BusinessException(ERROR_POOL_RECUPERAR_PLANTILLA, "Error al intenar borrar alguna plantilla en la carpeta temporal");
					}
					long t2 = System.currentTimeMillis() -t1;
					log.info("La plantilla que se busca ya fue previamente cargada y esta en memoria fin "+t2);
				}
				if(idPlantillas.offer(idPlantilla)){
					log.info("idPlantilla cargada en memoria.");
				}else{
					throw new  BusinessException(ERROR_POOL_RECUPERAR_INSERTAR_PLANTILLA, "No se pudo insertar la plantilla");
				}
				
				
				
			}catch(IOException exception){
				log.error("Error al intenar recuperar alguna plantilla ", exception);
				throw new  BusinessException(ERROR_POOL_RECUPERAR_PLANTILLA, exception.getMessage());
			}finally{
				try{
					if (is != null) {
						is.close();
					}
					if (inputStream != null) {
						inputStream.close();
					}
				}
				catch(IOException ioException){
					log.error("Error cerrando stream.", ioException);
				}
				
			}
			return docxDocumentDtoPaso;
		
	}

	/**
	 * Borra tanto el FIFO como el mapa que contienen las plantillas 
	 * 
	 * @author ortegaos
	 * 
	 */
	public void clear() {
		log.info("Se va a limpiar tanto el manejador como el contenedor de plantillas");
		idPlantillas   = null;
		documentoDtoMP = null;
	}

	/**
	 * Establece la capacidad a manejar en el pool de plantillas
	 * @author ortegaos
	 * 
	 */
	public void setCapacity(Integer capacity) {
		log.info("Se va a establecer la capacidad del pool de plantillas");
		this.capacity = capacity;
	}

	/**
	 * Obtiene el gestionador de documentos que se este usando cual se recupera las plantillas del CS de Documentum
	 * @author Osvaldo Ortega Martínez <osvaldo.ortega.martinez@hp.com>
	 * @return
	 */
	public GestionarDocumentoService getGestionarDocumentoService() {
		return gestionarDocumentoService;
	}
	
	/**
	 * Establece el gestionador de documentos con el cual se recupera las plantillas del CS de Documentum
	 * @author Osvaldo Ortega Martínez <osvaldo.ortega.martinez@hp.com>
	 * @return
	 */
	public void setGestionarDocumentoService(GestionarDocumentoService gestionarDocumentoService) {
		this.gestionarDocumentoService = gestionarDocumentoService;
	}
	
	/**
	 * Establece el periodo en cual se hara la validación del pool de plantillas
	 * @param periodoRevision
	 */
	public void setPeriodoRevision(Long periodoRevision) {
		this.periodoRevision = periodoRevision;
	}

	/**
	 * Establece el periodo maximo que puede permanecer un documento dentro del pool
	 * @param periodoPermanencia
	 */
	public void setPeriodoPermanencia(Long periodoPermanencia) {
		this.periodoPermanencia = periodoPermanencia;
	}
   
	/**
	 * Clase interna que tiene los atributos necesarios para el manejo de las plantillas dentro del pool
	 * idPlantilla es el identificador unico con el cual se identifica una plantilla
	 * rutaTemporal ruta donde se guarda la plantilla de manera temporal
	 * fechaCreacion es la fecha en milisengundos que nos indica que día ingreso la plantilla al pool 
	 * @author Osvaldo Ortega Martínez <osvaldo.ortega.martinez@hp.com>
	 *
	 *
	 */
	private static class PoolDto{
		private String idPlantilla;
		private String rutaTemporal;
		private long   fechaCreacion;
		
		private String nombreDocumento;
		
		public long getFechaCreacion() {
			return fechaCreacion;
		}
		public void setFechaCreacion(long fechaCreacion) {
			this.fechaCreacion = fechaCreacion;
		}
		public void setIdPlantilla(String idPlantilla) {
			this.idPlantilla = idPlantilla;
		}
		public String getRutaTemporal() {
			return rutaTemporal;
		}
		public void setRutaTemporal(String rutaTemporal) {
			this.rutaTemporal = rutaTemporal;
		}
		
		public void setNombreDocumento(String nombreDocumento){
			this.nombreDocumento = nombreDocumento;
		}
		
		public String getNombreDocumento(){
			return nombreDocumento;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
			    return true;
			}
			if (obj instanceof PoolDto) {
				PoolDto comparar = (PoolDto) obj;				
				return this.idPlantilla.equals(comparar.idPlantilla);
			}else{
				return false;
			}
		}
		@Override
		public int hashCode() {			
			return this.idPlantilla.hashCode();
		}
	}


}

package com.mx.pmx.pmi.sad.generadorDocx.core.exception;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConvertirBeanComplejo {

	
	private static final Log log = LogFactory
			.getLog(ConvertirBeanComplejo.class);
	private static final String PREFIJO_ARRAY_OF = "ArrayOf";
	private static final String PAQUETE_CONVERTER_MODELOCOMUN = "mx.gob.sat.stii.util.convertidores.";
	private static final String SUFIJO_CONVERTER = "Converter";

	/**
	 * Metodo para convertir un objeto de tipo ArrayOf.. generado por Axis a un objeto de tipo Collection, utilizando
	 * el mapa mapaClases de configuración, el cual indica al proceso los tipos destino de acuerdo al tipo origen.
	 * @param mapaClases mapa de configuracion para efectuar la conversión, la llave corresponde a la clase origen
	 *                   en forma de String (class.getName()) y el campo valor corresponde al tipo destino. 
	 * @param arrayAxis  Clase destino de tipo ArrayOf... que debera llenarse con los elementos convertidos de lista.
	 * @param lista      Coleccion origen.
	 * @return el objeto tipo ArrayOf.. llena con los elementos convertidos.
	 */
	public static Object convertirListaArray(Map<String, Class<?>> mapaClases,
			Class<?> arrayAxis, Collection<?> lista) {
		// log.info("arrayAxis =" + arrayAxis);
		// log.info("lista =" + lista);

		try {
			if (lista != null && !lista.isEmpty()) {
				Method setterAxis = obtenerMetodoAxis(arrayAxis, "set");
				// log.info("setterAxis =" + setterAxis);
				StringBuffer tipoObjetos = new StringBuffer(setterAxis
						.getParameterTypes()[0].getName().substring(
						setterAxis.getParameterTypes()[0].getName()
								.indexOf("L") + 1));
				tipoObjetos.replace(tipoObjetos.indexOf(";"), tipoObjetos
						.indexOf(";") + 1, "");
				// log.info("Tipo objetos [" + tipoObjetos + "]");
				Object arrayAxisType = arrayAxis.newInstance();
				Object arregloAxis = Array.newInstance(Class
						.forName(tipoObjetos.toString()), lista.size());
				int i = 0;
				for (Object objLista : lista) {
					Object objetoAxis = Class.forName(tipoObjetos.toString())
							.newInstance();
					copiarBean(mapaClases, objLista, objetoAxis);
					Array.set(arregloAxis, i, objetoAxis);
					i++;
				}
				setterAxis.invoke(arrayAxisType, arregloAxis);
				return arrayAxisType;
			}
		} catch (Exception e) {
			throw new ConversionException(e);
		}
		return null;
	}

	/**
	 * Metodo para convertir un objeto de tipo ArrayOf.. generado por Axis a un objeto de tipo Collection, utilizando
	 * el mapa <b>mapaClases</b> de configuración, el cual indica al proceso los tipos destino de acuerdo al tipo origen.
	 * @param mapaClases mapa de configuracion para efectuar la conversión, la llave corresponde a la clase origen
	 *                   en forma de String (class.getName()) y el campo valor corresponde al tipo destino,
	 *                   ejemplo: mapaClases.put(FechaString.class.getName(), Date.class); 
	 * @param lista   lista destino
	 * @param arrayAxis - objeto origen de tipo ArrayOf... que debera ser convertido a una coleccion.
	 * @param claseDestino - tipo destino de los elementos contenidos en arrayAxis 
	 * @return la lista llena con los objetos convertidos.
	 */
	public static Collection<Object> convertirArrayLista(
			Map<String, Class<?>> mapaClases, Collection<Object> lista,
			Object arrayAxis, Class<?> claseDestino) {
		try {
			if (arrayAxis != null) {
				Method getterAxis = obtenerMetodoAxis(arrayAxis.getClass(),
						"get");
				// log.info("claseDestino ="+claseDestino);
				if (getterAxis != null) {
					// log.info(getterAxis.getName());
					Object arreglo = getterAxis
							.invoke(arrayAxis, new Object[0]);
					if (arreglo.getClass().isArray()) {
						int tamanioArreglo = Array.getLength(arreglo);
						// log.info("TamañoArreglo *[" + tamanioArreglo + "]");
						for (int i = 0; i < tamanioArreglo; i++) {
							Object elementoDestino = claseDestino.newInstance();
							// log.info("origen = "+Array.get(arreglo,
							// i)+" destino ="+ elementoDestino);
							copiarBean(mapaClases, Array.get(arreglo, i),
									elementoDestino);
							lista.add(elementoDestino);
						}
					}
				}
				return lista;
			}
		} catch (Exception e) {
			throw new ConversionException(e);
		}
		return null;
	}

	/**
	 * Copia y convierte los valores de los atributos comunes del objeto origen al objeto destino utilizando el mapa <b>mapaClases</b> de configuracion.
	 * Los atributos deberan tener el mismo nombre y pueden ser objetos complejos(Dto's, ADB's, etc.) u objetos simples: Integer, String, etc.
	 * @param mapaClases mapa de configuracion para efectuar la conversión, la llave corresponde a la clase origen
	 *                   en forma de String (class.getName()) y el campo valor corresponde al tipo destino,
	 *                   ejemplo: mapaClases.put(EntidadADB.class.getName(), EntidadDto.class);
	 *                    
	 * @param origen    objeto fuente
	 * @param destino   objeto al cual se le asignaran los valores.
	 */
	public static void copiarBean(Map<String, Class<?>> mapaClases,
			Object origen, Object destino) {
		Method[] metodos = origen.getClass().getMethods();

		// log.info("mapa =" + mapaClases);
		// log.info("origen =" + origen);
		// log.info("destino =" + destino);

		// for (int i = 0; i < campos.length; i++) {
		// log.info("campo ="+campos[i]);
		for (int j = 0; j < metodos.length; j++) {
			if (metodos[j].getName().startsWith("get")
			// && metodos[j].getName().toUpperCase().endsWith(
					// campos[i].getName().toUpperCase())
					&& !Modifier.isNative(metodos[j].getModifiers())) {
				// log.info("metodo :" + metodos[j]);
				try {
					String nombrePropiedad = metodos[j].getName().substring(3);
					// log.info("nombrePropiedad=" + nombrePropiedad);
					Method metodoDestino = null;
					try {
						metodoDestino = destino.getClass().getMethod(
								metodos[j].getName());
					} catch (NoSuchMethodException e) {
						// log.info("No existe metodo "+metodos[j].getName()+
						// " en clase destino "+destino.getClass());
					}

					if (metodoDestino != null) {
						// log.info("metodoDestino=" + metodoDestino);
						Object atributoOrigen = metodos[j].invoke(origen);
						// log.info("atributoOrigen=" + atributoOrigen);
						if (atributoOrigen != null) {
							if (atributoOrigen instanceof Collection<?>) {
								// log.info("collection ");
								Collection<Object> coleccionOrigen = ((Collection<Object>) atributoOrigen);
								// Se obtiene el tipo del contenedor
								String nombreMetodoOrigen = origen.getClass()
										.getName()
										+ "." + metodos[j].getName();
								// log.info("nombreMetodoOrigen = "+
								// nombreMetodoOrigen);
								Class<?> claseContenedorDestino = (Class<?>) mapaClases
										.get(nombreMetodoOrigen);

								// log.info("claseContenedorDestino = " +
								// claseContenedorDestino);
								if (claseContenedorDestino != null) {
									Object contenedorDestino = claseContenedorDestino
											.newInstance();

									// log.info("contenedorDestino = " +
									// contenedorDestino);
									// De Collection a Collection
									if (contenedorDestino instanceof Collection<?>) {
										// log.info("De Collection a Collection");

										if (coleccionOrigen.size() > 0) {
											Object primerElementoOrigen = coleccionOrigen
													.iterator().next();
											Class<?> claseElementoDestino = (Class<?>) mapaClases
													.get(primerElementoOrigen
															.getClass()
															.getName());
											copiarLista(
													mapaClases,
													coleccionOrigen,
													(Collection<Object>) contenedorDestino,
													claseElementoDestino);
											asignarPropiedad(destino,
													nombrePropiedad,
													contenedorDestino);

										}

									} else {
										// log.info("de Collection a ArrayOf");
										// de Collection a ArrayOf
										if (esArrayOf(contenedorDestino)) {
											// log.info("de Collection a ArrayOf");
											contenedorDestino = convertirListaArray(
													mapaClases,
													claseContenedorDestino,
													coleccionOrigen);
											asignarPropiedad(destino,
													nombrePropiedad,
													contenedorDestino);
											// BeanUtils.copyProperty(destino,
											// nombrePropiedad,
											// contenedorDestino);
										}

									}
								}

							} else {

								if (esArrayOf(atributoOrigen)) {
									// log.info("ArrayOf");
									// Se obtiene el tipo del contenedor
									String nombreMetodoOrigen = origen
											.getClass().getName()
											+ "." + metodos[j].getName();
									// log.info("nombreMetodoOrigen = "+
									// nombreMetodoOrigen);
									Class<?> claseContenedorDestino = (Class<?>) mapaClases
											.get(nombreMetodoOrigen);
									// log.info("claseContenedorDestino = "+
									// claseContenedorDestino);
									if (claseContenedorDestino != null) {
										Object contenedorDestino = claseContenedorDestino
												.newInstance();

										// De ArrayOf a Collection
										if (contenedorDestino instanceof Collection<?>) {
											// Se obtiene el tipo del contenedor
											String nombreClaseTipoArray = obtenerNombreClaseTipoArray(atributoOrigen
													.getClass());
											// log.info("nombreClaseTipoArray "+nombreClaseTipoArray);
											Class<?> claseElementoDestino = (Class<?>) mapaClases
													.get(nombreClaseTipoArray);
											if (claseElementoDestino != null) {
												convertirArrayLista(
														mapaClases,
														(Collection) contenedorDestino,
														atributoOrigen,
														claseElementoDestino);
												asignarPropiedad(destino,
														nombrePropiedad,
														contenedorDestino);
											} else {
												log
														.fatal("No existe configuracion para elementoDestino "
																+ nombreClaseTipoArray);

											}
											// BeanUtils.copyProperty(destino,
											// nombrePropiedad,
											// contenedorDestino);

										} else {
											// de ArrayOf a ArrayOf
											if (esArrayOf(contenedorDestino)) {
												// TODO por implementar
											}
										}
									}
								} else {

									Class<?> claseDestino = (Class<?>) mapaClases
											.get(atributoOrigen.getClass()
													.getName());
									if (claseDestino != null) {
										// Es un objeto complejo
										// log.info("bean complejo");
										Object elementoDestino = claseDestino
												.newInstance();
										copiarBean(mapaClases, atributoOrigen,
												elementoDestino);
										asignarPropiedad(destino,
												nombrePropiedad,
												elementoDestino);
										// BeanUtils.copyProperty(destino,
										// nombrePropiedad, elementoDestino);

									} else {
										// Es un tipo basico
										asignarPropiedad(destino,
												nombrePropiedad, atributoOrigen);

									}
								}

							}

						} else {
							// Valor nulo
							asignarPropiedad(destino, nombrePropiedad,
									atributoOrigen);
						}

					}

				} catch (Exception e) {

					log.fatal("error al convertir bean " + " e.getMessage() = "
							+ e.getMessage() + " e.getCause() " + e.getCause());
					throw new ConversionException(e);
				}

			}

		}
		// }

	}

	/**
	 * Convierte y asigna los atributos comunes entre el objeto origen y destino, 
	 * siempre y cuando se trate de un atributo simple, es decir que no tenga atributos dentro de si mismo.
	 * Por cada atributo en el objeto origen si se encuentra como elemento comun en destino, intentara asignar 
	 * la propiedad en destino.  
	 * @param origen 
	 * @param destino
	 */
	public static void convertirBeanSimple(Object origen, Object destino) {
		Method[] metodos = origen.getClass().getMethods();

		
		for (int j = 0; j < metodos.length; j++) {
			if (metodos[j].getName().startsWith("get")
			// && metodos[j].getName().toUpperCase().endsWith(
					// campos[i].getName().toUpperCase())
					&& !Modifier.isNative(metodos[j].getModifiers())) {
				// log.info("metodo :" + metodos[j]);
				try {
					String nombrePropiedad = metodos[j].getName().substring(3);
					// log.info("nombrePropiedad=" + nombrePropiedad);
					Method metodoDestino = null;
					try {
						metodoDestino = destino.getClass().getMethod(
								metodos[j].getName());
					} catch (NoSuchMethodException e) {
						// log.fatal("No existe metodo "+metodos[j].getName()+
						// " en clase destino "+destino.getClass());
					}

					if (metodoDestino != null) {
						// log.info("metodoDestino=" + metodoDestino);
						Object atributoOrigen = metodos[j].invoke(origen);
						// log.info("atributoOrigen=" + atributoOrigen);
						asignarPropiedad(destino, nombrePropiedad,
								atributoOrigen);
					}

				} catch (Exception e) {

					log.fatal("error al convertir bean " + " e.getMessage() = "
							+ e.getMessage() + " e.getCause() " + e.getCause());
					throw new ConversionException(e);
				}

			}

		}
		// }

	}

	/**
	 * @param destino
	 * @param nombrePropiedad
	 * @param valorDestino
	 */
	private static void asignarPropiedad(Object destino,
			String nombrePropiedad, Object valorDestino) {
		// log.info("--obj destino =" + destino+("--nombrePropiedad =" +
		// nombrePropiedad)+("--valorDestino =" + valorDestino));
		// log.info(("--nombrePropiedad =" +
		// nombrePropiedad)+("--valorDestino =" + valorDestino));
		Method setPropiedad = null;
		try {
			// asignacion simple
			if (destino != null && nombrePropiedad != null) {
				setPropiedad = obtenerMetodoSetter(destino.getClass(),
						nombrePropiedad);
				setPropiedad.invoke(destino, valorDestino);

			}
		} catch (IllegalArgumentException e) {

			//log.info(("--nombrePropiedad =" + nombrePropiedad)+ ("--valorDestino =" + valorDestino));
			//log.info(e.getMessage()+ " : Ocurrio un error al asignar con set "+ nombrePropiedad + " con el valor " + valorDestino+ " en " + destino.getClass().getName());
			//
			try {

				Object valorDestinoConvertido = obtenerValorDestinoConvertido(
						setPropiedad, valorDestino);
				//log.info("valorDestinoConvertido " + valorDestinoConvertido);
				setPropiedad.invoke(destino, valorDestinoConvertido);
			} catch (Exception e1) {
				//log.info(e1.getMessage()+ " : Ocurrio un error al asignar con Converter " + " "	+ nombrePropiedad + " con el valor " + valorDestino	+ " en " + destino.getClass().getName());
				try {
					Object valorDestinoConstructorString = obtenerValorDestinoConstructorString(setPropiedad, valorDestino);
					setPropiedad.invoke(destino, valorDestinoConstructorString);
				} catch (Exception e2) {
					//log.info(e2.getMessage()+ " : Ocurrio un error al asignar con Constructor String "+ " " + nombrePropiedad + " con el valor "+ valorDestino + " en "	+ destino.getClass().getName());
					try {
						Object valorDestinoAsignarConString = obtenerValorDestinoAsignarConString(
								setPropiedad, valorDestino);
						setPropiedad.invoke(destino,
								valorDestinoAsignarConString);
					} catch (Exception e3) {
						log.fatal(e3.getMessage()+ " : Ocurrio un error al asignar con String "	+ " " + nombrePropiedad + " con el valor "+ valorDestino + " en "+ destino.getClass().getName());
					}
				}
			}
		} catch (Exception e) {
			log.fatal(e.getMessage() + " : Ocurrio un error al asignar "+ nombrePropiedad + " con el valor " + valorDestino	+ " en " + destino.getClass().getName());
		}
		

	}

	private static Object obtenerValorDestinoAsignarConString(
			Method setPropiedad, Object valorDestino) throws Exception {
		//log.info("obtenerValorDestinoAsignarConString " + setPropiedad + " "+ valorDestino);
		Class tipos[] = setPropiedad.getParameterTypes();
		Class argumentoSet = tipos[0];
		Constructor<Object> constructorVacio = argumentoSet.getConstructor();
		Object valorDestinoAsignarConString = constructorVacio.newInstance();
		String nombreTipo = constructorVacio.getName();
		nombreTipo = nombreTipo.substring(nombreTipo.lastIndexOf('.') + 1);
		Method metodoSetPropiedadPrincipal = obtenerMetodoSetter(argumentoSet,
				nombreTipo);
		metodoSetPropiedadPrincipal.invoke(valorDestinoAsignarConString,
				valorDestino.toString());
		return valorDestinoAsignarConString;
	}

	private static Object obtenerValorDestinoConstructorString(
			Method setPropiedad, Object valorDestino) throws Exception {
		//log.info("obtenerValorDestinoConstructorString " + setPropiedad + " "+ valorDestino);
		Class tipos[] = setPropiedad.getParameterTypes();
		Class argumentoSet = tipos[0];
		// wraper con constructor de string
		Constructor<Object> constructorString = argumentoSet
				.getConstructor(String.class);
		Object valorDestinoConstructorString = constructorString
				.newInstance(valorDestino.toString());

		return valorDestinoConstructorString;
	}

	private static Object obtenerValorDestinoConvertido(Method setPropiedad,
			Object valorDestino) throws Exception {
		//log.info("obtenerValorDestinoConvertido " + setPropiedad + " "	+ valorDestino);
		Object valorDestinoConvertido = null;
		Class tipos[] = setPropiedad.getParameterTypes();
		Class argumentoSet = null;
		argumentoSet = tipos[0];
		Constructor<Object> constructorVacio = argumentoSet.getConstructor();
		String nombreTipo = constructorVacio.getName();
		nombreTipo = nombreTipo.substring(nombreTipo.lastIndexOf('.') + 1);
		String nombreClaseConverter = obtenerNombreClaseConverter(nombreTipo);
		Class claseConverter = Class.forName(nombreClaseConverter);
		Method metodoConvert = claseConverter
				.getMethod("convert", Object.class);
		//log.info("Converter " + claseConverter + " metodo " + metodoConvert);
		valorDestinoConvertido = metodoConvert.invoke(null, valorDestino);
		//log.info("valorDestinoConvertido " + valorDestinoConvertido);
		return valorDestinoConvertido;
	}

	private static String obtenerNombreClaseConverter(String nombreTipo) {

		return PAQUETE_CONVERTER_MODELOCOMUN + nombreTipo + SUFIJO_CONVERTER;
	}

	private static String obtenerNombreClaseTipoArray(Class<?> arrayAxis) {
		String nombreObjetos = arrayAxis.getName().substring(
				arrayAxis.getName().indexOf(PREFIJO_ARRAY_OF)
						+ PREFIJO_ARRAY_OF.length());
		String nombrePaquete = arrayAxis.getName().substring(0,
				arrayAxis.getName().indexOf(PREFIJO_ARRAY_OF));
		return nombrePaquete + nombreObjetos;
	}

	private static Method obtenerMetodoAxis(Class<?> arrayAxis,
			String getterSetter) {
		// log.info("arrayAxis =" + arrayAxis);
		String nombreObjetos = arrayAxis.getName().substring(
				arrayAxis.getName().indexOf("ArrayOf") + 7);
		StringBuffer sb = new StringBuffer(getterSetter);
		sb.append(nombreObjetos);
		// log.info("metodo =" + sb);
		Method metodoAxis = null;
		for (Method m : arrayAxis.getMethods()) {
			if (m.getName().indexOf(sb.toString()) != -1) {
				metodoAxis = m;
				break;
			}
		}
		return metodoAxis;
	}

	public static void copiarLista(Map<String, Class<?>> mapaClases,
			Collection<Object> listaOrigen,
			Collection<Object> coleccionDestino, Class<?> claseDestino) {

		try {
			// log.info("listaOrigen " + listaOrigen);
			// /log.info("listaOrigen tam " + listaOrigen.size());
			// log.info("coleccionDestino " + coleccionDestino);
			for (Object elementoOrigen : listaOrigen) {

				Object elementoDestino = claseDestino.newInstance();
				copiarBean(mapaClases, elementoOrigen, elementoDestino);
				// log.info("se agrega elemento " + elementoDestino);
				coleccionDestino.add(elementoDestino);
				// log.info("coleccionDestino tam " + coleccionDestino.size());
			}
			// log.info("coleccionDestino " + coleccionDestino);
			// log.info("coleccionDestino tam " + coleccionDestino.size());
		} catch (Exception e) {
			throw new ConversionException(e);
		}

	}

	private static Method obtenerMetodoSetter(Class<?> clase, String propiedad) {
		Method[] metodos = clase.getMethods();
		for (Method metodo : metodos) {
			if (metodo.getName().equals("set" + propiedad)) {

				return metodo;
			}
		}
		return null;

	}

	private static boolean esArrayOf(Object objeto) {

		boolean resultado = false;
		if (objeto != null) {
			// log.info(objeto.getClass().getName() + " es ArrayOf ? ");
			if (objeto.getClass().getName().indexOf(PREFIJO_ARRAY_OF) > 0) {
				resultado = true;

			}

		}
		// log.info("resultado  =" + resultado);
		return resultado;
	}

}

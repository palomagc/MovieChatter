package icaro.aplicaciones.recursos.extractorSemantico;

import gate.Annotation;
import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

import java.util.HashSet;

public interface ItfUsoExtractorSemantico extends ItfUsoRecursoSimple {
	public HashSet<Annotation> extraerAnotaciones(HashSet<String> anotaciones, String textoUsuario)
			throws Exception;
}
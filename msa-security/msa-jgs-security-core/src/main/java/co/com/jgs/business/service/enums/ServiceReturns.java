package co.com.jgs.business.service.enums;

public enum ServiceReturns {
	
	UNEXPECTED_ERROR(-1, "Se ha presentado un error inesperado al intentar ejecutar la operación."),
	SUCCESS(0, "Exito al ejecutar el servicio."),
	EXPECTED_ERROR(1, "Error esperado por validación."),
	DATA_NOT_FOUND(2, "Error consultado en base de datos."),
	LICENSE_ERROR(3, "Error al validar la licencia del usuario."),
	SECURITY_ERROR(4, "Error de seguridad."),
	UNVAILABLE_ERROR(5, "Error en la disponibilidad de los recursos.");
	
	
	private String message;
	private Integer code;
	
	ServiceReturns(Integer code, String message){
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getMessageAndCode() {
		return this.code+"-"+this.message;
	}
}

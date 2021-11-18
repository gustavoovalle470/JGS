package co.com.jgs.base.response;

import co.com.jgs.bo.system.Messages;

public class JGSResponse {
	private Object objectToReturn;
	private Messages messageResponse;
	private boolean success;
	
	public JGSResponse() {
		objectToReturn = null;
		messageResponse = null;
		success = false;
	}
	
	public void setSuccessOperation(Object objectToReturn, Messages messages, String data) {
		this.objectToReturn = objectToReturn;
		messageResponse = messages;
                replaceData(data);
		success = true;
	}
	
	public void setSuccessOperation(Object objectToReturn, Messages messages) {
		objectToReturn = objectToReturn;
		messageResponse = messages;
		success = true;
	}

	public Object getObjectToReturn() {
		return objectToReturn;
	}

	public void setObjectToReturn(Object objectToReturn) {
		this.objectToReturn = objectToReturn;
	}

	public Messages getMessageResponse() {
		return messageResponse;
	}

	public void setMessageResponse(Messages messageResponse) {
		this.messageResponse = messageResponse;
	}

	public void setMessageResponse(Messages messageResponse, String data) {
		this.messageResponse = messageResponse;
		replaceData(data);
	}
	
	public boolean isSuccess() {
		return success;
	}
        
        /**
     * Reemplaza todos los & por la data de usuario, esta vendra separada por coma.
     * @param allData String la data a reemplazar separada por coma.
     */
    public void replaceData(String allData){
        for(String data: allData.split(",")){
            this.messageResponse.setMessage(this.messageResponse.getMessage().replaceFirst("&", data));
        }
    }
}

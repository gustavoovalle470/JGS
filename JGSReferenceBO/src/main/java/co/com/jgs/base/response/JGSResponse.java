package co.com.jgs.base.response;

import co.com.jgs.bo.system.Messages;

public class JGSResponse {
	private Object objectToReturn;
	private Messages messageResponse;
	private boolean success;
	
	public JGSResponse() {
		this.objectToReturn = null;
		this.messageResponse = null;
		this.success = false;
	}
	
	public void setSuccessOperation(Object objectToReturn, Messages messages, String data) {
		this.objectToReturn = objectToReturn;
		this.messageResponse = messages;
		this.messageResponse.replaceData(data);
		this.success = true;
	}
	
	public void setSuccessOperation(Object objectToReturn, Messages messages) {
		this.objectToReturn = objectToReturn;
		this.messageResponse = messages;
		this.success = true;
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
		this.messageResponse.replaceData(data);
	}
	
	public boolean isSuccess() {
		return success;
	}
}

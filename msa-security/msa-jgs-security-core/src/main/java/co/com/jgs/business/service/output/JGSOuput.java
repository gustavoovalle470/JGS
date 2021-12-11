package co.com.jgs.business.service.output;

import java.util.ArrayList;
import java.util.List;
import co.com.jgs.bo.system.Messages;

public class JGSOuput{
    private Object objectToReturn;
    private Messages messageResponse;
    private List<String> errorMessages;
    private boolean success;

    public JGSOuput() {
        objectToReturn = null;
        messageResponse = null;
        success = false;
        errorMessages = new ArrayList<>();
    }

    public void setSuccessOperation(Object objectToReturn, Messages messages, String data) {
        this.objectToReturn = objectToReturn;
        messageResponse = messages;
        messageResponse.replaceData(data); 
        success = true;
    }

    public void setSuccessOperation(Object objectToReturn, Messages messages) {
        this.objectToReturn = objectToReturn;
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
        messageResponse.replaceData(data);    
        this.messageResponse = messageResponse;
    }

    public boolean isSuccess() {
        return success;
    }
	    
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void addMessage(String errorMessage){
        this.errorMessages.add(errorMessage);
    }
}

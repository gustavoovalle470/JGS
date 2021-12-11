package co.com.jgs.business.service.input;

public class JGSInput{
	private String licenseId;
	private String sessionId;
	private String username;
	
	public String getLicense() {
		return licenseId;
	}
	
	public void setLicense(String licenceId) {
		this.licenseId = licenceId;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String toString() {
		return "Username: "+getUsername()+", LicenseId: "+getLicense();
	}
}

package co.com.jgs;

public class ReleaseController {
	public String name = "JGSPersistenceService";
	public String compilation = "29211118-1";
	public String timestamp = "2021-11-18";
	public String version = "1.0.0-BETA";
	public boolean releaseCandidate = false;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompilation() {
		return compilation;
	}
	public void setCompilation(String compilation) {
		this.compilation = compilation;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public boolean isReleaseCandidate() {
		return releaseCandidate;
	}
	public void setReleaseCandidate(boolean releaseCandidate) {
		this.releaseCandidate = releaseCandidate;
	}
}

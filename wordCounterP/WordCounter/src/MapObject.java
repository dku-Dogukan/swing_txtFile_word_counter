

public class MapObject {
	private int key;
	private String map;
	
	
	
	
	public MapObject(String map,int key) {
		
		this.key = key;
		this.map = map;
	}
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getMap() {
		return map;
	}
	public void setMap(String map) {
		this.map = map;
	}
	@Override
	public String toString() {
		
		return this.map+"="+this.key;
	}
	
	

}

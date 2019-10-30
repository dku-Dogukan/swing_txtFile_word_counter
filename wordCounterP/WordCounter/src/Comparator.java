

public class Comparator implements java.util.Comparator<MapObject> {

	@Override
	public int compare(MapObject o1, MapObject o2) {
		return new Integer(o1.getKey()).compareTo(o2.getKey());
	}

}

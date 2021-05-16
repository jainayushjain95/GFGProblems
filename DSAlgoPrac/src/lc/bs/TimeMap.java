package lc.bs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TimeMap {

	static class Data {
		String value;
		int timestamp;
		public Data(String value, int timestamp) {
			super();
			this.value = value;
			this.timestamp = timestamp;
		}
	}
	
	Map<String, List<Data>> dataStore;
	
	public TimeMap() {
		dataStore = new HashMap<String, List<Data>>();
	}

	public void set(String key, String value, int timestamp) {
		if(dataStore.containsKey(key)) {
			dataStore.get(key).add(new Data(value, timestamp));
		} else {
			List<Data> data = new ArrayList<TimeMap.Data>();
			data.add(new Data(value, timestamp));
			dataStore.put(key, data);
		}
	}

	public String get(String key, int timestamp) {
		String value = "";
		if(this.dataStore.containsKey(key)) {
			List<Data> data = this.dataStore.get(key);
			value = getDataObject(data, 0, data.size() - 1, timestamp).value;
		}
		return value;
	}
	
	public static Data getDataObject(List<Data> data, int beginIndex, int endIndex, int timestamp) {
		if(beginIndex > endIndex) {
			return new Data("", timestamp);
		}
		int midIndex = getMidIndex(beginIndex, endIndex);
		if(data.get(midIndex).timestamp > timestamp) {
			return getDataObject(data, beginIndex, midIndex - 1, timestamp);
		} else if(midIndex == data.size() - 1 || data.get(midIndex + 1).timestamp > timestamp) {
			return data.get(midIndex);
		}
		return getDataObject(data, midIndex + 1, endIndex, timestamp);
	}
	
	public static int getMidIndex(int beginIndex, int endIndex) {
        return (endIndex - beginIndex)/2 + beginIndex;
    }
	
	public static void main(String[] args) {
		TimeMap obj = new TimeMap();
		obj.set("love", "high", 10);
		obj.set("love", "low", 20);
		String a = obj.get("love", 10);
		System.out.println(a);
	}
	
}

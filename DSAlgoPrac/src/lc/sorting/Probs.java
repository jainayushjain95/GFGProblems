package lc.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class TimestampWebsite {
	int timestamp;
	String website;
	public TimestampWebsite(int timestamp, String website) {
		super();
		this.timestamp = timestamp;
		this.website = website;
	}
}

class CompareTimestampWebsite implements Comparator<TimestampWebsite> {

	@Override
	public int compare(TimestampWebsite o1, TimestampWebsite o2) {
		return o1.timestamp - o2.timestamp;
	}
	
}

public class Probs {
	
	public static void main(String[] args) {
		String[] username = {"him","mxcmo","jejuvvtye","wphmqzn","uwlblbrkqv","flntc","esdtyvfs","nig","jejuvvtye","nig","mxcmo","flntc","nig","jejuvvtye","odmspeq","jiufvjy","esdtyvfs","mfieoxff","nig","flntc","mxcmo","qxbrmo"};
		int[] timestamp = {113355592,304993712,80831183,751306572,34485202,414560488,667775008,951168362,794457022,813255204,922111713,127547164,906590066,685654550,430221607,699844334,358754380,301537469,561750506,612256123,396990840,60109482};
		String[] website = {"k","o","o","nxpvmh","dssdnkv","kiuorlwdcw","twwginujc","evenodb","qqlw","mhpzoaiw","jukowcnnaz","m","ep","qn","wxeffbcy","ggwzd","tawp","gxm","pop","xipfkhac","weiujzjcy","x"};
		System.out.println(new Probs().mostVisitedPattern(username, timestamp, website));
	}

	public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
		List<String> threeSequenceList = new ArrayList<String>();
		Map<String, List<TimestampWebsite>> map = getMap(username, timestamp, website);
		String maxCountSequence = getSequenceCountMap(map);
		threeSequenceList = Arrays.asList(maxCountSequence.split(" "));
		
		return threeSequenceList;
    }
	
	public String getSequenceCountMap(Map<String, List<TimestampWebsite>> map) {
		String maxCountSequence = "";
		Map<String, Integer> sequenceCountMap = new HashMap<String, Integer>();
		for(String name : map.keySet()) {
			System.out.println("\n");
			List<TimestampWebsite> timestampWebsites = map.get(name);
			for(int i = 0;i < timestampWebsites.size(); i++) {
				System.out.println(name + ", " + timestampWebsites.get(i).timestamp + ", " + timestampWebsites.get(i).website);
			}
		}
		for(String name : map.keySet()) {
			List<TimestampWebsite> timestampWebsites = map.get(name);
			if(timestampWebsites.size() >= 3) {
				Set<String> visits = new HashSet<String>();
				for(int i = 0;i < timestampWebsites.size() - 2; i++) {
					for(int j = i + 1;j < timestampWebsites.size() - 1; j++) {
						for(int k = j + 1;k < timestampWebsites.size(); k++) {
							String threeSequence = getThreeSequence(timestampWebsites, i, j, k);
							if(!visits.contains(threeSequence)) {
								int freq = 1 + sequenceCountMap.getOrDefault(threeSequence, 0);
								sequenceCountMap.put(threeSequence, freq);	
								visits.add(threeSequence);
								if(maxCountSequence == "" || sequenceCountMap.get(threeSequence) > sequenceCountMap.get(maxCountSequence)) {
									maxCountSequence = threeSequence;
								} else if(sequenceCountMap.get(threeSequence) == sequenceCountMap.get(maxCountSequence)) {
									if(maxCountSequence.compareTo(threeSequence) > 0) {
										maxCountSequence = threeSequence;
									}
								}
							}
						}	
					}
				}
			}
		}
		
		return maxCountSequence;
	}
	
	public String getThreeSequence(List<TimestampWebsite> timestampWebsites, int i, int j, int k) {
		StringBuilder stringBuilder = new StringBuilder(timestampWebsites.get(i).website);
		stringBuilder.append(" ");
		stringBuilder.append(timestampWebsites.get(j).website);
		stringBuilder.append(" ");
		stringBuilder.append(timestampWebsites.get(k).website);
		return stringBuilder.toString();
	}
	
	public Map<String, List<TimestampWebsite>> getMap(String[] username, int[] timestamp, String[] website) {
		Map<String, List<TimestampWebsite>> map = new HashMap<String, List<TimestampWebsite>>();
		
		for(int i = 0;i < username.length; i++) {
			String name = username[i];
			if(!map.containsKey(name)) {
				map.put(name, new ArrayList<TimestampWebsite>());
			}
			map.get(name).add(new TimestampWebsite(timestamp[i], website[i]));
		}
		
		for(String name : map.keySet()) {
			Collections.sort(map.get(name), new CompareTimestampWebsite());
		}
		
		return map;
	}
	
}

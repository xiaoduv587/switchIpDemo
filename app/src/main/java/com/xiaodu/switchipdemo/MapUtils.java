package com.xiaodu.switchipdemo;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
 
public class MapUtils {


	public static void main(String[] args) {
 
		Map<String, String> myMap = new HashMap<String, String>();
		myMap.put("a", "张三");
		myMap.put("b", "李四");
		myMap.put("c", "王五");
		myMap.put("d", "小六");
 
		Set<String> set = getKeySetByMap(myMap);
 
		List<String> keyListBySet = getKeyListBySet(set);
 
		List<String> keyList = getListByMap(myMap, true);
		List<String> valuesList = getListByMap(myMap, false);
 
		Iterator<String> it = set.iterator();
 
		while (it.hasNext()) {
			String key = it.next().toString();
			System.out.println("set:" + key);
		}
		for (int i = 0; i < keyListBySet.size(); i++) {
 
			System.out.println("keyListBySet:" + i + "->" + keyListBySet.get(i)
					+ ";key:" + keyList.get(i) + ";values:"
					+ valuesList.get(i));
 
		}
 
	}
 
	/**
	 * 根据map获取map包含的key,返回set
	 * 
	 * @param map
	 * @return
	 */
	public static Set<String> getKeySetByMap(Map<String, String> map) {
 
		Set<String> keySet = new HashSet<String>();
		keySet.addAll(map.keySet());
 
		return keySet;
	}
 
	/**
	 * 根据key的set返回key的list
	 * 
	 * @param set
	 * @return
	 */
	public static List<String> getKeyListBySet(Set<String> set) {
		List<String> keyList = new ArrayList<String>();
		keyList.addAll(set);
		return keyList;
	}
 
	/**
	 * 根据map返回key和value的list
	 * 
	 * @param map
	 * @param isKey
	 *            true为key,false为value
	 * @return
	 */
	public static List<String> getListByMap(Map<String, String> map,
			boolean isKey) {
		List<String> list = new ArrayList<String>();
 
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next().toString();
			if (isKey) {
				list.add(key);
			} else {
				list.add(map.get(key));
			}
		}
 
		return list;
	}
 
}

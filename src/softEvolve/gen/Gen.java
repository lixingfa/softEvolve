/**
 * 
 */
package softEvolve.gen;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import common.constant.GenConstant;
import common.constant.SysConstant;

/**
 * @author lixingfa
 * @date 2018年4月4日下午5:39:00
 * 
 */
public class Gen {
	
	public Map<String, String>[] multiply(Map<String, String> fatherGens,Map<String, String> motherGens){
		Map<String, String> fatherGensCopy = new HashMap<String, String>();
		Map<String, String> motherGensCopy = new HashMap<String, String>();
		Map<String, String>[] sons = new HashMap[4];
		Map<String, String> semen[] = new HashMap[4];
		Map<String, String> egg[] = new HashMap[4];
		for (int i = 0; i < sons.length; i++) {
			sons[i] = new HashMap<String, String>();
			semen[i] = new HashMap<String, String>();
			egg[i] = new HashMap<String, String>();
		}
		//1、染色体倍增
		//父染色体增加一倍
		for (Entry<String, String> entry: fatherGens.entrySet()) {
			fatherGensCopy.put(entry.getKey(), entry.getValue());
		}
		//新增的染色体发生部分交换
		gensExchange(fatherGensCopy);
		//母染色体增加一倍
		for (Entry<String, String> entry: motherGens.entrySet()) {
			motherGensCopy.put(entry.getKey(), entry.getValue());
		}
		//新增的染色体发生部分交换
		gensExchange(motherGensCopy);
		//2、分裂成四个生殖细胞
		
		
		return sons;
	}
	
	/**
	 * gensExchange:(双倍体中父母双方的基因发生部分交换)
	 * @author lixingfa
	 * @date 2018年4月9日上午11:23:10
	 * @param gens
	 */
	private void gensExchange(Map<String, String> gens){
		//每对染色体都随机互换一部分
		//1、找出同源染色体
		for (Entry<String, String> entry: gens.entrySet()) {
			String key = entry.getKey();
			String genName = key.substring(0, key.indexOf("-") -1);
			for (Entry<String, String> other: gens.entrySet()) {
				if (other.getKey().startsWith(genName) && !other.getKey().equals(key)) {
					//2、交换完整的，有效的基因，包括内含子和外显子
					char[] gen = entry.getValue().toCharArray();
					char[] otherGen = other.getValue().toCharArray();
					int begin = (int)(gen.length * Math.random());
					int end = begin + (int)(gen.length * GenConstant.EXCHANGE_PERCENT);
					for (int i = begin; i <= end; ) {
						if (gen[i] == '-') {
							//gen和otherGen的坐标是不一样的
						}
						i++;
					}
					break;
				}
			}
		}
	}

}

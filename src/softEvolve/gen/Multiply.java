/**
 * 
 */
package softEvolve.gen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
public class Multiply {
	
	/**
	 * multiply:(繁殖，父母双方的染色体各倍增一次，然后分裂成四个生殖细胞，这些细胞再组成四个原始细胞)
	 * @author lixingfa
	 * @date 2018年4月11日上午10:26:29
	 * @param fatherGens
	 * @param motherGens
	 * @return
	 * @throws Exception
	 */
	public Map<String, String>[] multiply(Map<String, String> fatherGens,Map<String, String> motherGens) throws Exception{
		Map<String, String> fatherGensCopy = new HashMap<String, String>();
		Map<String, String> motherGensCopy = new HashMap<String, String>();
		Map<String, String>[] sons = new HashMap[4];
		//1、染色体倍增(基因的增强在平时使用时已经反馈到基因里了，不需要额外的机制去处理。也就是基因已经随着软件的使用发生改变，但因为没有重新编译，所以没有体现出来。进化是环境调控的，不依赖于突变。)
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
		Map<String, String> semen[] = getSemensOrEggs(fatherGens, fatherGensCopy);
		Map<String, String> egg[] = getSemensOrEggs(motherGens, motherGensCopy);
		//3、生殖细胞随机组合成新的原始细胞
		for (int i = 0; i < sons.length; i++) {
			int si = (int)(Math.random() * 10) % 4;
			while (semen[si] == null) {
				si = (int)(Math.random() * 10) % 4;
			}
			int ei = (int)(Math.random() * 10) % 4;
			while (egg[si] == null) {
				ei = (int)(Math.random() * 10) % 4;
			}
			sons[i].putAll(addParentTips(semen[si], "-" + SysConstant.FATHER));
			sons[i].putAll(addParentTips(egg[si], "-" + SysConstant.MOTHER));
			semen[si] = null;
			egg[ei] = null;
		}		
		return sons;
	}
	
	/**
	 * gensExchange:(双倍体中父母双方的基因发生部分交换)
	 * @author lixingfa
	 * @date 2018年4月9日上午11:23:10
	 * @param gens
	 */
	private void gensExchange(Map<String, String> gens) throws Exception{
		//每对染色体都随机互换一部分
		//1、找出同源染色体
		Set<String> hasExchange = new HashSet<String>();
		for (Entry<String, String> entry: gens.entrySet()) {
			String key = entry.getKey();
			String genName = key.substring(0, key.indexOf("-") -1);
			for (Entry<String, String> other: gens.entrySet()) {
				if (!hasExchange.contains(genName) && other.getKey().startsWith(genName) && !other.getKey().equals(key)) {
					//2、交换完整的，有效的基因，包括内含子和外显子
					char[] gen = entry.getValue().toCharArray();
					char[] otherGen = other.getValue().toCharArray();
					int begin = (int)(gen.length * Math.random());
					int end = begin + (int)(gen.length * GenConstant.EXCHANGE_PERCENT);
					if (end > gen.length) {
						end = gen.length - 1;
					}
					if (end > otherGen.length) {
						end = otherGen.length - 1;
					}
					StringBuffer g = new StringBuffer();
					StringBuffer o = new StringBuffer();
					int index = 0;
					int gIndex = 0;
					int oIndex = 0;
					while (gIndex < gen.length && oIndex < otherGen.length) {						
						if (gen[gIndex] == '-') {//内含子都是只占三个位置的
							if (index >= begin && index <= end) {//交换
								o.append(gen[gIndex]).append(gen[gIndex + 1]).append(gen[gIndex + 2]);
							}else {
								g.append(gen[gIndex]).append(gen[gIndex + 1]).append(gen[gIndex + 2]);								
							}
							gIndex = gIndex + 3;
						}
						if (otherGen[oIndex] == '-') {
							if (index >= begin && index <= end) {//交换
								g.append(otherGen[oIndex]).append(otherGen[oIndex + 1]).append(otherGen[oIndex + 2]);
							}else {
								o.append(otherGen[oIndex]).append(otherGen[oIndex + 1]).append(otherGen[oIndex + 2]);								
							}
							oIndex = oIndex + 3;
						}
						try {
							if (index >= begin && index <= end) {//交换
								g.append(otherGen[oIndex]);
								o.append(gen[gIndex]);
							}else {
								g.append(gen[gIndex]);
								o.append(otherGen[oIndex]);								
							}
						} catch (Exception e) {
							throw new Exception("染色体交换时下标越界，gen.length=" + gen.length + ",gIndex=" + gIndex 
									+ ",otherGen.length=" + otherGen.length + ",oIndex=" + oIndex);
						}
						gIndex++;
						oIndex++;
						index++;
					}
					gens.put(key, String.valueOf(otherGen));
					gens.put(other.getKey(), String.valueOf(gen));
					hasExchange.add(genName);
					break;
				}
			}
		}
	}
	
	/**
	 * getSemensOrEggs:(原基因和倍增的基因分裂成4个生殖细胞)
	 * @author lixingfa
	 * @date 2018年4月10日下午7:39:55
	 * @param parent 原基因
	 * @param parentCopy 倍增的基因
	 * @return 4个生殖细胞，即只有原基因一半的细胞
	 */
	private Map<String, String>[] getSemensOrEggs(Map<String, String> parent, Map<String, String> parentCopy){
		Map<String, String> se[] = new HashMap[4];
		for (int i = 0; i < se.length; i++) {
			se[i] = new HashMap<String, String>();
		}
		for (Entry<String, String> entry: parent.entrySet()) {
			String key = entry.getKey();
			String genName = key.substring(0, key.indexOf("-") -1);
			int i = (int)(Math.random() * 10) % 2;//保证乱序
			if (se[i].containsKey(genName)) {
				se[(i + 1) % 2].put(genName, entry.getValue());
			}else {
				se[i].put(genName, entry.getValue());
			}
		}
		for (Entry<String, String> entry: parentCopy.entrySet()) {
			String key = entry.getKey();
			String genName = key.substring(0, key.indexOf("-") -1);
			int i = (int)(Math.random() * 10) % 2;
			if (se[i + 2].containsKey(genName)) {
				se[(i + 1) % 2 + 2].put(genName, entry.getValue());
			}else {
				se[i + 2].put(genName, entry.getValue());
			}
		}
		return se;
	}

	/**
	 * addParentTips:(给对应Map的key加上后缀)
	 * @author lixingfa
	 * @date 2018年4月10日下午8:14:12
	 * @param gens
	 * @param tips
	 */
	private Map<String, String> addParentTips(Map<String, String> gens,String tips){
		Map<String, String> map = new HashMap<String, String>();
		for (Entry<String, String> entry: gens.entrySet()) {
			map.put(entry.getKey() + tips, entry.getValue());
		}
		return map;
	}
}

/**
 * 
 */
package softEvolve.gen;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import common.constant.SysConstant;

/**
 * @author lixingfa
 * @date 2018年4月4日下午5:39:00
 * 
 */
public class Gen {
	
	public Map<String, String>[] multiply(Map<String, String> fatherGens,Map<String, String> motherGens){
		Map<String, String>[] sons = new HashMap[4];
		Map<String, String> semen[] = new HashMap[4];
		Map<String, String> egg[] = new HashMap[4];
		for (int i = 0; i < sons.length; i++) {
			sons[i] = new HashMap<String, String>();
			semen[i] = new HashMap<String, String>();
			egg[i] = new HashMap<String, String>();
		}
		//先将父母染色体随机分成两个生殖细胞
		Set<Entry<String, String>> fatherSet = fatherGens.entrySet();
		for (Entry<String, String> entry: fatherSet) {
			if (entry.getKey().endsWith(SysConstant.FATHER)) {
				
			}else {
				
			}
		}
		//父母染色体交换，再分裂得到生殖细胞
		
		return sons;
	}

}

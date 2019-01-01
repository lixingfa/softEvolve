package softEvolve.gen.model;

import softEvolve.gen.constant.GenTypeConstant;

public class Gen {

	private String name = "Gen";//每种基因都有自己的名称和特定的功能
	private long value;//基因的表达，往往是通过量来的，大多数时候并不是非黑即白的关系
	private long index;//索引，从0开始，后面的会覆盖之前的
	private int status;//0不表达，1表达，2同样不表达的情况下优先表达
	private int type = GenTypeConstant.STRUCTURE;//基因的类别，结构、事件……
	
	/*******************************/
	public String getName() {
		return name;
	}
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	public long getIndex() {
		return index;
	}
	public void setIndex(long index) {
		this.index = index;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}

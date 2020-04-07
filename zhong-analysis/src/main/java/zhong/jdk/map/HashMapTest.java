package zhong.jdk.map;

import java.util.HashMap;

/**
 * 重写equals方法必须重写hashcode方法
 *
 * @ClassName: HashMap
 * @author: ganquanzhong
 * @date: 2020/4/7 13:44
 */
public class HashMapTest extends Object{
	public static void main(String[] args){
		Key key1 = new Key("nihao");
		Key key2 = new Key("nihao");
		HashMap map = new HashMap<String , Object>();
		map.put(key1,"Key with id is 1");
		HashMapTest test = new HashMapTest();

		System.out.println(map.get(key1));
		System.out.println(map.get(key2));

	}

}

class Key{
	private String name;
	public Key(){};

	public Key(String s){
		System.out.println("===========>"+calcHashCode("nihao"));
		name=s;
	};
	public String getName(){
		return name;
	}

	/**
	 *	HashMap 是数组+链表的方式实现  jdk8后加入了红黑树【1.】
	 *	重写hashCode方法，保证同一个对象实例的hashcode相同
	 *  同时还要重写equals方法  保证值相同
	 *
	 */

	public int hashCode(){
		System.out.println(name.hashCode());
		return name.hashCode();
	}


	public boolean equals(Object o){
		if (o == null || !(o instanceof Key)) {
			return false;
		}else{
			return this.name.equals(  ((Key) o).getName()  );
		}
	}

	/**
	 *  String中计算hashCode的方法
	 */

	public  int calcHashCode(String name){
		int hash = 0;
		int h=hash;
		char[] value=name.toCharArray();
		char val[] = value;
		for (int i = 0; i < value.length; i++) {
			h = 31 * h + val[i];
		}
		hash = h;
		return h;
	};


	/*
    （1）如果作用于基本数据类型的变量（byte,short,char,int,long,float,double,boolean ）,
		对于==而言，则直接比较其存储的"值"是否相等

		如果作用于引用类型的变量（String），,对于==而言，则比较的是所指向的对象的地址（即是否指向同一个对象）。

	（2）equals方法是用来比较两个对象的引用是否相等，即是否指向同一个对象。
		equals方法是基类Object中的方法，因此对于所有的继承于Object的类都会有该方法。

	（3）equals方法不能作用于基本数据类型的变量

	（4）如果没有对equals方法进行重写，则比较的是引用类型的变量所指向的对象的地址；

	而String类对equals方法进行了重写，用来比较指向的字符串对象所存储的字符串是否相等。

	其他的一些类诸如Double，Date，Integer等，都对equals方法进行了重写用来比较指向的对象所存储的内容是否相等。
	*/
}
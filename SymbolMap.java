import java.util.*;


class SymbolMap{
	/*symbol name ->  symbol*/
	private Map<String,List<MiniJavaSymbol>> symTable;
	private String className;
	/*current field and method offset of class*/
	private int currentFieldOffset;
	private int currentMethodOffset;
	/*method counter*/
	private int methodCounter;


	public SymbolMap(String pc){
		methodCounter=0;
		currentFieldOffset=0;
		currentMethodOffset=0;
		symTable= new LinkedHashMap<String,List<MiniJavaSymbol>>();
		className=pc;
	}


	public void setMethodCounter(int c){
		methodCounter=c;
	}

	public int getMethodCounter(){
		return methodCounter;
	}

	public void setFieldOffset(int new_offset){
		currentFieldOffset=new_offset;
	}

	public void setMethodOffset(int new_offset){
		currentMethodOffset=new_offset;
	}

	public int getFieldOffset(){
		return currentFieldOffset;
	}

	public int getMethodOffset(){
		return currentMethodOffset;
	}

	public String getClassName(){
		return className;
	}

	public void insert(String symName,MiniJavaSymbol sym){
		if (symTable.containsKey(symName)){
			List<MiniJavaSymbol> ll=symTable.get(symName);
			ll.add(sym);
		}
		else{
			List<MiniJavaSymbol> ll=new LinkedList<MiniJavaSymbol>();
			ll.add(sym);
			symTable.put(symName,ll);
		}
		
	}


	public Set<Map.Entry<String,List<MiniJavaSymbol>>> entrySet(){
		return symTable.entrySet();
	}


	public List<MiniJavaSymbol> get(String symName){
		if (symTable.containsKey(symName)){
			return symTable.get(symName);
		}
		else{
			return null;
		}
	}


	public MiniJavaSymbol get(String symName,String category,String cs){
		if (cs.equals("local")){
		/*search fields or methods declared locally*/
	 		if (symTable.containsKey(symName)){
	 			for (MiniJavaSymbol symbol : symTable.get(symName) ){
	 				if (symbol.getCategory().equals(category)){
	 					return symbol;
	 				}
	 			}
			}
			return null;
		}
		else{
		/*search fields or methods inherited*/
			Set<String> key_set=symTable.keySet();
			for (String key : key_set){
				if (key.contains("."+symName)){
					for (MiniJavaSymbol symbol : symTable.get(key) ){
						if (symbol.getCategory().equals(category)){
		 					return symbol;
		 				}
					}
				}
			}
			return null;
		}
	}


	public boolean contains(String symName){
		return symTable.containsKey(symName);
	}
	

	public void print(){

		System.out.println("");
		System.out.print("--------Symbol map for class: "+className);
		String parentClassName=Main.inheritanceMap.get(className);
		while (parentClassName != null){
			System.out.print("->" +parentClassName);
			String temp=parentClassName;
			parentClassName=Main.inheritanceMap.get(temp);
		}
		System.out.println("--------");
		int field_offset=0;
		int method_offset=0;
		System.out.println("----FIELDS----");
		for(String key : symTable.keySet()){
			List<MiniJavaSymbol> value=symTable.get(key);
			for (MiniJavaSymbol symbol : value){
				if (symbol.getCategory().equals("F_VAR")){
					String name=symbol.getName();
					int offset=symbol.getOffset();					
					if (key.contains(".")){
						continue;
					}
					else{
						System.out.println(className+"."+name+": "+offset);
					}
				}
			}
		}
		System.out.println("----METHODS----");
		for(String key : symTable.keySet()){
			List<MiniJavaSymbol> value=symTable.get(key);
			for (MiniJavaSymbol symbol : value){
				if (symbol.getCategory().equals("METHOD")){
					String name=symbol.getName();
					int offset=symbol.getOffset();					
					if (key.contains(".")){
						continue;
					}
					else{
						System.out.println(className+"."+name+": "+offset);
					}
				}
			}
		}

		System.out.println("");
		for(String key : symTable.keySet()){
			System.out.print("Symbol name:"+key+"->");
			List<MiniJavaSymbol> value=symTable.get(key);
			Iterator<MiniJavaSymbol> i = value.iterator();
			while (i.hasNext()){
				i.next().print();
			}
			System.out.println("");
		}
		
	}
}
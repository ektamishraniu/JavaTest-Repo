package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
* Class Refrigerator
* Shelves type and their capacity
*/
public class Refrigerator {
	
	List<Stuff> items;
	Map<SHELVES, Integer> capMap;
	
    // Type
    public enum Refrigerator{
		SMALL,
		MEDIUM,
		LARGE
	}

    // OverloadExcption to error out overloading of Refrigerator
	public class OverloadException extends Exception{
		public OverloadException(String errMsg){
			super(errMsg);
		}
	}
	
	// Constructor
	public Refrigerator(Map<SHELVES, Integer> capacity)throws Exception{
		if(capacity == null || capacity.size() < 3){
			throw new Exception("Please Give Capacity Of Shelves !!");
		}
		items = new ArrayList<Refrigerator.Stuff>();
		capMap = capacity;
	}
	
	// What is going in Refrigerator
	public class Stuff{
		
		int itemId;
		SHELVES itemSize;
		String name;

        // get type to be used for hashcode generation
        private Refrigerator getType() {
			return Refrigerator.this;
		}
		
        //Constructor
		public Stuff(int itemId, int itemCount, SHELVES size, String name){
			this.itemId = itemId;
			this.itemSize = size;
			this.name = name;
		}
		//Constructor
		public Stuff(int itemId){
			this.itemId = itemId;
		}
        // Get stuff id
		public int getStuffId() {
			return itemId;
		}
        // Get stuff name
		public String getName() {
			return name;
		}		
        // Get shelves size
		public SHELVES getStuffSize(){
			return itemSize;
		}

        // Override the equals to match type
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Stuff other = (Stuff) obj;
			if (!getType().equals(other.getType()))
				return false;
			if (itemId != other.itemId)
				return false;
			return true;
		}

        // Mandatory hashcode override for equals
        @Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getType().hashCode();
			result = prime * result + itemId;
			return result;
		}
		
	}
	
	// Lets put some stuff in Refrigerator
	public boolean putStuff(Stuff item)throws OverloadException{
		if(item == null){
			return false;
		}
		if(capMap.get(item.getStuffSize()) < 1){
			throw new OverloadException("No Space Is Available !!");
		}
		items.add(item);
		capMap.put(item.getStuffSize(), Integer.valueOf(capMap.get(item.getStuffSize()).intValue() + 1));
		return true;
	}
	
	// Lets take out some stuff from Refrigerator 
	public Stuff getStuff(int itemId)throws Exception{
		Stuff myStuff = new Stuff(itemId);
		int index = items.indexOf(myStuff);
		if(index >=0){
			Stuff item = items.get(index);
			capMap.put(item.getStuffSize(), Integer.valueOf(capMap.get(item.getStuffSize()).intValue() - 1));
			return item;
			
		}else{
			throw new Exception("Sorry Nothing Is Found");
		}
			
	}
	
}

package com.sparksys.common.entity;

import java.io.Serializable;

public class MstD001PlantCount  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -637969498973387344L;

	private String typeId;
	
	private String typeName;
	
	private String typeCount;

	public MstD001PlantCount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeCount() {
		return typeCount;
	}

	public void setTypeCount(String typeCount) {
		this.typeCount = typeCount;
	}

}

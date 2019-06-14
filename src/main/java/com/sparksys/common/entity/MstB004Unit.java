package com.sparksys.common.entity;

import java.util.List;

public class MstB004Unit extends CommonOutDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4186630102931401223L;

	private Long id;

    private Long parentId;

    private String unitChName;

    private String unitEnName;

    private Long unitId;

    private List<MstB004Unit> mstB004Units;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getUnitChName() {
        return unitChName;
    }

    public void setUnitChName(String unitChName) {
        this.unitChName = unitChName == null ? null : unitChName.trim();
    }

    public String getUnitEnName() {
        return unitEnName;
    }

    public void setUnitEnName(String unitEnName) {
        this.unitEnName = unitEnName == null ? null : unitEnName.trim();
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

	public List<MstB004Unit> getMstB004Units() {
		return mstB004Units;
	}

	public void setMstB004Units(List<MstB004Unit> mstB004Units) {
		this.mstB004Units = mstB004Units;
	}
    
}
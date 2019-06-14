package com.sparksys.common.entity;

import java.io.Serializable;

public class SysPermissionInit implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 6393334193707586161L;

	private Long permissionInitId;

    private String permissionInitCode;

    private Long permissionInitSort;

    private String permissionInitUrl;

    public Long getPermissionInitId() {
        return permissionInitId;
    }

    public void setPermissionInitId(Long permissionInitId) {
        this.permissionInitId = permissionInitId;
    }

    public String getPermissionInitCode() {
        return permissionInitCode;
    }

    public void setPermissionInitCode(String permissionInitCode) {
        this.permissionInitCode = permissionInitCode == null ? null : permissionInitCode.trim();
    }

    public Long getPermissionInitSort() {
        return permissionInitSort;
    }

    public void setPermissionInitSort(Long permissionInitSort) {
        this.permissionInitSort = permissionInitSort;
    }

    public String getPermissionInitUrl() {
        return permissionInitUrl;
    }

    public void setPermissionInitUrl(String permissionInitUrl) {
        this.permissionInitUrl = permissionInitUrl == null ? null : permissionInitUrl.trim();
    }
}
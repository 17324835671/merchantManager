package com.sparksys.system.user.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sparksys.common.dao.SysUserDao;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.SysUser;
import com.sparksys.common.utils.RedisConstant;
import com.sparksys.common.utils.SnowFlakeId;
import com.sparksys.system.user.service.ISysUserService;
@Service
@CacheConfig(cacheNames = RedisConstant.SYS_USER)
public class SysUserServiceImpl implements ISysUserService {

	@Resource
	private SysUserDao userDao;
	
	@Caching(evict = { @CacheEvict(key="#p0.userId",allEntries=true,beforeInvocation=true),
			@CacheEvict(key="#p0.userName",allEntries=true,beforeInvocation=true),
			@CacheEvict(value="findSysUserPageList",allEntries=true,beforeInvocation=true),
			@CacheEvict(value="findSysUserList",allEntries=true,beforeInvocation=true)})
	public void deleteSysUer(SysUser user) {
		if(user.getUserId()!=null&&!user.getUserId().equals(0L)) {
			userDao.deleteSysUserById(user.getUserId());
		}
	}

	@Caching(evict = { @CacheEvict(key="#p0.userId",allEntries=true,beforeInvocation=true),
			@CacheEvict(key="#p0.userName",allEntries=true,beforeInvocation=true),
			@CacheEvict(value="findSysUserPageList",allEntries=true,beforeInvocation=true),
			@CacheEvict(value="findSysUserList",allEntries=true,beforeInvocation=true)})
	public void saveSysUer(SysUser user) {
		if(user.getUserId()==null||"".equals(user.getUserId())) {
			user.setUserId(SnowFlakeId.getSnowFlakeId());
			userDao.insertSysUser(user);
		}
	}

	@Cacheable(key ="#p0")
	public SysUser findSysUerById(Long userId) {
		SysUser user = userDao.findSysUserById(userId);
		return user;
	}

	@Cacheable(key ="#p0")
	public SysUser findSysUerByUserNamePwd(Map<String, Object> map) {
		SysUser user = userDao.findSysUserByUserNamePwd(map);
		return user;
	}

	@Caching(evict = { @CacheEvict(key="#p0.userId",allEntries=true,beforeInvocation=true),
			@CacheEvict(key="#p0.userName",allEntries=true,beforeInvocation=true),
			@CacheEvict(value="findSysUserPageList",allEntries=true,beforeInvocation=true),
			@CacheEvict(value="findSysUserList",allEntries=true,beforeInvocation=true)})
	public void updateSysUer(SysUser user) {
		if(user.getUserId()!=null&&!"".equals(user.getUserId())) {
			userDao.updateSysUser(user);
		}
	}

	@Cacheable(value ="findSysUserList")
	public List<SysUser> findSysUserList(Map<String, Object> map) {
		List<SysUser> list = userDao.findSysUserList(map);
		return list;
	}

	@Cacheable(value="findSysUserPageList")
	public PageBean<SysUser> findSysUserPageList(int currentPage,int pageSize,Map<String, Object> map) {
		PageHelper.startPage(currentPage, pageSize);
		List<SysUser> list = userDao.findSysUserList(map);
		int totalCount = userDao.findSysUserListCount(map);
		PageBean<SysUser> pageData = new PageBean<>(currentPage, pageSize, totalCount);
		pageData.setItems(list);
		return pageData;
	}

	@Override
	public List<SysUser> findRoleUserList(Long roleId) {
		List<SysUser> list = userDao.findRoleUserList(roleId);
		return list;
	}
}

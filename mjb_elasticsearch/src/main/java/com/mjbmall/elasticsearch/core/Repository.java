package com.mjbmall.elasticsearch.core;

import java.util.List;

import org.apache.http.HttpEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mjbmall.elasticsearch.core.entity.HitEntity;

public interface Repository<T> {
	
    /**
     * 同步方式向ES集群写入数据
     * @param index
     * @param type
     * @param id
     * @param entity
     * @return boolean
     */
    public boolean putSync(String index, String type, String id, HttpEntity entity);
    
    /**
     * 异步的方式向ES写入数据
     * @param index
     * @param type
     * @param id
     * @param entity
     * @return boolean
     */
    public void putAsync(String index, String type, String id, HttpEntity entity);
    
    /**
     * DSL方式查询获取List结果
     * @param index
     * @param type
     * @param entity
     * @return list
     */
    public List<HitEntity<T>> queryDSLByList(String index, String type, String query);

    /**
     * 轻量方式查询获取List结果
     * @param index
     * @param type
     * @param entity
     * @return list
     */
    public List<HitEntity<T>> querySimpleByList(String index, String type, String query);
    
    /**
     * DSL查询获取page结果
     * @param index
     * @param type
     * @param entity
     * @param pageable
     * @return page
     */
    public Page<HitEntity<T>> queryDSLByPage(String index, String type, String query, Pageable pageable);

    /**
     * 轻量查询获取page结果
     * @param index
     * @param type
     * @param entity
     * @param pageable
     * @return page
     */
    public Page<HitEntity<T>> querySimpleByPage(String index, String type, String query, Pageable pageable);
}

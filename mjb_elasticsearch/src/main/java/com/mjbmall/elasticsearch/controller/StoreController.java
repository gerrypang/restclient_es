package com.mjbmall.elasticsearch.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjbmall.elasticsearch.core.controller.BaseController;
import com.mjbmall.elasticsearch.core.entity.HitEntity;
import com.mjbmall.elasticsearch.core.until.DzResponse;
import com.mjbmall.elasticsearch.domain.Store;
import com.mjbmall.elasticsearch.domain.vo.QueryRequest;
import com.mjbmall.elasticsearch.repositories.StoreRepository;

/**
 * StoreController
 * @author Gerry_Pang
 */
@Controller
@RequestMapping("/search/store")
public class StoreController extends BaseController {

	@Autowired
	private StoreRepository storeRepository;
	
    /**
     * List查询
     * @param query
     * @param request
     * @return
     */
    @RequestMapping(value = "list", method = {RequestMethod.GET} )
    @ResponseBody
    public List<HitEntity<Store>> search(@RequestParam(value="query") String query) {
    	String deCodeQuery = "";
		try {
			deCodeQuery = URLDecoder.decode(query, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
		}
    	List<HitEntity<Store>> returnList = storeRepository.findPageByOneParam(deCodeQuery);
    	
    	return returnList;
    }
    
    /**
     * Page分页查询 POST
     * @param query
     * @param page
     * @param pageSize 
     * @return Page<HitEntity<Store>>
     */
    @RequestMapping(value = "page", method = RequestMethod.GET )
    @ResponseBody
    public Page<HitEntity<Store>> getPage(
    		@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
    	String deCodeQuery = "";
    	Page<HitEntity<Store>> returnPage = null;
    	
       	try {
       		deCodeQuery = URLDecoder.decode(query, "UTF-8");
    	} catch (UnsupportedEncodingException e) {
    		logger.error(e.toString());
    	}
       	
		Pageable pageable = new PageRequest(page, pageSize);
		logger.info("/search/page -> pageSize = {}, pageNumber = {}", pageable.getPageNumber(), pageable.getPageSize());
		returnPage = storeRepository.findPageByOneParam(deCodeQuery, pageable);
		
		return returnPage;
    }
    
    /**
     * Page分页查询 GET
     * @param query
     * @return DzResponse
     */
    @RequestMapping(value = "page", method = RequestMethod.POST )
    @ResponseBody
    public DzResponse postPage(@RequestBody QueryRequest queryReq) {
    	DzResponse result = null;
    	String deCodeQuery = "";
    	Page<HitEntity<Store>> returnPage = null;
    	try {
    		deCodeQuery = URLDecoder.decode(queryReq.getQuery(), "UTF-8");
    		Pageable pageable = new PageRequest(queryReq.getPage(), queryReq.getPageSize());
    		logger.info("/search/page -> pageSize = {}, pageNumber = {}", pageable.getPageNumber(), pageable.getPageSize());
	    	// 根据请求参数判断是否走排序
	    	if(StringUtils.isBlank(queryReq.getSortField()) && StringUtils.isBlank(queryReq.getSortOrder())){
	    		returnPage = storeRepository.findPageByOneParam(deCodeQuery, pageable);
	    	}else{
	    		returnPage = storeRepository.findPageByOneParam(deCodeQuery, pageable, queryReq.getSortField(), queryReq.getSortOrder());
	    	}
    		result = DzResponse.SUCCESS(returnPage);
    	} catch (UnsupportedEncodingException e) {
    		logger.error(e.toString());
    		result = DzResponse.FAILURE(e.getMessage());
    	}catch (Exception e) {
    		logger.error(e.toString());
    		result = DzResponse.FAILURE(e.getMessage());
		}
    	return result;
    }
    
    public void toJson(Object obj){
        // write your code here
        ObjectMapper mapper = new ObjectMapper();
        try {
			String jsonInString = mapper.writeValueAsString(obj);
			logger.info(jsonInString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

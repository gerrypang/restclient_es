package com.mjbmall.elasticsearch.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjbmall.elasticsearch.core.entity.HitEntity;
import com.mjbmall.elasticsearch.domain.Demotable;
import com.mjbmall.elasticsearch.repositories.DemotableRepository;

@Controller
@RequestMapping("/search/demotable")
public class DemotableController {

	@Autowired
	private DemotableRepository demotableRepository;
	/** 日志类*/
	private static final Logger logger = LoggerFactory.getLogger(DemotableController.class);
	
    /**
     * List查询
     * @param query
     * @param request
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public List<HitEntity<Demotable>> search(@RequestParam(value="query") String query) {
    	String deCodeQuery = "";
		try {
			deCodeQuery = URLDecoder.decode(query, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
		}
    	List<HitEntity<Demotable>> returnList = demotableRepository.findPageByOneParam(deCodeQuery);
    	
    	return returnList;
    }
    
    /**
     * Page分页查询
     * @param query
     * @param page
     * @param pageSize 
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.GET)
    @ResponseBody
    public Page<HitEntity<Demotable>> searchPage(
    		@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
    	String deCodeQuery = "";
    	Page<HitEntity<Demotable>> returnPage = null;
    	
		try {
			deCodeQuery = URLDecoder.decode(query, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
		}
		Pageable pageable = new PageRequest(page, pageSize);
		logger.info("/search/page -> pageSize = {}, pageNumber = {}", pageable.getPageNumber(), pageable.getPageSize());
		returnPage = demotableRepository.findPageByOneParam(deCodeQuery, pageable);
		return returnPage;
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

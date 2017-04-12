package com.mjb.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjbmall.elasticsearch.core.ClusterNode;
import com.mjbmall.elasticsearch.core.TemplateRepository;
import com.mjbmall.elasticsearch.core.entity.HitEntity;
import com.mjbmall.elasticsearch.domain.Book;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath*:TemplateRepository.class"})
@ContextConfiguration(classes=TemplateRepository.class)
public class QueryTest {
	public ClusterNode clusterNode;
	public TemplateRepository<Book> templateRepository;
		
	@Test
	public void queryTest(){
		List<String> hosts = new ArrayList<String>();
		hosts.add("192.168.1.117:9200");
		clusterNode = new ClusterNode(hosts);
		
		templateRepository = new TemplateRepository<Book>(clusterNode);
		
		long startTime=System.currentTimeMillis();   //获取开始时间 
		Pageable pageable = new PageRequest(0, 2);
		Page<HitEntity<Book>> books = templateRepository.queryDSLByPage("sys", "book", "{\"bool\": {\"should\" :[{\"match\" : {\"name\" : \"国\"}},{\"match\" : {\"message\" : \"国\"}}] }}", pageable);
		long endTime=System.currentTimeMillis(); //获取结束时间  
		System.out.println("程序运行时间： "+(endTime-startTime)+"ms");   
		/*
		for (int i = 0; i < books.getContent().size(); i++) {
			System.out.println(books.getContent().get(i));
		}
		*/
		toJson(books);
	}
	
    public void toJson(Object obj){
        // write your code here
        ObjectMapper mapper = new ObjectMapper();
        try {
			String jsonInString = mapper.writeValueAsString(obj);
			System.out.println(jsonInString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public ClusterNode getClusterNode() {
		return clusterNode;
	}

	public void setClusterNode(ClusterNode clusterNode) {
		this.clusterNode = clusterNode;
	}

	public TemplateRepository<Book> getTemplateRepository() {
		return templateRepository;
	}

	public void setTemplateRepository(TemplateRepository<Book> templateRepository) {
		this.templateRepository = templateRepository;
	}
}

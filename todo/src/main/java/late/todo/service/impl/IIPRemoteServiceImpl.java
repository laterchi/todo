/**
 * @description
 */
package late.todo.service.impl;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import late.todo.service.IIPRemoteService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @projectName todo
 * @packageName late.todo.service.impl
 * @fileName IIPRemoteServiceImpl.java
 * @author chijingjia
 * @createTime :2019年1月4日 上午11:48:24
 * @version: v1.0
 */
@Slf4j
@Service
public class IIPRemoteServiceImpl implements IIPRemoteService {
	@Autowired
	RestTemplate restTemplate;
	@Value(value = "${late.ip.url}")
	String remoteUrl;
	@Value(value = "${late.ip.mark}")
	String mark;

	@Override
	public String getInternetIP() {
		String aa = restTemplate.getForObject(remoteUrl, String.class);
		aa=aa.substring(aa.indexOf("[")+1,aa.indexOf("]"));
		return aa;
	}

}

package org.ares.app.demo.common.cfg;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.ares.app.demo.common.mvc.filter.DemoFilter;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DemoBeanCfg {

	@Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(3000);
        requestFactory.setConnectTimeout(3000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        List<MediaType> smt=new ArrayList<>();
        smt.add(MediaType.TEXT_HTML);
        smt.add(MediaType.APPLICATION_JSON_UTF8);
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        StringHttpMessageConverter smc=new StringHttpMessageConverter(Charset.forName("UTF-8"));
        smc.setSupportedMediaTypes(smt);
        messageConverters.add(smc);
        MappingJackson2HttpMessageConverter jmc=new MappingJackson2HttpMessageConverter();
        jmc.setSupportedMediaTypes(smt);
        messageConverters.add(jmc);
        restTemplate.setMessageConverters(messageConverters);
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }
	
	@Bean
    public StringEncryptor encryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();  
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();  
        config.setAlgorithm("PBEWithMD5AndDES");  
        config.setPassword("ares@2017");  
        encryptor.setConfig(config);
        config.setStringOutputType("base64");
        return encryptor;
    }

	@Bean
	public Filter demoFilter(){
		return new DemoFilter();
	}
}

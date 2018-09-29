package org.troublert.configuration;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.spring.JaxRsConfig;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.troublert.services.GetData;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Configuration
@Import(JaxRsConfig.class) 
public class CxfConfig {
	
	@Autowired
	public CamelContext camelContext;
	
	@Autowired
	GetData getData;
	
	@Bean
	public ProducerTemplate producerTemplate() {
		return this.camelContext.createProducerTemplate();
	}
	
	@Bean
    public JAXRSServerFactoryBean rsServer() {
        final JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
        factory.setServiceBean(getData);
        factory.setAddress("/troublet");
        JacksonJsonProvider jacksonProvider = new JacksonJsonProvider();
        jacksonProvider.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS , false);
        factory.setProvider(jacksonProvider);
        return factory;
    }
	@Bean
	public ServletRegistrationBean cxfServlet() {
		final ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new CXFServlet(),
				"/services/*");
		servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
	}

}

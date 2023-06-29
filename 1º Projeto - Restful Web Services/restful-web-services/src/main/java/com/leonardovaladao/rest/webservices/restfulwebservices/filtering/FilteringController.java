package com.leonardovaladao.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping("/filter")
public class FilteringController {

	@GetMapping
	private SomeBean filtering() {		
		return new SomeBean("value1", "value2", "value3");
	}
	
	@GetMapping("/list")
	private List<SomeBean> filteringList() {
		return Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value4", "value5", "value6"));
	}
	
	@GetMapping("/dynamic")
	private MappingJacksonValue filteringWithDynamicFilter() {		
		SomeBean bean = new SomeBean("value1", "value2", "value3");
		
		MappingJacksonValue mapping = jacksonMapping(Arrays.asList(bean), Arrays.asList("field1"));
		
		return mapping;
	}
	
	@GetMapping("/list/dynamic")
	private MappingJacksonValue filteringListWithDynamicFilter() {
		List<SomeBean> beans = Arrays.asList(new SomeBean("value1-a", "value2-a", "value3-a"),
				new SomeBean("value1-b", "value2-b", "value3-b"));
		
		MappingJacksonValue mapping = jacksonMapping(beans, Arrays.asList("field2", "field3"));
				
		return mapping;
	}
	
	private MappingJacksonValue jacksonMapping(List<SomeBean> beans, List<String> fields) {
		if (beans.size() <= 0 || fields.size() <= 0) {
			throw new IllegalArgumentException("The 'beans' and 'fields' arguments should have at least 1 item on each list!");
		}
		
		MappingJacksonValue mapping;
		if (beans.size() > 1) {
			mapping = new MappingJacksonValue(beans);
		} else {
			mapping = new MappingJacksonValue(beans.get(0));
		}
		
		SimpleBeanPropertyFilter filter;
		if (beans.size() > 1) {
			filter = SimpleBeanPropertyFilter.filterOutAllExcept(new HashSet<String>(fields));
		} else {
			filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields.get(0));
		}

		FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter);
		mapping.setFilters(filters);
		
		return mapping;
	}
}

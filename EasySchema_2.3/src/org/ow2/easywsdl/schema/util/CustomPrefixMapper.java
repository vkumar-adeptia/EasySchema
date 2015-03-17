package org.ow2.easywsdl.schema.util;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public class CustomPrefixMapper extends NamespacePrefixMapper {

	public CustomPrefixMapper()
	{}
	
	@Override
	public String getPreferredPrefix(String namespaceUri, String suggestion,
			boolean requirePrefix) {
		
		if (namespaceUri.equals("http://www.w3.org/2001/XMLSchema"))
		{
			return "xs";
		}		
		
		return suggestion;
	}

}

/**
 * easySchema - easyWSDL toolbox Platform.
 * Copyright (c) 2008,  eBM Websourcing
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the University of California, Berkeley nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE REGENTS AND CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.ow2.easywsdl.schema.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.ow2.easywsdl.schema.api.Include;
import org.ow2.easywsdl.schema.api.Schema;
import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.SchemaReader.FeatureConstants;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSchema;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractIncludeImpl;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaElementImpl;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaReader;

import com.ebmwebsourcing.easycommons.uri.UriManager;

/**
 * @author Nicolas Salatge - eBM WebSourcing
 */
public class IncludeImpl extends AbstractIncludeImpl<org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Include, Schema> implements Include {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param incl
	 * @param features
	 * @param imports
	 * @param reader
	 * @throws SchemaException
     * @throws URISyntaxException
     *             If the schemaLocation attribute of the include is not a valid
     *             URI.
	 */
	public IncludeImpl(final org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Include incl, final Map<FeatureConstants, Object> features, final Map<URI, AbsItfSchema> imports, final URI baseURI, final AbstractSchemaReader reader) throws SchemaException, URISyntaxException {
		super(incl, features, imports, baseURI, reader);

		// get the documentation
		// this.documentation = new
		// org.ow2.easywsdl.schema.impl.DocumentationImpl
		// (this.model.getDocumentation());
	}

	/**
	 * @param incl
	 * @param parent
	 * @param imports
	 * @param reader
	 * @throws SchemaException
     * @throws URISyntaxException
     *             If the schemaLocation attribute of the include is not a valid
     *             URI.
	 */
	public IncludeImpl(final org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Include incl, final AbstractSchemaElementImpl parent, final Map<URI, AbsItfSchema> imports, final URI baseURI, AbstractSchemaReader reader) throws SchemaException, URISyntaxException {
		super(incl, parent, imports, baseURI, reader);

		// get the documentation
		// this.documentation = new
		// org.ow2.easywsdl.schema.impl.DocumentationImpl
		// (this.model.getDocumentation());
	}

	/**
     * {@inheritDoc}
     */
    public URI getLocationURI() {
        if (this.model.getSchemaLocation() != null) {
            return UriManager.filePathToUri(this.model.getSchemaLocation());
        } else {
            return null;
        }
    }

	public void setLocationURI(final URI locationURI) {
        this.model.setSchemaLocation(locationURI.toString());
	}

}

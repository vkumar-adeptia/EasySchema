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
package org.ow2.easywsdl.schema.api.abstractElmt;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.logging.Logger;

import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.SchemaReader.FeatureConstants;
import org.ow2.easywsdl.schema.api.absItf.AbsItfInclude;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSchema;
import org.ow2.easywsdl.schema.impl.IncludeImpl;
import org.ow2.easywsdl.schema.impl.SchemaImpl;

/**
 * @author Nicolas Salatge - eBM WebSourcing
 */
public abstract class AbstractIncludeImpl<E, S extends AbsItfSchema> extends AbstractSchemaElementImpl<E> implements AbsItfInclude<S> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(AbstractIncludeImpl.class.getName());

	private final Map<FeatureConstants, Object> features;
	private final Map<URI, AbsItfSchema> imports;
	private final URI baseURI;
	private final AbstractSchemaReader reader;
	
	private S schema;

	
	
	private AbstractIncludeImpl(final E model, final Map<FeatureConstants, Object> features, final AbstractSchemaElementImpl parent, final Map<URI, AbsItfSchema> imports, final URI baseURI, final AbstractSchemaReader reader) throws SchemaException {
		super(model, parent);
		if (features != null) {
			this.features = features;
		} else if (parent != null) {
			this.features = ((AbstractSchemaImpl) this.parent).getFeatures();
		} else {
			this.features = null;
		}
		this.imports = imports;
		this.baseURI = baseURI;
		this.reader = reader;

		// force include retrieval at construction time.
//		getSchema();
	}


    /**
     * Default constructor
     *
     * @param model
     *            the model
     * @param parent
     *            the parent description
     */
	public AbstractIncludeImpl(final E model, final Map<FeatureConstants, Object> features, final Map<URI, AbsItfSchema> imports, final URI baseURI, final AbstractSchemaReader reader) throws SchemaException {
		this(model, features, null, imports, baseURI, reader);
	}

	
	/**
	 * Default constructor
	 *
	 * @param model
	 *            the model
	 * @param parent
	 *            the parent description
	 */
	public AbstractIncludeImpl(final E model, final AbstractSchemaElementImpl parent, final Map<URI, AbsItfSchema> imports, final URI baseURI, final AbstractSchemaReader reader) throws SchemaException {
		this(model, null, parent, imports, baseURI, reader);
	}

	private void retrieveInclude(final URI schemaLocation, final Map<FeatureConstants, Object> features, final Map<URI, AbsItfSchema> imports, final URI baseURI, final AbstractSchemaReader reader) throws SchemaException {
		assert imports != null;
		if (schemaLocation == null) return; 
		
		try {
			// Try to identify a cyclic import loop
			if (!imports.containsKey(schemaLocation)) {
				S externalSchema = (S) reader.readExternalPart(schemaLocation, baseURI, imports);
				imports.put(schemaLocation, externalSchema);
				if(this instanceof IncludeImpl)
					((SchemaImpl)externalSchema).setTargetNamespace(((SchemaImpl)this.getParent()).getTargetNamespace());
				((AbstractSchemaImpl) externalSchema).initialize();
			}
			this.schema = (S) imports.get(schemaLocation);

		} catch (final SchemaException e) {
			throw new SchemaException("the imported document cannot be import at: " + schemaLocation.toString(), e);
		} catch (final URISyntaxException e) {
			throw new SchemaException("the imported document cannot be import at: " + schemaLocation.toString() + " because the URI is invalid", e);
		} catch (final MalformedURLException e) {
            throw new SchemaException("the imported document cannot be import at: " + schemaLocation.toString() + " because the URI is invalid", e);
        }
	}

	
	private boolean haveImportDocumentsFeature() {
		if (features == null) return false;
		Boolean importDocuments = (Boolean) features.get(FeatureConstants.IMPORT_DOCUMENTS);
		if (importDocuments == null) return false;
		return importDocuments.booleanValue();
	}
	
	
	/**
	 * @return the parent
	 */
	public S getSchema() {
		if (this.schema == null) {
			if (haveImportDocumentsFeature()) {
				try {
					
					this.retrieveInclude(getLocationURI(), features, imports, baseURI, reader);
				} catch (SchemaException se) {
					throw new RuntimeException(se);
				}
			}
	
			if (this.schema != null) {
				((AbstractSchemaImpl) this.schema).setFeatures(features);
			}
		}
		return this.schema;
	}

	public void setSchema(final S schema) {
		this.schema = schema;
		if(this.schema != null) {
			((AbstractSchemaImpl)this.getParentSchema()).addIncludeElementsInAllList(this);
		}
	}

	/**
	 * @return the parent parent
	 */
	public S getParentSchema() {
		return (S) this.parent.getSchema();
	}

}

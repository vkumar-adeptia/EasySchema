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

import java.math.BigInteger;

import javax.xml.namespace.QName;

import org.ow2.easywsdl.schema.SchemaFactory;
import org.ow2.easywsdl.schema.api.Element;
import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.Type;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractElementImpl;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaElementImpl;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.FormChoice;


/**
 * @author Nicolas Salatge - eBM WebSourcing
 */
public class ElementImpl extends
AbstractElementImpl<org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Element, Type>
implements Element {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

        private QName qname = null;

	/**
	 *
	 * @param model
	 * @param parent
	 * @throws SchemaException
	 */
	public ElementImpl(
			final org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Element model,
			final AbstractSchemaElementImpl parent) {
		super(model, parent);

		findType();

		//findReferencedElementIfExist();
	}

	private void findType() {
		// if anonymous element
		if (this.model.getComplexType() != null) {
			this.type = new ComplexTypeImpl(this.model.getComplexType(), parent);
		} else if (this.model.getSimpleType() != null) {
			this.type = new SimpleTypeImpl(this.model.getSimpleType(), parent);
		} else if (this.model.getType() != null) {
			this.type = (Type) this.parent.getSchema().getType(this.model.getType());
			if (this.type == null && SchemaFactory.getDefaultSchema() != null) {
				this.type = (Type) SchemaFactory.getDefaultSchema().getType(this.model.getType());
			}
			if(this.type == null)
				this.type = (Type) this.parent.getSchema().getType(new QName(this.getSchema().getTargetNamespace(),this.model.getType().getLocalPart()));
		}
	}

	public void findReferencedElementIfExist() {
		// if referenced element
		if (this.model.getRef() != null) {
			this.referencedElement = this.parent.getSchema().getElement(this.model.getRef());
			if(this.referencedElement != null) {
				this.type = (Type) this.referencedElement.getType();
			}
		}
	}

	public QName getQName() {
            if (qname != null) return qname;

            if (this.model.getRef() != null) {
                // The element is defined by reference
                qname = this.model.getRef();
                if (this.referencedElement == null) {
                    findReferencedElementIfExist();
                }
            }
            else if (this.model.getName() != null) {
                // The element is defined inlined
                final String prefix = this.parent.getSchema().getAllNamespaces().getPrefix(
                                                                                           this.parent.getSchema().getTargetNamespace());
                if (prefix != null) {
                    qname = new QName(this.parent.getSchema().getTargetNamespace(), this.model.getName(), prefix);
                } else {
                    qname = new QName(this.parent.getSchema().getTargetNamespace(), this.model.getName());
                }
            }
            // Else the XSD is not valid, so we return null.

            return qname;
	}

	@Override
	public Type getType() {
		if(this.type == null) {
			this.findType();
		}
		return super.getType();
	}

	@Override
	public void setType(Type type) {
		super.setType(type);
		this.model.setType(type.getQName());
	}

	public void setQName(QName name) {
		this.model.setName(name.getLocalPart());
	}

	public String getMaxOccurs() {
		return this.model.getMaxOccurs();
	}

	public int getMinOccurs() {
		return this.model.getMinOccurs().intValue();
	}

	public void setMaxOccurs(String max) {
		this.model.setMaxOccurs(max);
	}

	public void setMinOccurs(int min) {
		this.model.setMinOccurs(BigInteger.valueOf(min));
	}

	public QName getRef() {
		return this.model.getRef();
	}

	public void setRef(QName name) {
		this.model.setRef(name);
		findReferencedElementIfExist();
	}

	public boolean isNillable() {
		return this.model.isNillable();
	}

	public void setNillable(boolean nill) {
		this.model.setNillable(nill);
	}

	public FormChoice getForm() {
		FormChoice res = this.model.getForm();
		if(res == null) {
			res = this.getParent().getSchema().getElementFormDefault();
		}
		return res;
	}

}

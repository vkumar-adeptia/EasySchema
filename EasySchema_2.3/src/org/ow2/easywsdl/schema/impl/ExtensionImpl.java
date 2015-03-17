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

import org.ow2.easywsdl.schema.api.Extension;
import org.ow2.easywsdl.schema.api.Sequence;
import org.ow2.easywsdl.schema.api.Type;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractExtensionImpl;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaElementImpl;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.ExplicitGroup;

/**
 * @author Nicolas Salatge - eBM WebSourcing
 */
public class ExtensionImpl
        extends
        AbstractExtensionImpl<org.ow2.easywsdl.schema.org.w3._2001.xmlschema.ExtensionType, Type, Sequence>
        implements Extension {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;

    public ExtensionImpl(
            final org.ow2.easywsdl.schema.org.w3._2001.xmlschema.ExtensionType model,
            final AbstractSchemaElementImpl parent) {
        super(model, parent);

        // get the base
        if(this.model.getBase() != null) {
        	this.baseType = (Type) this.getSchema().getType(this.model.getBase());
        }

        // get the sequence
        if(this.model.getSequence() != null) {
        	this.sequence = new SequenceImpl(this.model.getSequence(), this);
        }
        
    }

    public Type getBase() {
        return this.baseType;
    }

    public void setBase(Type base) {
    	this.baseType = base;
    	this.model.setBase(this.baseType.getQName());
    }
    

    @Override
	public void setSequence(Sequence sequence) {
		super.setSequence(sequence);
		this.model.setSequence((ExplicitGroup) ((AbstractSchemaElementImpl)this.sequence).getModel());
	}


	public Sequence createSequence() {
		return new SequenceImpl(new ExplicitGroup(), this);
	}

}

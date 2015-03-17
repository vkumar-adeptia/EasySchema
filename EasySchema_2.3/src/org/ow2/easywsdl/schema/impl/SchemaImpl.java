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
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.NotImplementedException;
import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.SchemaReader;
import org.ow2.easywsdl.schema.api.Type;
import org.ow2.easywsdl.schema.api.absItf.AbsItfSchema;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaElementImpl;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaImpl;
import org.ow2.easywsdl.schema.api.extensions.NamespaceMapperImpl;
import org.ow2.easywsdl.schema.api.extensions.SchemaLocatorImpl;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Annotated;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.FormChoice;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.NamedAttributeGroup;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.NamedGroup;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.OpenAttrs;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.TopLevelComplexType;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.TopLevelElement;
import org.ow2.easywsdl.schema.org.w3._2001.xmlschema.TopLevelSimpleType;

public class SchemaImpl extends AbstractSchemaImpl<org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema, Type, org.ow2.easywsdl.schema.api.Element, org.ow2.easywsdl.schema.api.Attribute, org.ow2.easywsdl.schema.api.Include, org.ow2.easywsdl.schema.api.Import, org.ow2.easywsdl.schema.api.Redefine>
  implements org.ow2.easywsdl.schema.api.Schema
{
  private static final long serialVersionUID = 1L;
  private SchemaReaderImpl reader;

  /**
   * @param documentURI
   * @param schema
   * @param namespaceMapper
   * @param schemaLocator
   * @param features
   * @param imports
   * @param reader
   * @throws SchemaException
   * @throws URISyntaxException
   *             If a schemaLocation attribute of an import, include or
   *             redefine is not a valid URI.
   */
  public SchemaImpl(URI documentURI, org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema schema, NamespaceMapperImpl namespaceMapper, SchemaLocatorImpl schemaLocator, Map<SchemaReader.FeatureConstants, Object> features, Map<URI, AbsItfSchema> imports, SchemaReaderImpl reader)
    throws SchemaException, URISyntaxException
  {
    super(documentURI, schema, namespaceMapper, schemaLocator);

    setFeatures(features);
    this.model = schema;

    this.reader = reader;

    if (reader == null) {
      throw new SchemaException("schema reader cannot be null");
    }

    for (OpenAttrs item : ((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema)this.model).getIncludeOrImportOrRedefine())
    {
      if ((item instanceof org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Import)) {
        org.ow2.easywsdl.schema.api.Import impt = new ImportImpl((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Import)item, this, imports, getDocumentURI(), reader);
        getImports().add(impt);
      }

      if ((item instanceof org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Include)) {
        org.ow2.easywsdl.schema.api.Include incl = new IncludeImpl((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Include)item, this, imports, getDocumentURI(), reader);
        getIncludes().add(incl);
      }

      if ((item instanceof org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Redefine)) {
        org.ow2.easywsdl.schema.api.Redefine red = new RedefineImpl((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Redefine)item, this, imports, getDocumentURI(), reader);
        getRedefines().add(red);
      }
    }
  }

  public SchemaImpl() throws SchemaException
  {
    super("./");
    this.reader = new SchemaReaderImpl();
    this.model = new org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema();
    getAllNamespaces().addNamespace("xsd", "http://www.w3.org/2001/XMLSchema");
  }

  public void initialize()
  {
    for (Annotated item : ((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema)this.model).getSimpleTypeOrComplexTypeOrGroup())
    {
      if ((item instanceof org.ow2.easywsdl.schema.org.w3._2001.xmlschema.SimpleType)) {
        org.ow2.easywsdl.schema.api.SimpleType st = new SimpleTypeImpl((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.SimpleType)item, this);
        getTypes().add(st);
      }

      if ((item instanceof org.ow2.easywsdl.schema.org.w3._2001.xmlschema.ComplexType)) {
        ComplexTypeImpl ct = new ComplexTypeImpl((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.ComplexType)item, this);
        getTypes().add(ct);
      }
    }

    for (Annotated item : ((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema)this.model).getSimpleTypeOrComplexTypeOrGroup())
    {
      if ((item instanceof org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Element)) {
        ElementImpl e = new ElementImpl((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Element)item, this);
        if(e.getType()!=null && e.getType().toString().equals("anonymous type")){
//        	if(e.getType().getQName()==null)
//        		e.getType().setQName(new QName("anonymous type"));
        	getTypes().add(e.getType());
        }
        getElements().add(e);
      }

      if ((item instanceof org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Attribute)) {
        AttributeImpl a = new AttributeImpl((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Attribute)item, this);
        getAttributes().add(a);
      }
      if ((item instanceof NamedAttributeGroup)) {
        getAttributeGroups().add(item);
      }
      if ((item instanceof NamedGroup)) {
        getNamedGroups().add(item);
      }
    }
    addImportElementsInAllList();
    addIncludeElementsInAllList();
    addRedefineElementsInAllList();
    for (org.ow2.easywsdl.schema.api.Element elmt : getElements()) {
      ((ElementImpl)elmt).findReferencedElementIfExist();
    }

    for (Type type : getTypes())
      if ((type instanceof org.ow2.easywsdl.schema.api.ComplexType))
        ((ComplexTypeImpl)type).findReferencedElementIfExist();
  }

  public String getTargetNamespace()
  {
    return ((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema)this.model).getTargetNamespace();
  }

  public FormChoice getElementFormDefault() {
    return ((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema)this.model).getElementFormDefault();
  }

  public FormChoice getAttributeFormDefault() {
    return ((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema)this.model).getAttributeFormDefault();
  }

  public String getLang() {
    return ((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema)this.model).getLang();
  }

  public String getVersion() {
    return ((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema)this.model).getVersion();
  }

  public List<String> getBlockDefault() {
    return ((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema)this.model).getBlockDefault();
  }

  public List<String> getFinalDefault() {
    return ((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema)this.model).getFinalDefault();
  }

  public void addAttribute(org.ow2.easywsdl.schema.api.Attribute att)
  {
    super.addAttribute(att);
    ((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema)this.model).getSimpleTypeOrComplexTypeOrGroup().add((Annotated)((AbstractSchemaElementImpl)att).getModel());
  }

  public void addImport(org.ow2.easywsdl.schema.api.Import impt)
  {
    super.addImport(impt);
    ((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema)this.model).getIncludeOrImportOrRedefine().add((Annotated)((AbstractSchemaElementImpl)impt).getModel());
  }

  public void addInclude(org.ow2.easywsdl.schema.api.Include incl) throws SchemaException
  {
    super.addInclude(incl);
    ((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema)this.model).getIncludeOrImportOrRedefine().add((Annotated)((AbstractSchemaElementImpl)incl).getModel());
  }

  public void addRedefine(org.ow2.easywsdl.schema.api.Redefine red)
  {
    super.addRedefine(red);
    ((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema)this.model).getIncludeOrImportOrRedefine().add((Annotated)((AbstractSchemaElementImpl)red).getModel());
  }

  public void addElement(org.ow2.easywsdl.schema.api.Element elmt)
  {
    super.addElement(elmt);
    ((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema)this.model).getSimpleTypeOrComplexTypeOrGroup().add((Annotated)((AbstractSchemaElementImpl)elmt).getModel());
  }

  public void addType(Type type)
  {
    super.addType(type);
    ((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema)this.model).getSimpleTypeOrComplexTypeOrGroup().add((Annotated)((AbstractSchemaElementImpl)type).getModel());
  }

  public org.ow2.easywsdl.schema.api.Import removeImport(org.ow2.easywsdl.schema.api.Import importDef) {
    throw new NotImplementedException();
  }

  public org.ow2.easywsdl.schema.api.Include removeInclude(org.ow2.easywsdl.schema.api.Include includeDef) throws SchemaException {
    throw new NotImplementedException();
  }

  public org.ow2.easywsdl.schema.api.Redefine removeRedefine(org.ow2.easywsdl.schema.api.Redefine redefineDef) {
    throw new NotImplementedException();
  }

  public org.ow2.easywsdl.schema.api.Attribute createAttribute() {
    return new AttributeImpl(new org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Attribute(), this);
  }

  public Type createComplexType() {
    return new ComplexTypeImpl(new TopLevelComplexType(), this);
  }

  public org.ow2.easywsdl.schema.api.Element createElement() {
    return new ElementImpl(new TopLevelElement(), this);
  }

  public Type createSimpleType() {
    return new SimpleTypeImpl(new TopLevelSimpleType(), this);
  }

  public void setTargetNamespace(String tns) {
    ((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema)this.model).setTargetNamespace(tns);
  }

  public org.ow2.easywsdl.schema.api.Import createImport() throws SchemaException {
    try {
      return new ImportImpl(new org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Import(), this, null, getDocumentURI(), this.reader);
    }
    catch (URISyntaxException e) {
      throw new SchemaException(e);
    }
  }

  public org.ow2.easywsdl.schema.api.Include createInclude() throws SchemaException {
    try {
      return new IncludeImpl(new org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Include(), this, null, getDocumentURI(), this.reader);
    }
    catch (URISyntaxException e) {
      throw new SchemaException(e);
    }
  }

  public org.ow2.easywsdl.schema.api.Redefine createRedefine() throws SchemaException {
    try {
      return new RedefineImpl(new org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Redefine(), this, null, getDocumentURI(), this.reader);
    }
    catch (URISyntaxException e) {
      throw new SchemaException(e);
    }
  }

  public void setAttributeFormDefault(FormChoice form) {
    ((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema)this.model).setAttributeFormDefault(form);
  }

  public void setElementFormDefault(FormChoice form) {
    ((org.ow2.easywsdl.schema.org.w3._2001.xmlschema.Schema)this.model).setElementFormDefault(form);
  }
}
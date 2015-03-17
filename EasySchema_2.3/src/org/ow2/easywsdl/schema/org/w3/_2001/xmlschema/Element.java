//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-792 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.09.23 at 09:27:55 AM CEST 
//


package org.ow2.easywsdl.schema.org.w3._2001.xmlschema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Copyable;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.builder.CopyBuilder;
import org.jvnet.jaxb2_commons.lang.builder.JAXBCopyBuilder;
import org.jvnet.jaxb2_commons.lang.builder.JAXBEqualsBuilder;
import org.jvnet.jaxb2_commons.lang.builder.JAXBHashCodeBuilder;
import org.jvnet.jaxb2_commons.lang.builder.JAXBToStringBuilder;


/**
 * 
 *    The element element can be used either
 *    at the top level to define an element-type binding globally,
 *    or within a content model to either reference a globally-defined
 *    element or type or declare an element-type binding locally.
 *    The ref form is not allowed at the top level.
 * 
 * <p>Java class for element complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="element">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.w3.org/2001/XMLSchema}annotated">
 *       &lt;sequence>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="simpleType" type="{http://www.w3.org/2001/XMLSchema}localSimpleType"/>
 *           &lt;element name="complexType" type="{http://www.w3.org/2001/XMLSchema}localComplexType"/>
 *         &lt;/choice>
 *         &lt;group ref="{http://www.w3.org/2001/XMLSchema}identityConstraint" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.w3.org/2001/XMLSchema}defRef"/>
 *       &lt;attGroup ref="{http://www.w3.org/2001/XMLSchema}occurs"/>
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}QName" />
 *       &lt;attribute name="substitutionGroup" type="{http://www.w3.org/2001/XMLSchema}QName" />
 *       &lt;attribute name="default" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="fixed" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="nillable" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="abstract" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="final" type="{http://www.w3.org/2001/XMLSchema}derivationSet" />
 *       &lt;attribute name="block" type="{http://www.w3.org/2001/XMLSchema}blockSet" />
 *       &lt;attribute name="form" type="{http://www.w3.org/2001/XMLSchema}formChoice" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "element", propOrder = {
    "simpleType",
    "complexType",
    "identityConstraint"
})
@XmlSeeAlso({
    TopLevelElement.class,
    LocalElement.class
})
public abstract class Element
    extends Annotated
    implements CopyTo, Copyable, Equals, HashCode, ToString
{

    protected LocalSimpleType simpleType;
    protected LocalComplexType complexType;
    @XmlElementRefs({
        @XmlElementRef(name = "key", namespace = "http://www.w3.org/2001/XMLSchema", type = JAXBElement.class),
        @XmlElementRef(name = "keyref", namespace = "http://www.w3.org/2001/XMLSchema", type = Keyref.class),
        @XmlElementRef(name = "unique", namespace = "http://www.w3.org/2001/XMLSchema", type = JAXBElement.class)
    })
    protected List<Object> identityConstraint;
    @XmlAttribute
    protected QName type;
    @XmlAttribute
    protected QName substitutionGroup;
    @XmlAttribute(name = "default")
    protected String _default;
    @XmlAttribute
    protected String fixed;
    @XmlAttribute
    protected Boolean nillable;
    @XmlAttribute(name = "abstract")
    protected Boolean _abstract;
    @XmlAttribute(name = "final")
    @XmlSchemaType(name = "derivationSet")
    protected List<String> _final;
    @XmlAttribute
    @XmlSchemaType(name = "blockSet")
    protected List<String> block;
    @XmlAttribute
    protected FormChoice form;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String name;
    @XmlAttribute
    protected QName ref;
    @XmlAttribute
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger minOccurs;
    @XmlAttribute
    @XmlSchemaType(name = "allNNI")
    protected String maxOccurs;

    /**
     * Gets the value of the simpleType property.
     * 
     * @return
     *     possible object is
     *     {@link LocalSimpleType }
     *     
     */
    public LocalSimpleType getSimpleType() {
        return simpleType;
    }

    /**
     * Sets the value of the simpleType property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalSimpleType }
     *     
     */
    public void setSimpleType(LocalSimpleType value) {
        this.simpleType = value;
    }

    /**
     * Gets the value of the complexType property.
     * 
     * @return
     *     possible object is
     *     {@link LocalComplexType }
     *     
     */
    public LocalComplexType getComplexType() {
        return complexType;
    }

    /**
     * Sets the value of the complexType property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalComplexType }
     *     
     */
    public void setComplexType(LocalComplexType value) {
        this.complexType = value;
    }

    /**
     * Gets the value of the identityConstraint property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identityConstraint property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentityConstraint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Keyref }
     * {@link JAXBElement }{@code <}{@link Keybase }{@code >}
     * {@link JAXBElement }{@code <}{@link Keybase }{@code >}
     * 
     * 
     */
    public List<Object> getIdentityConstraint() {
        if (identityConstraint == null) {
            identityConstraint = new ArrayList<Object>();
        }
        return this.identityConstraint;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setType(QName value) {
        this.type = value;
    }

    /**
     * Gets the value of the substitutionGroup property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getSubstitutionGroup() {
        return substitutionGroup;
    }

    /**
     * Sets the value of the substitutionGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setSubstitutionGroup(QName value) {
        this.substitutionGroup = value;
    }

    /**
     * Gets the value of the default property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefault() {
        return _default;
    }

    /**
     * Sets the value of the default property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefault(String value) {
        this._default = value;
    }

    /**
     * Gets the value of the fixed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFixed() {
        return fixed;
    }

    /**
     * Sets the value of the fixed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFixed(String value) {
        this.fixed = value;
    }

    /**
     * Gets the value of the nillable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isNillable() {
        if (nillable == null) {
            return false;
        } else {
            return nillable;
        }
    }

    /**
     * Sets the value of the nillable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNillable(Boolean value) {
        this.nillable = value;
    }

    /**
     * Gets the value of the abstract property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isAbstract() {
        if (_abstract == null) {
            return false;
        } else {
            return _abstract;
        }
    }

    /**
     * Sets the value of the abstract property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAbstract(Boolean value) {
        this._abstract = value;
    }

    /**
     * Gets the value of the final property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the final property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFinal().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFinal() {
        if (_final == null) {
            _final = new ArrayList<String>();
        }
        return this._final;
    }

    /**
     * Gets the value of the block property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the block property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBlock().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getBlock() {
        if (block == null) {
            block = new ArrayList<String>();
        }
        return this.block;
    }

    /**
     * Gets the value of the form property.
     * 
     * @return
     *     possible object is
     *     {@link FormChoice }
     *     
     */
    public FormChoice getForm() {
        return form;
    }

    /**
     * Sets the value of the form property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormChoice }
     *     
     */
    public void setForm(FormChoice value) {
        this.form = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the ref property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getRef() {
        return ref;
    }

    /**
     * Sets the value of the ref property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setRef(QName value) {
        this.ref = value;
    }

    /**
     * Gets the value of the minOccurs property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinOccurs() {
        if (minOccurs == null) {
            return new BigInteger("1");
        } else {
            return minOccurs;
        }
    }

    /**
     * Sets the value of the minOccurs property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinOccurs(BigInteger value) {
        this.minOccurs = value;
    }

    /**
     * Gets the value of the maxOccurs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxOccurs() {
        if (maxOccurs == null) {
            return "1";
        } else {
            return maxOccurs;
        }
    }

    /**
     * Sets the value of the maxOccurs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxOccurs(String value) {
        this.maxOccurs = value;
    }

    public void toString(ToStringBuilder toStringBuilder) {
        super.toString(toStringBuilder);
        {
            LocalSimpleType theSimpleType;
            theSimpleType = this.getSimpleType();
            toStringBuilder.append("simpleType", theSimpleType);
        }
        {
            LocalComplexType theComplexType;
            theComplexType = this.getComplexType();
            toStringBuilder.append("complexType", theComplexType);
        }
        {
            List<Object> theIdentityConstraint;
            theIdentityConstraint = this.getIdentityConstraint();
            toStringBuilder.append("identityConstraint", theIdentityConstraint);
        }
        {
            QName theType;
            theType = this.getType();
            toStringBuilder.append("type", theType);
        }
        {
            QName theSubstitutionGroup;
            theSubstitutionGroup = this.getSubstitutionGroup();
            toStringBuilder.append("substitutionGroup", theSubstitutionGroup);
        }
        {
            String theDefault;
            theDefault = this.getDefault();
            toStringBuilder.append("_default", theDefault);
        }
        {
            String theFixed;
            theFixed = this.getFixed();
            toStringBuilder.append("fixed", theFixed);
        }
        {
            Boolean theNillable;
            theNillable = this.isNillable();
            toStringBuilder.append("nillable", theNillable);
        }
        {
            Boolean theAbstract;
            theAbstract = this.isAbstract();
            toStringBuilder.append("_abstract", theAbstract);
        }
        {
            List<String> theFinal;
            theFinal = this.getFinal();
            toStringBuilder.append("_final", theFinal);
        }
        {
            List<String> theBlock;
            theBlock = this.getBlock();
            toStringBuilder.append("block", theBlock);
        }
        {
            FormChoice theForm;
            theForm = this.getForm();
            toStringBuilder.append("form", theForm);
        }
        {
            String theName;
            theName = this.getName();
            toStringBuilder.append("name", theName);
        }
        {
            QName theRef;
            theRef = this.getRef();
            toStringBuilder.append("ref", theRef);
        }
        {
            BigInteger theMinOccurs;
            theMinOccurs = this.getMinOccurs();
            toStringBuilder.append("minOccurs", theMinOccurs);
        }
        {
            String theMaxOccurs;
            theMaxOccurs = this.getMaxOccurs();
            toStringBuilder.append("maxOccurs", theMaxOccurs);
        }
    }

    public String toString() {
        final ToStringBuilder toStringBuilder = new JAXBToStringBuilder(this);
        toString(toStringBuilder);
        return toStringBuilder.toString();
    }

    public void equals(Object object, EqualsBuilder equalsBuilder) {
        if (!(object instanceof Element)) {
            equalsBuilder.appendSuper(false);
            return ;
        }
        if (this == object) {
            return ;
        }
        super.equals(object, equalsBuilder);
        final Element that = ((Element) object);
        equalsBuilder.append(this.getSimpleType(), that.getSimpleType());
        equalsBuilder.append(this.getComplexType(), that.getComplexType());
        equalsBuilder.append(this.getIdentityConstraint(), that.getIdentityConstraint());
        equalsBuilder.append(this.getType(), that.getType());
        equalsBuilder.append(this.getSubstitutionGroup(), that.getSubstitutionGroup());
        equalsBuilder.append(this.getDefault(), that.getDefault());
        equalsBuilder.append(this.getFixed(), that.getFixed());
        equalsBuilder.append(this.isNillable(), that.isNillable());
        equalsBuilder.append(this.isAbstract(), that.isAbstract());
        equalsBuilder.append(this.getFinal(), that.getFinal());
        equalsBuilder.append(this.getBlock(), that.getBlock());
        equalsBuilder.append(this.getForm(), that.getForm());
        equalsBuilder.append(this.getName(), that.getName());
        equalsBuilder.append(this.getRef(), that.getRef());
        equalsBuilder.append(this.getMinOccurs(), that.getMinOccurs());
        equalsBuilder.append(this.getMaxOccurs(), that.getMaxOccurs());
    }

    public boolean equals(Object object) {
        if (!(object instanceof Element)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final EqualsBuilder equalsBuilder = new JAXBEqualsBuilder();
        equals(object, equalsBuilder);
        return equalsBuilder.isEquals();
    }

    public void hashCode(HashCodeBuilder hashCodeBuilder) {
        super.hashCode(hashCodeBuilder);
        hashCodeBuilder.append(this.getSimpleType());
        hashCodeBuilder.append(this.getComplexType());
        hashCodeBuilder.append(this.getIdentityConstraint());
        hashCodeBuilder.append(this.getType());
        hashCodeBuilder.append(this.getSubstitutionGroup());
        hashCodeBuilder.append(this.getDefault());
        hashCodeBuilder.append(this.getFixed());
        hashCodeBuilder.append(this.isNillable());
        hashCodeBuilder.append(this.isAbstract());
        hashCodeBuilder.append(this.getFinal());
        hashCodeBuilder.append(this.getBlock());
        hashCodeBuilder.append(this.getForm());
        hashCodeBuilder.append(this.getName());
        hashCodeBuilder.append(this.getRef());
        hashCodeBuilder.append(this.getMinOccurs());
        hashCodeBuilder.append(this.getMaxOccurs());
    }

    public int hashCode() {
        final HashCodeBuilder hashCodeBuilder = new JAXBHashCodeBuilder();
        hashCode(hashCodeBuilder);
        return hashCodeBuilder.toHashCode();
    }

    public Object copyTo(Object target, CopyBuilder copyBuilder) {
        if (null == target) {
            throw new IllegalArgumentException("Target argument must not be null for abstract copyable classes.");
        }
        final Element copy = ((Element) target);
        super.copyTo(copy, copyBuilder);
        {
            LocalSimpleType sourceSimpleType;
            sourceSimpleType = this.getSimpleType();
            LocalSimpleType copySimpleType = ((LocalSimpleType) copyBuilder.copy(sourceSimpleType));
            copy.setSimpleType(copySimpleType);
        }
        {
            LocalComplexType sourceComplexType;
            sourceComplexType = this.getComplexType();
            LocalComplexType copyComplexType = ((LocalComplexType) copyBuilder.copy(sourceComplexType));
            copy.setComplexType(copyComplexType);
        }
        {
            List<Object> sourceIdentityConstraint;
            sourceIdentityConstraint = this.getIdentityConstraint();
            List<Object> copyIdentityConstraint = ((List<Object> ) copyBuilder.copy(sourceIdentityConstraint));
            copy.identityConstraint = null;
            List<Object> uniqueIdentityConstraintl = copy.getIdentityConstraint();
            uniqueIdentityConstraintl.addAll(copyIdentityConstraint);
        }
        {
            QName sourceType;
            sourceType = this.getType();
            QName copyType = ((QName) copyBuilder.copy(sourceType));
            copy.setType(copyType);
        }
        {
            QName sourceSubstitutionGroup;
            sourceSubstitutionGroup = this.getSubstitutionGroup();
            QName copySubstitutionGroup = ((QName) copyBuilder.copy(sourceSubstitutionGroup));
            copy.setSubstitutionGroup(copySubstitutionGroup);
        }
        {
            String sourceDefault;
            sourceDefault = this.getDefault();
            String copyDefault = ((String) copyBuilder.copy(sourceDefault));
            copy.setDefault(copyDefault);
        }
        {
            String sourceFixed;
            sourceFixed = this.getFixed();
            String copyFixed = ((String) copyBuilder.copy(sourceFixed));
            copy.setFixed(copyFixed);
        }
        {
            Boolean sourceNillable;
            sourceNillable = this.isNillable();
            Boolean copyNillable = ((Boolean) copyBuilder.copy(sourceNillable));
            copy.setNillable(copyNillable);
        }
        {
            Boolean sourceAbstract;
            sourceAbstract = this.isAbstract();
            Boolean copyAbstract = ((Boolean) copyBuilder.copy(sourceAbstract));
            copy.setAbstract(copyAbstract);
        }
        {
            List<String> sourceFinal;
            sourceFinal = this.getFinal();
            List<String> copyFinal = ((List<String> ) copyBuilder.copy(sourceFinal));
            copy._final = null;
            List<String> uniqueFinall = copy.getFinal();
            uniqueFinall.addAll(copyFinal);
        }
        {
            List<String> sourceBlock;
            sourceBlock = this.getBlock();
            List<String> copyBlock = ((List<String> ) copyBuilder.copy(sourceBlock));
            copy.block = null;
            List<String> uniqueBlockl = copy.getBlock();
            uniqueBlockl.addAll(copyBlock);
        }
        {
            FormChoice sourceForm;
            sourceForm = this.getForm();
            FormChoice copyForm = ((FormChoice) copyBuilder.copy(sourceForm));
            copy.setForm(copyForm);
        }
        {
            String sourceName;
            sourceName = this.getName();
            String copyName = ((String) copyBuilder.copy(sourceName));
            copy.setName(copyName);
        }
        {
            QName sourceRef;
            sourceRef = this.getRef();
            QName copyRef = ((QName) copyBuilder.copy(sourceRef));
            copy.setRef(copyRef);
        }
        {
            BigInteger sourceMinOccurs;
            sourceMinOccurs = this.getMinOccurs();
            BigInteger copyMinOccurs = ((BigInteger) copyBuilder.copy(sourceMinOccurs));
            copy.setMinOccurs(copyMinOccurs);
        }
        {
            String sourceMaxOccurs;
            sourceMaxOccurs = this.getMaxOccurs();
            String copyMaxOccurs = ((String) copyBuilder.copy(sourceMaxOccurs));
            copy.setMaxOccurs(copyMaxOccurs);
        }
        return copy;
    }

    public Object copyTo(Object target) {
        final CopyBuilder copyBuilder = new JAXBCopyBuilder();
        return copyTo(target, copyBuilder);
    }

}

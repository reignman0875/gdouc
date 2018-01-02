
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para Trade complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Trade">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="trade_num" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="trader_init" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trade_status_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="conclusion_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inhouse_ind" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="acct_num" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="acct_cont_num" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="acct_short_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="acct_ref_num" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="port_num" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="concluded_date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="contr_approv_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contr_date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="cr_anly_init" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="credit_term_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cp_gov_contr_ind" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contr_exch_method" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contr_cnfrm_method" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contr_tlx_hold_ind" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creation_date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="creator_init" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trade_mod_date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="trade_mod_init" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="invoice_cap_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="internal_agreement_ind" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="credit_status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="credit_res_exp_date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="contr_anly_init" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contr_status_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="max_order_num" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="is_long_term_ind" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="special_contract_num" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cargo_id_number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trans_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="internal_parent_trade_num" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="copy_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="product_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="econfirm_status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trade", propOrder = {
    "tradeNum",
    "traderInit",
    "tradeStatusCode",
    "conclusionType",
    "inhouseInd",
    "acctNum",
    "acctContNum",
    "acctShortName",
    "acctRefNum",
    "portNum",
    "concludedDate",
    "contrApprovType",
    "contrDate",
    "crAnlyInit",
    "creditTermCode",
    "cpGovContrInd",
    "contrExchMethod",
    "contrCnfrmMethod",
    "contrTlxHoldInd",
    "creationDate",
    "creatorInit",
    "tradeModDate",
    "tradeModInit",
    "invoiceCapType",
    "internalAgreementInd",
    "creditStatus",
    "creditResExpDate",
    "contrAnlyInit",
    "contrStatusCode",
    "maxOrderNum",
    "isLongTermInd",
    "specialContractNum",
    "cargoIdNumber",
    "transId",
    "internalParentTradeNum",
    "copyType",
    "productId",
    "econfirmStatus"
})
public class Trade {

    @XmlElement(name = "trade_num")
    protected int tradeNum;
    @XmlElement(name = "trader_init")
    protected String traderInit;
    @XmlElement(name = "trade_status_code")
    protected String tradeStatusCode;
    @XmlElement(name = "conclusion_type")
    protected String conclusionType;
    @XmlElement(name = "inhouse_ind")
    protected String inhouseInd;
    @XmlElement(name = "acct_num", required = true, type = Integer.class, nillable = true)
    protected Integer acctNum;
    @XmlElement(name = "acct_cont_num", required = true, type = Integer.class, nillable = true)
    protected Integer acctContNum;
    @XmlElement(name = "acct_short_name")
    protected String acctShortName;
    @XmlElement(name = "acct_ref_num")
    protected String acctRefNum;
    @XmlElement(name = "port_num", required = true, type = Integer.class, nillable = true)
    protected Integer portNum;
    @XmlElement(name = "concluded_date", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar concludedDate;
    @XmlElement(name = "contr_approv_type")
    protected String contrApprovType;
    @XmlElement(name = "contr_date", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar contrDate;
    @XmlElement(name = "cr_anly_init")
    protected String crAnlyInit;
    @XmlElement(name = "credit_term_code")
    protected String creditTermCode;
    @XmlElement(name = "cp_gov_contr_ind")
    protected String cpGovContrInd;
    @XmlElement(name = "contr_exch_method")
    protected String contrExchMethod;
    @XmlElement(name = "contr_cnfrm_method")
    protected String contrCnfrmMethod;
    @XmlElement(name = "contr_tlx_hold_ind")
    protected String contrTlxHoldInd;
    @XmlElement(name = "creation_date", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationDate;
    @XmlElement(name = "creator_init")
    protected String creatorInit;
    @XmlElement(name = "trade_mod_date", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar tradeModDate;
    @XmlElement(name = "trade_mod_init")
    protected String tradeModInit;
    @XmlElement(name = "invoice_cap_type")
    protected String invoiceCapType;
    @XmlElement(name = "internal_agreement_ind")
    protected String internalAgreementInd;
    @XmlElement(name = "credit_status")
    protected String creditStatus;
    @XmlElement(name = "credit_res_exp_date", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creditResExpDate;
    @XmlElement(name = "contr_anly_init")
    protected String contrAnlyInit;
    @XmlElement(name = "contr_status_code")
    protected String contrStatusCode;
    @XmlElement(name = "max_order_num", required = true, type = Short.class, nillable = true)
    protected Short maxOrderNum;
    @XmlElement(name = "is_long_term_ind")
    protected String isLongTermInd;
    @XmlElement(name = "special_contract_num")
    protected String specialContractNum;
    @XmlElement(name = "cargo_id_number")
    protected String cargoIdNumber;
    @XmlElement(name = "trans_id")
    protected int transId;
    @XmlElement(name = "internal_parent_trade_num", required = true, type = Integer.class, nillable = true)
    protected Integer internalParentTradeNum;
    @XmlElement(name = "copy_type")
    protected String copyType;
    @XmlElement(name = "product_id", required = true, type = Integer.class, nillable = true)
    protected Integer productId;
    @XmlElement(name = "econfirm_status")
    protected String econfirmStatus;

    /**
     * Obtiene el valor de la propiedad tradeNum.
     * 
     */
    public int getTradeNum() {
        return tradeNum;
    }

    /**
     * Define el valor de la propiedad tradeNum.
     * 
     */
    public void setTradeNum(int value) {
        this.tradeNum = value;
    }

    /**
     * Obtiene el valor de la propiedad traderInit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTraderInit() {
        return traderInit;
    }

    /**
     * Define el valor de la propiedad traderInit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTraderInit(String value) {
        this.traderInit = value;
    }

    /**
     * Obtiene el valor de la propiedad tradeStatusCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTradeStatusCode() {
        return tradeStatusCode;
    }

    /**
     * Define el valor de la propiedad tradeStatusCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTradeStatusCode(String value) {
        this.tradeStatusCode = value;
    }

    /**
     * Obtiene el valor de la propiedad conclusionType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConclusionType() {
        return conclusionType;
    }

    /**
     * Define el valor de la propiedad conclusionType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConclusionType(String value) {
        this.conclusionType = value;
    }

    /**
     * Obtiene el valor de la propiedad inhouseInd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInhouseInd() {
        return inhouseInd;
    }

    /**
     * Define el valor de la propiedad inhouseInd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInhouseInd(String value) {
        this.inhouseInd = value;
    }

    /**
     * Obtiene el valor de la propiedad acctNum.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAcctNum() {
        return acctNum;
    }

    /**
     * Define el valor de la propiedad acctNum.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAcctNum(Integer value) {
        this.acctNum = value;
    }

    /**
     * Obtiene el valor de la propiedad acctContNum.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAcctContNum() {
        return acctContNum;
    }

    /**
     * Define el valor de la propiedad acctContNum.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAcctContNum(Integer value) {
        this.acctContNum = value;
    }

    /**
     * Obtiene el valor de la propiedad acctShortName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctShortName() {
        return acctShortName;
    }

    /**
     * Define el valor de la propiedad acctShortName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctShortName(String value) {
        this.acctShortName = value;
    }

    /**
     * Obtiene el valor de la propiedad acctRefNum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctRefNum() {
        return acctRefNum;
    }

    /**
     * Define el valor de la propiedad acctRefNum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctRefNum(String value) {
        this.acctRefNum = value;
    }

    /**
     * Obtiene el valor de la propiedad portNum.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPortNum() {
        return portNum;
    }

    /**
     * Define el valor de la propiedad portNum.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPortNum(Integer value) {
        this.portNum = value;
    }

    /**
     * Obtiene el valor de la propiedad concludedDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getConcludedDate() {
        return concludedDate;
    }

    /**
     * Define el valor de la propiedad concludedDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setConcludedDate(XMLGregorianCalendar value) {
        this.concludedDate = value;
    }

    /**
     * Obtiene el valor de la propiedad contrApprovType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrApprovType() {
        return contrApprovType;
    }

    /**
     * Define el valor de la propiedad contrApprovType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrApprovType(String value) {
        this.contrApprovType = value;
    }

    /**
     * Obtiene el valor de la propiedad contrDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getContrDate() {
        return contrDate;
    }

    /**
     * Define el valor de la propiedad contrDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setContrDate(XMLGregorianCalendar value) {
        this.contrDate = value;
    }

    /**
     * Obtiene el valor de la propiedad crAnlyInit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrAnlyInit() {
        return crAnlyInit;
    }

    /**
     * Define el valor de la propiedad crAnlyInit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrAnlyInit(String value) {
        this.crAnlyInit = value;
    }

    /**
     * Obtiene el valor de la propiedad creditTermCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditTermCode() {
        return creditTermCode;
    }

    /**
     * Define el valor de la propiedad creditTermCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditTermCode(String value) {
        this.creditTermCode = value;
    }

    /**
     * Obtiene el valor de la propiedad cpGovContrInd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpGovContrInd() {
        return cpGovContrInd;
    }

    /**
     * Define el valor de la propiedad cpGovContrInd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpGovContrInd(String value) {
        this.cpGovContrInd = value;
    }

    /**
     * Obtiene el valor de la propiedad contrExchMethod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrExchMethod() {
        return contrExchMethod;
    }

    /**
     * Define el valor de la propiedad contrExchMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrExchMethod(String value) {
        this.contrExchMethod = value;
    }

    /**
     * Obtiene el valor de la propiedad contrCnfrmMethod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrCnfrmMethod() {
        return contrCnfrmMethod;
    }

    /**
     * Define el valor de la propiedad contrCnfrmMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrCnfrmMethod(String value) {
        this.contrCnfrmMethod = value;
    }

    /**
     * Obtiene el valor de la propiedad contrTlxHoldInd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrTlxHoldInd() {
        return contrTlxHoldInd;
    }

    /**
     * Define el valor de la propiedad contrTlxHoldInd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrTlxHoldInd(String value) {
        this.contrTlxHoldInd = value;
    }

    /**
     * Obtiene el valor de la propiedad creationDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationDate() {
        return creationDate;
    }

    /**
     * Define el valor de la propiedad creationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationDate(XMLGregorianCalendar value) {
        this.creationDate = value;
    }

    /**
     * Obtiene el valor de la propiedad creatorInit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatorInit() {
        return creatorInit;
    }

    /**
     * Define el valor de la propiedad creatorInit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatorInit(String value) {
        this.creatorInit = value;
    }

    /**
     * Obtiene el valor de la propiedad tradeModDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTradeModDate() {
        return tradeModDate;
    }

    /**
     * Define el valor de la propiedad tradeModDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTradeModDate(XMLGregorianCalendar value) {
        this.tradeModDate = value;
    }

    /**
     * Obtiene el valor de la propiedad tradeModInit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTradeModInit() {
        return tradeModInit;
    }

    /**
     * Define el valor de la propiedad tradeModInit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTradeModInit(String value) {
        this.tradeModInit = value;
    }

    /**
     * Obtiene el valor de la propiedad invoiceCapType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceCapType() {
        return invoiceCapType;
    }

    /**
     * Define el valor de la propiedad invoiceCapType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceCapType(String value) {
        this.invoiceCapType = value;
    }

    /**
     * Obtiene el valor de la propiedad internalAgreementInd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInternalAgreementInd() {
        return internalAgreementInd;
    }

    /**
     * Define el valor de la propiedad internalAgreementInd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInternalAgreementInd(String value) {
        this.internalAgreementInd = value;
    }

    /**
     * Obtiene el valor de la propiedad creditStatus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditStatus() {
        return creditStatus;
    }

    /**
     * Define el valor de la propiedad creditStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditStatus(String value) {
        this.creditStatus = value;
    }

    /**
     * Obtiene el valor de la propiedad creditResExpDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreditResExpDate() {
        return creditResExpDate;
    }

    /**
     * Define el valor de la propiedad creditResExpDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreditResExpDate(XMLGregorianCalendar value) {
        this.creditResExpDate = value;
    }

    /**
     * Obtiene el valor de la propiedad contrAnlyInit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrAnlyInit() {
        return contrAnlyInit;
    }

    /**
     * Define el valor de la propiedad contrAnlyInit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrAnlyInit(String value) {
        this.contrAnlyInit = value;
    }

    /**
     * Obtiene el valor de la propiedad contrStatusCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrStatusCode() {
        return contrStatusCode;
    }

    /**
     * Define el valor de la propiedad contrStatusCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrStatusCode(String value) {
        this.contrStatusCode = value;
    }

    /**
     * Obtiene el valor de la propiedad maxOrderNum.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getMaxOrderNum() {
        return maxOrderNum;
    }

    /**
     * Define el valor de la propiedad maxOrderNum.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setMaxOrderNum(Short value) {
        this.maxOrderNum = value;
    }

    /**
     * Obtiene el valor de la propiedad isLongTermInd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsLongTermInd() {
        return isLongTermInd;
    }

    /**
     * Define el valor de la propiedad isLongTermInd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsLongTermInd(String value) {
        this.isLongTermInd = value;
    }

    /**
     * Obtiene el valor de la propiedad specialContractNum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecialContractNum() {
        return specialContractNum;
    }

    /**
     * Define el valor de la propiedad specialContractNum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecialContractNum(String value) {
        this.specialContractNum = value;
    }

    /**
     * Obtiene el valor de la propiedad cargoIdNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCargoIdNumber() {
        return cargoIdNumber;
    }

    /**
     * Define el valor de la propiedad cargoIdNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCargoIdNumber(String value) {
        this.cargoIdNumber = value;
    }

    /**
     * Obtiene el valor de la propiedad transId.
     * 
     */
    public int getTransId() {
        return transId;
    }

    /**
     * Define el valor de la propiedad transId.
     * 
     */
    public void setTransId(int value) {
        this.transId = value;
    }

    /**
     * Obtiene el valor de la propiedad internalParentTradeNum.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getInternalParentTradeNum() {
        return internalParentTradeNum;
    }

    /**
     * Define el valor de la propiedad internalParentTradeNum.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setInternalParentTradeNum(Integer value) {
        this.internalParentTradeNum = value;
    }

    /**
     * Obtiene el valor de la propiedad copyType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCopyType() {
        return copyType;
    }

    /**
     * Define el valor de la propiedad copyType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCopyType(String value) {
        this.copyType = value;
    }

    /**
     * Obtiene el valor de la propiedad productId.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * Define el valor de la propiedad productId.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProductId(Integer value) {
        this.productId = value;
    }

    /**
     * Obtiene el valor de la propiedad econfirmStatus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEconfirmStatus() {
        return econfirmStatus;
    }

    /**
     * Define el valor de la propiedad econfirmStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEconfirmStatus(String value) {
        this.econfirmStatus = value;
    }

}

package com.wonders.fzb.legislate.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "WEGOV_EXCHANGE_PIVOT")
public class ExchangePivot {
	@Id
	@Column(name = "st_id", unique = true, nullable = false, length = 32)
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	private String id;
	@Column(name = "st_module")
	private String module;
	@Column(name = "st_type")
	private String type;
	@Column(name = "st_table_name")
	private String tableName;
	@Column(name = "st_info_id")
	private String infoId;
	@Column(name = "st_parser_class_name")
	private String parser_class_name;
	@Column(name = "st_send_user_id")
	private String sendUserId;
	@Column(name = "st_send_user_name")
	private String sendUserName;
	@Column(name = "st_file_path")
	private String filePath;
	@Column(name = "st_file_name")
	private String fileName;
	@Column(name = "bl_file_content")
	private byte[] fileContent;
	@Column(name = "st_summary")
	private String summary;
	@Column(name = "st_remark")
	private String remark;
	@Column(name = "dt_insert_date")
	private Date insertDate;
	@Column(name = "dt_transport_date")
	private Date transportDate;
	@Column(name = "dt_recv_date")
	private Date recvDate;
	@Column(name = "st_recv_status")
	private String recvStatus;
	@Column(name = "st_recv_user_id")
	private String recvUserId;
	@Column(name = "st_recv_user_name")
	private String recvUserName;
	@Column(name = "st_net")
	private String net;
	@Column(name = "st_op_type")
	private String opType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	public String getParser_class_name() {
		return parser_class_name;
	}
	public void setParser_class_name(String parser_class_name) {
		this.parser_class_name = parser_class_name;
	}
	public String getSendUserId() {
		return sendUserId;
	}
	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}
	public String getSendUserName() {
		return sendUserName;
	}
	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getFileContent() {
		return fileContent;
	}
	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public Date getTransportDate() {
		return transportDate;
	}
	public void setTransportDate(Date transportDate) {
		this.transportDate = transportDate;
	}
	public Date getRecvDate() {
		return recvDate;
	}
	public void setRecvDate(Date recvDate) {
		this.recvDate = recvDate;
	}
	public String getRecvStatus() {
		return recvStatus;
	}
	public void setRecvStatus(String recvStatus) {
		this.recvStatus = recvStatus;
	}
	public String getRecvUserId() {
		return recvUserId;
	}
	public void setRecvUserId(String recvUserId) {
		this.recvUserId = recvUserId;
	}
	public String getRecvUserName() {
		return recvUserName;
	}
	public void setRecvUserName(String recvUserName) {
		this.recvUserName = recvUserName;
	}
	public String getNet() {
		return net;
	}
	public void setNet(String net) {
		this.net = net;
	}
	public String getOpType() {
		return opType;
	}
	public void setOpType(String opType) {
		this.opType = opType;
	}
	
}

package com.aikshika.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblparentqueries", catalog = "dbschoolmanagementsystem")
public class Tblparentqueries implements java.io.Serializable  {

	private Integer intParentQueriesId;
	private String txtQuestion;
	private String txtAnswer;
	private String txtParentId;
	private Integer txtStatus;

	public Tblparentqueries() {
	}

	public Tblparentqueries(Integer intParentQueriesId, String txtQuestion, String txtAnswer, String txtParentId,
			Integer txtStatus) {
		this.intParentQueriesId = intParentQueriesId;
		this.txtQuestion = txtQuestion;
		this.txtAnswer = txtAnswer;
		this.txtParentId = txtParentId;
		this.txtStatus = txtStatus;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "intParentQueriesId", unique = true, nullable = false)
	public Integer getIntParentQueriesId() {
		return intParentQueriesId;
	}

	public void setIntParentQueriesId(Integer intParentQueriesId) {
		this.intParentQueriesId = intParentQueriesId;
	}

	@Column(name = "txtQuestion", length = 500)
	public String getTxtQuestion() {
		return txtQuestion;
	}

	public void setTxtQuestion(String txtQuestion) {
		this.txtQuestion = txtQuestion;
	}

	@Column(name = "txtAnswer", length = 1000)
	public String getTxtAnswer() {
		return txtAnswer;
	}

	public void setTxtAnswer(String txtAnswer) {
		this.txtAnswer = txtAnswer;
	}

	@Column(name = "txtParentId", length = 50)
	public String getTxtParentId() {
		return txtParentId;
	}

	public void setTxtParentId(String txtParentId) {
		this.txtParentId = txtParentId;
	}

	@Column(name = "txtStatus", length = 11)
	public Integer getTxtStatus() {
		return txtStatus;
	}

	public void setTxtStatus(Integer txtStatus) {
		this.txtStatus = txtStatus;
	}

}

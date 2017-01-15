package com.aikshika.entity;
// Generated Oct 17, 2016 10:09:49 AM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Mstquestionandanswer generated by hbm2java
 */
@Entity
@Table(name = "mstquestionandanswer", catalog = "dbschoolmanagementsystem")
public class Mstquestionandanswer implements java.io.Serializable {

	private Integer intQuestionAndAnswerId;
	private Mstregistration mstregistration;
	private Mststudclass mststudclass;
	private Mstsubject mstsubject;
	private String txtQuestion;
	private String txtAnswer;
	private Integer intTeacherId;

	public Mstquestionandanswer() {
	}

	public Mstquestionandanswer(Mstregistration mstregistration, Mststudclass mststudclass, Mstsubject mstsubject,
			String txtQuestion, String txtAnswer, Integer intTeacherId) {
		this.mstregistration = mstregistration;
		this.mststudclass = mststudclass;
		this.mstsubject = mstsubject;
		this.txtQuestion = txtQuestion;
		this.txtAnswer = txtAnswer;
		this.intTeacherId = intTeacherId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "intQuestionAndAnswerId", unique = true, nullable = false)
	public Integer getIntQuestionAndAnswerId() {
		return this.intQuestionAndAnswerId;
	}

	public void setIntQuestionAndAnswerId(Integer intQuestionAndAnswerId) {
		this.intQuestionAndAnswerId = intQuestionAndAnswerId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intRegistrationId")
	public Mstregistration getMstregistration() {
		return this.mstregistration;
	}

	public void setMstregistration(Mstregistration mstregistration) {
		this.mstregistration = mstregistration;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intClassId")
	public Mststudclass getMststudclass() {
		return this.mststudclass;
	}

	public void setMststudclass(Mststudclass mststudclass) {
		this.mststudclass = mststudclass;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intSubjectId")
	public Mstsubject getMstsubject() {
		return this.mstsubject;
	}

	public void setMstsubject(Mstsubject mstsubject) {
		this.mstsubject = mstsubject;
	}

	@Column(name = "txtQuestion", length = 1000)
	public String getTxtQuestion() {
		return this.txtQuestion;
	}

	public void setTxtQuestion(String txtQuestion) {
		this.txtQuestion = txtQuestion;
	}

	@Column(name = "txtAnswer", length = 2000)
	public String getTxtAnswer() {
		return this.txtAnswer;
	}

	public void setTxtAnswer(String txtAnswer) {
		this.txtAnswer = txtAnswer;
	}

	@Column(name = "intTeacherId")
	public Integer getIntTeacherId() {
		return this.intTeacherId;
	}

	public void setIntTeacherId(Integer intTeacherId) {
		this.intTeacherId = intTeacherId;
	}

}

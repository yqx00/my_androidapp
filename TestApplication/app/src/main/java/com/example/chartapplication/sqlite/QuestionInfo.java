package com.example.chartapplication.sqlite;

public class QuestionInfo {

	private String question;

	public QuestionInfo()
	{

	}
	public QuestionInfo( String queValue )
	{
		question=queValue;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}
package com.example.chartapplication.model;

public class Questions {
   String question="";
  int answer=0;
  boolean clickResult=false;

  public String getQuestion() {
    if (question==null)
      question="";
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public int getAnswer() {
    return answer;
  }

  public void setAnswer(int answer) {
    this.answer = answer;
  }

  public void setAnswerResult(boolean result) {
    clickResult=result;
  }
}
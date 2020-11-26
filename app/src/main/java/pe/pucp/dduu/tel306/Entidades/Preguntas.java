package pe.pucp.dduu.tel306.Entidades;

public class Preguntas {
    private int id ;
    private String questionText;
    private String questionDate;
    private Respuestas[] answers;

    public Preguntas(int id , String questionText, String questionDate){
        this.id = id ;
        this.questionText = questionText;
        this.questionDate = questionDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(String questionDate) {
        this.questionDate = questionDate;
    }


    public Respuestas[] getAnswers() {
        return answers;
    }

    public void setAnswers(Respuestas[] answers) {
        this.answers = answers;
    }
}

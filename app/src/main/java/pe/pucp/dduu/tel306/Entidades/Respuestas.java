package pe.pucp.dduu.tel306.Entidades;

public class Respuestas {
    private int id;
    private String answerText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }


    public Respuestas(int id, String answerText) {
        this.id = id;
        this.answerText = answerText;
    }
    public Respuestas(){}
}

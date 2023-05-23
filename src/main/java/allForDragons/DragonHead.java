package allForDragons;

public class DragonHead {
    private Double eyesCount; //Поле не может быть null
    public Double getEyesCount() {
        return eyesCount;
    }

    public void setEyesCount(Double eyesCount) {
        this.eyesCount = eyesCount;
    }

    public DragonHead(Double eyesCount){
        this.eyesCount=eyesCount;
    }
}
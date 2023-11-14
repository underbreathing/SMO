package by.underBreathing.simulationModel.model;

import java.util.Objects;

public class Bid {
    private final int numberOfProducer;
    private final int number;//номер сообщения

    public Bid(final int numberOfProducer,final int number){
        this.numberOfProducer = numberOfProducer;
        this.number = number;
    }



    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(this.getClass() != obj.getClass()){
            return false;
        }
        return Objects.equals(this.number, ((Bid) obj).number);

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.number);
    }

    @Override
    public String toString() {
        return "Bid " + numberOfProducer + "-" + number;
    }
}

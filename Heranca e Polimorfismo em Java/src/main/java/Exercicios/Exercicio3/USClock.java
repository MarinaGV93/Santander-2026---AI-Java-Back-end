package Exercicios.Exercicio3;

public non-sealed class USClock extends Clock{
    private String periodIndicator;

    public String getPeriodIndicator() {
        return periodIndicator;
    }

    public void setAfterMidday(){
        this.periodIndicator = "PM";
    }

    public void setBeforeMidday(){
        this.periodIndicator = "AM";
    }


    public void setHour(int hour) {
        //Periodo vai receber AM
        setBeforeMidday();

        //Se a hora for maior a 12 e for menor ou igual a 23
        if ((hour > 12) && (hour <= 23)){
            //Periodo vai receber PM
            setAfterMidday();

            //Subtrai a hora por 12
            this.hour = hour - 12;
        } else
        //Se a hora for maior ou igual a 24
        if ((hour >= 24)){

            //Retorna 0
            this.hour = 0;
        } else {
            this.hour = hour;
        }
    }

    //Met odo de conversão
    @Override
    Clock convert(final Clock clock) {
        this.second = clock.getSecond();
        this.minute = clock.getMinute();

        switch (clock){
            case USClock usClock -> {
                this.hour = usClock.getHour();
                this.periodIndicator = usClock.getPeriodIndicator();
            }

            case BRLClock brClock -> this.setHour(brClock.getHour());
        }
        return this;
    }

    @Override
    public String getTime() {
        return super.getTime() + " " + this.periodIndicator;
    }
}

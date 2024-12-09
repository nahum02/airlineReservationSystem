package bizlogic;

import DB.CustomerData;
import javafx.beans.property.SimpleStringProperty;  //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text

public class Flights extends CustomerData {
    private SimpleStringProperty flightNum;
    private SimpleStringProperty flightDate;
    private SimpleStringProperty departTime;
    private SimpleStringProperty departFrom;
    private SimpleStringProperty arrivalTo;
    private SimpleStringProperty airline;
    private SimpleStringProperty seatPrice;

    private String flightNums;
    private String flightDates;
    private String departTimes;
    private String departFroms;
    private String arrivalTos;
    private String airlines;
    private String seatPrices;

    private String date;
    private String from;
    private String to;

    public Flights() {

    }


    public Flights (String flightNum, String flightDate, String departTime, String departFrom,
                    String arrivalTo, String airline, String seatPrice) {

        this.flightNum = new SimpleStringProperty (flightNum);
        this.flightDate = new SimpleStringProperty (flightDate);
        this.departTime = new SimpleStringProperty (departTime);
        this.departFrom = new SimpleStringProperty (departFrom);
        this.arrivalTo = new SimpleStringProperty (arrivalTo);
        this.airline = new SimpleStringProperty (airline);
        this.seatPrice = new SimpleStringProperty (seatPrice);


    }

    public String getFlightNum() {
        return flightNum.get();
    }


    public void setFlightNum(String flightNum) {
        this.flightNum = new SimpleStringProperty (flightNum);
    }


    public String getFlightDate() {
        return flightDate.get();
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = new SimpleStringProperty (flightDate);
    }


    public String getDepartTime() {
        return departTime.get();
    }


    public void setDepartTime(String departTime) {
        this.departTime = new SimpleStringProperty (departTime);
    }


    public String getDepartFrom() {
        return departFrom.get();
    }


    public void setDepartFrom(String departFrom) {
        this.departFrom = new SimpleStringProperty (departFrom);
    }


    public String getArrivalTo() {
        return arrivalTo.get();
    }


    public void setArrivalTo(String arrivalTo) {
        this.arrivalTo = new SimpleStringProperty (arrivalTo);
    }

    public String getAirline() {
        return airline.get();
    }


    public void setAirline(String airline) {
        this.airline = new SimpleStringProperty (airline);
    }


    public String getSeatPrice() {
        return seatPrice.get();
    }


    public void setSeatPrice(String seatPrice) {
        this.seatPrice = new SimpleStringProperty (seatPrice);
    }



    public boolean flightFilledCorrect(String flightNums, String flightDates, String departTimes,
                                       String departFroms, String arrivalTos, String airlines,
                                       String seatPrices) throws Exception {

        if (flightNums.trim().equals("") || flightDates.trim().equals("") ||
                departTimes.trim().equals("") || departFroms.trim().equals("") ||
                arrivalTos.trim().equals("") || airlines.trim().equals("") ||
                seatPrices.trim().equals("")) {

            return false;
        }
        else {

            this.flightNums = flightNums;
            this.flightDates = flightDates;
            this.departTimes = departTimes;
            this.departFroms = departFroms;
            this.arrivalTos = arrivalTos;
            this.airlines = airlines;
            this.seatPrices = seatPrices;

            return true;
        }
    }
    public void addFlights() throws Exception {
        addFlight(flightNums, flightDates, departTimes, departFroms,
                arrivalTos, airlines, seatPrices);

    }


    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public String getFrom() {
        return from;
    }


    public void setFrom(String from) {
        this.from = from;
    }


    public String getTo() {
        return to;
    }


    public void setTo(String to) {
        this.to = to;
    }

}
